package com.api.musiconnect.dto.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ProjectResponseDTO {

    private Long id;
    private String name;
    private String description;
    private Long creatorId;
    private LocalDateTime createdAt;
    private LocalDateTime startDate;
    private String status;
}
