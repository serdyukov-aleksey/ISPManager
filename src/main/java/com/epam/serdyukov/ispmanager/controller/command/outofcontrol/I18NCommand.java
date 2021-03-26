package com.epam.serdyukov.ispmanager.controller.command.outofcontrol;

import com.epam.serdyukov.ispmanager.controller.Path;
import com.epam.serdyukov.ispmanager.model.entity.User;
import com.epam.serdyukov.ispmanager.controller.command.ICommand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.jstl.core.Config;

/**
 * @author Aleksey Serdyukov
 */
public class I18NCommand implements ICommand {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();

        String fmtLocale = "javax.servlet.jsp.jstl.fmt.locale";
        String defaultLocale = "defaultLocale";

        if (request.getParameter("ru") != null) {
            Config.set(session, fmtLocale, Path.LOCALE_NAME_RU);
            session.setAttribute(defaultLocale, "ru");

        } else {
            Config.set(session, fmtLocale, "en");
            session.setAttribute(defaultLocale, Path.LOCALE_NAME_EN);
        }

        User user = (User) session.getAttribute("fullUser");
        String url = (user.getRoleId()==1) ? "controller?action=users":"controller?action=account";
        return url;
    }
}