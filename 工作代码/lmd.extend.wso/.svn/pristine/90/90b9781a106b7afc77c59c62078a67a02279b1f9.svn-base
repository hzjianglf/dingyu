<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <title>Pie 3D Chart</title>
        <link href="assets/ui/css/style.css" rel="stylesheet" type="text/css" />
        <link href="assets/prettify/prettify.css" rel="stylesheet" type="text/css" />
        <script type="text/javascript" src="Charts/jquery.min.js"></script>
        <script type="text/javascript" src="Charts/FusionCharts.js"></script>
        <script type="text/javascript" src="assets/prettify/prettify.js"></script>
        <script type="text/javascript" src="assets/ui/js/json2.js"></script>
        <script type="text/javascript" src="assets/ui/js/lib.js" ></script>

   <div id="chartdiv2" ></div>
        <script type="text/javascript">
        $(function(){
        	var queryStr={};
        	$.ajax({   
                type: "post",
                url: "falsh/pie.jsp",
                data: queryStr,   
                dataType: "text",
                success: callback
            });
            function callback(data){   
        	    var chart = new FusionCharts("Charts/Pie3D.swf", "ChartId", "100%", "360px", "0", "0");
     		   chart.setXMLData( data );		   
     		   chart.render("chartdiv2");
        	}   
        });
           	</script>
     </body>
 </html>
