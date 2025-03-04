package com.betrybe.Clinica.service.expections.EmailExceptions;

import com.betrybe.Clinica.service.expections.NotFoundException;

public class EmailNotFound extends NotFoundException {
  public EmailNotFound() {
    super("Email não cadastrado!!");
  }
}
