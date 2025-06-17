package com.api.musiconnect.dto.response;

import com.api.musiconnect.model.enums.Language;
import com.api.musiconnect.model.enums.UserGender;
import com.api.musiconnect.model.enums.UserStatus;
import com.api.musiconnect.model.enums.UserType;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record UserProfileResponse(
        String username,
        UserType userType,
        LocalDateTime createdAt,
        LocalDate birthdate,
        String bio,
        String location,
        UserGender gender,
        UserStatus status,
        Language language
) {
}
