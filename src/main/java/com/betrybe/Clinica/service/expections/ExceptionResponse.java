package com.betrybe.Clinica.service.expections;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.cglib.core.Local;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

public class ExceptionResponse implements Serializable {
  private static final long serialVersionUID = 1L;
  @JsonFormat(pattern="dd-MM-yyyy HH:mm:ss")
  private LocalDateTime timestamp;
  private String message;
  private String details;

  public ExceptionResponse(LocalDateTime timestamp, String message, String details) {
    this.timestamp = timestamp;
    this.message = message;
    this.details = details;
  }

  public LocalDateTime getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(LocalDateTime timestamp) {
    this.timestamp = timestamp;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public String getDetails() {
    return details;
  }

  public void setDetails(String details) {
    this.details = details;
  }
}
