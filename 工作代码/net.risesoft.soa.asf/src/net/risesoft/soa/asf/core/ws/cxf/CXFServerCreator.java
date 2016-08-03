package net.risesoft.soa.asf.core.ws.cxf;

import net.risesoft.soa.asf.core.ws.AbstractServerCreator;
import net.risesoft.soa.asf.core.ws.WSServer;

import org.apache.cxf.frontend.ServerFactoryBean;

import com.lmd.interceptor.interceptor.AuthIntercetpr;

public class CXFServerCreator extends AbstractServerCreator
{
  public WSServer create()
  {
    ServerFactoryBean cxf = new ServerFactoryBean();
    ClassLoader original = Thread.currentThread().getContextClassLoader();
    WSServer server = null;
    try {
      Thread.currentThread().setContextClassLoader(CXFServerCreator.class.getClassLoader());
      cxf.setAddress(this.address);
      cxf.setServiceClass(this.serviceClass);
      cxf.setServiceBean(this.serviceObject);
      cxf.setBindingConfig(CXFUtils.getBindingConfiguration());
      cxf.setDataBinding(CXFUtils.getDataBinding());
      cxf.setBus(CXFUtils.getCurrentBus());

      cxf.getInInterceptors().add(new BasicAuthorizationInterceptor());
      cxf.getOutInterceptors().add(new BasicAuthorizationInterceptor());
      cxf.getInInterceptors().add(new AuthIntercetpr());
//      cxf.getInInterceptors().add(new LoggingInInterceptor());
//      cxf.getOutInterceptors().add(new LoggingOutInterceptor());
      
      cxf.setStart(false);
      server = new CXFServer(cxf.create());
    } catch (Exception ex) {
      this.log.warn("failed to create server: WebService[addr=" + this.address + ", svcClass=" + this.serviceClass + "]", ex);
    } finally {
      Thread.currentThread().setContextClassLoader(original);
    }
    return server;
  }
}