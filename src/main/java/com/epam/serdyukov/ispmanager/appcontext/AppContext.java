package com.epam.serdyukov.ispmanager.appcontext;

import com.epam.serdyukov.ispmanager.model.repository.*;
import com.epam.serdyukov.ispmanager.model.repository.impl.*;
import com.epam.serdyukov.ispmanager.model.service.*;
import com.epam.serdyukov.ispmanager.model.service.impl.*;

public class AppContext {
  private static volatile AppContext appContext = new AppContext();
  //repos
  private final ITariffRepo tariffRepo = new TariffRepoImpl();
  private final IUserRepo userRepo = new UserRepoImpl();
  private final IAccountRepo accountRepo = new AccountRepoImpl();
  private final IContactDetailsRepo contactDetailsRepo = new ContactDetailsRepoImpl();
  private final IServiceRepo packageServiceRepo = new ServiceRepoImpl();
  private final ITransactionRepo transactionRepo = new TransactionRepoImpl();


  //services
  private final ITariffService tariffService = new TariffServiceImpl(tariffRepo);
  private final IContactDetailsService detailsService = new ContactDetailsServiceImpl(contactDetailsRepo);
  private final IAccountService accountService = new AccountServiceImpl(accountRepo);
  private final IUserService userService = new UserServiceImpl(userRepo, detailsService, accountService);
  private final IContactDetailsService contactDetailsService = new ContactDetailsServiceImpl(contactDetailsRepo);
  private final IPackageService packageService = new PackageServiceImpl(packageServiceRepo);
  private final ITransactionService transactionService = new TransactionServiceImpl(transactionRepo);

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
  public IContactDetailsService getContactDetailsService(){
    return contactDetailsService;
  }
  public IPackageService getPackageService(){
    return packageService;
  }
  public ITransactionService getTransactionService(){
    return transactionService;
  }
}
