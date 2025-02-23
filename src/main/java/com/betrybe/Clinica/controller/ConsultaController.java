package com.betrybe.Clinica.controller;
import com.betrybe.Clinica.controller.dto.ConsultaCreationDto;
import com.betrybe.Clinica.controller.dto.ConsultaDto;
import com.betrybe.Clinica.entity.Consulta;
import com.betrybe.Clinica.repository.MedicoRepository;
import com.betrybe.Clinica.repository.PacienteRepository;
import com.betrybe.Clinica.service.ConsultaService;
import com.betrybe.Clinica.service.expections.ConsultaNotFoundException;
import com.betrybe.Clinica.service.expections.MedicoNotFoundException;
import com.betrybe.Clinica.service.expections.PacienteNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/consultas")
@CrossOrigin(origins = "*")
public class ConsultaController {

  private final ConsultaService consultaService;


  @Autowired
  public ConsultaController(ConsultaService consultaService, MedicoRepository medicoRepository, PacienteRepository pacienteRepository) {
    this.consultaService = consultaService;
  }

  @GetMapping
  public ResponseEntity<List<ConsultaDto>> listAllConsulta() throws ConsultaNotFoundException {
    List<Consulta> allConsultas = consultaService.findAll();
    List<ConsultaDto> consultasDto =  allConsultas.stream().map(ConsultaDto::fromEntity).toList();
    return ResponseEntity.ok(consultasDto);
  }

  @GetMapping("/{consultaId}")
  public ResponseEntity<ConsultaDto> listByID(@PathVariable Long consultaId) throws ConsultaNotFoundException {
    ConsultaDto consultaDto = ConsultaDto.fromEntity(consultaService.findById(consultaId));
    return ResponseEntity.ok(consultaDto);
  }

  @PostMapping
  public ResponseEntity<ConsultaDto> createConsulta(@RequestBody ConsultaCreationDto consultaCreationDto) throws PacienteNotFoundException,
          MedicoNotFoundException {
    return ResponseEntity.status(HttpStatus.CREATED).body(ConsultaDto.fromEntity(consultaService.createConsulta(consultaCreationDto)));
  }

  @DeleteMapping("/{idConsulta}")
  public ResponseEntity<Void> deleteConsulta(@PathVariable Long idConsulta) throws ConsultaNotFoundException {
    consultaService.removeConsulta(idConsulta);
    return ResponseEntity.noContent().build();
  }

  @PutMapping("/status-consulta/{idConsulta}")
  public ResponseEntity<Void> updateConsultaStatus(@PathVariable Long idConsulta, @RequestBody @Valid ConsultaDto consulta)
    throws ConsultaNotFoundException {
      consultaService.updateStatus(idConsulta, consulta.status());
      return ResponseEntity.noContent().build();
  }

}
