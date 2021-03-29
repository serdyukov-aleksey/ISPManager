package com.epam.serdyukov.ispmanager.controller.command.common;

import com.epam.serdyukov.ispmanager.controller.Path;
import com.epam.serdyukov.ispmanager.controller.command.ICommand;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Mock controller command.
 *
 * @author Aleksey Serdyukov
 */
public class NoCommand implements ICommand {
  @Override
  public String execute(HttpServletRequest request, HttpServletResponse response) {
    String errorMessage = "No such command";
    request.setAttribute("errorMessage", errorMessage);
    return Path.PAGE_ERROR_PAGE;
  }
}
