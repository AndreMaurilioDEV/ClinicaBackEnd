package com.betrybe.hospitalExample.entity;

import com.betrybe.hospitalExample.entity.Roles.EspecialidadeMedica;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "medicos")
public class Medico {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String nome;
  private String crm;
  private String cpf ;
  private EspecialidadeMedica especialidade;
  private LocalDate date;
  private LocalDate dataCadastro;

  private String imageName;
  private String imageType;

  @Lob
  private byte[] imageData;

  @ManyToMany(mappedBy = "medico")
  private List<Consulta> consultaList;

  public Medico() {}

  @PrePersist
  public void prePersist() {
    this.dataCadastro = LocalDate.now();
  }

  public Medico(String nome, String crm, String cpf, EspecialidadeMedica especialidade, LocalDate date, String imageName, String imageType, byte[] imageData) {
    this.nome = nome;
    this.crm = crm;
    this.cpf = cpf;
    this.especialidade = especialidade;
    this.date = date;
    this.imageName = imageName;
    this.imageType = imageType;
    this.imageData = imageData;
  }

  public String getImageName() {
    return imageName;
  }

  public void setImageName(String imageName) {
    this.imageName = imageName;
  }

  public String getImageType() {
    return imageType;
  }

  public void setImageType(String imageType) {
    this.imageType = imageType;
  }

  public byte[] getImageData() {
    return imageData;
  }

  public void setImageData(byte[] imageData) {
    this.imageData = imageData;
  }

  public LocalDate getDataCadastro() {
    return dataCadastro;
  }

  public void setDataCadastro(LocalDate dataCadastro) {
    this.dataCadastro = dataCadastro;
  }

  public Long getId() {
    return id;
  }

  public String getNome() {
    return nome;
  }

  public String getCrm() {
    return crm;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public void setCrm(String crm) {
    this.crm = crm;
  }

  public String getCpf() {
    return cpf;
  }

  public void setCpf(String cpf) {
    this.cpf = cpf;
  }

  public EspecialidadeMedica getEspecialidade() {
    return especialidade;
  }

  public void setEspecialidade(EspecialidadeMedica especialidade) {
    this.especialidade = especialidade;
  }

  public LocalDate getDate() {
    return date;
  }

  public void setDate(LocalDate date) {
    this.date = date;
  }

  public List<Consulta> getConsultaList() {
    return consultaList;
  }

  public void setConsultaList(List<Consulta> consultaList) {
    this.consultaList = consultaList;
  }
}
