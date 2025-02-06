package com.betrybe.Clinica.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class ConfirmarPerson {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, length = 6)
  private String codigo;

  @Column(nullable = false)
  private LocalDateTime criacaoEm;

  @Column
  private LocalDateTime confirmacaoEm;

  @ManyToOne
  @JoinColumn(nullable = false, name = "person_id")
  private Person person;
}
