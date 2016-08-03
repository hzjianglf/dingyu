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
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<!--自适应宽度,并不允许缩放-->
<script src="js/html5.js"></script>
<script src="js/html5media.min.js"></script>
<script src="js/wgw.gs.lib.min.js"></script>
<script src="js/url.js"></script>
<link href="css/style.css" rel="stylesheet" type="text/css" />
<link href="css/l_style.css" rel="stylesheet" type="text/css" />
<link href="../css/url_skip.css" rel="stylesheet" type="text/css" />
<title><s:property value="enterInfor.enterName" /></title>
</head>

<body>
<div class="FrontSlide" >
<div class="bannerScrollWrap" >
<div class="leftCover"></div>
<div id="bannerScroll">
<div class="prev"><span id="prev" class="active"></span></div>
<ul class="bannerList sc_scroller">
	<s:iterator value="siteOptionList" var="siteOption">
		<li><a href="<s:property value="#siteOption.url"/>"><img src="<%=filePath%>/<s:property value="#siteOption.optionValue"/>" width="100%" height="250"/></a></li> 
	</s:iterator>
</ul>
<div class="next"><span id="next" class="active"></span></div> 
</div>
<div class="rightCover"></div>
</div>
</div>
<script type="text/javascript" src="js/qy_touch.js"></script>
<script type="text/javascript" src="js/m_common.js"></script>
<div style="clear:both"></div>
<div style="width:100%; height:200px; margin-top:10px;">
	<div class="index_l">
		<a href="javascript:getUrl('<s:property value="#attr.siteMenuList.{menuId}[0]"/>','1')">
		<div class="index_b">
			<img src="<%=filePath%>/<s:property value="#attr.siteMenuList.{imageValue}[0]"/>">
			<div class="index_span"><s:property value="#attr.siteMenuList.{menuName}[0]"  escapeHtml="false"/></div>
		</div></a>
	</div>
	<div class="index_r">
		<a href="javascript:getUrl('<s:property value="#attr.siteMenuList.{menuId}[1]"/>','1')">
		<div class="index_b">
			<img src="<%=filePath%>/<s:property value="#attr.siteMenuList.{imageValue}[1]"/>">
			<div class="index_span"><s:property value="#attr.siteMenuList.{menuName}[1]"  escapeHtml="false"/></div>
		</div></a>
	</div>
</div>
<div style="width:100%; height:200px;">
	<div class="index_l">
		<a href="javascript:getUrl('<s:property value="#attr.siteMenuList.{menuId}[2]"/>','3')">
			<div class="index_b">
				<img src="<%=filePath%>/<s:property value="#attr.siteMenuList.{imageValue}[2]"/>">
				<div class="index_span"><s:property value="#attr.siteMenuList.{menuName}[2]"  escapeHtml="false"/></div>
			</div>
		</a>
	</div>
	<div class="index_r">
		<a href="javascript:getUrl('<s:property value="#attr.siteMenuList.{menuId}[3]"/>','4')">
			<div class="index_b">
				<img src="<%=filePath%>/<s:property value="#attr.siteMenuList.{imageValue}[3]"/>">
				<div class="index_span"><s:property value="#attr.siteMenuList.{menuName}[3]"  escapeHtml="false"/></div>
			</div>
		</a>
	</div>
</div>

<script src="../js/url_skip.js"></script>

<div class="box-guide hide">
	<div class="box-img">
		<img src="../images/guide.png" data-pinit="registered">
	</div>
</div>
<div id="share-shadow" class="shade"></div>
</body>
</html>
