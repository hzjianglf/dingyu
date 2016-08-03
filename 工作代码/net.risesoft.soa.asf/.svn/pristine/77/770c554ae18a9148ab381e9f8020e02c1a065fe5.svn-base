package net.risesoft.soa.asf.core.ws;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractServerCreator
  implements ServerCreator
{
  protected Logger log = LoggerFactory.getLogger(super.getClass());
  protected Class<?> serviceClass;
  protected Object serviceObject;
  protected String address;

  public void setAddress(String address)
  {
    this.address = address;
  }

  public void setServiceClass(Class<?> svcClass) {
    this.serviceClass = svcClass;
  }

  public void setServiceObject(Object obj) {
    this.serviceObject = obj;
  }
}