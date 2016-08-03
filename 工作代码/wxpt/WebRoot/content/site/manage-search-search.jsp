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

<title>周边检索</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<title>周边检索</title>
<link rel="stylesheet" type="text/css" href="<%=basePath%>mange/css/ditu.css"/> 
<script language="javascript" src="http://webapi.amap.com/maps?v=1.2&key=be9045736dd333a8668b8c45e4bf33fe"></script>
<script language="javascript">
var mapObj;
var marker = new Array();
var windowsArr = new Array();
//中心点坐标
var x="${x}";
var y="${y}";

var cpoint = new AMap.LngLat(y,x);
function mapInit() {
    //加载地图
    mapObj = new AMap.Map("iCenter");
    //添加中心点
    var cmarker = new AMap.Marker({
        icon:new AMap.Icon({
            image:"http://www.amap.com/images/mark.png",
            size:new AMap.Size(20, 30),
            imageOffset:new AMap.Pixel(-32, 0)
        }),
        position:cpoint,
        offset : {
                x : -10,
                y : -30
        },
        map:mapObj
		
    });
	placeSearch();
}
//地点查询函数
function placeSearch() {
    var MSearch;
    //加载服务插件，实例化地点查询类  
    var citys="${city}";   
    mapObj.plugin(["AMap.PlaceSearch"], function() {
        MSearch = new AMap.PlaceSearch({ 
            city: "${city}"
        }); 
        //查询成功时的回调函数
        AMap.event.addListener(MSearch, "complete", placeSearch_CallBack); 
        //周边搜索
        MSearch.searchNearBy("${matter}", cpoint, 500); 
    });
}
//查询结果的marker和infowindow 
function addmarker(i, d) {
    var lngX = d.location.getLng();
    var latY = d.location.getLat();
    var markerOption = {
	    map:mapObj,
        icon:"http://webapi.amap.com/images/" + (i + 1) + ".png",
        position:new AMap.LngLat(lngX, latY)
    };
    var mar = new AMap.Marker(markerOption);
    marker.push(new AMap.LngLat(lngX, latY));

    var infoWindow = new AMap.InfoWindow({
        content:"<h3><font color=\"#00a6ac\">&nbsp;&nbsp;" + (i + 1) + "." + d.name + "</h3></font>" + TipContents(d.type, d.address, d.tel),
        size:new AMap.Size(300, 0), 
        autoMove:true, 
        offset:{x:0, y:-30}
    });
    windowsArr.push(infoWindow);
  
    var aa = function (e) {infoWindow.open(mapObj, mar.getPosition());};
    AMap.event.addListener(mar, "click", aa);
}
//回调函数 
function placeSearch_CallBack(data) {
    var resultStr = "";
    var poiArr = data.poiList.pois;
    var resultCount = data.poiList.pois.length;
    for (var i = 0; i < data.poiList.pois.length; i++) {
        resultStr += "<div id='divid" + (i + 1) + "' onmouseover='openMarkerTipById1(" + i + ",this)' onmouseout='onmouseout_MarkerStyle(" + (i + 1) + ",this)' style=\"font-size: 12px;cursor:pointer;padding:0px 0 4px 2px; border-bottom:1px solid #C1FFC1;\"><table><tr><td><img src=\"http://webapi.amap.com/images/" + (i + 1) + ".png\"></td>" + "<td><h3><font color=\"#00a6ac\">名称: " + poiArr[i].name + "</font></h3>";
        resultStr += TipContents(poiArr[i].type, poiArr[i].address, poiArr[i].tel) + "</td></tr></table></div>";
        addmarker(i, poiArr[i]);
    }
    mapObj.setFitView();
    document.getElementById("result").innerHTML = resultStr;
}
function TipContents(type, address, tel) {  //信息窗体内容
    if (type == "" || type == "undefined" || type == null || type == " undefined" || typeof type == "undefined") {
        type = "暂无";
    }
    if (address == "" || address == "undefined" || address == null || address == " undefined" || typeof address == "undefined") {
        address = "暂无";
    }
    if (tel == "" || tel == "undefined" || tel == null || tel == " undefined" || typeof address == "tel") {
        tel = "暂无";
    }
    var str = "&nbsp;&nbsp;地址：" + address + "<br />&nbsp;&nbsp;电话: " + tel + " <br />&nbsp;&nbsp;类型：" + type;
    return str;
}
//根据数组id打开搜索结果点tip
function openMarkerTipById1(pointid, thiss) {  
    thiss.style.background = '#CAE1FF';
    windowsArr[pointid].open(mapObj, marker[pointid]);
}
//鼠标移开后点样式恢复
function onmouseout_MarkerStyle(pointid, thiss) { 
    thiss.style.background = "";
}
</script>
</head>
<body onLoad="mapInit();">  
    <div id="iCenter" style="width: 100%; height: 50%"></div>
    <div class="demo_box">
        <!-- <p><input type="button" value="查询" onClick="placeSearch()"/><br /> -->
        </p>
        <div id="r_title"><b>中心点坐标周边查询结果:</b></div>
        <div id="result"> </div>
    </div>        
</body>
</html>