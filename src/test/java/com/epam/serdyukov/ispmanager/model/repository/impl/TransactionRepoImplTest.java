package com.epam.serdyukov.ispmanager.model.repository.impl;

import com.epam.serdyukov.ispmanager.model.entity.Transaction;
import com.epam.serdyukov.ispmanager.model.repository.ITransactionRepo;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.Assert.*;

public class TransactionRepoImplTest {

  ITransactionRepo repo = new TransactionRepoImpl();

  @Test
  public void getAllByAccount() {
  }

  @Test
  public void getAll() {
    List<Transaction> transactions = repo.getAllByAccount(2);

  }

  @Test
  public void getById() {
  }

  @Test
  public void create() {
    Transaction transaction = new Transaction();
    transaction.setTimestamp(LocalDateTime.now());
    transaction.setAccount(3);
    transaction.setCredit(false);
    transaction.setAmount(BigDecimal.valueOf(50));
    transaction.setDescription("Test debit");
    repo.create(transaction);
  }

  @Test
  public void update() {
  }

  @Test
  public void delete() {
  }
}