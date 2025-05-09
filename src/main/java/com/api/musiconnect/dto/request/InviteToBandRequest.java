package com.api.musiconnect.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record InviteToBandRequest(
        @NotNull(message = "El ID del usuario es obligatorio")
        Long userId,

        @NotNull(message = "El ID de la banda es obligatorio")
        Integer bandId,

        @NotBlank(message = "El campo InvitedBy es obligatorio")
        String createdBy

) {
}
