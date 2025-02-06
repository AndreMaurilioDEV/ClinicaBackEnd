package com.betrybe.Clinica.service;

import com.betrybe.Clinica.entity.Person;
import com.betrybe.Clinica.repository.ConfirmacaoRepository;
import com.betrybe.Clinica.repository.PersonRepository;
import com.betrybe.Clinica.security.Role;
import com.betrybe.Clinica.service.expections.InvalidToken;
import com.betrybe.Clinica.service.expections.PersonAlreadyExists;
import com.betrybe.Clinica.service.expections.PersonNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PersonService implements UserDetailsService {

  private final PersonRepository personRepository;
  private final ConfirmacaoRepository confirmacaoRepository;

  @Autowired
  private EmailService emailService;

  @Autowired
  public PersonService(PersonRepository personRepository, ConfirmacaoRepository confirmacaoRepository) {
    this.personRepository = personRepository;
    this.confirmacaoRepository = confirmacaoRepository;
  }

  public Person findById(Long id) throws PersonNotFoundException {
    return personRepository.findById(id).orElseThrow(PersonNotFoundException::new);
  }


  public Person findByEmail(String username) throws PersonNotFoundException {
    return personRepository.findByUsername(username).orElseThrow(PersonNotFoundException::new);
  }

  public List<Person> findAll() {
    return personRepository.findAll();
  }

  public Person removePerson(Long id) throws PersonNotFoundException {
    Person personFromRepository = findById(id);
    personRepository.delete(personFromRepository);
    return personFromRepository;
  }


  public Person createPerson(Person person, Role role) throws PersonAlreadyExists {
    if(this.personRepository.findByUsername(person.getUsername()).isPresent()) {
        throw new PersonAlreadyExists();
    }

    String tempPassword = UUID.randomUUID().toString().substring(0,8);
    String hashedPassword = new BCryptPasswordEncoder()
            .encode(tempPassword);

    System.out.println("Senha temporária: " + tempPassword);
    System.out.println("Senha hash: " + hashedPassword);

    person.setPassword(hashedPassword);
    person.setRole(role);
    person.setIsConfirmed(false);

    personRepository.save(person);

    emailService.sendEmail(person.getUsername(),
            "Novo Cadastro Efetuado",
            "<h3>Ola," + person.getName() + "você está recebendo um email de cadastro</h3>" +
                    " <p>Use a senha aleatória" + tempPassword + " para efetuar o primeiro login. </p>" +
                    "<p><strong>Após isso, faça a alteração da senha nas configurações da sua conta, se preferir.</strong></p>"
    );
    return person;
  }

  public Person generateResetToken(String email) throws PersonNotFoundException{
    Person person = personRepository.findByUsername(email).orElseThrow(PersonNotFoundException::new);
    String resetToken = UUID.randomUUID().toString().substring(0,6);
    person.setResetToken(resetToken);
    personRepository.save(person);
    String resetLink = "https://.com/redefinir-senha?token=" + resetToken;
    emailService.sendEmail(person.getUsername(),
            "Redefinição de Senha",
            "<h3>Olá, " + person.getName() + "</h3>" +
                    "<p>Você solicitou a redefinição da sua senha.</p>" +
                    "<p><strong>Clique no botão abaixo para redefinir sua senha:</strong></p>" +
                    "<a href='" + resetLink + "' " +
                    "style='display: inline-block; padding: 10px 20px; font-size: 16px; " +
                    "color: white; background-color: #28a745; text-decoration: none; border-radius: 5px;'>Redefinir Senha</a>" +
                    "<p>Se você não solicitou essa alteração, ignore este e-mail.</p>"
    );

    return person;
  }


  public Person updatePassword(Long id, String newPassword, String currentPassword) throws PersonNotFoundException {
    Person personFromDB = findById(id);
    if (!new BCryptPasswordEncoder().matches(currentPassword, personFromDB.getPassword())) {
      throw new IllegalArgumentException("Senha atual não está correta.");
    }

    if (newPassword == null || newPassword.length() < 8) {
      throw new IllegalArgumentException("A senha deve ter pelo menos 8 caracteres.");
    }
    String hashedPassword = new BCryptPasswordEncoder().encode(newPassword);
    personFromDB.setPassword(hashedPassword);
    personFromDB.setIsConfirmed(true);
    emailService.sendEmail(personFromDB.getUsername(),
            "Senha alterada com sucesso",
            "<h3>Olá,"+ personFromDB.getName() + "</h3>"+
                      "<p>Você alterou sua senha com sucesso.</p>" +
                      "<p>Se não foi você, entre em contato com o suporte imediatamente.</p>"
            );
    return personRepository.save(personFromDB);
  }

  public Person changePassword(String token, String newPassword) throws PersonNotFoundException {
    Person person = personRepository.findByResetToken(token)
            .orElseThrow(() -> new IllegalArgumentException("Token inválido ou expirado"));

    if (newPassword == null || newPassword.length() < 8) {
      throw new IllegalArgumentException("A senha deve ter pelo menos 8 caracteres.");
    }

    String hashedPassword = new BCryptPasswordEncoder().encode(newPassword);
    person.setPassword(hashedPassword);
    person.setResetToken(null);

    return personRepository.save(person);
  }


  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Person person = personRepository.findByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));

    List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority("ROLE_" + person.getRole().name()));

    return new User(person.getUsername(), person.getPassword(), authorities);
  }

}
