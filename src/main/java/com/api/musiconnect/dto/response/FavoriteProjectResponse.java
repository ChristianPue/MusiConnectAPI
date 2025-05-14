package com.api.musiconnect.dto.response;

import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FavoriteProjectResponse {

    private Long id;
    private Long projectId;
    private String projectName;  // Puedes ajustar según los atributos de tu entidad Project
    private Long userId;
    private String username;  // Puedes ajustar según los atributos de tu entidad User
    private LocalDateTime createdAt;

}
