package com.epam.serdyukov.ispmanager.model.repo;

import com.epam.serdyukov.ispmanager.model.entity.Entity;

import java.util.List;


public interface IEntity<T extends Entity> {
    List<T> getAll();

    T getById(long id);

    void create(T t);

    void update(T t);

    void delete(long id);
}
