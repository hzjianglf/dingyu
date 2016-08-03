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
	<script src="html://html5shim.googlecode.com/svn/trunk/html5.js"></script>
	<script src="http://html5media.googlecode.com/svn/trunk/src/html5media.min.js"></script>
	<link rel="stylesheet" href="css/style.css" />
	<title>登录</title>
	<script type="text/javascript" src="js/url.js"></script>
</head>

<body style="background:#ffffff">
	<header id="rcyp">
		用户登录
	</header>
	<section class="text">
		<p><input type="text" value="用户名" onClick="value='';focus()"></p>
		<p><input type="text" value="密  码" onClick="value='';focus()"></p>
	</section>
	<section>
		<p><input type="button" value="登录"  class="button"></p>
		<p style="font-weight:bold; text-align:center">如果您不是会员，请
		<%--<a href="#">--%>立即注册<%--</a>--%>
		</p>
	</section>
	<jsp:include page="footer.jsp"/>
</body>
</html>
