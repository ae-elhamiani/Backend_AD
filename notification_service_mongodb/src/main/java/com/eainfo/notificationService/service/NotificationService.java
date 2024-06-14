package com.eainfo.notificationService.service;

import com.eainfo.notificationService.model.Notification;
import com.eainfo.notificationService.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationRepository notificationRepository;

    public List<Notification> getNotificationsByEmail(String email) {
        return notificationRepository.findByEmail(email);
    }

    public void deleteNotification(String id) {
        notificationRepository.deleteById(id);
    }

    public void updateNotificationStatus(String id, Notification notification) {
        Optional<Notification> existingNotification = notificationRepository.findById(id);
        if (existingNotification.isPresent()) {
            Notification notif = existingNotification.get();
            notif.setSent(notification.isSent());
            notificationRepository.save(notif);
        }
    }
}
