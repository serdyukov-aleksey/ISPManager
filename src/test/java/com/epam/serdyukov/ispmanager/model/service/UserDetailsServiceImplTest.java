package com.epam.serdyukov.ispmanager.model.service;

import com.epam.serdyukov.ispmanager.appcontext.AppContext;
import com.epam.serdyukov.ispmanager.model.entity.ContactDetails;
import com.epam.serdyukov.ispmanager.model.entity.User;
import com.epam.serdyukov.ispmanager.model.repository.impl.UserRepoImpl;
import com.epam.serdyukov.ispmanager.model.service.impl.ContactDetailsServiceImpl;
import org.junit.Test;
import org.mindrot.jbcrypt.BCrypt;

import java.util.List;

import static org.junit.Assert.*;

public class UserDetailsServiceImplTest {

  UserRepoImpl repo = new UserRepoImpl();
  IContactDetailsService detailsService = AppContext.getInstance().getDetailsService();

  @Test
  public void findAll() {
    String password = "user";

    String hashed = BCrypt.hashpw(password, BCrypt.gensalt());
    String hashed2 = BCrypt.hashpw(password, BCrypt.gensalt());
    String hashed3 = BCrypt.hashpw(password, BCrypt.gensalt());

    System.out.println(BCrypt.hashpw(password, BCrypt.gensalt()));
    System.out.println(BCrypt.hashpw(password, BCrypt.gensalt()));
    System.out.println(BCrypt.hashpw(password, BCrypt.gensalt()));
// gensalt's log_rounds parameter determines the complexity
// the work factor is 2**log_rounds, and the default is 10
//    String hashed = BCrypt.hashpw(password, BCrypt.gensalt(12));

// Check that an unencrypted password matches one that has
// previously been hashed
    if (BCrypt.checkpw(password, hashed))
      System.out.println("It matches");
    else
      System.out.println("It does not match");
    if (BCrypt.checkpw(password, hashed2))
      System.out.println("It matches");
    else
      System.out.println("It does not match");
    if (BCrypt.checkpw(password, hashed3))
      System.out.println("It matches");
    else
      System.out.println("It does not match");
  }

  @Test
  public void find() {
    List<User> users  = repo.getAll();
    assertEquals(2, repo.getAll().size());
    User user = users.get(0);
    long id =user.getDetails().getId();
    ContactDetails details = detailsService.find(id);
    user.setDetails(detailsService.find(user.getDetails().getId()));
    assertTrue(true);
  }

  @Test
  public void save() {
  }

  @Test
  public void update() {
  }

  @Test
  public void remove() {
  }
}