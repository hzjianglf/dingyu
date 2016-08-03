package net.risesoft.soa.asf.core.ws;

import java.util.HashMap;
import java.util.Map;

public class WSServerHolder
{
  private static final Map<String, WSServer> webServices = new HashMap();

  public static void putServer(String serviceId, WSServer server)
  {
    synchronized (webServices) {
      if (!(webServices.containsKey(serviceId))) webServices.put(serviceId, server);
    }
  }

  public static WSServer getServer(String serviceId)
  {
    synchronized (webServices) {
      return ((WSServer)webServices.get(serviceId));
    }
  }

  public static void removeServer(String serviceId)
  {
    synchronized (webServices) {
      webServices.remove(serviceId);
    }
  }

  public static void clear()
  {
    synchronized (webServices) {
      for (WSServer server : webServices.values()) {
        server.shutdown();
      }
      webServices.clear();
    }
  }
}