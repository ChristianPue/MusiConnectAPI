package com.api.musiconnect.dto.response;

import java.time.LocalDateTime;

public record FollowResponse(
        Long id,
        Long followerId,        // ID del usuario que sigue
        Long followingId,       // ID del usuario seguido
        LocalDateTime createdAt // Fecha de creación del seguimiento
)
{}
