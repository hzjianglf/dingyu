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
	<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no" />  <!--自适应宽度,并不允许缩放-->
	<script mce_src="html://html5shim.googlecode.com/svn/trunk/html5.js"></script>
	<script src="http://html5media.googlecode.com/svn/trunk/src/html5media.min.js"></script>
	<script src="js/jquery-1.4.4.min.js"></script>
	<link rel="stylesheet" href="css/style.css" />
	<title><s:property value="pageAbout.pageTitle" /></title>
	<script type="text/javascript" src="js/url.js"></script>
</head>

<body>
<div id="rcyp">
	<s:property value="pageAbout.pageTitle" />
</div>
<header>
	 <div class="xiangqing"><img src="<%=filePath %>/<s:property value="pageAbout.metaImageValue" />" width="200" height="200"  style="padding:10px;"/></div> 
</header>
<section style="font-size:12px;">
	<p style="text-align:center; font-size:14px; font-weight:bold"><s:property value="pageAbout.pageTitle" /></p>
	<div id="title1"><s:property value="pageAbout.metaDetail" escapeHtml="false"/></div>
	<%-- 
	<p align="center">
		<a href="address.html"><input  type="button" value="立即购买" style="background:#FF3333; border:1px #FF3333 solid; color:#FFFFFF; width:90px; height:30px;"/></a>
		<a href="shopcar.html"><input  type="button" value="我的购物车" style="background:#cccccc; border:1px #cccccc solid; color:#FFFFFF; width:90px; height:30px;"/></a>
		<input  type="button" value="加入购物车" style="background:#0099FF; border:1px #0099FF solid; color:#FFFFFF; width:90px; height:30px;"/>
	</p>
	--%>
</section>
<br>
<section>
	<img src="images/xiangxi.png" width="100%">
	<p style="margin-left:13%; margin-top:-6%; font-seze:14px;">详情</p>
	<div style="clear:both"></div>
	<div style="margin-left:2%; margin-right:2%"><s:property value="pageAbout.metaValue" escapeHtml="false"/></div>
</section>
<div style="clear:both"></div>
<div style="height:50px;"></div>
<jsp:include page="footer.jsp"/>
</body>
</html>
