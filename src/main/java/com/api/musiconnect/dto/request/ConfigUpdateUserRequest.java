package com.api.musiconnect.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record ConfigUpdateUserRequest(
        @Email(message = "El email debe tener un formato válido.")
        String email,

        @Size(min = 8, max = 50, message = "La contraseña debe tener entre 8 y 50 caracteres.")
        @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&]).{8,50}$",
                message = "La contraseña debe contener al menos una letra mayúscula, una letra minúscula, un número y un carácter especial.")
        String password,

        @Size(min = 3, max = 30, message = "El nombre de usuario debe tener entre 3 y 30 caracteres.")
        String username,

        @Size(min = 9, max = 9, message = "El teléfono debe tener 9 dígitos.")
        @Pattern(regexp = "^[0-9]{9}$", message = "El teléfono debe contener solo números.")
        String phone,

        @Pattern(regexp = "^(ADMIN|ARTIST|PRODUCER)$", message = "El tipo de usuario debe ser ADMIN, ARTIST o PRODUCER.")
        String userType,

        @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "El formato de fecha debe ser yyyy-MM-dd.")
        String birthdate,

        @Pattern(regexp = "^[a-zA-Z]+$", message = "El firstname solo debe contener letras.")
        String firstName,

        @Pattern(regexp = "^[a-zA-Z]+$", message = "El lastname solo debe contener letras.")
        String lastName,

        String bio,

        String location,

        @Pattern(regexp = "^(MALE|FEMALE|INDETERMINATE)$", message = "El género debe ser MALE, FEMALE o INDETERMINATE.")
        String gender,

        @Pattern(regexp = "^(ONLINE|AWAY|DO_NOT_DISTURB|INVISIBLE)$", message = "El estado debe ser ACTIVE o INACTIVE.")
        String status,

        @Pattern(regexp = "^(ENGLISH|CHINESE|SPANISH|FRENCH|JAPANESE|TURKEY|GERMANY|OTHER)$", message = "El idioma debe ser uno de los siguientes: ENGLISH, CHINESE, SPANISH, FRENCH, JAPANESE, TURKEY, GERMANY o OTHER.")
        String language
) {
}
