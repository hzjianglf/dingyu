package net.risesoft.soa.asf.web.helper;

import java.text.MessageFormat;

import org.springframework.stereotype.Component;

@Component
public class SystemHelper
{
  public String getJavaVM()
  {
    return System.getProperty("java.vm.name") + "(build " + System.getProperty("java.vm.version") + ", " + 
      System.getProperty("java.vm.info") + ")";
  }

  public String getUpTimeStr(long uptime)
  {
    long upTimeInSec = uptime / 1000L;
    long sec = upTimeInSec % 60L;
    long min = upTimeInSec / 60L % 60L;
    long hour = upTimeInSec / 60L / 60L;
    return MessageFormat.format("{0} 小时 {1} 分 {2} 秒", new Object[] { Long.valueOf(hour), Long.valueOf(min), Long.valueOf(sec) });
  }
}