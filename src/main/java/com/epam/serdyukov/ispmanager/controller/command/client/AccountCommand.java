package com.epam.serdyukov.ispmanager.controller.command.client;

import com.epam.serdyukov.ispmanager.appcontext.AppContext;
import com.epam.serdyukov.ispmanager.controller.Path;
import com.epam.serdyukov.ispmanager.model.entity.Account;
import com.epam.serdyukov.ispmanager.model.entity.ContactDetails;
import com.epam.serdyukov.ispmanager.model.entity.Tariff;
import com.epam.serdyukov.ispmanager.model.entity.User;
import com.epam.serdyukov.ispmanager.model.service.*;
import com.epam.serdyukov.ispmanager.model.service.impl.*;
import com.epam.serdyukov.ispmanager.utils.ReportBuilder;
import com.epam.serdyukov.ispmanager.controller.command.ICommand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Aleksey Serdyukov
 */
public class AccountCommand implements ICommand {
  private final ITransactionService transactionService = TransactionServiceImpl.getInstance();
  private final IUserService userService =  AppContext.getInstance().getUserService();
  private final IContactDetailsService detailsService = new ContactDetailsServiceImpl();
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
      forward = topUpAccount(request, response, userService, accountService, fullUser);
    }

    if (request.getParameter("btnEmail") != null) {
      forward = changeEmail(request, response, detailsService, fullUser);
    }

    if (request.getParameter("btnPassword") != null) {
      forward = changePassword(request, response, userService, fullUser);
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
        ReportBuilder.tariffPDF(response, tariff);
      }
    }
  }

  private String topUpAccount(HttpServletRequest request, HttpServletResponse response, IUserService userService, IAccountService accountService, User user) {
    HttpSession session = request.getSession();
    String resp = Path.COMMAND_ACCOUNT;
    User fullUser = (User) session.getAttribute("user");

    BigDecimal amount = BigDecimal.valueOf(Long.parseLong(request.getParameter("amount")));
    accountService.topUp(fullUser, amount);

    try {
      response.sendRedirect(resp);
      resp = Path.COMMAND_REDIRECT;
    } catch (IOException e) {
      resp = Path.PAGE_ERROR_PAGE;
    }
    return resp;
  }

  private String changeEmail(HttpServletRequest request, HttpServletResponse response, IContactDetailsService detailsService, User user) {
    String errorMessage;
    String resp = Path.COMMAND_ACCOUNT;
    String email = request.getParameter("inputEmail");
    if (email == null || email.isEmpty()) {
      errorMessage = "Email can't be empty";
      request.setAttribute("errorMessage", errorMessage);
    } else {
      ContactDetails detail = user.getDetails();
      detail.setEmail(email);
      detailsService.update(detail);
      resp = Path.PAGE_ACCOUNT;
      try {
        response.sendRedirect(resp);
        resp = Path.COMMAND_REDIRECT;
      } catch (IOException e) {
        resp = Path.PAGE_ERROR_PAGE;
      }
    }
    return resp;
  }

  private String changePassword(HttpServletRequest request, HttpServletResponse response, IUserService userService, User user) {
    String errorMessage;
    String resp = Path.COMMAND_ACCOUNT;
    String password = request.getParameter("inputPassword");
    if (password == null || password.isEmpty()) {
      errorMessage = "Password can't be empty";
      request.setAttribute("errorMessage", errorMessage);
      resp = Path.PAGE_ACCOUNT;
    } else {
      user.setPassword(password);
      userService.update(user);
      try {
        response.sendRedirect(resp);
        resp = Path.COMMAND_REDIRECT;
      } catch (IOException e) {
        resp = Path.PAGE_ERROR_PAGE;
      }
    }
    return resp;
  }
}