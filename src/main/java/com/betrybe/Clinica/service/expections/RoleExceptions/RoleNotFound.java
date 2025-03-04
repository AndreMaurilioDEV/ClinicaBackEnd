package com.betrybe.Clinica.service.expections.RoleExceptions;

public class RoleNotFound extends RuntimeException {
  public RoleNotFound() {
    super("Role não encontrada para esse usuário.");
  }
}
