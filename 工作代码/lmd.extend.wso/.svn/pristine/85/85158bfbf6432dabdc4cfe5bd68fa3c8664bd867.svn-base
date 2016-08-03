<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<html xmlns="http://www.w3.org/1999/xhtml">
 <head>
        <title>Pie 3D Chart</title>
        <link href="assets/ui/css/style.css" rel="stylesheet" type="text/css" />
        <link href="assets/prettify/prettify.css" rel="stylesheet" type="text/css" />
        <!-- <script type="text/javascript" src="Charts/jquery.min.js"></script> -->
        <script src="../js/jquery-1.8.0.min.js"></script>
        <script type="text/javascript" src="Charts/FusionCharts.js"></script>
        <script type="text/javascript" src="assets/prettify/prettify.js"></script>
        <script type="text/javascript" src="assets/ui/js/json2.js"></script>
        <script type="text/javascript" src="assets/ui/js/lib.js" ></script>
    </head>   
<%
   String  width = request.getParameter("width")==null?"400px":request.getParameter("width");
   String  height = request.getParameter("height")==null?"350px":request.getParameter("height");
%>
<body>
   <div id="chartdiv2" ></div>
        <script type="text/javascript">
	        $(function(){
	        	var queryStr={};
	        	$.ajax({   
	                type: "post",
	               // url: "pie.jsp",
	                url: "../pie.do",
	                data: queryStr,   
	                dataType: "text",
	                success: callback
	            });
	            function callback(data){   
	        	    var chart = new FusionCharts("Charts/Pie2Ds.swf", "ChartId", "<%=width%>", "<%=height%>", "0", "0");
	     		   chart.setXMLData( data );		   
	     		   chart.render("chartdiv2");
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
        </script>
     </body>
 </html>
