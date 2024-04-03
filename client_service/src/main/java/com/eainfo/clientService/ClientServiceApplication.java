package com.eainfo.clientService;

import com.eainfo.clientService.model.ClientProfile;
import com.eainfo.clientService.model.Pack;
import com.eainfo.clientService.repository.ClientProfileRepository;
import com.eainfo.clientService.repository.PackRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
@EnableFeignClients(basePackages = "com.eainfo.openfeignService")
public class ClientServiceApplication {
    public static void main(String[] args) {

        ApplicationContext context = SpringApplication.run(ClientServiceApplication.class, args);
        insertTestData(context);

    }


    private static void insertTestData(ApplicationContext context) {
//        // Get UserRepository bean
        PackRepository packRepository = context.getBean(PackRepository.class);
        ClientProfileRepository clientProfileRepository= context.getBean(ClientProfileRepository.class);


        ClientProfile clientProfile = new ClientProfile();
        clientProfile.setName("student");
        clientProfile.setCode("std");

        clientProfileRepository.save(clientProfile);

        ClientProfile clientProfile1 = new ClientProfile();
        clientProfile1.setName("morrocan res");
        clientProfile1.setCode("mre");
        clientProfileRepository.save(clientProfile1);

        ClientProfile clientProfile2 = new ClientProfile();
        clientProfile2.setName("particulier");
        clientProfile2.setCode("prt");
        clientProfileRepository.save(clientProfile2);


        // Insert test user data
        Pack pack = new Pack();
        pack.setName("student");
        pack.setCode("std");
        pack.setClientProfile(clientProfile);
        packRepository.save(pack);

        Pack pack1 = new Pack();
        pack1.setName("first");
        pack1.setCode("frt");
        pack1.setClientProfile(clientProfile1);
        packRepository.save(pack1);

        Pack pack2 = new Pack();
        pack2.setName("blue");
        pack2.setCode("b");
        pack2.setClientProfile(clientProfile1);
        packRepository.save(pack2);

        Pack pack3 = new Pack();
        pack3.setName("azur");
        pack3.setCode("az");
        pack3.setClientProfile(clientProfile2);
        packRepository.save(pack3);

        Pack pack4 = new Pack();
        pack4.setName("azur33");
        pack4.setCode("az3");
        pack4.setClientProfile(clientProfile2);
        packRepository.save(pack4);


    }
}