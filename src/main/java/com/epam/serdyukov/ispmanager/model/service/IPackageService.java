package com.epam.serdyukov.ispmanager.model.service;

import com.epam.serdyukov.ispmanager.model.entity.PackageService;

import java.util.List;

/**
 * @author Aleksey Serdyukov
 */
public interface IPackageService {

    List<PackageService> findAll();

    PackageService find(long id);

    void save(PackageService service);

    void update(PackageService service);

    void remove(long id);
}
