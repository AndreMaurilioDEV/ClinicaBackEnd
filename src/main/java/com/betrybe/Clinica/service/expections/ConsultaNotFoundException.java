package com.betrybe.Clinica.service.expections;

public class ConsultaNotFoundException extends NotFoundException {
  public ConsultaNotFoundException() {
    super("Consulta não encontrada");
  }
}
