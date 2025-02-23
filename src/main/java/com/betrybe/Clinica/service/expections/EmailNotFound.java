package com.betrybe.Clinica.service.expections;

public class EmailNotFound extends NotFoundException {
  public EmailNotFound() {
    super("Email não cadastrado!!");
  }
}
