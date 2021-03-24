package com.epam.serdyukov.ispmanager.model.services.impl;


import com.epam.serdyukov.ispmanager.model.entity.PackageServices;
import com.epam.serdyukov.ispmanager.model.repo.IService;
import com.epam.serdyukov.ispmanager.model.repo.impl.ServiceImpl;
import com.epam.serdyukov.ispmanager.model.services.IPackageService;

import java.util.List;

public class PackageServiceImpl implements IPackageService {
    private final IService repo = new ServiceImpl();

    @Override
    public List<PackageServices> findAll() {
        return this.repo.getAll();
    }

    @Override
    public PackageServices find(long id) {
        return this.repo.getById(id);
    }

    @Override
    public void save(PackageServices service) {
        this.repo.create(service);
    }

    @Override
    public void update(PackageServices service) {
        this.repo.update(service);
    }

    @Override
    public void remove(long id) {
        this.repo.delete(id);
    }
}
