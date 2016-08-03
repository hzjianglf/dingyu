package sso.sdgt_views;

import com.itrus.cvm.CVM;
import java.io.File;
import java.io.PrintStream;
import java.net.URL;
import net.risesoft.soa.framework.service.config.Config;
import net.risesoft.soa.framework.service.config.EnvMode;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Platform;

public class CvmConfig
{
  private static boolean inited = false;

  public static synchronized void init() {
    if (!inited)
      try {
        String configPath = "";
        if (Config.envMode == EnvMode.Product) {
          configPath = Config.productConfigLocation + "ca/";
        } else {
          URL bundleRootURL = FileLocator.getBundleFile(Platform.getBundle("sso.sdgt_views")).toURL();
          configPath = bundleRootURL.getPath();
          if (configPath.startsWith("/")) configPath = configPath.substring(1);
          if (!configPath.endsWith("/")) configPath = configPath + '/';
          configPath = configPath + "webapp/WEB-INF/";
        }
        System.out.println("########## CVMConfigPath=" + configPath + " ##########");

        CVM.config(configPath + "cvm.xml");
        inited = true;
      } catch (Exception ex) {
        inited = false;
        System.err.println("CA-CVM 鍒濆鍖栧け璐� ERROR=" + ex.getMessage());
      }
  }
}