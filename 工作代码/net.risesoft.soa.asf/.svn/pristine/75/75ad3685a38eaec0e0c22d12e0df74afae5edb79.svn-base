package net.risesoft.soa.asf;

import egov.appservice.asf.service.ServiceClientFactory;
import javax.persistence.Transient;
import net.risesoft.soa.asf.core.ServiceProcessor;
import net.risesoft.soa.asf.core.WebConsole;
import net.risesoft.soa.asf.core.thirdparty.ThirdpartyServiceProcessor;
import net.risesoft.soa.asf.egov.ServiceClientImpl;
import org.osgi.framework.BundleContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ASFActivator
{
  private static Logger log = LoggerFactory.getLogger(ASFActivator.class);
  private BundleContext bundleContext;
  private ServiceProcessor localServiceProcessor;

  @Transient
  private WebConsole webConsole;

  private void processLocalServices()
  {
    log.info("starting local service processor...");

    this.localServiceProcessor.refresh();
    log.info("started local service processor.");
  }

  private void processThirdPartyServices() {
    log.info("starting thirdparty service processor.");
    ThirdpartyServiceProcessor thirdpartyServiceProcessor = new ThirdpartyServiceProcessor();
    thirdpartyServiceProcessor.refresh();
    log.info("started local service processor.");
  }

  private void registerServiceClientImplementor(Class<ServiceClientImpl> clazz) {
    ServiceClientFactory.registerImplementor(clazz);
  }

  private void startWebConsole() {
    log.info("starting asf web console...");
    this.webConsole = new WebConsole(this.bundleContext);
    this.webConsole.start();
    log.info("started asf web console.");
  }
}