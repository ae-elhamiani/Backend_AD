package com.eainfo.demandService.openfeign.notification;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "notification-service-mongodb", path = "/notification-service-mongodb")
public interface NotificationMongodb {

    @PostMapping("send-email")
    void sendEmail(@RequestBody EmailSender request);

}
