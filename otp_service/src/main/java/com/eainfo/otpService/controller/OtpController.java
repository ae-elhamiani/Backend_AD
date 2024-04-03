package com.eainfo.otpService.controller;

import com.eainfo.openfeignService.otp.OtpCompare;
import com.eainfo.openfeignService.otp.outiles.enums.OtpState;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.eainfo.otpService.service.GenerateOtp;
import com.eainfo.otpService.service.CompareOtp;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
public class OtpController {

    private final GenerateOtp generateOtp;
    private final CompareOtp compareOtp;
    @PostMapping("/generate")
    public String generateOtp(@RequestBody OtpCompare otpRequest ) {
        return generateOtp.generateOtp(otpRequest.getSecretKey());
    }

    @PostMapping("/compare")
    public OtpState compareOtp(@RequestBody OtpCompare otpRequest) {
        return compareOtp.compareOtp(otpRequest.getSecretKey(), otpRequest.getUserInput());

    }
}
