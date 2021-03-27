package com.epam.serdyukov.ispmanager.model.service.impl;

import com.epam.serdyukov.ispmanager.model.connectionpool.DBManager;
import com.epam.serdyukov.ispmanager.model.entity.PackageServices;
import com.epam.serdyukov.ispmanager.model.entity.Tariff;
import com.epam.serdyukov.ispmanager.model.entity.User;
import com.epam.serdyukov.ispmanager.model.repository.IUserRepo;
import com.epam.serdyukov.ispmanager.model.repository.impl.UserRepoImpl;
import com.epam.serdyukov.ispmanager.model.service.*;

import javax.jws.soap.SOAPBinding;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * @author Aleksey Serdyukov
 */
public class UserServiceImpl implements IUserService {

  private final IUserRepo repo = new UserRepoImpl();
  private final IContactDetailsService detailsService = new ContactDetailsServiceImpl();
  private final IAccountService accountService = AccountServiceImpl.getInstance();
  private static IUserService instance;
  public static synchronized IUserService getInstance() {
    if (instance == null)
      instance = new UserServiceImpl();
    return instance;
  }

  private UserServiceImpl() {}


  @Override
  public List<User> findAll() {
    return this.repo.getAll();
  }

  @Override
  public List<User> findAllFullInfo() {
    List<User> users = findAll();
    List<User> fullUser = new ArrayList<>();
    for (User user : users) {
      fullUser.add(userToFullUser(user));
    }
    return fullUser;
  }

  @Override
  public User findByLoginFullInfo(String login) {
    return userToFullUser(findByLogin(login));
  }

  @Override
  public User find(long id) {
    return this.repo.getById(id);
  }

  @Override
  public void save(User user) {
    this.repo.create(user);
  }

  @Override
  public void update(User user) {
    this.repo.update(user);
  }

  @Override
  public void remove(int id) {
    this.repo.delete(id);
  }

  @Override
  public User findByLogin(String login) {
    return this.repo.getByLogin(login);
  }

  @Override
  public List<Tariff> findUserTariffs(User user) {
    return this.repo.getTariffs(user);
  }

  @Override
  public void saveLinksUsersHasTariffs(User user, String[] tariffsId) {
    this.repo.addLinksUsersHasTariffs(user, tariffsId);
  }

  @Override
  public void removeLinksUsersHasTariffs(User user) {
    this.repo.deleteLinksUsersHasTariffs(user);
  }

  @Override
  public void updateFullUserToSession(HttpServletRequest request, HttpSession session, User user) {
    User fullUser =  userToFullUser(user);
    session.setAttribute("fullUser", fullUser);
    request.setAttribute("fullUser", fullUser);
  }

  private User userToFullUser (User user){
    user.setDetails(detailsService.find(user.getDetails().getId()));
    user.setAccount(accountService.find(user.getAccount().getId()));
    user.setTariffs(new HashSet<>(findUserTariffs(user)));
    return user;

  }
}
