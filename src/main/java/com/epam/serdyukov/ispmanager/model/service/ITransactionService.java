package com.epam.serdyukov.ispmanager.model.service;

import com.epam.serdyukov.ispmanager.model.entity.Transaction;
import com.epam.serdyukov.ispmanager.model.entity.User;

import java.math.BigDecimal;

public interface ITransactionService {
  BigDecimal calcTransactionsByAccount(long id);

  void saveDailyDebtsByUser(User user);

  void saveDailyDebtsByAllUsers();

  void save(Transaction transaction);
}
