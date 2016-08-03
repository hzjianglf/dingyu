<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/templates/"
			+ request.getAttribute("templateName") + "/";
	String filePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/web/images/0" + "/";
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
<title>城色</title>

</head>

<body>
	<div class="top">
			<h3><s:property value="menuName"/></h3>	
	</div>
	<div class="line"></div>
	<s:iterator value="pageList" var="page">
		<div class="menu"><a href="<s:property value="#page.pageUrl"/>">
			<div class="menu_img">
				<img src="<%=filePath %>/<s:property value="#page.metaImageValue"/>" width="100" height="60">
			</div>
			<div class="menu_content">
				<ul>
					<li><strong style="color: #371814;"><s:property value="#page.pageTitle"/></strong>
					</li>
					<li class="menu_content1"><s:property value="#page.metaDetail"/></li>
				</ul>
			</div></a>
		</div>
		<div class="line" style="margin-top:5px;"></div>
	</s:iterator>
	<div style="height:90px;"></div>
	<footer class="foot">
		<s:iterator value="siteOptionList" var="siteOption">
			<a href='<s:property value="#siteOption.url"/>'><img src="<%=filePath%>/<s:property value="#siteOption.optionValue"/>"/> </a>
		</s:iterator>
	</footer>
</body>
</html>
