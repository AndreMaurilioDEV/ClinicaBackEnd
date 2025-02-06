package com.betrybe.hospitalExample.service.expections;

public class ConsultaNotFoundException extends NotFoundException {
  public ConsultaNotFoundException() {
    super("Consulta não encontrada");
  }
}
