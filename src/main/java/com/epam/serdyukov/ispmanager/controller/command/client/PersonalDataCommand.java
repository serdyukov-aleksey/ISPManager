package com.epam.serdyukov.ispmanager.controller.command.client;

import com.epam.serdyukov.ispmanager.appcontext.AppContext;
import com.epam.serdyukov.ispmanager.controller.Path;
import com.epam.serdyukov.ispmanager.model.entity.Account;
import com.epam.serdyukov.ispmanager.model.entity.Tariff;
import com.epam.serdyukov.ispmanager.model.entity.User;
import com.epam.serdyukov.ispmanager.model.service.*;
import com.epam.serdyukov.ispmanager.controller.command.ICommand;
import com.epam.serdyukov.ispmanager.model.service.impl.AccountServiceImpl;
import com.epam.serdyukov.ispmanager.model.service.impl.TariffServiceImpl;
import com.epam.serdyukov.ispmanager.model.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;

/**
 * @author Aleksey Serdyukov
 */
public class PersonalDataCommand implements ICommand {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        User fullUser = (User) session.getAttribute("fullUser");

        IUserService userService =  AppContext.getInstance().getUserService();
        ITariffService tariffService = AppContext.getInstance().getTariffService();

        String forward = Path.COMMAND_ACCOUNT;

        if (request.getParameterValues("arrTrafficsId") != null) {
            String[] trafficsId = request.getParameterValues("arrTrafficsId");
            userService.saveLinksUsersHasTariffs(fullUser, trafficsId);
            List<Tariff> tariffs = tariffService.findAll();
            try {
                response.sendRedirect(forward);
            } catch (IOException e) {
                e.printStackTrace();
            }
            forward = Path.COMMAND_REDIRECT;
        }

        List<Tariff> internetTariffs = tariffService.findAllById(1);
        List<Tariff> iptvTariffs = tariffService.findAllById(2);
        List<Tariff> telephonyTariffs = tariffService.findAllById(3);

        request.setAttribute("internetTariffs", internetTariffs);
        request.setAttribute("iptvTariffs", iptvTariffs);
        request.setAttribute("telephonyTariffs", telephonyTariffs);

        // Sorting Internet tariffs
        if (request.getParameter("name_inet_asc") != null) {
            internetTariffs.sort(Comparator.comparing(Tariff::getName));
            forward = Path.PAGE_ACCOUNT;
        }
        if (request.getParameter("name_inet_desc") != null) {
            internetTariffs.sort((o1, o2) -> o2.getName().compareTo(o1.getName()));
            forward = Path.PAGE_ACCOUNT;
        }
        if (request.getParameter("price_inet_asc") != null) {
            internetTariffs.sort(Comparator.comparingDouble(Tariff::getPrice));
            forward = Path.PAGE_ACCOUNT;
        }
        if (request.getParameter("price_inet_desc") != null) {
            internetTariffs.sort((o1, o2) -> Double.compare(o2.getPrice(), o1.getPrice()));
            forward = Path.PAGE_ACCOUNT;
        }

        // Sorting TV tariffs
        if (request.getParameter("name_iptv_asc") != null) {
            iptvTariffs.sort(Comparator.comparing(Tariff::getName));
            forward = Path.PAGE_ACCOUNT;
        }
        if (request.getParameter("name_iptv_desc") != null) {
            iptvTariffs.sort((o1, o2) -> o2.getName().compareTo(o1.getName()));
            forward = Path.PAGE_ACCOUNT;
        }
        if (request.getParameter("price_iptv_asc") != null) {
            iptvTariffs.sort(Comparator.comparingDouble(Tariff::getPrice));
            forward = Path.PAGE_ACCOUNT;
        }
        if (request.getParameter("price_iptv_desc") != null) {
            iptvTariffs.sort((o1, o2) -> Double.compare(o2.getPrice(), o1.getPrice()));
            forward = Path.PAGE_ACCOUNT;
        }

        // Sorting Phone tariffs
        if (request.getParameter("name_phone_asc") != null) {
            telephonyTariffs.sort(Comparator.comparing(Tariff::getName));
            forward = Path.PAGE_ACCOUNT;
        }
        if (request.getParameter("name_phone_desc") != null) {
            telephonyTariffs.sort((o1, o2) -> o2.getName().compareTo(o1.getName()));
            forward = Path.PAGE_ACCOUNT;
        }
        if (request.getParameter("price_phone_asc") != null) {
            telephonyTariffs.sort(Comparator.comparingDouble(Tariff::getPrice));
            forward = Path.PAGE_ACCOUNT;
        }
        if (request.getParameter("price_phone_desc") != null) {
            telephonyTariffs.sort((o1, o2) -> Double.compare(o2.getPrice(), o1.getPrice()));
            forward = Path.PAGE_ACCOUNT;
        }

        return forward;
    }
}
