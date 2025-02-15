package com.betrybe.Clinica.controller.dto;

import com.betrybe.Clinica.entity.Person;
import com.betrybe.Clinica.security.Role;

import java.time.LocalTime;

public record PersonCreationDto(String username, String password, Role role, String name, Boolean isConfirmed, LocalTime lastResetRequest) {
  public Person toEntity() {
    return new Person(username, null,null, name, false, null);
  }
}
