package com.betrybe.hospitalExample.controller.dto;

import com.betrybe.hospitalExample.entity.Consulta;
import com.betrybe.hospitalExample.entity.Medico;
import com.betrybe.hospitalExample.entity.Paciente;
import com.betrybe.hospitalExample.entity.Roles.Status;
import com.betrybe.hospitalExample.entity.Roles.TipoAtendimento;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

public record ConsultaCreationDto(LocalTime horario, LocalDate date, Long medicoIds, Long pacienteIds, Status status, TipoAtendimento tipoAtendimento) {
  public Consulta toEntity(Medico medico, Paciente paciente) {
    return new Consulta(horario, date, medico, paciente, status, tipoAtendimento);
  }
}