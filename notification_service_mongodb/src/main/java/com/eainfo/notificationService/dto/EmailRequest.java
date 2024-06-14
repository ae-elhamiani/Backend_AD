package com.eainfo.notificationService.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmailRequest {
    private String toEmail;
    private String templateCategory;
    private Map<String, Object> variables;

}