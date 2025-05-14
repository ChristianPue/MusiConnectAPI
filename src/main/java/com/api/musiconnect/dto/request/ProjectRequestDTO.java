package com.api.musiconnect.dto.request;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ProjectRequestDTO {

    private String name;
    private String description;
    private Long creatorId;
    private LocalDateTime startDate;
    private String status;
}
