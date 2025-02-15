package com.betrybe.Clinica.controller;

import com.betrybe.Clinica.controller.dto.PacienteCreationDto;
import com.betrybe.Clinica.controller.dto.PacienteDto;
import com.betrybe.Clinica.entity.Paciente;
import com.betrybe.Clinica.service.PacienteService;
import com.betrybe.Clinica.service.expections.InvalidCpfException;
import com.betrybe.Clinica.service.expections.PacienteAlreadyExistsException;
import com.betrybe.Clinica.service.expections.PacienteNotFoundException;
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
  public List<PacienteDto> listAllPacientes() throws PacienteNotFoundException {
    List<Paciente> allPacientes = pacienteService.findAll();
    return allPacientes.stream().map(PacienteDto::fromEntity).toList();
  }

  @GetMapping("/{idPaciente}")
  public PacienteDto listByID(@PathVariable Long idPaciente) throws PacienteNotFoundException {
    return PacienteDto.fromEntity(pacienteService.findById(idPaciente));
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public PacienteDto createPaciente(@RequestBody PacienteCreationDto pacienteCreationDto) throws PacienteNotFoundException, InvalidCpfException, PacienteAlreadyExistsException {
    Paciente paciente = pacienteCreationDto.toEntity();
    Paciente savedPaciente = pacienteService.createPaciente(paciente);
    return PacienteDto.fromEntity(savedPaciente);
  }

  @PutMapping("/{idPaciente}")
  public PacienteDto updatePaciente(@PathVariable Long idPaciente, @RequestBody PacienteCreationDto pacienteCreationDto)
          throws  PacienteNotFoundException {
    return PacienteDto.fromEntity(pacienteService.updatePaciente(idPaciente, pacienteCreationDto.toEntity()));
  }

  @DeleteMapping("/{idPaciente}")
  public ResponseEntity<Void> deletePaciente(@PathVariable Long idPaciente) throws PacienteNotFoundException {
    pacienteService.deletePaciente(idPaciente);
    return ResponseEntity.status(204).body(null);
  }

  @PutMapping("/status-update/{idPaciente}")
  public PacienteDto updateStatusPaciente(@PathVariable Long idPaciente)
          throws PacienteNotFoundException {
    return PacienteDto.fromEntity(pacienteService.updateStatusPaciente(idPaciente));
  }
}