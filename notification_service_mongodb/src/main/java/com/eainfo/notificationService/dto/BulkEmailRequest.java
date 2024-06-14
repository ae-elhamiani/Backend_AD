package com.eainfo.notificationService.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BulkEmailRequest {
    private List<String> toEmails;
    private String templateCategory;
    private Map<String, Object> variables;
}
