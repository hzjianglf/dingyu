<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
<% String imagePath = request.getContextPath()+"/images/";
%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String name = request.getParameter("name");
//<%=request.getUserPrincipal().getName().toString()
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'index.jsp' starting page</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<script type="text/javascript" src="<%=basePath %>js/jquery-1.8.0.min.js"></script>
<script type="text/javascript">
		$(function(){
			htmlobj=$.ajax({url:"getemails.html?status=emailNum&name=<%=name%>",async:false});
			  $("#emailNum").html(htmlobj.responseText);
		});
	</script>
</head>

<body>
	<form id="emailForm" action="getemails.html" method="get">
	<input type="hidden" name="status" value="login">
	<input name="name" type="hidden" value="<%=name%>">  

	<div style="cursor:pointer; background:#c1a400; padding:10px 0px 10px 0px;width:179px;font-size: 12px;" onclick="document.getElementById('emailForm').submit();">
		<div style="margin-left:15px;background:url(images/dbemail.png) no-repeat;line-height:34px;color:#ffffff;">
			<span style="margin-left:44px;">待阅邮件[  <span id="emailNum">0</span> ]</span>
		</div>						
	</div>

	</form>
</body>
</html>
