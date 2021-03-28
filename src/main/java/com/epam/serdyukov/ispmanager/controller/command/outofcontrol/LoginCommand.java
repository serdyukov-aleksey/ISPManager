package com.epam.serdyukov.ispmanager.controller.command.outofcontrol;

import com.epam.serdyukov.ispmanager.appcontext.AppContext;
import com.epam.serdyukov.ispmanager.controller.Path;
import com.epam.serdyukov.ispmanager.model.entity.Role;
import com.epam.serdyukov.ispmanager.model.entity.User;
import com.epam.serdyukov.ispmanager.model.service.IUserService;
import com.epam.serdyukov.ispmanager.model.service.impl.UserServiceImpl;
import com.epam.serdyukov.ispmanager.controller.command.ICommand;
import org.mindrot.jbcrypt.BCrypt;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author Aleksey Serdyukov
 */
public class LoginCommand implements ICommand {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();

        String login = request.getParameter("login");
        String password = request.getParameter("password");

        // error handler
        String errorMessage;
        String forward = Path.PAGE_LOGIN;

        if (login == null || password == null || login.isEmpty() || password.isEmpty()) {
            errorMessage = "Login or password can't be empty";
            request.setAttribute("errorMessage", errorMessage);
            return forward;
        }

        IUserService service =  AppContext.getInstance().getUserService();
        User user = service.findByLogin(login);

        if (user == null || !BCrypt.checkpw(password, user.getPassword())) {
            errorMessage = "Cannot find user with such login or password";
            request.setAttribute("errorMessage", errorMessage);
            return forward;
        } else {
            Role userRole = Role.getRole(user);

            if(userRole == Role.ADMIN){
                forward = Path.COMMAND_SHOW_USERS;
            }

            if (userRole == Role.CLIENT){
                forward = Path.COMMAND_ACCOUNT;
            }

            session.setAttribute("user", user);
            session.setAttribute("userRole", userRole);
        }
        return forward;
    }
}
