package com.betrybe.Clinica.controller;

import com.betrybe.Clinica.controller.dto.PacienteCreationDto;
import com.betrybe.Clinica.controller.dto.PacienteDto;
import com.betrybe.Clinica.entity.Paciente;
import com.betrybe.Clinica.service.PacienteService;
import com.betrybe.Clinica.service.expections.InvalidCpfException;
import com.betrybe.Clinica.service.expections.PacienteExceptions.PacienteAlreadyExistsException;
import com.betrybe.Clinica.service.expections.PacienteExceptions.PacienteNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pacientes")
@CrossOrigin(origins = "*")
public class PacienteController {
  private final PacienteService pacienteService;

  @Autowired
  public PacienteController(PacienteService pacienteService) {
    this.pacienteService = pacienteService;
  }

  @GetMapping
  public ResponseEntity<List<PacienteDto>> listAllPacientes() {
    List<Paciente> allPacientes = pacienteService.findAll();
    List<PacienteDto> pacienteDtos = allPacientes.stream().map(PacienteDto::fromEntity).toList();
    return ResponseEntity.ok(pacienteDtos);
  }

  @GetMapping("/{idPaciente}")
  public ResponseEntity<PacienteDto> listByID(@PathVariable Long idPaciente) throws PacienteNotFoundException {
    PacienteDto pacienteDto = PacienteDto.fromEntity(pacienteService.findById(idPaciente));
    return ResponseEntity.ok(pacienteDto);
  }

  @PostMapping
  public ResponseEntity<PacienteDto> createPaciente(@RequestBody PacienteCreationDto pacienteCreationDto) throws
          InvalidCpfException, PacienteAlreadyExistsException {
    Paciente paciente = pacienteCreationDto.toEntity();
    Paciente savedPaciente = pacienteService.createPaciente(paciente);
    return ResponseEntity.status(HttpStatus.CREATED).body(PacienteDto.fromEntity(savedPaciente));
  }

  @PutMapping("/{idPaciente}")
  public ResponseEntity<Void> updatePaciente(@PathVariable Long idPaciente, @RequestBody PacienteCreationDto pacienteCreationDto)
          throws  PacienteNotFoundException {
    PacienteDto.fromEntity(pacienteService.updatePaciente(idPaciente, pacienteCreationDto.toEntity()));
    return ResponseEntity.noContent().build();
  }

  @DeleteMapping("/{idPaciente}")
  public ResponseEntity<Void> deletePaciente(@PathVariable Long idPaciente) throws PacienteNotFoundException {
    pacienteService.deletePaciente(idPaciente);
    return ResponseEntity.noContent().build();
  }

  @PutMapping("/status-update/{idPaciente}")
  public ResponseEntity<Void> updateStatusPaciente(@PathVariable Long idPaciente)
          throws PacienteNotFoundException {
    PacienteDto.fromEntity(pacienteService.updateStatusPaciente(idPaciente));
    return ResponseEntity.noContent().build();
  }
}