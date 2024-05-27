package com.eainfo.demandService.openfeign.notification;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SmsSender {
    private String keyPhone;
    private String numPhone;
    private String codeOtpSms;

}
