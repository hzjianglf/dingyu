<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'manage-gong-xian.jsp' starting page</title>
    
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
 * 该示例主要流程分为三个步骤
 * 1. 首先调用公交路线查询服务(lineSearch)
 * 2. 根据返回结果解析，输出解析结果(lineSearch_Callback)
 * 3. 在地图上绘制公交线路()
 */
var mapObj; //地图对象
var resLine  = ''; //结果表格对象

function mapInit() {
    var opts = {
        level:13, //设置地图缩放级别
        center:new AMap.LngLat(116.397428, 39.90923) //设置地图中心点
    }
    mapObj = new AMap.Map("iCenter", opts);
    mapclear();lineSearch();
}
/*
 *清空地图覆盖物与查询结果
 */
function mapclear() {
    btContent = '';
    resLine   = '';
    mapObj.clearMap();
}
/*
 *公交线路查询
 */
function lineSearch() {
    //加载公交线路查询插件
    //实例化公交线路查询类，只取回一条路线
    mapObj.plugin(["AMap.LineSearch"], function() {
       var linesearch = new AMap.LineSearch({
            pageIndex:1,
            city:"${city}",
            pageSize:1,
            extensions:'all'
        });
        //搜索“536”相关公交线路
        linesearch.search('${che}');
        AMap.event.addListener(linesearch, "complete", lineSearch_Callback);
        AMap.event.addListener(linesearch, "error", function(e){alert(e.info);
        });
    });
}
 /*
 * 公交路线查询服务返回数据解析概况
 * param Array[]  lineArr     返回公交线路总数
 * param String   lineName    公交线路名称
 * param String   lineCity    公交所在城市
 * param String   company     公交所属公司
 * param Number   stime       首班车时间
 * param Number   etime       末班车时间
 * param Number   bprice      公交起步票价
 * param Number   tprice      公交全程票价
 * param Array[]  pathArr     公交线路路径数组
 */
function lineSearch_Callback(data) {
    var lineArr  = data.lineInfo;
    var lineNum  = data.lineInfo.length;
    if(lineNum == 0) {
        resLine = data.info;
    }
    else {
        resLine += "<div id=\"divid" + i + "\"><table>";
        for(var i = 0; i < lineNum; i++) {
            var lineName = lineArr[i].name;
            var lineCity = lineArr[i].city;
            var distance = lineArr[i].distance;
            var company  = lineArr[i].company;
            var stime    = lineArr[i].stime;
            var etime    = lineArr[i].etime;
            var pathArr  = lineArr[i].path;
            var stops    = lineArr[i].via_stops;
            var startPot = stops[0].location;
            var endPot   = stops[stops.length-1].location;
            //结果输出用DIV展现，输出内容
            resLine += "<tr><td><h3><font color=\"#00a6ac\">" + lineName + "</font></h3></td></tr>";
            resLine += "<tr><td>首末车时间：" + stime.substring(0, 2) + ":" + stime.substring(2, 4) + '-' + etime.substring(0, 2) + ":" + etime.substring(2, 4) + "；" + "全长：" + distance + "公里;" + "所属公司：" + company+"</td></tr>";
           //绘制第一条路线
            if(i==0) drawbusLine(startPot,endPot,pathArr);
        }
        resLine += "</table></div>"

        document.getElementById('result').innerHTML = resLine;
    }
}

/*
 *绘制路线
 */
function drawbusLine(startPot,endPot,BusArr) {
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
        offset:{x:-16,y:-34}, //相对于基点的位置
        zIndex:10
    });
    var endmarker = new AMap.Marker({
        map:mapObj,
        position:new AMap.LngLat(endPot.lng,endPot.lat), //基点位置
        icon:eicon, //复杂图标
        offset:{x:-16,y:-34}, //相对于基点的位置
        zIndex:10
    });
    //绘制乘车的路线
        busPolyline = new AMap.Polyline({
            map:mapObj,
            path:BusArr,
            strokeColor:"#005cb5",//线颜色
            strokeOpacity:0.8,//线透明度
            strokeWeight:6//线宽
        });
        mapObj.setFitView();
}
</script>
  </head>
  
  <body onLoad="mapInit();">
    <div id="iCenter" style="height:50%; width:100%"></div>
    <!--<input type="button" value="公交线路查询" onClick="mapclear();lineSearch()" style="margin-left:5px"/>-->
    <div id="r_title"><b>查询结果仅返回第一条:</b></div>
    <div id="result"></div>
</body>
</html>
