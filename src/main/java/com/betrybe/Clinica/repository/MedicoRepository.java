package com.betrybe.Clinica.repository;

import com.betrybe.Clinica.entity.Medico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MedicoRepository extends JpaRepository<Medico, Long> {
  Optional<Medico> findBycrm(String crm);
}
