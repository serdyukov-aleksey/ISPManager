package com.epam.serdyukov.ispmanager.controller.command.admin;

import com.epam.serdyukov.ispmanager.appcontext.AppContext;
import com.epam.serdyukov.ispmanager.controller.Path;
import com.epam.serdyukov.ispmanager.controller.command.ICommand;
import com.epam.serdyukov.ispmanager.model.entity.User;
import com.epam.serdyukov.ispmanager.model.service.IAccountService;
import com.epam.serdyukov.ispmanager.model.service.IContactDetailsService;
import com.epam.serdyukov.ispmanager.model.service.IUserService;
import java.io.IOException;
import java.util.HashSet;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * Edit user controller command.
 *
 * @author Aleksey Serdyukov
 */
public class EditClientCommand implements ICommand {
  @Override
  public String execute(HttpServletRequest request, HttpServletResponse response) {
    long id = Long.parseLong(request.getParameter("user_id"));
    String forward = Path.COMMAND_SHOW_USERS;

    IUserService userService = AppContext.getInstance().getUserService();
    IContactDetailsService detailsService = AppContext.getInstance().getDetailsService();
    IAccountService accountService = AppContext.getInstance().getAccountService();

    User user;
    if (id != 0) {
      user = userService.find(id);
      user.setRoleId(user.getRoleId());
      user.setDetails(detailsService.find(user.getDetails().getId()));
      user.setAccount(accountService.find(user.getAccount().getId()));
      user.setTariffs(new HashSet<>(userService.findUserTariffs(user)));

      ServletContext servletContext = request.getServletContext();
      servletContext.setAttribute("user_id", user.getId());
    } else {
      HttpSession session = request.getSession();
      user = (User) session.getAttribute("newUser");
    }

    if (request.getParameter("btnLock") != null) {
      forward = blockUser(response, userService, user);
    }

    if (request.getParameter("btnChangeUser") != null) {
      forward = update(request, response, userService, user);
    }

    if (request.getParameter("btnBack") != null) {
      forward = goBack(request, response);
    }
    return forward;
  }

  private String update(HttpServletRequest request, HttpServletResponse response,
                        IUserService userService, User user) {
    String resp = Path.COMMAND_PROFILE;
    String surname = request.getParameter("surname");
    String firstName = request.getParameter("firstName");
    String lastName = request.getParameter("lastName");
    user.setSurname(surname);
    user.setFirstName(firstName);
    user.setLastName(lastName);
    userService.update(user);
    userService.removeLinksUsersHasTariffs(user);
    String[] trafficsId = request.getParameterValues("arrTrafficsId");
    if (trafficsId != null) {
      userService.saveLinksUsersHasTariffs(user, trafficsId);
    }
    HttpSession session = request.getSession();
    session.removeAttribute("newUser");
    session.setAttribute("newUser", user);
    try {
      response.sendRedirect(resp);
      resp = Path.COMMAND_REDIRECT;
    } catch (IOException e) {
      resp = Path.PAGE_ERROR_PAGE;
    }
    return resp;
  }

  private String blockUser(HttpServletResponse response, IUserService userService, User user) {
    String resp = Path.COMMAND_SHOW_USERS;
    if (user.isBlocked()) {
      user.setBlocked(false);
      userService.update(user);
    } else {
      user.setBlocked(true);
      userService.update(user);
    }
    try {
      response.sendRedirect(resp);
      resp = Path.COMMAND_REDIRECT;
    } catch (IOException e) {
      resp = Path.PAGE_ERROR_PAGE;
    }
    return resp;
  }

  private String goBack(HttpServletRequest request, HttpServletResponse response) {
    String resp = Path.COMMAND_SHOW_USERS;
    HttpSession session = request.getSession();
    session.removeAttribute("newUser");

    ServletContext servletContext = request.getServletContext();
    servletContext.removeAttribute("user_id");
    try {
      response.sendRedirect(resp);
      resp = Path.COMMAND_REDIRECT;
    } catch (IOException e) {
      resp = Path.PAGE_ERROR_PAGE;
    }
    return resp;
  }
}