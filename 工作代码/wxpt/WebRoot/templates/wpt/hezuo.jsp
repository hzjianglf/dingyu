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
</head>

<body>
<div class="top">
<div class="logo">
	<img src="<%=filePath%>/<s:property value="logoName"/>"/>
</div>
<div class="break">
    <a href="javascript:window.location.reload();"><img src="images/break.png" /></a>
</div>
</div>
<div style=" height:200px;">
	<img src="<%=filePath%>/<s:property value="bannerName"/>" height="100%"  width="100%" />
</div>
<!--banner结束-->
<div style="width:100%;  background:#FFF">
	<s:iterator value="pageList" var="pageAbout">
		<div>
		<div style="width:100%; height:50px; background:#e2e9eb;">
			<span style="margin-left:15px; font-weight:bold; font-size:36px; color:#000000;">
            <s:property value="#pageAbout.pageTitle"/></span>
		</div>
		<div style="border-bottom:1px dashed #cccccc; margin-bottom:10px;"></div>
		<div class="aboutus" style="font-family:微软雅黑; font-size:18px; margin-left:5px; height: auto;">
			<s:property value="#pageAbout.metaValue" escapeHtml="false"/>
		</div>
    </div>   
	</s:iterator>
</div>
<div style=" clear:both"></div>
<div style=" height:50px;"></div>
<div style=" clear:both"></div>
<jsp:include page="footer.jsp"/>
</body>
</html>
    