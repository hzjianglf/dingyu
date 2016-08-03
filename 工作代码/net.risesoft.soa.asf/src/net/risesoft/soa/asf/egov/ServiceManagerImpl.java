package net.risesoft.soa.asf.egov;

import egov.appservice.asf.exception.ServiceManagerException;
import egov.appservice.asf.model.ServiceLog;
import egov.appservice.asf.model.ServiceState;
import egov.appservice.asf.service.ServiceManager;

public class ServiceManagerImpl
  implements ServiceManager
{
  public void changeState(String uid, ServiceState state)
    throws ServiceManagerException
  {
  }

  public ServiceLog getLog(String uid)
    throws ServiceManagerException
  {
    return null;
  }

  public ServiceState getState(String uid) throws ServiceManagerException {
    return null;
  }

  public String[] search(String whereCase) throws ServiceManagerException {
    return null;
  }
}