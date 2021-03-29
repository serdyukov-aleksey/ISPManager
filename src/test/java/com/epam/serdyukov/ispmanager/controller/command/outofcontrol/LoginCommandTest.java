package com.epam.serdyukov.ispmanager.controller.command.outofcontrol;

import com.epam.serdyukov.ispmanager.controller.Path;
import com.epam.serdyukov.ispmanager.model.entity.User;
import com.epam.serdyukov.ispmanager.model.service.IUserService;
import org.junit.Before;
import org.junit.Test;
import org.mindrot.jbcrypt.BCrypt;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;

public class LoginCommandTest {
  @Mock
  HttpServletRequest req;
  @Mock
  HttpServletResponse resp;
  @Mock
  IUserService userService;
  @Mock
  HttpSession session;
  @InjectMocks
  LoginCommand cut;

  User user = new User();
  User admin = new User();

  @Before
  public void setUp() {
    user.setLogin("user");
    user.setPassword(BCrypt.hashpw("pass", BCrypt.gensalt()));
    user.setRoleId(2);

    admin.setLogin("admin");
    admin.setPassword(BCrypt.hashpw("pass", BCrypt.gensalt()));
    admin.setRoleId(1);
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void ifUserIsUser() {
    Mockito.when(req.getParameter("login")).thenReturn("user");
    Mockito.when(req.getParameter("password")).thenReturn("pass");
    Mockito.when(userService.findByLogin("user")).thenReturn(user);
    Mockito.when(req.getSession()).thenReturn(session);

    String result = cut.execute(req, resp);
    assertEquals(Path.COMMAND_ACCOUNT, result);
  }

  @Test
  public void ifUserIsAdmin() {
    Mockito.when(req.getParameter("login")).thenReturn("admin");
    Mockito.when(req.getParameter("password")).thenReturn("pass");
    Mockito.when(userService.findByLogin("admin")).thenReturn(admin);
    Mockito.when(req.getSession()).thenReturn(session);

    String result = cut.execute(req, resp);
    assertEquals(Path.COMMAND_SHOW_USERS, result);
  }

  @Test
  public void ifUserIsBlank() {
    Mockito.when(req.getParameter("login")).thenReturn(null);
    Mockito.when(req.getParameter("password")).thenReturn(null);
    Mockito.when(req.getSession()).thenReturn(session);
    String result = cut.execute(req, resp);
    assertEquals(Path.PAGE_LOGIN, result);
  }

  @Test
  public void ifPasswordIsincorrect() {
    Mockito.when(req.getParameter("login")).thenReturn("admin");
    Mockito.when(req.getParameter("password")).thenReturn("pass__");
    Mockito.when(userService.findByLogin("admin")).thenReturn(admin);
    Mockito.when(req.getSession()).thenReturn(session);

    String result = cut.execute(req, resp);
    assertEquals(Path.PAGE_LOGIN, result);
  }
}