package com.betrybe.Clinica.repository;

import com.betrybe.Clinica.entity.ConfirmarPerson;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConfirmacaoRepository extends JpaRepository<Long, ConfirmarPerson> {
  ConfirmarPerson findByCodigo(String Codigo);
}
