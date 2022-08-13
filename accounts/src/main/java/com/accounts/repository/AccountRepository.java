package com.accounts.repository;

import com.accounts.domain.Account;
import com.feign.account.Currencies;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    List<Account> findAllByClientId(Long clientId);
    Optional<Account> findByClientIdAndCurrency(Long clientId, Currencies curr);
}
