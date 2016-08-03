<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script type="text/javascript" src="../../js/jquery-1.8.0.min.js"></script>

<title>首页</title> 
<link id="show"  href="css/main.css" type="text/css" rel="stylesheet">


</head>

<body id="body" style="background:none; background:url(images/rmdl.png)">


</body>
<script type="text/javascript">
<%
String cspath="";
%>
var swid = screen.width;
var url;
if(swid==1024){
	 document.getElementById('show').href="css/main_two.css";
	url="../../../home_two.html";
}else{
	 document.getElementById('show').href="css/main_three.css";
	 url="../../../home_three.html";
}
$(function(){
	var queryStr={
	        "id":"_JElqYINYEeW-bO0waJfjrQ"
	    };
	$.ajax({   
        type: "post",
        url: url,
        data: queryStr,   
        dataType: "text",
        success: function callback(data){ 
        	var x=document.getElementById("body");
        	  x.innerHTML=data;
 		}
     });
});

function show(url){
	window.location.href=url;
}
</script>
</html>

