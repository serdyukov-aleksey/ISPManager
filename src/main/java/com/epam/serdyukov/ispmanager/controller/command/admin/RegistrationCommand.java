package com.epam.serdyukov.ispmanager.controller.command.admin;

import com.epam.serdyukov.ispmanager.controller.Path;
import com.epam.serdyukov.ispmanager.controller.command.ICommand;
import com.epam.serdyukov.ispmanager.model.entity.Account;
import com.epam.serdyukov.ispmanager.model.entity.ContactDetails;
import com.epam.serdyukov.ispmanager.model.entity.Tariff;
import com.epam.serdyukov.ispmanager.model.entity.User;
import com.epam.serdyukov.ispmanager.model.services.*;
import com.epam.serdyukov.ispmanager.model.services.impl.AccountServiceImpl;
import com.epam.serdyukov.ispmanager.model.services.impl.UserDetailsServiceImpl;
import com.epam.serdyukov.ispmanager.model.services.impl.TariffServiceImpl;
import com.epam.serdyukov.ispmanager.model.services.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class RegistrationCommand implements ICommand {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String firstName = request.getParameter("firstName").trim();
        String lastName = request.getParameter("lastName").trim();
//        String surname = request.getParameter("surname").trim();

        String city = request.getParameter("city").trim();
        String street = request.getParameter("street").trim();
        String building = request.getParameter("building").trim();
        String apartment = request.getParameter("apartment").trim();
        String email = request.getParameter("email").trim();
        String phone = request.getParameter("phone").trim();
        String login = request.getParameter("login").trim();

        String[] trafficsId = request.getParameterValues("arrTrafficsId");

        IUserService userService = new UserServiceImpl();
        IUserDetailsService detailsService = new UserDetailsServiceImpl();
        IAccountService accountService = new AccountServiceImpl();
        ITariffService tariffService = new TariffServiceImpl();

        ContactDetails details = new ContactDetails();
        details.setFirstName(firstName);
        details.setLastName(lastName);
        details.setCity(city);
        details.setStreet(street);
        details.setBuilding(building);
        details.setFlat(apartment);
        details.setEmail(email);
        details.setPhone(phone);
        detailsService.save(details);

        Account account = new Account();
        account.setNumber(accountService.getNumberContract());
        account.setBalance(0);
        accountService.save(account);

        Set<Tariff> tariffs;
        if (trafficsId != null) {
            tariffs = new HashSet<>();
            for (String item : trafficsId) {
                tariffs.add(tariffService.find(Long.parseLong(item)));
            }
        } else {
            tariffs = Collections.emptySet();
        }

        User newUser = new User();
        newUser.setLogin(login);
        newUser.setPassword("str0ngpwd");  //TODO change for generated value
        newUser.setRoleId(2);
        newUser.setBlocked(true);
        newUser.setDetails(details);
        newUser.setAccount(account);
        newUser.setTariffs(tariffs);
        userService.save(newUser);

        if (trafficsId != null) {
            userService.saveLinksUsersHasTariffs(newUser, trafficsId);
        }

        HttpSession session = request.getSession();
        session.setAttribute("newUser", newUser);

        String resp = Path.COMMAND_PROFILE;
        try {
            response.sendRedirect(resp);
            resp = Path.COMMAND_REDIRECT;
        } catch (IOException e) {
            resp = Path.PAGE_ERROR_PAGE;
        }
        return resp;
    }
}
