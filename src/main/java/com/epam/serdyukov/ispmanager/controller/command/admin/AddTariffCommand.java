package com.epam.serdyukov.ispmanager.controller.command.admin;

import com.epam.serdyukov.ispmanager.appcontext.AppContext;
import com.epam.serdyukov.ispmanager.controller.Path;
import com.epam.serdyukov.ispmanager.controller.command.ICommand;
import com.epam.serdyukov.ispmanager.model.entity.Tariff;
import com.epam.serdyukov.ispmanager.model.service.ITariffService;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Command for add new tariff.
 *
 * @author  Aleksey Serdyukov
 */
public class AddTariffCommand implements ICommand {
  @Override
  public String execute(HttpServletRequest request, HttpServletResponse response) {
    String name = request.getParameter("name").trim();
    String price = request.getParameter("price").trim();
    String description = request.getParameter("description").trim();
    String serviceId = request.getParameter("serviceId");

    String resp = Path.COMMAND_SHOW_SERVICES;

    Tariff tariff = new Tariff();
    tariff.setName(name);
    tariff.setPrice(Double.parseDouble(price));
    tariff.setDescription(description);
    tariff.setServiceId(Long.parseLong(serviceId));

    ITariffService service = AppContext.getInstance().getTariffService();
    service.save(tariff);
    try {
      response.sendRedirect(resp);
      resp = Path.COMMAND_REDIRECT;
    } catch (IOException e) {
      resp = Path.PAGE_ERROR_PAGE;
    }
    return resp;
  }
}
