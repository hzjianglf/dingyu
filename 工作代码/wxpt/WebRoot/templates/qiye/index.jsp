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
<link rel="stylesheet" href="css/m_style.css" />
<link rel="stylesheet" href="css/index.css" />
<script type="text/javascript" src="js/wgw.gs.lib.min.js"></script>
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no" />
	<script type="text/javascript" src="js/url.js"></script>
<!--自适应宽度,并不允许缩放-->
<title><s:property value="enterInfor.enterName"/></title>

</head>

<body>
	<div class="FrontSlide">
		<div class="bannerScrollWrap">
			<div class="leftCover"></div>
			<div id="bannerScroll">
				<div class="prev">
					<span id="prev" class="active"></span>
				</div>
				<ul class="bannerList sc_scroller">
					<s:iterator value="siteOptionList" var="siteOption">
						<li><img src="<%=filePath%>/<s:property value="#siteOption.optionValue"/>" width="100%" height="300"/></li> 
				    </s:iterator>
				</ul>

				<div class="next">
					<span id="next" class="active"></span>
				</div>
			</div>
			<div class="rightCover"></div>
		</div>
	</div>
	<script type="text/javascript" src="js/qy_touch.js"></script>
	<script type="text/javascript" src="js/m_common.js"></script>
	<section class="index1">
		<ul>
			<a href="javascript:fyInit('<s:property value="#attr.siteMenuList.{menuId}[0]"/>','1')"><li style="background:#8ed4d2"><s:property value="#attr.siteMenuList.{menuName}[0]" escapeHtml="false"/></li> </a>
			<a href="javascript:fyInit('<s:property value="#attr.siteMenuList.{menuId}[1]"/>','2')"><li style="background:#f8b55e"><s:property value="#attr.siteMenuList.{menuName}[1]" escapeHtml="false"/></li> </a>
		</ul>
	</section>
	<div style="clear:both"></div>
	<a href="javascript:getUrl('<s:property value="#attr.siteMenuList.{menuId}[2]"/>','3')">
		<section class="index2"><s:property value="#attr.siteMenuList.{menuName}[2]" escapeHtml="false"/></section> </a>
	<section class="index1">
		<ul>
			<a href="javascript:fyInit('<s:property value="#attr.siteMenuList.{menuId}[3]"/>','4')"><li style="background:#fcb1ba"><s:property value="#attr.siteMenuList.{menuName}[3]" escapeHtml="false"/></li> </a>
			<a href="javascript:getUrl('<s:property value="#attr.siteMenuList.{menuId}[4]"/>','5')"><li style="background:#8ef6d0"><s:property value="#attr.siteMenuList.{menuName}[4]" escapeHtml="false"/></li> </a>
		</ul>
	</section>
</body>
</html>
