package com.betrybe.Clinica.service;

import com.betrybe.Clinica.entity.Person;
import com.betrybe.Clinica.repository.PersonRepository;
import com.betrybe.Clinica.security.Role;
import com.betrybe.Clinica.service.expections.EmailNotFound;
import com.betrybe.Clinica.service.expections.PersonAlreadyExists;
import com.betrybe.Clinica.service.expections.PersonNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class PersonService implements UserDetailsService {

  private final PersonRepository personRepository;

  @Autowired
  private EmailService emailService;

  @Autowired
  public PersonService(PersonRepository personRepository) {
    this.personRepository = personRepository;
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
    person.setPassword(hashedPassword);
    person.setRole(role);
    person.setIsConfirmed(false);
    personRepository.save(person);
    sendCreateAccountEmail(person, tempPassword);
    return person;
  }

  public Person generateResetToken(String email) throws EmailNotFound {
    Person person = personRepository.findByUsername(email).orElseThrow(EmailNotFound::new);
    String resetToken = UUID.randomUUID().toString().substring(0,6);
    person.setResetToken(resetToken);
    personRepository.save(person);
    sendRecoveryEmail(person, person.getResetToken());
    return person;
  }

  public Person updatePassword(String email, String newPassword, String currentPassword) throws PersonNotFoundException {
    Person personFromDB = findByEmail(email);
    if (!new BCryptPasswordEncoder().matches(currentPassword, personFromDB.getPassword())) {
      throw new IllegalArgumentException("Senha atual não está correta.");
    }
    if (newPassword == null || newPassword.length() < 8) {
      throw new IllegalArgumentException("A senha deve ter pelo menos 8 caracteres.");
    }
    String hashedPassword = new BCryptPasswordEncoder().encode(newPassword);
    personFromDB.setPassword(hashedPassword);
    personFromDB.setIsConfirmed(true);
    sendUpdatePasswordEmail(personFromDB);
    return personRepository.save(personFromDB);
  }

  public Person changePassword(String token, String newPassword) {
    Person person = personRepository.findByResetToken(token)
            .orElseThrow(() -> new IllegalArgumentException("Código expirado"));
    if (newPassword == null || newPassword.length() < 8) {
      throw new IllegalArgumentException("A senha deve ter pelo menos 8 caracteres.");
    }
    String hashedPassword = new BCryptPasswordEncoder().encode(newPassword);
    person.setPassword(hashedPassword);
    person.setResetToken(null);
    return personRepository.save(person);
  }

  public Person validateTokenReset(String token) {
    Person person = personRepository.findByResetToken(token).orElseThrow(() ->
            new ResponseStatusException(HttpStatus.BAD_REQUEST, "Código inválido!!"));
    if (person.getLastResetRequest().plusMinutes(2).isBefore(LocalDateTime.now())) {
      throw new ResponseStatusException(HttpStatus.GONE, "O código expirou, solicite novamente!!");
    }
   return person;
  }

  private void sendRecoveryEmail(Person person, String token) {
      String resetLink = "http://localhost:5173/verificacao";
      String message = "<h3>Olá, " + person.getName() + "</h3>" +
              "<p>Você solicitou a redefinição da sua senha.</p>" +
              "<p>Use o código de confirmação" + token + " para redefinir a senha</p>" +
              "<p><strong>Clique no botão abaixo para redefinir sua senha:</strong></p>" +
              "<a href='" + resetLink + "' " +
              "style='display: inline-block; padding: 10px 20px; font-size: 16px; " +
              "color: white; background-color: #28a745; text-decoration: none; border-radius: 5px;'>Redefinir Senha</a>" +
              "<p>Se você não solicitou essa alteração, ignore este e-mail.</p>";
      emailService.sendEmail(person.getUsername(), "Confirmar Redefinição de senha", message);
  }

  private void sendCreateAccountEmail(Person person, String tempPassword) {
      String message = "<h3>Ola," + person.getName() + "você está recebendo um email de cadastro</h3>" +
              " <p>Use a senha aleatória" + tempPassword + " para efetuar o primeiro login. </p>" +
              "<p><strong>Após isso, faça a alteração da senha nas configurações da sua conta.</strong></p>";
      emailService.sendEmail(person.getUsername(), "Novo Cadastro", message);
  }

  private void sendUpdatePasswordEmail(Person person) {
    String message = "<h3>Olá,"+ person.getName() + "</h3>"+
            "<p>Você alterou sua senha com sucesso.</p>" +
            "<p>Se não foi você, entre em contato com o suporte imediatamente.</p>";
    emailService.sendEmail(person.getUsername(), "Senha Alterada", message);
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Person person = personRepository.findByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));

    List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority("ROLE_" + person.getRole().name()));

    return new User(person.getUsername(), person.getPassword(), authorities);
  }

}
