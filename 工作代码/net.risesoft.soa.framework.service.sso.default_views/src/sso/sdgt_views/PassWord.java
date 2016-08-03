package sso.sdgt_views;

import egov.appservice.asf.exception.ServiceClientException;
import egov.appservice.asf.service.ServiceClient;
import egov.appservice.asf.service.ServiceClientFactory;
import egov.appservice.org.exception.OrgUnitManageException;
import egov.appservice.org.model.Person;
import egov.appservice.org.service.PersonManager;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import net.risesoft.soa.framework.util.Digest;

public class PassWord
{
  public static boolean changepwd(String loginname, String newpassword)
    throws OrgUnitManageException
  {
    boolean result = true;
    ServiceClient sc = ServiceClientFactory.getServiceClient();

    String orguid = "";
    try {
      PersonManager pm = (PersonManager)sc.getServiceByName("org.PersonManager");
      List<Person> p = pm.search(" loginname='" + loginname + "'");
      for (Person p1 : p) {
        orguid = p1.getUID();
      }
      Person p1 = pm.getPerson(orguid);
      p1.setPassword(Digest.SHA1(newpassword));
      pm.update(p1, false);
      result = true;
    }
    catch (ServiceClientException e) {
      result = false;
      e.printStackTrace();
    }
    catch (NoSuchAlgorithmException e) {
      result = false;
      e.printStackTrace();
    }
    return result;
  }
}