package com.feign.account;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "account")
public interface AccountClient {

    @PostMapping(path = "api/v1/account")
    ResponseEntity<Void> addNewAccountToClient(@RequestBody AccountDto accountDto);

    @GetMapping(path = "api/v1/account/{clientId}")
    ResponseEntity<List<AccountDto>> getAllAccounts(@PathVariable Long clientId);

    @GetMapping(path = "api/v1/account/{clientId}/{accountId}")
    ResponseEntity<AccountDto> getChosenAccount(@PathVariable Long clientId, @PathVariable Long accountId);

    @DeleteMapping(path = "api/v1/account/{clientId}/{accountId}")
    ResponseEntity<Void> deleteAccount(@PathVariable Long clientId, @PathVariable Long accountId);
}
