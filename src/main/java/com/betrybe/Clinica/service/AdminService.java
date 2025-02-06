package com.betrybe.Clinica.service;

import com.betrybe.Clinica.entity.Person;
import com.betrybe.Clinica.repository.PersonRepository;
import com.betrybe.Clinica.security.Role;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

  @Value("${ADMIN_NAME}")
  private String adminName;

  @Value("${ADMIN_EMAIL}")
  private String adminEmail;

  @Value("${ADMIN_PASSWORD}")
  private String adminPassword;

  private final PersonRepository personRepository;
  private final PasswordEncoder passwordEncoder;

  public AdminService(PersonRepository personRepository, PasswordEncoder passwordEncoder) {
    this.personRepository = personRepository;
    this.passwordEncoder = passwordEncoder;
  }

  @Transactional
  public void createAdmin() {
    if (personRepository.findByUsername(adminEmail).isEmpty()) {
    Person person = new Person();
    person.setName(adminName);
    person.setUsername(adminEmail);
    person.setPassword(passwordEncoder.encode(adminPassword));
    person.setRole(Role.ADMIN);
    personRepository.save(person);
    System.out.println("Usuário Admin criado com sucesso!");
  }
  }
}
