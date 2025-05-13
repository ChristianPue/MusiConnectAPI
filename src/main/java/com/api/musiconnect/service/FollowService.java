package com.api.musiconnect.service;

import com.api.musiconnect.dto.response.FollowResponse;
import com.api.musiconnect.exception.ResourceNotFoundException;
import com.api.musiconnect.mapper.FollowMapper;
import com.api.musiconnect.model.entity.Follow;
import com.api.musiconnect.model.entity.User;
import com.api.musiconnect.repository.FollowRepository;
import com.api.musiconnect.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FollowService {

    private final FollowRepository followRepository;
    private final UserRepository userRepository;
    private final FollowMapper followMapper;

    // Seguir a un usuario
    @Transactional
    public FollowResponse followUser(Long followerId, Long followedId) {
        // Verificar si los usuarios existen
        User follower = userRepository.findById(followerId)
                .orElseThrow(() -> new ResourceNotFoundException("El usuario con id " + followerId + " no existe."));
        User followed = userRepository.findById(followedId)
                .orElseThrow(() -> new ResourceNotFoundException("El usuario con id " + followedId + " no existe."));

        // Verificar si ya existe una relación de seguimiento
        if (followRepository.existsByFollowerAndFollowed(follower, followed)) {
            throw new RuntimeException("El usuario ya sigue a este usuario.");
        }

        // Crear el seguimiento
        Follow follow = Follow.builder()
                .follower(follower)
                .followed(followed)
                .build();

        followRepository.save(follow);

        // Retornar la respuesta
        return followMapper.FollowToFollowResponse(follow);
    }

    // Dejar de seguir a un usuario
    @Transactional
    public String unfollowUser(Long followerId, Long followedId) {
        // Verificar si los usuarios existen
        User follower = userRepository.findById(followerId)
                .orElseThrow(() -> new ResourceNotFoundException("El usuario con id " + followerId + " no existe."));
        User followed = userRepository.findById(followedId)
                .orElseThrow(() -> new ResourceNotFoundException("El usuario con id " + followedId + " no existe."));

        // Buscar el seguimiento
        followRepository.deleteByFollowerAndFollowed(follower, followed);
        return "El usuario ha dejado de seguir a este usuario.";
    }

    // Obtener todos los seguimientos de un usuario (a quién sigue)
    @Transactional
    public List<FollowResponse> getFollowers(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("El usuario con id " + userId + " no existe."));

        List<Follow> follows = followRepository.findByFollower(user);
        return followMapper.FollowsToFollowsResponse(follows);
    }

    // Obtener todos los seguidores de un usuario (quién lo sigue)
    @Transactional
    public List<FollowResponse> getFollowed(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("El usuario con id " + userId + " no existe."));

        List<Follow> follows = followRepository.findByFollowed(user);
        return followMapper.FollowsToFollowsResponse(follows);
    }

    // Verificar si un usuario sigue a otro
    @Transactional
    public boolean isFollowing(Long followerId, Long followedId) {
        User follower = userRepository.findById(followerId)
                .orElseThrow(() -> new ResourceNotFoundException("El usuario con id " + followerId + " no existe."));
        User followed = userRepository.findById(followedId)
                .orElseThrow(() -> new ResourceNotFoundException("El usuario con id " + followedId + " no existe."));

        return followRepository.existsByFollowerAndFollowed(follower, followed);
    }
}