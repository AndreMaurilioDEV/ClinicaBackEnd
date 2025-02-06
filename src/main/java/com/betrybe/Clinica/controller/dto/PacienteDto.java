package com.betrybe.Clinica.controller.dto;

import com.betrybe.Clinica.entity.Paciente;
import com.betrybe.Clinica.entity.Roles.Genero;
import jakarta.annotation.Nullable;

import java.time.LocalDate;

public record PacienteDto(Long id, String nome, String cpf, String telefone,
                          LocalDate date,
                          String endereco,
                          String estado,
                          String cidade,
                          String cep,
                          LocalDate dataCadastro,
                          String email,
                          @Nullable String planoDeSaude,
                          @Nullable String numeroPlano,
                          Boolean ativo,
                          Genero genero
) {
  public static PacienteDto fromEntity(Paciente paciente) {
    return new PacienteDto(
            paciente.getId(),
            paciente.getCpf(),
            paciente.getNome(),
            paciente.getTelefone(),
            paciente.getDate(),
            paciente.getEndereco(),
            paciente.getEstado(),
            paciente.getCidade(),
            paciente.getCep(),
            paciente.getDataCadastro(),
            paciente.getEmail(),
            paciente.getPlanoDeSaude(),
            paciente.getNumeroPlano(),
            paciente.getAtivo(),
            paciente.getGenero()
    );
  }
}
