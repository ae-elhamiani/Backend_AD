package com.eainfo.clientService.service;

import com.bastiaanjansen.otp.SecretGenerator;
import com.eainfo.clientService.enums.ClientStatus;
import com.eainfo.clientService.model.Client;
import com.eainfo.clientService.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ClientService {
    private final ClientRepository clientRepository;
    public List<Client> getAllClient (){
        return clientRepository.findAll();

    }
    public Client getClientById(String idClient){
        return clientRepository.findById(idClient);
    }
    public List<Client> getClientByStatus(ClientStatus status){
        return clientRepository.findByClientStatus(status);

    }

    public Client createClient(Client request){
        Client client = Client.builder()
                    .id(UUID.randomUUID().toString())
                    .profileClientCode(request.getProfileClientCode())
                    .clientStatus(ClientStatus.PRE_PROSPECT)
                    .secretKey(SecretGenerator.generate())
                    .email(request.getEmail())
                    .build();
        clientRepository.save(client);
        return client;

    }

}