package com.betrybe.hospitalExample.repository;

import com.betrybe.hospitalExample.entity.Medico;
import com.betrybe.hospitalExample.entity.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MedicoRepository extends JpaRepository<Medico, Long> {
  Optional<Medico> findBycrm(String crm);
}
