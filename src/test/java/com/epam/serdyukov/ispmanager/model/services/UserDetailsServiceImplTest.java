package com.epam.serdyukov.ispmanager.model.services;

import com.epam.serdyukov.ispmanager.model.entity.ContactDetails;
import com.epam.serdyukov.ispmanager.model.entity.User;
import com.epam.serdyukov.ispmanager.model.repo.impl.UserRepoImpl;
import com.epam.serdyukov.ispmanager.model.services.impl.UserDetailsServiceImpl;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class UserDetailsServiceImplTest {

  UserRepoImpl repo = new UserRepoImpl();
  IUserDetailsService detailsService = new UserDetailsServiceImpl();

  @Test
  public void findAll() {
  }

  @Test
  public void find() {
    List<User> users  = repo.getAll();
    assertEquals(2, repo.getAll().size());
    User user = users.get(0);
    long id =user.getDetails().getId();
    ContactDetails details = detailsService.find(id);
    user.setDetails(detailsService.find(user.getDetails().getId()));
    assertTrue(true);
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
}