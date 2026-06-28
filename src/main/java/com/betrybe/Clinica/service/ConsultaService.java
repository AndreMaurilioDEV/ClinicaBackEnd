package com.betrybe.Clinica.service;

import com.betrybe.Clinica.controller.dto.ConsultaCreationDto;
import com.betrybe.Clinica.entity.Consulta;
import com.betrybe.Clinica.entity.Medico;
import com.betrybe.Clinica.entity.Paciente;
import com.betrybe.Clinica.entity.Roles.Status;
import com.betrybe.Clinica.repository.ConsultaRepository;
import com.betrybe.Clinica.repository.MedicoRepository;
import com.betrybe.Clinica.repository.PacienteRepository;
import com.betrybe.Clinica.service.expections.ConsultaExceptions.ConsultaAlreadyExistisException;
import com.betrybe.Clinica.service.expections.ConsultaExceptions.ConsultaNotFoundException;
import com.betrybe.Clinica.service.expections.MedicoExceptions.MedicoNotFoundException;
import com.betrybe.Clinica.service.expections.PacienteExceptions.PacienteNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ConsultaService {
  private final ConsultaRepository consultaRepository;
  private final MedicoService medicoService;
  private final PacienteService pacienteService;

  @Autowired
  public ConsultaService(ConsultaRepository consultaRepository,
                         MedicoService medicoService,
                         PacienteService pacienteService,
                         PacienteRepository pacienteRepository,
                         MedicoRepository medicoRepository) {
    this.consultaRepository = consultaRepository;
    this.medicoService = medicoService;
    this.pacienteService = pacienteService;
  }

  public List<Consulta> findAll() throws ConsultaNotFoundException {
    return consultaRepository.findAll();
  }

  public Consulta findById(Long id) throws ConsultaNotFoundException {
    return consultaRepository.findById(id).orElseThrow(ConsultaNotFoundException::new);
  }

  public Consulta createConsulta(ConsultaCreationDto consultaDto)
          throws MedicoNotFoundException, PacienteNotFoundException, ConsultaAlreadyExistisException {

    // Busca consulta por horário e data
    boolean exists = consultaRepository.existsByHorarioAndDateTime(consultaDto.horario(), consultaDto.date());
    if (exists) {
      throw new ConsultaAlreadyExistisException();
    }
    Consulta consulta = new Consulta();
    consulta.setHorario(consultaDto.horario());
    consulta.setDateTime(consultaDto.date());
    consulta.setStatus(consultaDto.status());
    consulta.setTipoAtendimento(consultaDto.tipoAtendimento());

    // Buscar o médico pelo ID
    Medico medico = medicoService.findById(consultaDto.medicoIds());
    if (medico == null) {
      throw new MedicoNotFoundException();
    }
      consulta.setMedicos(medico);

    // Buscar o paciente pelo ID
      Paciente paciente = pacienteService.findById(consultaDto.pacienteIds());
      if (paciente == null) {
        throw new PacienteNotFoundException();
      }
      consulta.setPacientes(paciente);

    return consultaRepository.save(consulta);
  }

  public Consulta updateConsulta(Long id, Consulta consulta) throws ConsultaNotFoundException {
    Consulta consultaToEdit = findById(id);
    consultaToEdit.setHorario(consulta.getHorario());
    consultaToEdit.setDateTime(consulta.getDateTime());
    consultaToEdit.setPacientes(consulta.getPacientes());
    consultaToEdit.setMedicos(consulta.getMedicos());
    consultaToEdit.setStatus(consulta.getStatus());
    consultaToEdit.setTipoAtendimento(consulta.getTipoAtendimento());
    return consultaRepository.save(consultaToEdit);
  }

  public Consulta removeConsulta(Long id) throws ConsultaNotFoundException {
    Consulta consultaFromDB = findById(id);
    consultaRepository.deleteById(id);
    return consultaFromDB;
  }

  public Consulta updateStatus(Long id, Status status) throws ConsultaNotFoundException {
    Consulta consulta = findById(id);
    consulta.setStatus(status);
    return consultaRepository.save(consulta);
  }

  public Consulta updateStatusConsultaToFaltou(Long id) throws ConsultaNotFoundException {
    Consulta consulta = findById(id);
    consulta.setStatus(Status.FALTOU);
    return consultaRepository.save(consulta);

  }


}
