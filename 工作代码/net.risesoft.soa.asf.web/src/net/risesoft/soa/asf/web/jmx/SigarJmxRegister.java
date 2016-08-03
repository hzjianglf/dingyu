package net.risesoft.soa.asf.web.jmx;

import java.lang.management.ManagementFactory;

import javax.management.MBeanServer;
import javax.management.ObjectName;

import org.hyperic.sigar.jmx.SigarRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SigarJmxRegister
{
  private static final Logger log = LoggerFactory.getLogger(SigarJmxRegister.class);

  public void register() {
    MBeanServer server = ManagementFactory.getPlatformMBeanServer();
    SigarRegistry reg = new SigarRegistry();
    try {
      ObjectName objectName = ObjectName.getInstance(reg.getObjectName());
      if (server.isRegistered(objectName)) {
        server.unregisterMBean(objectName);
      }
      server.registerMBean(reg, objectName);
    } catch (Exception ex) {
      log.error("未能注册 Sigar JMX: " + ex.getMessage());
    }
  }
}