package com.betrybe.hospitalExample.service.expections;

public class NotFoundException extends Exception {
  public NotFoundException(String message) {
    super(message);
  }
}
