package com.betrybe.Clinica.controller;

import com.betrybe.Clinica.controller.dto.MedicoCreationDto;
import com.betrybe.Clinica.controller.dto.MedicoDto;
import com.betrybe.Clinica.entity.Medico;
import com.betrybe.Clinica.service.MedicoService;
import com.betrybe.Clinica.service.expections.MedicoNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/medicos")
@CrossOrigin(origins = "*")
public class MedicoController {
  private final MedicoService medicoService;

  @Autowired
  public MedicoController(MedicoService medicoService) {
    this.medicoService = medicoService;
  }

  @GetMapping
  public List<MedicoDto> listAllMedicos() throws MedicoNotFoundException {
    List<Medico> allMedicos = medicoService.findAll();
    return allMedicos.stream().map(MedicoDto::fromEntity).toList();
  }

  @GetMapping("/{idMedico}")
  public MedicoDto listByID(@PathVariable Long idMedico) throws MedicoNotFoundException {
    return MedicoDto.fromEntity(medicoService.findById(idMedico));
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public MedicoDto createMedico(@RequestBody MedicoCreationDto medicoCreationDto)
          throws MedicoNotFoundException, IOException {
    Medico medico = medicoCreationDto.toEntity();
    Medico savedMedico = medicoService.createNewMedico(medico);
    return MedicoDto.fromEntity(savedMedico);
  }

  @PutMapping("/{id}")
  public MedicoDto updateMedico(@PathVariable Long idMedico, @RequestBody MedicoCreationDto medicoCreationDto)
    throws  MedicoNotFoundException {
    return MedicoDto.fromEntity(medicoService.updateMedico(idMedico, medicoCreationDto.toEntity()));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteMedico(@PathVariable("id") Long id) throws MedicoNotFoundException {
    medicoService.deleteMedico(id);
    return ResponseEntity.status(204).body(null);
  }
}
