package com.api.musiconnect.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "projects")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name; // Nombre del proyecto musical

    @Column(nullable = true)
    private String description; // Descripción del proyecto

    @ManyToOne
    @JoinColumn(name = "creator_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_UserCreator"))
    private User creator; // El usuario o grupo que crea el proyecto

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt; // Fecha de creación

    @Column(nullable = true)
    private LocalDateTime startDate; // Fecha de inicio del proyecto

    @Column(nullable = true)
    private String status; // Estado del proyecto (En curso, Finalizado, etc.)

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
}
