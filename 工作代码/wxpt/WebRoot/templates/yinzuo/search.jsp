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
	<title>搜索</title>
	<script src="js/jquery-1.4.4.min.js"></script>
	<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no" />  <!--自适应宽度,并不允许缩放-->
	<script mce_src="html://html5shim.googlecode.com/svn/trunk/html5.js"></script>
	<script src="http://html5media.googlecode.com/svn/trunk/src/html5media.min.js"></script>
	<script src="js/slides.min.jquery.js"></script>
	<link rel="stylesheet" href="css/style.css" />
	<script type="text/javascript" src="js/url.js"></script>
</head>

<body style="background:#FFFFFF">
<div id="rcyp">
	产品搜索
</div>
<div style="text-align:center; margin-top:20px;">
	<input type="text"  style="height:25px"/>
	<input type="button" value="搜索" style="background:#0099FF; border:1px #0099FF solid; width:50px; height:30px; color:#FFFFFF; font-size:14px; font-weight:bold"/>
</div>
<jsp:include page="footer.jsp"/>
</body>
</html>
