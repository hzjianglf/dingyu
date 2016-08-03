package net.risesoft.soa.asf.web.interceptor;

import java.io.IOException;
import java.security.Principal;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jasig.cas.client.authentication.AttributePrincipal;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.resource.DefaultServletHttpRequestHandler;

public class IsAdminUserInterceptor extends HandlerInterceptorAdapter
{
  private static final String ADMIN_ROLE = "Admin";
  private PathMatcher pathMatcher = new AntPathMatcher();
  private String[] excludePaths;

  public final boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
    throws ServletException, IOException
  {
    if (this.excludePaths != null) {
      for (String exclude : this.excludePaths) {
        if (this.pathMatcher.match(exclude, request.getRequestURI())) {
          return true;
        }
      }
    }
    Principal principal = request.getUserPrincipal();
    if ((!(handler instanceof DefaultServletHttpRequestHandler)) && 
      (principal != null) && 
      (principal instanceof AttributePrincipal)) {
      AttributePrincipal attributePrincipal = (AttributePrincipal)principal;
      String personType = (String)attributePrincipal.getAttributes().get("ORGTYPE");
      if ((personType == null) || (!("Admin".equalsIgnoreCase(personType)))) {
        handleNotAdminUser(request, response, handler);
        return false;
      }
    }
    return true;
  }

  private void handleNotAdminUser(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException, IOException
  {
    response.sendRedirect(request.getContextPath() + "/notAdmin.jsp");
  }

  public String[] getExcludePaths() {
    return this.excludePaths;
  }

  public void setExcludePaths(String[] excludes) {
    this.excludePaths = excludes;
  }
}