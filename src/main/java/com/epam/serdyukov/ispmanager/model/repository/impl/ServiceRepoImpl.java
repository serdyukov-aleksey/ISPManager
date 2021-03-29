package com.epam.serdyukov.ispmanager.model.repository.impl;

import com.epam.serdyukov.ispmanager.model.builder.QueryBuilder;
import com.epam.serdyukov.ispmanager.model.builder.ServiceQueryBuilder;
import com.epam.serdyukov.ispmanager.model.connectionpool.DBManager;
import com.epam.serdyukov.ispmanager.model.entity.PackageService;
import com.epam.serdyukov.ispmanager.model.repository.IServiceRepo;
import java.util.List;

/**
 * Services repository interface implementation.
 *
 * @author Aleksey Serdyukov.
 */
public class ServiceRepoImpl implements IServiceRepo {
  private static final String GET_ALL = "SELECT * FROM services";
  private static final String GET_BY_ID = "SELECT id, name, description FROM services WHERE id = ?";
  private static final String CREATE = "INSERT INTO services (name, description) VALUES (?, ?)";
  private static final String UPDATE = "UPDATE services SET name = ?, description = ? WHERE id = ?";
  private static final String DELETE = "DELETE FROM services WHERE id = ?";

  private DBManager instance = DBManager.getInstance();

  @Override
  public List<PackageService> getAll() {
    QueryBuilder queryBuilder = new ServiceQueryBuilder();
    return queryBuilder.executeAndReturnList(instance, GET_ALL);
  }

  @Override
  public PackageService getById(long id) {
    QueryBuilder queryBuilder = new ServiceQueryBuilder();
    return (PackageService) queryBuilder.executeAndReturn(instance, GET_BY_ID, id);
  }

  @Override
  public void create(PackageService packageService) {
    QueryBuilder queryBuilder = new ServiceQueryBuilder();
    queryBuilder.execute(instance, CREATE, packageService.getName(),
        packageService.getDescription());
  }

  @Override
  public void update(PackageService packageService) {
  }

  @Override
  public void delete(long id) {
    QueryBuilder queryBuilder = new ServiceQueryBuilder();
    queryBuilder.execute(instance, DELETE, id);
  }
}
