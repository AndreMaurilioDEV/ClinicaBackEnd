package com.betrybe.Clinica.service.expections;

public class NameEmptyException extends IllegalArgumentException {
  public NameEmptyException() {
    super("O Nome é obrigatório.");
  }
}
