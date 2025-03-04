package com.betrybe.Clinica.service.expections.PacienteExceptions;

import com.betrybe.Clinica.service.expections.NotFoundException;

public class PacienteAlreadyExistsException extends RuntimeException {
  public PacienteAlreadyExistsException() {
    super("Já existe paciente com este CPF!");
  }
}
