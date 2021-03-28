package com.epam.serdyukov.ispmanager.model.service.impl;

import com.epam.serdyukov.ispmanager.model.entity.ContactDetails;
import com.epam.serdyukov.ispmanager.model.repository.IContactDetailsRepo;
import com.epam.serdyukov.ispmanager.model.service.IContactDetailsService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ContactDetailsServiceImplTest {

  @Mock
  IContactDetailsRepo repo;

  @InjectMocks
  ContactDetailsServiceImpl cut;

  ContactDetails testDetails = new ContactDetails();

  @Before
  public void setUp()  {
    testDetails.setId(1);
    testDetails.setCity("City");
    testDetails.setStreet("Street");
    testDetails.setHome("Home");
    testDetails.setApartment("Apartment");
    testDetails.setEmail("email");
    testDetails.setPhone("+380501234567");

    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void findAll() {
    List<ContactDetails> testList = new ArrayList<>();
    Mockito.when(repo.getAll()).thenReturn(testList);
    assertEquals(testList, cut.findAll());
  }

  @Test
  public void find() {
    Mockito.when(repo.getById(1)).thenReturn(testDetails);
    assertEquals(testDetails, cut.find(1));
  }

  @Test
  public void save() {
    cut.save(testDetails);
    Mockito.verify(repo, Mockito.times(1)).create(testDetails);
  }

  @Test
  public void update() {
    cut.update(testDetails);
    Mockito.verify(repo, Mockito.times(1)).update(testDetails);
  }

  @Test
  public void remove() {
    cut.remove(1);
    Mockito.verify(repo, Mockito.times(1)).delete(1);
  }

  @Test
  public void getNextIdValue() {
    Mockito.when(repo.getNextIdValue()).thenReturn(5L);
    assertEquals(5L, cut.getNextIdValue());
  }
}