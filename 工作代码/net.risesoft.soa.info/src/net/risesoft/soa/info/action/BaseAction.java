package net.risesoft.soa.info.action;

import com.opensymphony.xwork2.ActionSupport;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

public class BaseAction extends ActionSupport
  implements ServletRequestAware, ServletResponseAware
{
  private static final long serialVersionUID = 1L;
  public static final String JSON = "json";
  private HttpServletRequest servletRequest;
  private HttpServletResponse servletResponse;
  public String json = "{success:true}";
  public String operation;

  public HttpServletRequest getServletRequest()
  {
    return this.servletRequest;
  }

  public void setServletRequest(HttpServletRequest servletRequest) {
    this.servletRequest = servletRequest;
  }

  public HttpServletResponse getServletResponse() {
    return this.servletResponse;
  }

  public void setServletResponse(HttpServletResponse servletResponse) {
    this.servletResponse = servletResponse;
  }

  public String getJson() {
    return this.json;
  }

  public void setJson(String json) {
    this.json = json;
  }

  public void setJson(boolean success, String message) {
    StringBuffer sb = new StringBuffer();
    sb.append("{success:").append(success);
    sb.append(",message:'");
    sb.append(StringEscapeUtils.escapeJavaScript(message));
    sb.append("'}");
    this.json = sb.toString();
  }

  public void setJson(boolean success) {
    StringBuffer sb = new StringBuffer();
    sb.append("{success:").append(success).append("}");
    this.json = sb.toString();
  }

  public final String getOperation() {
    return this.operation;
  }

  public final void setOperation(String operation) {
    this.operation = operation;
  }
}