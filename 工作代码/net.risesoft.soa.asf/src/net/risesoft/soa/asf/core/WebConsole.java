package net.risesoft.soa.asf.core;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.service.http.HttpService;
import org.osgi.util.tracker.ServiceTracker;
import org.osgi.util.tracker.ServiceTrackerCustomizer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WebConsole
{
  private static final Logger log = LoggerFactory.getLogger(WebConsole.class);
  private static final String webServiceContext = "/services";
  private BundleContext bundleContext;
  private ServiceTracker httpServiceTracker;
  private HttpService httpService;
  private boolean started = false;

  public WebConsole(BundleContext context) {
    this.bundleContext = context;
  }

  public void start()
  {
    this.httpServiceTracker = new ServiceTracker(this.bundleContext, HttpService.class.getName(), null)
    {
      public Object addingService(ServiceReference reference) {
        WebConsole.this.httpService = ((HttpService)this.context.getService(reference));

        return WebConsole.this.httpService;
      }

      public void removedService(ServiceReference reference, Object service) {
        HttpService httpService = (HttpService)service;
        httpService.unregister("/services");
        WebConsole.this.started = false;
        super.removedService(reference, service);
      }
    };
    this.httpServiceTracker.open();
  }

  public void stop() {
    log.info("stopping asf web console...");
    this.httpServiceTracker.close();
    if ((this.httpService != null) && (this.started)) this.httpService.unregister("/services");
    log.info("stopped asf web console.");
  }
}