package com.epam.serdyukov.ispmanager.controller;

import com.epam.serdyukov.ispmanager.controller.command.CommandFactory;
import com.epam.serdyukov.ispmanager.controller.command.ICommand;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Controller extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        proccesRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        proccesRequest(req, resp);
    }

    private void proccesRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CommandFactory commandFactory = CommandFactory.commandFactory();
        ICommand iCommand = commandFactory.getCommand(req);

        // extract command name from the request
        String page = iCommand.execute(req, resp);
        RequestDispatcher dispatcher = req.getRequestDispatcher(page);

        // if the forward address is not null go to the address
        if (!page.equals("redirect"))
            dispatcher.forward(req, resp);
    }
}