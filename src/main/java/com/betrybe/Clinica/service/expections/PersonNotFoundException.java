package com.betrybe.Clinica.service.expections;

public class PersonNotFoundException extends NotFoundException {
  public PersonNotFoundException() {
    super("Pessoa não cadastrada");
  }
}
