package net.risesoft.soa.info;

import org.springframework.context.ApplicationContext;

public class ApplicationContextHandler
{
  private static ApplicationContext appContext = null;

  public static void initialize(ApplicationContext parent) {
    appContext = parent;
  }

  public static ApplicationContext getAppContext() {
    return appContext;
  }
}