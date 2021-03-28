package com.epam.serdyukov.ispmanager.model.service.impl;

import com.epam.serdyukov.ispmanager.model.entity.Tariff;
import com.epam.serdyukov.ispmanager.model.entity.User;
import com.epam.serdyukov.ispmanager.model.repository.impl.TariffRepoImpl;
import com.epam.serdyukov.ispmanager.model.repository.impl.UserRepoImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;

public class TariffServiceImplTest {
  @Mock
  private TariffRepoImpl repo;

  Tariff tariff = new Tariff();

  @InjectMocks
  TariffServiceImpl cut;

  User testUser = new User();

  @Before
  public void setUp() throws Exception {
    tariff.setId(1);
    tariff.setName("Test tariff");
    tariff.setDescription("Test description");
    tariff.setPrice(100);
    tariff.setServiceId(1);
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void findAll() {
  }

  @Test
  public void find() {
  }

  @Test
  public void testFind() {
  }

  @Test
  public void findAllById() {
    Mockito.when(repo.getById(1)).thenReturn(tariff);
    assertEquals(tariff, cut.find(1));
//    assertEquals("Test tariff", cut.find(1).getName());
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