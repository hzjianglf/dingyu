package net.risesoft.soa.asf.core.ws;

import javax.servlet.http.HttpSession;

public class HttpSessionManager
{
  public static final String VALID_USER_KEY = "ASF_VALID_USER";
  private static final ThreadLocal<HttpSession> httpSession = new ThreadLocal();

  public static void setHttpSession(HttpSession session) {
    httpSession.set(session);
  }

  public static HttpSession getHttpSession() {
    return ((HttpSession)httpSession.get());
  }
}