package com.api.musiconnect.mapper;

import com.api.musiconnect.dto.response.NotificationResponse;
import com.api.musiconnect.model.entity.Notification;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class NotificationMapper {

    // Convertir Notification a NotificationResponse
    public NotificationResponse notificationToNotificationResponse(Notification notification) {
        if (notification == null) {
            return null;
        }

        return new NotificationResponse(
                notification.getId(),
                notification.getTransmitter().getId(),
                notification.getReceiver().getId(),
                notification.getType(),
                notification.getContent(),
                notification.isRead(),
                notification.getCreatedAt(),
                notification.getUpdatedAt()
        );
    }

    // Convertir List<Notification> a List<NotificationResponse>
    public List<NotificationResponse> notificationsToNotificationResponses(List<Notification> notifications) {
        if (notifications == null) {
            return null;
        }

        return notifications.stream()
                .map(this::notificationToNotificationResponse)
                .collect(Collectors.toList());
    }
}
