package com.betrybe.Clinica.service.expections.MedicoExceptions;


public class InvalidCrmException extends IllegalArgumentException {
  public InvalidCrmException() {
    super("O CRM é inválido!!");
  }
}
