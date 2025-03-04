package com.betrybe.Clinica.advice;

import com.betrybe.Clinica.service.expections.ExceptionResponse;
import com.betrybe.Clinica.service.expections.NotFoundException;
import com.betrybe.Clinica.service.expections.RoleExceptions.RoleNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalAdviceController {
  @ExceptionHandler
  public ResponseEntity<ExceptionResponse> handleNotFound(NotFoundException exception, WebRequest webRequest) {
    ExceptionResponse response =
            new ExceptionResponse(LocalDateTime.now(),exception.getMessage(), webRequest.getDescription(false));
    return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(ResponseStatusException.class)
  public ResponseEntity<ExceptionResponse> handleBadRequest(ResponseStatusException exception, WebRequest webRequest) {
    ExceptionResponse response =
            new ExceptionResponse(LocalDateTime.now(),exception.getMessage(), webRequest.getDescription(false));
    return new ResponseEntity<>(response, exception.getStatusCode());
  }

  @ExceptionHandler(RuntimeException.class)
  public ResponseEntity<ExceptionResponse> handleRunTimeException(RuntimeException ex, WebRequest webRequest) {
    ExceptionResponse response =
            new ExceptionResponse(LocalDateTime.now(),ex.getMessage(), webRequest.getDescription(false));
    return new ResponseEntity<>(response, HttpStatus.CONFLICT);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ExceptionResponse> genericException(Exception ex, WebRequest webRequest) {
    ExceptionResponse response = new ExceptionResponse(LocalDateTime.now(), ex.getMessage(), webRequest.getDescription(false));
    return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(IllegalArgumentException.class)
  public ResponseEntity<ExceptionResponse> handleIllegalArgumentException(IllegalArgumentException ex, WebRequest webRequest) {
    ExceptionResponse response = new ExceptionResponse(LocalDateTime.now(), ex.getMessage(), webRequest.getDescription(false));
    return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(RoleNotFound.class)
  public ResponseEntity<ExceptionResponse> handleRoleNotFound(RuntimeException ex, WebRequest webRequest) {
    ExceptionResponse response = new ExceptionResponse(LocalDateTime.now(), ex.getMessage(), webRequest.getDescription(false));
    return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
  }
}
