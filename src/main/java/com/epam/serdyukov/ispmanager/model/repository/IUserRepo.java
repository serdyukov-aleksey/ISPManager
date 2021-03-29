package com.epam.serdyukov.ispmanager.model.repository;

import com.epam.serdyukov.ispmanager.model.entity.Tariff;
import com.epam.serdyukov.ispmanager.model.entity.User;
import java.util.List;

/**
 * User repository interface.
 *
 * @author Aleksey Serdyukov.
 */
public interface IUserRepo extends IEntityRepo<User> {

  User getByLogin(String login);

  List<Tariff> getTariffs(User user);

  void addLinksUsersHasTariffs(User user, String[] tariffsId);

  void deleteLinksUsersHasTariffs(User user);
}
