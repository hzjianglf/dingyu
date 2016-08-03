package net.risesoft.soa.asf.core.external;

import net.risesoft.soa.asf.core.AbstractServiceDefinition;
import net.risesoft.soa.asf.core.remote.RemoteServiceDefinition;
import org.apache.cxf.BusFactory;
import org.apache.cxf.aegis.databinding.AegisDatabinding;
import org.apache.cxf.binding.soap.SoapBindingConfiguration;
import org.apache.cxf.frontend.ClientProxyFactoryBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExternalServiceDefinition extends AbstractServiceDefinition
{
  private static final Logger log = LoggerFactory.getLogger(RemoteServiceDefinition.class);
  private String address;
  private String svcClassName;

  public ExternalServiceDefinition(String serviceId, String svcClassName, String address)
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
    return super.getServiceClass();
  }

  protected Object getServiceObjectInternal()
  {
    if (this.serviceObject == null) {
      ClientProxyFactoryBean factory = new ClientProxyFactoryBean();
      factory.setBus(BusFactory.newInstance("org.apache.cxf.bus.CXFBusFactory").createBus());
      factory.setServiceClass(getServiceClass());
      factory.setDataBinding(new AegisDatabinding());
      factory.setBindingConfig(new SoapBindingConfiguration());
      factory.setAddress(this.address);
      this.serviceObject = factory.create();
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
      } catch (ClassNotFoundException ex1) {
        log.debug("can not found class", ex1);
      }
  }
}