package com.betrybe.hospitalExample.service.expections;

public class PersonAlreadyExists extends RuntimeException {
  public PersonAlreadyExists() {
    super("Usuário já existe!!");
  }
}
