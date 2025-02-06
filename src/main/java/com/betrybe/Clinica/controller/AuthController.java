package com.betrybe.Clinica.controller;

import com.betrybe.Clinica.controller.dto.AuthDto;
import com.betrybe.Clinica.controller.dto.TokenDto;
import com.betrybe.Clinica.service.PersonService;
import com.betrybe.Clinica.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class AuthController {
  private final TokenService tokenService;

  private final AuthenticationManager authenticationManager;

  private final PersonService personService;

  @Autowired
  public AuthController(TokenService tokenService, AuthenticationManager authenticationManager, PersonService personService) {
    this.tokenService = tokenService;
    this.authenticationManager = authenticationManager;
    this.personService = personService;
  }

  @PostMapping("/login")
  public TokenDto login(@RequestBody AuthDto authDto) {
    UsernamePasswordAuthenticationToken usernamePassword = new UsernamePasswordAuthenticationToken(
            authDto.username(), authDto.password());
    Authentication authentication = authenticationManager.authenticate(usernamePassword);
    System.out.println(authentication);
    String role = authentication.getAuthorities().stream()
            .findFirst()
            .orElseThrow(() -> new RuntimeException("Role não encontrada"))
            .getAuthority();
    String token = tokenService.generateToken(authentication.getName(), role);
    return new TokenDto(token);
  }
}
