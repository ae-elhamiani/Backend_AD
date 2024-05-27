package com.eainfo.otpService.service;

import com.eainfo.otpService.config.OtpProperties;
import com.eainfo.otpService.enums.OtpState;
import com.eainfo.otpService.model.OtpGenerated;
import com.eainfo.otpService.repository.OtpGeneratedRepository;
import com.bastiaanjansen.otp.HMACAlgorithm;
import com.bastiaanjansen.otp.HOTPGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class CompareOtp {
    private final OtpGeneratedRepository otpGeneratedRepository;
    private final OtpProperties otpProperties;
    public String compareOtp(byte[] secretKey , String inputUser) {
        OtpGenerated otpGenerated = otpGeneratedRepository.findTopBySecretKeyOrderByIdDesc(secretKey);
        System.out.println("5 otp**********************dRequest");
        if (pastTime(otpGenerated.getDate_generation()) < otpProperties.getExpiryOtpTimeInMinutes()) {
            System.out.println("6 otp**********************dRequest");
            System.out.println(otpGenerated);
            System.out.println(pastTime(otpGenerated.getDate_generation()));
            if (otpGenerated.getNb_attempt() < otpProperties.getMaxGenerationAttempts()) {
                Boolean isOtpValid = compareOtp(secretKey, inputUser, otpGenerated.getCounter());
                otpGenerated.incrementNb_attempt();
                otpGeneratedRepository.save(otpGenerated);
                System.out.println("7 otp**********************dRequest");
                System.out.println(isOtpValid);


                if (isOtpValid) {
                    System.out.println("8 otp**********************dRequest");
                    System.out.println(OtpState.VALID);

                    return OtpState.VALID.toString();
                }else {
                    return OtpState.INVALID.toString();
                }
            } else{
                return OtpState.EXPIRED_ATTEMPT.toString();
            }
        } else {
            return OtpState.TIMEOUT.toString();
        }
    }

    public Boolean compareOtp( byte[] secretKey, String userInput , Integer counter) {
        HOTPGenerator hotp = new HOTPGenerator.Builder(secretKey)
                .withPasswordLength(otpProperties.getOtpLengthChar())
                .withAlgorithm(HMACAlgorithm.SHA256)
                .build();
        return hotp.verify(userInput, counter);
    }

    private long pastTime(Date date) {
        long diffInMilliseconds = new Date().getTime() - date.getTime();
        return TimeUnit.MILLISECONDS.toMinutes(diffInMilliseconds);
    }
}
