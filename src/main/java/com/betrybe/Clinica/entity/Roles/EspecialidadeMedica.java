package com.betrybe.Clinica.entity.Roles;

public enum EspecialidadeMedica {
  CARDIOLOGIA("Cardiologia"),
  DERMATOLOGIA("Dermatologia"),
  PEDIATRIA("Pediatria"),
  ORTOPEDIA("Ortopedia"),
  GINECOLOGIA("Ginecologia"),
  PSIQUIATRIA("Psiquiatria"),
  NEUROLOGIA("Neurologia"),
  OFTALMOLOGIA("Oftalmologia"),
  ONCOLOGIA("Oncologia");

  private String descricao;

  EspecialidadeMedica(String descricao) {
    this.descricao = descricao;
  }

  public String getDescricao() {
    return descricao;
  }
}
