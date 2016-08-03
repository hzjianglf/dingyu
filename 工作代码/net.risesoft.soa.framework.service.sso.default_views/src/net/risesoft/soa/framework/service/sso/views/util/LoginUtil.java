package net.risesoft.soa.framework.service.sso.views.util;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import net.risesoft.soa.framework.service.sso.view.ViewUtil;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.ServiceReference;
import org.osgi.service.packageadmin.PackageAdmin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoginUtil
{
  private static final Logger log = LoggerFactory.getLogger(LoginUtil.class);
  private static final String bgPath = "/webapp/bg/";
  private static final Bundle bundle = FrameworkUtil.getBundle(ViewUtil.class);
  private static final String LF = "\r\n";

  public static final String getBgArrayScript()
  {
    try
    {
      String[] list = getImageList(bundle);
      StringBuffer sb = new StringBuffer();
      sb.append("var bgArray=new Array();\r\n");

      if (list != null) {
        for (int i = 0; i < list.length; i++) {
          sb.append("bgArray[" + i + "]=\"bg/" + list[i] + "\";" + "\r\n");
        }
      }
      return sb.toString();
    } catch (Exception e) {
      log.error("获取背景图发生异常", e);
    }return "//获取背景图发生异常";
  }

  private static String[] getImageList(Bundle bundle)
  {
    Bundle self = getSelfBundle(bundle);
    Enumeration paths = self.getEntryPaths("/webapp/bg/");
    List imageList = new ArrayList();
    while ((paths != null) && (paths.hasMoreElements())) {
      String p = (String)paths.nextElement();
      if (p.endsWith("/")) {
        continue;
      }
      if (!p.startsWith("/")) p = "/" + p;
      String image = p.substring("/webapp/bg/".length());
      String name = image.toLowerCase();
      if ((name.endsWith(".jpg")) || (name.endsWith(".jpeg")) || (name.endsWith(".png")) || (name.endsWith(".gif"))) {
        imageList.add(image);
      }
    }

    return (String[])imageList.toArray(new String[imageList.size()]);
  }

  private static Bundle getSelfBundle(Bundle bundle) {
    Bundle[] fragments = getFragments(bundle);
    for (Bundle f : fragments) {
      if (f.getSymbolicName().equals("net.risesoft.soa.framework.service.sso.default_views")) {
        return f;
      }
    }
    return bundle;
  }

  private static Bundle[] getFragments(Bundle bundle) {
    BundleContext context = bundle.getBundleContext();
    PackageAdmin packageAdmin = getPackageAdmin(context);
    return packageAdmin.getFragments(bundle);
  }

  private static PackageAdmin getPackageAdmin(BundleContext context) {
    ServiceReference reference = context.getServiceReference(PackageAdmin.class.getName());
    PackageAdmin packageAdmin = (PackageAdmin)context.getService(reference);
    return packageAdmin;
  }

  public static final String getCookieValue(HttpServletRequest request, String cookieName) {
    Cookie[] cookies = request.getCookies();
    if (cookies != null) {
      for (int i = 0; i < cookies.length; i++) {
        String name = cookies[i].getName();
        if ((name != null) && (name.equals(cookieName))) {
          return cookies[i].getValue();
        }
      }
    }
    return "";
  }
}