package com.eainfo.otpService.service;

import com.eainfo.otpService.model.OtpGenerated;
import com.eainfo.openfeignService.otp.outiles.enums.OtpState;
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
    public OtpState compareOtp(byte[] secretKey , String inputUser) {
        OtpGenerated otpGenerated = otpGeneratedRepository.findTopBySecretKeyOrderByDateGenerationDesc(secretKey);
        if (isPast30Minutes(otpGenerated.getDateGeneration()) < 15) {
            if (otpGenerated.getNb_attempt() < 3) {
                Boolean isOtpValid = compareOtp(secretKey, inputUser, otpGenerated.getCounter());
                otpGenerated.incrementNb_attempt();
                otpGeneratedRepository.save(otpGenerated);

                if (isOtpValid) {
                    return OtpState.VALID;
                }else {
                    return OtpState.INVALID;
                }
            } else{
                return OtpState.EXPIRED_ATTEMPT;
            }
        } else {
            return OtpState.TIMEOUT;
        }
    }

    public Boolean compareOtp( byte[] secretKey, String userInput , Integer counter) {
        HOTPGenerator hotp = new HOTPGenerator.Builder(secretKey)
                .withPasswordLength(8)
                .withAlgorithm(HMACAlgorithm.SHA256)
                .build();
        return hotp.verify(userInput, counter);
    }

    private long isPast30Minutes(Date date) {
        long diffInMilliseconds = new Date().getTime() - date.getTime();
        return TimeUnit.MILLISECONDS.toMinutes(diffInMilliseconds);
    }

}
