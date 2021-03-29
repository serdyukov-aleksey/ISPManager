package com.epam.serdyukov.ispmanager.model.service;

import com.epam.serdyukov.ispmanager.model.entity.Transaction;
import com.epam.serdyukov.ispmanager.model.entity.User;
import java.math.BigDecimal;
import java.util.List;

/**
 * Transaction service interface .
 *
 * @author Aleksey Serdyukov.
 */
public interface ITransactionService {
  /**
   * Calculate all transactions.
   *
   * @return  Account amount.
   */
  BigDecimal calcTransactionsByAccount(long id);

  /**
   * Save daily debt by all user tariffs.
   */
  void saveDailyDebtsByUser(User user);

  /**
   * Save daily debts by all users.
   */
  void saveDailyDebtsByAllUsers();

  void save(Transaction transaction);

  List<Transaction> getAllByAccount(long id);

  /**
   * Create top-up transaction.
   */
  void topUp(User user, BigDecimal amount);

  /**
   * Checks if user has negative account amount and blocks it.
   */
  void recalcBalanceAndBlockByAllUsers();
}
