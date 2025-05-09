package com.api.musiconnect.dto.response;

import java.time.LocalDateTime;

public record InviteToBandResponse(
        String message,
        Integer bandId,
        Long userId,
        LocalDateTime createdAt,
        String createdBy
) {
}
