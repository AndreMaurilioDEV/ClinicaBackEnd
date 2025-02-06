package com.betrybe.hospitalExample.service.expections;

public class PacienteNotFoundException extends NotFoundException {
  public PacienteNotFoundException() {
    super("Paciente não encontrado!");
  }
}
