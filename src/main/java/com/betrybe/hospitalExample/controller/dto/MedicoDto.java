package com.betrybe.hospitalExample.controller.dto;

import com.betrybe.hospitalExample.entity.Medico;
import com.betrybe.hospitalExample.entity.Roles.EspecialidadeMedica;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;

public record MedicoDto(Long id, String nome,
                        String crm,
                        EspecialidadeMedica especialidadeMedica,
                        LocalDate date,
                        LocalDate dataCadastro,
                        String imageName,
                        String imageType,
                        byte[] imageData
                        ) {

  public static MedicoDto fromEntity(Medico medico) {
    return new MedicoDto(
            medico.getId(),
            medico.getNome(),
            medico.getCrm(),
            medico.getEspecialidade(),
            medico.getDate(),
            medico.getDataCadastro(),
            medico.getImageName(),
            medico.getImageType(),
            medico.getImageData()
    );
  }
}
