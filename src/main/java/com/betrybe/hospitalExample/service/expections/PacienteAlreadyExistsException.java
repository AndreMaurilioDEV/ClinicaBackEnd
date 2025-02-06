package com.betrybe.hospitalExample.service.expections;

public class PacienteAlreadyExistsException extends NotFoundException {
  public PacienteAlreadyExistsException() {
    super("Já existe paciente com este CPF!");
  }
}
