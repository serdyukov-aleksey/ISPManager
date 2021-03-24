package com.epam.serdyukov.ispmanager.model.repo;

import com.epam.serdyukov.ispmanager.model.entity.ContactDetails;
import com.epam.serdyukov.ispmanager.model.repo.impl.UserDetailsRepoImpl;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserDetailsRepoImplTest {

  IUserDetailsRepo repo = new UserDetailsRepoImpl();

  @Test
  public void getAll() {
  }

  @Test
  public void getById() {
    ContactDetails cd = repo.getById(1L);
    assertEquals("Ivanov", cd.getLastName());
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
}