package com.eainfo.packsService.controller;

import com.eainfo.packsService.model.ClientProfile;
import com.eainfo.packsService.model.Pack;
import com.eainfo.packsService.repository.ClientProfileRepository;
import com.eainfo.packsService.repository.PacksRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
public class PacksController {

    private final PacksRepository packsRepository;
    private final ClientProfileRepository clientProfileRepository;

    @GetMapping("/clientProfile")
    public ResponseEntity<List<ClientProfile>> getAllClientProfiles() {
        List<ClientProfile> profiles = clientProfileRepository.findAll();
        return ResponseEntity.ok(profiles);
    }

    @GetMapping("/packs/{clientProfileCode}")
    public ResponseEntity<List<Pack>> getPacksByClientProfile(@PathVariable String clientProfileCode) {
        List<Pack> packs = packsRepository.findByClientProfileCode(clientProfileCode);
        return ResponseEntity.ok(packs);
    }


}
