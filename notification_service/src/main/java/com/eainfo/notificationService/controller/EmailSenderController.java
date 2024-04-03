package com.eainfo.notificationService.controller;

import com.eainfo.notificationService.service.EmailSenderService;
import com.eainfo.openfeignService.notification.EmailSender;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class EmailSenderController {

    private final EmailSenderService emailSenderService;

   @PostMapping("/send-otp-email")
   public void sendOtpEmail(@RequestBody EmailSender request) {

       String subject = "Agence Directe – Ouverture de compte en ligne";
       String templatePath = "otpService/email/send-otp-email-template2.html";

       Map<String, Object> variables = new HashMap<>();
       variables.put("codeOtpEmail", request.getCodeOtpEmail());
       variables.put("logo", subject);

        emailSenderService.sendOtpEmail(request.getEmail(), subject, variables, templatePath);
   }
}
