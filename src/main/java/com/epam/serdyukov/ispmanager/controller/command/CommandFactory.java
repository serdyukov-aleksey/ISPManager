package com.epam.serdyukov.ispmanager.controller.command;

import com.epam.serdyukov.ispmanager.controller.command.admin.AddTariffCommand;
import com.epam.serdyukov.ispmanager.controller.command.admin.EditClientCommand;
import com.epam.serdyukov.ispmanager.controller.command.admin.EditServicesCommand;
import com.epam.serdyukov.ispmanager.controller.command.admin.EditTariffCommand;
import com.epam.serdyukov.ispmanager.controller.command.admin.MainCommand;
import com.epam.serdyukov.ispmanager.controller.command.admin.ProfileCommand;
import com.epam.serdyukov.ispmanager.controller.command.admin.RegistrationCommand;
import com.epam.serdyukov.ispmanager.controller.command.admin.RemoveTariffCommand;
import com.epam.serdyukov.ispmanager.controller.command.admin.ShowUserListCommand;
import com.epam.serdyukov.ispmanager.controller.command.client.AccountCommand;
import com.epam.serdyukov.ispmanager.controller.command.client.PersonalDataCommand;
import com.epam.serdyukov.ispmanager.controller.command.client.SaveUserProfileCommand;
import com.epam.serdyukov.ispmanager.controller.command.client.TransactionCommand;
import com.epam.serdyukov.ispmanager.controller.command.client.UserProfileCommand;
import com.epam.serdyukov.ispmanager.controller.command.common.LogoutCommand;
import com.epam.serdyukov.ispmanager.controller.command.common.NoCommand;
import com.epam.serdyukov.ispmanager.controller.command.common.PdfBuilderCommand;
import com.epam.serdyukov.ispmanager.controller.command.outofcontrol.I18NCommand;
import com.epam.serdyukov.ispmanager.controller.command.outofcontrol.LoginCommand;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;


/**
 * Main Command factory for Controller.
 *
 * @author Aleksey Serdyukov
 */
public class CommandFactory {
  private static CommandFactory factory = new CommandFactory();
  private static Map<String, ICommand> commands = new HashMap<>();

  private CommandFactory() {}

  /**
     * Singleton.
     */
  public static CommandFactory commandFactory() {
    if (factory == null) {
      factory = new CommandFactory();
    }
    return factory;
  }

  static {
    // common commands
    commands.put("login", new LoginCommand());
    commands.put("logout", new LogoutCommand());
    commands.put("pdf_builder", new PdfBuilderCommand());
    commands.put("no_command", new NoCommand());
    commands.put("i18n", new I18NCommand());
    commands.put("redirect", null);

    // admin commands
    commands.put("main", new MainCommand());
    commands.put("users", new ShowUserListCommand());
    commands.put("services", new EditServicesCommand());
    commands.put("registration", new RegistrationCommand());
    commands.put("edit_client", new EditClientCommand());
    commands.put("profile", new ProfileCommand());
    commands.put("add_tariff", new AddTariffCommand());
    commands.put("edit_tariff", new EditTariffCommand());
    commands.put("remove_tariff", new RemoveTariffCommand());

    // client commands
    commands.put("account", new AccountCommand());
    commands.put("personal_data", new PersonalDataCommand());
    commands.put("user_profile", new UserProfileCommand());
    commands.put("transactions", new TransactionCommand());
    commands.put("save_profile", new SaveUserProfileCommand());
  }

  public ICommand getCommand(HttpServletRequest request) {
    String action = request.getParameter("action");
    return commands.get(action);
  }
}
