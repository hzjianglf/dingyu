package net.risesoft.soa.asf.core.ws.cxf;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.risesoft.soa.asf.core.ServiceDefinition;
import net.risesoft.soa.asf.core.ServiceRepository;
import net.risesoft.soa.asf.core.ServiceRepositoryHolder;
import net.risesoft.soa.asf.core.event.RepositoryEvent;
import net.risesoft.soa.asf.core.event.RepositoryListener;
import net.risesoft.soa.asf.core.ws.WSServer;
import net.risesoft.soa.asf.core.ws.WSServerHolder;
import org.apache.cxf.Bus;
import org.apache.cxf.BusException;
import org.apache.cxf.BusFactory;
import org.apache.cxf.common.classloader.ClassLoaderUtils;
import org.apache.cxf.common.classloader.ClassLoaderUtils.ClassLoaderHolder;
import org.apache.cxf.resource.ResourceManager;
import org.apache.cxf.transport.DestinationFactory;
import org.apache.cxf.transport.DestinationFactoryManager;
import org.apache.cxf.transport.http.AbstractHTTPDestination;
import org.apache.cxf.transport.http.DestinationRegistry;
import org.apache.cxf.transport.http.HTTPTransportFactory;
import org.apache.cxf.transport.servlet.AbstractHTTPServlet;
import org.apache.cxf.transport.servlet.ServletContextResourceResolver;
import org.apache.cxf.transport.servlet.ServletController;
import org.apache.cxf.transport.servlet.servicelist.ServiceListGeneratorServlet;

public class OsgiNonSpringCXFServlet extends AbstractHTTPServlet
{
  private static final long serialVersionUID = 6160676539367518211L;
  private DestinationRegistry destinationRegistry;
  private Bus bus;
  private ServletController controller;
  private ClassLoader loader;
  private boolean loadBus;
  private RepositoryListener repoListener;

  public OsgiNonSpringCXFServlet()
  {
    this.loadBus = true;
  }

  public OsgiNonSpringCXFServlet(DestinationRegistry destinationRegistry)
  {
    this(destinationRegistry, true);
  }

  public OsgiNonSpringCXFServlet(DestinationRegistry destinationRegistry, boolean loadBus)
  {
    this.loadBus = true;

    this.destinationRegistry = destinationRegistry;
    this.loadBus = loadBus;
  }

  public void init(ServletConfig sc) throws ServletException
  {
    super.init(sc);
    if ((this.bus == null) && (this.loadBus)) {
      loadBus(sc);
    }
    if (this.bus != null) {
      this.loader = ((ClassLoader)this.bus.getExtension(ClassLoader.class));
      ResourceManager resourceManager = (ResourceManager)this.bus.getExtension(ResourceManager.class);
      resourceManager.addResourceResolver(
        new ServletContextResourceResolver(sc.getServletContext()));
      if (this.destinationRegistry == null) {
        this.destinationRegistry = getDestinationRegistryFromBus(this.bus);
      }
    }

    this.controller = createServletController(sc);
  }

  private static DestinationRegistry getDestinationRegistryFromBus(Bus bus) {
    DestinationFactoryManager dfm = (DestinationFactoryManager)bus.getExtension(DestinationFactoryManager.class);
    try {
      DestinationFactory df = dfm
        .getDestinationFactory("http://cxf.apache.org/transports/http/configuration");
      if (df instanceof HTTPTransportFactory) {
        HTTPTransportFactory transportFactory = (HTTPTransportFactory)df;
        return transportFactory.getRegistry();
      }
    }
    catch (BusException localBusException) {
    }
    return null;
  }

  protected void loadBus(ServletConfig sc) {
    this.bus = 
      CXFUtils.getCurrentBus();
    registerRepositoryListener();
  }

  private ServletController createServletController(ServletConfig servletConfig) {
    HttpServlet serviceListGeneratorServlet = 
      new ServiceListGeneratorServlet(this.destinationRegistry, this.bus);
    ServletController newController = 
      new LazyCXFServletController(this.destinationRegistry, 
      servletConfig, 
      serviceListGeneratorServlet);
    return newController;
  }

  private void registerRepositoryListener() {
    ServiceRepository repo = ServiceRepositoryHolder.getRepository("asf-local-repository");
    this.repoListener = new RepositoryListener()
    {
      public void onEvent(RepositoryEvent event) {
        if (event.getType() == 2) {
          String serviceId = event.getServiceDefinition().getServiceId();
          WSServer server = WSServerHolder.getServer(serviceId);
          if (server != null) {
            server.shutdown();
            WSServerHolder.removeServer(serviceId);
          }
        }
      }
    };
    if (repo == null) return; repo.addListener(this.repoListener);
  }

  private void removeRepositoryListener() {
    ServiceRepository repo = ServiceRepositoryHolder.getRepository("asf-local-repository");
    if (repo == null) return; repo.removeListener(this.repoListener);
  }

  public Bus getBus() {
    return this.bus;
  }

  public void setBus(Bus bus) {
    this.bus = bus;
  }

  protected void invoke(HttpServletRequest request, HttpServletResponse response) throws ServletException
  {
    ClassLoaderUtils.ClassLoaderHolder origLoader = null;
    try {
      if (this.loader != null) {
        origLoader = ClassLoaderUtils.setThreadContextClassloader(this.loader);
      }
      if (this.bus != null) {
        BusFactory.setThreadDefaultBus(this.bus);
      }
      this.controller.invoke(request, response);
    } finally {
      BusFactory.setThreadDefaultBus(null);
      if (origLoader != null)
        origLoader.reset();
    }
  }

  public void destroy()
  {
    for (String path : this.destinationRegistry.getDestinationsPaths())
    {
      AbstractHTTPDestination dest = this.destinationRegistry.getDestinationForPath(path);
      synchronized (dest) {
        this.destinationRegistry.removeDestination(path);
        dest.releaseRegistry();
      }
    }
    this.destinationRegistry = null;
    destroyBus();
    removeRepositoryListener();
  }

  public void destroyBus() {
    if (this.bus != null)
      this.bus.shutdown(true);
  }
}