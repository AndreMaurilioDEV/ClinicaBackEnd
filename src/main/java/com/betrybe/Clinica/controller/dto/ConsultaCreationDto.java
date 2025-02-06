package com.betrybe.Clinica.controller.dto;

import com.betrybe.Clinica.entity.Consulta;
import com.betrybe.Clinica.entity.Medico;
import com.betrybe.Clinica.entity.Paciente;
import com.betrybe.Clinica.entity.Roles.Status;
import com.betrybe.Clinica.entity.Roles.TipoAtendimento;

import java.time.LocalDate;
import java.time.LocalTime;

public record ConsultaCreationDto(LocalTime horario, LocalDate date, Long medicoIds, Long pacienteIds, Status status, TipoAtendimento tipoAtendimento) {
  public Consulta toEntity(Medico medico, Paciente paciente) {
    return new Consulta(horario, date, medico, paciente, status, tipoAtendimento);
  }
}