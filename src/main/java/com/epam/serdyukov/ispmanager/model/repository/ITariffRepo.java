package com.epam.serdyukov.ispmanager.model.repository;

import com.epam.serdyukov.ispmanager.model.entity.Tariff;
import java.util.List;

/**
 * Tariff repository interface.
 *
 * @author Aleksey Serdyukov.
 */
public interface ITariffRepo extends IEntityRepo<Tariff> {

  List<Tariff> getAllByServiceId(long id);

  Tariff getByName(String name);
}
