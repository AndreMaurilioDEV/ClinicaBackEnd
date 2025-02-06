package com.betrybe.Clinica.service.expections;

public class MedicoNotFoundException extends NotFoundException {
  public MedicoNotFoundException() {
    super("Médico não encontrado!");
  }
}
