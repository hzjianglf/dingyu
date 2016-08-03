package net.risesoft.soa.filecube.web.util;

import net.risesoft.soa.filecube.util.GlobalInfo;

public class GlobalResources
{
  public static GlobalInfo getResources()
  {
    GlobalInfo globalInfo = GlobalInfo.getInstance();

    globalInfo.startOpenOfficeService();
    try {
      Thread.sleep(1000L);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    return globalInfo;
  }
}