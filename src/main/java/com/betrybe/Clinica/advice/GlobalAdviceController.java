package com.betrybe.Clinica.advice;

import com.betrybe.Clinica.service.expections.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalAdviceController {
  @ExceptionHandler
  public ResponseEntity<String> handleNotFound(NotFoundException exception) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(exception.getMessage());
  }
}
