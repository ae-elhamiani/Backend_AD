package com.eainfo.notificationService.controller;

import com.eainfo.notificationService.dto.EmailRequest;
import com.eainfo.notificationService.dto.BulkEmailRequest;
import com.eainfo.notificationService.service.EmailSenderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notification-service-mongodb")
@RequiredArgsConstructor
public class EmailSenderController {

    private final EmailSenderService emailSenderService;

    @PostMapping("/send-email")
    public void sendEmail(@RequestBody EmailRequest request) {
        emailSenderService.sendEmail(request.getToEmail(), request.getTemplateCategory(), request.getVariables());
    }

    @PostMapping("/send-bulk-email")
    public void sendBulkEmail(@RequestBody BulkEmailRequest request) {
        emailSenderService.sendBulkEmail(request.getToEmails(), request.getTemplateCategory(), request.getVariables());
    }
}
