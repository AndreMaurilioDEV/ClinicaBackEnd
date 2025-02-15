package com.betrybe.Clinica.controller;
import com.betrybe.Clinica.controller.dto.ConsultaCreationDto;
import com.betrybe.Clinica.controller.dto.ConsultaDto;
import com.betrybe.Clinica.entity.Consulta;
import com.betrybe.Clinica.entity.Medico;
import com.betrybe.Clinica.entity.Paciente;
import com.betrybe.Clinica.repository.MedicoRepository;
import com.betrybe.Clinica.repository.PacienteRepository;
import com.betrybe.Clinica.service.ConsultaService;
import com.betrybe.Clinica.service.expections.ConsultaNotFoundException;
import com.betrybe.Clinica.service.expections.MedicoNotFoundException;
import com.betrybe.Clinica.service.expections.PacienteNotFoundException;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
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

  private final MedicoRepository medicoRepository;

  private final PacienteRepository pacienteRepository;

  @Autowired
  public ConsultaController(ConsultaService consultaService, MedicoRepository medicoRepository, PacienteRepository pacienteRepository) {
    this.consultaService = consultaService;
    this.medicoRepository = medicoRepository;
    this.pacienteRepository = pacienteRepository;
  }

  @GetMapping
  public List<ConsultaDto> listAllConsulta() throws ConsultaNotFoundException {
    List<Consulta> allConsultas = consultaService.findAll();
    return allConsultas.stream().map(ConsultaDto::fromEntity).toList();
  }

  @GetMapping("/{consultaId}")
  public ConsultaDto listByID(@PathVariable Long consultaId) throws ConsultaNotFoundException {
    return ConsultaDto.fromEntity(consultaService.findById(consultaId));
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public ConsultaDto createConsulta(@RequestBody ConsultaCreationDto consultaCreationDto) throws PacienteNotFoundException, MedicoNotFoundException {
    return ConsultaDto.fromEntity(consultaService.createConsulta(consultaCreationDto));
  }

  @DeleteMapping("/{idConsulta}")
  public ResponseEntity<Void> deleteConsulta(@PathVariable Long idConsulta) throws ConsultaNotFoundException {
    consultaService.removeConsulta(idConsulta);
    return ResponseEntity.status(204).body(null);
  }

  @PutMapping("/status-consulta/{idConsulta}")
  public ResponseEntity<Void> updateConsultaStatus(@PathVariable Long idConsulta, @RequestBody @Valid ConsultaDto consulta)
    throws ConsultaNotFoundException {
      consultaService.updateStatus(idConsulta, consulta.status());
      return ResponseEntity.status(204).body(null);
  }

}
