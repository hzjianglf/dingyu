<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@page import="net.risesoft.soa.info.util.ConfigUtil"%>
<%@page import="java.util.List"%>
<%@page import="net.risesoft.soa.info.model.Information"%>
<%@page import="net.risesoft.soa.info.tools.InformationList"%>
<%
int pageSize = (Integer)request.getAttribute("pageSize");
int count = (Integer)request.getAttribute("count");
int pageNo = (Integer)request.getAttribute("pageNo");
String infoID = (String)request.getAttribute("infoID");
Information information = InformationList.get(infoID);
	String shortacnum = "";
try{
shortacnum=request.getParameter("shortacnum");
}catch(Exception e){}

String acid = "";
try{
acid=request.getParameter("acid");
}catch(Exception e){}

String url = "/info/display/infoShow.jsp";
if (information.getUrl() != null && information.getUrl().trim().length() > 0){
	url = information.getUrl();
}
String actionName = (String)request.getAttribute("action"); 
List<Object> infos = (List<Object>)request.getAttribute("infos");
%>
<%
for(int i = 0; i < infos.size(); i = i+4){
%>
  <table id="<%=infos.get(i)%>" align="center" cellpadding="0" cellspacing="0" border="0" style="width: 100%;">
  	<tr>
		<td align="left" height="40"><a href="<%=url%>?id=<%=infos.get(i)%>&acid=<%=acid%>" class="link1" target="_blank"><%=infos.get(i+1)%></a></td>
		<td align="right">
		<%if (actionName.contains("acversion")){%>
  			<a href="javascript:infoAdd2('<%=infoID%>','<%=infos.get(i)%>');"><img alt="版本" src="/info/css/images/ss.png" border="0" title="版本"></a>
		<%}
		
		
		if(actionName.contains("disable")){
			%>
			<a href="javascript:infoDisable('<%=infos.get(i)%>');"><img alt="禁用" src="/info/css/images/und.png" border="0" title="禁用"></a>
			<%
		}
		
		if (actionName.contains("edit")){%>
  			<a href="javascript:infoEdit('<%=infos.get(i)%>');"><img alt="编辑" src="/info/css/images/edit.png" border="0" title="编辑"></a>
		<%}
		if (actionName.contains("delete")) {%>  	
			<a href="javascript:infoDelete('<%=infos.get(i)%>');"><img alt="删除" src="/info/css/images/delete.png" border="0" title="删除"></a>
		<%}%>
		</td>
	</tr>
	<tr>
		<td colspan="2" height=30 align="right" style="border-bottom: 1px dotted rgb(209, 209, 209);">
		      <DIV class="funBox"><%=infos.get(i+2)%>&nbsp;创建于&nbsp;<%=infos.get(i+3).toString().replaceAll("00:00", "")%><!--<SPAN>|</SPAN>--></DIV>
		</td>
	</tr>
  </table>
<%}%>
<script language="javascript">
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
  
  