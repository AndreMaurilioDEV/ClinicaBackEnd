package com.betrybe.hospitalExample.controller.dto;

import com.betrybe.hospitalExample.entity.Paciente;
import com.betrybe.hospitalExample.entity.Roles.Genero;

import java.time.LocalDate;

public record PacienteCreationDto(String nome, String cpf, String telefone,
                                  LocalDate date,
                                  String endereco,
                                  String estado,
                                  String cidade,
                                  String cep,
                                  String email,
                                  String planoDeSaude,
                                  String numeroPlano,
                                  Boolean ativo,
                                  Genero genero
) {
  public Paciente toEntity() {
    return new Paciente(nome, cpf, telefone, date, endereco, estado, cidade, cep, email, planoDeSaude, numeroPlano, ativo, genero);
  }
}
