package com.api.musiconnect.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "follows", uniqueConstraints = @UniqueConstraint(columnNames = {"follower_id", "followed_id"}))
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Follow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // ID del usuario que sigue
    @ManyToOne()
    @JoinColumn(name = "follower_id", referencedColumnName = "id"
            ,foreignKey = @ForeignKey(name = "FK_UserFollowerId"))
    private User follower;

    // ID del usuario seguido
    @ManyToOne()
    @JoinColumn(name = "followed_id", referencedColumnName = "id"
            ,foreignKey = @ForeignKey(name = "FK_UserFollowedId"))
    private User followed;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt; // Fecha de creación

    private LocalDateTime updatedAt; // Fecha de última actualización

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
