package com.betrybe.Clinica.service.expections.ConsultaExceptions;

import com.betrybe.Clinica.service.expections.NotFoundException;

public class ConsultaNotFoundException extends NotFoundException {
  public ConsultaNotFoundException() {
    super("Consulta não encontrada");
  }
}
