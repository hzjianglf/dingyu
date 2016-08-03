<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
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

<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=ECe2b50ae093fe82dff717edec0e41e3"></script>

<style type="text/css">
body, html,#allmap {
	/* [disabled]width: 100%; */
	height: 100%;
	overflow: hidden;
	margin: 0;
}
</style>
<link rel="stylesheet" href="css/m_style.css"/>
<script type="text/javascript" src="js/lib.min.js"></script>
<script src="js/jquery-1.8.3.js"></script>
<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no" />  <!--自适应宽度,并不允许缩放-->
<script mce_src="html://html5shim.googlecode.com/svn/trunk/html5.js"></script>
<script src="http://html5media.googlecode.com/svn/trunk/src/html5media.min.js"></script>
<link href="css/index.css" rel="stylesheet" type="text/css" />
<title>联系我们</title>
<script type="text/javascript">
function getUrl(menuID,menuNamID){
	var urlStr = window.location.href;
	var url = urlStr.split("!");
	if(menuID == 0){
		window.location.href=url[0]+"!index?"+url[1].split("?")[1];
	}else{
		
		window.location.href = url[0]+"!getMenuPage?"+url[1].split("?")[1]+"&menuNameID="+menuNamID +"&menuID="+menuID;
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
<div id="allmap"></div>
<jsp:include page="foot.jsp"/>
<script type="text/javascript">
	var url = window.location.href;
	var center = url.split("=");
	// 百度地图API功能
	var map = new BMap.Map("allmap");   
	 // 创建Map实例
	 //var point = new BMap.Point(center[1].split(",")[0],center[1].split(",")[1]);
	var point = new BMap.Point(<s:property value="contactUs.localtion"/>); 
	map.centerAndZoom(point, 17);
    
	//var marker1 = new BMap.Marker(new BMap.Point(center[1].split(",")[0],center[1].split(",")[1]));  // 创建标注
	var marker1 = new BMap.Marker(new BMap.Point(<s:property value="contactUs.localtion"/>));
	map.addOverlay(marker1); 
	//创建信息窗口
	var infoWindow1 = new BMap.InfoWindow("榜棚街1号华鲁大厦5楼");
	//marker1.addEventListener("click", function(){this.openInfoWindow(infoWindow1);});
	

	map.addControl(new BMap.NavigationControl());  //添加默认缩放平移控件
	map.addControl(new BMap.NavigationControl({anchor: BMAP_ANCHOR_TOP_RIGHT, type: BMAP_NAVIGATION_CONTROL_SMALL}));  //右上角，仅包含平移和缩放按钮
	map.addControl(new BMap.NavigationControl({anchor: BMAP_ANCHOR_BOTTOM_LEFT, type: BMAP_NAVIGATION_CONTROL_PAN}));  //左下角，仅包含平移按钮
	map.enableScrollWheelZoom();

	map.addControl(new BMap.OverviewMapControl());              //添加默认缩略地图控件
	//map.addControl(new BMap.OverviewMapControl({isOpen:true, anchor: BMAP_NAVIGATION_CONTROL_ZOOM}));   //右上角，打开
</script>
</body>
</html>
