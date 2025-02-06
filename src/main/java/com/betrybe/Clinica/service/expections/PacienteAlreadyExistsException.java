package com.betrybe.Clinica.service.expections;

public class PacienteAlreadyExistsException extends NotFoundException {
  public PacienteAlreadyExistsException() {
    super("Já existe paciente com este CPF!");
  }
}
