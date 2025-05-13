package com.api.musiconnect.mapper;

import com.api.musiconnect.dto.request.GeneralUpdateUser;
import com.api.musiconnect.dto.request.RegisterUserRequest;
import com.api.musiconnect.dto.request.ConfigUpdateUserRequest;
import com.api.musiconnect.dto.response.UserResponse;
import com.api.musiconnect.model.entity.User;
import com.api.musiconnect.model.enums.Gender;
import com.api.musiconnect.model.enums.Instrument;
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
    // Convertir CreateUserRequest a User
    public User RegisterUser_UserRequestToUser(RegisterUserRequest request)
    {
        if (request == null)
        {
            return null;
        }

        // Formato de fecha para LocalDate
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        // Tipo de usuario
        UserType userType = UserType.ARTIST;
        // Fecha de creación
        LocalDateTime now = LocalDateTime.now();
        // Estado de disponibilidad
        UserStatus status = UserStatus.ONLINE;
        // Lista de instrumentos: vacío
        List<Instrument> instruments = List.of();
        // Convertir: String a Enum. Atributo gender
        Gender gender = Gender.valueOf(request.gender());

        return User.builder()
                .userType(userType)
                .email(request.email())
                .username(request.username())
                .password(request.password())
                .birthdate(LocalDate.parse(request.birthdate(), formatter)) // Convertir de String a LocalDate si es necesario
                .gender(gender)
                .bio(null)
                .location(null)
                .status(status)
                .instruments(instruments)
                .createdAt(now)
                .build();
    }

    // Convertir UpdateUserRequest a User
    public User ConfigUpdate_UserRequestToUser(ConfigUpdateUserRequest request)
    {
        if (request == null)
        {
            return null;
        }

        // Formato de fecha para LocalDate
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        // Convertir: String a Enum. Atributo UserType
        UserType userType = UserType.valueOf(request.userType());
        // Convertir: String a Enum. Atributo UserStatus
        UserStatus status = UserStatus.valueOf(request.status());
        // Convertir: String a Enum. Atributo gender
        Gender gender = Gender.valueOf(request.gender());
        // Fecha de creación
        LocalDateTime now = LocalDateTime.now();

        return User.builder()
                .userType(userType)
                .email(request.email())
                .username(request.username())
                .password(request.password())
                .birthdate(LocalDate.parse(request.birthdate(), formatter)) // Convertir de String a LocalDate si es necesario
                .gender(gender)
                .bio(request.bio())
                .location(request.location())
                .status(status)
                .updatedAt(now)
                .build();
    }

    // Convertir User a UserResponse
    public UserResponse UserToUserResponse(User user)
    {
        if (user == null)
        {
            return null;
        }

        return new UserResponse(
                user.getId(),
                user.getUserType(),
                user.getEmail(),
                user.getUsername(),
                user.getPassword(),
                user.getBirthdate(),
                user.getGender(),
                user.getBio(),
                user.getLocation(),
                user.getStatus(),
                user.getInstruments().size(),
                user.getCreatedAt(),
                user.getUpdatedAt()
        );
    }

    // Convertir List<User> a List<UserResponse>
    public List<UserResponse> UsersToUsersResponse(List<User> users)
    {
        if (users == null)
        {
            return null;
        }

        return users.stream()
                .map(this::UserToUserResponse)
                .collect(Collectors.toList());
    }

    // Convertir GeneralUpdate a User
    public User GeneralUpdate_UserRequestToUser(GeneralUpdateUser request)
    {
        if (request == null)
        {
            return null;
        }

        // Convertir: String a Enum. Atributo UserStatus
        UserStatus status = UserStatus.valueOf(request.status());

        return User.builder()
                .status(status)
                .build();
    }
}
