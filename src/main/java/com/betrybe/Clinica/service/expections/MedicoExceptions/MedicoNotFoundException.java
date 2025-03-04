package com.betrybe.Clinica.service.expections.MedicoExceptions;

import com.betrybe.Clinica.service.expections.NotFoundException;

public class MedicoNotFoundException extends NotFoundException {
  public MedicoNotFoundException() {
    super("Médico não encontrado!");
  }
}
