package com.api.musiconnect.dto.request;

import lombok.*;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FavoriteProjectRequest {

    @NotNull(message = "El ID del proyecto es obligatorio")
    private Long projectId;

    @NotNull(message = "El ID del usuario es obligatorio")
    private Long userId;

}
