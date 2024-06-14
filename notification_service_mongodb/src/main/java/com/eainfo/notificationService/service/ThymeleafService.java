package com.eainfo.notificationService.service;

import com.eainfo.notificationService.model.EmailTemplate;
import com.eainfo.notificationService.repository.EmailTemplateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ITemplateResolver;
import org.thymeleaf.templateresolver.StringTemplateResolver;

import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ThymeleafService {

    private final EmailTemplateRepository emailTemplateRepository;
    private final TemplateEngine templateEngine = emailTemplateEngine();

    private TemplateEngine emailTemplateEngine() {
        final SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(stringTemplateResolver());
        templateEngine.setTemplateEngineMessageSource(emailMessageSource());
        return templateEngine;
    }

    private ITemplateResolver stringTemplateResolver() {
        final StringTemplateResolver templateResolver = new StringTemplateResolver();
        templateResolver.setTemplateMode(TemplateMode.HTML);
        templateResolver.setCacheable(false);
        return templateResolver;
    }

    private ResourceBundleMessageSource emailMessageSource() {
        final ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("mail/MailMessages");
        return messageSource;
    }

    public Optional<EmailTemplate> getEmailTemplate(String templateName) {
        return emailTemplateRepository.findByName(templateName);
    }

    public String createContent(String templateContent, Map<String, Object> variables) {
        final Context context = new Context();
        context.setVariables(variables);
        return templateEngine.process(templateContent, context);
    }
}
