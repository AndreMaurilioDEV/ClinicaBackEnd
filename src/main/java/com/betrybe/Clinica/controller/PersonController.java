package com.betrybe.Clinica.controller;

import com.betrybe.Clinica.controller.dto.*;
import com.betrybe.Clinica.entity.Person;
import com.betrybe.Clinica.repository.PersonRepository;
import com.betrybe.Clinica.security.Role;
import com.betrybe.Clinica.service.PersonService;
import com.betrybe.Clinica.service.expections.PersonAlreadyExists;
import com.betrybe.Clinica.service.expections.PersonNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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
  public List<PersonDto> listAll() throws PersonNotFoundException {
    List<Person> allPersons = personService.findAll();
    return allPersons.stream().map(PersonDto::fromEntity).toList();
  }

  @DeleteMapping("/{idPerson}")
  public ResponseEntity<Void> removePerson(@PathVariable Long idPerson) {
    return ResponseEntity.status(204).body(null);
  }

  
  @GetMapping("/user-details/{username}")
  @Secured("ROLE_EMPLOYEE")
  public PersonDto listByEmail(@PathVariable String username) throws PersonNotFoundException {
    return PersonDto.fromEntity(personService.findByEmail(username));
  }

  @PostMapping("/employees")
  @ResponseStatus(HttpStatus.CREATED)
  public PersonDto createNewEmployee(@RequestBody PersonCreationDto personCreationDto)
          throws PersonAlreadyExists {
    Person person = personCreationDto.toEntity();
    return PersonDto.fromEntity(personService.createPerson(person, Role.EMPLOYEE));
  }


}
