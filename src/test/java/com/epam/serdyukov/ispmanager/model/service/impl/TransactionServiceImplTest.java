package com.epam.serdyukov.ispmanager.model.service.impl;

import com.epam.serdyukov.ispmanager.model.entity.Account;
import com.epam.serdyukov.ispmanager.model.entity.Tariff;
import com.epam.serdyukov.ispmanager.model.entity.Transaction;
import com.epam.serdyukov.ispmanager.model.entity.User;
import com.epam.serdyukov.ispmanager.model.repository.ITransactionRepo;
import com.epam.serdyukov.ispmanager.model.service.IAccountService;
import com.epam.serdyukov.ispmanager.model.service.IUserService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;

public class TransactionServiceImplTest {

  @Mock
  ITransactionRepo repo;
  @Mock
  IAccountService accountService;
  @Mock
  IUserService userService;
  @Mock
  Transaction tr;

  @InjectMocks
  TransactionServiceImpl cut;

  List<Transaction> transactions = new ArrayList<>();
  User testUser = new User();

  @Before
  public void setUp() {
    Transaction tr1 = new Transaction(LocalDateTime.now(), 1, BigDecimal.valueOf(100),true,"+");
    Transaction tr2 = new Transaction(LocalDateTime.now(), 1, BigDecimal.valueOf(50),false,"-");
    Transaction tr3 = new Transaction(LocalDateTime.now(), 1, BigDecimal.valueOf(20),false,"-");
    transactions.add(tr1);
    transactions.add(tr2);
    transactions.add(tr3);

    Account account = new Account();
    account.setId(1);
    account.setNumber(1);
    account.setBalance(BigDecimal.ZERO);

    Set<Tariff> tariffs = new HashSet<>();
    tariffs.add(new Tariff("1","",30d,1));
    tariffs.add(new Tariff("2","",60d,2));
    tariffs.add(new Tariff("3","",90d,3));
    testUser.setLogin("testLogin");
    testUser.setPassword("testPwd");
    testUser.setSurname("Serdyukov");
    testUser.setFirstName("Aleksey");
    testUser.setLastName("Sergeevich");
    testUser.setRoleId(2);
    testUser.setBlocked(false);
    testUser.setAccount(account);
    testUser.setTariffs(tariffs);

    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void calcTransactionsByAccount() {
    Mockito.when(repo.getAllByAccount(1)).thenReturn(transactions);
    assertEquals(BigDecimal.valueOf(30), cut.calcTransactionsByAccount(1));
  }

  @Test
  public void saveDailyDebtsByUser() {
    cut.saveDailyDebtsByUser(testUser);
    Mockito.verify(repo, Mockito.times(3)).create(any(Transaction.class));
  }

  @Test
  public void saveDailyDebtsByAllUsers() {
    List<User> users = new ArrayList<>();
    users.add(testUser);
    Mockito.when(userService.findAllFullInfo()).thenReturn(users);
    cut.saveDailyDebtsByAllUsers();
    Mockito.verify(repo, Mockito.times(3)).create(any(Transaction.class));
  }

  @Test
  public void topUp() {
//??
//        Mockito.doNothing().when(cut).save(any(Transaction.class));
//    Mockito.when(repo.getAllByAccount(testUser.getAccount().getId())).thenReturn(transactions);
//    Mockito.doNothing().when(accountService).update(any(Account.class));
//    cut.topUp(testUser,BigDecimal.valueOf(50));
//    assertEquals(BigDecimal.valueOf(30), testUser.getAccount().getBalance());
//    Mockito.verify(accountService, Mockito.times(1)).update(any(Account.class));

  }

  @Test
  public void save() {
    cut.save(any(Transaction.class));
    Mockito.verify(repo, Mockito.times(1)).create(any(Transaction.class));
  }

  @Test
  public void getAllByAccount() {
    Mockito.when(repo.getAllByAccount(1)).thenReturn(transactions);
    assertEquals(transactions, cut.getAllByAccount(1));
  }
}