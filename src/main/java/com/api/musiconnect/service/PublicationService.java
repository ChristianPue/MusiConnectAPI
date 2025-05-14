package com.api.musiconnect.service;

import com.api.musiconnect.dto.request.PublicationRequest;
import com.api.musiconnect.dto.response.PublicationResponse;
import com.api.musiconnect.exception.ResourceNotFoundException;
import com.api.musiconnect.mapper.PublicationMapper;
import com.api.musiconnect.model.entity.Publication;
import com.api.musiconnect.model.entity.User;
import com.api.musiconnect.repository.PublicationRepository;
import com.api.musiconnect.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PublicationService {

    private final PublicationRepository publicationRepository;
    private final UserRepository userRepository;
    private final PublicationMapper publicationMapper;

    // Crear una publicación
    @Transactional
    public PublicationResponse createPublication(PublicationRequest request) {
        User author = userRepository.findById(request.authorId())
                .orElseThrow(() -> new ResourceNotFoundException("El autor con id " + request.authorId() + " no existe."));

        Publication publication = publicationMapper.publicationRequestToPublication(request, author);

        publicationRepository.save(publication);

        return publicationMapper.publicationToPublicationResponse(publication);
    }

    // Obtener todas las publicaciones de un usuario
    @Transactional
    public List<PublicationResponse> getPublicationsByUser(Long userId) {
        User author = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("El usuario con id " + userId + " no existe."));

        List<Publication> publications = publicationRepository.findByAuthorOrderByCreatedAtDesc(author);
        return publicationMapper.publicationsToPublicationsResponse(publications);
    }

    // Obtener publicaciones paginadas y ordenadas por fecha
    @Transactional
    public Page<PublicationResponse> getPublicationsPaged(Long userId, Pageable pageable) {
        User author = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("El usuario con id " + userId + " no existe."));

        Page<Publication> publicationsPage = publicationRepository.findByAuthorPaged(author, pageable);

        return publicationsPage.map(publicationMapper::publicationToPublicationResponse);
    }

    // Obtener una publicación por su ID
    @Transactional
    public PublicationResponse getPublicationById(Long publicationId) {
        Publication publication = publicationRepository.findById(publicationId)
                .orElseThrow(() -> new ResourceNotFoundException("La publicación con id " + publicationId + " no existe."));

        return publicationMapper.publicationToPublicationResponse(publication);
    }

    // Editar una publicación existente
    @Transactional
    public PublicationResponse updatePublication(Long publicationId, PublicationRequest request) {
        Publication publication = publicationRepository.findById(publicationId)
                .orElseThrow(() -> new ResourceNotFoundException("La publicación con id " + publicationId + " no existe."));

        publication.setTitle(request.title());
        publication.setContent(request.content());
        publicationRepository.save(publication);

        return publicationMapper.publicationToPublicationResponse(publication);
    }

    // Eliminar una publicación por ID
    @Transactional
    public String deletePublication(Long publicationId) {
        Publication publication = publicationRepository.findById(publicationId)
                .orElseThrow(() -> new ResourceNotFoundException("La publicación con id " + publicationId + " no existe."));

        publicationRepository.delete(publication);
        return "La publicación ha sido eliminada exitosamente.";
    }
}
