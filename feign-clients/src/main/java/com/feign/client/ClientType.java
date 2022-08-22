package com.feign.client;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public enum ClientType {
    PREMIUM(9.99,10,1.01,0.99),
    NORMAL(6.99,5,1.04,0.96),
    BASIC(2.99,2,1.08,0.92);

    private Double monthlyCost;
    private Integer numberOfAccounts;
    private Double buyCurrency;
    private Double sellCurrency;

    ClientType(Double monthlyCost, Integer numberOfAccounts, Double buyCurrency, Double sellCurrency) {
        this.monthlyCost = monthlyCost;
        this.numberOfAccounts = numberOfAccounts;
        this.buyCurrency = buyCurrency;
        this.sellCurrency = sellCurrency;
    }
}
