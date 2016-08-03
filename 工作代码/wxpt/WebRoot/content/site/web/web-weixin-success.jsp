<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>微信通</title>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="cache-control" content="no-cache">
<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<script type="text/javascript"
	src="<%=basePath%>manager/js/jquery-1.8.0.min.js"></script>
<style type="text/css">
#activity-name {
	color: #000;
	font-size: 20px;
	font-weight: bold;
	word-break: normal;
	word-wrap: break-word;
}
</style>
<style type="text/css">
body,html,#allmap {
	width: 100%;
	height: 100%;
	overflow: hidden;
	margin: 0;
}

#l-map {
	height: 100%;
	width: 78%;
	float: left;
	border-right: 2px solid #bcbcbc;
}

#r-result {
	height: 100%;
	width: 20%;
	float: left;
}

dl,dt,dd,ul,li {
	margin: 0;
	padding: 0;
	list-style: none;
}

dt {
	font-size: 14px;
	font-family: "微软雅黑";
	font-weight: bold;
	border-bottom: 1px dotted #000;
	padding: 5px 0 5px 5px;
	margin: 5px 0;
}

dd {
	padding: 5px 0 0 5px;
}

li {
	line-height: 28px;
}
</style>
<script type="text/javascript"
	src="http://api.map.baidu.com/api?v=1.5&ak=ECe2b50ae093fe82dff717edec0e41e3"></script>

<script type="text/javascript"
	src="http://api.map.baidu.com/library/SearchInfoWindow/1.5/src/SearchInfoWindow_min.js"></script>
<link rel="stylesheet"
	href="http://api.map.baidu.com/library/SearchInfoWindow/1.5/src/SearchInfoWindow_min.css" />
<script type="text/javascript"
	src="http://developer.baidu.com/map/jsdemo/demo/convertor.js"></script>
<script type="text/javascript"
	src="http://developer.baidu.com/map/jsdemo/demo/changeMore.js"></script>

</head>

<body>
	<div data-role="content">


		<h1 id="activity-name">客户资料<a href="javascript:backMap()"><img src="http://www.uniqyw.com/wxpt/web/image/backMap.jpg" style="float: right;display: none;" id="backMap" ></a>
		</h1>

		<s:iterator value="customersList" var="customer" status="i">
			<input type="hidden" id="<s:property value="#i.index" />customersNo"
				value="<s:property value="#customer.customersNo" />" />
			<input type="hidden" id="<s:property value="#i.index" />locationY"
				value="<s:property value="#customer.locationY" />" />
			<input type="hidden" id="<s:property value="#i.index" />locationX"
				value="<s:property value="#customer.locationX" />" />
			 <div id="<s:property value="#i.index" />xiaoxi" style="margin:0;width:100%;heigh:200px;display: none;">
									<s:property value="#customer.customersName" /><br>
									<a href="javascript:checkImage(<s:property value="#i.index" />)">
									<img src="http://www.uniqyw.com/wxpt/web/images/<s:property value="#customer.customersImage" />" id="<s:property value="#i.index" />" alt="" style="float:right;zoom:1;overflow:hidden;width:100px;height:60px;margin-left:3px;"  /></a>
									地址：<s:property value="#customer.customersAddress" /><br>
									规格型号：<s:property value="#i.index" /><br>
									数量：共<s:property value="#customer.count" />台<br>
									客户介绍：<s:property value="#customer.introduce" /><br>
			 </div> 
		</s:iterator>
		<div id="allmap"></div>
		 <input type="hidden" id="type" value='<s:property value="type" />'> 
		 <input type="hidden" id="userXY" value="<s:property value="userXY" />" />
		 <input type="hidden" id="center" value="<s:property value="center" />" />
		 <input type="hidden" id="count" value="<s:property value="count" />">
	</div>

</body>

<script type="text/javascript">
	//已粉色当前位置为中心
	//new BMap.Point(116.404, 39.915)
	var j = 0;
	var map = new BMap.Map("allmap");
	if ($("#type").val() == 1) {
		var gpsPoint = new BMap.Point($("#userXY").val().split(",")[0], $(
				"#userXY").val().split(",")[1]);
		map.centerAndZoom(gpsPoint, 14);
	} else {

		map.centerAndZoom($("#center").val(), 12);
	}
	map.enableDoubleClickZoom();

	map.enableScrollWheelZoom();
	for ( var i = 0; i < ($("#count").val() * 1); i++) {
		
		var point = new BMap.Point($("#" + i + "locationY").val(), $(
				"#" + i + "locationX").val());
		addMarker(point, i);
	}
	var j = 0;
	var i = 0;
	function addMarker(point, index) {
		var opts = {
			position : point, // 指定文本标注所在的地理位置
			offset : new BMap.Size(1, 1),
		//设置文本偏移量

		};
		var label = new BMap.Label($("#" + index + "customersNo").val(), opts); // 创建文本标注对象
		label.setStyle({
			color : "black",
			width : "0px",
			fontSize : "12px",
			height : "0px",
			lineHeight : "20px",
			border : "none",
			//fontFamily : "微软雅黑",
			backgroundColor : "none",
		});
		label.setZIndex(index);
		var marker = new BMap.Marker(point);
		marker.setLabel(label);
		map.addOverlay(marker);
		map.addOverlay(marker.getLabel());
		marker
				.addEventListener(
						"click",
						function() {
							$("#"+j+"xiaoxi").hide();
							$("#"+index+"xiaoxi").show();
							j = index;
							i = index;
							$("#backMap").show();
						});

	}
	function backMap(){
		$("#"+i+"xiaoxi").hide();
		$("#backMap").hide();
	}
	function checkImage(index) {

		var width = $("#" + index).width();
		if (width == $("#" + index+"xiaoxi").width()) {
			$("#" + index).width(100);
			$("#" + index).height(60);
		} else {
			$("#" + index).width($("#" + index+"xiaoxi").width());
			$("#" + index).height($("#" + index+"xiaoxi").width()*2/3);
		}
	}
</script>

</html>

