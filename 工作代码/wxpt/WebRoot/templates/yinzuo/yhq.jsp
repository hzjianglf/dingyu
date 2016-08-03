<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/templates/"+request.getAttribute("templateName")+"/";
	String filePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/web/images/"+request.getAttribute("enterID")+"/";
%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<!DOCTYPE HTML>
<html>
  <head>
    <base href="<%=basePath%>">
    
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script src="js/jquery-1.4.4.min.js"></script>
<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no" />  <!--自适应宽度,并不允许缩放-->
<script mce_src="html://html5shim.googlecode.com/svn/trunk/html5.js"></script>
<script src="http://html5media.googlecode.com/svn/trunk/src/html5media.min.js"></script>
<link rel="stylesheet" href="css/style.css" />
<style>
	nav ul li{border:1px #CCCCCC solid; text-align:center; margin:20px;}
</style>
<title><s:property value="menuName.replaceAll('<[^>]*>','')" /></title>
<script type="text/javascript" src="js/url.js"></script>
</head>

<body>
<img src="<%=filePath%>/<s:property value="bannerName"/>"  width="100%" height="100"/>
<nav>
	<ul>
		<s:iterator value="pageList"  var="page">
			<li><img src="<%=filePath%>/<s:property value="#page.metaImageValue"/>"  width="60%" height="90"/>
			<!-- 320*160 --></li>
		</s:iterator>
	</ul>
</nav>
<div style="clear:both"></div>
<div style="height:50px;"></div>
<jsp:include page="footer.jsp" />
</body>
</html>
