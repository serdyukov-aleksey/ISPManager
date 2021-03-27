package com.epam.serdyukov.ispmanager.controller.command.client;

import com.epam.serdyukov.ispmanager.controller.Path;
import com.epam.serdyukov.ispmanager.controller.command.ICommand;
import com.epam.serdyukov.ispmanager.model.entity.User;
import com.epam.serdyukov.ispmanager.model.service.IUserService;
import com.epam.serdyukov.ispmanager.model.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UserProfileCommand implements ICommand {
  private final IUserService userService = UserServiceImpl.getInstance();

  @Override
  public String execute(HttpServletRequest request, HttpServletResponse response) {
    HttpSession session = request.getSession();
    String forward = Path.PAGE_USER_PROFILE;
    User fullUser = (User) session.getAttribute("user");
    userService.updateFullUserToSession(request, session, fullUser);
    return forward;
  }

}
