package com.epam.serdyukov.ispmanager.model.service.impl;

import com.epam.serdyukov.ispmanager.model.entity.Account;
import com.epam.serdyukov.ispmanager.model.entity.User;
import com.epam.serdyukov.ispmanager.model.repository.IAccountRepo;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class AccountServiceImplTest {

  @Mock
  IAccountRepo repo;


  private Account testAcc = new Account();
  private User testUser = new User();
  List<Account> testList = new ArrayList<>();

  @InjectMocks
  AccountServiceImpl cut;

  @Before
  public void setUp() {
    testAcc.setId(1);
    testAcc.setNumber(1);
    testAcc.setBalance(BigDecimal.valueOf(100));
    testList.add(testAcc);
    testUser.setAccount(testAcc);

    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void findAll() {
    Mockito.when(repo.getAll()).thenReturn(testList);
    assertEquals(testList, cut.findAll());
  }

  @Test
  public void find() {
    Mockito.when(repo.getById(1)).thenReturn(testAcc);
    assertEquals(testAcc, cut.find(1));
  }

  @Test
  public void save() {
    cut.save(testAcc);
    Mockito.verify(repo, Mockito.times(1)).create(testAcc);
  }

  @Test
  public void update() {
    cut.update(testAcc);
    Mockito.verify(repo, Mockito.times(1)).update(testAcc);
  }

  @Test
  public void remove() {
    cut.remove(1);
    Mockito.verify(repo, Mockito.times(1)).delete(1);
  }

  @Test
  public void getNumberContract() {
    Mockito.when(repo.newNumberContract()).thenReturn(5L);
    assertEquals(5L, cut.getNumberContract());
  }

  @Test
  public void getNextIdValue() {
    Mockito.when(repo.getNextIdValue()).thenReturn(10L);
    assertEquals(10L, cut.getNextIdValue());
  }


}