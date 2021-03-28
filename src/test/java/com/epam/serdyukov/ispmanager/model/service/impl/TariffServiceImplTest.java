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

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class TariffServiceImplTest {
  @Mock
  private TariffRepoImpl repo;

  Tariff tariff = new Tariff();
  List<Tariff> tariffs = new ArrayList<>();

  @InjectMocks
  TariffServiceImpl cut;


  @Before
  public void setUp() {
    tariff.setId(1);
    tariff.setName("Test tariff");
    tariff.setDescription("Test description");
    tariff.setPrice(100);
    tariff.setServiceId(1);
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void findAll() {

    Mockito.when(repo.getAll()).thenReturn(tariffs);
    assertEquals(tariffs, cut.findAll());
  }

  @Test
  public void find() {
    Mockito.when(repo.getById(1)).thenReturn(tariff);
    assertEquals(tariff, cut.find(1));
  }

  @Test
  public void strFind() {
    Mockito.when(repo.getByName("name")).thenReturn(tariff);
    assertEquals(tariff, cut.find("name"));
  }

  @Test
  public void findAllById() {
    Mockito.when(repo.getAllByServiceId(1)).thenReturn(tariffs);
    assertEquals(tariffs, cut.findAllById(1));
  }

  @Test
  public void save() {
    cut.save(tariff);
    Mockito.verify(repo, Mockito.times(1)).create(tariff);
  }

  @Test
  public void update() {
    cut.update(tariff);
    Mockito.verify(repo, Mockito.times(1)).update(tariff);
  }

  @Test
  public void remove() {
    cut.remove(1);
    Mockito.verify(repo, Mockito.times(1)).delete(1);
  }
}