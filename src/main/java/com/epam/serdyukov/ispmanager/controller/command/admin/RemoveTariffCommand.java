package com.epam.serdyukov.ispmanager.controller.command.admin;

import com.epam.serdyukov.ispmanager.appcontext.AppContext;
import com.epam.serdyukov.ispmanager.controller.Path;
import com.epam.serdyukov.ispmanager.controller.command.ICommand;
import com.epam.serdyukov.ispmanager.model.service.ITariffService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Remove tariff from user controller command.
 *
 * @author Aleksey Serdyukov
 */
public class RemoveTariffCommand implements ICommand {
  @Override
  public String execute(HttpServletRequest request, HttpServletResponse response) {
    ITariffService service = AppContext.getInstance().getTariffService();
    int tariffId = Integer.parseInt(request.getParameter("tariff_id"));
    service.remove(tariffId);
    return Path.COMMAND_SHOW_SERVICES;
  }
}
