package com.betrybe.Clinica.entity.Roles;

public enum TipoAtendimento {
  CONSULTA("Consulta"),
  EXAME("Exame"),
  PROCEDIMENTO("Procedimento"),
  RETORNO("Retorno"),
  ENCAIXE("Encaixe");


  private String descricao;

  TipoAtendimento(String descricao) {
    this.descricao = descricao;
  }

  public String getDescricao() {
    return descricao;
  }
}
