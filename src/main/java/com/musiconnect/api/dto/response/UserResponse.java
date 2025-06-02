package com.musiconnect.api.dto.response;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.musiconnect.api.model.enums.UserGender;
import com.musiconnect.api.model.enums.UserStatus;
import com.musiconnect.api.model.enums.UserType;

public record UserResponse(
  Long id,
  String email,
  String password,
  String phone,
  LocalDateTime createdAt,
  LocalDateTime updatedAt,
  UserType type,
  String username,
  LocalDate birthdate,
  String bio,
  String location,
  UserGender gender,
  UserStatus status
) {
}