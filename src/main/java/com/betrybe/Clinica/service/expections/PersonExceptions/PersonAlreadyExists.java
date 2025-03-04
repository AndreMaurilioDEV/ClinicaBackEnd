package com.betrybe.Clinica.service.expections.PersonExceptions;

public class PersonAlreadyExists extends RuntimeException {
  public PersonAlreadyExists() {
    super("Usuário já existe!!");
  }
}
