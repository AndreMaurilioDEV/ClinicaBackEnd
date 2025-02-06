package com.betrybe.hospitalExample.controller;
import com.betrybe.hospitalExample.controller.dto.ConsultaCreationDto;
import com.betrybe.hospitalExample.controller.dto.ConsultaDto;
import com.betrybe.hospitalExample.entity.Consulta;
import com.betrybe.hospitalExample.entity.Medico;
import com.betrybe.hospitalExample.entity.Paciente;
import com.betrybe.hospitalExample.entity.Roles.Status;
import com.betrybe.hospitalExample.entity.Roles.TipoAtendimento;
import com.betrybe.hospitalExample.repository.MedicoRepository;
import com.betrybe.hospitalExample.repository.PacienteRepository;
import com.betrybe.hospitalExample.service.ConsultaService;
import com.betrybe.hospitalExample.service.expections.ConsultaNotFoundException;
import com.betrybe.hospitalExample.service.expections.MedicoNotFoundException;
import com.betrybe.hospitalExample.service.expections.PacienteNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

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
  public List<ConsultaDto> getAll() throws ConsultaNotFoundException {
    List<Consulta> allConsultas = consultaService.findAll();
    return allConsultas.stream().map(ConsultaDto::fromEntity).toList();
  }

  @GetMapping("/{consultaId}")
  public ConsultaDto getConsultaById(@PathVariable Long consultaId) throws ConsultaNotFoundException {
    return ConsultaDto.fromEntity(consultaService.findById(consultaId));
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public ConsultaDto createConsulta(@RequestBody ConsultaCreationDto consultaCreationDto) throws PacienteNotFoundException, MedicoNotFoundException {
    return ConsultaDto.fromEntity(consultaService.createConsulta(consultaCreationDto));
  }

  @DeleteMapping("/{idConsulta}")
  public ConsultaDto deleteConsulta(@PathVariable Long idConsulta) throws ConsultaNotFoundException {
    return ConsultaDto.fromEntity(consultaService.removeConsulta(idConsulta));
  }

  @PutMapping("/status-consulta/{idConsulta}")
  public ConsultaDto updateConsultaStatus(@PathVariable Long idConsulta, @RequestBody @Valid ConsultaDto consulta)
    throws ConsultaNotFoundException {
      return ConsultaDto.fromEntity(consultaService.updateStatus(idConsulta, consulta.status()));
  }

  @PutMapping("/{idConsulta}/paciente/{idPaciente}")
  public ConsultaDto updateConsultaPaciente(@PathVariable Long idConsulta, Long idPaciente) throws
          PacienteNotFoundException, ConsultaNotFoundException {
    return ConsultaDto.fromEntity(consultaService.editConsultaPaciente(idConsulta, idPaciente));
  }

  @PutMapping("/{idConsulta}/medico/{idMedico}")
  public ConsultaDto updateConsultaMedico(@PathVariable Long idConsulta, Long idMedico) throws
          PacienteNotFoundException, ConsultaNotFoundException {
    return ConsultaDto.fromEntity(consultaService.editConsultaPaciente(idConsulta, idMedico));
  }

  @PutMapping("/{idConsulta}/editar-consulta")
  public ConsultaDto updateConsulta(@PathVariable Long idConsulta, @RequestBody ConsultaCreationDto consultaCreationDto)
          throws MedicoNotFoundException, PacienteNotFoundException, ConsultaNotFoundException {
    Medico medico = medicoRepository.findById(consultaCreationDto.medicoIds()).orElseThrow(MedicoNotFoundException::new);
    Paciente paciente = pacienteRepository.findById(consultaCreationDto.pacienteIds()).orElseThrow(PacienteNotFoundException::new);
    return ConsultaDto.fromEntity(consultaService.updateConsulta(idConsulta, consultaCreationDto.toEntity(medico, paciente)));
  }



}
