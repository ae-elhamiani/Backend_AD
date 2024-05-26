package com.eainfo.clientService;

import com.eainfo.clientService.model.Client;
import com.eainfo.clientService.repository.ClientRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ApplicationContext;

import java.util.UUID;

@SpringBootApplication
@EnableFeignClients(basePackages = "com.eainfo.openfeignService")
public class ClientServiceApplication {
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(ClientServiceApplication.class, args);
        insertTestData(context);

    }
    private static void insertTestData(ApplicationContext context) {
        ClientRepository clientRepository = context.getBean(ClientRepository.class);
        Client client = new Client();
        client.setId(UUID.randomUUID().toString());
        client.setEmail("prt@gma");
        client.setKeyPhone("djfksjh");

        clientRepository.save(client);


    }
}