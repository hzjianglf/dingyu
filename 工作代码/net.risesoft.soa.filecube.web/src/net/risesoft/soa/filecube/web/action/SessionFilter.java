package net.risesoft.soa.filecube.web.action;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import net.risesoft.soa.framework.service.sso.client.util.OrgModelHelper;
import net.risesoft.soa.framework.session.SessionUser;
import net.risesoft.soa.framework.session.SessionUtil;

public class SessionFilter
  implements Filter
{
  public void init(FilterConfig filterConfig)
    throws ServletException
  {
  }

  public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
    throws IOException, ServletException
  {
    HttpServletRequest request = (HttpServletRequest)req;
    HttpServletResponse response = (HttpServletResponse)resp;

    SessionUser sessionUser = (SessionUser)request.getSession().getAttribute("session.User");

    if (request.getSession().getAttribute("session.User") == null) {
      String orgUID = OrgModelHelper.getValue("ID").toString();
      sessionUser = new SessionUser(orgUID);
      sessionUser.setOrgType(OrgModelHelper.getValue("ORGTYPE").toString());
      sessionUser.setIp(request.getRemoteAddr());
      sessionUser.setUserName(OrgModelHelper.getUserName());
      request.getSession().setAttribute("session.User", sessionUser);
    }
    SessionUtil.removeSessionUser();
    SessionUtil.putSessionUser(sessionUser);

    response.setHeader("Cache-Control", "no-cache,no-store");
    response.setHeader("Pragma", "no-cache");
    response.setDateHeader("Expires", -1L);
    chain.doFilter(request, response);
  }

  public void destroy()
  {
  }
}