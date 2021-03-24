package com.epam.serdyukov.ispmanager.model.repo;

import com.epam.serdyukov.ispmanager.model.entity.Tariff;
import com.epam.serdyukov.ispmanager.model.entity.User;

import java.util.List;

public interface IUserRepo extends IEntity<User> {

    User getByLogin(String login);

    List<Tariff> getTariffs(User user);

    void addLinksUsersHasTariffs(User user, String[] tariffsId);

    void deleteLinksUsersHasTariffs(User user);
}
