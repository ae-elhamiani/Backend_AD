package com.eainfo.notificationService.service;

import com.eainfo.notificationService.dto.TemplateRequest;
import com.eainfo.notificationService.model.EmailTemplate;
import com.eainfo.notificationService.repository.EmailTemplateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmailTemplateService {

    private final EmailTemplateRepository emailTemplateRepository;

    public Optional<EmailTemplate> getTemplateByName(String name) {
        return emailTemplateRepository.findByName(name);
    }

    public EmailTemplate saveTemplate(TemplateRequest template) {
        EmailTemplate emailTemplate = EmailTemplate.builder()
                .name(template.getName())
                .subject(template.getSubject())
                .category(template.getCategory())
                .defaultTemplate(template.getDefaultTemplate())
                .content(template.getContent())
                .version(template.getVersion())
                .build();
        return emailTemplateRepository.save(emailTemplate);
    }

    public List<EmailTemplate> getAllTemplates() {
        return emailTemplateRepository.findAll();
    }

    public void updateTemplate(String id, EmailTemplate updatedTemplate) {
        Optional<EmailTemplate> existingTemplate = emailTemplateRepository.findById(id);
        if (existingTemplate.isPresent()) {
            EmailTemplate template = existingTemplate.get();
            template.setName(updatedTemplate.getName());
            template.setSubject(updatedTemplate.getSubject());
            template.setContent(updatedTemplate.getContent());
            template.setVersion(updatedTemplate.getVersion());
            emailTemplateRepository.save(template);
        }
    }

    public void deleteTemplate(String id) {
        emailTemplateRepository.deleteById(id);
    }

    public Optional<EmailTemplate> findDefaultTemplateByCategory(String category) {
        return emailTemplateRepository.findByCategoryAndDefaultTemplateOrderByVersionDesc(category, true).stream().findFirst();
    }
}
