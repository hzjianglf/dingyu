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
<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no" />  <!--自适应宽度,并不允许缩放-->
<title><s:property value="pageAbout.pageTitle" /></title>
</head>

<body>
<header>
	<div style="background:#cccccc; width:100%; height:40px; text-align:center; font-size:18px; padding-top:10px;"><s:property value="pageAbout.pageTitle" /></div>
</header>
<article>
	<div class="weiba-content">
			<s:property value="pageAbout.metaValue" escapeHtml="false"/>
	</div>
	<div class="weiba-content">
			<img src="<%=filePath %>/<s:property value="pageAbout.metaImageValue" />">
	</div>
</article>
</body>
</html>
