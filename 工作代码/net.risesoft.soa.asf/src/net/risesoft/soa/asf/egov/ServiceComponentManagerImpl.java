package net.risesoft.soa.asf.egov;

import java.util.List;

import net.risesoft.soa.asf.dao.ServiceComponent2Dao;
import net.risesoft.soa.asf.dao.ServiceComponentDao;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import egov.appservice.asf.exception.ServiceComponentManagerException;
import egov.appservice.asf.model.ServiceComponent;
import egov.appservice.asf.service.ServiceComponentManager;
@Component("egov.ServiceComponentManager")
public class ServiceComponentManagerImpl
  implements ServiceComponentManager, ApplicationContextAware
{

  @Autowired
  private ServiceComponentDao compDao;

  @Autowired
  private ServiceComponent2Dao comp2Dao;
  private static ApplicationContext appContext;

  public static ServiceComponentManager getInstance()
  {
    return (ServiceComponentManager) ((ApplicationContextAware)appContext.getBean("egov.ServiceComponentManager"));
  }

  public void delete(String uid) throws ServiceComponentManagerException
  {
  }

  public ServiceComponent register(String moduleId,ServiceComponent component) throws ServiceComponentManagerException
  {
    return null;
  }

  public ServiceComponent update(ServiceComponent component) throws ServiceComponentManagerException {
    return null;
  }

  @Transactional(readOnly=true)
  public ServiceComponent get(String uid) throws ServiceComponentManagerException {
    ServiceComponent comp = null;
    try {
      comp = this.compDao.findOne(uid);
      comp.getName();
    } catch (Exception localException1) {
      comp = null;
    }
    if (comp == null) try {
        comp = this.comp2Dao.findOne(uid);
        comp.getName();
      } catch (Exception localException2) {
        comp = null;
      }
    return comp;
  }

  public String[] search(String whereCase) throws ServiceComponentManagerException {
    Assert.notNull(whereCase);
    List modules = this.compDao.findAll();
    String[] results = new String[modules.size()];
    for (int i = 0; i < results.length; ++i) {
      results[i] = ((ServiceComponent) modules.get(i)).getId();
    }
    return results;
  }

  public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    appContext = applicationContext;
  }
}