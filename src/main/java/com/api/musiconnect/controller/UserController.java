package com.api.musiconnect.controller;

import com.api.musiconnect.dto.request.ConfigUpdateUserRequest;
import com.api.musiconnect.dto.request.GeneralUpdateUserRequest;
import com.api.musiconnect.dto.request.RegisterUserRequest;
import com.api.musiconnect.dto.response.GeneralUserResponse;
import com.api.musiconnect.dto.response.UserProfileResponse;
import com.api.musiconnect.dto.response.UserResponse;
import com.api.musiconnect.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserResponse> registerUser(@Valid @RequestBody RegisterUserRequest request) {
        return new ResponseEntity<>(userService.registerUser(request), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable Long id) {
        return new ResponseEntity<>(userService.getUserById(id), HttpStatus.OK);
    }

    @PutMapping(path = "/config_user={id}")
    public ResponseEntity<UserResponse> configUpdateUserById(@PathVariable Long id, @Valid @RequestBody ConfigUpdateUserRequest request)
    {
        return new ResponseEntity<>(userService.configUpdateUserById(id, request), HttpStatus.OK);
    }

    @GetMapping("/username={username}")
    public ResponseEntity<UserProfileResponse> getUserByUsername(@PathVariable String username)
    {
        return new ResponseEntity<>(userService.getUserByUsername(username), HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable Long id)
    {
        return new ResponseEntity<>(userService.deleteUserById(id), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<GeneralUserResponse> generalUpdateUser(@Valid @RequestBody GeneralUpdateUserRequest request)
    {
        return new ResponseEntity<>(userService.generalUpdateUser(request), HttpStatus.OK);
    }
}
