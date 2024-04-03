package com.eainfo.openfeignService.client;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "client-service", path = "/client-service")
public interface Client {
}
