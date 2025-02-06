package com.betrybe.hospitalExample.controller.dto;

import com.betrybe.hospitalExample.entity.Consulta;
import com.betrybe.hospitalExample.entity.Medico;
import com.betrybe.hospitalExample.entity.Roles.Status;
import com.betrybe.hospitalExample.entity.Roles.TipoAtendimento;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;


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
