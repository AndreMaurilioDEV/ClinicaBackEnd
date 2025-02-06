package com.betrybe.hospitalExample.repository;

import com.betrybe.hospitalExample.entity.Paciente;
import com.betrybe.hospitalExample.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long> {
  Optional<Paciente> findBycpf(String cpf);
}
