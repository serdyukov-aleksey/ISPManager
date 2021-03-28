package com.epam.serdyukov.ispmanager.controller.command.client;

import com.epam.serdyukov.ispmanager.appcontext.AppContext;
import com.epam.serdyukov.ispmanager.controller.Path;
import com.epam.serdyukov.ispmanager.controller.command.ICommand;
import com.epam.serdyukov.ispmanager.model.entity.Transaction;
import com.epam.serdyukov.ispmanager.model.entity.User;
import com.epam.serdyukov.ispmanager.model.service.ITransactionService;
import com.epam.serdyukov.ispmanager.model.service.impl.TransactionServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class TransactionCommand implements ICommand {
  @Override
  public String execute(HttpServletRequest request, HttpServletResponse response) {
    HttpSession session = request.getSession();
    String forward = Path.PAGE_TRANSACTIONS;
    User fullUser = (User) session.getAttribute("user");
    ITransactionService ts = AppContext.getInstance().getTransactionService();
    List<Transaction> transactions = ts.getAllByAccount(fullUser.getAccount().getId());
    session.setAttribute("transactions", transactions);
    request.setAttribute("transactions", transactions);
    return forward;
  }
}
