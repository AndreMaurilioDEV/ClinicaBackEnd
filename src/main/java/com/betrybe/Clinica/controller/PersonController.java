package com.betrybe.Clinica.controller;

import com.betrybe.Clinica.controller.dto.*;
import com.betrybe.Clinica.entity.Person;
import com.betrybe.Clinica.repository.PersonRepository;
import com.betrybe.Clinica.security.Role;
import com.betrybe.Clinica.service.PersonService;
import com.betrybe.Clinica.service.expections.PersonExceptions.PersonAlreadyExists;
import com.betrybe.Clinica.service.expections.PersonExceptions.PersonNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/persons")
@CrossOrigin(origins = "*")
public class PersonController {

  private final PersonService personService;
  private final PersonRepository personRepository;

  @Autowired
  public PersonController(PersonService personService, PersonRepository personRepository) {
    this.personRepository = personRepository;
    this.personService = personService;
  }

  @GetMapping
  public ResponseEntity<List<PersonDto>> listAll() {
    List<Person> allPersons = personService.findAll();
    List<PersonDto> personDtos = allPersons.stream().map(PersonDto::fromEntity).toList();
    return ResponseEntity.ok(personDtos);
  }

  @DeleteMapping("/{idPerson}")
  public ResponseEntity<Void> removePerson(@PathVariable Long idPerson) throws PersonNotFoundException {
    personService.removePerson(idPerson);
    return ResponseEntity.noContent().build();
  }

  
  @GetMapping("/user-details/{username}")
  @Secured("ROLE_EMPLOYEE")
  public ResponseEntity<PersonDto> listByEmail(@PathVariable String username) throws PersonNotFoundException {
    PersonDto personDto = PersonDto.fromEntity(personService.findByEmail(username));
    return ResponseEntity.ok(personDto);
  }

  @PostMapping("/employees")
  public ResponseEntity<PersonDto> createNewEmployee(@RequestBody PersonCreationDto personCreationDto) {
    Person person = personCreationDto.toEntity();
    Person savePerson = personService.createPerson(person, Role.EMPLOYEE);
    return ResponseEntity.status(HttpStatus.CREATED).body(PersonDto.fromEntity(savePerson));
  }


}
