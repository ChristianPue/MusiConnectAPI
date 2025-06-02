package com.musiconnect.api.mapper;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.musiconnect.api.dto.request.RegisterUserRequest;
import com.musiconnect.api.dto.response.UserProfileResponse;
import com.musiconnect.api.dto.response.UserResponse;
import com.musiconnect.api.model.entity.User;
import com.musiconnect.api.model.entity.UserProfile;
import com.musiconnect.api.model.enums.Language;
import com.musiconnect.api.model.enums.UserGender;
import com.musiconnect.api.model.enums.UserStatus;
import com.musiconnect.api.model.enums.UserType;

@Component
public class UserMapper {
  // Convertir "RegisterUserRequest" a "User"
  public User toUser(RegisterUserRequest request) {
    if (request == null) {
      return null;
    }

    UserProfile profile = new UserProfile();
    profile.setType(UserType.USER);
    profile.setUsername(request.username());
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    profile.setBirthdate(LocalDate.parse(request.birthdate(), formatter));
    profile.setGender(UserGender.valueOf(request.gender()));
    profile.setStatus(UserStatus.ONLINE);
    profile.setLanguage(Language.ENGLISH);

    User user = User.builder()
      .userProfile(profile)
      .email(request.email())
      .password(request.password())
      .createdAt(LocalDateTime.now())
      .build();
    
    profile.setUser(user); // Guardado bidireccional
    return user;
  }
  
  // Convertir "List<User>" a "List<UserResponse>"
  public List<UserResponse> toUsersReponse(List<User> users) {
    if (users == null) {
      return null;
    }

    return users.stream()
      .map(this::toUserResponse)
      .collect(Collectors.toList());
  }

  // Convertir "User" a "UserResponse"
  public UserResponse toUserResponse(User user) {
    if (user == null) {
      return null;
    }

    return new UserResponse(
      user.getId(),
      user.getEmail(),
      user.getPassword(),
      user.getPhone(),
      user.getCreatedAt(),
      user.getUpdatedAt(),
      user.getUserProfile().getType(),
      user.getUserProfile().getUsername(),
      user.getUserProfile().getBirthdate(),
      user.getUserProfile().getBio(),
      user.getUserProfile().getLocation(),
      user.getUserProfile().getGender(),
      user.getUserProfile().getStatus()
    );
  }

  // Convertir "User" a "UserProfileResponse"
  public UserProfileResponse toUserProfileResponse(User user) {
    if (user == null) {
      return null;
    }

    return new UserProfileResponse(
      user.getUserProfile().getType(),
      user.getUserProfile().getUsername(),
      user.getUserProfile().getBirthdate(),
      user.getUserProfile().getBio(),
      user.getUserProfile().getLocation(),
      user.getUserProfile().getGender(),
      user.getUserProfile().getStatus(),
      user.getUserProfile().getLanguage()
    );
  } 

}
