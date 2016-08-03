<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/templates/"
			+ request.getAttribute("templateName") + "/";
	String filePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/web/images/" + request.getAttribute("enterID")
			+ "/";
%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE HTML>
<html>
<head>
<base href="<%=basePath%>">
<meta charset="utf-8" />
<link rel="stylesheet" href="css/index.css" />
<script type="text/javascript" src="js/wgw.gs.lib.min.js"></script>
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<!--自适应宽度,并不允许缩放-->
<title><s:property value="menuName.replaceAll('<[^>]*>','')" /></title>

</head>

<body>
	<header>
		<img src="<%=filePath %>/<s:property value="bannerName"/>" width="100%" height="200" />
	</header>
	<address>
		<ul>
			<li>地址：<s:property value="contactUs.address"/></li>
			<li>电话：<s:property value="contactUs.telephone"/></li>
			<li>邮箱：<s:property value="contactUs.email"/></li>
		</ul>
	</address>
</body>
</html>
