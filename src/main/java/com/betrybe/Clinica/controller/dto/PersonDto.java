package com.betrybe.Clinica.controller.dto;

import com.betrybe.Clinica.entity.Person;
import com.betrybe.Clinica.security.Role;

public record PersonDto(Long id, String username, Role role, String name) {
  public static PersonDto fromEntity(Person person) {
    return new PersonDto(
            person.getId(),
            person.getUsername(),
            person.getRole(),
            person.getName()
            );
  }
}
