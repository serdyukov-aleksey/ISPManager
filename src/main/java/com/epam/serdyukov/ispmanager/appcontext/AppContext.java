package com.epam.serdyukov.ispmanager.appcontext;

import com.epam.serdyukov.ispmanager.model.repository.IAccountRepo;
import com.epam.serdyukov.ispmanager.model.repository.IContactDetailsRepo;
import com.epam.serdyukov.ispmanager.model.repository.IServiceRepo;
import com.epam.serdyukov.ispmanager.model.repository.ITariffRepo;
import com.epam.serdyukov.ispmanager.model.repository.ITransactionRepo;
import com.epam.serdyukov.ispmanager.model.repository.IUserRepo;
import com.epam.serdyukov.ispmanager.model.repository.impl.AccountRepoImpl;
import com.epam.serdyukov.ispmanager.model.repository.impl.ContactDetailsRepoImpl;
import com.epam.serdyukov.ispmanager.model.repository.impl.ServiceRepoImpl;
import com.epam.serdyukov.ispmanager.model.repository.impl.TariffRepoImpl;
import com.epam.serdyukov.ispmanager.model.repository.impl.TransactionRepoImpl;
import com.epam.serdyukov.ispmanager.model.repository.impl.UserRepoImpl;
import com.epam.serdyukov.ispmanager.model.service.IAccountService;
import com.epam.serdyukov.ispmanager.model.service.IContactDetailsService;
import com.epam.serdyukov.ispmanager.model.service.IPackageService;
import com.epam.serdyukov.ispmanager.model.service.ITariffService;
import com.epam.serdyukov.ispmanager.model.service.ITransactionService;
import com.epam.serdyukov.ispmanager.model.service.IUserService;
import com.epam.serdyukov.ispmanager.model.service.impl.AccountServiceImpl;
import com.epam.serdyukov.ispmanager.model.service.impl.ContactDetailsServiceImpl;
import com.epam.serdyukov.ispmanager.model.service.impl.PackageServiceImpl;
import com.epam.serdyukov.ispmanager.model.service.impl.TariffServiceImpl;
import com.epam.serdyukov.ispmanager.model.service.impl.TransactionServiceImpl;
import com.epam.serdyukov.ispmanager.model.service.impl.UserServiceImpl;

/**
 * Class creates all repositories and services on app starts.
 *
 * @author  Aleksey Serdyukov.
 */
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
  private final IContactDetailsService detailsService =
      new ContactDetailsServiceImpl(contactDetailsRepo);
  private final IAccountService accountService = new AccountServiceImpl(accountRepo);
  private final IUserService userService = new UserServiceImpl(userRepo,
      detailsService, accountService);
  private final IContactDetailsService contactDetailsService =
      new ContactDetailsServiceImpl(contactDetailsRepo);
  private final IPackageService packageService = new PackageServiceImpl(packageServiceRepo);
  private final ITransactionService transactionService = new TransactionServiceImpl(transactionRepo,
      accountService, userService);

  public static AppContext getInstance() {
    return appContext;
  }

  public ITariffService getTariffService() {
    return tariffService;
  }

  public IUserService getUserService() {
    return userService;
  }

  public IContactDetailsService getDetailsService() {
    return detailsService;
  }

  public IAccountService getAccountService() {
    return accountService;
  }

  public IContactDetailsService getContactDetailsService() {
    return contactDetailsService;
  }

  public IPackageService getPackageService() {
    return packageService;
  }

  public ITransactionService getTransactionService() {
    return transactionService;
  }
}
