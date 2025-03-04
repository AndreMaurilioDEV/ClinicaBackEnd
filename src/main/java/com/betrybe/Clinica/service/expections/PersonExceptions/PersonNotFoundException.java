package com.betrybe.Clinica.service.expections.PersonExceptions;

import com.betrybe.Clinica.service.expections.NotFoundException;

public class PersonNotFoundException extends NotFoundException {
  public PersonNotFoundException() {
    super("Pessoa não cadastrada");
  }
}
