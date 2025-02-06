package com.betrybe.hospitalExample.entity;

import com.betrybe.hospitalExample.entity.Roles.Status;
import com.betrybe.hospitalExample.entity.Roles.TipoAtendimento;
import jakarta.persistence.*;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "consultas")
public class Consulta {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private LocalTime horario;

  private LocalDate dateTime;

  private Status status;

  private TipoAtendimento tipoAtendimento;

  @ManyToOne
  @JoinColumn(name = "medico_id", nullable = false)
  private Medico medico;

  @ManyToOne
  @JoinColumn(name = "paciente_id", nullable = false)
  private Paciente paciente;

  public Consulta() {}

  public Consulta(LocalDate dateTime) {
    this.dateTime = dateTime;
  }

  public Consulta(LocalTime horario, LocalDate date, Medico medico, Paciente paciente, Status status, TipoAtendimento tipoAtendimento) {
    this.dateTime = date;
    this.medico = medico;
    this.paciente = paciente;
    this.status = status;
    this.horario = horario;
    this.tipoAtendimento = tipoAtendimento;
  }

  public Consulta(LocalDate date, Medico medico, Paciente paciente) {
  }

  public TipoAtendimento getTipoAtendimento() {
    return tipoAtendimento;
  }

  public void setTipoAtendimento(TipoAtendimento tipoAtendimento) {
    this.tipoAtendimento = tipoAtendimento;
  }

  public Long getId() {
    return id;
  }

  public LocalTime getHorario() {
    return horario;
  }

  public void setHorario(LocalTime horario) {
    this.horario = horario;
  }

  public LocalDate getDateTime() {
    return dateTime;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setDateTime(LocalDate dateTime) {
    this.dateTime = dateTime;
  }

  public Medico getMedicos() {
    return medico;
  }

  public Paciente getPacientes() {
    return paciente;
  }

  public void setMedicos(Medico medicos) {
    this.medico = medicos;
  }

  public void setPacientes(Paciente pacientes) {
    this.paciente = pacientes;
  }

  public Status getStatus() {
    return status;
  }

  public void setStatus(Status status) {
    this.status = status;
  }
}
