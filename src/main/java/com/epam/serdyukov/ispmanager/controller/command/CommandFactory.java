package com.epam.serdyukov.ispmanager.controller.command;


import com.epam.serdyukov.ispmanager.controller.command.admin.MainCommand;
import com.epam.serdyukov.ispmanager.controller.command.client.AccountCommand;
import com.epam.serdyukov.ispmanager.controller.command.outofcontrol.LoginCommand;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;


public class CommandFactory {
    private static CommandFactory factory = new CommandFactory();
    private static Map<String, ICommand> commands = new HashMap<>();

    private CommandFactory() {

    }

    public static CommandFactory commandFactory() {
        if (factory == null) {
            factory = new CommandFactory();
        }
        return factory;
    }

    static {
        // common commands
        commands.put("login", new LoginCommand());

        // admin commands
        commands.put("main", new MainCommand());

        // client commands
        commands.put("account", new AccountCommand());
         }

    public ICommand getCommand(HttpServletRequest request) {
        String action = request.getParameter("action");
        return commands.get(action);
    }
}
