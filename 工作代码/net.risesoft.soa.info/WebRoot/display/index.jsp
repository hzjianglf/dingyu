<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@page import="net.risesoft.soa.info.tools.InformationList"%>
<%@page import="java.util.List"%>
<%@page import="net.risesoft.soa.info.model.Information"%>
<%@page import="net.risesoft.soa.info.util.ConfigUtil"%>
<%
String infoID = request.getParameter("infoID");
String width = request.getParameter("width");
if (width == null){
	width = "960";
}
String search = request.getParameter("search");
if (search == null){
	search = "";
}

String name = "";
Information	information = InformationList.get(infoID);
if (information != null){
	name = information.getName();
}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>资源发布</title>
<LINK type="text/css" href="/info/css/style.css" rel="stylesheet" >
<LINK type="text/css" href="/jquery/css/jquery-ui-1.8.23.custom.css" rel="stylesheet" >
<script type="text/javascript" src="/jquery/js/jquery-1.8.2.min.js"></script>
<script type="text/javascript" language="javascript" src="/jquery/js/jquery-ui-1.8.23.custom.min.js"></script>
<script type="text/javascript" src="/info/ckeditor/ckeditor.js"></script>
<STYLE type="text/css">
.headWrap{-moz-box-shadow:0 1px 2px rgba(23,87,117,0.5);-webkit-box-shadow:0 1px 2px rgba(23,87,117,0.5);box-shadow:0 1px 2px rgba(23,87,117,0.5)}

.headWrap .headShadow{ opacity:.9; border-bottom:#2596c2 1px solid; background-color:#36a4d2;background:-webkit-gradient(linear,left top,left bottom,from(#45addd),to(#279cc8));background:-moz-linear-gradient(top,#45addd,#279cc8);background:transparent\9;filter:progid:DXImageTransform.Microsoft.gradient(startColorstr='#45addd',endColorstr='#279cc8');background:-ms-linear-gradient(top,#45addd,#279cc8);}
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
</style>
</head>
<body class="body">
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
<table align="center" cellpadding="0" cellspacing="0" width="<%=width%>" border="0" style="height: 600px; border-radius: 5px 5px 5px; background-color: rgb(255, 255, 255);">
	<tr>
		<td align="left" height="40" style="border-bottom: 1px solid #D1D1D1; padding-left: 20px;"><font class="font1"><%=name%></font></td>
		<td align="right" style="border-bottom: 1px solid #D1D1D1; padding-right: 20px;">&nbsp;<a id="returnLink" href="javascript:returnHome();" class="link" style="display:none;">返回列表</a></td>
	</tr>
	<tr>
		<td colspan=2 align="center" valign="top" height="100%"><DIV id="infoMainDiv"></DIV></td>
	</tr>
</table>
</DIV>
<script language="javascript">
$("#infoMainDiv").load("/info/display/panel.jsp", {"infoID":"<%=infoID%>", "search":"<%=search%>"});
function returnHome(){
	$("#returnLink").hide();
	jQuery( "*", $("#infoMainDiv")).add([$("#infoMainDiv")]).each(function(){
		jQuery.event.remove(this);
		jQuery.removeData(this);
	});
	$("#infoMainDiv").innerHTML = "";
	$("#infoMainDiv").empty();
	$("#infoMainDiv").load("/info/display/panel.jsp", {"infoID":"<%=infoID%>", "search":"<%=search%>"});
}
</script>
</body>
</html>

