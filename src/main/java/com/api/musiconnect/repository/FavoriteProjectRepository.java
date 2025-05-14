package com.api.musiconnect.repository;

import com.api.musiconnect.model.entity.FavoriteProject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FavoriteProjectRepository extends JpaRepository<FavoriteProject, Long> {

    Optional<FavoriteProject> findByUserIdAndProjectId(Long userId, Long projectId);
}
