package com.api.musiconnect.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.URI;
import java.time.Instant;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler
{
  // Maneja errores de validación (p. ej., @NotBlank, @Size en DTOs)
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ProblemDetail handleValidationExceptions(MethodArgumentNotValidException ex)
  {
    ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, "Validation failed / Falló la validación");
    problemDetail.setTitle("Validation Error");
    problemDetail.setType(URI.create("/errors/validation-error")); // Link to potential error documentation
    problemDetail.setProperty("timestamp", Instant.now());

    var fieldErrors = ex.getBindingResult().getFieldErrors().stream()
            .collect(Collectors.toMap(fe -> fe.getField(), fe -> fe.getDefaultMessage()));
    problemDetail.setProperty("errors", fieldErrors);

    return problemDetail;
  }

  // Recurso no encontrado
  @ExceptionHandler(ResourceNotFoundException.class)
  public ProblemDetail handleResourceNotFoundException(ResourceNotFoundException ex)
  {
    ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, "Resource Not Found");

    problemDetail.setTitle("Resource Not Found");
    problemDetail.setType(URI.create("/errors/resource-not-found")); // Link to potential error documentation
    problemDetail.setProperty("timestamp", Instant.now());

    return problemDetail;
  }

  // Petición incorrecta
  @ExceptionHandler(BadRequestException.class)
  public ProblemDetail handleBadRequestException(BadRequestException ex) {
    ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, "Bad Request");

    problemDetail.setTitle("Bad Request");
    problemDetail.setType(URI.create("/errors/bad-request")); // Link to potential error documentation
    problemDetail.setProperty("timestamp", Instant.now());

    return problemDetail;
  }

  // Obtención de datos inválida
  @ExceptionHandler(InvalidInputException.class)
  public ProblemDetail handleInvalidInputException(InvalidInputException ex) {
    ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, "Invalid Input");

    problemDetail.setTitle("Invalid Input");
    problemDetail.setType(URI.create("/errors/invalid-input")); // Link to potential error documentation
    problemDetail.setProperty("timestamp", Instant.now());

    return problemDetail;
  }
}
