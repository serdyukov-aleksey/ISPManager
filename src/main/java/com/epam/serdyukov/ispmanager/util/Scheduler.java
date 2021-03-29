package com.epam.serdyukov.ispmanager.util;

import com.epam.serdyukov.ispmanager.appcontext.AppContext;
import com.epam.serdyukov.ispmanager.model.service.ITransactionService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import org.apache.log4j.Logger;

/**
 * Scheduler for save daily debts.
 *
 * @author Aleksey Serdyukov.
 */
@WebListener
public class Scheduler implements ServletContextListener {
  private static final Logger log = Logger.getLogger(Scheduler.class);
  private ScheduledExecutorService scheduler;
  private ITransactionService transactionService = AppContext.getInstance().getTransactionService();

  @Override
  public void contextInitialized(ServletContextEvent event) {
    scheduler = Executors.newSingleThreadScheduledExecutor();
    scheduler.scheduleAtFixedRate(new DailyTask(), 0, 1, TimeUnit.DAYS);
  }

  @Override
  public void contextDestroyed(ServletContextEvent event) {
    scheduler.shutdownNow();
  }

  /**
   * Runnable class for task.
   */
  public class DailyTask implements Runnable {

    @Override
    public void run() {
//      transactionService.saveDailyDebtsByAllUsers();
//      transactionService.recalcBalanceAndBlockByAllUsers();
      log.info("Daily user debts completed by schedulers");
    }

  }

}