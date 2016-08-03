package net.risesoft.soa.asf.core.ws.cxf;

import egov.appservice.asf.exception.ServiceClientException;
import egov.appservice.asf.service.ServiceClient;
import egov.appservice.asf.service.ServiceClientFactory;
import egov.appservice.org.exception.AuthenticateFailException;
import egov.appservice.org.service.AuthenticateService;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Map;
import net.risesoft.soa.asf.core.util.RuntimeConfig;
import org.apache.cxf.binding.Binding;
import org.apache.cxf.binding.soap.interceptor.SoapHeaderInterceptor;
import org.apache.cxf.configuration.security.AuthorizationPolicy;
import org.apache.cxf.endpoint.Endpoint;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.interceptor.InterceptorChain;
import org.apache.cxf.message.Exchange;
import org.apache.cxf.message.Message;
import org.apache.cxf.transport.Conduit;
import org.apache.cxf.transport.Destination;
import org.apache.cxf.ws.addressing.EndpointReferenceType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BasicAuthorizationInterceptor extends SoapHeaderInterceptor
{
  protected Logger log = LoggerFactory.getLogger(super.getClass());
  private ServiceClient serviceClient;
  private AuthenticateService authService;

  public void handleMessage(Message message)
    throws Fault
  {
    String enableAuth = RuntimeConfig.getProperty("asf.auth.enable", "true");
    if (enableAuth.trim().equalsIgnoreCase("true")) {
      AuthorizationPolicy policy = (AuthorizationPolicy)message.get(AuthorizationPolicy.class);
      if (policy == null) {
        sendErrorResponse(message, 401);
        return;
      }
      if (isAsfInternalService(policy)) return;
      if (!(authenticate(policy.getUserName(), policy.getPassword()))) {
        this.log.warn("Invalid username or password for user: " + policy.getUserName());
        sendErrorResponse(message, 403);
      }
    }
  }

  private boolean isAsfInternalService(AuthorizationPolicy policy) {
    return "asf-internal-service-only".equals(policy.getUserName());
  }

  private boolean authenticate(String userName, String password) {
    initServiceIfNeed();
    if (this.authService != null) {
      try {
        return this.authService.authenticate(userName, password).equals("true");
      } catch (AuthenticateFailException ex) {
        this.log.warn(ex.getMessage());
        return false;
      }
    }
    return false;
  }

  private void initServiceIfNeed() {
    if (this.serviceClient == null) {
      this.serviceClient = ServiceClientFactory.getServiceClient("asf-local-repository");
    }
    if (this.authService != null) return;
    try {
      this.authService = ((AuthenticateService)this.serviceClient.getServiceByName("org.AuthenticateService"));
    } catch (ServiceClientException localServiceClientException) {
      this.log.warn("unable init org.AuthenticateService!, retry next invoke.");
    }
  }

  private void sendErrorResponse(Message message, int responseCode)
  {
    Message outMessage = getOutMessage(message);
    outMessage.put(Message.RESPONSE_CODE, Integer.valueOf(responseCode));

    Map responseHeaders = (Map)message.get(Message.PROTOCOL_HEADERS);
    if (responseHeaders != null) {
      responseHeaders.put("WWW-Authenticate", Arrays.asList(new String[] { "Basic realm=realm" }));
      responseHeaders.put("Content-Length", Arrays.asList(new String[] { "0" }));
    }
    message.getInterceptorChain().abort();
    try {
      getConduit(message).prepare(outMessage);
      close(outMessage);
    } catch (IOException e) {
      this.log.warn(e.getMessage(), e);
    }
  }

  private Message getOutMessage(Message inMessage) {
    Exchange exchange = inMessage.getExchange();
    Message outMessage = exchange.getOutMessage();
    if (outMessage == null) {
      Endpoint endpoint = (Endpoint)exchange.get(Endpoint.class);
      outMessage = endpoint.getBinding().createMessage();
      exchange.setOutMessage(outMessage);
    }
    outMessage.putAll(inMessage);
    return outMessage;
  }

  private Conduit getConduit(Message inMessage) throws IOException {
    Exchange exchange = inMessage.getExchange();
    EndpointReferenceType target = (EndpointReferenceType)exchange.get(EndpointReferenceType.class);
    Conduit conduit = exchange.getDestination().getBackChannel(inMessage, null, target);
    exchange.setConduit(conduit);
    return conduit;
  }

  private void close(Message outMessage) throws IOException {
    OutputStream os = (OutputStream)outMessage.getContent(OutputStream.class);
    os.flush();
    os.close();
  }
}