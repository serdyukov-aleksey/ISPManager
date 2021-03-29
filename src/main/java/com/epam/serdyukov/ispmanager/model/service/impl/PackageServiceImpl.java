package com.epam.serdyukov.ispmanager.model.service.impl;

import com.epam.serdyukov.ispmanager.model.entity.PackageService;
import com.epam.serdyukov.ispmanager.model.repository.IServiceRepo;
import com.epam.serdyukov.ispmanager.model.service.IPackageService;
import java.util.List;

/**
 * Package service interface implementation.
 *
 * @author Aleksey Serdyukov.
 */
public class PackageServiceImpl implements IPackageService {
  private final IServiceRepo repo;

  public PackageServiceImpl(IServiceRepo repo) {
    this.repo = repo;
  }

  @Override
  public List<PackageService> findAll() {
    return this.repo.getAll();
  }

  @Override
  public PackageService find(long id) {
    return this.repo.getById(id);
  }

  @Override
  public void save(PackageService service) {
    this.repo.create(service);
  }

  @Override
  public void update(PackageService service) {
    this.repo.update(service);
  }

  @Override
  public void remove(long id) {
    this.repo.delete(id);
  }
}
