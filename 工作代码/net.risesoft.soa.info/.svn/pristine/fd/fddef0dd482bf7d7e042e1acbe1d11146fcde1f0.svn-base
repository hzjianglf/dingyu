package net.risesoft.soa.info.manager.util;

import egov.appservice.ac.exception.AccessManagerException;
import java.util.List;
import net.risesoft.soa.ac.manager.ResourceManager;
import net.risesoft.soa.ac.model.Resource;
import net.risesoft.soa.framework.session.SessionUser;
import net.risesoft.soa.info.dao.InformationDao;
import net.risesoft.soa.info.dao.InformationDescDao;
import net.risesoft.soa.info.model.Information;
import net.risesoft.soa.info.tools.InformationList;
import net.risesoft.soa.info.tools.SpringUtil;

public class ContainerUtil
{
  public Resource get(String id)
  {
    ResourceManager rm = (ResourceManager)SpringUtil.getBean("resourceManager");
    return rm.getResource(id);
  }

  public void save(Resource resource, String parentID, SessionUser su)
    throws AccessManagerException
  {
    if ("root".equalsIgnoreCase(parentID)) {
      parentID = null;
    }
    String id = resource.getId();
    ResourceManager rm = (ResourceManager)SpringUtil.getBean("resourceManager");
    if ((id == null) || (id.length() == 0)) {
      rm.create(resource, parentID);
    } else {
      Resource base = rm.getResource(id);
      String baseDN = base.getDn();
      String baseName = base.getName();
      String name = resource.getName();
      rm.update(resource);
      if (!(name.equals(baseName))) {
        resource = rm.getResource(id);
        String dn = resource.getDn();
        InformationDao informationDao = (InformationDao)SpringUtil.getBean("informationDao");
        List informations = informationDao.findByDN("%" + baseDN);
        for (int i = 0; i < informations.size(); ++i) {
          Information information = (Information)informations.get(i);
          String infoID = information.getId();
          information.setDn(dn);
          InformationList.get(infoID).setDn(dn);
        }
        informationDao.save(informations);
      }
    }
  }

  public void delete(String id, SessionUser su) throws AccessManagerException {
    ResourceManager rm = (ResourceManager)SpringUtil.getBean("resourceManager");
    Resource resource = rm.getResource(id);
    if (resource != null) {
      resource.getName();
      rm.delete(id);
      String dn = resource.getDn();
      InformationDao informationDao = (InformationDao)SpringUtil.getBean("informationDao");
      InformationDescDao informationDescDao = (InformationDescDao)SpringUtil.getBean("informationDescDao");
      List informations = informationDao.findByDN("%" + dn);
      for (int i = 0; i < informations.size(); ++i) {
        Information information = (Information)informations.get(i);
        String infoID = information.getId();
        List informationDescs = informationDescDao.findByInfoID(infoID);
        informationDescDao.delete(informationDescs);
      }

      informationDao.delete(informations);
    }
  }
}