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
    
	<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no" /> 
<script type="text/javascript" src="js/wgw.gs.lib.min.js"></script>
<link href="css/style.css" rel="stylesheet"/>
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
<div style=" font-size:28px; font-weight:bold; margin-left:10px;">
	<s:property value="contactUs.pageTitle"/>
</div>
<div style="border-bottom:1px solid #dfdfe1;"></div>
<div style=" margin-left:10px;">
	<div style="font-size:24px; font-weight:boldl;">地址：<s:property value="contactUs.address"/></div>
    <div style="font-size:24px; font-weight:boldl;">电话：<s:property value="contactUs.landline"/></div>
    <div style="font-size:24px; font-weight:boldl;">手机：<s:property value="contactUs.telephone"/></div>
    <div style="font-size:24px; font-weight:boldl;">联系人：<s:property value="contactUs.contacts"/></div>
</div>
<div id="allmap" style="height: 300px; width: 100%;">
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=ECe2b50ae093fe82dff717edec0e41e3"></script>
<jsp:include page="zongbu.jsp"/>
</div>
<div style=" height:50px;"></div>
<div style=" clear:both"></div>
<jsp:include page="footer.jsp"/>

</body>
</html>
