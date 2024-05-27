package com.eainfo.demandService.openfeign.notification;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmailSender {
    private String email;
    private String codeOtpEmail;

}
