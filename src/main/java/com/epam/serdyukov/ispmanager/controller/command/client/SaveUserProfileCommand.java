package com.epam.serdyukov.ispmanager.controller.command.client;

import com.epam.serdyukov.ispmanager.appcontext.AppContext;
import com.epam.serdyukov.ispmanager.controller.Path;
import com.epam.serdyukov.ispmanager.controller.command.ICommand;
import com.epam.serdyukov.ispmanager.model.entity.ContactDetails;
import com.epam.serdyukov.ispmanager.model.entity.User;
import com.epam.serdyukov.ispmanager.model.service.IContactDetailsService;
import com.epam.serdyukov.ispmanager.model.service.IUserService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.mindrot.jbcrypt.BCrypt;

/**
 * User profile save controller command.
 *
 * @author Aleksey Serdyukov
 */
public class SaveUserProfileCommand implements ICommand {
  private final IUserService userService = AppContext.getInstance().getUserService();
  private final IContactDetailsService detailsService =
      AppContext.getInstance().getDetailsService();

  @Override
  public String execute(HttpServletRequest request, HttpServletResponse response) {
    HttpSession session = request.getSession();
    User fullUser = (User) session.getAttribute("user");

    String city = request.getParameter("city").trim();
    String street = request.getParameter("street").trim();
    String home = request.getParameter("home").trim();
    String apartment = request.getParameter("apartment").trim();
    String email = request.getParameter("email").trim();
    String phone = request.getParameter("phone").trim();

    ContactDetails details = new ContactDetails();
    details.setId(fullUser.getDetails().getId());
    details.setCity(city);
    details.setStreet(street);
    details.setHome(home);
    details.setApartment(apartment);
    details.setEmail(email);
    details.setPhone(phone);

    if (!request.getParameter("password").trim().equals("")) {
      String password = request.getParameter("password").trim();
      fullUser.setPassword(BCrypt.hashpw(password, BCrypt.gensalt()));
    }
    String firstName = request.getParameter("firstName").trim();
    String lastName = request.getParameter("lastName").trim();
    String surname = request.getParameter("surname").trim();
    fullUser.setSurname(surname);
    fullUser.setFirstName(firstName);
    fullUser.setLastName(lastName);

    detailsService.save(details);
    userService.save(fullUser);

    return Path.PAGE_USER_PROFILE;
  }
}
