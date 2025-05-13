package com.api.musiconnect.repository;

import com.api.musiconnect.model.entity.FavoriteConvocatori;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FavoriteConvocatoriRepository extends JpaRepository<FavoriteConvocatori, Long> {
    List<FavoriteConvocatori> findByUserId(Long userId);
}