package com.betrybe.Clinica.service.expections.PacienteExceptions;

import com.betrybe.Clinica.service.expections.NotFoundException;

public class PacienteNotFoundException extends NotFoundException {
  public PacienteNotFoundException() {
    super("Paciente não encontrado!");
  }
}
