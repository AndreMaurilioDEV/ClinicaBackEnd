package com.betrybe.Clinica.service.expections.TokenResetExceptions;

import com.betrybe.Clinica.service.expections.NotFoundException;

public class InvalidToken extends NotFoundException {
  public InvalidToken(String message) {
    super("Token inválido!!");
  }
}

