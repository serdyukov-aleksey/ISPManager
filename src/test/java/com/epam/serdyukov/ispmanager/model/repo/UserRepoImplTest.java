package com.epam.serdyukov.ispmanager.model.repo;


import com.epam.serdyukov.ispmanager.model.entity.User;
import com.epam.serdyukov.ispmanager.model.repository.impl.UserRepoImpl;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class UserRepoImplTest {

  UserRepoImpl repo = new UserRepoImpl();

  @Test
  public void getAll() {
    List<User> users  = repo.getAll();
    assertEquals(2, repo.getAll().size());

  }

  @Test
  public void getById() {
  }

  @Test
  public void create() {
  }

  @Test
  public void update() {
  }

  @Test
  public void delete() {
  }

  @Test
  public void getByLogin() {
  }

  @Test
  public void getTariffs() {
  }

  @Test
  public void addLinksUsersHasTariffs() {
  }

  @Test
  public void deleteLinksUsersHasTariffs() {
  }
}