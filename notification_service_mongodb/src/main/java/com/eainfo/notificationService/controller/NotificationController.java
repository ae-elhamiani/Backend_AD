package com.eainfo.notificationService.controller;

import com.eainfo.notificationService.model.Notification;
import com.eainfo.notificationService.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notifications")
@RequiredArgsConstructor
public class NotificationController {
    private final NotificationService notificationService;

    @GetMapping("/email/{email}")
    public ResponseEntity<List<Notification>> getNotificationsByEmail(@PathVariable String email) {
        List<Notification> notifications = notificationService.getNotificationsByEmail(email);
        return ResponseEntity.ok(notifications);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Notification> updateNotificationStatus(@PathVariable String id, @RequestBody Notification notification) {
        notificationService.updateNotificationStatus(id, notification);
        return ResponseEntity.ok(notification);
    }
}
