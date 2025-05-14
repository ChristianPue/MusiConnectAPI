package com.api.musiconnect.controller;

import com.api.musiconnect.dto.request.CommentRequest;
import com.api.musiconnect.dto.response.CommentResponse;
import com.api.musiconnect.service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    // Crear un nuevo comentario
    @PostMapping
    public ResponseEntity<CommentResponse> createComment(@Valid @RequestBody CommentRequest request) {
        CommentResponse response = commentService.createComment(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // Obtener un comentario por ID
    @GetMapping("/{commentId}")
    public ResponseEntity<CommentResponse> getCommentById(@PathVariable Long commentId) {
        CommentResponse comment = commentService.getCommentById(commentId);
        return new ResponseEntity<>(comment, HttpStatus.OK);
    }

    // Actualizar un comentario
    @PutMapping("/{commentId}")
    public ResponseEntity<CommentResponse> updateComment(@PathVariable Long commentId, @Valid @RequestBody CommentRequest request) {
        CommentResponse updatedComment = commentService.updateComment(commentId, request);
        return new ResponseEntity<>(updatedComment, HttpStatus.ACCEPTED);
    }

    // Eliminar un comentario
    @DeleteMapping("/{commentId}")
    public ResponseEntity<String> deleteComment(@PathVariable Long commentId) {
        String response = commentService.deleteComment(commentId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // Obtener todos los comentarios de una publicación
    @GetMapping("/publication/{publicationId}")
    public ResponseEntity<List<CommentResponse>> getCommentsByPublication(@PathVariable Long publicationId) {
        List<CommentResponse> comments = commentService.getCommentsByPublication(publicationId);
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }
}
