package com.eainfo.otpService.service;

import com.eainfo.otpService.config.OtpProperties;
import com.eainfo.otpService.model.OtpGenerated;
import com.eainfo.openfeignService.otp.enums.OtpState;
import com.eainfo.otpService.repository.OtpGeneratedRepository;
import com.bastiaanjansen.otp.HMACAlgorithm;
import com.bastiaanjansen.otp.HOTPGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class GenerateOtp {
    private final OtpGeneratedRepository otpGeneratedRepository;
    private final OtpProperties otpProperties;


    public String generateOtp(byte[] secretKey) {
        OtpGenerated otpGenerated = otpGeneratedRepository.findTopBySecretKeyOrderByIdDesc(secretKey);
        boolean shouldGenerateNewOtp = otpGenerated == null || pastTime(otpGenerated.getDate_generation()) >  otpProperties.getRetryWaitTimeInMinutes();

        if (otpGenerated != null && otpGenerated.getNb_generation() >= otpGenerated.getNb_generation() && !shouldGenerateNewOtp) {
            return "null";
        }
        String otp;
        if (shouldGenerateNewOtp) {
            otp = generate(secretKey, 0);
            otpGenerated = new OtpGenerated(secretKey,0, OtpState.PENDING, 1, new Date());
        } else{
            otpGenerated.incrementCounter();
            otp = generate(secretKey, otpGenerated.getCounter());
            otpGenerated.incrementNb_generation();
            otpGenerated = new OtpGenerated(secretKey,0, OtpState.PENDING, otpGenerated.getNb_generation(), new Date());
        }
        System.out.println(otp);
        otpGeneratedRepository.save(otpGenerated);
        return otp;
    }



    public String generate(byte[] secretKey ,Integer counter) {
        HOTPGenerator hotp = new HOTPGenerator.Builder(secretKey)
                .withPasswordLength(otpProperties.getOtpLengthChar())
                .withAlgorithm(HMACAlgorithm.SHA256)
                .build();
        return hotp.generate(counter);

    }

    private long pastTime(Date date) {
        long diffInMilliseconds = new Date().getTime() - date.getTime();
        return TimeUnit.MILLISECONDS.toMinutes(diffInMilliseconds);
    }
}
