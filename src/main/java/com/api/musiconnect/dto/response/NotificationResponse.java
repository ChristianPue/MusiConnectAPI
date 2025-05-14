package com.api.musiconnect.dto.response;

import com.api.musiconnect.model.enums.NotificationType;

import java.time.LocalDateTime;

public record NotificationResponse(
        Long id,
        Long transmitterId,         // ID del usuario que genera la acción
        Long receiverId,            // ID del usuario que recibe la notificación
        NotificationType type,      // Tipo de notificación (FOLLOW, COMMENT, LIKE)
        String content,             // Texto de la notificación
        boolean isRead,             // Estado de lectura
        LocalDateTime createdAt,    // Fecha de creación
        LocalDateTime updatedAt     // Fecha de última actualización
) {}
