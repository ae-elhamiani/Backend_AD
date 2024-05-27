package com.eainfo.packsService;

import com.eainfo.packsService.model.ClientProfile;
import com.eainfo.packsService.model.Pack;
import com.eainfo.packsService.repository.ClientProfileRepository;
import com.eainfo.packsService.repository.PacksRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ApplicationContext;
@SpringBootApplication
public class PacksServiceApplication {
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(PacksServiceApplication.class, args);

        insertTestData(context);
    }


    private static void insertTestData(ApplicationContext context) {
        PacksRepository packsRepository = context.getBean(PacksRepository.class);
        ClientProfileRepository clientProfileRepository= context.getBean(ClientProfileRepository.class);


        ClientProfile clientProfile0 = new ClientProfile();
        clientProfile0.setName("Particulier résidant au Maroc");
        clientProfile0.setCode("prt");

        clientProfileRepository.save(clientProfile0);

        ClientProfile clientProfile1 = new ClientProfile();
        clientProfile1.setName("Marocain résidant à l’étranger");
        clientProfile1.setCode("mre");
        clientProfileRepository.save(clientProfile1);

        ClientProfile clientProfile2 = new ClientProfile();
        clientProfile2.setName("Etudiant résidant au Maroc");
        clientProfile2.setCode("std");
        clientProfileRepository.save(clientProfile2);

        ClientProfile clientProfile3 = new ClientProfile();
        clientProfile3.setName("Professionnels");
        clientProfile3.setCode("pro");
        clientProfileRepository.save(clientProfile3);


        // Insert test user data
        Pack pack0 = new Pack();
        pack0.setName("Pack Azur");
        pack0.setCode("azr");
        pack0.setClientProfile(clientProfile0);
        packsRepository.save(pack0);

        Pack pack1 = new Pack();
        pack1.setName("Pack Gold");
        pack1.setCode("gld");
        pack1.setClientProfile(clientProfile0);
        packsRepository.save(pack1);

        Pack pack2 = new Pack();
        pack2.setName("Pack Platinum");
        pack2.setCode("plt");
        pack2.setClientProfile(clientProfile0);
        packsRepository.save(pack2);

        Pack pack3 = new Pack();
        pack3.setName("Pack Blue");
        pack3.setCode("pb");
        pack3.setClientProfile(clientProfile1);
        packsRepository.save(pack3);

        Pack pack4 = new Pack();
        pack4.setName("Pack First");
        pack4.setCode("pf");
        pack4.setClientProfile(clientProfile1);
        packsRepository.save(pack4);

        Pack pack5 = new Pack();
        pack5.setName("Pack Jeune Campus");
        pack5.setCode("pjc");
        pack5.setClientProfile(clientProfile2);
        packsRepository.save(pack5);

        Pack pack6 = new Pack();
        pack6.setName("Pack Essentiel");
        pack6.setCode("ess");
        pack6.setClientProfile(clientProfile3);
        packsRepository.save(pack6);

        Pack pack7 = new Pack();
        pack7.setName("Pack Auto-Entrepreneur");
        pack7.setCode("entr");
        pack7.setClientProfile(clientProfile3);
        packsRepository.save(pack7);

        Pack pack8 = new Pack();
        pack8.setName("Pack Classic");
        pack8.setCode("cls");
        pack8.setClientProfile(clientProfile3);
        packsRepository.save(pack8);

        Pack pack9 = new Pack();
        pack9.setName("Pack Gold");
        pack9.setCode("gld");
        pack9.setClientProfile(clientProfile3);
        packsRepository.save(pack9);

        Pack pack10 = new Pack();
        pack10.setName("Pack Platinum");
        pack10.setCode("plt");
        pack10.setClientProfile(clientProfile3);
        packsRepository.save(pack10);
    }
}