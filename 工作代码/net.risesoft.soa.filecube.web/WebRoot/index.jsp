<%@page import="net.risesoft.soa.framework.session.SessionConst"%>
<%@page import="net.risesoft.soa.framework.session.SessionUser"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!-- 加载全局资源 -->
<jsp:include page="page/global_js_const.jsp"></jsp:include>
<!-- 加载session -->
<jsp:include page="header.jsp"></jsp:include>
<html>
<head>
<link rel="stylesheet" type="text/css" href="<%=path %>/css/index.css" />
<link rel="stylesheet" type="text/css" href="<%=path %>/css/left.css" />
<link rel="stylesheet" type="text/css" href="<%=path %>/commons/colorbox/colorbox.css" />
<link rel="stylesheet" type="text/css" href="<%=path %>/commons/pagination/pagination.css" />
<link rel="stylesheet" type="text/css" href="<%=path %>/commons/jquery/ui/css/flick/jquery-ui-1.9.1.custom.min.css" />
<link rel="stylesheet" type="text/css" href="<%=path %>/commons/css/listDataTable.css" />
<link rel="stylesheet" type="text/css" href="<%=path %>/commons/css/topTools.css" />
<script type="text/javascript" src="<%=path %>/commons/jquery/jquery-1.7.2.min.js"></script>

<link rel="stylesheet" type="text/css" href="/extjs/3.4/resources/css/ext-all.css" />
<script type="text/javascript" src="/extjs/3.4/adapter/ext/ext-base.js"></script>
<script type="text/javascript" src="/extjs/3.4/ext-all.js"></script>
<script type="text/javascript" src="/extjs/3.4/src/locale/ext-lang-zh_CN.js"></script>
<script type="text/javascript" src="/extjs/init.js"></script>
<!-- 
<script type="text/javascript" src="<%=path %>/commons/grade/jquery.raty.js"></script>
 -->
<script type="text/javascript" src="<%=path %>/commons/colorbox/jquery.colorbox.js"></script>
<script type="text/javascript" src="<%=path %>/commons/jquery/ui/jquery-ui-1.9.1.custom.min.js"></script>

<script type="text/javascript" src="<%=path %>/commons/js/commons.js"></script>
<script type="text/javascript" src="<%=path %>/commons/pagination/pagination.js"></script>
<script type="text/javascript" src="<%=path %>/js/index.js"></script>
<script type="text/javascript" src="<%=path %>/js/left.js"></script>
<script type="text/javascript" src="<%=path %>/js/permission/permission.js"></script>
<script type="text/javascript" src="<%=path %>/js/file/file.js"></script>
<script type="text/javascript" src="<%=path %>/js/folder/folder.js"></script>

<script type="text/javascript" src="<%=path %>/commons/ajaxUpload/customUpload.js"></script>
<style type="text/css">
.png{
_background: url(<%=path %>/commons/css/filecube.png) no-repeat !important;
filter: progid:DXImageTransform.Microsoft.AlphaImageLoader(enabled=true, sizingMethod=noscale, src="<%=path %>/commons/css/filecube.png");
background:none;
width:118px;height:133px;
}
</style>
</head>
<body>
	<div id="a-b">
		
	</div>
	<!-- top区域 -->	
	<div style="display:none" class="topTools" id="topTools">		
		<div id="header" class="headInside">	
			<h1></h1>	
			<ul id="topNav" class="topNav right">			
				  <li class="topNavItem">
					<a href="/fulltext">
						<u>全文检索</u>
						<i></i>
				  	</a>
				  </li>
			</ul>
		</div>
		<div class="headShadow">
			<div class="png"></div>
			<span style="left: 149px;top:10px; ;position: absolute;">
				欢迎您：
				<a style="font-size: 14px;color: #FEFEFE;">
					<%=((SessionUser)session.getAttribute(SessionConst.USER)).getUserName() %>
				</a>
			</span>
		</div>
	</div>
	<!-- top区域结束 -->
	

<!-- 左边树 -->
<jsp:include page="left.jsp"></jsp:include>
<!-- 上传文件控件 -->
<div id="global_uploadDiv">	
</div>
</body>
</html>