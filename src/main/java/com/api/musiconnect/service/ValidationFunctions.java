package com.api.musiconnect.service;

import com.api.musiconnect.dto.request.ConfigUpdateUserRequest;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.stream.Stream;

@Component
public class ValidationFunctions
{
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

    // Metodo para verificar si la solicitud está vacía
    public boolean isRequestEmpty(ConfigUpdateUserRequest request) {
        return Stream.of(
                request.email(), request.password(), request.username(), request.phone(), request.userType(),
                request.birthdate(), request.firstName(),
                request.lastName(), request.bio(),
                request.location(), request.gender(),
                request.status(), request.language()
        ).allMatch(Objects::isNull);
    }

    // Es mayor de edad?
    public boolean isValidBirthdate(LocalDate birthdate) {
        LocalDate today = LocalDate.now();
        LocalDate minimumDate = today.minusYears(18); // Calcula la fecha mínima permitida (18 años atrás)

        return birthdate.isBefore(minimumDate) || birthdate.isEqual(minimumDate); // Verifica si cumple la edad mínima
    }

    // Metodo para confirmar que un String no sea null ni esté vacío ("")
    public boolean isNotNullOrBlank(String value) {
        return value != null && !value.trim().isEmpty();
    }
}
