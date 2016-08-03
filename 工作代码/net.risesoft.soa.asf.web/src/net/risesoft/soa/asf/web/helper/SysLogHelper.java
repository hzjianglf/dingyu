package net.risesoft.soa.asf.web.helper;

import java.lang.management.ManagementFactory;
import java.util.Collections;
import java.util.List;

import javax.management.MBeanServer;
import javax.management.ObjectName;

import org.springframework.stereotype.Component;

@Component
public class SysLogHelper
{
  private static ObjectName LOGBACK_ONAME;

  static
  {
    try
    {
      LOGBACK_ONAME = ObjectName.getInstance("ch.qos.logback.classic:Name=default,Type=ch.qos.logback.classic.jmx.JMXConfigurator");
    }
    catch (Exception localException) {
    }
  }

  public List<String> getLoggerList() {
    MBeanServer svr = ManagementFactory.getPlatformMBeanServer();
    try {
      return ((List)svr.getAttribute(LOGBACK_ONAME, "LoggerList")); } catch (Exception localException) {
    }
    return Collections.EMPTY_LIST;
  }

  public String getLoggerEffectiveLevel(String logger)
  {
    MBeanServer svr = ManagementFactory.getPlatformMBeanServer();
    try {
      return ((String)svr.invoke(LOGBACK_ONAME, 
        "getLoggerEffectiveLevel", 
        new Object[] { logger }, 
        new String[] { String.class.getName() })); } catch (Exception localException) {
    }
    return "";
  }

  public String getLoggerLevel(String logger)
  {
    MBeanServer svr = ManagementFactory.getPlatformMBeanServer();
    try {
      return ((String)svr.invoke(LOGBACK_ONAME, 
        "getLoggerLevel", 
        new Object[] { logger }, 
        new String[] { String.class.getName() })); } catch (Exception localException) {
    }
    return "";
  }

  public void setLoggerLevel(String logger, String level)
  {
    MBeanServer svr = ManagementFactory.getPlatformMBeanServer();
    try {
      svr.invoke(LOGBACK_ONAME, 
        "setLoggerLevel", 
        new Object[] { logger, level }, 
        new String[] { String.class.getName(), String.class.getName() });
    }
    catch (Exception localException)
    {
    }
  }
}