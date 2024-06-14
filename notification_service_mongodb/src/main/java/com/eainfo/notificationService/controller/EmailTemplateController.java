package com.eainfo.notificationService.controller;


import com.eainfo.notificationService.dto.TemplateRequest;
import com.eainfo.notificationService.model.EmailTemplate;
import com.eainfo.notificationService.service.EmailTemplateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/templates")
@RequiredArgsConstructor
public class EmailTemplateController {
    private final EmailTemplateService emailTemplateService;

    @GetMapping("/{name}")
    public ResponseEntity<EmailTemplate> getTemplateByName(@PathVariable String name) {
        Optional<EmailTemplate> template = emailTemplateService.getTemplateByName(name);
        return template.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }


    @PostMapping
    public ResponseEntity<EmailTemplate> saveTemplate(@RequestBody TemplateRequest template) {
        EmailTemplate savedTemplate = emailTemplateService.saveTemplate(template);
        return ResponseEntity.ok(savedTemplate);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmailTemplate> updateTemplate(@PathVariable String id, @RequestBody EmailTemplate updatedTemplate) {
        emailTemplateService.updateTemplate(id, updatedTemplate);
        return ResponseEntity.ok(updatedTemplate);
    }

    @GetMapping
    public ResponseEntity<List<EmailTemplate>> getAllTemplates() {
        List<EmailTemplate> templates = emailTemplateService.getAllTemplates();
        return ResponseEntity.ok(templates);
    }
}

