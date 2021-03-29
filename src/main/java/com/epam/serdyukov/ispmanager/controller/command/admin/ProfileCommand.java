package com.epam.serdyukov.ispmanager.controller.command.admin;

import com.epam.serdyukov.ispmanager.appcontext.AppContext;
import com.epam.serdyukov.ispmanager.controller.Path;
import com.epam.serdyukov.ispmanager.controller.command.ICommand;
import com.epam.serdyukov.ispmanager.model.entity.User;
import com.epam.serdyukov.ispmanager.model.service.IAccountService;
import com.epam.serdyukov.ispmanager.model.service.IContactDetailsService;
import com.epam.serdyukov.ispmanager.model.service.IUserService;
import java.util.HashSet;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * User profile controller command.
 *
 * @author Aleksey Serdyukov
 */
public class ProfileCommand implements ICommand {
  @Override
  public String execute(HttpServletRequest request, HttpServletResponse response) {
    HttpSession session = request.getSession();
    ServletContext servletContext = request.getServletContext();

    if (session.getAttribute("newUser") != null) {
      User newUser = (User) session.getAttribute("newUser");
      request.setAttribute("fullUser", newUser);
    }

    if (request.getParameter("user_id") != null) {
      long id = Long.parseLong(request.getParameter("user_id"));
      show(request, id);
    }

    if (servletContext.getAttribute("user_id") != null) {
      Long id = (Long) servletContext.getAttribute("user_id");
      show(request, id);
    }

    return Path.PAGE_PROFILE;
  }

  private void show(HttpServletRequest request, long id) {
    IUserService userService = AppContext.getInstance().getUserService();
    IContactDetailsService detailsService = AppContext.getInstance().getDetailsService();
    IAccountService accountService = AppContext.getInstance().getAccountService();

    User user = userService.find(id);
    user.setRoleId(user.getRoleId());
    user.setDetails(detailsService.find(user.getDetails().getId()));
    user.setAccount(accountService.find(user.getAccount().getId()));
    user.setTariffs(new HashSet<>(userService.findUserTariffs(user)));
    request.setAttribute("fullUser", user);
  }
}
