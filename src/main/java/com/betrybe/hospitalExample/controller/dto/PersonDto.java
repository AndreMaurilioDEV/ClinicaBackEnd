package com.betrybe.hospitalExample.controller.dto;

import com.betrybe.hospitalExample.entity.Person;
import com.betrybe.hospitalExample.security.Role;

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
