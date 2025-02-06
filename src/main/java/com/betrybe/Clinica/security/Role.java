package com.betrybe.Clinica.security;

public enum Role {
  ADMIN("ROLE_ADMIN"),
  EMPLOYEE("ROLE_EMPLOYEE");

  private final String name;

  Role(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }
}
