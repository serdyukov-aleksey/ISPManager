package com.epam.serdyukov.ispmanager.appcontext;

import com.epam.serdyukov.ispmanager.model.repository.IAccountRepo;
import com.epam.serdyukov.ispmanager.model.repository.ITariffRepo;
import com.epam.serdyukov.ispmanager.model.repository.IUserRepo;
import com.epam.serdyukov.ispmanager.model.repository.impl.AccountRepoImpl;
import com.epam.serdyukov.ispmanager.model.repository.impl.TariffRepoImpl;
import com.epam.serdyukov.ispmanager.model.repository.impl.UserRepoImpl;
import com.epam.serdyukov.ispmanager.model.service.IAccountService;
import com.epam.serdyukov.ispmanager.model.service.IContactDetailsService;
import com.epam.serdyukov.ispmanager.model.service.ITariffService;
import com.epam.serdyukov.ispmanager.model.service.IUserService;
import com.epam.serdyukov.ispmanager.model.service.impl.AccountServiceImpl;
import com.epam.serdyukov.ispmanager.model.service.impl.ContactDetailsServiceImpl;
import com.epam.serdyukov.ispmanager.model.service.impl.TariffServiceImpl;
import com.epam.serdyukov.ispmanager.model.service.impl.UserServiceImpl;

public class AppContext {
  private static volatile AppContext appContext = new AppContext();
  private final ITariffRepo tariffRepo = new TariffRepoImpl();
  private final ITariffService tariffService = new TariffServiceImpl(tariffRepo);
  private final IUserRepo userRepo = new UserRepoImpl();
  private final IContactDetailsService detailsService = new ContactDetailsServiceImpl();
  private final IAccountRepo accountRepo = new AccountRepoImpl();
  private final IAccountService accountService = new AccountServiceImpl(accountRepo);
  private final IUserService userService = new UserServiceImpl(userRepo, detailsService, accountService);

  public static AppContext getInstance() {
    return appContext;
  }

  public ITariffService getTariffService() {
    return tariffService;
  }
  public IUserService getUserService(){
    return userService;
  }
  public IContactDetailsService getDetailsService(){
    return detailsService;
  }
  public IAccountService getAccountService(){
    return accountService;
  }
}
