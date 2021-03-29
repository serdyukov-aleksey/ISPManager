package com.epam.serdyukov.ispmanager.controller.command.common;

import com.epam.serdyukov.ispmanager.appcontext.AppContext;
import com.epam.serdyukov.ispmanager.controller.Path;
import com.epam.serdyukov.ispmanager.controller.command.ICommand;
import com.epam.serdyukov.ispmanager.model.entity.User;
import com.epam.serdyukov.ispmanager.model.service.IAccountService;
import com.epam.serdyukov.ispmanager.model.service.IContactDetailsService;
import com.epam.serdyukov.ispmanager.model.service.IUserService;
import com.epam.serdyukov.ispmanager.util.ReportBuilder;
import java.util.HashSet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Print docs to PDF controller command.
 *
 * @author Aleksey Serdyukov
 */
public class PdfBuilderCommand implements ICommand {
  @Override
  public String execute(HttpServletRequest request, HttpServletResponse response) {
    HttpSession session = request.getSession();
    String forward = Path.COMMAND_SHOW_USERS;
    if (session.getAttribute("newUser") != null) {
      User user = (User) session.getAttribute("newUser");

      ReportBuilder.contractPdf(response, user);
    } else {
      long id = Long.parseLong(request.getParameter("user_id"));
      IUserService userService = AppContext.getInstance().getUserService();
      IContactDetailsService detailsService = AppContext.getInstance().getDetailsService();
      IAccountService accountService = AppContext.getInstance().getAccountService();

      User fullUser = userService.find(id);
      fullUser.setRoleId(fullUser.getRoleId());
      fullUser.setDetails(detailsService.find(fullUser.getDetails().getId()));
      fullUser.setAccount(accountService.find(fullUser.getAccount().getId()));
      fullUser.setTariffs(new HashSet<>(userService.findUserTariffs(fullUser)));
      request.setAttribute("fullUser", fullUser);

      ReportBuilder.contractPdf(response, fullUser);
    }
    return forward;
  }
}
