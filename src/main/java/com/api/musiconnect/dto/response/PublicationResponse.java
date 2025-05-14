package com.api.musiconnect.dto.response;

import java.time.LocalDateTime;

public record PublicationResponse(
        Long id,
        String title,
        String content,
        LocalDateTime createdAt,
        Long authorId,
        String authorUsername,
        int commentCount
) {}
