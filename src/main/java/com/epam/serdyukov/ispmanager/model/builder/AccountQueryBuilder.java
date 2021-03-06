package com.epam.serdyukov.ispmanager.model.builder;

import com.epam.serdyukov.ispmanager.model.entity.Account;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Account entity query builder.
 *
 * @author Aleksey Serdyukov.
 */
public class AccountQueryBuilder extends QueryBuilder<Account> {
  @Override
  public List<Account> getListOfResult(ResultSet rs) throws SQLException {
    List<Account> accounts = new ArrayList<>();
    while (rs.next()) {
      Account account = new Account();
      account.setId(rs.getLong("id"));
      account.setNumber(rs.getInt("number"));
      account.setBalance(rs.getBigDecimal("balance"));
      accounts.add(account);
    }
    return accounts;
  }

  @Override
  public Account getResult(ResultSet rs) throws SQLException {
    Account account = new Account();
    while (rs.next()) {
      account.setId(rs.getLong("id"));
      account.setNumber(rs.getInt("number"));
      account.setBalance(rs.getBigDecimal("balance"));
    }
    return account;
  }
}
