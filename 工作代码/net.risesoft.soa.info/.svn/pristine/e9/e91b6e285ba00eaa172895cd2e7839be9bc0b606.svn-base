package net.risesoft.soa.info.manager.service;

import net.risesoft.soa.info.dao.InformationDao;
import net.risesoft.soa.info.model.Information;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("informationManager")
public class InformationManager
{

  @Autowired
  public InformationDao informationDao;

  @Transactional
  public void save(Information information)
    throws Exception
  {
    try
    {
      if ((information.getListShowStyle() == null) || (information.getListShowStyle().trim().length() == 0)) {
        information.setListShowStyle("blog");
      }
      if ((information.getShowStyle() == null) || (information.getShowStyle().trim().length() == 0)) {
        information.setShowStyle("template");
      }
      this.informationDao.save(information);
    } catch (Exception e) {
      e.printStackTrace();
      throw new Exception("保存失败!");
    }
  }

  @Transactional
  public void delete(String id) throws Exception {
    this.informationDao.delete(id);
  }

  public Information get(String id) {
    return ((Information)this.informationDao.findOne(id));
  }
}