<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'manage-gong-jiao.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>manage/css/ditu.css" />
<script language="javascript" src="http://webapi.amap.com/maps?v=1.2&key=be9045736dd333a8668b8c45e4bf33fe"></script>
<script language="javascript">
/*  
 * 该示例主要流程是分为三个步骤
 * 1. 首先调用公交换乘服务(transfer_route)
 * 2. 根据返回结果解析，拼接解析结果(transCallBack)
 * 3. 在地图上绘制导航线路()
 */
var mapObj; //地图对象
var btContent = new Array(); //结果表格数组
var resultStr;//结果拼接string

/*
 *初始化地图
 *param mapObj 地图对象
 */
function mapInit() {
var zhux = "${zhux}";
		var zhuy = "${zhuy}";
	var opts = {
		level : 13, //设置地图缩放级别36.69237517639;117.1299771838
		center : new AMap.LngLat(zhuy, zhux) //设置地图中心点
	}
	mapObj = new AMap.Map("iCenter", opts);
	transfer_route();
}
/*
 * 调用公交换乘服务
 * param Object trans 公交换乘服务实例
 */
function transfer_route() {
	var trans;
	//加载公交换乘插件
	mapObj.plugin(["AMap.Transfer"], function() {      
		transOptions = {
			city: "${city}",                            //公交城市
    		policy: AMap.TransferPolicy.LEAST_TIME //乘车策略
		};
		//构造公交换乘类
		trans = new AMap.Transfer (transOptions);
		//返回导航查询结果          
	    AMap.event.addListener(trans, "complete", transCallBack);
	    //显示错误信息
	    AMap.event.addListener(trans, "error", function(e) {alert(e.info);});
	    //根据起、终点坐标查询公交换乘路线36.69237517639;117.1299771838
			var starx = "${starx}";
	var stary = "${stary}";
	var endx = "${endx}";
	var endy = "${endy}";
		trans.search(new AMap.LngLat(stary,starx), new AMap.LngLat(endy,endx)); 
	});
}
 /*
 * 公交换乘服务返回数据解析概况
 * param Object  btCount       换乘方案总数
 * param Array[] btPlans       换乘方案数组
 * param Object  btOrigin      换乘起点
 * param Object  btDestination 换乘终点
 * param Object  btTaxiCost    全程打的花费
 * param Object  btType        查询状态
 * param Array[] BusArr        公交路径数组
 * param Array[] WalkArr       步行路径数组
 * param Array[] onbus         公交换乘点（上车站）数组
 * param Object  naviInfo      换乘段导航信息
 */
