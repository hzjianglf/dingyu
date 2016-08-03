package net.risesoft.soa.info.service;

import java.util.List;
import net.risesoft.soa.info.dao.InformationIndexDao;
import net.risesoft.soa.info.model.InformationIndex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("informationIndexManager")
public class InformationIndexManager
{

  @Autowired
  public InformationIndexDao informationIndexDao;

  @Transactional
  public void save(InformationIndex informationIndex)
    throws Exception
  {
    try
    {
      this.informationIndexDao.save(informationIndex);
    } catch (Exception e) {
      e.printStackTrace();
      throw new Exception("保存失败!");
    }
  }

  public InformationIndex get(String id) {
    return ((InformationIndex)this.informationIndexDao.findOne(id));
  }

  public List<InformationIndex> findByInstance(String infoID, String instance) {
    return this.informationIndexDao.findByInstance(infoID, instance);
  }
}