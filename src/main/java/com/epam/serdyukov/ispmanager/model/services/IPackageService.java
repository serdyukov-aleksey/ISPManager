package com.epam.serdyukov.ispmanager.model.services;

import com.epam.serdyukov.ispmanager.model.entity.PackageServices;

import java.util.List;

public interface IPackageService {

    List<PackageServices> findAll();

    PackageServices find(long id);

    void save(PackageServices service);

    void update(PackageServices service);

    void remove(long id);
}
