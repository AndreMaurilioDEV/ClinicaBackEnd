package com.betrybe.Clinica.service;

import com.betrybe.Clinica.entity.Person;
import com.betrybe.Clinica.repository.PersonRepository;
import com.betrybe.Clinica.security.Role;
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

    System.out.println("Senha temporária: " + tempPassword);
    System.out.println("Senha hash: " + hashedPassword);

    person.setPassword(hashedPassword);
    person.setRole(role);
    person.setIsConfirmed(false);

    personRepository.save(person);

    emailService.sendEmail(person.getUsername(),
            "- - Novo Cadastro - - ",
            "Você está recebendo um email de cadastro. Use a senha aleatória" + tempPassword + " para efetuar o login. " +
                    "Se preferir faça a alteração da senha nas configurações da sua conta."
    );
    return person;
  }


  public Person updatePassword(Long id, String newPassword) throws PersonNotFoundException {
    if (newPassword == null || newPassword.length() < 8) {
      throw new IllegalArgumentException("A senha deve ter pelo menos 8 caracteres.");
    }
    Person personFromDB = findById(id);
    String hashedPassword = new BCryptPasswordEncoder().encode(newPassword);
    personFromDB.setPassword(hashedPassword);
    personFromDB.setIsConfirmed(true);
    return personRepository.save(personFromDB);
  }


  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Person person = personRepository.findByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));

    List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority("ROLE_" + person.getRole().name()));

    return new User(person.getUsername(), person.getPassword(), authorities);
  }

}
