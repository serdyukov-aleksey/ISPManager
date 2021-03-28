package com.epam.serdyukov.ispmanager.model.service.impl;

import com.epam.serdyukov.ispmanager.model.entity.PackageServices;
import com.epam.serdyukov.ispmanager.model.repository.IContactDetailsRepo;
import com.epam.serdyukov.ispmanager.model.repository.IServiceRepo;
import com.epam.serdyukov.ispmanager.model.repository.impl.ServiceRepoImpl;
import com.epam.serdyukov.ispmanager.model.service.IPackageService;

import java.util.List;

/**
 * @author Aleksey Serdyukov
 */
public class PackageServiceImpl implements IPackageService {
    private final IServiceRepo repo;

    public PackageServiceImpl (IServiceRepo repo){
        this.repo=repo;
    }
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
