package com.api.musiconnect.dto.response;

import com.api.musiconnect.model.enums.Language;
import com.api.musiconnect.model.enums.UserGender;
import com.api.musiconnect.model.enums.UserStatus;
import com.api.musiconnect.model.enums.UserType;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record UserResponse(
        Long id,
        String email,
        String username,
        String phone,
        UserType userType,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        LocalDate birthdate,
        String firstName,
        String lastName,
        String bio,
        String location,
        UserGender gender,
        UserStatus status,
        Language language
) {
}
