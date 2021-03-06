package com.epam.serdyukov.ispmanager.controller.command.admin;

import com.epam.serdyukov.ispmanager.appcontext.AppContext;
import com.epam.serdyukov.ispmanager.controller.Path;
import com.epam.serdyukov.ispmanager.controller.command.ICommand;
import com.epam.serdyukov.ispmanager.model.entity.Account;
import com.epam.serdyukov.ispmanager.model.entity.ContactDetails;
import com.epam.serdyukov.ispmanager.model.entity.Tariff;
import com.epam.serdyukov.ispmanager.model.entity.User;
import com.epam.serdyukov.ispmanager.model.service.IAccountService;
import com.epam.serdyukov.ispmanager.model.service.IContactDetailsService;
import com.epam.serdyukov.ispmanager.model.service.ITariffService;
import com.epam.serdyukov.ispmanager.model.service.IUserService;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.mindrot.jbcrypt.BCrypt;

/**
 * New user registration controller command.
 *
 * @author Aleksey Serdyukov
 */
public class RegistrationCommand implements ICommand {

  @Override
  public String execute(HttpServletRequest request, HttpServletResponse response) {
    String firstName = request.getParameter("firstName").trim();
    String lastName = request.getParameter("lastName").trim();
    String surname = request.getParameter("surname").trim();
    String login = request.getParameter("login").trim();
    String password = request.getParameter("password").trim();

    String city = request.getParameter("city").trim();
    String street = request.getParameter("street").trim();
    String home = request.getParameter("home").trim();
    String apartment = request.getParameter("apartment").trim();
    String email = request.getParameter("email").trim();
    String phone = request.getParameter("phone").trim();

    String[] trafficsId = request.getParameterValues("arrTrafficsId");

    IUserService userService = AppContext.getInstance().getUserService();
    IContactDetailsService detailsService = AppContext.getInstance().getDetailsService();
    IAccountService accountService = AppContext.getInstance().getAccountService();
    ITariffService tariffService = AppContext.getInstance().getTariffService();

    ContactDetails details = new ContactDetails();
    details.setId(detailsService.getNextIdValue());
    details.setCity(city);
    details.setStreet(street);
    details.setHome(home);
    details.setApartment(apartment);
    details.setEmail(email);
    details.setPhone(phone);
    detailsService.save(details);

    Account account = new Account();
    account.setId(accountService.getNextIdValue());
    account.setNumber(accountService.getNumberContract());
    account.setBalance(BigDecimal.ZERO);
    accountService.save(account);

    Set<Tariff> tariffs;
    if (trafficsId != null) {
      tariffs = new HashSet<>();
      for (String item : trafficsId) {
        tariffs.add(tariffService.find(Long.parseLong(item)));
      }
    } else {
      tariffs = Collections.emptySet();
    }

    User newUser = new User();
    newUser.setLogin(login);
    newUser.setPassword(BCrypt.hashpw(password, BCrypt.gensalt()));
    newUser.setSurname(surname);
    newUser.setFirstName(firstName);
    newUser.setLastName(lastName);
    newUser.setRoleId(2);
    newUser.setBlocked(true);
    newUser.setDetails(details);
    newUser.setAccount(account);
    newUser.setTariffs(tariffs);
    userService.save(newUser);

    if (trafficsId != null) {
      userService.saveLinksUsersHasTariffs(newUser, trafficsId);
    }

    HttpSession session = request.getSession();
    session.setAttribute("newUser", newUser);

    String resp = Path.COMMAND_PROFILE;
    try {
      response.sendRedirect(resp);
      resp = Path.COMMAND_REDIRECT;
    } catch (IOException e) {
      resp = Path.PAGE_ERROR_PAGE;
    }
    return resp;
  }
}
