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

<meta name="viewport"
	content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<script type="text/javascript" src="js/wgw.gs.lib.min.js"></script>
<link href="css/style.css" rel="stylesheet" />
<script type="text/javascript" src="js/url.js"></script>
<style>
.content {
	font-size: 18px;
	width: 90%;
	height: 55px;
	padding: 10px;
	overflow: hidden;
	margin-top: 5px;
	text-indent: 2em;
	font-family: 微软雅黑;
}
</style>
<script>
	var s = 5;
	var minheight = 55;
	var maxheight = 200;
	function shoppingcat(index) {
		var key = document.getElementById("key"+index).innerText;
		if (key == "查看更多文章") {
			
			document.getElementById("content"+index).style.height="auto";
			if (document.getElementById("content"+index).style.height < maxheight) {
				setTimeout("shoppingcat('"+index+"');", 1);
			} else {
				document.getElementById("key"+index).innerText = "关闭";
			}
		} else {
			document.getElementById("content"+index).style.height = "55px";
			if (document.getElementById("content"+index).style.height > minheight) {
				setTimeout("shoppingcat('"+index+"');", 1);
			} else {
				document.getElementById("key"+index).innerText = "查看更多文章";
			}
		}
	}
</script>
</head>

<body>
	<div class="top">
		<div class="logo">
			<img src="<%=filePath%>/<s:property value="logoName"/>" />
		</div>
		<div class="break">
			<a href="javascript:window.location.reload();"><img src="images/break.png" /></a>
		</div>
	</div>
	<div style=" height:200px;">
		<img src="<%=filePath%>/<s:property value="bannerName"/>" height="100%"
			width="100%" />
	</div>
	<div style="width:100%;  background:#FFF; clear:both">
		<s:iterator value="pageList" var="pageAbout" status="status">
			<div>
				<div class="bg">
					<span><s:property value="#pageAbout.pageTitle"/></span>
				</div>
				<div id="content<s:property value="#status.index"/>" class="content">
					<s:property value="#pageAbout.metaValue" escapeHtml="false"/>
				</div>
				<div class="more" id="key<s:property value="#status.index"/>" onClick="shoppingcat('<s:property value="#status.index"/>');">查看更多文章</div>
			</div>
			<div style="clear:both;"></div>
		</s:iterator>
	</div>
	<div style=" height:50px;"></div>
	<div style=" clear:both"></div>
	<jsp:include page="footer.jsp"/>
</body>
</html>
