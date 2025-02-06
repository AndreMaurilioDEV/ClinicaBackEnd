package com.betrybe.Clinica.service;

import com.betrybe.Clinica.entity.Medico;
import com.betrybe.Clinica.repository.MedicoRepository;
import com.betrybe.Clinica.service.expections.MedicoAlreadyExistsException;
import com.betrybe.Clinica.service.expections.MedicoNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class MedicoService {
  private final MedicoRepository medicoRepository;

  @Autowired
  public MedicoService(MedicoRepository medicoRepository) {
    this.medicoRepository = medicoRepository;
  }

  public List<Medico> findAll() throws MedicoNotFoundException {
    return medicoRepository.findAll();
  }

  public Medico findById(Long id) throws MedicoNotFoundException {
    return medicoRepository.findById(id).orElseThrow(MedicoNotFoundException::new);
  }

  public Medico createNewMedico(Medico medico) throws MedicoNotFoundException,
          MedicoAlreadyExistsException, IOException {

    if(medicoRepository.findBycrm(medico.getCrm()).isPresent()) {
      throw new MedicoAlreadyExistsException();
    }

    if(medico.getNome() == null || medico.getNome().isEmpty()) {
      throw new IllegalArgumentException("Nome é obrigatório!");
    }

    return medicoRepository.save(medico);
  }

  public Medico updateMedico(Long id, Medico medico) throws MedicoNotFoundException {
    Medico medicoFromBD = findById(id);
    medicoFromBD.setNome(medico.getNome());
    medicoFromBD.setCrm(medico.getCrm());
    return medicoRepository.save(medicoFromBD);
  }

  public Medico deleteMedico(Long id) throws MedicoNotFoundException {
    Medico medicoFromDB = findById(id);
    medicoRepository.deleteById(id);
    return medicoFromDB;
  }

  private boolean isCrmValid(String crm) {
    return crm != null && crm.matches("\\d{4,7}-[A-Z]{2}"); // Exemplo: 123456-SP
  }

}
