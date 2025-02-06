package com.betrybe.hospitalExample.service.expections;

public class PersonNotFoundException extends NotFoundException {
  public PersonNotFoundException() {
    super("Pessoa não cadastrada");
  }
}
