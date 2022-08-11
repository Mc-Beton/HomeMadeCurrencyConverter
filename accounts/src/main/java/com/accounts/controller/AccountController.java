package com.accounts.controller;

import com.accounts.exceptions.AccountNotAvailableException;
import com.accounts.facade.AccountFacade;
import com.accounts.mapper.AccountMapper;
import com.accounts.service.AccountService;
import com.feign.account.AccountDto;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/account")
@AllArgsConstructor
public class AccountController {

    private final AccountService accountService;
    private final AccountFacade accountFacade;
    private final AccountMapper accountMapper;

    @PostMapping
    public void addNewAccountToClient(@RequestBody AccountDto accountDto) {
        accountFacade.createNewAccountFacade(accountDto);
    }

    @GetMapping("/{clientId}")
    public List<AccountDto> getAllAccounts(@PathVariable Long clientId) {
        return accountFacade.getClientsAccounts(clientId);
    }

    @GetMapping("/{clientId}/{accountId}")
    public AccountDto getChosenAccount(@PathVariable Long clientId, @PathVariable Long accountId) throws AccountNotAvailableException {
        return accountMapper.mapToAccountDto(accountFacade.getClientChosenAccount(clientId, accountId).get());
    }

    @DeleteMapping("/{clientId}/{accountId}")
    public void deleteAccount(@PathVariable Long clientId, @PathVariable Long accountId) throws AccountNotAvailableException {
        accountFacade.deleteAccountByUser(clientId, accountId);
    }
}
