package com.epam.serdyukov.ispmanager.controller.command.common;

import org.apache.log4j.Logger;
import com.epam.serdyukov.ispmanager.controller.Path;
import com.epam.serdyukov.ispmanager.controller.command.ICommand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author Aleksey Serdyukov
 */
public class LogoutCommand implements ICommand {
    private static final Logger log = Logger.getLogger(LogoutCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        log.debug("ICommand starts");

        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        log.debug("ICommand finished");
        return Path.PAGE_LOGIN;
    }
}
