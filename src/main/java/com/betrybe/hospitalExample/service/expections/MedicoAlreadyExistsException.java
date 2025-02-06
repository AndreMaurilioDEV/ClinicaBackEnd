package com.betrybe.hospitalExample.service.expections;

public class MedicoAlreadyExistsException extends RuntimeException {
  public MedicoAlreadyExistsException() {
    super("Já existe um médico com este CRM!");
  }
}
