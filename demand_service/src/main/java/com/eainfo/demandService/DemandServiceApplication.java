package com.eainfo.demandService;

import com.eainfo.demandService.config.DemandParamsProperties;
import com.eainfo.demandService.config.DemandSecurityProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableConfigurationProperties({DemandSecurityProperties.class, DemandParamsProperties.class})
@EnableFeignClients(basePackages = {"com.eainfo.demandService.openfeign"})


public class DemandServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemandServiceApplication.class, args);
    }
}
