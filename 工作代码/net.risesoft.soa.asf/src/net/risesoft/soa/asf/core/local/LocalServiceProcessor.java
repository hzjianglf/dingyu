package net.risesoft.soa.asf.core.local;

import java.net.URL;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Iterator;
import java.util.List;
import net.risesoft.soa.asf.core.ServiceMetaData;
import net.risesoft.soa.asf.core.ServiceProcessor;
import net.risesoft.soa.asf.core.ServiceProxyFactory;
import net.risesoft.soa.asf.core.ServiceRepository;
import net.risesoft.soa.asf.core.ServiceRepositoryHolder;
import net.risesoft.soa.asf.core.parser.ASFDefinitionParser;
import net.risesoft.soa.asf.core.util.ASF;
import net.risesoft.soa.asf.core.util.FrameServerJCEProvicer;
import net.risesoft.soa.asf.dao.ServiceModuleDao;
import net.risesoft.soa.asf.model.RuntimeInfo;
import net.risesoft.soa.asf.model.ServiceModule;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleEvent;
import org.osgi.framework.ServiceEvent;
import org.osgi.framework.ServiceListener;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.SynchronousBundleListener;
import org.osgi.service.packageadmin.PackageAdmin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.osgi.context.BundleContextAware;

public class LocalServiceProcessor
  implements ServiceProcessor, SynchronousBundleListener, ServiceListener, BundleContextAware, InitializingBean
{
  private static final Logger log = LoggerFactory.getLogger(LocalServiceProcessor.class);
  private static final String ASF_HEADER = "Asf-Definition";
  private static final List<String> processedBundles = new ArrayList();
  private BundleContext bundleContext;

  @Autowired
  private ServiceModuleDao moduleDao;

  @Autowired
  private ServiceProxyFactory proxyFactory;
  private ServiceRepository serviceRepository;

  public void refresh()
  {
    Bundle[] bundles = this.bundleContext.getBundles();
    for (Bundle b : bundles)
      if (needToBeProcessedBundle(b))
        processServiceModuleBundle(b);
  }

  public void destroy()
  {
    this.bundleContext.removeServiceListener(this);
    this.bundleContext.removeBundleListener(this);
    this.serviceRepository.clear();
    processedBundles.clear();
  }

  public void serviceChanged(ServiceEvent e) {
    if (e.getType() == 1) {
      ServiceReference ref = e.getServiceReference();
      String flag = ref.getBundle().getSymbolicName() + ".ApplicationContext";
      if (flag.equals(ref.getProperty("service.pid")))
        refresh();
    }
  }

  public void bundleChanged(BundleEvent e)
  {
    Bundle b = e.getBundle();
    int eType = e.getType();
    if ((eType == 2) && (needToBeProcessedBundle(b))) {
      processServiceModuleBundle(b);
    } else if ((eType == 256) && (isServiceModuleBundle(b))) {
      processedBundles.remove(b.getLocation());
      String adp = b.getHeaders().get("Asf-Definition").toString();
      URL url = b.getResource(adp);

      ServiceModule module = null;
      try {
        module = ASFDefinitionParser.load(url.openStream());
        for (egov.appservice.asf.model.ServiceComponent c : module.getComponents()) {
          String serviceId = module.getId() + "." + c.getId();
          this.serviceRepository.removeServiceDefinition(serviceId);
        }
        this.moduleDao.delete(module.getId());
      } catch (Throwable ex) {
        log.error("uninstall error: ServiceModule[id=" + module.getId() + "]", ex);
      }
    }
  }

  private ServiceRepository createRepository() {
    ServiceRepository repo = ServiceRepositoryHolder.getRepository("asf-local-repository");
    if (repo == null) {
      repo = new LocalServiceRepository();
      ServiceRepositoryHolder.putRepository(repo);
    }
    return repo;
  }

  private void processServiceModuleBundle(Bundle b) {
    String adp = b.getHeaders().get("Asf-Definition").toString();
    log.debug("found asf definition file[" + adp + "] in bundle[" + b.getLocation() + "]");
    URL url = b.getResource(adp);

    ServiceModule module = null;
    try {
      module = ASFDefinitionParser.load(url.openStream());
    } catch (Throwable ex) {
      log.error("parse error: [" + b.getLocation() + adp + "]", ex);
      return;
    }

    for (Iterator iter = module.getComponents().iterator(); iter.hasNext(); ) {
      egov.appservice.asf.model.ServiceComponent c = (egov.appservice.asf.model.ServiceComponent)iter.next();

      String serviceId = module.getId() + "." + c.getId();
      LocalServiceDefinition serviceDefinition = new LocalServiceDefinition(serviceId, b);
      serviceDefinition.setMetaData(new ServiceMetaData(module, c));
      serviceDefinition.setServiceProxyFactory(this.proxyFactory);
      this.serviceRepository.addServiceDefinition(serviceId, serviceDefinition);

      String address = getWebServiceAddress(module, c);

      ((net.risesoft.soa.asf.model.ServiceComponent)c).setWsdl(address);
      ((net.risesoft.soa.asf.model.ServiceComponent)c).setId(serviceId);
      ((net.risesoft.soa.asf.model.ServiceComponent)c).setCategory(module.getId());
    }

    RuntimeInfo runtimeInfo = new RuntimeInfo();
    runtimeInfo.setHostId(ASF.HostId);
    module.setRuntimeInfo(runtimeInfo);
    this.moduleDao.save(module);

    processedBundles.add(b.getLocation());
  }

  private String getWebServiceAddress(ServiceModule m, egov.appservice.asf.model.ServiceComponent c) {
    return 
      '/' + 
      m.getId() + 
      '/' + 
      c.getId()
      .replace('.', '/');
  }

  private boolean needToBeProcessedBundle(Bundle b)
  {
    return ((isServiceModuleBundle(b)) && (unProcessedBundle(b)) && (isActive(b)));
  }

  private boolean isActive(Bundle b) {
    int state = b.getState();
    return (state == 32);
  }

  private boolean unProcessedBundle(Bundle b)
  {
    return (!(processedBundles.contains(b.getLocation())));
  }

  private boolean isServiceModuleBundle(Bundle b) {
    return (b.getHeaders().get("Asf-Definition") != null);
  }

  private void checkEnvironment()
  {
  }

  private boolean checkEnvironmentJar(ServiceReference sr) {
    Bundle serviceBundle = sr.getBundle();
    if (serviceBundle.getBundleId() == 0L) {
      ServiceReference ref = this.bundleContext.getServiceReference(PackageAdmin.class.getName());
      PackageAdmin packageAdmin = (PackageAdmin)this.bundleContext.getService(ref);
      Bundle[] fragments = packageAdmin.getFragments(serviceBundle);
      for (Bundle bundle : fragments) {
        String exportPackages = (String)bundle.getHeaders().get("Export-Package");
        if ((exportPackages != null) && (exportPackages.contains("frameserver.license"))) {
          return FrameServerJCEProvicer.isBundleJarSigned(bundle);
        }
      }
    }
    return false;
  }

  public void setBundleContext(BundleContext bundleContext) {
    this.bundleContext = bundleContext;
  }

  public void afterPropertiesSet() throws Exception {
    checkEnvironment();

    this.serviceRepository = createRepository();

    this.bundleContext.addBundleListener(this);
    this.bundleContext.addServiceListener(this);
  }
}