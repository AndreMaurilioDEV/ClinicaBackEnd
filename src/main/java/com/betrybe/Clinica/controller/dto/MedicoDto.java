package com.betrybe.Clinica.controller.dto;

import com.betrybe.Clinica.entity.Medico;
import com.betrybe.Clinica.entity.Roles.EspecialidadeMedica;

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
