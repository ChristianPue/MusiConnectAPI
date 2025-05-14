package com.api.musiconnect.dto.request;

import com.api.musiconnect.model.enums.NotificationType;

public record NotificationRequest(
        Long transmitterId,         // ID del usuario que genera la acción
        Long receiverId,           // ID del usuario que recibe la notificación
        NotificationType type,     // Tipo de notificación
        String content             // Contenido de la notificación
) {}
