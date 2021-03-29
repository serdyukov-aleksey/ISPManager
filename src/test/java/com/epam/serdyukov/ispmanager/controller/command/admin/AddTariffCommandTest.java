package com.epam.serdyukov.ispmanager.controller.command.admin;

import com.epam.serdyukov.ispmanager.controller.Path;
import com.epam.serdyukov.ispmanager.controller.command.outofcontrol.LoginCommand;
import com.epam.serdyukov.ispmanager.model.entity.Tariff;
import com.epam.serdyukov.ispmanager.model.entity.User;
import com.epam.serdyukov.ispmanager.model.service.ITariffService;
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

public class AddTariffCommandTest {
  @Mock
  HttpServletRequest req;
  @Mock
  HttpServletResponse resp;
  @Mock
  ITariffService service;
  @InjectMocks
  AddTariffCommand cut;


  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void testExecute() {
    Mockito.when(req.getParameter("name")).thenReturn("tarifName");
    Mockito.when(req.getParameter("price")).thenReturn("10");
    Mockito.when(req.getParameter("description")).thenReturn("tariffDescr");
    Mockito.when(req.getParameter("serviceId")).thenReturn("1");
    String result = cut.execute(req,resp);
    assertEquals(Path.COMMAND_REDIRECT, result);
    Mockito.verify(service, Mockito.times(1)).save(any(Tariff.class));

  }
}