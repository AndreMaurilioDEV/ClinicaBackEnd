package com.betrybe.Clinica.entity;

import com.betrybe.Clinica.entity.Roles.Genero;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "pacientes")
public class Paciente {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String cpf;
  private String nome;
  private String telefone;
  private LocalDate date;
  private String endereco;
  private String estado;
  private String cidade;
  private String cep;

  @Column(nullable = false, updatable = false)
  private LocalDate dataCadastro;

  private String email;
  private String planoDeSaude;
  private String numeroPlano;
  private Genero genero;
  private Boolean ativo;

  @ManyToMany(mappedBy = "paciente")
  private List<Consulta> consultaList;

  public Paciente() {}

  @PrePersist
  public void prePersist() {
    this.dataCadastro = LocalDate.now();
  }

  public Paciente( String cpf, String nome, String telefone, LocalDate date, String endereco, String estado, String cidade, String cep, String email,
                  String numeroPlano, String planoDeSaude, Boolean ativo, Genero genero) {
    this.cpf = cpf;
    this.nome = nome;
    this.telefone = telefone;
    this.date = date;
    this.endereco = endereco;
    this.estado = estado;
    this.cidade = cidade;
    this.cep = cep;
    this.email = email;
    this.numeroPlano = numeroPlano;
    this.planoDeSaude = planoDeSaude;
    this.ativo = ativo;
    this.genero = genero;
  }

  public String getEndereco() {
    return endereco;
  }

  public void setEndereco(String endereco) {
    this.endereco = endereco;
  }

  public Genero getGenero() {
    return genero;
  }

  public void setGenero(Genero genero) {
    this.genero = genero;
  }

  public String getEstado() {
    return estado;
  }

  public void setEstado(String estado) {
    this.estado = estado;
  }

  public String getCidade() {
    return cidade;
  }

  public void setCidade(String cidade) {
    this.cidade = cidade;
  }

  public String getCep() {
    return cep;
  }

  public void setCep(String cep) {
    this.cep = cep;
  }

  public LocalDate getDataCadastro() {
    return dataCadastro;
  }

  public void setDataCadastro(LocalDate dataCadastro) {
    this.dataCadastro = dataCadastro;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPlanoDeSaude() {
    return planoDeSaude;
  }

  public void setPlanoDeSaude(String planoDeSaude) {
    this.planoDeSaude = planoDeSaude;
  }

  public String getNumeroPlano() {
    return numeroPlano;
  }

  public void setNumeroPlano(String numeroPlano) {
    this.numeroPlano = numeroPlano;
  }

  public Boolean getAtivo() {
    return ativo;
  }

  public void setAtivo(Boolean ativo) {
    this.ativo = ativo;
  }

  public Long getId() {
    return id;
  }

  public String getCpf() {
    return cpf;
  }

  public String getNome() {
    return nome;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setCpf(String cpf) {
    this.cpf = cpf;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public String getTelefone() {
    return telefone;
  }

  public void setTelefone(String telefone) {
    this.telefone = telefone;
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
