package com.musiconnect.api.service;

import java.time.LocalDate;
import java.util.function.Consumer;
import java.util.stream.Stream;

import org.springframework.stereotype.Component;

import com.musiconnect.api.dto.request.UserProfileRequest;

@Component
public class OtherFunctions {
  // Metodo auxiliar para actualizar si el valor no es nulo
  public <T> void updateIfNotNull(T value, Consumer<T> setter) {
    if (value != null) {
      setter.accept(value);
    }
  }

  // Metodo auxiliar para saber si un valor no es nulo. Retornar true o false
  public <T> boolean isNotNull(T value) {
    return value != null;
  }

  // Es mayor de edad?
  public boolean isValidBirthdate(LocalDate birthdate) {
    LocalDate today = LocalDate.now();
    LocalDate minimumDate = today.minusYears(18); // Calcula la fecha mínima permitida (18 años atrás)

    return birthdate.isBefore(minimumDate) || birthdate.isEqual(minimumDate); // Verifica si cumple la edad mínima
  }

  // Metodo para confirmar que un String no sea null ni esté vacío ("")
  public boolean isNotNullOrBlank(String value) 
  { 
    return value != null && !value.trim().isEmpty(); 
  }

  public boolean isRequestEmpty(UserProfileRequest request) {
    if (request == null) return true; // Verifica si todo el objeto es nulo

    return Stream.of(
      request.type(), request.username(), request.birthdate(), request.bio(),
      request.location(), request.gender(), request.status(), request.language()
    ).allMatch(value -> value == null || (value instanceof String && ((String) value).isBlank()));
  }
}
