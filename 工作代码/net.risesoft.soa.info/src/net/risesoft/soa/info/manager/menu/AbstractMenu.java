package net.risesoft.soa.info.manager.menu;

import java.util.List;
import net.risesoft.soa.ac.manager.DomainManager;
import net.risesoft.soa.ac.util.AdminType;
import net.risesoft.soa.framework.session.SessionUser;
import net.risesoft.soa.info.tools.SpringUtil;

public abstract class AbstractMenu
  implements Menu
{
  public SessionUser su;
  public String adminType;

  public String getAdminType()
  {
    String uid = this.su.getUserUID();
    this.adminType = AdminType.getAdminType(uid);
    if (this.adminType != null) {
      return this.adminType;
    }
    this.adminType = "";
    try
    {
      DomainManager dm = (DomainManager)SpringUtil.getBean("domainManager");
      List domains = dm.getDomains(uid);
      if (domains.size() > 0)
        this.adminType = "Domain";
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    return this.adminType;
  }
}