package com.betrybe.hospitalExample.controller;

import com.betrybe.hospitalExample.controller.dto.*;
import com.betrybe.hospitalExample.entity.Paciente;
import com.betrybe.hospitalExample.entity.Person;
import com.betrybe.hospitalExample.repository.PersonRepository;
import com.betrybe.hospitalExample.security.Role;
import com.betrybe.hospitalExample.service.PersonService;
import com.betrybe.hospitalExample.service.expections.MedicoNotFoundException;
import com.betrybe.hospitalExample.service.expections.PacienteNotFoundException;
import com.betrybe.hospitalExample.service.expections.PersonAlreadyExists;
import com.betrybe.hospitalExample.service.expections.PersonNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.annotation.Secured;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/persons")
@CrossOrigin(origins = "*")
public class PersonController {

  private final PersonService personService;

  @Autowired
  public PersonController(PersonService personService) {
    this.personService = personService;
  }

  @GetMapping
  public List<PersonDto> getAll() throws PersonNotFoundException {
    List<Person> allPersons = personService.findAll();
    return allPersons.stream().map(PersonDto::fromEntity).toList();
  }

  @DeleteMapping("/{idPerson}")
  public PersonDto removePerson(@PathVariable Long idPerson) throws PersonNotFoundException {
    return PersonDto.fromEntity(personService.removePerson(idPerson));
  }

  
  @GetMapping("/user-details/{username}")
  @Secured("ROLE_EMPLOYEE")
  public PersonDto getByEmail(@PathVariable String username) throws PersonNotFoundException {
    return PersonDto.fromEntity(personService.findByEmail(username));
  }

  @PostMapping("/employees")
  @ResponseStatus(HttpStatus.CREATED)
  public PersonDto createNewEmployee(@RequestBody PersonCreationDto personCreationDto)
          throws PersonAlreadyExists {
    Person person = personCreationDto.toEntity();
    return PersonDto.fromEntity(personService.createPerson(person, Role.EMPLOYEE));
  }

  @PostMapping("/admins")
  @ResponseStatus(HttpStatus.CREATED)
  public PersonDto createNewAdmin(@RequestBody PersonCreationDto personCreationDto)
          throws PersonAlreadyExists {
    Person person = new Person(personCreationDto.username(), personCreationDto.name(), null, null, null);
    return PersonDto.fromEntity(personService.createPerson(person, Role.ADMIN));
  }

  @PutMapping("/{id}/change-password")
  public ResponseEntity<Void> updatePerson(@PathVariable Long id, @RequestBody PasswordChangeDto passwordChangeDto)
          throws PersonNotFoundException {
    personService.updatePassword(id, passwordChangeDto.newPassword());
    return ResponseEntity.noContent().build();
  }

}
