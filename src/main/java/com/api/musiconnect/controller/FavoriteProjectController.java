package com.api.musiconnect.controller;

import com.api.musiconnect.dto.request.FavoriteProjectRequest;
import com.api.musiconnect.dto.response.FavoriteProjectResponse;
import com.api.musiconnect.service.FavoriteProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/favorites")
@RequiredArgsConstructor
public class FavoriteProjectController {

    private final FavoriteProjectService favoriteProjectService;

    // Agregar proyecto a favoritos
    @PostMapping
    public ResponseEntity<FavoriteProjectResponse> addFavoriteProject(
            @Valid @RequestBody FavoriteProjectRequest request) {
        FavoriteProjectResponse response = favoriteProjectService.addFavoriteProject(request);
        return ResponseEntity.ok(response);
    }

    // Eliminar proyecto de favoritos
    @DeleteMapping
    public ResponseEntity<Void> removeFavoriteProject(
            @Valid @RequestBody FavoriteProjectRequest request) {
        favoriteProjectService.removeFavoriteProject(request);
        return ResponseEntity.noContent().build();
    }
}
