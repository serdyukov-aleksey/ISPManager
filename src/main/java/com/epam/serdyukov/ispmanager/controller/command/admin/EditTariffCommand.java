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
 * Edit tariff controller command.
 *
 * @author Aleksey Serdyukov
 */
public class EditTariffCommand implements ICommand {
  @Override
  public String execute(HttpServletRequest request, HttpServletResponse response) {
    long id = Long.parseLong(request.getParameter("tariff_id"));
    String name = request.getParameter("name").trim();
    double price = Double.parseDouble(request.getParameter("price").trim());
    String description = request.getParameter("description").trim();
    String resp = Path.COMMAND_SHOW_SERVICES;

    Tariff tariff = new Tariff();
    tariff.setId(id);
    tariff.setName(name);
    tariff.setPrice(price);
    tariff.setDescription(description);

    ITariffService service = AppContext.getInstance().getTariffService();
    service.update(tariff);
    try {
      response.sendRedirect(resp);
      resp = Path.COMMAND_REDIRECT;
    } catch (IOException e) {
      resp = Path.PAGE_ERROR_PAGE;
    }
    return resp;
  }
}
