package com.betrybe.Clinica.service.expections;

public class InvalidEspecialidadeMedica extends NotFoundException {
  public InvalidEspecialidadeMedica() {
    super("Especialidade Médica Inválida!!");
  }
}
