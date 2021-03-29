package com.epam.serdyukov.ispmanager.controller.command.admin;

import com.epam.serdyukov.ispmanager.appcontext.AppContext;
import com.epam.serdyukov.ispmanager.controller.Path;
import com.epam.serdyukov.ispmanager.controller.command.ICommand;
import com.epam.serdyukov.ispmanager.model.entity.PackageService;
import com.epam.serdyukov.ispmanager.model.entity.Tariff;
import com.epam.serdyukov.ispmanager.model.service.IPackageService;
import com.epam.serdyukov.ispmanager.model.service.ITariffService;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Edit services controller command.
 *
 * @author Aleksey Serdyukov
 */
public class EditServicesCommand implements ICommand {
  @Override
  public String execute(HttpServletRequest request, HttpServletResponse response) {
    IPackageService iPackageService = AppContext.getInstance().getPackageService();
    ITariffService iTariffService = AppContext.getInstance().getTariffService();

    List<PackageService> services = iPackageService.findAll();
    List<Tariff> internetTariffs = iTariffService.findAllById(1);
    List<Tariff> iptvTariffs = iTariffService.findAllById(2);
    List<Tariff> telephonyTariffs = iTariffService.findAllById(3);

    request.setAttribute("services", services);
    request.setAttribute("internetTariffs", internetTariffs);
    request.setAttribute("iptvTariffs", iptvTariffs);
    request.setAttribute("telephonyTariffs", telephonyTariffs);

    return Path.PAGE_SERVICES;
  }
}