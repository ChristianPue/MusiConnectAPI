package com.api.musiconnect.controller;

import com.api.musiconnect.dto.request.FollowRequest;
import com.api.musiconnect.dto.response.FollowResponse;
import com.api.musiconnect.service.FollowService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/follow")
@RequiredArgsConstructor
public class FollowController {

    private final FollowService followService;

    // Endpoint para seguir a un usuario
    @PostMapping
    public ResponseEntity<FollowResponse> followUser(@Valid @RequestBody FollowRequest request) {
        // Llamamos al servicio para seguir al usuario
        FollowResponse response = followService.followUser(request.followerId(), request.followedId());
        // Devolvemos la respuesta con el estado CREATED
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // Endpoint para dejar de seguir a un usuario con parámetros en la URL
    @DeleteMapping("/unfollow/{followerId}/{followedId}")
    public ResponseEntity<String> unfollowUser(@PathVariable Long followerId, @PathVariable Long followedId) {
        // Llamamos al servicio para dejar de seguir al usuario
        String response = followService.unfollowUser(followerId, followedId);
        // Devolvemos un mensaje con el estado OK
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // Endpoint para obtener todos los seguidores de un usuario
    @GetMapping("/followers/{userId}")
    public ResponseEntity<List<FollowResponse>> getFollowers(@PathVariable Long userId) {
        List<FollowResponse> followers = followService.getFollowers(userId);
        return new ResponseEntity<>(followers, HttpStatus.OK);
    }

    // Endpoint para obtener todos los usuarios seguidos por un usuario
    @GetMapping("/followed/{userId}")
    public ResponseEntity<List<FollowResponse>> getFollowed(@PathVariable Long userId) {
        List<FollowResponse> followed = followService.getFollowed(userId);
        return new ResponseEntity<>(followed, HttpStatus.OK);
    }

    // Endpoint para verificar si un usuario sigue a otro
    @GetMapping("/isFollowing")
    public ResponseEntity<Boolean> isFollowing(@RequestParam Long followerId, @RequestParam Long followedId) {
        boolean isFollowing = followService.isFollowing(followerId, followedId);
        return new ResponseEntity<>(isFollowing, HttpStatus.OK);
    }
}
