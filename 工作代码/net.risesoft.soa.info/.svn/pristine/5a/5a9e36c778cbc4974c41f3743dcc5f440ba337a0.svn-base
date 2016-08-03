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
<STYLE type="text/css">
.body {
    background: url("images/wrapbg_v0.0.1.jpg") no-repeat scroll center top #73CFF1;
    color: #333333;
    font: 12px/1.75 Tahoma,Arial,sans-serif,宋体;
    height: 100%;
}
</style>
</head>
<body class="body">
<table id="infoTable" align="center" cellpadding="0" cellspacing="0" border="0" width="<%=width%>" style="border-radius: 5px 5px 5px; background-color: rgb(255, 255, 255);">
	<tr>
		<td align="left" height="40" style="border-bottom: 1px solid #D1D1D1; padding-left: 20px;"><font class="font1"><%=name%></font></td>
		<td align="right" style="border-bottom: 1px solid #D1D1D1; padding-right: 20px;">&nbsp;<a id="returnLink" href="javascript:returnHome();" class="link" style="display:none;">返回列表</a></td>
	</tr>
	<tr>
		<td colspan=2 align="center" valign="top" height="100%"><DIV id="infoMainDiv"></DIV></td>
	</tr>
</table>
<div id="dialog_success_message" title="提示信息" style="display:none;">
	<table border=0>
		<tr>
			<td style="width:40px; height:40px; background: url(/info/images/Valid.png) no-repeat center center;" align="center"></td>
			<td id="message_success_div" style="font-size:12pt;"></td>
		</tr>
	</table>
</div>
<div id="dialog_cancel_message" title="提示信息" style="display:none;">
	<table border=0>
		<tr>
			<td style="width:40px; height:40px; background: url(/info/images/Warning_2.png) no-repeat center center;" align="center"></td>
			<td id="message_cancel_div" style="font-size:12pt;"></td>
		</tr>
	</table>
</div>
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

$(document).ready(function() { 
	$(function() {
		$( "#dialog_success_message" ).dialog({
			autoOpen: false,
			modal: true,
			resizable: false,
			buttons: {
				"关闭": function() {
					$( this ).dialog( "close" );
				}
			}
		});
		$( "#dialog_cancel_message" ).dialog({
			autoOpen: false,
			modal: true,
			resizable: false,
			buttons: {
				"关闭": function() {
					$( this ).dialog( "close" );
				}
			}
		});
	});
}); 
</script>
</body>
</html>

