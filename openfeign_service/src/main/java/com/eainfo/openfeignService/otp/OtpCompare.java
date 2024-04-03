package com.eainfo.openfeignService.otp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OtpCompare {
    private byte[] secretKey;
    private String userInput;

}