package net.risesoft.soa.asf.core.ws.cxf;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import net.risesoft.soa.asf.core.ServiceRepository;
import net.risesoft.soa.asf.core.ServiceRepositoryHolder;
import net.risesoft.soa.asf.core.local.LocalServiceDefinition;
import net.risesoft.soa.asf.core.ws.HttpSessionManager;
import net.risesoft.soa.asf.core.ws.WSServer;
import net.risesoft.soa.asf.core.ws.WSServerHolder;
import net.risesoft.soa.asf.util.json.JSONException;
import net.risesoft.soa.asf.util.json.JSONObject;
import org.apache.cxf.transport.http.DestinationRegistry;
import org.apache.cxf.transport.servlet.ServletController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class LazyCXFServletController extends ServletController
{
  private static final Logger log = LoggerFactory.getLogger(LazyCXFServletController.class);

  public LazyCXFServletController(DestinationRegistry df, ServletConfig servletConfig, HttpServlet servlet) {
    super(df, servletConfig, servlet);
  }

  public void invoke(HttpServletRequest request, HttpServletResponse res) throws ServletException
  {
    HttpSession session = request.getSession();
    HttpSessionManager.setHttpSession(session);
    prepareWebService(request);
    ClassLoader original = Thread.currentThread().getContextClassLoader();
    try {
      Thread.currentThread().setContextClassLoader(LazyCXFServletController.class.getClassLoader());
      super.invoke(new HttpServletRequestWrapper(request), res);
    } finally {
      Thread.currentThread().setContextClassLoader(original);
    }
  }

  private void checkPermission(HttpServletRequest request, LocalServiceDefinition local)
  {
  }

  private void prepareWebService(HttpServletRequest request)
  {
    String address = request.getRequestURI().substring(request.getContextPath().length());

    String serviceId = parseServiceId(address);
    if ((address.length() == 0) || (serviceId.length() == 0)) return;

    ServiceRepository repo = ServiceRepositoryHolder.getRepository("asf-local-repository");
    if (repo != null) {
      LocalServiceDefinition local = (LocalServiceDefinition)repo.getServiceDefinition(serviceId);
      if (local != null) {
        checkPermission(request, local);
        String paramsStr = request.getParameter("params");
        local.getParametersMap().clear();
        local.getParametersMap().putAll(parseServiceParams(paramsStr));

        WSServer server = getWebService(serviceId);
        if (server == null) {
          WebServiceInfo info = new WebServiceInfo(address, serviceId);
          log.debug("publishing: " + info + '.');
          CXFServerCreator creator = new CXFServerCreator();
          creator.setAddress(address);
          creator.setServiceClass(local.getServiceClass());
          creator.setServiceObject(local.getServiceObject());
          server = creator.create();
          server.startup();
          holdWebService(serviceId, server);
          log.debug("published: " + info + '.');
        }
      }
    }
  }

  private String parseServiceId(String address) {
    String rawAddr = (address.startsWith("/")) ? address.substring(1) : address;
    return rawAddr.replace('/', '.');
  }

  private Map parseServiceParams(String paramsStr) {
    if (paramsStr == null) return new HashMap();
    try {
      return new JSONObject(paramsStr); } catch (JSONException localJSONException) {
    }
    return Collections.EMPTY_MAP;
  }

  private WSServer getWebService(String serviceId)
  {
    return WSServerHolder.getServer(serviceId);
  }

  private void holdWebService(String serviceId, WSServer server) {
    WSServerHolder.putServer(serviceId, server);
  }

  private static class HttpServletRequestWrapper extends HttpServletRequestWrapper
  {
    public HttpServletRequestWrapper(HttpServletRequest request)
    {
      super(request);
    }

    public String getPathInfo()
    {
      return super.getRequestURI().substring(super.getContextPath().length());
    }
  }

  private static class WebServiceInfo
  {
    String address;
    String serviceId;

    public WebServiceInfo(String address, String serviceId)
    {
      this.address = address;
      this.serviceId = serviceId;
    }

    public String toString() {
      return "WebService[serviceId=" + 
        this.serviceId + 
        ", " + 
        "address=" + 
        this.address + 
        ']';
    }
  }
}