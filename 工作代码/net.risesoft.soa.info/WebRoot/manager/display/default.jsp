<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@page import="net.risesoft.soa.info.manager.menu.DefaultMenu"%>
<%
	DefaultMenu sm = new DefaultMenu(request);
	String menu = sm.getMenuJson();
%>
<html>
<head>
<title></title>
<script language="javascript" type="text/javascript">
var nodeID = 'root';
<%
  if (menu != null && menu.length() > 2) {
				out.println("new Ext.Toolbar({renderTo: 'info-tbar',items: "
						+ menu + "});");
			}%>    
</script>
</head>

<body>
<div id="info-tbar"></div>
</body>
</html>