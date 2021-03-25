package com.epam.serdyukov.ispmanager.model.repo;


import com.epam.serdyukov.ispmanager.model.entity.Tariff;

import java.util.List;

public interface ITariff extends IEntity<Tariff> {

    List<Tariff> getAllByServiceId(long id);

    Tariff getByName(String name);
}
