package com.api.musiconnect.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PublicationRequest(
        @NotNull(message = "El ID del autor no puede ser nulo.")
        Long authorId,

        @NotBlank(message = "El título no puede estar vacío.")
        String title,

        @NotBlank(message = "El contenido no puede estar vacío.")
        String content
) {}
