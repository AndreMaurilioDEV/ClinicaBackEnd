package com.betrybe.Clinica.service.expections;

public class PacienteNotFoundException extends NotFoundException {
  public PacienteNotFoundException() {
    super("Paciente não encontrado!");
  }
}
