package com.api.musiconnect.dto.request;

import com.api.musiconnect.model.enums.MusicalGenre;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.util.List;

public record CreateBandRequest(
        @NotBlank(message = "El nombre de la banda es obligatorio.")
        @Size(min = 4, max = 50, message = "El nombre de la banda debe tener entre 4 y 50 caracteres.")
        String nombre,

        @Size(max=50, message ="La descripcion de la banda debe tener como maximo 50 caracteres")
        String descripcion,

        @NotEmpty(message = "El/los genero/s musical/es de la banda son/es obligatorio/s")
        List<MusicalGenre> musicalGenre,

        @NotBlank(message = "El campo InvitedBy es obligatorio")
        String createdBy
) {
}
