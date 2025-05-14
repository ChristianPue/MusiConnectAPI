package com.api.musiconnect.service;

import com.api.musiconnect.dto.request.FavoriteProjectRequest;
import com.api.musiconnect.dto.response.FavoriteProjectResponse;
import com.api.musiconnect.model.entity.FavoriteConvocatori;
import com.api.musiconnect.model.entity.User;
import com.api.musiconnect.repository.FavoriteConvocatoriRepository;
import com.api.musiconnect.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FavoriteProjectService {

    private final FavoriteConvocatoriRepository favoriteConvocatoriRepository;
    private final UserRepository userRepository;

    @Transactional
    public FavoriteProjectResponse addFavoriteProject(FavoriteProjectRequest request) {
        // Verificar si el usuario existe
        Optional<User> user = userRepository.findById(request.getUserId());
        if (user.isEmpty()) {
            throw new RuntimeException("Usuario no encontrado");
        }

        // Verificar si el proyecto ya está en favoritos
        Optional<FavoriteConvocatori> existingFavorite = favoriteConvocatoriRepository
                .findByUserIdAndProjectId(request.getUserId(), request.getProjectId());
        
        if (existingFavorite.isPresent()) {
            throw new RuntimeException("El proyecto ya está marcado como favorito");
        }

        // Crear nuevo favorito
        FavoriteConvocatori favorite = FavoriteConvocatori.builder()
                .user(user.get())
                .projectId(request.getProjectId())
                .build();

        FavoriteConvocatori savedFavorite = favoriteConvocatoriRepository.save(favorite);

        return new FavoriteProjectResponse(
                savedFavorite.getId(),
                savedFavorite.getProjectId(),
                "Proyecto " + savedFavorite.getProjectId(), // Este es un ejemplo, puedes añadir el nombre real del proyecto
                savedFavorite.getUser().getId(),
                savedFavorite.getUser().getUsername(),
                savedFavorite.getCreatedAt()
        );
    }

    @Transactional
    public void removeFavoriteProject(FavoriteProjectRequest request) {
        Optional<FavoriteConvocatori> favorite = favoriteConvocatoriRepository
                .findByUserIdAndProjectId(request.getUserId(), request.getProjectId());

        if (favorite.isEmpty()) {
            throw new RuntimeException("Proyecto no encontrado en favoritos");
        }

        favoriteConvocatoriRepository.delete(favorite.get());
    }
}
