package com.betrybe.Clinica.controller;

import com.betrybe.Clinica.controller.dto.*;
import com.betrybe.Clinica.entity.Person;
import com.betrybe.Clinica.repository.PersonRepository;
import com.betrybe.Clinica.service.PersonService;
import com.betrybe.Clinica.service.TokenService;
import com.betrybe.Clinica.service.expections.EmailNotFound;
import com.betrybe.Clinica.service.expections.PersonNotFoundException;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class AuthController {
  private final TokenService tokenService;

  private final AuthenticationManager authenticationManager;

  private final PersonService personService;

  private final PersonRepository personRepository;

  @Autowired
  public AuthController(TokenService tokenService, AuthenticationManager authenticationManager, PersonService personService, PersonRepository personRepository) {
    this.tokenService = tokenService;
    this.authenticationManager = authenticationManager;
    this.personService = personService;
    this.personRepository = personRepository;
  }

  @PostMapping("/login")
  @ResponseStatus(HttpStatus.CREATED)
  public TokenDto login(@RequestBody AuthDto authDto) {
    UsernamePasswordAuthenticationToken usernamePassword = new UsernamePasswordAuthenticationToken(
            authDto.username(), authDto.password());
    Authentication authentication = authenticationManager.authenticate(usernamePassword);
    String role = authentication.getAuthorities().stream()
            .findFirst()
            .orElseThrow(() -> new RuntimeException("Role não encontrada"))
            .getAuthority();
    String token = tokenService.generateToken(authentication.getName(), role);
    return new TokenDto(token);
  }

  @PostMapping("/forgot-password")
  public ResponseEntity<String> forgotPassword(@RequestBody PasswordResetRequest request) throws EmailNotFound {
      personService.generateResetToken(request.email());
      return ResponseEntity.status(HttpStatus.CREATED).body("Um E-mail foi enviado com as instruções para redefinição da senha");
  }

  @PostMapping("/reset-password")
  public ResponseEntity<String> resetPassword(@RequestBody PasswordResetDto request) throws PersonNotFoundException {
    personService.changePassword(request.token(),request.newPassword());
    return ResponseEntity.status(HttpStatus.CREATED).body("Senha redefinida com sucesso.");
  }

  @PutMapping("/change-password")
  public ResponseEntity<Void> updatePassword(@RequestParam String email, @RequestParam String currentPassword, @RequestParam String newPassword)
          throws PersonNotFoundException {
    personService.updatePassword(email, newPassword, currentPassword);
    return ResponseEntity.noContent().build();
  }

  @PostMapping("/validate-reset-token")
  public ResponseEntity<Void> validateResetToken(@RequestBody ResetTokenRequestDto request) {
    personService.validateTokenReset(request.token());
    return ResponseEntity.ok().build();
  }
}
