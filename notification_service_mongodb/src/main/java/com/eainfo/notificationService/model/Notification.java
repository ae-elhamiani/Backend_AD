package com.eainfo.notificationService.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "notifications")
public class Notification {
    @Id
    private String id;
    private String email;
    private String templateName;
    private String exception = "";
    private Date createdDate;
    private boolean sent;

}

