package com.betrybe.Clinica.service.expections;

public class InvalidCrmException extends NotFoundException {
  public InvalidCrmException() {
    super("O CRM é inválido!!");
  }
}
