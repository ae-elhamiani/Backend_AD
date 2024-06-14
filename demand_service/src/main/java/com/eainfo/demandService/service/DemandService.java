package com.eainfo.demandService.service;

import com.eainfo.demandService.model.Demand;
import com.eainfo.demandService.repository.DemandRepository;
import com.eainfo.demandService.openfeign.customer.CustomerService;
import com.eainfo.demandService.openfeign.customer.Customer;
import com.eainfo.demandService.openfeign.notification.NotificationMongodb;
import com.eainfo.demandService.openfeign.otp.OtpService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ExecutorService;

@Service
@RequiredArgsConstructor
public class DemandService {
    private final DemandRepository demandRepository;
    private final CustomerService customerService;

    public Demand getDemandByID(String id){
        Demand demand = demandRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Demand not found with id: " + id));
        Customer customer = customerService.clientById(demand.getIdClient());
        demand.setClient(customer);
        return demand;
    }

    public Demand getDemandByEmail(String email){
        Demand demand = demandRepository.findByEmail(email);
        Customer customer = customerService.clientById(demand.getIdClient());
        demand.setClient(customer);
        return demand;
    }

    public List<Demand> AllDemands(){
        List<Demand> demands = demandRepository.findAll();
        demands.forEach(acc -> acc.setClient(customerService.clientById(acc.getIdClient())));
        return demands;
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