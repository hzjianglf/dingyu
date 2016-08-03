<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Calendar"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html xmlns="http://www.w3.org/1999/xhtml">
 <head>
        <title>需求统计</title>
        <link href="assets/ui/css/style.css" rel="stylesheet" type="text/css" />
        <link href="assets/prettify/prettify.css" rel="stylesheet" type="text/css" />
        <!-- <script type="text/javascript" src="Charts/jquery.min.js"></script> -->
        <script src="../js/jquery-1.8.0.min.js"></script>
        
        <style type="text/css">@import "../css/jquery.datepick.css";</style> 
		<script type="text/javascript" src="../js/jquery.datepick.js"></script>
		<script type="text/javascript" src="../js/jquery.datepick-zh-CN.js"></script>
       
        <script type="text/javascript" src="Charts/FusionCharts.js"></script>
        <script type="text/javascript" src="assets/prettify/prettify.js"></script>
        <script type="text/javascript" src="assets/ui/js/json2.js"></script>
         <script type="text/javascript" src="assets/ui/js/lib.js" ></script>
    </head>   

<body>
<%Calendar day = Calendar.getInstance();
SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
String date = sdf.format(day.getTime()); %>
<div> 选择时间<input type="text" value="<%=date %>" id="dateId" onchange="refresh(this)"></div>
   <div id="chartdiv" ></div>
         <script type="text/javascript" src="Data/String/js/MSLine4.js" ></script>
        <script type="text/javascript">
 		$(function(){
 			$('#dateId').datepick({dateFormat: 'yy-mm-dd'}); 
        	var queryStr={};
        	$.ajax({   
                type: "post",
               // url: "xqdata.jsp",
                url: "../mnicontro/xqtj.do",
                data: queryStr,   
                dataType: "text",
                success: callback
            });
            function callback(data){ 
            	var chart = new FusionCharts('Charts/MSLine.swf', 'ChartId', '100%', '90%', '0', '0');
     		   chart.setXMLData( data);		   
     		   chart.render("chartdiv");
        	}   
        	
        });
            
 		  var fulls = "left=0,screenX=0,top=0,screenY=0,scrollbars=1";    //定义弹出窗口的参数
 		  if (window.screen) {
 		     var ah = screen.availHeight - 30;
 		     var aw = screen.availWidth - 10;
 		     fulls += ",height=" + ah;
 		     fulls += ",innerHeight=" + ah;
 		     fulls += ",width=" + aw;
 		     fulls += ",innerWidth=" + aw;
 		     fulls += ",resizable"
 		 } else {
 		     fulls += ",resizable"; // 对于不支持screen属性的浏览器，可以手工进行最大化。 manually
 		 }
 		 function openUrl(url){
 		  window.open(url,"",fulls);
 		 }
 		var chart = new FusionCharts('Charts/MSLine.swf', 'ChartId', '100%', '90%', '0', '0');
 		function refresh(obj){
 			$.ajax({   
                type: "post",
                url: "../mnicontro/xqtj.do",
                data: {"date":obj.value},   
                dataType: "text",
                success: function(data){
          		   chart.setXMLData(data);		   
          		   chart.render("chartdiv");
                }
            });
 		}
 		
 		
		</script>
     </body>
 </html>
