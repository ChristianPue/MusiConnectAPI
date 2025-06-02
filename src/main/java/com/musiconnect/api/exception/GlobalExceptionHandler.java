package com.musiconnect.api.exception;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
  @ExceptionHandler(DuplicateResourceException.class)
  public Map<String, Object> handleDuplicateResourceException(DuplicateResourceException ex) {
    Map<String, Object> err = new LinkedHashMap<>();
    err.put("timestamp", LocalDateTime.now());
    err.put("message", ex.getMessage());
    err.put("details", "DuplicateResourceException");
    return err;
  }

  @ExceptionHandler(BadRequestException.class)
  public Map<String, Object> handleBadRequestException(BadRequestException ex) {
    Map<String, Object> err = new LinkedHashMap<>();
    err.put("timestamp", LocalDateTime.now());
    err.put("message", ex.getMessage());
    err.put("details", "BadRequestException");
    return err;
  }

  @ExceptionHandler(InvalidInputException.class)
  public Map<String, Object> handleInvalidInputException(InvalidInputException ex) {
    Map<String, Object> err = new LinkedHashMap<>();
    err.put("timestamp", LocalDateTime.now());
    err.put("message", ex.getMessage());
    err.put("details", "InvalidInputException");
    return err;
  }

  @ExceptionHandler(ResourceNotFoundException.class)
  public Map<String, Object> handleResourceNotFoundException(ResourceNotFoundException ex) {
    Map<String, Object> err = new LinkedHashMap<>();
    err.put("timestamp", LocalDateTime.now());
    err.put("message", ex.getMessage());
    err.put("details", "ResourceNotFoundException");
    return err;
  }
}
