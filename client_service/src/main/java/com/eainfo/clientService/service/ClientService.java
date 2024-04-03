package com.eainfo.clientService.service;

import com.eainfo.clientService.dto.ClientRequest;
import com.eainfo.clientService.model.Client;
import com.eainfo.clientService.model.ClientProfile;
import com.eainfo.clientService.model.Compte;
import com.eainfo.clientService.model.Pack;
import com.eainfo.clientService.repository.ClientProfileRepository;
import com.eainfo.clientService.repository.ClientRepository;
import com.eainfo.clientService.repository.CompteRepository;
import com.eainfo.clientService.repository.PackRepository;
import com.eainfo.openfeignService.notification.EmailSender;
import com.eainfo.openfeignService.notification.Notification;
import com.eainfo.openfeignService.otp.OtpCompare;
import com.eainfo.openfeignService.otp.OtpService;
import com.eainfo.openfeignService.otp.outiles.enums.OtpState;
import com.bastiaanjansen.otp.SecretGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutorService;
import java.util.Collections;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ClientService {
    private final ClientRepository clientRepository;
    private final PackRepository packRepository;
    private final ClientProfileRepository clientProfileRepository;
    private final OtpService otpService;
    private final Notification notification;
    private final CompteRepository compteRepository;
    private final ExecutorService taskExecutor;




    public ResponseEntity<?> emailInput(ClientRequest clientRequest) {
        Client client = clientRepository.findByEmail(clientRequest.getEmail());
        ClientProfile clientProfile = clientProfileRepository.findByCode(clientRequest.getClientProfileCode());
        Pack pack = packRepository.findByCode(clientRequest.getPackCode());

        if (client == null) {
            client = new Client(clientRequest.getEmail(), UUID.randomUUID().toString(), SecretGenerator.generate(), clientProfile);
            Compte compte = new Compte(pack, client);
            clientRepository.save(client);
            compteRepository.save(compte);

            OtpCompare otpCompare = OtpCompare.builder()
                    .secretKey(client.getSecretKey())
                    .build();
            String generateOtp = otpService.generateOtp(otpCompare);

            if (generateOtp!=null) {
                EmailSender emailSender = EmailSender.builder()
                        .email(client.getEmail())
                        .codeOtpEmail(generateOtp)
                        .build();
                taskExecutor.execute(() -> {
                    try {
                        notification.sendOtpEmail(emailSender);
                    } catch (Exception e) {
                        System.err.println("Error sending OTP email: " + e.getMessage());
                    }
                });
                Map<String, String> response = Collections.singletonMap("uuid", client.getUuid());
                return ResponseEntity.ok(response);
            } else {
                return ResponseEntity.badRequest().body(Collections.singletonMap("error", "MAX_GENERATED_OTP_ERROR"));
            }

        } else if(!client.getEmailStatus()){
            OtpCompare otpCompare = OtpCompare.builder()
                    .secretKey(client.getSecretKey())
                    .build();
            String generateOtp = otpService.generateOtp(otpCompare);

            if (generateOtp!=null) {
                EmailSender emailSender = EmailSender.builder()
                        .email(client.getEmail())
                        .codeOtpEmail(generateOtp)
                        .build();
                taskExecutor.execute(() -> {
                    try {
                        notification.sendOtpEmail(emailSender);
                    } catch (Exception e) {
                        System.err.println("Error sending OTP email: " + e.getMessage());
                    }
                });
                Map<String, String> response = Collections.singletonMap("uuid", client.getUuid());
                return ResponseEntity.ok(response);
            } else {
                return ResponseEntity.badRequest().body(Collections.singletonMap("error", "MAX_GENERATED_OTP_ERROR"));
            }

        } else {
            return ResponseEntity.badRequest().body(Collections.singletonMap("error", "client already exist"));
        }

    }

    public OtpState otpEmailInput(ClientRequest clientRequest) {
        Client client = clientRepository.findByUuid(clientRequest.getUuidClient());
        OtpCompare otpCompare = OtpCompare.builder()
                .userInput(clientRequest.getUserInput())
                .secretKey(client.getSecretKey())
                .build();
        if (otpService.compareOtp(otpCompare)==OtpState.VALID){
            client.setEmailStatus(true);
            clientRepository.save(client);
        }
        return otpService.compareOtp(otpCompare);
    }
    public String phoneInput(ClientRequest clientRequest) {
        Client client = clientRepository.findByUuid(clientRequest.getUuidClient());
            OtpCompare otpCompare = OtpCompare.builder()
                    .secretKey(client.getSecretKey())
                    .build();
            String generateOtp = otpService.generateOtp(otpCompare);

            if (generateOtp!=null) {
                EmailSender emailSender = EmailSender.builder()
                        .email(client.getEmail())
                        .codeOtpEmail(generateOtp)
                        .build();
                notification.sendOtpEmail(emailSender);
                return client.getUuid();
            } else {
                return "MAX_GENERATED_OTP_ERROR";
            }
    }

    public OtpState otpPhoneInput(ClientRequest clientRequest) {
        Client client = clientRepository.findByUuid(clientRequest.getUuidClient());
        OtpCompare otpCompare = OtpCompare.builder()
                .userInput(clientRequest.getUserInput())
                .secretKey(client.getSecretKey())
                .build();
        if (otpService.compareOtp(otpCompare)==OtpState.VALID){
            client.setPhoneStatus(true);
            clientRepository.save(client);
        }
        return otpService.compareOtp(otpCompare);

    }
}