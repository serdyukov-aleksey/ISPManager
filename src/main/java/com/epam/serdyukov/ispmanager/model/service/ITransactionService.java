package com.epam.serdyukov.ispmanager.model.service;

import com.epam.serdyukov.ispmanager.model.entity.Transaction;
import com.epam.serdyukov.ispmanager.model.entity.User;

import java.math.BigDecimal;
import java.util.List;

public interface ITransactionService {
  BigDecimal calcTransactionsByAccount(long id);

  void saveDailyDebtsByUser(User user);

  void saveDailyDebtsByAllUsers();

  void save(Transaction transaction);

  List<Transaction> getAllByAccount (long id);

  void topUp(User user, BigDecimal amount);
}
