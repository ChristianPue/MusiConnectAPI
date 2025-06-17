package com.api.musiconnect.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record GeneralUpdateUserRequest(
        @NotNull(message = "El id no puede ser vacío.")
        Long id,

        @Pattern(regexp = "^(ONLINE|AWAY|DO_NOT_DISTURB|INVISIBLE)$", message = "El estado debe ser ACTIVE o INACTIVE.")
        String status,

        @Pattern(regexp = "^(ENGLISH|CHINESE|SPANISH|FRENCH|JAPANESE|TURKEY|GERMANY|OTHER)$", message = "El idioma debe ser uno de los siguientes: ENGLISH, CHINESE, SPANISH, FRENCH, JAPANESE, TURKEY, GERMANY o OTHER.")
        String language
) {
}
