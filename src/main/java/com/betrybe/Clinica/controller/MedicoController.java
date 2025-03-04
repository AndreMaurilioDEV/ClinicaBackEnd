package com.betrybe.Clinica.controller;

import com.betrybe.Clinica.controller.dto.MedicoCreationDto;
import com.betrybe.Clinica.controller.dto.MedicoDto;
import com.betrybe.Clinica.entity.Medico;
import com.betrybe.Clinica.service.MedicoService;
import com.betrybe.Clinica.service.expections.MedicoExceptions.InvalidCrmException;
import com.betrybe.Clinica.service.expections.MedicoExceptions.MedicoNotFoundException;
import com.betrybe.Clinica.service.expections.NameEmptyException;
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
  public ResponseEntity<List<MedicoDto>> listAllMedicos()  {
    List<Medico> allMedicos = medicoService.findAll();
    List<MedicoDto> medicoDtos = allMedicos.stream().map(MedicoDto::fromEntity).toList();
    return ResponseEntity.ok(medicoDtos);
  }

  @GetMapping("/{idMedico}")
  public ResponseEntity<MedicoDto> listByID(@PathVariable Long idMedico) throws MedicoNotFoundException {
    MedicoDto medicoDto = MedicoDto.fromEntity(medicoService.findById(idMedico));
    return ResponseEntity.ok(medicoDto);
  }

  @PostMapping
  public ResponseEntity<MedicoDto> createMedico(@RequestBody MedicoCreationDto medicoCreationDto) {
    Medico medico = medicoCreationDto.toEntity();
    Medico savedMedico = medicoService.createNewMedico(medico);
    return ResponseEntity.status(HttpStatus.CREATED).body(MedicoDto.fromEntity(savedMedico));
  }

  @PutMapping("/{id}")
  public ResponseEntity<Void> updateMedico(@PathVariable Long idMedico, @RequestBody MedicoCreationDto medicoCreationDto) throws MedicoNotFoundException {
    medicoService.updateMedico(idMedico, medicoCreationDto.toEntity());
    return ResponseEntity.noContent().build();
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteMedico(@PathVariable("id") Long id) throws MedicoNotFoundException {
    medicoService.deleteMedico(id);
    return ResponseEntity.noContent().build();
  }
}
