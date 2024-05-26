package com.eainfo.demandService.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "demand.params")
public class DemandParamsProperties {
    private long expirationTimeMs;
    private String generationAlgorithm;

}

