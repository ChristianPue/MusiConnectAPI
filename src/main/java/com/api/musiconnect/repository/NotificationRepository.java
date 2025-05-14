package com.api.musiconnect.repository;

import com.api.musiconnect.model.entity.Notification;
import com.api.musiconnect.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {

    // Obtener todas las notificaciones de un receptor, ordenadas por fecha de creación descendente
    @Query("SELECT n FROM Notification n WHERE n.receiver = :receiver ORDER BY n.createdAt DESC")
    List<Notification> findByReceiverOrderByCreatedAtDesc(@Param("receiver") User receiver);

    // Contar notificaciones no leídas de un receptor
    @Query("SELECT COUNT(n) FROM Notification n WHERE n.receiver = :receiver AND n.isRead = false")
    long countUnreadByReceiver(@Param("receiver") User receiver);

    // Obtener notificaciones no leídas de un receptor
    @Query("SELECT n FROM Notification n WHERE n.receiver = :receiver AND n.isRead = false")
    List<Notification> findUnreadByReceiver(@Param("receiver") User receiver);
}
