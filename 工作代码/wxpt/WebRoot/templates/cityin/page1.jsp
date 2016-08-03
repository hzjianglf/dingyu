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

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<!--自适应宽度,并不允许缩放-->
<script src="js/html5.js"></script>
<script src="js/html5media.min.js"></script>
<link href="css/style.css" rel="stylesheet" type="text/css" />
<title><s:property value="pageAbout.menuName.replaceAll('<[^>]*>','')"/></title>

</head>

<body>
	<div class="top">
			<h3><s:property value="pageAbout.pageTitle"/></h3>	
	</div>
	<div class="line"></div>
	<div>
	<div style="float: right;">
		
	</div>
	<div style="width:95%;margin:0 auto; margin-top: 5px;">
		<img src="<%=filePath %>/<s:property value="pageAbout.metaImageValue" />" width="100%"/>
	</div>
	<div style="width:95%;margin:0 auto; ">
		<s:property value="pageAbout.metaValue" escapeHtml="false"/>
	</div>
	
	</div>
  </body>
</html>
