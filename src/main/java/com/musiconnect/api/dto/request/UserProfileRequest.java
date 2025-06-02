package com.musiconnect.api.dto.request;

import jakarta.validation.constraints.Pattern;

public record UserProfileRequest(
  @Pattern(regexp = "^(ADMIN|USER|ARTIST|BAND|PRODUCER)$", message = "El tipo debe ser ADMIN, USER, ARTIST, BAND o PRODUCER.")
  String type,
  
  String username,
  
  @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "El formato de fecha debe ser yyyy-MM-dd.")
  String birthdate,
  
  String bio,
  
  String location,
  
  @Pattern(regexp = "^(MALE|FEMALE|INDETERMINATE)$", message = "El género debe ser MALE, FEMALE o INDETERMINATE.")
  String gender,
  
  @Pattern(regexp = "^(ONLINE|AWAY|DO_NOT_DISTURB|INVISIBLE)$", message = "El estado debe ser ONLINE, AWAY, DO_NOT_DISTURB o INVISIBLE.")
  String status,
  
  @Pattern(regexp = "^(ENGLISH|SPANISH|CHINESE|FRENCH|GERMAN|JAPANESE|OTHER)$", message = "El idioma debe ser ENGLISH, SPANISH, CHINESE, FRENCH, GERMAN, JAPANESE o OTHER.")
  String language
) {
}
