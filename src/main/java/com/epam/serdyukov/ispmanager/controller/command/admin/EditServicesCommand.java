package com.epam.serdyukov.ispmanager.controller.command.admin;

import com.epam.serdyukov.ispmanager.controller.Path;
import com.epam.serdyukov.ispmanager.model.entity.PackageServices;
import com.epam.serdyukov.ispmanager.model.entity.Tariff;
import com.epam.serdyukov.ispmanager.model.service.*;
import com.epam.serdyukov.ispmanager.controller.command.ICommand;
import com.epam.serdyukov.ispmanager.model.service.impl.PackageServiceImpl;
import com.epam.serdyukov.ispmanager.model.service.impl.TariffServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class EditServicesCommand implements ICommand {
  @Override
  public String execute(HttpServletRequest request, HttpServletResponse response) {
    IPackageService IPackageService = new PackageServiceImpl();
    ITariffService ITariffService = new TariffServiceImpl();

    List<PackageServices> services = IPackageService.findAll();
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