package com.accounts.mapper;

import com.accounts.domain.Account;
import com.feign.account.AccountDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountMapper {

    public Account mapToAccount(AccountDto accountDto) {
        return new Account(
                accountDto.getId(),
                accountDto.getClientId(),
                accountDto.getAmount(),
                accountDto.getCurrency(),
                accountDto.getCreated()
        );
    }

    public AccountDto mapToAccountDto(Account account) {
        return new AccountDto(
                account.getId(),
                account.getClientId(),
                account.getAmount(),
                account.getCurrency(),
                account.getCreated()
        );
    }

    public List<AccountDto> mapToAccountDtoList(List<Account> accounts) {
        return accounts.stream()
                .map(this::mapToAccountDto)
                .collect(Collectors.toList());
    }
}
