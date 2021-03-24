package com.epam.serdyukov.ispmanager.model.services;

import com.epam.serdyukov.ispmanager.model.entity.Tariff;

import java.util.List;

public interface ITariffService {

    List<Tariff> findAll();

    List<Tariff> findAllById(long id);

    Tariff find(long id);

    Tariff find(String name);

    void save(Tariff tariff);

    void update(Tariff tariff);

    void remove(long id);
}
