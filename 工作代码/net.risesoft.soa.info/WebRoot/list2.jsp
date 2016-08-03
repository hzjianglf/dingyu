<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@page import="net.risesoft.soa.info.tools.SpringUtil"%>
<%@page import="net.risesoft.soa.ac.manager.AccessControlService"%>
<%@page import="egov.appservice.ac.model.Resource"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="net.risesoft.soa.framework.session.SessionConst"%>
<%@page import="net.risesoft.soa.framework.session.SessionUser"%>
<%
SessionUser sessionUser = (SessionUser)request.getSession().getAttribute(SessionConst.USER);
String id = request.getParameter("id");
int pageNo = Integer.parseInt(request.getParameter("pageNo"));
int count = 0;
List<Resource> resources = new ArrayList<Resource>();
if (sessionUser != null){
	AccessControlService accessControlService = SpringUtil.getBean("accessControlService");
	resources = accessControlService.getSubResources(sessionUser.getUserUID(), "browse", id, null);
	count = resources.size();
}

int pageSize = 12;
if (count > 12){
	pageSize = 16;
}
%>

			<table width=95% border=0 cellpadding="0" cellspacing="0">
				<tr>
				<%
					for(int i = pageNo * pageSize; i < (pageNo + 1) * pageSize; i++){ 
						Resource resource = null;
						if (i < resources.size()){
							resource = resources.get(i);
						}
						if (i != 0 && i != pageNo * pageSize && i%4 == 0){
							if(resource == null){
								break;
							}
							out.println("</tr></table><table width=95% border=0 cellpadding=0 cellspacing=0><tr>");
						}
						if (resource != null){
							String type = resource.getType();
							String img = "images/folder.png";
							if ("information".equals(type)){
								img = "images/readme.png";
							}
				%>
					<td height=150 valign="top" align="left" style="border-bottom:1px solid #dbe6e6;">
						<table height=130 border=0 onclick="javascript:showInfoList2(this);" style="_cursor: pointer; cursor: pointer;" id="<%=resource.getUID()%>" type="<%=type%>" name="<%=resource.getName()%>">
							<tr>
								<td height=100 width=175 align=center valign="bottom"><img src="<%=img%>" width=80 height=90 border=0 alt=""></td>
							</tr>
							<tr>
								<td height=30 align="center" style="padding-bottom:5px; color: #000000;"><%=resource.getName()%></td>
							</tr>
						</table>
					</td>
				<%
						}else{
				%>
					<td height=150 style="border-bottom:1px solid #dbe6e6;">
						<table height=130 border=0>
							<tr>
								<td height=90 width=175 align=center>&nbsp;</td>
							</tr>
							<tr>
								<td height=40 align="center"></td>
							</tr>
						</table>
					</td>
				<%
						}
					}
				%>
				</tr>
			</table>
<script>
	var _cur_page = <%=pageNo%>;
	var pageSize = <%=pageSize%>;

	$("#Pagination").pagination(<%=count%>, {
	  num_edge_entries: 2,
	  num_display_entries: 5,
	  callback: pageselectCallback,
	  current_page:_cur_page,
	  items_per_page:pageSize
	});
	
</script>
