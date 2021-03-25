package com.epam.serdyukov.ispmanager.model.repo.impl;

import com.epam.serdyukov.ispmanager.model.dao.builders.ServiceQueryBuilder;
import com.epam.serdyukov.ispmanager.model.entity.PackageServices;
import com.epam.serdyukov.ispmanager.model.pool.DBManager;
import com.epam.serdyukov.ispmanager.model.dao.builders.QueryBuilder;
import com.epam.serdyukov.ispmanager.model.repo.IService;

import java.util.List;

public class ServiceImpl implements IService {
    private static final String GET_ALL = "SELECT * FROM services";
    private static final String GET_BY_ID = "SELECT id, name, description FROM services WHERE id = ?";
    private static final String CREATE = "INSERT INTO services (name, description) VALUES (?, ?)";
    private static final String UPDATE = "UPDATE services SET name = ?, description = ? WHERE id = ?";
    private static final String DELETE = "DELETE FROM services WHERE id = ?";

    private DBManager instance = DBManager.getInstance();

    @Override
    public List<PackageServices> getAll() {
        QueryBuilder queryBuilder = new ServiceQueryBuilder();
        return queryBuilder.executeAndReturnList(instance, GET_ALL);
    }

    @Override
    public PackageServices getById(long id) {
        QueryBuilder queryBuilder = new ServiceQueryBuilder();
        return (PackageServices) queryBuilder.executeAndReturn(instance, GET_BY_ID, id);
    }

    @Override
    public void create(PackageServices packageServices) {
        QueryBuilder queryBuilder = new ServiceQueryBuilder();
        queryBuilder.execute(instance, CREATE, packageServices.getName(), packageServices.getDescription());
    }

    @Override
    public void update(PackageServices packageServices) {

    }

    @Override
    public void delete(long id) {
        QueryBuilder queryBuilder = new ServiceQueryBuilder();
        queryBuilder.execute(instance, DELETE, id);
    }
}
