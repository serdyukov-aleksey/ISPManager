package com.epam.serdyukov.ispmanager.controller.command.admin;

import com.epam.serdyukov.ispmanager.appcontext.AppContext;
import com.epam.serdyukov.ispmanager.controller.Path;
import com.epam.serdyukov.ispmanager.model.entity.PackageServices;
import com.epam.serdyukov.ispmanager.model.entity.Tariff;
import com.epam.serdyukov.ispmanager.model.entity.User;
import com.epam.serdyukov.ispmanager.model.service.*;
import com.epam.serdyukov.ispmanager.controller.command.ICommand;
import com.epam.serdyukov.ispmanager.model.service.impl.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * @author Aleksey Serdyukov
 */
public class MainCommand implements ICommand {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        IPackageService IPackageService = AppContext.getInstance().getPackageService();
        ITariffService ITariffService = AppContext.getInstance().getTariffService();
        IUserService IUserService =  AppContext.getInstance().getUserService();
        IContactDetailsService detailsService = AppContext.getInstance().getDetailsService();
        IAccountService IAccountService = AppContext.getInstance().getAccountService();

        List<PackageServices> services = IPackageService.findAll();
        List<Tariff> internetTariffs = ITariffService.findAllById(1);
        List<Tariff> iptvTariffs = ITariffService.findAllById(2);
        List<Tariff> telephonyTariffs = ITariffService.findAllById(3);

        List<User> users = IUserService.findAll();
        List<User> fullUser = new ArrayList<>();
        for (User user : users) {
            user.setRoleId(user.getRoleId());
            user.setDetails(detailsService.find(user.getDetails().getId()));
            user.setAccount(IAccountService.find(user.getAccount().getId()));
            user.setTariffs(new HashSet<>(IUserService.findUserTariffs(user)));
            fullUser.add(user);
        }

        request.setAttribute("services", services);
        request.setAttribute("internetTariffs", internetTariffs);
        request.setAttribute("iptvTariffs", iptvTariffs);
        request.setAttribute("telephonyTariffs", telephonyTariffs);
        request.setAttribute("fullUser", fullUser);

        return Path.PAGE_MAIN;
    }
}

