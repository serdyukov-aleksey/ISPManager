package com.epam.serdyukov.ispmanager.controller.command.admin;

import com.epam.serdyukov.ispmanager.controller.Path;
import com.epam.serdyukov.ispmanager.controller.command.outofcontrol.LoginCommand;
import com.epam.serdyukov.ispmanager.model.entity.Account;
import com.epam.serdyukov.ispmanager.model.entity.ContactDetails;
import com.epam.serdyukov.ispmanager.model.entity.Tariff;
import com.epam.serdyukov.ispmanager.model.entity.User;
import com.epam.serdyukov.ispmanager.model.service.IAccountService;
import com.epam.serdyukov.ispmanager.model.service.IContactDetailsService;
import com.epam.serdyukov.ispmanager.model.service.IUserService;
import org.junit.Before;
import org.junit.Test;
import org.mindrot.jbcrypt.BCrypt;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;

public class EditClientCommandTest {
  @Mock
  HttpServletRequest req;
  @Mock
  HttpServletResponse resp;
  @Mock
  IUserService userService;
  @Mock
  IContactDetailsService detailsService;
  @Mock
  IAccountService accountService;
  @Mock
  HttpSession session;
  @Mock
  ServletContext servletContext;
  @Mock
  ContactDetails contactDetails;
  @Mock
  Account account;

  @InjectMocks
  EditClientCommand cut;

  User user = new User();
  List<Tariff> tariffs = new ArrayList<>();

  @Before
  public void setUp() {
    user.setLogin("user");
    user.setPassword(BCrypt.hashpw("pass", BCrypt.gensalt()));
    user.setRoleId(2);
    ContactDetails contactDetails = new ContactDetails();
    contactDetails.setId(1);
    user.setDetails(contactDetails);
    Account account = new Account();
    account.setId(1);
    user.setAccount(account);
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void testBlockingUser() {
    Mockito.when(req.getParameter("user_id")).thenReturn("1");
    Mockito.when(userService.find(1)).thenReturn(user);
    Mockito.when(detailsService.find(any(Long.class))).thenReturn(contactDetails);
    Mockito.when(accountService.find(any(Long.class))).thenReturn(account);
    Mockito.when(userService.findUserTariffs(user)).thenReturn(tariffs);
    Mockito.when(session.getAttribute("newUser")).thenReturn(user);
    Mockito.when(req.getServletContext()).thenReturn(servletContext);
    Mockito.when(req.getParameter("btnLock")).thenReturn("btnLock");
    String result = cut.execute(req, resp);
    Mockito.verify(userService, Mockito.times(1)).update(any(User.class));
//    assertEquals(Path.COMMAND_REDIRECT, result);

  }
}