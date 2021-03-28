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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.swing.text.StyledEditorKit;
import java.math.BigDecimal;
import java.util.*;

import static org.junit.Assert.*;

public class UserServiceImplTest {

  @Mock
  private UserRepoImpl repo;
  @Mock
  private ContactDetailsServiceImpl detailsService;
  @Mock
  private AccountServiceImpl accountService;
  @Mock
  private HttpServletRequest request;
  @Mock
  private HttpSession session;

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
    Set<Tariff> tariffs = new HashSet<>();

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
    List <User> users = new ArrayList<>();
    users.add(testUser);
    Mockito.when(repo.getAll()).thenReturn(users);
    assertEquals(users, cut.findAll());
  }

  @Test
  public void find() {
    Mockito.when(repo.getById(1)).thenReturn(testUser);
    assertEquals(testUser, cut.find(1));
  }

  @Test
  public void save() {
    cut.save(testUser);
    Mockito.verify(repo, Mockito.times(1)).create(testUser);
  }

  @Test
  public void update() {
    cut.update(testUser);
    Mockito.verify(repo, Mockito.times(1)).update(testUser);
  }

  @Test
  public void remove() {
    cut.remove(1);
    Mockito.verify(repo, Mockito.times(1)).delete(1);
  }

  @Test
  public void findByLogin() {
    String login = "test";
    Mockito.when(repo.getByLogin(login)).thenReturn(testUser);
    assertEquals(testUser, cut.findByLogin(login));
  }

  @Test
  public void findUserTariffs() {
    List<Tariff> tariffs = new ArrayList<>();
    Mockito.when(repo.getTariffs(testUser)).thenReturn(tariffs);
    assertEquals(tariffs, cut.findUserTariffs(testUser));
  }

  @Test
  public void saveLinksUsersHasTariffs() {
    String [] tariffs = new String[]{"test1", "test2"};
    cut.saveLinksUsersHasTariffs(testUser, tariffs);
    Mockito.verify(repo, Mockito.times(1)).addLinksUsersHasTariffs(testUser, tariffs);
  }

  @Test
  public void removeLinksUsersHasTariffs() {
    cut.removeLinksUsersHasTariffs(testUser);
    Mockito.verify(repo, Mockito.times(1)).deleteLinksUsersHasTariffs(testUser);
  }


  @Test
  public void findAllFullInfo() {
    List <User> users = new ArrayList<>();
    users.add(testUser);
    Mockito.when(repo.getAll()).thenReturn(users);
    assertEquals(users, cut.findAll());
  }

  @Test
  public void findByLoginFullInfo() {
    Mockito.when(repo.getByLogin("test")).thenReturn(testUser);
    Mockito.when(detailsService.find(testUser.getDetails().getId())).thenReturn(testUser.getDetails());
    Mockito.when(accountService.find(testUser.getAccount().getId())).thenReturn(testUser.getAccount());
    assertEquals(testUser, cut.findByLoginFullInfo("test"));
  }

  @Test
  public void updateFullUserToSession() {
    cut.updateFullUserToSession(request, session, testUser);
    Mockito.verify(request, Mockito.times(1)).setAttribute("fullUser", testUser);
    Mockito.verify(session, Mockito.times(1)).setAttribute("fullUser", testUser);

  }
}