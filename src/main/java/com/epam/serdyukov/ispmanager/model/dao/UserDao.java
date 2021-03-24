package com.epam.serdyukov.ispmanager.model.dao;


import com.epam.serdyukov.ispmanager.model.dao.builders.UserQueryBuilder;
import com.epam.serdyukov.ispmanager.model.entity.User;
import com.epam.serdyukov.ispmanager.model.pool.DBManager;
import com.epam.serdyukov.ispmanager.model.dao.builders.QueryBuilder;

/**
 * Data access object for User entity.
 */
public class UserDao {

  private static final String GET_BY_LOGIN =
      "SELECT * FROM users WHERE login=?";

  private static final String GET_BY_ID =
      "SELECT * FROM users WHERE id=?";

  private DBManager instance = DBManager.getInstance();
  private QueryBuilder queryBuilder = new UserQueryBuilder();

  public User getById(long id) {
    return (User) queryBuilder.executeAndReturn(instance, GET_BY_ID, id);
  }

  public User getByLogin(String login) {
    return (User) queryBuilder.executeAndReturn(instance, GET_BY_LOGIN, login);
  }

}
