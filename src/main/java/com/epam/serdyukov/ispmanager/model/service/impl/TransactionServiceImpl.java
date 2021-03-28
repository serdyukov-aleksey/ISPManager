package com.epam.serdyukov.ispmanager.model.service.impl;

import com.epam.serdyukov.ispmanager.appcontext.AppContext;
import com.epam.serdyukov.ispmanager.model.entity.Account;
import com.epam.serdyukov.ispmanager.model.entity.Tariff;
import com.epam.serdyukov.ispmanager.model.entity.Transaction;
import com.epam.serdyukov.ispmanager.model.entity.User;
import com.epam.serdyukov.ispmanager.model.repository.ITransactionRepo;
import com.epam.serdyukov.ispmanager.model.repository.impl.TransactionRepoImpl;
import com.epam.serdyukov.ispmanager.model.service.IAccountService;
import com.epam.serdyukov.ispmanager.model.service.ITransactionService;
import com.epam.serdyukov.ispmanager.model.service.IUserService;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public class TransactionServiceImpl implements ITransactionService {
  private final ITransactionRepo repo;
  private final IAccountService accountService;
  private final IUserService userService;

  public TransactionServiceImpl(ITransactionRepo repo, IAccountService accountService,
                                IUserService userService){
    this.repo=repo;
    this.accountService=accountService;
    this.userService=userService;
  }

  @Override
  public BigDecimal calcTransactionsByAccount(long id) {
    List<Transaction> transactions = repo.getAllByAccount(id);
    BigDecimal result = BigDecimal.ZERO;
    for (Transaction t: transactions){
      result = (t.isCredit()) ? result.add(t.getAmount()):result.subtract(t.getAmount());
    }
    return result;
  }

  @Override
  public void saveDailyDebtsByUser(User user) {
    Set<Tariff> tariffs = user.getTariffs();
    for (Tariff t:tariffs) {
      Transaction transaction = new Transaction();
      transaction.setTimestamp(LocalDateTime.now());
      transaction.setAccount(user.getAccount().getId());
      transaction.setAmount(BigDecimal.valueOf(t.getPrice()).divide(BigDecimal.valueOf(30),2));
      transaction.setCredit(false);
      transaction.setDescription("Daily debiting of funds for the service: "+t.getName());
      repo.create(transaction);
    }
  }

  @Override
  public void saveDailyDebtsByAllUsers() {
    List<User> users = userService.findAllFullInfo();
    for (User user: users){
      saveDailyDebtsByUser(user);
    }
  }

  @Override
  public void topUp(User user, BigDecimal amount) {
    Transaction transaction = new Transaction();
    transaction.setTimestamp(LocalDateTime.now());
    transaction.setAccount(user.getAccount().getId());
    transaction.setCredit(true);
    transaction.setAmount(amount);
    transaction.setDescription("Top up account");
    save(transaction);

    Account account = user.getAccount();
    account.setBalance(calcTransactionsByAccount(account.getId()));
    accountService.update(account);

    if (user.isBlocked() && user.getAccount().getBalance().compareTo(BigDecimal.ZERO) > 0) {
      user.setBlocked(false);
      userService.update(user);
    }
  }

  @Override
  public void save(Transaction transaction) {
    repo.create(transaction);
  }

  @Override
  public List<Transaction> getAllByAccount(long id) {
    return repo.getAllByAccount(id);
  }
}
