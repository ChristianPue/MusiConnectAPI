package com.api.musiconnect.mapper;

import com.api.musiconnect.dto.request.CommentRequest;
import com.api.musiconnect.dto.response.CommentResponse;
import com.api.musiconnect.model.entity.Comment;
import com.api.musiconnect.model.entity.Publication;
import com.api.musiconnect.model.entity.User;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CommentMapper {

    // Convertir CommentRequest a Comment
    public Comment commentRequestToComment(CommentRequest request, User author, Publication publication) {
        if (request == null || author == null || publication == null) {
            return null;
        }

        return Comment.builder()
                .content(request.content())
                .createdAt(LocalDateTime.now())
                .author(author)
                .publication(publication)
                .build();
    }

    // Convertir Comment a CommentResponse
    public CommentResponse commentToCommentResponse(Comment comment) {
        if (comment == null) {
            return null;
        }

        return new CommentResponse(
                comment.getId(),
                comment.getContent(),
                comment.getCreatedAt(),
                comment.getAuthor().getId(),
                comment.getAuthor().getUsername(),
                comment.getPublication().getId()
        );
    }

    // Convertir List<Comment> a List<CommentResponse>
    public List<CommentResponse> commentsToCommentsResponse(List<Comment> comments) {
        if (comments == null) {
            return null;
        }

        return comments.stream()
                .map(this::commentToCommentResponse)
                .collect(Collectors.toList());
    }
}
