package com.eainfo.otpService.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import lombok.Data;

@Data
@Configuration
@ConfigurationProperties(prefix = "otp.params")
public class OtpProperties {
    private int retryWaitTimeInMinutes;
    private int maxGenerationAttempts;
    private int otpLengthChar;
    private String generationAlgorithm;
    private int expiryOtpTimeInMinutes;
    private int maxAttemptsBeforeExpiry;

}
