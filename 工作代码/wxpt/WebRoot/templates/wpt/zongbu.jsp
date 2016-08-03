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
<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
<style type="text/css">
body, html,#allmap {width: 100%;height: 500px;overflow: hidden;margin:0;}
</style>
<div id="allmap"></div>
<script type="text/javascript">

	// 百度地图API功能
	var map = new BMap.Map("allmap");            // 创建Map实例
	var point = new BMap.Point(<s:property value="contactUs.localtion"/>); 
	map.centerAndZoom(point, 17);
    
	var marker1 = new BMap.Marker(new BMap.Point(<s:property value="contactUs.localtion"/>));  // 创建标注
	map.addOverlay(marker1); 
	//创建信息窗口
	//var infoWindow1 = new BMap.InfoWindow("榜棚街1号华鲁大厦5楼");
	//marker1.addEventListener("click", function(){this.openInfoWindow(infoWindow1);});
	

	//map.addControl(new BMap.NavigationControl());  //添加默认缩放平移控件
	//map.addControl(new BMap.NavigationControl({anchor: BMAP_ANCHOR_TOP_RIGHT, type: BMAP_NAVIGATION_CONTROL_SMALL}));  //右上角，仅包含平移和缩放按钮
	//map.addControl(new BMap.NavigationControl({anchor: BMAP_ANCHOR_BOTTOM_LEFT, type: BMAP_NAVIGATION_CONTROL_PAN}));  //左下角，仅包含平移按钮
	//map.enableScrollWheelZoom();

	//map.addControl(new BMap.OverviewMapControl());              //添加默认缩略地图控件
	//map.addControl(new BMap.OverviewMapControl({isOpen:true, anchor: BMAP_NAVIGATION_CONTROL_ZOOM}));   //右上角，打开
</script>    