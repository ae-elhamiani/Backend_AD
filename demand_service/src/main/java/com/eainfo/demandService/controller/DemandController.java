package com.eainfo.demandService.controller;


import com.eainfo.demandService.dto.DemandRequest;
import com.eainfo.demandService.model.Demand;
import com.eainfo.demandService.service.DemandService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
public class DemandController {

    private final DemandService demandService;

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody DemandRequest clientRequest){
        return demandService.signupEmail(clientRequest);
    }

    @GetMapping("/Demands")
    public List<Demand> getAllDemand() {
        return demandService.AllDemands();
    }
    @GetMapping("/Demands/{accountId}")
    public ResponseEntity<Demand> getDemandByID(@PathVariable String accountId) {
        Demand demand = demandService.getDemandByID(accountId);
        return ResponseEntity.ok(demand);
    }
    @PostMapping("/login")
    public ResponseEntity<?> generateOtp(@RequestBody DemandRequest clientRequest){
        return demandService.loginEmail(clientRequest);
    }

    @PostMapping("/otp-email")
    public ResponseEntity<?> compareOtpEmail(@RequestBody DemandRequest clientRequest){
        return demandService.otpEmailInput(clientRequest);
    }
//
//    @PostMapping("/phone")
//    public ResponseEntity<?> phoneStep(@RequestBody DemandRequest clientRequest){
//        try {
//            String result = demandService.phoneInput(clientRequest);
//            return ResponseEntity.ok(result);
//        } catch (Exception e) {
//            return ResponseEntity.badRequest().body("Error in phone step: " + e.getMessage());
//        }
//    }
//
//    @PostMapping("/otp-phone")
//    public ResponseEntity<OtpState> compareOtpPhone(@RequestBody DemandRequest clientRequest){
//        OtpState state = demandService.otpPhoneInput(clientRequest);
//        return ResponseEntity.ok(state);
//    }

}

