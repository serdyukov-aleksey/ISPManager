package com.epam.serdyukov.ispmanager.model.services.impl;

import com.epam.serdyukov.ispmanager.model.entity.Account;
import com.epam.serdyukov.ispmanager.model.repo.impl.AccountRepoImpl;
import com.epam.serdyukov.ispmanager.model.repo.IAccountRepo;
import com.epam.serdyukov.ispmanager.model.services.IAccountService;

import java.util.List;

public class AccountServiceImpl implements IAccountService {
    private final IAccountRepo repo = new AccountRepoImpl();

    @Override
    public List<Account> findAll() {
        return this.repo.getAll();
    }

    @Override
    public Account find(long id) {
        return this.repo.getById(id);
    }

    @Override
    public void save(Account account) {
        this.repo.create(account);
    }

    @Override
    public void update(Account account) {
        this.repo.update(account);
    }

    @Override
    public void remove(long id) {
        this.repo.delete(id);
    }

    @Override
    public long getNumberContract() {
        return repo.newNumberContract();
    }
}
