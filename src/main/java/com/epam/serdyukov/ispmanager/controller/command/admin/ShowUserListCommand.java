package com.epam.serdyukov.ispmanager.controller.command.admin;

import com.epam.serdyukov.ispmanager.controller.Path;
import com.epam.serdyukov.ispmanager.model.entity.User;
import com.epam.serdyukov.ispmanager.model.service.*;
import com.epam.serdyukov.ispmanager.controller.command.ICommand;
import com.epam.serdyukov.ispmanager.model.service.impl.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class ShowUserListCommand implements ICommand {
  @Override
  public String execute(HttpServletRequest request, HttpServletResponse response) {
    IPackageService IPackageService = new PackageServiceImpl();
    ITariffService ITariffService = new TariffServiceImpl();
    IUserService IUserService = UserServiceImpl.getInstance();
    IContactDetailsService detailsService = new ContactDetailsServiceImpl();
    IAccountService IAccountService =  AccountServiceImpl.getInstance();


    List<User> users = IUserService.findAll();
    List<User> fullUser = new ArrayList<>();
    for (User user : users) {
      user.setRoleId(user.getRoleId());
      user.setDetails(detailsService.find(user.getDetails().getId()));
      user.setAccount(IAccountService.find(user.getAccount().getId()));
      user.setTariffs(new HashSet<>(IUserService.findUserTariffs(user)));
      fullUser.add(user);
    }
    request.setAttribute("fullUser", fullUser);

    return Path.PAGE_USER_LIST;
  }
}