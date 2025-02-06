package com.betrybe.hospitalExample.controller;

import com.betrybe.hospitalExample.controller.dto.AuthDto;
import com.betrybe.hospitalExample.controller.dto.TokenDto;
import com.betrybe.hospitalExample.entity.Person;
import com.betrybe.hospitalExample.service.PersonService;
import com.betrybe.hospitalExample.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
