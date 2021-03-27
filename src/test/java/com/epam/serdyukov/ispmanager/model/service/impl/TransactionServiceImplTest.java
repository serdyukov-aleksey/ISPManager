package com.epam.serdyukov.ispmanager.model.service.impl;

import com.epam.serdyukov.ispmanager.model.entity.User;
import com.epam.serdyukov.ispmanager.model.service.ITransactionService;
import com.epam.serdyukov.ispmanager.model.service.IUserService;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

public class TransactionServiceImplTest {
  ITransactionService ts = TransactionServiceImpl.getInstance();
  IUserService us =  UserServiceImpl.getInstance();
  @Test
  public void calcTransactionsByAccount() {
    BigDecimal res = ts.calcTransactionsByAccount(2);
    BigDecimal exp = BigDecimal.valueOf(150);
    assertEquals(exp, res);
  }

  @Test
  public void createTransactionsByAccount(){
    List<User> users = us.findAllFullInfo();
    for (User user: users){
      ts.saveDailyDebtsByUser(user);
    }

  }
}