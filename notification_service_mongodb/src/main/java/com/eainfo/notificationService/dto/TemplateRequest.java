package com.eainfo.notificationService.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TemplateRequest {
    private String category;
    private String name;
    private String subject;
    private String content;
    private Boolean defaultTemplate;
    private Integer version;
}
