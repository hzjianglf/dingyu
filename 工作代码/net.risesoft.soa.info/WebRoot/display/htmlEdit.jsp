<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@page import="net.risesoft.soa.info.util.ConfigUtil"%>
<%
String id = request.getParameter("id");
String infoID = request.getParameter("infoID");
String operation = request.getParameter("operation");
String type = request.getParameter("type");
%>
<%
if (type.equals("all")){%>
<script type="text/javascript">
    jQuery('#infoHtmlDiv').load('/info/display/htmlEditAll.jsp',{'id':'<%=id%>','infoID':'<%=infoID%>'});
</script>
<%
}
if (type.equals("html")){
%>
<script type="text/javascript">
	jQuery('#infoHtmlDiv').load('/info/display/htmlEdit_html.jsp',{'id':'<%=id%>', 'operation':'<%=operation%>'});
</script>
<%}
if (type.equals("doc")){
%>
<script type="text/javascript">
	jQuery('#infoHtmlDiv').load('/info/display/htmlEdit_doc.jsp',{'id':'<%=id%>','infoID':'<%=infoID%>'});
</script>
<%}%>
