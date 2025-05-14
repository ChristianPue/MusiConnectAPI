package com.api.musiconnect.dto.response;

import java.time.LocalDateTime;

public record CommentResponse(
        Long id,
        String content,
        LocalDateTime createdAt,
        Long authorId,
        String authorUsername,
        Long publicationId
) {}
