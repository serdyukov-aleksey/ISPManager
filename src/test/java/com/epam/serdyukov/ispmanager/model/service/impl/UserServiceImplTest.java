package com.epam.serdyukov.ispmanager.model.service.impl;

import com.epam.serdyukov.ispmanager.model.entity.User;
import com.epam.serdyukov.ispmanager.model.service.IUserService;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class UserServiceImplTest {

  IUserService userService= UserServiceImpl.getInstance();

  @Test
  public void findAll() {
    List <User> users = userService.findAllFullInfo();

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
}