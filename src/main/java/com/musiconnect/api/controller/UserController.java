package com.musiconnect.api.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.musiconnect.api.dto.request.RegisterUserRequest;
import com.musiconnect.api.dto.request.UserProfileRequest;
import com.musiconnect.api.dto.response.UserProfileResponse;
import com.musiconnect.api.dto.response.UserResponse;
import com.musiconnect.api.model.enums.UserStatus;
import com.musiconnect.api.service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
  private final UserService service;

  @PostMapping
  public ResponseEntity<UserResponse> registerUser(@Valid @RequestBody RegisterUserRequest request) {
    return new ResponseEntity<>(service.registerUser(request), HttpStatus.CREATED);
  }

  @GetMapping
  public ResponseEntity<List<UserResponse>> getAllUsers() {
    return new ResponseEntity<>(service.getAllUsers(), HttpStatus.OK);
  }

  @PostMapping(path = "/{id}")
  public ResponseEntity<UserProfileResponse> createProfile(@PathVariable Long id, @Valid @RequestBody UserProfileRequest request) {
    return new ResponseEntity<>(service.createProfile(id, request), HttpStatus.CREATED);
  }

  @PutMapping(path = "/{id}/profile")
  public ResponseEntity<UserProfileResponse> editProfile(@PathVariable Long id, @Valid @RequestBody UserProfileRequest request) {
    return new ResponseEntity<>(service.editProfile(id, request), HttpStatus.OK);
  }

  @DeleteMapping(path = "/{id}")
  public ResponseEntity<String> deleteUser(@PathVariable Long id) {
    return new ResponseEntity<>(service.deleteUser(id), HttpStatus.OK);
  }

  @PatchMapping(path = "/{id}/status")
  public ResponseEntity<UserProfileResponse> changeStatus(@PathVariable Long id, @RequestParam UserStatus status) {
    return new ResponseEntity<>(service.changeStatus(id, status), HttpStatus.OK);
  }
}