package com.eainfo.demandService.service;

import com.eainfo.demandService.dto.DemandRequest;
import com.eainfo.demandService.enums.StepWeb;
import com.eainfo.demandService.model.Demand;
import com.eainfo.demandService.openfeign.customer.Customer;
import com.eainfo.demandService.openfeign.customer.CustomerService;
import com.eainfo.demandService.openfeign.notification.EmailSender;
import com.eainfo.demandService.openfeign.notification.NotificationMongodb;
import com.eainfo.demandService.openfeign.otp.OtpCompare;
import com.eainfo.demandService.openfeign.otp.OtpService;
import com.eainfo.demandService.repository.DemandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ExecutorService;
@Service
@RequiredArgsConstructor
public class StepEmailService {
    private final DemandRepository demandRepository;
    private final DemandService demandService;
    private final OtpService otpService;
    private final CustomerService customerService;
    private final NotificationMongodb notificationMongodb;
    private final ExecutorService taskExecutor;
    private final JwtTokenUtil jwtTokenUtil;



    public ResponseEntity<?> generateSendOtp(Demand demand){
        System.out.println("Generating OTP for email: " + demand.getClient().getEmail());

        OtpCompare otpCompare = OtpCompare.builder()
                .secretKey(demand.getClient().getSecretKey())
                .build();
        String generateOtp = otpService.generateOtp(otpCompare);
        System.out.println("Generated OTP: " + generateOtp);

        if (generateOtp != null) {
            EmailSender emailSender = EmailSender.builder()
                    .toEmail(demand.getClient().getEmail())
                    .templateCategory("otp-email")
                    .variables(Collections.singletonMap("codeOtpEmail", generateOtp))
                    .build();
            taskExecutor.execute(() -> {
                try {
                    notificationMongodb.sendEmail(emailSender);
                    System.out.println("OTP email sent to: " + demand.getClient().getEmail());
                } catch (Exception e) {
                    System.err.println("Error sending OTP email: " + e.getMessage());
                }
            });
            Map<String, String> response = Collections.singletonMap("uuid", demand.getId());
            return ResponseEntity.ok(response);
        } else {
            System.err.println("Max generated OTP error for email: " + demand.getClient().getEmail());
            return ResponseEntity.badRequest().body(Collections.singletonMap("error", "MAX_GENERATED_OTP_ERROR"));
        }
    }

    public ResponseEntity<?> signupEmail(DemandRequest demandRequest) {
        Demand demand = demandRepository.findByEmail(demandRequest.getEmail());
        if (demand == null || demand.getStepWeb() == StepWeb.EMAIL_STEP) {
            Customer customer = Customer.builder()
                    .email(demandRequest.getEmail())
                    .profileClientCode(demandRequest.getProfileClientCode())
                    .build();
            customer = customerService.createClient(customer);
            System.out.println(customer);
            demand = Demand.builder()
                    .id(UUID.randomUUID().toString())
                    .idClient(customer.getId())
                    .email(demandRequest.getEmail())
                    .client(customer)
                    .stepWeb(StepWeb.EMAIL_STEP)
                    .build();
            demandRepository.save(demand);
            return generateSendOtp(demand);
        } else {
            EmailSender emailSender = EmailSender.builder()
                    .toEmail(demandRequest.getEmail())
                    .templateCategory("email-exists")
                    .variables(Collections.emptyMap())
                    .build();
            taskExecutor.execute(() -> {
                try {
                    notificationMongodb.sendEmail(emailSender);
                } catch (Exception e) {
                    System.err.println("Error sending email exist: " + e.getMessage());
                }
            });
            return ResponseEntity.badRequest().body(Collections.singletonMap("error", "email already exist"));
        }
    }

    public ResponseEntity<?> loginEmail(DemandRequest demandRequest) {
        Demand demand = demandRepository.findByEmail(demandRequest.getEmail());

        if (demand.getStepWeb() != StepWeb.EMAIL_STEP) {
            return generateSendOtp(demand);
        } else {
            EmailSender emailSender = EmailSender.builder()
                    .toEmail(demand.getEmail())
                    .templateCategory("otp-email")
                    .variables(Collections.emptyMap())
                    .build();
            taskExecutor.execute(() -> {
                try {
                    notificationMongodb.sendEmail(emailSender);
                } catch (Exception e) {
                    System.err.println("Error sending OTP email: " + e.getMessage());
                }
            });
            return ResponseEntity.badRequest().body(Collections.singletonMap("error", "demand doesn't exist"));
        }
    }

    public ResponseEntity<?> otpEmailInput(DemandRequest demandRequest) {
        Demand demand = demandRepository.findById(demandRequest.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Demand not found with id: " + demandRequest.getId()));

        Customer customer = customerService.clientById(demand.getIdClient());
        OtpCompare otpCompare = OtpCompare.builder()
                .userInput(demandRequest.getUserInput())
                .secretKey(customer.getSecretKey())
                .build();

        try {
            if (Objects.equals(otpService.compareOtp(otpCompare), "VALID")) {
                if (demand.getStepWeb() == StepWeb.EMAIL_STEP) {
                    demand.advanceToNextStep();
                    demandRepository.save(demand);
                }
                String token = jwtTokenUtil.generateToken(demandRequest.getEmail());
                ResponseCookie cookie = ResponseCookie.from("token", token)
                        .httpOnly(true)
                        .secure(false)
                        .path("/")
                        .sameSite("")
                        .build();
                Map<String, String> response = new HashMap<>();
                response.put("message", "OTP verified successfully!");
                response.put("step web", demand.getStepWeb().toString());

                return ResponseEntity.ok()
                        .header(HttpHeaders.SET_COOKIE, cookie.toString())
                        .body(response);
            } else {
                return ResponseEntity.badRequest().body(Collections.singletonMap("error", "OTP verification failed"));
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Collections.singletonMap("error", "Error comparing OTP Email: " + e.getMessage()));
        }
    }

    public ResponseEntity<?> resendOtp(DemandRequest demandRequest){
        String email = demandRequest.getEmail();
        System.out.println("Resending OTP for email: " + email);

        Demand demand = demandService.getDemandByEmail(email);
        if (demand == null) {
            System.err.println("No demand found for email: " + email);
            return ResponseEntity.badRequest().body(Collections.singletonMap("error", "No demand found for email: " + email));
        }

        try {
            return generateSendOtp(demand);
        } catch (Exception e) {
            System.err.println("Error resending OTP: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.singletonMap("error", "Error resending OTP: " + e.getMessage()));
        }
    }


}
