<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%
String infoType = request.getParameter("infoType");
String name = request.getParameter("name");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>应急预案查询</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<LINK rel="stylesheet" type="text/css" href="css/pagination.css">
<script type="text/javascript" src="js/pagination.js"></script>
<style type="text/css">
	.input{
		border: 1px solid #ccd0d0;
		height: 18px;
		line-height: 20px;
		padding-left: 5px;
		vertical-align:middle;
	}
	.listDataTableStyle{
		
	}
	.listDataTableStyle table{
		width:100%;		
		line-height:30px;
		font-size: 12px;
	}
	.listDataTableStyle table .changeColor{
		background-color: #F5F5F5;
	}
	.listDataTableStyle table thead{
		background: none repeat scroll 0 0 #F5F5F5;
	}
	.listDataTableStyle table tr:hover{
		background-color: #C0C0C0;
	}
	.listDataTableStyle table tr th{
		border-right: 1px solid #E5E5E5;
		border-bottom: 1px solid #E5E5E5;
		/*border-top: 1px solid #E5E5E5;*/
		/*padding-left: 5px;*/
		text-align: center;
		font-size: 14px;
		/*font-weight:bold;*/
	}
	.listDataTableStyle table tr td{
		border-right: 1px solid #E5E5E5;
		/*padding-left: 5px;*/
		font-size: 14px;
		border-bottom: 1px solid #E5E5E5;
	}
</style>
</head>
<body>
<input type="hidden" id="infoType" value="<%=infoType %>"/>
<table border=0 width=100% height=100%>
	<tr>
		<td align="center">
		<table width="96%" border="0" align="center" cellspacing="0" style="vertical-align:top;" height=35 >
			<tr>
				<td align="left" colspan=2 height=35 style="background:url(images/bullet_green.png) no-repeat left center; padding-left:20px; border-bottom:1px solid #d6d6d6;"><%=name%></td>
			</tr>
		</table>
		</td>
	</tr>
	<tr>
		<td align="center" valign=top style="font-size:12pt;">
		<div style="width: 100%;height: 15px"></div>
 		<table border="0" bgcolor="#eeeeee">
			<tr style="font-size:10pt;">
				<td width=90 align=center>发布时间段:</td>
				<td align=left style="padding-left:5px;">
					<input type="text" style="width:100px;" id="startTime" class="input" value=""/>--
					<input type="text" style="width:100px;" id="endTime" class="input" value=""/>
				</td>
				<td width=70 align=center>标题:</td>
				<td align=left style="padding-left:5px;" >
					<input type="text" style="width:100px;" id="title" class="input" value=""/>
				</td>
				<td width=60 align="center" rowspan=2><img src="<%=request.getContextPath()%>/images/search.png" alt="查询" title="查询" height=28 width=28 border=0 style="cursor: pointer;" onclick="javascript:inform_search();"></td>
			</tr>
		</table>
		<div>
			<div id="search_list"></div>
			<DIV id="Pagination" class="pagination" style="float: right; padding-right: 20px; padding-top: 10px; padding-bottom: 10px;"></DIV>
		</div>
		</td>
	</tr>
</table>
<script>
var pageSize = Math.floor((_leftWinHeight - 200)/40);
if (pageSize < 10){
	pageSize = 10;
}
$(document).ready(function(){
	$('#startTime').datepicker({showButtonPanel: true, showWeek: true, numberOfMonths: 2, showOtherMonths: true, selectOtherMonths: true, dateFormat: 'yy-mm-dd'});
	$('#endTime').datepicker({showButtonPanel: true, showWeek: true, numberOfMonths: 2, showOtherMonths: true, selectOtherMonths: true, dateFormat: 'yy-mm-dd'});
	inform_search();
});
function inform_search(){
	//$("#inform_list").mask('装载中...');
	$("#search_list").empty();
	$("#search_list").load("/info/infoSearchList.action",{"infoType":$("#infoType").val(),"startTime": $("#startTime").val(),"endTime": $("#endTime").val(),"title": $("#title").val(),"pageSize" :pageSize});
}

</script>
</html>