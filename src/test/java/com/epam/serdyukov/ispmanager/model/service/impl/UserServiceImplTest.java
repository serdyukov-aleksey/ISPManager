package com.epam.serdyukov.ispmanager.model.service.impl;

import com.epam.serdyukov.ispmanager.model.entity.Account;
import com.epam.serdyukov.ispmanager.model.entity.ContactDetails;
import com.epam.serdyukov.ispmanager.model.entity.Tariff;
import com.epam.serdyukov.ispmanager.model.entity.User;
import com.epam.serdyukov.ispmanager.model.repository.impl.UserRepoImpl;
import com.epam.serdyukov.ispmanager.model.service.IUserService;
import org.junit.Before;
import org.junit.Test;
import org.mindrot.jbcrypt.BCrypt;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

public class UserServiceImplTest {

  @Mock
  private UserRepoImpl repo;

  @InjectMocks
  UserServiceImpl cut;

  User testUser = new User();


  @Before
  public void setUp(){

    ContactDetails details = new ContactDetails();
    details.setId(1);
    details.setCity("City");
    details.setStreet("Street");
    details.setHome("Home");
    details.setApartment("Apartment");
    details.setEmail("email");
    details.setPhone("+380501234567");

    Account account = new Account();
    account.setId(1);
    account.setNumber(1);
    account.setBalance(BigDecimal.ZERO);
    Set<Tariff> tariffs = new HashSet<Tariff>();

    testUser.setLogin("testLogin");
    testUser.setPassword("testPwd");
    testUser.setSurname("Serdyukov");
    testUser.setFirstName("Aleksey");
    testUser.setLastName("Sergeevich");
    testUser.setRoleId(2);
    testUser.setBlocked(false);
    testUser.setDetails(details);
    testUser.setAccount(account);
    testUser.setTariffs(tariffs);

//    cut = UserServiceImpl.getInstance();
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void findAll() {


    List <User> users = cut.findAllFullInfo();

  }

  @Test
  public void find() {
  }

  @Test
  public void save() {
  }

  @Test
  public void update() {
  }

  @Test
  public void remove() {
  }

  @Test
  public void findByLogin() {
    String login = "test";
    Mockito.when(repo.getByLogin(login)).thenReturn(testUser);
    assertEquals(testUser, cut.findByLogin(login));
  }

  @Test
  public void findUserTariffs() {
  }

  @Test
  public void saveLinksUsersHasTariffs() {
  }

  @Test
  public void removeLinksUsersHasTariffs() {
  }


  @Test
  public void testFindAll() {
  }

  @Test
  public void findAllFullInfo() {
  }

  @Test
  public void findByLoginFullInfo() {
  }

  @Test
  public void testFind() {
  }

  @Test
  public void testSave() {
  }

  @Test
  public void testUpdate() {
  }

  @Test
  public void testRemove() {
  }

  @Test
  public void testFindByLogin() {
  }

  @Test
  public void testFindUserTariffs() {
  }

  @Test
  public void testSaveLinksUsersHasTariffs() {
  }

  @Test
  public void testRemoveLinksUsersHasTariffs() {
  }

  @Test
  public void updateFullUserToSession() {
  }
}