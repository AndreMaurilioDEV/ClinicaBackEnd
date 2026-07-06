package com.betrybe.Clinica.controller.dto;

import com.betrybe.Clinica.entity.Person;
import org.apache.catalina.User;

public record UserDto(String username, String name) {
    public static UserDto fromEntity(Person person) {
        return new UserDto(
                person.getUsername(),
                person.getName()
        );
    }
}
