package com.epam.serdyukov.ispmanager.model.service.impl;

import com.epam.serdyukov.ispmanager.model.entity.PackageService;
import com.epam.serdyukov.ispmanager.model.repository.IServiceRepo;
import com.epam.serdyukov.ispmanager.model.service.IPackageService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class PackageServiceImplTest {

  @Mock
  IServiceRepo repo;

  @InjectMocks
  PackageServiceImpl cut;

  PackageService testPs = new PackageService();

  @Before
  public void setUp(){
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void findAll() {
    List<PackageService> psList = new ArrayList<>();
    Mockito.when(repo.getAll()).thenReturn(psList);
    assertEquals(psList, cut.findAll());
  }

  @Test
  public void find() {
    Mockito.when(repo.getById(1)).thenReturn(testPs);
    assertEquals(testPs, cut.find(1));
  }

  @Test
  public void save() {
    cut.save(testPs);
    Mockito.verify(repo, Mockito.times(1)).create(testPs);
  }

  @Test
  public void update() {
    cut.update(testPs);
    Mockito.verify(repo, Mockito.times(1)).update(testPs);
  }

  @Test
  public void remove() {
    cut.remove(1);
    Mockito.verify(repo, Mockito.times(1)).delete(1);
  }
}