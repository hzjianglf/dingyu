package lmd.extend.wso.util;

import org.osgi.framework.Bundle;
import org.springframework.stereotype.Component;

@Component
public class BundleHelper 
{
  
	

  public static boolean matches(Bundle b, String filter) {
    String symName = b.getSymbolicName();
    String name = (String)b.getHeaders().get("Bundle-Name");
    return ((symName.contains(filter)) || ((name != null) && (name.contains(filter))));
  }

  public static String toStateString(int bundleState) {
    switch (bundleState)
    {
    case 2:
      return "部署成功";
    case 4:
      return "部署成功";
    case 8:
      return "部署成功";
    case 32:
      return "部署成功";
    case 16:
      return "未部署";
    case 1:
      return "未部署";
    }
    return "未知状态: " + bundleState;
  }

}