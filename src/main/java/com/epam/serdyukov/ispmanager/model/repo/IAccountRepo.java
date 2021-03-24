package com.epam.serdyukov.ispmanager.model.repo;


import com.epam.serdyukov.ispmanager.model.entity.Account;

public interface IAccountRepo extends IEntity<Account> {
    long newNumberContract();
}
