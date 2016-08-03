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
<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<!--自适应宽度,并不允许缩放-->
<title><s:property value="menuName.replaceAll('<[^>]*>','')" /></title>
<style>
a {
	color: #666666
}
</style>
<script type="text/javascript" src="js/url.js"></script>
</head>

<body>
	<header>
		<img src="<%=filePath %>/<s:property value="bannerName"/>" width="100%" height="200" />
	</header>
	<section>
	
		<s:iterator value="pageList" var="page">
		<s:if test="#page.pageUrl=='' || #page.pageUrl==NULL">
			<a href="javascript:getPage(<s:property value="#page.pageId"/>)">
		</s:if>	
		<s:if test="#page.pageUrl!='' && #page.pageUrl!=NULL">
			<a href="<s:property value="#page.pageUrl"/>">
		</s:if>	
			<div class="product">
					<ul>
						<li style="margin-left:-20px;"><img src="<%=filePath %>/<s:property value="#page.metaImageValue"/>"
							width="50" height="50" />
						</li>
						<li class="product1"><s:property value="#page.pageTitle"/></li>
						<li style="float:right; margin-top:15px; margin-right:5%"><img
							src="images/jiantou.png" />
						</li>
					</ul>
				</div>
			</a> 
		</s:iterator>
	</section>
	<jsp:include page="fenye.jsp"></jsp:include>
</body>
</html>
