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
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title><s:property value="menuName.replaceAll('<[^>]*>','')" /></title>
    
	<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no" /> 
<script type="text/javascript" src="js/wgw.gs.lib.min.js"></script>
<link href="css/style.css" rel="stylesheet"/>
<script type="text/javascript" src="js/url.js"></script>
</head>

<body style="background:#FFFFFF">
<div style="height:60px; width:100%; background:#000000">
<div class="top">
<div class="logo">
	<img src="<%=filePath%>/<s:property value="logoName"/>"/>
</div>
<div class="break">
    <a href="javascript:window.location.reload();"><img src="images/break.png" /></a>
</div>
</div>
</div>
<div style=" height:200px;">
	<img src="<%=filePath%>/<s:property value="bannerName"/>" height="100%"  width="100%" />
</div>

<div style="width:100%; background:#FFF; height:auto;">
	<s:iterator value="childMenuList" var="childMenu" status="status">
		<s:if test="#status.first">
			<div style=" margin-top:20px;clear:both">
		</s:if>
		<s:if test="#status.last">
			<div style=" margin-top:10px;clear:both; margin-bottom:20px;">
		</s:if>
		<s:if test="#status.first == false && #status.last == false">
			<div style=" margin-top:10px;clear:both">
		</s:if>
				<a href="javascript:getUrl(<s:property value="#childMenu.menuId"/>,4)"><div style="padding-top:10px; background:#dddddd; width:100%; height:50px;">
		            <div style="float:left; margin-left:10px; color:#fda307; font-weight:bold; font-size:25px;"><s:property value="#childMenu.menuName"/></div>
		            <div style=" float:right; margin-right:10px; text-align:center; margin-top:8px;"><img src="images/daohang.png"/></div>
		        </div></a>
        	</div>
	</s:iterator>
</div>
<div style=" height:50px;"></div>
<div style=" clear:both"></div>
<jsp:include page="footer.jsp"/>
</body>
</html>
