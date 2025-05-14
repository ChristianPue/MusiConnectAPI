package com.api.musiconnect.controller;

import com.api.musiconnect.dto.request.PublicationRequest;
import com.api.musiconnect.dto.response.PublicationResponse;
import com.api.musiconnect.service.PublicationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/publications")
@RequiredArgsConstructor
public class PublicationController {

    private final PublicationService publicationService;

    // Crear una nueva publicación
    @PostMapping
    public ResponseEntity<PublicationResponse> createPublication(@Valid @RequestBody PublicationRequest request) {
        PublicationResponse response = publicationService.createPublication(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // Obtener todas las publicaciones de un usuario
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<PublicationResponse>> getPublicationsByUser(@PathVariable Long userId) {
        List<PublicationResponse> publications = publicationService.getPublicationsByUser(userId);
        return new ResponseEntity<>(publications, HttpStatus.OK);
    }

    // Obtener publicaciones paginadas de un usuario
    @GetMapping("/paged/user/{userId}")
    public ResponseEntity<Page<PublicationResponse>> getPublicationsPaged(@PathVariable Long userId, Pageable pageable) {
        Page<PublicationResponse> publications = publicationService.getPublicationsPaged(userId, pageable);
        return new ResponseEntity<>(publications, HttpStatus.OK);
    }

    // Obtener una publicación por ID
    @GetMapping("/{publicationId}")
    public ResponseEntity<PublicationResponse> getPublicationById(@PathVariable Long publicationId) {
        PublicationResponse publication = publicationService.getPublicationById(publicationId);
        return new ResponseEntity<>(publication, HttpStatus.OK);
    }

    // Actualizar una publicación
    @PutMapping("/{publicationId}")
    public ResponseEntity<PublicationResponse> updatePublication(@PathVariable Long publicationId, @Valid @RequestBody PublicationRequest request) {
        PublicationResponse updated = publicationService.updatePublication(publicationId, request);
        return new ResponseEntity<>(updated, HttpStatus.ACCEPTED);
    }

    // Eliminar una publicación
    @DeleteMapping("/{publicationId}")
    public ResponseEntity<String> deletePublication(@PathVariable Long publicationId) {
        String response = publicationService.deletePublication(publicationId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
