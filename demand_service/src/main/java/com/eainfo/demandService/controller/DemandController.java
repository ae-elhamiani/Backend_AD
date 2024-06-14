package com.eainfo.demandService.controller;


import com.eainfo.demandService.model.Demand;
import com.eainfo.demandService.service.DemandService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
public class DemandController {

    private final DemandService demandService;

    @GetMapping("/Demands")
    public List<Demand> getAllDemand() {
        return demandService.AllDemands();
    }
    @GetMapping("/Demands/{accountId}")
    public ResponseEntity<Demand> getDemandByID(@PathVariable String accountId) {
        Demand demand = demandService.getDemandByID(accountId);
        return ResponseEntity.ok(demand);
    }

    @GetMapping("/Demands/{email}")
    public ResponseEntity<Demand> getDemandByEmail(@PathVariable String email) {
        Demand demand = demandService.getDemandByEmail(email);
        return ResponseEntity.ok(demand);
    }


}

