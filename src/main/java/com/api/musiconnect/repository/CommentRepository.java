package com.api.musiconnect.repository;

import com.api.musiconnect.model.entity.Comment;
import com.api.musiconnect.model.entity.Publication;
import com.api.musiconnect.model.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    // Obtener todos los comentarios de una publicación
    @Query("SELECT c FROM Comment c WHERE c.publication = :publication")
    List<Comment> findByPublication(@Param("publication") Publication publication);

    // Obtener todos los comentarios de un usuario
    @Query("SELECT c FROM Comment c WHERE c.author = :author")
    List<Comment> findByAuthor(@Param("author") User author);

    // Contar comentarios por publicación
    @Query("SELECT COUNT(c) FROM Comment c WHERE c.publication = :publication")
    long countByPublication(@Param("publication") Publication publication);

    // Obtener comentarios por publicación ordenados por fecha descendente
    @Query("SELECT c FROM Comment c WHERE c.publication = :publication ORDER BY c.createdAt DESC")
    List<Comment> findByPublicationOrderByCreatedAtDesc(@Param("publication") Publication publication);

    // Obtener comentarios paginados por publicación, ordenados por fecha descendente
    @Query("SELECT c FROM Comment c WHERE c.publication = :publication ORDER BY c.createdAt DESC")
    Page<Comment> findByPublicationPaged(@Param("publication") Publication publication, Pageable pageable);
}
