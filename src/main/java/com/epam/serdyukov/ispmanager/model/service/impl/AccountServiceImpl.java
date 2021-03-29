package com.epam.serdyukov.ispmanager.model.service.impl;

import com.epam.serdyukov.ispmanager.model.entity.Account;
import com.epam.serdyukov.ispmanager.model.repository.IAccountRepo;
import com.epam.serdyukov.ispmanager.model.service.IAccountService;
import java.util.List;

/**
 * Account service interface implementation.
 *
 * @author Aleksey Serdyukov.
 */
public class AccountServiceImpl implements IAccountService {
  private final IAccountRepo repo;

  public AccountServiceImpl(IAccountRepo repo) {
    this.repo = repo;
  }

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

  @Override
  public long getNextIdValue() {
    return repo.getNextIdValue();
  }


}
