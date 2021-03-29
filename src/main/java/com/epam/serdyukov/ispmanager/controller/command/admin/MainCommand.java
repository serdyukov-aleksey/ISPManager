package com.epam.serdyukov.ispmanager.controller.command.admin;

import com.epam.serdyukov.ispmanager.appcontext.AppContext;
import com.epam.serdyukov.ispmanager.controller.Path;
import com.epam.serdyukov.ispmanager.controller.command.ICommand;
import com.epam.serdyukov.ispmanager.model.entity.PackageService;
import com.epam.serdyukov.ispmanager.model.entity.Tariff;
import com.epam.serdyukov.ispmanager.model.entity.User;
import com.epam.serdyukov.ispmanager.model.service.IAccountService;
import com.epam.serdyukov.ispmanager.model.service.IContactDetailsService;
import com.epam.serdyukov.ispmanager.model.service.IPackageService;
import com.epam.serdyukov.ispmanager.model.service.ITariffService;
import com.epam.serdyukov.ispmanager.model.service.IUserService;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Main admin controller command.
 *
 * @author Aleksey Serdyukov
 */
public class MainCommand implements ICommand {
  @Override
  public String execute(HttpServletRequest request, HttpServletResponse response) {
    IPackageService packageService = AppContext.getInstance().getPackageService();
    ITariffService tariffService = AppContext.getInstance().getTariffService();
    IUserService userService = AppContext.getInstance().getUserService();
    IContactDetailsService detailsService = AppContext.getInstance().getDetailsService();
    IAccountService accountService = AppContext.getInstance().getAccountService();

    List<PackageService> services = packageService.findAll();
    List<Tariff> internetTariffs = tariffService.findAllById(1);

    List<User> users = userService.findAll();
    List<User> fullUser = new ArrayList<>();
    for (User user : users) {
      user.setRoleId(user.getRoleId());
      user.setDetails(detailsService.find(user.getDetails().getId()));
      user.setAccount(accountService.find(user.getAccount().getId()));
      user.setTariffs(new HashSet<>(userService.findUserTariffs(user)));
      fullUser.add(user);
    }

    List<Tariff> iptvTariffs = tariffService.findAllById(2);
    List<Tariff> telephonyTariffs = tariffService.findAllById(3);
    request.setAttribute("services", services);
    request.setAttribute("internetTariffs", internetTariffs);
    request.setAttribute("iptvTariffs", iptvTariffs);
    request.setAttribute("telephonyTariffs", telephonyTariffs);
    request.setAttribute("fullUser", fullUser);

    return Path.PAGE_MAIN;
  }
}

