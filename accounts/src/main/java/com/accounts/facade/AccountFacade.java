package com.accounts.facade;

import com.accounts.domain.Account;
import com.accounts.exceptions.AccountNotAvailableException;
import com.accounts.mapper.AccountMapper;
import com.accounts.repository.AccountRepository;
import com.accounts.service.AccountService;
import com.feign.account.AccountDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class AccountFacade {

    private final AccountService accountService;
    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;

    public void createNewAccountFacade(AccountDto accountDto) {
        log.info("Process started: add new account...");
        Optional<Account> account = accountRepository.findByClientIdAndCurrency(
                accountDto.getClientId(),
                accountDto.getCurrency());
        if (account.isPresent()) {
            log.info("Client {} has account with currency {}. Terminating process...",
                    accountDto.getClientId(),
                    accountDto.getCurrency());
        } else {
            log.info("Adding new account with currency {}, for client {}...",
                    accountDto.getCurrency(),
                    accountDto.getClientId());
            accountService.createNewAccount(accountDto);
        }
    }

    public Optional<Account> getClientChosenAccount(Long clientId, Long accountId) throws AccountNotAvailableException {
        log.info("Process started: get clients chosen account...");
        Optional<Account> account = accountRepository.findById(accountId);
        if (account.isPresent() && account.get().getClientId().equals(clientId)) {
            log.info("Access to account {}, by client {} - granted",
                    accountId, clientId);
            return account;
        } else {
            log.info("Access to account {}, by client {} - rejected",
                    accountId, clientId);
            throw new AccountNotAvailableException();
        }
    }

    public List<AccountDto> getClientsAccounts(Long clientId) {
        log.info("Process started: get clients accounts...");
        //TODO add client validation if exists
        List<Account> accounts = accountService
                .getClientsAccountList(clientId);
        log.info("Client {} has {} accounts",
                clientId,
                accounts.size());
        return accountMapper.mapToAccountDtoList(accounts);
    }

    public void deleteAccountByUser(Long clientId, Long accountId) throws AccountNotAvailableException {
        log.info("Process started: delete currency account...");
        Optional<Account> account = getClientChosenAccount(clientId, accountId);
        if(account.isPresent()
                && account.get().getAmount().equals(BigDecimal.ZERO)) {
            log.info("deleting...");
            accountService.deleteAccount(accountId);
            log.info("Account deleted...");
        } else {
            log.info("Access to account {}, by client {} - rejected",
                    accountId, clientId);
            throw new AccountNotAvailableException();
        }
    }
}
