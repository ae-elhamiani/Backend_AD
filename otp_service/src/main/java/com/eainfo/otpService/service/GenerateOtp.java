package com.eainfo.otpService.service;

import com.eainfo.otpService.model.Counter;
import com.eainfo.otpService.model.OtpGenerated;
import com.eainfo.openfeignService.otp.outiles.enums.OtpState;
import com.eainfo.otpService.repository.CounterRepository;
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
    private final CounterRepository counterRepository;
    private final OtpGeneratedRepository otpGeneratedRepository;
    private final Counter counter;

    public String generateOtp(byte[] secretKey) {
        String otp = null;
        OtpGenerated otpGenerated = otpGeneratedRepository.findTopBySecretKeyOrderByDateGenerationDesc(secretKey);

        if (otpGenerated == null) {
            otp = generate(secretKey, counter.getCounter());
            otpGenerated = new OtpGenerated(secretKey,0, OtpState.PENDING, 1, new Date(), counter.getCounter());
        } else{
            if (otpGenerated.getNb_generation()<5){
                otp = generate(secretKey, counter.getCounter());
                otpGenerated.incrementNb_generation();
                otpGenerated = new OtpGenerated(secretKey,0, OtpState.PENDING, otpGenerated.getNb_generation(), new Date(), counter.getCounter());
            }
            else if (isPast30Minutes(otpGenerated.getDateGeneration()) > 1){
                otp = generate(secretKey, counter.getCounter());
                otpGenerated = new OtpGenerated(secretKey,0, OtpState.PENDING, 1, new Date(), counter.getCounter());
            }

        }
        otpGeneratedRepository.save(otpGenerated);
        counter.incrementCounter();
        counterRepository.save(counter);
        return otp;
    }



    public String generate(byte[] secretKey ,Integer counter) {

        HOTPGenerator hotp = new HOTPGenerator.Builder(secretKey)
                .withPasswordLength(8)
                .withAlgorithm(HMACAlgorithm.SHA256)
                .build();

        return hotp.generate(counter);

    }

    private long isPast30Minutes(Date date) {
        long diffInMilliseconds = new Date().getTime() - date.getTime();
        return TimeUnit.MILLISECONDS.toMinutes(diffInMilliseconds);
    }
}
