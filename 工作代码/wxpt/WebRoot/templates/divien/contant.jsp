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
    
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" href="css/m_style.css"/>
<script type="text/javascript" src="js/lib.min.js"></script>
<script src="js/jquery-1.8.3.js"></script>
<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no" />  <!--自适应宽度,并不允许缩放-->
<script mce_src="html://html5shim.googlecode.com/svn/trunk/html5.js"></script>
<script src="http://html5media.googlecode.com/svn/trunk/src/html5media.min.js"></script>
<link href="css/index.css" rel="stylesheet" type="text/css" />
<title>联系我们</title>
<script type="text/javascript">
	function getUrl(menuID, menuNamID) {
		var urlStr = window.location.href;
		var url = urlStr.split("!");
		if (menuID == 0) {
			window.location.href = url[0]+"!index?"+url[1].split("?")[1].split("&")[0];
		} else {
			
			window.location.href = url[0] + "!getMenuPage?"
			+ url[1].split("?")[1].split("&")[0] + "&menuNameID=" + menuNamID
					+ "&menuID=" + menuID;
			//$("#url").html(url[0]+"!getMenuPage?"+url[1].split("?")[1]+"&menuNamID="+menuNamID);
		}

	}
	function getMap(){
		var urlStr = window.location.href;
		var url = urlStr.split("!");
		window.location.href = url[0]+"!getMap?"+url[1].split("?")[1];
		//+"&menuNameID="+menuNamID +"&menuID="+menuID;
	}
	function getMsg(){
		var urlStr = window.location.href;
		var url = urlStr.split("!");
		window.location.href = url[0]+"!getMsg?"+url[1].split("?")[1];
	}
	function getShare(){
		var urlStr = window.location.href;
		var url = urlStr.split("!");
		window.location.href = url[0]+"!getShare?"+url[1].split("?")[1];
	}
</script>

<style>
	.title{width:100%; height:30px; background:#FFFFCC; text-align:center; font-weight:bold; padding-top:10px}
	section{margin-top:-15px;}
	address ul li{margin-left:10px; line-height:40px;}
</style>
</head>

<body>
<header> <img
		src="<%=filePath%>/<s:property value="logoName"/>" width="144" height="34"/> </header>
<div id="box_root" class ="pageIndex">
<div id="box_mainBody">
<div id="box_main" >
<header id="box_header" class="header">
<div class="columnSpace" id="elem-navigation" name="meun"> 
<div id="Columns_navigation" class="Columns_navigation">	
<nav class="navigation01" >
<div class="navBarL active" id="columnprev" onClick="goPrev();">				
<span ></span></div>
<div class="navBody" id="singleScroll">
<ul class="sc_scroller" id="singleScrollUl" style="width:6000px">
<li>
<span class="active" id="column1"><a href="javascript:getUrl(0,0)">首页</a></span>
</li>
<s:iterator status="i" value="siteMenuList" var="siteMenu">
<li><span class="active" id="column<s:property value="#i.index+1"/>" ><a href="javascript:getUrl(<s:property value="#siteMenu.menuId"/>,<s:property value="#i.index+1"/>)"><s:property value="#siteMenu.menuName" escapeHtml="false"/></a></span></li>
</s:iterator>
</ul>
</div>
<div class="navBarR active" id="columnnext" onClick="goNext();">
<span ></span></div>
</nav>
<script type="text/javascript">
function setColumnWidth(){getTotalWidth("1");getTotalWidth("2");getTotalWidth("3");getTotalWidth("4");getTotalWidth("5");$("#singleScrollUl").css("width",ulwidth);		
}
</script>

</div> 
</div>
</header>

﻿</div>
</div>
</div>
<script type="text/javascript" src="js/qy_touch.js"></script>
<script type="text/javascript" src="js/m_common.js"></script>
<section>
    <div class="title">联系我们</div>
</section>
<address>
	<ul>
		<li><s:property value="enterInfor.enterName" /></li>
		<li>地址：<s:property value="contactUs.address"/></li>
		<li>电话：<s:property value="contactUs.telephone"/></li>
		<li>邮箱:<s:property value="contactUs.email"/></li>
	</ul>
</address>
<jsp:include page="foot.jsp"/>
</body>
</html>
