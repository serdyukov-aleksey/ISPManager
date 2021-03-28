package com.epam.serdyukov.ispmanager.model.repo;


import com.epam.serdyukov.ispmanager.model.entity.ContactDetails;
import com.epam.serdyukov.ispmanager.model.repository.impl.ContactDetailsRepoImpl;
import com.epam.serdyukov.ispmanager.model.repository.IContactDetailsRepo;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserDetailsRepoImplTest {

  IContactDetailsRepo repo = new ContactDetailsRepoImpl();

  @Test
  public void getAll() {
  }

  @Test
  public void getById() {
    ContactDetails cd = repo.getById(1L);
    assertEquals("Kharkiv", cd.getCity());
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