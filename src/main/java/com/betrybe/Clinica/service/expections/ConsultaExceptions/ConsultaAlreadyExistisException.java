package com.betrybe.Clinica.service.expections.ConsultaExceptions;

public class ConsultaAlreadyExistisException extends RuntimeException {
  public ConsultaAlreadyExistisException() {
    super("Já existe um atendimento nesse horário e data");
  }
}
