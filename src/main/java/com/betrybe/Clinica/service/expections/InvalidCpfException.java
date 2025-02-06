package com.betrybe.Clinica.service.expections;

public class InvalidCpfException extends NotFoundException {
  public InvalidCpfException() {
    super("CPF inválido!!");
  }
}
