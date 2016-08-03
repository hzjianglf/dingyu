<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@page import="net.risesoft.soa.info.util.ConfigUtil"%>
<%
String id = request.getParameter("id");
String infoID = request.getParameter("infoID");
%>
<table align="center" cellpadding="0" cellspacing="0" border="0" height=120>
	<tr>
		<td><font size=3>请选择正文模式：</font><a href="javascript:void(0);" class="link1" onClick="javascript:loadType('html')">文本编辑</a>&nbsp;&nbsp;<a href="javascript:void(0);" class="link1" onClick="javascript:loadType('doc')">上传WORD文档</a></td>
	</tr>
</table>
<script type="text/javascript">
	function loadType(type){
		if (type == 'html'){
			jQuery('#infoHtmlDiv').load('/info/display/htmlEdit_html.jsp', {'id': '<%=id%>','operation':'create'});
		}else{
			jQuery('#infoHtmlDiv').load('/info/display/htmlEdit_doc.jsp', {'id': '<%=id%>','infoID': '<%=infoID%>'});
		}
	}
	
</script>