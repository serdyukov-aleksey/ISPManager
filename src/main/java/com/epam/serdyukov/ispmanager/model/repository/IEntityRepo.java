package com.epam.serdyukov.ispmanager.model.repository;

import com.epam.serdyukov.ispmanager.model.entity.Entity;
import java.util.List;

/**
 * Main CRUD repository interface.
 *
 * @author Aleksey Serdyukov.
 */
public interface IEntityRepo<T extends Entity> {
  List<T> getAll();

  T getById(long id);

  void create(T t);

  void update(T t);

  void delete(long id);
}
