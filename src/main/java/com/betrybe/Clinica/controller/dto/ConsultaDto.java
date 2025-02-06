package com.betrybe.Clinica.controller.dto;

import com.betrybe.Clinica.entity.Consulta;
import com.betrybe.Clinica.entity.Roles.Status;
import com.betrybe.Clinica.entity.Roles.TipoAtendimento;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalTime;


public record ConsultaDto(
        Long id,
        LocalTime horario,
        LocalDate dateTime, 
        String medicoNome,
        String medicoCrm,
        String pacienteNome,
        String pacienteCpf,

        @NotNull()
        Status status,

        TipoAtendimento tipoAtendimento
) {

    public static ConsultaDto fromEntity(Consulta consulta) {
        return new ConsultaDto(
                consulta.getId(),
                consulta.getHorario(),
                consulta.getDateTime(),
                consulta.getMedicos().getNome(),
                consulta.getMedicos().getCrm(),
                consulta.getPacientes().getNome(),
                consulta.getPacientes().getCpf(),
                consulta.getStatus(),
                consulta.getTipoAtendimento()
        );
    }
}
