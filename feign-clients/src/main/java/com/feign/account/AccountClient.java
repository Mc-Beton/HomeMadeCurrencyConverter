package com.feign.account;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "account")
public interface AccountClient {

    @PostMapping(path = "api/v1/account")
    ResponseEntity<Void> addNewAccountToClient(@RequestBody AccountDto accountDto);
}
