package net.risesoft.soa.asf.web.jmx;

import java.lang.management.ManagementFactory;
import java.util.Hashtable;

import javax.management.MBeanServer;
import javax.management.ObjectName;

import org.hibernate.SessionFactory;
import org.hibernate.jmx.StatisticsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class HibernateJmxRegister
{
  private static final Logger log = LoggerFactory.getLogger(HibernateJmxRegister.class);

  @Autowired
  private SessionFactory sessionFactory;

  public void register()
  {
    Hashtable tb = new Hashtable();
    tb.put("type", "statistics");
    tb.put("sessionFactory", "RC7");
    try {
      ObjectName on = new ObjectName("hibernate", tb);
      StatisticsService stats = new StatisticsService();
      stats.setSessionFactory(this.sessionFactory);
      MBeanServer server = ManagementFactory.getPlatformMBeanServer();
      if (server.isRegistered(on)) {
        server.unregisterMBean(on);
      }
      server.registerMBean(stats, on);
    } catch (Exception ex) {
      log.error("未能注册 Hibernate JMX: " + ex.getMessage());
    }
  }
}