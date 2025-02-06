package com.betrybe.Clinica.service.expections;

public class InvalidToken extends NotFoundException {
  public InvalidToken(String message) {
    super("Token inválido!!");
  }
}

