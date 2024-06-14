//package com.eainfo.demandService.controller;
//
//import com.eainfo.demandService.dto.DemandRequest;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//
//public class StepPhoneController {
//@PostMapping("/phone")
//public ResponseEntity<?> phoneStep(@RequestBody DemandRequest clientRequest){
//    try {
//        String result = demandService.phoneInput(clientRequest);
//        return ResponseEntity.ok(result);
//    } catch (Exception e) {
//        return ResponseEntity.badRequest().body("Error in phone step: " + e.getMessage());
//    }
//}
//
//@PostMapping("/otp-phone")
//public ResponseEntity<OtpState> compareOtpPhone(@RequestBody DemandRequest clientRequest){
//    OtpState state = demandService.otpPhoneInput(clientRequest);
//    return ResponseEntity.ok(state);
//}
//}
