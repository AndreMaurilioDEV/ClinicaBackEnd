package com.betrybe.Clinica.controller;

import com.betrybe.Clinica.controller.dto.*;
import com.betrybe.Clinica.entity.Person;
import com.betrybe.Clinica.security.Role;
import com.betrybe.Clinica.service.PersonService;
import com.betrybe.Clinica.service.expections.PersonAlreadyExists;
import com.betrybe.Clinica.service.expections.PersonNotFoundException;
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

  @PostMapping("/forgot-password")
  public PersonDto forgotPassword(@RequestBody PasswordResetRequest request) throws PersonNotFoundException {
    return  PersonDto.fromEntity(personService.generateResetToken(request.email()));
  }

  @PostMapping("/reset-password")
  public PersonDto resetPassword(@RequestBody PasswordResetDto request) throws PersonNotFoundException {
    return  PersonDto.fromEntity(personService.changePassword(request.token(),request.email()));
  }

  @PutMapping("/change-password")
  public ResponseEntity<Void> updatePassword(@RequestParam Long id, @RequestParam String currentPassword, @RequestParam String newPassword)
          throws PersonNotFoundException {
    personService.updatePassword(id, newPassword, currentPassword);
    return ResponseEntity.noContent().build();
  }

}
