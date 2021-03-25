package com.epam.serdyukov.ispmanager.controller.command.admin;

import com.epam.serdyukov.ispmanager.controller.Path;
import com.epam.serdyukov.ispmanager.controller.command.ICommand;
import com.epam.serdyukov.ispmanager.model.services.ITariffService;
import com.epam.serdyukov.ispmanager.model.services.impl.TariffServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RemoveTariffCommand implements ICommand {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        ITariffService service = new TariffServiceImpl();
        int tariffId = Integer.parseInt(request.getParameter("tariff_id"));
        service.remove(tariffId);
        return Path.COMMAND_MAIN;
    }
}
