<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="net.risesoft.soa.framework.service.sso.client.util.OrgModelHelper"%>
<%@page import="net.risesoft.soa.framework.session.SessionUser"%>
<%@page import="net.risesoft.soa.framework.session.SessionConst"%>
<%@page import="net.risesoft.soa.framework.session.SessionUtil"%>
<%
SessionUser sessionUser = (SessionUser)request.getSession().getAttribute(SessionConst.USER);
 
if (request.getSession().getAttribute(SessionConst.USER) == null){
    String orgUID = OrgModelHelper.getValue("ID").toString();
    sessionUser = new SessionUser(orgUID);
    sessionUser.setOrgType(OrgModelHelper.getValue("ORGTYPE").toString());
    sessionUser.setIp(request.getRemoteAddr());
    sessionUser.setUserName(OrgModelHelper.getUserName());    
    //session.put(SessionConst.USER, sessionUser);
    request.getSession().setAttribute(SessionConst.USER, sessionUser);
}
SessionUtil.removeSessionUser();
SessionUtil.putSessionUser(sessionUser);

response.setHeader("Cache-Control","no-cache,no-store");  //在IE和FireFox中都起作用
response.setHeader("Pragma","no-cache");  //HTTP 1.0
response.setDateHeader("Expires",-1);  //prevents caching at the proxy server
%>

<script type="text/javascript">
global_CUR_SESSION_USER_UID = '<%= sessionUser.getUserUID()%>';
global_CUR_SESSION_USER_ORGTYPE = '<%=sessionUser.getOrgType()%>';
</script>
