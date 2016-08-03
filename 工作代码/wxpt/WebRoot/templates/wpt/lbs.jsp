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

<title><s:property value="menuName.replaceAll('<[^>]*>','')" /></title>

<meta name="viewport"
	content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<script type="text/javascript" src="js/wgw.gs.lib.min.js"></script>
<link href="css/style.css" rel="stylesheet" />
<script type="text/javascript" src="js/url.js"></script>
</head>

<body style="background:#FFFFFF">
	<div style="height:60px; width:100%; background:#000000">
		<div class="top">
			<div class="logo">
				<img src="<%=filePath%>/<s:property value="logoName"/>" />
			</div>
			<div class="break">
				<a href="javascript:window.location.reload();"><img src="images/break.png" /></a>
			</div>
		</div>
	</div>
	<s:iterator value="pageList" var="pageAbout">
		<div style=" width:100%;">
			<div style=" width:100%;">
				<img src="<%=filePath %>/<s:property value="#pageAbout.metaImageValue"/>" style=" width:100%;" />
			</div>
			<div style="color:#000; font-size:24px; font-weight:bold;"><s:property value="#pageAbout.pageTitle"/></div>
			<div style=" margin-top:10px; margin-bottom:10px; font-size:18px;">
				<s:property value="#pageAbout.metaValue" escapeHtml="false"/></div>
		</div>
	</s:iterator>
	
	
	<div style=" height:50px;"></div>
	<div style=" clear:both"></div>
	<jsp:include page="footer.jsp"/>
</body>
</html>
