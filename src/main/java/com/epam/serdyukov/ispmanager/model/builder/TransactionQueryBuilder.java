package com.epam.serdyukov.ispmanager.model.builder;

import com.epam.serdyukov.ispmanager.model.entity.Transaction;
import com.epam.serdyukov.ispmanager.model.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TransactionQueryBuilder extends QueryBuilder<Transaction> {

  @Override
  public List<Transaction> getListOfResult(ResultSet rs) throws SQLException {
    List<Transaction> transactions = new ArrayList<>();
    while (rs.next()) {
      transactions.add(getResult(rs));
    }
    return transactions;
  }

  @Override
  public Transaction getResult(ResultSet rs) throws SQLException {
    Transaction transaction = new Transaction();
    transaction.setTimestamp(rs.getTimestamp("timestamp").toLocalDateTime());
    transaction.setAccount(rs.getLong("account_id"));
    transaction.setAmount(rs.getBigDecimal("amount"));
    transaction.setCredit(rs.getBoolean("is_credit"));
    transaction.setDescription(rs.getString("description"));
    return transaction;
  }
}
