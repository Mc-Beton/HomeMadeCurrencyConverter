package com.feign.account;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class AccountDto {

    private Long id;
    private Long clientId;
    private BigDecimal amount;
    private Currencies currency;
    private LocalDateTime created;
}
