package com.epam.serdyukov.ispmanager.util;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@WebListener
public class Scheduler implements ServletContextListener {

  private ScheduledExecutorService scheduler;

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
      // Do your daily job here.
    }

  }

}
