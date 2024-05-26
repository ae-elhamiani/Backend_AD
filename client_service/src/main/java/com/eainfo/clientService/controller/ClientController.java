package com.eainfo.clientService.controller;

import com.eainfo.clientService.dto.ClientRequest;
import com.eainfo.clientService.model.Client;
import com.eainfo.clientService.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController

@RequiredArgsConstructor
public class ClientController {
    private final ClientService clientService;

    @GetMapping("/clients")
    public List<Client> allClient(){
        return clientService.getAllClient(); }
    @GetMapping("/client/{accountId}")
    public ResponseEntity<Client> clientById(@PathVariable("accountId") String accountId) {
        Client client = clientService.getClientById(accountId);
        return ResponseEntity.ok(client);
    }

    @GetMapping("/clientByStatus")
    public List<Client> clientByStatus(@RequestBody ClientRequest request){
        return clientService.getClientByStatus(request.getClientStatus());
    }
    @PostMapping("/create-client")
    public ResponseEntity<Client> createClient(@RequestBody Client request){
        Client client = clientService.createClient(request);
        return ResponseEntity.ok(client);
    }
}

