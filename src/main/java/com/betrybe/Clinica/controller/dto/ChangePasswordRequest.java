package com.betrybe.Clinica.controller.dto;

public record ChangePasswordRequest(String email, String currentPassword, String newPassword) {}
