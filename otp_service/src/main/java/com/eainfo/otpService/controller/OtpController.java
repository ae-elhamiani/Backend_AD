package com.eainfo.otpService.controller;

import com.eainfo.otpService.dto.OtpRequest;

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

//    @GetMapping("/otps")
//    public String generateOtp(@RequestBody OtpRequest otpRequest ) {
//        return generateOtp.generateOtp(otpRequest.getSecretKey().getBytes());
//    }
    @PostMapping("/generate")
    public String generateOtp(@RequestBody OtpRequest otpRequest ) {
        return generateOtp.generateOtp(otpRequest.getSecretKey());
    }

    @PostMapping("/compare")
    public String compareOtp(@RequestBody OtpRequest otpRequest) {
        return compareOtp.compareOtp(otpRequest.getSecretKey(), otpRequest.getUserInput());

    }
}
