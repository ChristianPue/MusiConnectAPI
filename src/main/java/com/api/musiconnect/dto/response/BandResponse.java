package com.api.musiconnect.dto.response;

import com.api.musiconnect.model.enums.BandStatus;

import java.time.LocalDateTime;

public record BandResponse(
String mensaje,
Integer bandId,
Long userId,
String nombre,
String descripcion,
BandStatus estado,
int cantmusicalGenre,
LocalDateTime createdAt,
String createdBy,
LocalDateTime updatedAt
) {
}
