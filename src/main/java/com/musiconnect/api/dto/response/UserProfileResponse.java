package com.musiconnect.api.dto.response;

import java.time.LocalDate;

import com.musiconnect.api.model.enums.Language;
import com.musiconnect.api.model.enums.UserGender;
import com.musiconnect.api.model.enums.UserStatus;
import com.musiconnect.api.model.enums.UserType;

public record UserProfileResponse(
  UserType type,
  String username,
  LocalDate birthdate,
  String bio,
  String location,
  UserGender gender,
  UserStatus status,
  Language language
) {
}
