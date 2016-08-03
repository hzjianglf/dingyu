<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>驾车导航</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>manage/css/ditu.css" />
<script language="javascript"
	src="http://webapi.amap.com/maps?v=1.2&key=be9045736dd333a8668b8c45e4bf33fe"></script>
<script type="text/javascript">
	var mapObj;
	var route_text, steps;
	var polyline;
	//基本地图加载
	/* $(document).ready(function(){  
	//集成IM  
	var employeeSN = "${starx}";
	alert(employeeSN);  
	EimUtil.RunEim(employeeSN);  
	alert(EimUtil.RunEim(employeeSN));
	});  */
	function mapInit() {
		var zhux = "${zhux}";
		var zhuy = "${zhuy}";

		var opt = {
			level : 13, //设置地图缩放级别29  
			center : new AMap.LngLat(zhuy, zhux)
		//设置地图中心点 
		}
		mapObj = new AMap.Map("iCenter", opt);
		driving_route();
	}

	//起、终点
	var starx = "${starx}";
	var stary = "${stary}";
	var endx = "${endx}";
	var endy = "${endy}";
	var start_xy = new AMap.LngLat(stary, starx);
	var end_xy = new AMap.LngLat(endy, endx);

	//驾车导航
	function driving_route() {

		var MDrive;
		mapObj.plugin([ "AMap.Driving" ], function() {
			var DrivingOption = {
				//驾车策略，包括 LEAST_TIME，LEAST_FEE, LEAST_DISTANCE,REAL_TRAFFIC
				policy : AMap.DrivingPolicy.LEAST_TIME
			};
			MDrive = new AMap.Driving(DrivingOption); //构造驾车导航类 
			AMap.event.addListener(MDrive, "complete", driving_routeCallBack); //返回导航查询结果
			MDrive.search(start_xy, end_xy); //根据起终点坐标规划驾车路线
		});
	}
	//导航结果展示
	function driving_routeCallBack(data) {
		console.log(data);
		var routeS = data.routes;
		if (routeS.length <= 0) {
			document.getElementById("result").innerHTML = "未查找到任何结果!<br />建议：<br />1.请确保所有字词拼写正确。<br />2.尝试不同的关键字。<br />3.尝试更宽泛的关键字。";
		} else {
			route_text = "";
			for ( var v = 0; v < routeS.length; v++) {
				//驾车步骤数
				steps = routeS[v].steps
				var route_count = steps.length;
				//行车距离（米）
				var distance = routeS[v].distance;
				//拼接输出html
				for ( var i = 0; i < steps.length; i++) {
					route_text += "<tr><td align=\"left\" onMouseover=\"driveDrawFoldline('"
							+ i
							+ "')\">"
							+ i
							+ "."
							+ steps[i].instruction
							+ "</td></tr>";
				}
			}
			//输出行车路线指示
			route_text = "<table cellspacing=\"5px\"><tr><td style=\"background:#e1e1e1;\">路线</td></tr><tr><td><img src=\"http://code.mapabc.com/images/start.gif\" />&nbsp;&nbsp;${starName}</td></tr>"
					+ route_text
					+ "<tr><td><img src=\"http://code.mapabc.com/images/end.gif\" />&nbsp;&nbsp;${endName}</td></tr></table>";
			document.getElementById("result").innerHTML = route_text;
			drivingDrawLine();
		}
	}
	//绘制驾车导航路线
	function drivingDrawLine(s) {
		//起点、终点图标
		var sicon = new AMap.Icon({
			image : "http://www.amap.com/images/poi.png",
			size : new AMap.Size(44, 44),
			imageOffset : new AMap.Pixel(-334, -180)
		});
		var startmarker = new AMap.Marker({
			icon : sicon, //复杂图标
			visible : true,
			position : start_xy,
			map : mapObj,
			offset : {
				x : -16,
				y : -40
			}
		});
		var eicon = new AMap.Icon({
			image : "http://www.amap.com/images/poi.png",
			size : new AMap.Size(44, 44),
			imageOffset : new AMap.Pixel(-334, -134)
		});
		var endmarker = new AMap.Marker({
			icon : eicon, //复杂图标
			visible : true,
			position : end_xy,
			map : mapObj,
			offset : {
				x : -16,
				y : -40
			}
		});
		//起点到路线的起点 路线的终点到终点 绘制无道路部分
		var extra_path1 = new Array();
		extra_path1.push(start_xy);
		extra_path1.push(steps[0].path[0]);
		var extra_line1 = new AMap.Polyline({
			map : mapObj,
			path : extra_path1,
			strokeColor : "#9400D3",
			strokeOpacity : 0.7,
			strokeWeight : 4,
			strokeStyle : "dashed",
			strokeDasharray : [ 10, 5 ]
		});

		var extra_path2 = new Array();
		var path_xy = steps[(steps.length - 1)].path;
		extra_path2.push(end_xy);
		extra_path2.push(path_xy[(path_xy.length - 1)]);
		var extra_line2 = new AMap.Polyline({
			map : mapObj,
			path : extra_path2,
			strokeColor : "#9400D3",
			strokeOpacity : 0.7,
			strokeWeight : 4,
			strokeStyle : "dashed",
			strokeDasharray : [ 10, 5 ]
		});

		var drawpath = new Array();
		for ( var s = 0; s < steps.length; s++) {
			drawpath = steps[s].path;
			var polyline = new AMap.Polyline({
				map : mapObj,
				path : drawpath,
				strokeColor : "#9400D3",
				strokeOpacity : 0.7,
				strokeWeight : 4,
				strokeDasharray : [ 10, 5 ]
			});
		}
		mapObj.setFitView();
	}
	//绘制驾车导航路段
	function driveDrawFoldline(num) {
		var drawpath1 = new Array();
		drawpath1 = steps[num].path;
		if (polyline != null) {
			polyline.setMap(null);
		}
		polyline = new AMap.Polyline({
			map : mapObj,
			path : drawpath1,
			strokeColor : "#FF3030",
			strokeOpacity : 0.9,
			strokeWeight : 4,
			strokeDasharray : [ 10, 5 ]
		});

		mapObj.setFitView(polyline);
	}
</script>
</head>
<body onLoad="mapInit();">
	<div id="iCenter" style="width: 100%; height: 50%"></div>
	<div class="demo_box">
		<!-- <input type="button" value="驾车导航查询" onClick="driving_route()" /> -->
		<div id="r_title">
			<b>查询结果:</b>
		</div>
		<div id="result"></div>
	</div>
	<input type="hidden" value="${starx}" id="starx">
	<input type="hidden" value="${stary}" id="stary">
	<input type="hidden" value="${endx}" id="endx">
	<input type="hidden" value="${endy}" id="endy">
</body>

</html>
