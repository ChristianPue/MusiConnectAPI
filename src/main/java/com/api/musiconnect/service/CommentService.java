package com.api.musiconnect.service;

import com.api.musiconnect.dto.request.CommentRequest;
import com.api.musiconnect.dto.response.CommentResponse;
import com.api.musiconnect.exception.ResourceNotFoundException;
import com.api.musiconnect.mapper.CommentMapper;
import com.api.musiconnect.model.entity.Comment;
import com.api.musiconnect.model.entity.Publication;
import com.api.musiconnect.model.entity.User;
import com.api.musiconnect.repository.CommentRepository;
import com.api.musiconnect.repository.PublicationRepository;
import com.api.musiconnect.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final PublicationRepository publicationRepository;
    private final CommentMapper commentMapper;

    // Crear un comentario
    @Transactional
    public CommentResponse createComment(CommentRequest request) {
        User author = userRepository.findById(request.authorId())
                .orElseThrow(() -> new ResourceNotFoundException("El autor con id " + request.authorId() + " no existe."));

        Publication publication = publicationRepository.findById(request.publicationId())
                .orElseThrow(() -> new ResourceNotFoundException("La publicación con id " + request.publicationId() + " no existe."));

        Comment comment = commentMapper.commentRequestToComment(request, author, publication);
        commentRepository.save(comment);

        return commentMapper.commentToCommentResponse(comment);
    }

    // Obtener un comentario por su ID
    @Transactional
    public CommentResponse getCommentById(Long commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new ResourceNotFoundException("El comentario con id " + commentId + " no existe."));

        return commentMapper.commentToCommentResponse(comment);
    }

    // Modificar un comentario
    @Transactional
    public CommentResponse updateComment(Long commentId, CommentRequest request) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new ResourceNotFoundException("El comentario con id " + commentId + " no existe."));

        // Actualizar el contenido del comentario
        comment.setContent(request.content());
        commentRepository.save(comment);

        return commentMapper.commentToCommentResponse(comment);
    }

    // Eliminar un comentario por su ID
    @Transactional
    public String deleteComment(Long commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new ResourceNotFoundException("El comentario con id " + commentId + " no existe."));

        commentRepository.delete(comment);
        return "El comentario ha sido eliminado exitosamente.";
    }

    // Obtener todos los comentarios de una publicación
    @Transactional
    public List<CommentResponse> getCommentsByPublication(Long publicationId) {
        Publication publication = publicationRepository.findById(publicationId)
                .orElseThrow(() -> new ResourceNotFoundException("La publicación con id " + publicationId + " no existe."));

        List<Comment> comments = commentRepository.findByPublication(publication);
        return commentMapper.commentsToCommentsResponse(comments);
    }
}
