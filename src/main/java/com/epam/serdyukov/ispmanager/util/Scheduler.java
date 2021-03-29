package com.epam.serdyukov.ispmanager.util;

import com.epam.serdyukov.ispmanager.appcontext.AppContext;
import com.epam.serdyukov.ispmanager.model.service.ITransactionService;
import org.apache.log4j.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

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

  public class DailyTask implements Runnable {

    @Override
    public void run() {
      transactionService.saveDailyDebtsByAllUsers();
      transactionService.recalcBalanceAndBlockByAllUsers();
      log.info("Daily user debts completed by schedulers");
    }

  }

}