package com.api.musiconnect.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CommentRequest(
        @NotNull(message = "El ID del autor no puede ser nulo.")
        Long authorId,

        @NotNull(message = "El ID de la publicación no puede ser nulo.")
        Long publicationId,

        @NotBlank(message = "El contenido no puede estar vacío.")
        String content
) {}
