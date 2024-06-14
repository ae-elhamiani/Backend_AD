package com.eainfo.notificationService.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Data
@Builder @AllArgsConstructor @NoArgsConstructor
@Document(collection = "email_templates")
public class EmailTemplate {
    @Id
    private String id;
    private String category;
    private String name;
    private String subject;
    private String content;
    private Boolean defaultTemplate;
    private Integer version;
}
