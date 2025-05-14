package com.api.musiconnect.service;

import com.api.musiconnect.dto.response.NotificationResponse;
import com.api.musiconnect.mapper.NotificationMapper;
import com.api.musiconnect.model.entity.Notification;
import com.api.musiconnect.model.entity.User;
import com.api.musiconnect.model.enums.NotificationType;
import com.api.musiconnect.repository.NotificationRepository;
import com.api.musiconnect.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationRepository notificationRepository;
    private final UserRepository userRepository;
    private final NotificationMapper notificationMapper;

    // Crear una notificación
    public NotificationResponse createNotification(Long transmitterId, Long receiverId, NotificationType type, String content) {
        User transmitter = userRepository.findById(transmitterId)
                .orElseThrow(() -> new EntityNotFoundException("Transmitter user not found with ID: " + transmitterId));

        User receiver = userRepository.findById(receiverId)
                .orElseThrow(() -> new EntityNotFoundException("Receiver user not found with ID: " + receiverId));

        Notification notification = Notification.builder()
                .transmitter(transmitter)
                .receiver(receiver)
                .type(type)
                .content(content)
                .isRead(false)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        notificationRepository.save(notification);
        return notificationMapper.notificationToNotificationResponse(notification);
    }

    // Obtener todas las notificaciones de un usuario
    public List<NotificationResponse> getUserNotifications(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + userId));

        List<Notification> notifications = notificationRepository.findByReceiverOrderByCreatedAtDesc(user);
        return notificationMapper.notificationsToNotificationResponses(notifications);
    }

    // Marcar una notificación como leída
    public NotificationResponse markAsRead(Long notificationId) {
        Notification notification = notificationRepository.findById(notificationId)
                .orElseThrow(() -> new EntityNotFoundException("Notification not found with ID: " + notificationId));

        notification.setRead(true);
        notification.setUpdatedAt(LocalDateTime.now());
        notificationRepository.save(notification);

        return notificationMapper.notificationToNotificationResponse(notification);
    }

    // Eliminar una notificación
    public void deleteNotification(Long notificationId) {
        if (!notificationRepository.existsById(notificationId)) {
            throw new EntityNotFoundException("Notification not found with ID: " + notificationId);
        }

        notificationRepository.deleteById(notificationId);
    }
}
