package com.epam.serdyukov.ispmanager.controller.command.client;

import com.epam.serdyukov.ispmanager.appcontext.AppContext;
import com.epam.serdyukov.ispmanager.controller.Path;
import com.epam.serdyukov.ispmanager.controller.command.ICommand;
import com.epam.serdyukov.ispmanager.model.entity.Tariff;
import com.epam.serdyukov.ispmanager.model.entity.User;
import com.epam.serdyukov.ispmanager.model.service.IAccountService;
import com.epam.serdyukov.ispmanager.model.service.ITariffService;
import com.epam.serdyukov.ispmanager.model.service.ITransactionService;
import com.epam.serdyukov.ispmanager.model.service.IUserService;
import com.epam.serdyukov.ispmanager.util.ReportBuilder;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * Main user page controller command.
 *
 * @author Aleksey Serdyukov
 */
public class AccountCommand implements ICommand {
  private final ITransactionService transactionService =
      AppContext.getInstance().getTransactionService();
  private final IUserService userService =  AppContext.getInstance().getUserService();
  private final IAccountService accountService =  AppContext.getInstance().getAccountService();
  private final ITariffService tariffService = AppContext.getInstance().getTariffService();

  @Override
  public String execute(HttpServletRequest request, HttpServletResponse response) {
    HttpSession session = request.getSession();
    String forward = Path.PAGE_ACCOUNT;

    User fullUser = (User) session.getAttribute("user");
    userService.updateFullUserToSession(request, session, fullUser);

    if (request.getParameter("tariff_id") != null) {
      printTariff(request, response, fullUser);
    }

    if (request.getParameter("amount") != null) {
      forward = topUpAccount(request, response);
    }

    List<Tariff> internetTariffs = tariffService.findAllById(1);
    internetTariffs.sort(Comparator.comparingDouble(Tariff::getPrice));

    List<Tariff> iptvTariffs = tariffService.findAllById(2);
    iptvTariffs.sort(Comparator.comparingDouble(Tariff::getPrice));

    List<Tariff> telephonyTariffs = tariffService.findAllById(3);
    telephonyTariffs.sort(Comparator.comparingDouble(Tariff::getPrice));

    request.setAttribute("internetTariffs", internetTariffs);
    request.setAttribute("iptvTariffs", iptvTariffs);
    request.setAttribute("telephonyTariffs", telephonyTariffs);

    return forward;
  }

  private void printTariff(HttpServletRequest request, HttpServletResponse response, User user) {
    long tariffId = Long.parseLong(request.getParameter("tariff_id"));
    Set<Tariff> tariffs = user.getTariffs();
    for (Tariff tariff : tariffs) {
      if (tariff.getId() == tariffId) {
        ReportBuilder.tariffPdf(response, tariff);
      }
    }
  }

  private String topUpAccount(HttpServletRequest request, HttpServletResponse response) {
    HttpSession session = request.getSession();
    String resp = Path.COMMAND_ACCOUNT;
    User fullUser = (User) session.getAttribute("user");

    BigDecimal amount = BigDecimal.valueOf(Long.parseLong(request.getParameter("amount")));
    transactionService.topUp(fullUser, amount);

    try {
      response.sendRedirect(resp);
      resp = Path.COMMAND_REDIRECT;
    } catch (IOException e) {
      resp = Path.PAGE_ERROR_PAGE;
    }
    return resp;
  }

}