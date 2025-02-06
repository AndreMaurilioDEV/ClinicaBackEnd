package com.betrybe.Clinica.service;

import com.betrybe.Clinica.controller.dto.ConsultaCreationDto;
import com.betrybe.Clinica.entity.Consulta;
import com.betrybe.Clinica.entity.Medico;
import com.betrybe.Clinica.entity.Paciente;
import com.betrybe.Clinica.entity.Roles.Status;
import com.betrybe.Clinica.repository.ConsultaRepository;
import com.betrybe.Clinica.repository.MedicoRepository;
import com.betrybe.Clinica.repository.PacienteRepository;
import com.betrybe.Clinica.service.expections.ConsultaNotFoundException;
import com.betrybe.Clinica.service.expections.MedicoNotFoundException;
import com.betrybe.Clinica.service.expections.PacienteNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
          throws MedicoNotFoundException, PacienteNotFoundException {

    Consulta consulta = new Consulta();
    consulta.setHorario(consultaDto.horario());
    consulta.setDateTime(consultaDto.date());
    consulta.setStatus(consultaDto.status());
    consulta.setTipoAtendimento(consultaDto.tipoAtendimento());

      Medico medico = medicoService.findById(consultaDto.medicoIds());
      if (medico == null) {
        throw new MedicoNotFoundException();
      }
      consulta.setMedicos(medico);

      Paciente paciente = pacienteService.findById(consultaDto.pacienteIds());
      if (paciente == null) {
        throw new PacienteNotFoundException();
      }
      consulta.setPacientes(paciente);

    return consultaRepository.save(consulta);
  }

  public Consulta updateConsulta(Long id, Consulta consulta) throws ConsultaNotFoundException {
    Consulta consultaFromBD = findById(id);
    consultaFromBD.setHorario(consulta.getHorario());
    consultaFromBD.setDateTime(consulta.getDateTime());
    consultaFromBD.setPacientes(consulta.getPacientes());
    consultaFromBD.setMedicos(consulta.getMedicos());
    consultaFromBD.setStatus(consulta.getStatus());
    consultaFromBD.setTipoAtendimento(consulta.getTipoAtendimento());
    return consultaRepository.save(consultaFromBD);
  }

  public Consulta deleteConsulta(Long id) throws ConsultaNotFoundException {
    Consulta consultaFromBD = findById(id);
    consultaRepository.deleteById(id);
    return consultaFromBD;
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

  public Consulta editConsultaPaciente(Long idConsulta, Long idPaciente) throws
          ConsultaNotFoundException, PacienteNotFoundException {
    Consulta consultafromDB = findById(idConsulta);
    Paciente pacienteFromDB = pacienteService.findById(idPaciente);
    consultafromDB.setPacientes(pacienteFromDB);
    return consultaRepository.save(consultafromDB);
  }

  public Consulta editConsultaMedico(Long idConsulta, Long idMedico) throws
          ConsultaNotFoundException, MedicoNotFoundException {
    Consulta consultaFromDB = findById(idConsulta);
    Medico medicoFromDB = medicoService.findById(idMedico);
    consultaFromDB.setMedicos(medicoFromDB);
    return consultaRepository.save(consultaFromDB);
  }


}
