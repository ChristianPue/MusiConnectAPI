package com.api.musiconnect.repository;

import com.api.musiconnect.model.entity.FavoriteConvocatori;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FavoriteConvocatoriRepository extends JpaRepository<FavoriteConvocatori, Long> {
    List<FavoriteConvocatori> findByUserId(Long userId); // Ya existe, no hay cambios aquí.

    // Agregado: buscar un proyecto favorito por usuario y proyecto
    Optional<FavoriteConvocatori> findByUserIdAndProjectId(Long userId, Long projectId);
}
