package com.api.musiconnect.mapper;

import com.api.musiconnect.dto.request.PublicationRequest;
import com.api.musiconnect.dto.response.PublicationResponse;
import com.api.musiconnect.model.entity.Publication;
import com.api.musiconnect.model.entity.User;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class PublicationMapper {

    // Convertir PublicationRequest a Publication
    public Publication publicationRequestToPublication(PublicationRequest request, User author) {
        if (request == null || author == null) {
            return null;
        }

        return Publication.builder()
                .title(request.title())
                .content(request.content())
                .createdAt(LocalDateTime.now())
                .author(author)
                .build();
    }

    public PublicationResponse publicationToPublicationResponse(Publication publication) {
        if (publication == null) {
            return null;
        }

        return new PublicationResponse(
                publication.getId(),
                publication.getTitle(),
                publication.getContent(),
                publication.getCreatedAt(),
                publication.getAuthor().getId(),
                publication.getAuthor().getUsername(),
                publication.getComments() != null ? publication.getComments().size() : 0
        );
    }



    // Convertir List<Publication> a List<PublicationResponse>
    public List<PublicationResponse> publicationsToPublicationsResponse(List<Publication> publications) {
        if (publications == null) {
            return null;
        }

        return publications.stream()
                .map(this::publicationToPublicationResponse)
                .collect(Collectors.toList());
    }
}
