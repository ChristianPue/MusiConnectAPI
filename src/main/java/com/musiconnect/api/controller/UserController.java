package com.musiconnect.api.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.musiconnect.api.dto.request.RegisterUserRequest;
import com.musiconnect.api.dto.request.UserProfileRequest;
import com.musiconnect.api.dto.response.UserProfileResponse;
import com.musiconnect.api.dto.response.UserResponse;
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

  @PatchMapping(path = "/{id}")
  public ResponseEntity<UserProfileResponse> editUserProfile(@PathVariable Long id, @Valid @RequestBody UserProfileRequest request) {
    return new ResponseEntity<>(service.editUserProfile(id, request), HttpStatus.OK);
  }

  @DeleteMapping(path = "/{id}")
  public ResponseEntity<String> deleteUser(@PathVariable Long id) {
    return new ResponseEntity<>(service.deleteUser(id), HttpStatus.OK);
  }


}
