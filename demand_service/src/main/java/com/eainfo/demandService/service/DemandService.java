package com.eainfo.demandService.service;

import com.eainfo.demandService.dto.DemandRequest;
import com.eainfo.demandService.enums.StepWeb;
import com.eainfo.demandService.model.Demand;
import com.eainfo.demandService.repository.DemandRepository;
import com.eainfo.openfeignService.customer.CustomerService;
import com.eainfo.openfeignService.customer.Customer;
import com.eainfo.openfeignService.notification.EmailSender;
import com.eainfo.openfeignService.notification.Notification;
import com.eainfo.openfeignService.otp.OtpCompare;
import com.eainfo.openfeignService.otp.OtpService;
import com.eainfo.openfeignService.otp.enums.OtpState;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ExecutorService;

@Service
@RequiredArgsConstructor
public class DemandService {
    private final DemandRepository demandRepository;
    private final OtpService otpService;
    private final CustomerService customerService;
    private final Notification notification;
    private final ExecutorService taskExecutor;
    private final JwtTokenUtil jwtTokenUtil;

    public Demand getDemandByID(String id){
        Demand demand = demandRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Demand not found with id: " + id));
        Customer customer = customerService.clientById(demand.getIdClient());
        demand.setClient(customer);
        return demand;

    }

    public List<Demand> AllDemands(){
        List<Demand> demands = demandRepository.findAll();
        demands.forEach(acc->{
            acc.setClient(customerService.clientById(acc.getIdClient()));
        });
        return demands;

    }


    public ResponseEntity<?> generateSendOtp(Demand demand){
        OtpCompare otpCompare = OtpCompare.builder()
                .secretKey(demand.getClient().getSecretKey())
                .build();
        String generateOtp = otpService.generateOtp(otpCompare);
        System.out.println(generateOtp);

        if (generateOtp!=null) {
            EmailSender emailSender = EmailSender.builder()
                    .email(demand.getClient().getEmail())
                    .codeOtpEmail(generateOtp)
                    .build();
            taskExecutor.execute(() -> {
                try {
                    notification.sendOtpEmail(emailSender);
                } catch (Exception e) {
                    System.err.println("Error sending OTP email: " + e.getMessage());
                }
            });
            Map<String, String> response = Collections.singletonMap("uuid : ", demand.getId());
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.badRequest().body(Collections.singletonMap("error", "MAX_GENERATED_OTP_ERROR"));
        }
    }

        public ResponseEntity<?> signupEmail(DemandRequest demandRequest) {
            Demand demand = demandRepository.findByEmail(demandRequest.getEmail());
            if (demand == null || demand.getStepWeb()== StepWeb.EMAIL_STEP) {
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
            }
            else {
                EmailSender emailSender = EmailSender.builder()
                        .email(demandRequest.getEmail())
                        .build();
                taskExecutor.execute(() -> {
                    try {
                        notification.sendOtpEmail(emailSender);
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
                    .email(demand.getEmail())
                    .build();
            taskExecutor.execute(() -> {
                try {
                    notification.sendOtpEmail(emailSender);
                } catch (Exception e) {
                    System.err.println("Error sending OTP email: " + e.getMessage());
                }
            });
            return ResponseEntity.badRequest().body(Collections.singletonMap("error", "demand doesn't exist"));
        }

    }


    public ResponseEntity<?> otpEmailInput(DemandRequest demandRequest) {
        System.out.println(demandRequest);
        System.out.println("1 deman**********************dRequest");
        System.out.println(demandRequest.getId());
        Demand demand = demandRepository.findById(demandRequest.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Demand not found with id: " + demandRequest.getId()));
        System.out.println("2 deman**********************dRequest");
        System.out.println(demand);
        System.out.println("3 deman**********************dRequest");
        System.out.println(demand.getIdClient());

        Customer customer = customerService.clientById(demand.getIdClient());
        System.out.println("4 deman**********************dRequest");
        System.out.println(customer);
        OtpCompare otpCompare = OtpCompare.builder()
                .userInput(demandRequest.getUserInput())
                .secretKey(customer.getSecretKey())
                .build();
        try{
            if (otpService.compareOtp(otpCompare) == OtpState.VALID){
                if(demand.getStepWeb() == StepWeb.EMAIL_STEP){
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
                response.put("step web : ", demand.getStepWeb().toString());

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


//    public String phoneInput(DemandRequest demandRequest) {
//        feign.Client demand = demandRepository.findByUuid(demandRequest.getUuidClient());
//        OtpCompare otpCompare = OtpCompare.builder()
//                .secretKey(demand.getSecretKey())
//                .build();
//        String generateOtp = otpService.generateOtp(otpCompare);
//
//        if (generateOtp!=null) {
//            //for test
//            EmailSender emailSender = EmailSender.builder()
//                    .email(demand.getEmail())
//                    .codeOtpEmail(generateOtp)
//                    .build();
//            notification.sendOtpEmail(emailSender);
//            return demand.getUuid();
//        } else {
//            return "MAX_GENERATED_OTP_ERROR";
//        }
//    }

//    public OtpState otpPhoneInput(DemandRequest demandRequest) {
//        feign.Client demand = demandRepository.findByUuid(demandRequest.getUuidClient());
//        OtpCompare otpCompare = OtpCompare.builder()
//                .userInput(demandRequest.getUserInput())
//                .secretKey(demand.getSecretKey())
//                .build();
//        if (otpService.compareOtp(otpCompare)==OtpState.VALID){
//            demand.setPhoneStatus(true);
//            //step //demand_status
//            demandRepository.save(demand);
//        }
//        return otpService.compareOtp(otpCompare);
//
//    }
}