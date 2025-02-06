package com.betrybe.hospitalExample.controller;

import com.betrybe.hospitalExample.controller.dto.PacienteCreationDto;
import com.betrybe.hospitalExample.controller.dto.PacienteDto;
import com.betrybe.hospitalExample.entity.Paciente;
import com.betrybe.hospitalExample.service.PacienteService;
import com.betrybe.hospitalExample.service.expections.InvalidCpfException;
import com.betrybe.hospitalExample.service.expections.PacienteAlreadyExistsException;
import com.betrybe.hospitalExample.service.expections.PacienteNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
  public List<PacienteDto> getAll() throws PacienteNotFoundException {
    List<Paciente> allPacientes = pacienteService.findAll();
    return allPacientes.stream().map(PacienteDto::fromEntity).toList();
  }

  @GetMapping("/{idPaciente}")
  public PacienteDto getById(@PathVariable Long idPaciente) throws PacienteNotFoundException {
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
  public PacienteDto deleteMedico(@PathVariable Long idPaciente) throws PacienteNotFoundException {
    return PacienteDto.fromEntity(pacienteService.deletePaciente(idPaciente));
  }

  @PutMapping("/status-update/{idPaciente}")
  public PacienteDto updateStatusPaciente(@PathVariable Long idPaciente)
          throws PacienteNotFoundException {
    return PacienteDto.fromEntity(pacienteService.updateStatusPaciente(idPaciente));
  }
}