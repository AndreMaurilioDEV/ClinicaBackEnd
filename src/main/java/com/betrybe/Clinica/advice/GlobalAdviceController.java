package com.betrybe.Clinica.advice;

import com.betrybe.Clinica.service.expections.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalAdviceController {
  @ExceptionHandler
  public ResponseEntity<Map<String, String>> handleNotFound(NotFoundException exception) {
    Map<String, String> errorResponse = new HashMap<>();
    errorResponse.put("message", exception.getMessage());
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
  }

  @ExceptionHandler(ResponseStatusException.class)
  public ResponseEntity<Map<String, String>> handleBadRequest(ResponseStatusException exception) {
    Map<String, String> error = new HashMap<>();
    error.put("message", exception.getReason());
    return ResponseEntity.status(exception.getStatusCode()).body(error);
  }
}
