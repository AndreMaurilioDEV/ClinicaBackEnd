package com.betrybe.hospitalExample.controller.dto;

import com.betrybe.hospitalExample.entity.Person;
import com.betrybe.hospitalExample.security.Role;

public record PersonCreationDto(String username, String password, Role role, String name, Boolean isConfirmed) {
  public Person toEntity() {
    return new Person(username, null,null, name, false);
  }
}
