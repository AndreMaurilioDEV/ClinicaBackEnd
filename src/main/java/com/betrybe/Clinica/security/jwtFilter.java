package com.betrybe.Clinica.security;

import com.betrybe.Clinica.service.PersonService;
import com.betrybe.Clinica.service.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;

@Component
public class jwtFilter extends OncePerRequestFilter {

  private final TokenService tokenService;
  private final PersonService personService;

  @Autowired
  public jwtFilter(TokenService tokenService, PersonService personService) {
    this.tokenService = tokenService;
    this.personService = personService;
  }

  private Optional<String> extractToken(HttpServletRequest request) {

    Cookie[] cookies = request.getCookies();

    if (cookies == null) {
      return Optional.empty();
    }

    for (Cookie cookie : cookies) {

      if ("access_token".equals(cookie.getName())) {
        return Optional.of(cookie.getValue());
      }
    }

    return Optional.empty();
  }

  @Override
  protected void doFilterInternal(HttpServletRequest request,
                                  HttpServletResponse response,
                                  FilterChain filterChain) throws ServletException, IOException {

    String path = request.getRequestURI();
    if (path.equals("/persons/admins") || path.startsWith("/auth/")) {
      filterChain.doFilter(request, response);
      return;
    }

    Optional<String> token = extractToken(request);

    if (token.isPresent()) {
      String subject = tokenService.validateToken(token.get());

      UserDetails userDetails = personService.loadUserByUsername(subject);

      UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
              userDetails, null, userDetails.getAuthorities());
      SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    filterChain.doFilter(request, response);
  }

}

