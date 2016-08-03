package net.risesoft.soa.asf.core.thirdparty;

import java.util.List;
import net.risesoft.soa.asf.core.ServiceMetaData;
import net.risesoft.soa.asf.core.ServiceProcessor;
import net.risesoft.soa.asf.core.ServiceRepository;
import net.risesoft.soa.asf.core.ServiceRepositoryHolder;
import net.risesoft.soa.asf.dao.ServiceComponent2Dao;
import net.risesoft.soa.asf.dao.ServiceModule2Dao;
import net.risesoft.soa.asf.model.ServiceComponent2;
import net.risesoft.soa.asf.model.ServiceModule2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class ThirdpartyServiceProcessor
  implements ServiceProcessor
{
  private static final Logger log = LoggerFactory.getLogger(ThirdpartyServiceProcessor.class);
  private ServiceRepository serviceRepository;

  @Autowired
  private ServiceModule2Dao module2Dao;

  @Autowired
  private ServiceComponent2Dao component2Dao;

  public void destroy()
  {
    ServiceRepository sr = ServiceRepositoryHolder.removeRepository("asf-external-repository");
    if (sr == null) return; sr.clear();
  }

  public void refresh() {
    if (ServiceRepositoryHolder.getRepository("asf-external-repository") == null) {
      ServiceRepositoryHolder.putRepository(new ThirdpartyServiceRepository());
    }
    this.serviceRepository = ServiceRepositoryHolder.getRepository("asf-external-repository");
    this.serviceRepository.clear();
    ServiceModule2Dao dao = this.module2Dao;
    List moduleList = dao.findAll();
    for (ServiceModule2 module : moduleList)
      processDefinition(module);
  }

  private void processDefinition(ServiceModule2 module)
  {
    try {
      ServiceComponent2Dao dao = this.component2Dao;
      List compList = dao.findAll();
      String moduleId = module.getId();
      for (ServiceComponent2 c : compList)
        if (c.getModuleId().equals(moduleId)) {
          String serviceId = c.getId();
          String svcClassName = c.getInterfaze();
          String wsdl = c.getWsdl();
          ThirdpartyServiceDefinition serviceDefinition = new ThirdpartyServiceDefinition(serviceId, 
            svcClassName, 
            wsdl);
          serviceDefinition.setMetaData(new ServiceMetaData(module, c));
          this.serviceRepository.addServiceDefinition(serviceId, serviceDefinition);
          c.setId(serviceId);
          c.setCategory(module.getId());
        }
    }
    catch (Throwable ex) {
      log.error("parse error: [" + module + "]", ex);
    }
  }
}