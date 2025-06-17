package com.api.musiconnect.service;

import com.api.musiconnect.dto.request.ConfigUpdateUserRequest;
import com.api.musiconnect.dto.request.GeneralUpdateUserRequest;
import com.api.musiconnect.dto.request.RegisterUserRequest;
import com.api.musiconnect.dto.response.GeneralUserResponse;
import com.api.musiconnect.dto.response.UserProfileResponse;
import com.api.musiconnect.dto.response.UserResponse;
import com.api.musiconnect.exception.BadRequestException;
import com.api.musiconnect.exception.DuplicateResourceException;
import com.api.musiconnect.exception.InvalidInputException;
import com.api.musiconnect.exception.ResourceNotFoundException;
import com.api.musiconnect.mapper.UserMapper;
import com.api.musiconnect.model.entity.User;
import com.api.musiconnect.model.enums.Language;
import com.api.musiconnect.model.enums.UserGender;
import com.api.musiconnect.model.enums.UserStatus;
import com.api.musiconnect.model.enums.UserType;
import com.api.musiconnect.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserService
{
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    private final ValidationFunctions validationFunctions;

    // Registrar usuario
    @Transactional
    public UserResponse registerUser(RegisterUserRequest request)
    {
        // Saber si el usuario con dichos datos ya está en la base de datos
        if (userRepository.existsByEmail(request.email()))
        {
            throw  new DuplicateResourceException("El email ya existe.");
        }
        if (userRepository.existsByUsername(request.username()))
        {
            throw new DuplicateResourceException("El username ya existe.");
        }

        User user = userMapper.registerUsertoUser(request);
        userRepository.save(user);

        return userMapper.toUserResponse(user);
    }

    // Mostrar todos los usuarios
    @Transactional
    public List<UserResponse> getAllUsers()
    {
        List<User> users = userRepository.findAll();
        return userMapper.toListUserResponse(users);
    }

    // Mostrar usuario según su id
    @Transactional
    public UserResponse getUserById(Long id)
    {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("El usuario no existe"));
        return userMapper.toUserResponse(user);
    }

    // Mostrar usuario según su username
    @Transactional
    public UserProfileResponse getUserByUsername(String username)
    {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("El usuario no existe"));
        return userMapper.toUserProfileResponse(user);
    }

    // Actualizar usuario según su id
    @Transactional
    public UserResponse configUpdateUserById(Long id, ConfigUpdateUserRequest request)
    {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("El usuario no se encontró."));

        // Validar si no hay datos para actualizar
        if (validationFunctions.isRequestEmpty(request))
        {
            throw new BadRequestException("No se actualizó ningún dato.");
        }

        String email, password, username, phone; UserType userType;
        LocalDate birthdate; String firstname, lastname, bio, location;
        UserGender gender; UserStatus status; Language language;

        // Validar Username
        if (validationFunctions.isNotNullOrBlank(request.username()))
        {
            username = request.username();
            if (userRepository.existsByUsername(username))
            {
                throw new DuplicateResourceException("El username ya está registrado.");
            }
            validationFunctions.updateIfNotNull(username, user::setUsername);
        }
        // Validar Email
        if (validationFunctions.isNotNullOrBlank(request.email()))
        {
            email = request.email();
            if (userRepository.existsByEmail(email))
            {
                throw new DuplicateResourceException("El email ya está registrado.");
            }
            validationFunctions.updateIfNotNull(email, user::setEmail);
        }

        // Validar Contraseña
        if (validationFunctions.isNotNullOrBlank(request.password()))
        {
            password = request.password();
            // Es la misma contraseña?
            if (Objects.equals(password, user.getPassword()))
            {
                throw new InvalidInputException("La contraseña no puede ser la misma.");
            }
            validationFunctions.updateIfNotNull(password, user::setPassword);
        }

        // Se ingresó una fecha de nacimento válida? (mayor o igual de 18 años)
        if (validationFunctions.isNotNullOrBlank(request.birthdate()))
        {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            birthdate = LocalDate.parse(request.birthdate(), formatter);
            if (!validationFunctions.isValidBirthdate(birthdate))
            {
                throw new InvalidInputException("La fecha de nacimiento no es válida. Debe ser de un mayor de edad.");
            }
            validationFunctions.updateIfNotNull(birthdate, user.getUserProfile()::setBirthDate);
        }

        phone = request.phone();
        validationFunctions.updateIfNotNull(phone, user::setPhone);

        if (validationFunctions.isNotNullOrBlank(request.userType()))
        {
            userType = UserType.valueOf(request.userType());
            validationFunctions.updateIfNotNull(userType, user::setUserType);
        }

        firstname = request.firstName();
        validationFunctions.updateIfNotNull(firstname, user.getUserProfile()::setFirstName);

        lastname = request.lastName();
        validationFunctions.updateIfNotNull(lastname, user.getUserProfile()::setLastName);

        bio = request.bio();
        validationFunctions.updateIfNotNull(bio, user.getUserProfile()::setBio);

        location = request.location();
        validationFunctions.updateIfNotNull(location, user.getUserProfile()::setLocation);

        if (validationFunctions.isNotNullOrBlank(request.gender()))
        {
            gender = UserGender.valueOf(request.gender());
            validationFunctions.updateIfNotNull(gender, user.getUserProfile()::setGender);
        }

        if (validationFunctions.isNotNullOrBlank(request.status()))
        {
            status = UserStatus.valueOf(request.status());
            validationFunctions.updateIfNotNull(status, user.getUserProfile()::setStatus);
        }

        if (validationFunctions.isNotNullOrBlank(request.language()))
        {
            language = Language.valueOf(request.language());
            validationFunctions.updateIfNotNull(language, user.getUserProfile()::setLanguage);
        }

        userRepository.save(user);

        return userMapper.toUserResponse(user);
    }

    // Actualizar datos generales según su id
    @Transactional
    public GeneralUserResponse generalUpdateUser(GeneralUpdateUserRequest request)
    {
        User user = userRepository.findById(request.id())
                .orElseThrow(() -> new ResourceNotFoundException("El usuario no existe"));

        if (validationFunctions.isNotNull(request.status()))
        {
            UserStatus status = UserStatus.valueOf(request.status());
            validationFunctions.updateIfNotNull(status, user.getUserProfile()::setStatus);
        }
        if (validationFunctions.isNotNull(request.language()))
        {
            Language language = Language.valueOf(request.language());
            validationFunctions.updateIfNotNull(language, user.getUserProfile()::setLanguage);
        }

        userRepository.save(user);

        return userMapper.toGeneralUserResponse(user);
    }

    // Eliminar usuario según su id
    @Transactional
    public String deleteUserById(Long id)
    {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("El usuario no existe"));
        userRepository.delete(user);
        return "El usuario ha sido eliminado";
    }
}
