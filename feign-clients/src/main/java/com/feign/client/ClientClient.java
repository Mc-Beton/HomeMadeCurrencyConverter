package com.feign.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "client")
public interface ClientClient {

    @PostMapping(path = "v1/client")
    ResponseEntity<Void> createNewUser(@RequestBody ClientDto clientDto);
}
