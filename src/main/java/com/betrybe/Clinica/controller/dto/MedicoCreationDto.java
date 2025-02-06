package com.betrybe.Clinica.controller.dto;

import com.betrybe.Clinica.entity.Medico;
import com.betrybe.Clinica.entity.Roles.EspecialidadeMedica;

import java.time.LocalDate;

public record MedicoCreationDto(String nome, String crm, String cpf,
                                EspecialidadeMedica especialidadeMedica,
                                LocalDate date
                                ) {
  public Medico toEntity() {
    return new Medico(nome, crm, cpf, especialidadeMedica, date, null, null, null);
  }
}
