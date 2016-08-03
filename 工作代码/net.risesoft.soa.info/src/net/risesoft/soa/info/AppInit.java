package net.risesoft.soa.info;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class AppInit
  implements ApplicationContextAware, InitializingBean
{
  private ApplicationContext applicationContext;

  public void afterPropertiesSet()
    throws Exception
  {
    ApplicationContextHandler.initialize(this.applicationContext);
  }

  public void setApplicationContext(ApplicationContext applicationContext)
    throws BeansException
  {
    this.applicationContext = applicationContext;
  }
}