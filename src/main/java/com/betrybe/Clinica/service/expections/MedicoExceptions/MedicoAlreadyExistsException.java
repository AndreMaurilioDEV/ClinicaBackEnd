package com.betrybe.Clinica.service.expections.MedicoExceptions;

public class MedicoAlreadyExistsException extends RuntimeException {
  public MedicoAlreadyExistsException() {
    super("Já existe um médico com este CRM!");
  }
}
