package com.epam.serdyukov.ispmanager.model.service;

import com.epam.serdyukov.ispmanager.model.entity.Tariff;
import com.epam.serdyukov.ispmanager.model.entity.User;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


/**
 * User service interface .
 *
 * @author Aleksey Serdyukov.
 */
public interface IUserService {

  List<User> findAll();

  List<User> findAllFullInfo();

  User findByLoginFullInfo(String login);

  User find(long id);

  void save(User user);

  void update(User user);

  void remove(int id);

  User findByLogin(String login);

  List<Tariff> findUserTariffs(User user);

  void saveLinksUsersHasTariffs(User user, String[] tariffsId);

  void removeLinksUsersHasTariffs(User user);

  void updateFullUserToSession(HttpServletRequest request, HttpSession session, User fullUser);
}
