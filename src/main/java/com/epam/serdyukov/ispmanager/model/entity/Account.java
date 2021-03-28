package com.epam.serdyukov.ispmanager.model.entity;

import java.math.BigDecimal;

/**
 * @author Aleksey Serdyukov
 */
public class Account extends Entity {
    private static final long serialVersionUID = 1L;
    private long number;
    private BigDecimal balance;

    public Account() {
    }

    public Account(long id) {
        super(id);
    }

    public long getNumber() {
        return number;
    }

    public void setNumber(long number) {
        this.number = number;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

}
