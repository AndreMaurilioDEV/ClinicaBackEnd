package com.betrybe.hospitalExample.service.expections;

public class MedicoNotFoundException extends NotFoundException {
  public MedicoNotFoundException() {
    super("Médico não encontrado!");
  }
}
