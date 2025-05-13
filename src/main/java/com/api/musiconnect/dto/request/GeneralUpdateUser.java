package com.api.musiconnect.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record GeneralUpdateUser(
        @NotNull(message = "El id no puede ser vacío.")
        Long id,

        @Pattern(regexp = "^(ONLINE|AWAY|DO_NOT_DISTURB|INVISIBLE)$", message = "El estado de disponibilidad debe ser ONLINE, AWAY, DO_NOT_DISTURB o INVISIBLE.")
        String status
) {}