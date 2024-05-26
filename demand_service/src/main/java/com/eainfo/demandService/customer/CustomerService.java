package com.eainfo.demandService.customer;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "CLIENT-SERVICE")
public interface CustomerService {
    @PostMapping("/create-client")
    Customer createClient(@RequestBody Customer client);
    @GetMapping("/client/{accountId}")
    @CircuitBreaker(name = "clientService",fallbackMethod = "getDefaultCustomer")
    Customer clientById(@PathVariable("accountId") String accountId);

    default Customer getDefaultCustomer(String accountId, Exception exception){
        return Customer.builder()
                .id(accountId)
                .profileClientCode("not available")
                .email("not available")
                .keyPhone("not available")
                .numPhone("not available")
                .build();
    }
}
