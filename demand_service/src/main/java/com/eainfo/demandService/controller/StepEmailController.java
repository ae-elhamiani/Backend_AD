package com.eainfo.demandService.controller;

import com.eainfo.demandService.dto.DemandRequest;
import com.eainfo.demandService.service.StepEmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class StepEmailController {
    private final StepEmailService stepEmailService;

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody DemandRequest clientRequest){
        return stepEmailService.signupEmail(clientRequest);
    }
    @PostMapping("/login")
    public ResponseEntity<?> generateOtp(@RequestBody DemandRequest clientRequest){
        return stepEmailService.loginEmail(clientRequest);
    }

    @PostMapping("/otp-email")
    public ResponseEntity<?> compareOtpEmail(@RequestBody DemandRequest clientRequest){
        return stepEmailService.otpEmailInput(clientRequest);
    }

    @PostMapping("/resend-otp")
    public ResponseEntity<?> resendOtp(@RequestBody DemandRequest clientRequest){
        return stepEmailService.resendOtp(clientRequest);
    }


}
