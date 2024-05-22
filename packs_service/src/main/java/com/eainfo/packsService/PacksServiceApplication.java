package com.eainfo.packsService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = "com.eainfo.openfeignService")
public class PacksServiceApplication {
    public static void main(String[] args) {SpringApplication.run(PacksServiceApplication.class, args);}}