package com.betrybe.hospitalExample.service.expections;

public class InvalidCpfException extends NotFoundException {
  public InvalidCpfException() {
    super("CPF inválido!!");
  }
}
