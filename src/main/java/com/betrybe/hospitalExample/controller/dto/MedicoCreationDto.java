package com.betrybe.hospitalExample.controller.dto;

import com.betrybe.hospitalExample.entity.Medico;
import com.betrybe.hospitalExample.entity.Roles.EspecialidadeMedica;

import java.time.LocalDate;

public record MedicoCreationDto(String nome, String crm, String cpf,
                                EspecialidadeMedica especialidadeMedica,
                                LocalDate date
                                ) {
  public Medico toEntity() {
    return new Medico(nome, crm, cpf, especialidadeMedica, date, null, null, null);
  }
}
