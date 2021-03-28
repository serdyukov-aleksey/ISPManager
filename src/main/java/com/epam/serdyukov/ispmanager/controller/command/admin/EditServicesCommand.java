package com.epam.serdyukov.ispmanager.controller.command.admin;

import com.epam.serdyukov.ispmanager.appcontext.AppContext;
import com.epam.serdyukov.ispmanager.controller.Path;
import com.epam.serdyukov.ispmanager.model.entity.PackageService;
import com.epam.serdyukov.ispmanager.model.entity.Tariff;
import com.epam.serdyukov.ispmanager.model.service.*;
import com.epam.serdyukov.ispmanager.controller.command.ICommand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class EditServicesCommand implements ICommand {
  @Override
  public String execute(HttpServletRequest request, HttpServletResponse response) {
    IPackageService IPackageService = AppContext.getInstance().getPackageService();
    ITariffService ITariffService = AppContext.getInstance().getTariffService();

    List<PackageService> services = IPackageService.findAll();
    List<Tariff> internetTariffs = ITariffService.findAllById(1);
    List<Tariff> iptvTariffs = ITariffService.findAllById(2);
    List<Tariff> telephonyTariffs = ITariffService.findAllById(3);

    request.setAttribute("services", services);
    request.setAttribute("internetTariffs", internetTariffs);
    request.setAttribute("iptvTariffs", iptvTariffs);
    request.setAttribute("telephonyTariffs", telephonyTariffs);

    return Path.PAGE_SERVICES;
  }
}