function transCallBack(data) {
	var btCount       = data.count; 
	var btPlans       = data.plans; 
	var btOrigin      = data.origin;
	var btDestination = data.destination;
	var btTaxiCost    = data.taxi_cost;
    var startName     = "${starName}"; //可以使用地理编码解析起点和终点坐标
    var endName       = "${endName}"; 
 	var BusArr        = [];
 	var WalkArr		  = [];
 	var onbus         = new Array();
	var onwalk        = new Array();
 	//结果输出用表格展现，输出表格头
	var resTableHeader = "<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"><tr><td width=\"150\" style=\" border-left:1px solid #fff; background:#e1e1e1;\">　乘车方案</td><td width=\"300\" style=\" border-left:1px solid #fff; background:#e1e1e1;\">　导航信息</td></tr>";
	btContent.push(resTableHeader); 	
	//遍历每种换乘方案
	for (var i = 0; i < btPlans.length; i++) {
	 	var btDistance  = btPlans[i].distance;
	 	var btseg       = btPlans[i].segments;
	 	var lineNameArr = new Array();
	 	var resLine     = "";
	 	var naviInfo    = '';
	 	var lineName;
	 	for(var j = 0; j < btseg.length; j++) {
	 		naviInfo += btseg[j].instruction + "<br/>";
	 		if(btseg[j].transit_mode =="WALK") {
	 			if(i===0) {
	 				WalkArr.push(btseg[j].transit.path);
	 			}
	 		}
	 		else {
	 			lineName = btseg[j].transit.lines[0].name;
	 			lineNameArr.push(lineName);
	 			if(i===0) {
	 				BusArr.push(btseg[j].transit.path);
	 			}
	 		}		    
	 	}
	 	lineName = lineNameArr.join("-->");
	 	drawBuschangeLine(btOrigin,btDestination,BusArr,WalkArr);
	 	//结果输出用表格展现，输出表格内容
		resLine = "<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">"
		+ "<tr><td width=\"150\" class=\"change_blue\">"+ lineName +"</td><td width=\"300\" class=\"change_blue\" >"
		+ Getdistance(btDistance) + "</td></tr>" + "<tr><td width=\"150\" class=\"change_blue\" ></td><td width=\"300\"><img src=\"http://webapi.amap.com/images/start.gif\" /> <b>起点</b> " + startName + "</td></tr>" + "<tr><td width=\"150\" class=\"change_blue\"></td><td width=\"300\" class=\"change_blue\">"+ naviInfo +"</td></tr>"+ "<tr><td width=\"150\" class=\"change_blue\" ></td><td width=\"300\"><img src=\"http://webapi.amap.com/images/end.gif\" /> <b>终点</b> " + endName + "</td></tr>";
		btContent.push(resLine);
	 }
	resultStr = btContent.join("");
	 //写到result这个div
	document.getElementById("result").innerHTML = resultStr;
	//取出需要加换乘、步行图标的位置，这里仅画出第一个换乘方案
	var sinseg = btPlans[0].segments;
	for(var a in sinseg) {
 		if(sinseg[a].transit_mode =="WALK") {
 			onwalk.push(sinseg[a].transit.origin);
 		}
 		else {
 			onbus.push(sinseg[a].transit.on_station.location);
 		}
	}
	addMarker(onbus);
	mapObj.setFitView();	
}
//距离，米换算为千米
function Getdistance(len) {
	if (len <= 1000) {
		var s = len;
		return s + "米";
	} else {
		var s = Math.round(len / 1000);
		return "约" + s + "公里";
	}
}
//绘制路线，仅第一条
function drawBuschangeLine(startPot,endPot,BusArr,WalkArr) {
	//自定义起点，终点图标
	 var sicon = new AMap.Icon({  
        image: "http://www.amap.com/images/poi.png",  
        size: new AMap.Size(44,44),  
        imageOffset: new AMap.Pixel(-334, -180)  
    }); 
     var eicon = new AMap.Icon({  
        image: "http://www.amap.com/images/poi.png",  
        size: new AMap.Size(44,44),  
        imageOffset: new AMap.Pixel(-334, -134)  
    }); 
    //绘制起点，终点
	var stmarker = new AMap.Marker({
		map:mapObj,
		position:new AMap.LngLat(startPot.lng,startPot.lat), //基点位置
		icon:sicon, //复杂图标
		offset:{x:-16,y:-34} //相对于基点的位置
	});
	var endmarker = new AMap.Marker({
		map:mapObj,
		position:new AMap.LngLat(endPot.lng,endPot.lat), //基点位置
		icon:eicon, //复杂图标
		offset:{x:-16,y:-34} //相对于基点的位置
	});
	//绘制乘车的路线
	for(var j in BusArr) {
		busPolyline = new AMap.Polyline({
			map:mapObj,
			path:BusArr[j],
			strokeColor:"#005cb5",//线颜色
			strokeOpacity:0.8,//线透明度
			strokeWeight:6//线宽
		});
	}
	//绘制步行的路线
	for (var i in WalkArr) {
		walkPolyline = new AMap.Polyline({
			map:mapObj,
			path:WalkArr[i],
			strokeColor : "#6EB034", //线颜色
			strokeOpacity : 0.6, //线透明度
			strokeWeight : 6//线宽

		});
	}
	
}
function addMarker(busmar) {
	for (var i = 0; i < busmar.length; i++) {
		var busmarker = new AMap.Marker({
			icon : new AMap.Icon({
				image: "http://www.amap.com/images/busroute.png",
				size: new AMap.Size(20, 20),
				imageOffset: new AMap.Pixel(-33, -3)
			}),
			position : busmar[i],
			offset : {
				x : -25,
				y : -25
			},
			map:mapObj
		});
	}
}
</script>
  </head>
  
  <body onLoad="mapInit();">
    <div id="iCenter"  style="width:100%; height:50%;"></div>
    <div class="demo_box"> 
           <!--  <input type="button" value="公交换乘查询" onClick="transfer_route()"/> -->
        <div id="r_title"><b>公交换乘查询结果展示</b></div>
        <div id="result"> </div>
    </div>
</body>
</html>
