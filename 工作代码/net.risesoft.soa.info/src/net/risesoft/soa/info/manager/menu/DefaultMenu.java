package net.risesoft.soa.info.manager.menu;

import java.io.StringWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import net.risesoft.soa.framework.session.SessionUser;
import net.risesoft.soa.framework.util.json.JSONException;
import net.risesoft.soa.framework.util.json.JSONWriter;
import net.risesoft.soa.info.manager.MenuItem;

public class DefaultMenu extends AbstractMenu
{
  public DefaultMenu(HttpServletRequest request)
  {
    this.su = ((SessionUser)request.getSession().getAttribute("session.User"));
    super.getAdminType();
  }

  public String getMenuJson() {
    StringWriter sw = new StringWriter();
    if (this.su != null) {
      try {
        JSONWriter json = new JSONWriter(sw);
        json.array();
        MenuItem mi = new MenuItem(json, sw);
        if (this.adminType.equals("Admin")) {
          mi.addMenuItem("新增", "add-icon", new String[][] { { "", "资源", "", "folder-page-icon", "createInfoContainer" } });
        }
        json.endArray();
      } catch (JSONException e) {
        e.printStackTrace();
      }
    }
    return sw.toString();
  }
}