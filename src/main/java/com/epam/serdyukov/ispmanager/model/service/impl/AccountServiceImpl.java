package com.epam.serdyukov.ispmanager.model.service.impl;

import com.epam.serdyukov.ispmanager.appcontext.AppContext;
import com.epam.serdyukov.ispmanager.model.entity.Account;
import com.epam.serdyukov.ispmanager.model.entity.Transaction;
import com.epam.serdyukov.ispmanager.model.entity.User;
import com.epam.serdyukov.ispmanager.model.repository.impl.AccountRepoImpl;
import com.epam.serdyukov.ispmanager.model.repository.IAccountRepo;
import com.epam.serdyukov.ispmanager.model.service.IAccountService;
import com.epam.serdyukov.ispmanager.model.service.ITransactionService;
import com.epam.serdyukov.ispmanager.model.service.IUserService;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Aleksey Serdyukov
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

    @Override
    public void topUp(User user, BigDecimal amount) {
        ITransactionService ts = TransactionServiceImpl.getInstance();
        IUserService userService = AppContext.getInstance().getUserService();
        Transaction transaction = new Transaction();
        transaction.setTimestamp(LocalDateTime.now());
        transaction.setAccount(user.getAccount().getId());
        transaction.setCredit(true);
        transaction.setAmount(amount);
        transaction.setDescription("Top up account");
        ts.save(transaction);

        Account account = user.getAccount();
        account.setBalance(ts.calcTransactionsByAccount(account.getId()));
        update(account);

        if (user.isBlocked() && user.getAccount().getBalance().compareTo(BigDecimal.ZERO) > 0) {
            user.setBlocked(false);
            userService.update(user);
        }
    }


}
