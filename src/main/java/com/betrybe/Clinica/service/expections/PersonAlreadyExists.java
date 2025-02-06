package com.betrybe.Clinica.service.expections;

public class PersonAlreadyExists extends RuntimeException {
  public PersonAlreadyExists() {
    super("Usuário já existe!!");
  }
}
