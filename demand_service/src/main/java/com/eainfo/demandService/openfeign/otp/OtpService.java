package com.eainfo.demandService.openfeign.otp;

import com.eainfo.demandService.openfeign.otp.OtpCompare;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "otp-service")
public interface OtpService {

    @PostMapping("/generate")
    String generateOtp(@RequestBody OtpCompare request);

    @PostMapping("/compare")
    String compareOtp(@RequestBody OtpCompare request);

}
