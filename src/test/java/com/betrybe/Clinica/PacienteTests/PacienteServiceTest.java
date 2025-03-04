package com.betrybe.Clinica.PacienteTests;

import com.betrybe.Clinica.controller.dto.PacienteCreationDto;
import com.betrybe.Clinica.controller.dto.PacienteDto;
import com.betrybe.Clinica.entity.Paciente;
import com.betrybe.Clinica.repository.PacienteRepository;
import com.betrybe.Clinica.service.PacienteService;
import com.betrybe.Clinica.service.expections.InvalidCpfException;
import com.betrybe.Clinica.service.expections.PacienteExceptions.PacienteNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.assertArg;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class PacienteServiceTest {

  @Mock
  private PacienteService pacienteService;

  private PacienteRepository pacienteRepository;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void criarPaciente() throws InvalidCpfException {

    PacienteCreationDto request = new PacienteCreationDto("teste", null, null, null, null,null,null,
            null, "teste@email.com", null, null, null, null);

    Paciente paciente = new Paciente();
    paciente.setId(1L);
    paciente.setNome("teste");
    paciente.setEmail("teste@email.com");

    when(pacienteService.createPaciente(any(Paciente.class))).thenReturn(paciente);

    PacienteDto response = PacienteDto.fromEntity(pacienteService.createPaciente(request.toEntity()));

    assertThat(response.id()).isEqualTo(1L);
    assertThat(response.nome()).isEqualTo("teste");
    assertThat(response.email()).isEqualTo("teste@email.com");
  }
}
