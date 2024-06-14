package com.eainfo.notificationService.repository;

import com.eainfo.notificationService.model.Notification;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface NotificationRepository extends MongoRepository<Notification, String> {
    List<Notification> findByEmail(String email);
}
