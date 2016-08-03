package net.risesoft.soa.asf.core.thirdparty;

import net.risesoft.soa.asf.core.AbstractServiceDefinition;
import net.risesoft.soa.asf.core.remote.RemoteServiceDefinition;
import net.risesoft.soa.asf.core.ws.cxf.CXFClientCreator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ThirdpartyServiceDefinition extends AbstractServiceDefinition
{
  private static final Logger log = LoggerFactory.getLogger(RemoteServiceDefinition.class);
  private String address;
  private String svcClassName;

  public ThirdpartyServiceDefinition(String serviceId, String svcClassName, String address)
  {
    this.serviceId = serviceId;
    this.svcClassName = svcClassName;
    this.address = address;
  }

  public Class<?> getServiceClass()
  {
    if (super.getServiceClass() == null) {
      loadServiceClass();
    }
    return this.serviceClass;
  }

  protected Object getServiceObjectInternal()
  {
    if (this.serviceObject == null) {
      CXFClientCreator creator = new CXFClientCreator();
      creator.setAddress(this.address);
      creator.setServiceClass(getServiceClass());
      this.serviceObject = creator.create();
    }
    return this.serviceObject;
  }

  private void loadServiceClass() {
    ClassLoader cl = Thread.currentThread().getContextClassLoader();
    try {
      this.serviceClass = ((cl != null) ? cl.loadClass(this.svcClassName) : null); } catch (ClassNotFoundException localClassNotFoundException1) {
    }
    if (this.serviceClass == null) try {
        cl = super.getClass().getClassLoader();
        this.serviceClass = cl.loadClass(this.svcClassName);
      } catch (ClassNotFoundException localClassNotFoundException2) {
        log.debug("can not found serviceClass: " + this.svcClassName);
      }
  }
}