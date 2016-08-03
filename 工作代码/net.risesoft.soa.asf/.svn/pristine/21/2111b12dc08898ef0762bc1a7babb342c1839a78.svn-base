package net.risesoft.soa.asf.egov;

import egov.appservice.asf.exception.ServiceModuleManagerException;
import egov.appservice.asf.service.ServiceModuleManager;
import java.util.List;
import net.risesoft.soa.asf.dao.ServiceModule2Dao;
import net.risesoft.soa.asf.dao.ServiceModuleDao;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

@Component("egov.ServiceModuleManager")
public class ServiceModuleManagerImpl
  implements ServiceModuleManager, ApplicationContextAware
{

  @Autowired
  private ServiceModuleDao moduleDao;

  @Autowired
  private ServiceModule2Dao module2Dao;
  private static ApplicationContext appContext;

  public static ServiceModuleManager getInstance()
  {
    return ((ApplicationContextAware)appContext.getBean("egov.ServiceModuleManager"));
  }

  public net.risesoft.soa.asf.model.ServiceModule create(String id, String name, String version) throws ServiceModuleManagerException {
    Assert.hasText(id);
    Assert.hasText(name);
    Assert.hasText(version);
    net.risesoft.soa.asf.model.ServiceModule module = new net.risesoft.soa.asf.model.ServiceModule();
    module.setId(id);
    module.setName(name);
    module.setVersion(version);
    this.moduleDao.save(module);
    return ((net.risesoft.soa.asf.model.ServiceModule)this.moduleDao.findOne(module.getId()));
  }

  public void delete(String moduleId) throws ServiceModuleManagerException {
    Assert.hasText(moduleId);
    this.moduleDao.delete(moduleId);
  }

  @Transactional(readOnly=true)
  public net.risesoft.soa.asf.model.ServiceModule get(String moduleId) throws ServiceModuleManagerException {
    Assert.hasText(moduleId);
    egov.appservice.asf.model.ServiceModule module = null;
    try {
      module = (egov.appservice.asf.model.ServiceModule)this.moduleDao.findOne(moduleId);
      module.getName();
    } catch (Exception localException1) {
      module = null;
    }
    if (module == null) {
      try {
        module = (egov.appservice.asf.model.ServiceModule)this.module2Dao.findOne(moduleId);
        module.getName();
      } catch (Exception localException2) {
        module = null;
      }
    }
    net.risesoft.soa.asf.model.ServiceModule m = new net.risesoft.soa.asf.model.ServiceModule();
    m.setId(module.getId());
    m.setDescription(module.getDescription());
    m.setName(module.getName());
    m.setVersion(module.getVersion());
    return m;
  }

  public String[] search(String whereCase) throws ServiceModuleManagerException {
    return search(whereCase);
  }

  public String[] search(String queryStr, Object[] values) throws ServiceModuleManagerException {
    Assert.notNull(queryStr);
    List modules = this.moduleDao.findAll();
    String[] results = new String[modules.size()];
    for (int i = 0; i < results.length; ++i) {
      results[i] = ((net.risesoft.soa.asf.model.ServiceModule)modules.get(i)).getId();
    }
    return results;
  }

  public net.risesoft.soa.asf.model.ServiceModule update(egov.appservice.asf.model.ServiceModule module) throws ServiceModuleManagerException {
    Assert.notNull(module);
    Assert.isInstanceOf(net.risesoft.soa.asf.model.ServiceModule.class, module);
    this.moduleDao.save((net.risesoft.soa.asf.model.ServiceModule)module);
    return ((net.risesoft.soa.asf.model.ServiceModule)this.moduleDao.findOne(module.getId()));
  }

  public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    appContext = applicationContext;
  }
}