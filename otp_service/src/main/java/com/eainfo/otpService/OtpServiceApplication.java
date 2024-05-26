package com.eainfo.otpService;

import com.eainfo.otpService.config.OtpProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableConfigurationProperties(OtpProperties.class)
@EnableFeignClients(basePackages = "com.eainfo.openfeignService")
public class OtpServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(OtpServiceApplication.class, args);
    }
}