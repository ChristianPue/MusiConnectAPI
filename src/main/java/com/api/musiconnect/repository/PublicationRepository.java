package com.api.musiconnect.repository;

import com.api.musiconnect.model.entity.Publication;
import com.api.musiconnect.model.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public  interface PublicationRepository extends JpaRepository<Publication, Long> {

    // Obtener todas las publicaciones de un usuario
    @Query("SELECT p FROM Publication p WHERE p.author = :author")
    List<Publication> findByAuthor(@Param("author") User author);

    // Contar cuántas publicaciones tiene un usuario
    @Query("SELECT COUNT(p) FROM Publication p WHERE p.author = :author")
    long countByAuthor(@Param("author") User author);

    // Obtener publicaciones por usuario, ordenadas por fecha descendente
    @Query("SELECT p FROM Publication p WHERE p.author = :author ORDER BY p.createdAt DESC")
    List<Publication> findByAuthorOrderByCreatedAtDesc(@Param("author") User author);

    // Obtener publicaciones paginadas por usuario, ordenadas por fecha descendente
    @Query("SELECT p FROM Publication p WHERE p.author = :author ORDER BY p.createdAt DESC")
    Page<Publication> findByAuthorPaged(@Param("author") User author, Pageable pageable);
}
