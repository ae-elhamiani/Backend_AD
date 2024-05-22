package com.eainfo.openfeignService.customer;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "CLIENT-SERVICE")
public interface CustomerService {
    @PostMapping("/create-client")
    Customer createClient(@RequestBody Customer client);
    @GetMapping("/client/client-by-id")
    Customer findClientById(@RequestBody String id);

}
