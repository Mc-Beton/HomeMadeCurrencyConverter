package com.accounts.service;

import com.accounts.domain.Account;
import com.accounts.repository.AccountRepository;
import com.feign.account.AccountDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;

    public void createNewAccount(AccountDto accountDto) {
        Account account = Account.builder()
                .clientId(accountDto.getClientId())
                .amount(new BigDecimal(0))
                .currency(accountDto.getCurrency())
                .created(LocalDateTime.now())
                .build();
        accountRepository.save(account);
    }

    public List<Account> getClientsAccountList(Long clientId) {
        return accountRepository.findAllByClientId(clientId);
    }

    public void deleteAccount(Long accountId) {
        accountRepository.deleteById(accountId);
    }
}
