package com.eainfo.openfeignService.otp;

import com.eainfo.openfeignService.otp.outiles.enums.OtpState;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "otp-service", path = "/otp-service")
public interface OtpService {

    @PostMapping("generate")
    String generateOtp(@RequestBody OtpCompare request);

    @PostMapping("compare")
    OtpState compareOtp(@RequestBody OtpCompare request);

}
