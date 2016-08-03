<%@page import="net.risesoft.soa.ac.model.Resource"%>
<%@page import="net.risesoft.soa.ac.manager.ResourceManager"%>
<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="net.risesoft.soa.info.tools.SpringUtil"%>
<%
String id = request.getParameter("id");
ResourceManager resourceManager = SpringUtil.getBean("resourceManager");
Resource resource = resourceManager.getResource(id);
String name = resource.getName();
if (name.length() > 6){
	name = name.substring(6);
}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>信息发布</title>
<LINK rel="stylesheet" type="text/css" href="../css/style.css">
<LINK type="text/css" href="/jquery/css/jquery-ui-1.8.23.custom.css" rel="stylesheet" >
<script type="text/javascript" src="/jquery/js/jquery-1.8.2.min.js"></script>
<script type="text/javascript" language="javascript" src="/jquery/js/jquery-ui-1.8.23.custom.min.js"></script>
<STYLE type="text/css">
.headWrap{-moz-box-shadow:0 1px 2px rgba(23,87,117,0.5);-webkit-box-shadow:0 1px 2px rgba(23,87,117,0.5);box-shadow:0 1px 2px rgba(23,87,117,0.5)}

.headWrap .headShadow{ opacity:.9; border-bottom:#2596c2 1px solid; background-color:#36a4d2;background:-webkit-gradient(linear,left top,left bottom,from(#45addd),to(#279cc8));background:-moz-linear-gradient(top,#45addd,#279cc8);background:transparent\9;filter:progid:DXImageTransform.Microsoft.gradient(startColorstr='#45addd',endColorstr='#279cc8');background:-ms-linear-gradient(top,#45addd,#279cc8);}
.active{ background-color:#0177a7}

.link:hover {
	color: #006A92;
	text-decoration: none;
}
.link:focus {
	color: #006A92;
	text-decoration: none;
}
.link {
	font: 14px "SimSun";
	text-decoration: none;
	color: rgb(179, 179, 179);
}
.class_button{
	cursor: pointer;
	font-size: 11pt;
	color: #006A92;
	background:url(../images/left_button.png) no-repeat center center;
}
.class_button:hover{
	font-size:11pt;
	color: #006A92;
	background:url(../images/left_button_hover.png) no-repeat center center;
}
.class_button_check{
	background:url(../images/left_button_hover.png) no-repeat center center;
}
.active1{ background:url(../images/left_hover.png)}
.body {
    background: url("../images/wrapbg_v0.0.1.jpg") no-repeat scroll center top #73CFF1;
    color: #333333;
    font: 12px/1.75 Tahoma,Arial,sans-serif,宋体;
    height: 100%;
}
.png{
	filter: progid:DXImageTransform.Microsoft.AlphaImageLoader(enabled=true, sizingMethod=noscale, src="../css/images/info.png");
	background:none;
	width:118px;
}
</STYLE>
</head>
<body class="body">
<DIV class="w_head_outer">
<DIV class="headWrap">
<DIV class="headInside">
<H1></H1>
<UL id="topNav" class="topNav right">
  		<LI class="topNavItem" style="_cursor: pointer; cursor: pointer;"><A><U></U></A></LI>
</UL>
</DIV>
<DIV class="headShadow">
<div id="headWrap">
<div class="png"></div>
<span style="left: 149px; top:8px; position: absolute;font-size: 14px;color: #FEFEFE;height: 18px;line-height: 18px;"></span>
</div>
</DIV></DIV></DIV>
<DIV id="topWrap"></DIV>
<DIV id="mainWrapper" class="clear">
<table width="100%" border="0" align="center" cellspacing="0" style="vertical-align:top">
  <tr>
  	<td height="100%">
  		<table id="leftList_table" border=0 cellpadding="0" cellspacing="0" align="center" width="100%" style="background-color: #e0e9e9;border: 10px solid #e0e9e9; border-radius: 0px 0px 5px 5px; -moz-border-radius:0px 0px 5px 5px; -webkit-border-radius:0px 0px 5px 5px;">
  		<tr>
  			<td width=178 valign="top">
  				<table border=0 cellpadding="0" cellspacing="0">
  					<tr>
  						<td width=178 height=42 style="background:url(../images/head.png) no-repeat center center;" align="center">
  							<table border=0>
  								<tr>
  									<td><img src="../images/info1.png" broder=0 alt="" align="absmiddle"></td>
  									<td style="color:#FFFFFF; font-weight:bold; font-size:14pt; *padding-top:3px; padding-top:3px \0;"><%=name%></td>
  								</tr>
  							</table>
  						</td>
  					</tr>
  					<tr>
  						<td style="background-color: #FFFFFF; border:1px solid #ccd0d0; border-radius: 0px 0px 5px 5px; -moz-border-radius:0px 0px 5px 5px; -webkit-border-radius:0px 0px 5px 5px;" align=center height="100%" >
  							<table border=0 cellpadding="0" cellspacing="0" >
  								<tr>
  									<td height="10">&nbsp;</td>
  								</tr>
  								<tr>
  									<td id="left_list_td" align="center" width=147 valign="top" style="background:url(../images/left_bak.png); border-top:1px solid #dbe6e6; border-bottom:1px solid #dbe6e6;">
  										<div id="leftList_div"></div>
  									</td>
  								</tr>
  								<tr>
  									<td height="10">&nbsp;</td>
  								</tr>
  							</table>
  							<br>
  						</td>
  					</tr>
  				</table>
  			</td>
  			<td width=10><br><br></td>
  			<td valign=top style="background-color: #ffffff; border:1px solid #ccd0d0; border-radius: 5px; -moz-border-radius:5px; -webkit-border-radius:5px;">
  			  	<div id="infoShow_div"></div>
  			</td>
  		</tr>
  		</table>
  	</td>
  </tr>
</table>
</DIV>
<script>
var _leftWinWidth = 0;  
var _leftWinHeight = 0; 
//获取浏览器高、宽
function findDimensions() {  
	//获取窗口宽度  
	if (window.innerWidth)  
		_leftWinWidth = window.innerWidth;  
	else if ((document.body) && (document.body.clientWidth))  
		_leftWinWidth = document.body.clientWidth;  
	//获取窗口高度  
	if (window.innerHeight)  
		_leftWinHeight = window.innerHeight;  
	else if ((document.body) && (document.body.clientHeight))  
		_leftWinHeight = document.body.clientHeight;  
	//通过深入Document内部对body进行检测，获取窗口大小  
	if (document.documentElement && document.documentElement.clientHeight && document.documentElement.clientWidth){  
		_leftWinHeight = document.documentElement.clientHeight;  
		_leftWinWidth = document.documentElement.clientWidth;  
	}
	$("#left_list_td").attr("height", _leftWinHeight - 190 + "px");
	//$("#orgTree").attr("style","height:" + (_leftWinHeight - 160) + "px; overflow:auto; padding: 10px 0 0 10px; width: 240px;")
}

$(document).ready(function() {

	jQuery.ajaxSetup ({cache:false});
	
	findDimensions();  
	//调用函数，获取数值  
	window.onresize = findDimensions; 
	
	$("#leftList_div").load("list.jsp",{"id":"<%=id%>"});
	
});

</script>
</body>
</html>


