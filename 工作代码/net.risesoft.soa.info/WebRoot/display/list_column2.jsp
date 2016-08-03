<%@page import="net.risesoft.soa.info.util.StringUtil"%>
<%@page import="net.risesoft.soa.info.model.InformationDesc"%>
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
String actionName = (String)request.getAttribute("action"); 
List<Object> infos = (List<Object>)request.getAttribute("infos");
List<InformationDesc> header = (List<InformationDesc>)request.getAttribute("header");
%>

 <table align="center" cellpadding="0" cellspacing="0" border="0" style="width: 100%; border-top: 1px solid #E5E5E5;border-left:1px solid #E5E5E5;">
  	<tr style="background: #eeeeee;">
		<td width=30 height=35 style="border-right: 1px solid #E5E5E5; border-bottom: 1px solid #E5E5E5;">&nbsp;</td>
<%
for(int i = 0; i < header.size(); i++){
	InformationDesc informationDesc = header.get(i);
	int length = informationDesc.getListLength();
	if (length == 0){
%>		
	<td align="center" style="border-right: 1px solid #E5E5E5;border-bottom: 1px solid #E5E5E5;"><%=informationDesc.getListName()%></td>
<%
	}else{
%>
	<td align="center" width=<%=length%> style="border-right: 1px solid #E5E5E5;border-bottom: 1px solid #E5E5E5;"><%=informationDesc.getListName()%></td>
<%
	}
}

if(actionName.contains("delete")||actionName.contains("edit")){
%>
	<td width=50 style="border-right: 1px solid #E5E5E5;border-bottom: 1px solid #E5E5E5;">&nbsp;</td>
<%}%>
	</tr>
<%
if (infos.size() > 0){
	int size = pageNo * pageSize;
%>
<%	
	for(int i = 0; i < infos.size(); i++){
		List<String> temp = (List<String>)infos.get(i);
%>
 	<tr id="<%=temp.get(0)%>">
 		<td height=35 align="center" style="border-right: 1px solid #E5E5E5;border-bottom: 1px solid #E5E5E5;"><%=i + 1 + size%></td>
<%
		for (int j = 1; j < temp.size(); j++) {
			InformationDesc informationDesc = header.get(j - 1);
			int length = informationDesc.getTextLength();
			String value = temp.get(j);
			if (value != null && length > 0){
				value = StringUtil.subString(value, length*2);
			}
			if (value == null || value.trim().length() == 0){
		%>
			<td height=35 align="center" style="border-right: 1px solid #E5E5E5; border-bottom: 1px solid #E5E5E5;">&nbsp;</td>
		<%
				
			}else{
		%>
				<td height=35 align="center" style="border-right: 1px solid #E5E5E5; border-bottom: 1px solid #E5E5E5;"><a href="/info/display/infoShow.jsp?id=<%=temp.get(0)%>" class="link1" style="line-height:22px;" target="_blank"><%=value%></a></td>
		<%
			}
		%>
		
<%
		}
%>
	<%
		if (actionName.contains("acshow")) {%>  
<td align="center" style="border-right: 1px solid #E5E5E5;border-bottom: 1px solid #E5E5E5;">		
			<a href="javascript:infoEnable('<%=temp.get(0)%>');"><img alt="启用" src="/info/css/images/start.png" border="0" title="启用"></a>
			</td>
		<%}%>
		
 	</tr>
<%
	}
%>
 </table>
<%
}
%> 

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
  
  