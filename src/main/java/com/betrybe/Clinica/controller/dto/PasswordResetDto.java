package com.betrybe.Clinica.controller.dto;

public record PasswordResetDto(String token, String newPassword) {
}
