package com.accounts.controller;

import com.accounts.service.AccountService;
import com.feign.account.AccountDto;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/account")
@AllArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @PostMapping
    public void addNewAccountToClient(@RequestBody AccountDto accountDto) {
        accountService.createNewAccount(accountDto);
    }
}
