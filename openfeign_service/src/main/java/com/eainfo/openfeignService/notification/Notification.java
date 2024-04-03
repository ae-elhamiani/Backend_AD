package com.eainfo.openfeignService.notification;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "notification-service", path = "/notification-service")
public interface Notification{
//
@PostMapping("send-otp-email")
void sendOtpEmail(@RequestBody EmailSender request);


    @PostMapping("send-otp-sms")
    void sendSms(@RequestBody SmsSender request);
}
