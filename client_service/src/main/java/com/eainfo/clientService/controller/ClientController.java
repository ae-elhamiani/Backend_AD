package com.eainfo.clientService.controller;

import com.eainfo.clientService.dto.ClientRequest;
import com.eainfo.clientService.model.ClientProfile;
import com.eainfo.clientService.model.Pack;
import com.eainfo.clientService.repository.ClientProfileRepository;
import com.eainfo.clientService.repository.PackRepository;
import com.eainfo.clientService.service.ClientService;
import com.eainfo.clientService.service.JwtTokenUtil;
import com.eainfo.openfeignService.otp.outiles.enums.OtpState;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("/banking")
public class ClientController {

    private final ClientService clientService;
    private final PackRepository packRepository;
    private final ClientProfileRepository clientProfileRepository;
    private final JwtTokenUtil jwtTokenUtil;

    @GetMapping("/")
    public ResponseEntity<List<ClientProfile>> getAllClientProfiles() {
        List<ClientProfile> profiles = clientProfileRepository.findAll();
        return ResponseEntity.ok(profiles);
    }

    @GetMapping("/pack/{clientProfileCode}")
    public ResponseEntity<List<Pack>> getPacksByClientProfile(@PathVariable String clientProfileCode) {
        List<Pack> packs = packRepository.findByClientProfileCode(clientProfileCode);
        return ResponseEntity.ok(packs);
    }

    @PostMapping("/email")
    public ResponseEntity<?> generateOtp(@RequestBody ClientRequest clientRequest){
          return clientService.emailInput(clientRequest);
    }

    @PostMapping("/otp-email")
    public ResponseEntity<?> compareOtpEmail(@RequestBody ClientRequest clientRequest){
        try {
            OtpState result = clientService.otpEmailInput(clientRequest);
            if(result.equals(OtpState.VALID)) {
                String token = jwtTokenUtil.generateToken(clientRequest.getEmail());

                ResponseCookie cookie = ResponseCookie.from("token", token)
                        .httpOnly(true)
//                        .secure(true)
                        .path("/")
                        .build();

                return ResponseEntity.ok()
                        .header(HttpHeaders.SET_COOKIE, cookie.toString())
                        .body(Collections.singletonMap("message", "OTP verified successfully!"));
            } else {
                return ResponseEntity.badRequest().body(Collections.singletonMap("error", "OTP verification failed"));
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Collections.singletonMap("error", "Error comparing OTP Email: " + e.getMessage()));
        }
    }

    @PostMapping("/phone")
    public ResponseEntity<?> phoneStep(@RequestBody ClientRequest clientRequest){
        try {
            String result = clientService.phoneInput(clientRequest);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error in phone step: " + e.getMessage());
        }
    }

    @PostMapping("/otp-phone")
    public ResponseEntity<OtpState> compareOtpPhone(@RequestBody ClientRequest clientRequest){
        OtpState state = clientService.otpPhoneInput(clientRequest);
        return ResponseEntity.ok(state);
    }
}
