package com.epam.serdyukov.ispmanager.model.repo.impl;

import com.epam.serdyukov.ispmanager.model.dao.builders.ContactDetailsQueryBuilder;
import com.epam.serdyukov.ispmanager.model.entity.ContactDetails;
import com.epam.serdyukov.ispmanager.model.pool.DBManager;
import com.epam.serdyukov.ispmanager.model.dao.builders.QueryBuilder;
import com.epam.serdyukov.ispmanager.model.repo.IUserDetailsRepo;

import java.util.List;

public class UserDetailsRepoImpl implements IUserDetailsRepo {
  private static final String CREATE = "INSERT INTO user_details (id, city, street, building, flat, phone, first_name, last_name, email) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
  private static final String GET_ALL = "SELECT * FROM user_details";
  private static final String GET_BY_ID = "SELECT id, city, street, building, flat, phone, first_name, last_name, email FROM user_details WHERE id = ?";
  private static final String UPDATE = "UPDATE user_details SET email = ?, phone = ? WHERE id = ?";
  private static final String DELETE = "DELETE FROM user_details WHERE id = ?";
  private static final String GET_NEXT_AUTO_INCREMENT = "SELECT MAX(ID)+1 FROM user_details";

  private DBManager instance = DBManager.getInstance();
  private QueryBuilder queryBuilder = new ContactDetailsQueryBuilder();

  @Override
  public List<ContactDetails> getAll() {
    return this.queryBuilder.executeAndReturnList(instance, GET_ALL);
  }

  @Override
  public ContactDetails getById(long id) {
    return (ContactDetails) this.queryBuilder.executeAndReturn(instance, GET_BY_ID, id);
  }

  @Override
  public void create(ContactDetails details) {
    long id = queryBuilder.getNextAutoIncrement(instance, GET_NEXT_AUTO_INCREMENT);
    queryBuilder.execute(instance, CREATE,
        id, details.getCity(), details.getStreet(), details.getBuilding(), details.getFlat(),
        details.getPhone(), details.getFirstName(), details.getLastName(), details.getEmail());
  }

  @Override
  public void update(ContactDetails details) {
    this.queryBuilder.execute(instance, UPDATE, details.getEmail(), details.getPhone(), details.getId());
  }

  @Override
  public void delete(long id) {
    this.queryBuilder.execute(instance, DELETE, id);
  }
}
