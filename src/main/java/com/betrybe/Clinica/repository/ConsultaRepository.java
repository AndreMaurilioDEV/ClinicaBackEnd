package com.betrybe.Clinica.repository;

import com.betrybe.Clinica.entity.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;

@Repository
public interface ConsultaRepository extends JpaRepository<Consulta, Long> {
    boolean existsByHorarioAndDateTime(LocalTime horario, LocalDate dateTime);
}
