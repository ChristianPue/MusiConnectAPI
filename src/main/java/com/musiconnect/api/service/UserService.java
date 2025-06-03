package com.musiconnect.api.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;

import com.musiconnect.api.dto.request.RegisterUserRequest;
import com.musiconnect.api.dto.request.UserProfileRequest;
import com.musiconnect.api.dto.response.UserProfileResponse;
import com.musiconnect.api.dto.response.UserResponse;
import com.musiconnect.api.exception.BusinessRuleException;
import com.musiconnect.api.exception.DuplicateResourceException;
import com.musiconnect.api.exception.InvalidInputException;
import com.musiconnect.api.exception.ResourceNotFoundException;
import com.musiconnect.api.mapper.UserMapper;
import com.musiconnect.api.model.entity.User;
import com.musiconnect.api.model.enums.Language;
import com.musiconnect.api.model.enums.UserGender;
import com.musiconnect.api.model.enums.UserStatus;
import com.musiconnect.api.model.enums.UserType;
import com.musiconnect.api.repository.UserRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
  private final UserRepository repository;
  private final UserMapper mapper;
  private final OtherFunctions functions;

  // Registrar cuenta de usuario
  @Transactional
  public UserResponse registerUser(RegisterUserRequest request) {
    // Excepciones:
    // Si email y username ya existen
    if (repository.existsByEmailAndUsername(request.email(), request.username())) {
      throw new DuplicateResourceException("El email y username ya están registrados.");
    }
    // Si email ya existe
    if (repository.existsByEmail(request.email())) {
      throw new DuplicateResourceException("El email ya está registrado.");
    }
    // Si username ya existe
    if (repository.existsByUsername(request.username())) {
      throw new DuplicateResourceException("El username ya está registrado.");
    }

    // Si es mayor de edad
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    LocalDate birthdate = LocalDate.parse(request.birthdate(), formatter);
    if (!functions.isValidBirthdate(birthdate)) {
      throw new InvalidInputException("Debe ser mayor de edad.");
    }

    User user = mapper.toUser(request);
    repository.save(user);

    return mapper.toUserResponse(user);
  }

  // Mostrar usuarios
  @Transactional
  public List<UserResponse> getAllUsers() {
    List<User> users = repository.findAll();
    return mapper.toUsersReponse(users);
  }

  // Eliminar usuario
  @Transactional
  public String deleteUser(Long id) {
    User user = repository.findById(id)
      .orElseThrow(() -> new ResourceNotFoundException("El usuario no existe."));
    
    repository.delete(user);
    return "El usuario no ha sido eliminado.";
  }

  // US01: Crear perfil musical
  @Transactional
  public UserProfileResponse createProfile(Long id, UserProfileRequest request) {
    User user = repository.findById(id)
      .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado."));
    
    // Excepciones:
    // 1. Type
    if (!functions.isNotNullOrBlank(request.type())) {
      throw new BusinessRuleException("No se ingresó un tipo de usuario.");
    }
    // 2. Bio
    if (!functions.isNotNullOrBlank(request.bio())) {
      throw new BusinessRuleException("No se ingresó una biografía.");
    }
    // 3. Location
    if (!functions.isNotNullOrBlank(request.location())) {
      throw new BusinessRuleException("No se ingresó una locación.");
    }
    // 4. Gender
    if (!functions.isNotNullOrBlank(request.gender())) {
      throw new BusinessRuleException("No se ingresó un género.");
    }
    
    // Actualización de datos:
    user.getUserProfile().setType(UserType.valueOf(request.type()));
    user.getUserProfile().setBio(request.bio());
    user.getUserProfile().setLocation(request.location());
    user.getUserProfile().setGender(UserGender.valueOf(request.gender()));
    user.setUpdatedAt(LocalDateTime.now());

    repository.save(user);

    return mapper.toUserProfileResponse(user);
  }

  // US02: Editar perfil
  @Transactional
  public UserProfileResponse editProfile(Long id, UserProfileRequest request) {
    User user = repository.findById(id)
      .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado."));
    
    // Si se envió un DTO vacío
    if (functions.isRequestEmpty(request)) {
      return mapper.toUserProfileResponse(user);
    }

    if (functions.isNotNullOrBlank(request.type())) {
      UserType type = UserType.valueOf(request.type());
      
      user.getUserProfile().setType(type);
    }

    if (functions.isNotNullOrBlank(request.username())) {
      String username = request.username();

      if (repository.existsByUsername(username)) {
        throw new DuplicateResourceException("El username ya está registrado.");
      }

      user.getUserProfile().setUsername(username);
    }

    if (functions.isNotNullOrBlank(request.birthdate())) {
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
      LocalDate birthdate = LocalDate.parse(request.birthdate(), formatter);

      if (!functions.isValidBirthdate(birthdate)) {
        throw new InvalidInputException("Debe ser mayor de edad.");
      }

      user.getUserProfile().setBirthdate(birthdate);
    }

    if (functions.isNotNullOrBlank(request.bio())) {
      String bio = request.bio();

      user.getUserProfile().setBio(bio);
    }

    if (functions.isNotNullOrBlank(request.location())) {
      String location = request.location();

      user.getUserProfile().setLocation(location);
    }

    if (functions.isNotNullOrBlank(request.gender())) {
      UserGender gender = UserGender.valueOf(request.gender());

      user.getUserProfile().setGender(gender);
    }

    if (functions.isNotNullOrBlank(request.status())) {
      UserStatus status = UserStatus.valueOf(request.status());

      user.getUserProfile().setStatus(status);
    }

    if (functions.isNotNullOrBlank(request.language())) {
      Language language = Language.valueOf(request.language());

      user.getUserProfile().setLanguage(language);
    }

    user.setUpdatedAt(LocalDateTime.now());

    repository.save(user);

    return mapper.toUserProfileResponse(user);
  }

  // US03: Establecer disponibilidad
  public UserProfileResponse changeStatus(Long id, UserStatus status) {
    User user = repository.findById(id)
      .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado."));
    
    user.getUserProfile().setStatus(status);

    repository.save(user);

    return mapper.toUserProfileResponse(user);
  }
}
