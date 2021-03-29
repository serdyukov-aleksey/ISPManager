package com.epam.serdyukov.ispmanager.controller.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Main interface for the Command pattern implementation.
 *
 * @author Aleksey Serdyukov
 */
public interface ICommand {
  /**
   * Execution method for command.
   *
   * @return Address to go once the command is executed.
   */
  String execute(HttpServletRequest request, HttpServletResponse response);
}
