package com.api.musiconnect.service;

import com.api.musiconnect.dto.request.FavoriteProjectRequest;
import com.api.musiconnect.dto.response.FavoriteProjectResponse;
import com.api.musiconnect.model.entity.FavoriteProject;
import com.api.musiconnect.model.entity.Project;
import com.api.musiconnect.model.entity.User;
import com.api.musiconnect.repository.FavoriteProjectRepository;
import com.api.musiconnect.repository.ProjectRepository;
import com.api.musiconnect.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FavoriteProjectService {

    private final FavoriteProjectRepository favoriteProjectRepository;
    private final UserRepository userRepository;
    private final ProjectRepository projectRepository;

    @Transactional
    public FavoriteProjectResponse addFavoriteProject(FavoriteProjectRequest request) {
        Optional<User> user = userRepository.findById(request.getUserId());
        if (user.isEmpty()) {
            throw new RuntimeException("Usuario no encontrado");
        }

        Optional<Project> project = projectRepository.findById(request.getProjectId());
        if (project.isEmpty()) {
            throw new RuntimeException("Proyecto no encontrado");
        }

        Optional<FavoriteProject> existingFavorite = favoriteProjectRepository
                .findByUserIdAndProjectId(request.getUserId(), request.getProjectId());

        if (existingFavorite.isPresent()) {
            throw new RuntimeException("El proyecto ya está marcado como favorito");
        }

        FavoriteProject favorite = FavoriteProject.builder()
                .user(user.get())
                .project(project.get())
                .build();

        FavoriteProject savedFavorite = favoriteProjectRepository.save(favorite);

        return new FavoriteProjectResponse(
                savedFavorite.getId(),
                savedFavorite.getProject().getId(),
                savedFavorite.getProject().getName(), // Asegúrate de que Project tenga getName()
                savedFavorite.getUser().getId(),
                savedFavorite.getUser().getUsername(),
                savedFavorite.getCreatedAt()
        );
    }

    @Transactional
    public void removeFavoriteProject(FavoriteProjectRequest request) {
        Optional<FavoriteProject> favorite = favoriteProjectRepository
                .findByUserIdAndProjectId(request.getUserId(), request.getProjectId());

        if (favorite.isEmpty()) {
            throw new RuntimeException("Proyecto no encontrado en favoritos");
        }

        favoriteProjectRepository.delete(favorite.get());
    }
}
