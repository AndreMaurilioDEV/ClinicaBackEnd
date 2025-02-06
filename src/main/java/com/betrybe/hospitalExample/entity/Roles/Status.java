package com.betrybe.hospitalExample.entity.Roles;

public enum Status {
  AGENDADO("Agendado"),
  CONFIRMADO("Confirmado"),
  FALTOU("Faltou"),
  ATENDIDO("Atendido");


  private String descricao;

  Status(String descricao) {
    this.descricao = descricao;
  }

  public String getDescricao() {
    return descricao;
  }
}
