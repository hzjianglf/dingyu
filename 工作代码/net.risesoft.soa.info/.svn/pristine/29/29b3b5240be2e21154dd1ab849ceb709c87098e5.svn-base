package net.risesoft.soa.info.tools;

import java.util.List;
import net.risesoft.soa.info.dao.InformationDao;
import net.risesoft.soa.info.dao.InformationDescDao;
import net.risesoft.soa.info.model.Information;

public class InformationList
{
  public static Information get(String id)
  {
    InformationDao informationDao = (InformationDao)SpringUtil.getBean("informationDao");
    InformationDescDao informationDescDao = (InformationDescDao)SpringUtil.getBean("informationDescDao");
    Information information = (Information)informationDao.findOne(id);
    if (information != null) {
      List informationDescs = informationDescDao.findByInfoID(id);
      information.setElements(informationDescs);
    }

    return information;
  }
}