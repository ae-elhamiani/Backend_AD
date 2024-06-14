package com.eainfo.demandService.openfeign.notification;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmailSender {
//    private String email;
//    private String codeOtpEmail;

    private String toEmail;
    private String templateCategory;
    private Map<String, Object> variables;

}
