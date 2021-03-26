package com.epam.serdyukov.ispmanager.controller.command.client;

import com.epam.serdyukov.ispmanager.controller.Path;
import com.epam.serdyukov.ispmanager.model.entity.User;
import com.epam.serdyukov.ispmanager.model.service.*;
import com.epam.serdyukov.ispmanager.controller.command.ICommand;
import com.epam.serdyukov.ispmanager.model.service.impl.AccountServiceImpl;
import com.epam.serdyukov.ispmanager.model.service.impl.ContactDetailsServiceImpl;
import com.epam.serdyukov.ispmanager.model.service.impl.TariffServiceImpl;
import com.epam.serdyukov.ispmanager.model.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashSet;

public class UserProfileCommand implements ICommand {

  @Override
  public String execute(HttpServletRequest request, HttpServletResponse response) {
    HttpSession session = request.getSession();
    String forward = Path.PAGE_USER_PROFILE;
    IUserService userService = new UserServiceImpl();
    IContactDetailsService detailsService = new ContactDetailsServiceImpl();
    IAccountService accountService = new AccountServiceImpl();
    ITariffService tariffService = new TariffServiceImpl();

    User fullUser = (User) session.getAttribute("user");
    fullUser.setRoleId(fullUser.getRoleId());
    fullUser.setDetails(detailsService.find(fullUser.getDetails().getId()));
    fullUser.setAccount(accountService.find(fullUser.getAccount().getId()));
    fullUser.setTariffs(new HashSet<>(userService.findUserTariffs(fullUser)));

    session.setAttribute("fullUser", fullUser);
    request.setAttribute("fullUser", fullUser);
    return forward;
  }
}
