package com.api.musiconnect.mapper;
import com.api.musiconnect.dto.request.ConfigUpdateUserRequest;
import com.api.musiconnect.dto.request.RegisterUserRequest;
import com.api.musiconnect.dto.response.GeneralUserResponse;
import com.api.musiconnect.dto.response.UserProfileResponse;
import com.api.musiconnect.dto.response.UserResponse;
import com.api.musiconnect.model.entity.User;
import com.api.musiconnect.model.entity.UserProfile;
import com.api.musiconnect.model.enums.Language;
import com.api.musiconnect.model.enums.UserGender;
import com.api.musiconnect.model.enums.UserStatus;
import com.api.musiconnect.model.enums.UserType;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper
{
    // Convertir "RegisterUserRequest" a "User"
    public User registerUsertoUser(RegisterUserRequest request)
    {
        if (request == null)
        {
            return null;
        }

        UserProfile profile = new UserProfile();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        profile.setBirthDate(LocalDate.parse(request.birthdate(), formatter));
        profile.setGender(UserGender.valueOf(request.gender()));
        profile.setStatus(UserStatus.ONLINE);
        profile.setLanguage(Language.ENGLISH);

        User user = User.builder()
                .userProfile(profile) // Guardar UserProfile
                .email(request.email())
                .password(request.password())
                .username(request.username())
                .userType(UserType.ARTIST)
                .createdAt(LocalDateTime.now())
                .build();

        profile.setUser(user); // Guardado bidireccional
        return user;
    }

    // Convertir "ConfigUpdateUser" a "User"
    public User configUpdateUsertoUser(ConfigUpdateUserRequest request)
    {
        if (request == null)
        {
            return null;
        }

        UserProfile profile = new UserProfile();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        profile.setBirthDate(LocalDate.parse(request.birthdate(), formatter));
        profile.setFirstName(request.firstName());
        profile.setLastName(request.lastName());
        profile.setBio(request.bio());
        profile.setLocation(request.location());
        profile.setGender(UserGender.valueOf(request.gender()));
        profile.setStatus(UserStatus.valueOf(request.status()));
        profile.setLanguage(Language.valueOf(request.language()));

        User user = User.builder()
                .userProfile(profile) // Guardar UserProfile
                .email(request.email())
                .password(request.password())
                .username(request.username())
                .phone(request.phone())
                .userType(UserType.valueOf(request.userType()))
                .updatedAt(LocalDateTime.now())
                .build();

        profile.setUser(user); //Guardado bidireccional
        return user;
    }

    // Convertir "GeneralUpdateUser" a "User"
    // No hace falta

    // Convertir "User" a "UserResponse"
    public UserResponse toUserResponse(User user)
    {
        if (user == null)
        {
            return null;
        }

        UserProfile profile = user.getUserProfile();

        return new UserResponse(
                user.getId(),
                user.getEmail(),
                user.getUsername(),
                user.getPhone(),
                user.getUserType(),
                user.getCreatedAt(),
                user.getUpdatedAt(),
                profile.getBirthDate(),
                profile.getFirstName(),
                profile.getLastName(),
                profile.getBio(),
                profile.getLocation(),
                profile.getGender(),
                profile.getStatus(),
                profile.getLanguage()
        );
    }

    // Convertir "User" a "GeneralUserResponse"
    public GeneralUserResponse toGeneralUserResponse(User user)
    {
        if (user == null)
        {
            return null;
        }

        UserProfile profile = user.getUserProfile();

        return new GeneralUserResponse(
                profile.getStatus(),
                profile.getLanguage()
        );
    }

    // Convertir "User" a "UserProfileResponse"
    public UserProfileResponse toUserProfileResponse(User user)
    {
        return new UserProfileResponse(
                user.getUsername(),
                user.getUserType(),
                user.getCreatedAt(),
                user.getUserProfile().getBirthDate(),
                user.getUserProfile().getBio(),
                user.getUserProfile().getLocation(),
                user.getUserProfile().getGender(),
                user.getUserProfile().getStatus(),
                user.getUserProfile().getLanguage()
        );
    }

    // Convertir "List<User>" a "List<UserResponse>"
    public List<UserResponse> toListUserResponse(List<User> users)
    {
        if (users == null)
        {
            return null;
        }

        return users.stream()
                .map(this::toUserResponse)
                .collect(Collectors.toList());
    }
}
