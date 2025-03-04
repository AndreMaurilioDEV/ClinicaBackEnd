package com.betrybe.Clinica.service.expections.MedicoExceptions;

import com.betrybe.Clinica.service.expections.NotFoundException;

public class InvalidEspecialidadeMedica extends IllegalArgumentException {
  public InvalidEspecialidadeMedica() {
    super("Especialidade Médica Inválida!!");
  }
}
