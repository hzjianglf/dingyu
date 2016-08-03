package net.risesoft.soa.asf.core.local;

import egov.appservice.asf.model.ServiceComponent;
import net.risesoft.soa.asf.core.AbstractServiceDefinition;
import net.risesoft.soa.asf.core.ServiceMetaData;
import net.risesoft.soa.asf.core.ServiceProxyFactory;
import net.risesoft.soa.asf.core.util.ClassUtils;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.osgi.util.BundleDelegatingClassLoader;

public class LocalServiceDefinition extends AbstractServiceDefinition
{
  private static final Logger log = LoggerFactory.getLogger(LocalServiceDefinition.class);
  private Class<?> serviceImplementor;
  private Bundle bundle;
  private ServiceProxyFactory proxyFactory;

  public LocalServiceDefinition(String serviceId, Bundle bundle)
  {
    this.serviceId = serviceId;
    this.bundle = bundle;
    setClassLoader(BundleDelegatingClassLoader.createBundleClassLoaderFor(this.bundle));
  }

  public Class<?> getServiceClass()
  {
    if (this.serviceClass == null) {
      String serviceClassName = getMetaData().getComponent().getInterfaze();
      this.serviceClass = ClassUtils.loadClass(getClassLoader(), serviceClassName);
    }
    return this.serviceClass;
  }

  protected Object getServiceObjectInternal()
  {
    if (this.serviceImplementor == null) {
      String serviceImplementorName = getMetaData().getComponent().getImplementation();
      this.serviceImplementor = ClassUtils.loadClass(getClassLoader(), serviceImplementorName);
    }
    if (this.serviceObject != null) return this.serviceObject;
    try
    {
      Object obj = findObjectFromSpring();
      if (obj == null) {
        obj = findObjectFromOsgiService();
      }
      if (obj == null) {
        String factoryMethod = getMetaData().getComponent().getFactoryMethod();
        obj = ClassUtils.newInstance(this.serviceImplementor, factoryMethod);
      }
      if ((obj != null) && (this.proxyFactory != null)) {
        obj = this.proxyFactory.getProxy(getServiceClass(), obj, getParametersMap());
      }
      this.serviceObject = obj;
    } catch (Throwable ex) {
      log.warn("create instance error: ", ex);
    }
    return this.serviceObject;
  }

  void setServiceProxyFactory(ServiceProxyFactory proxyFactory) {
    this.proxyFactory = proxyFactory;
  }

  private Object findObjectFromSpring() {
    ServiceReference[] registedServiceRefs = this.bundle.getRegisteredServices();
    if (registedServiceRefs == null) return null;
    for (ServiceReference ref : registedServiceRefs) {
      Object tempObj = this.bundle.getBundleContext().getService(ref);
      if (tempObj instanceof ApplicationContext) {
        ApplicationContext ctx = (ApplicationContext)tempObj;
        try {
          Object obj = ctx.getBean(getServiceClass());
          if (obj != null) return obj; 
        } catch (NoSuchBeanDefinitionException localNoSuchBeanDefinitionException) {
        }
      }
    }
    return null;
  }

  private Object findObjectFromOsgiService() {
    ServiceReference ref = this.bundle.getBundleContext().getServiceReference(getMetaData()
      .getComponent()
      .getInterfaze());
    if (ref != null) {
      return this.bundle.getBundleContext().getService(ref);
    }
    return null;
  }
}