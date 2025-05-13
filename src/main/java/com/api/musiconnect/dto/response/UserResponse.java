package com.api.musiconnect.dto.response;

import com.api.musiconnect.model.enums.Gender;
import com.api.musiconnect.model.enums.UserStatus;
import com.api.musiconnect.model.enums.UserType;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record UserResponse(
        Long id,
        UserType userType,
        String email,
        String username,
        String password,
        LocalDate birthdate,
        Gender gender,
        String bio,
        String location,
        UserStatus status,
        int cantInstruments,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
)
{}
