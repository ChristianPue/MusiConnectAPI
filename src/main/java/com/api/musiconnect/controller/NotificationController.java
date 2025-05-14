package com.api.musiconnect.controller;

import com.api.musiconnect.dto.request.NotificationRequest;
import com.api.musiconnect.dto.response.NotificationResponse;
import com.api.musiconnect.service.NotificationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notification")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;

    // Crear una notificación
    @PostMapping
    public ResponseEntity<NotificationResponse> createNotification(@Valid @RequestBody NotificationRequest request) {
        NotificationResponse response = notificationService.createNotification(
                request.transmitterId(),
                request.receiverId(),
                request.type(),
                request.content()
        );
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // Obtener todas las notificaciones de un usuario
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<NotificationResponse>> getUserNotifications(@PathVariable Long userId) {
        List<NotificationResponse> responses = notificationService.getUserNotifications(userId);
        return new ResponseEntity<>(responses, HttpStatus.OK);
    }

    // Marcar una notificación como leída
    @PatchMapping("/read/{notificationId}")
    public ResponseEntity<NotificationResponse> markNotificationAsRead(@PathVariable Long notificationId) {
        NotificationResponse response = notificationService.markAsRead(notificationId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // Eliminar una notificación
    @DeleteMapping("/{notificationId}")
    public ResponseEntity<String> deleteNotification(@PathVariable Long notificationId) {
        notificationService.deleteNotification(notificationId);
        return new ResponseEntity<>("Notificación eliminada correctamente.", HttpStatus.OK);
    }
}
