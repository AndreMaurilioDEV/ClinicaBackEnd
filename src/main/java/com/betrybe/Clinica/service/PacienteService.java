package com.betrybe.Clinica.service;

import com.betrybe.Clinica.entity.Paciente;
import com.betrybe.Clinica.repository.PacienteRepository;
import com.betrybe.Clinica.service.expections.InvalidCpfException;
import com.betrybe.Clinica.service.expections.PacienteAlreadyExistsException;
import com.betrybe.Clinica.service.expections.PacienteNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PacienteService {
  public final PacienteRepository pacienteRepository;

  @Autowired
  public PacienteService(PacienteRepository pacienteRepository) {
    this.pacienteRepository = pacienteRepository;
  }

  public List<Paciente> findAll() throws PacienteNotFoundException {
    return pacienteRepository.findAll();
  }

  public Paciente findById(Long id) throws PacienteNotFoundException {
    return pacienteRepository.findById(id).orElseThrow(PacienteNotFoundException::new);
  }

  public Paciente createPaciente(Paciente paciente) throws PacienteNotFoundException,
          PacienteAlreadyExistsException,
          InvalidCpfException
          {

    if(pacienteRepository.findBycpf(paciente.getCpf()).isPresent()) {
      throw new PacienteAlreadyExistsException();
    }

    if(paciente.getNome() == null || paciente.getNome().isEmpty()) {
      throw new IllegalArgumentException("Nome é obrigatório.");
    }

    paciente.setAtivo(true);

    return pacienteRepository.save(paciente);
  }

  public Paciente updatePaciente(Long id, Paciente paciente) throws PacienteNotFoundException {
    Paciente pacienteFromDB = findById(id);
    pacienteFromDB.setNome(paciente.getNome());
    pacienteFromDB.setCpf(paciente.getCpf());
    return pacienteRepository.save(pacienteFromDB);
  }

  public Paciente updateStatusPaciente(Long id) throws PacienteNotFoundException {
    Paciente pacienteFromDB = findById(id);
    pacienteFromDB.setAtivo(false);
    return pacienteRepository.save(pacienteFromDB);
  }

  public Paciente deletePaciente(Long id) throws PacienteNotFoundException {
    Paciente pacienteFromDb = findById(id);
    pacienteRepository.deleteById(id);
    return pacienteFromDb;
  }

  private boolean isCpfValid(String cpf) {
    return cpf != null && cpf.matches("\\d{11}");
  }

}
