package com.api.musiconnect.model.entity;

import com.api.musiconnect.model.enums.NotificationType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "notifications")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Usuario que genera la acción (quien sigue, comenta, etc.)
    @ManyToOne
    @JoinColumn(name = "transmitter_id", referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "FK_NotificationTransmitter"))
    private User transmitter;

    // Usuario que recibe la notificación
    @ManyToOne
    @JoinColumn(name = "receiver_id", referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "FK_NotificationReceiver"))
    private User receiver;

    // Tipo de notificación
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private NotificationType type;

    // Contenido textual de la notificación
    @Column(nullable = false)
    private String content;

    // Estado de lectura
    @Column(nullable = false)
    private boolean isRead = false;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
