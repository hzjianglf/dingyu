<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="../manager/css/default.css">
<link rel="stylesheet" type="text/css" href="../manager/themes/icon.css">
<link rel="stylesheet" type="text/css" href="../manager/css/demo.css">
<link id="easyuiTheme" rel="stylesheet"
	href="../manager/themes/default/easyui.css" type="text/css">
<script type="text/javascript" src="../manager/js/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="../manager/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="../manager/js/jquery.cookie.js"></script>
<script type="text/javascript" src="../manager/js/hd.js"></script>
<script type="text/javascript" src="../manager/js/jquery.form.js"></script>
<script type="text/javascript" src="../manager/js/mouth-statistic.js"></script>
<script type="text/javascript" language="javascript"
	src="../manager/js/WebCalendar.js"></script>
<script type="text/javascript" src="../manager/js/cookie.js"></script>

<style type="text/css">
#toolbar,#orderAdd,#user,#temp,#ff,#add {
	visibility: hidden;
}

.serchTest {
	_margin-top: -20px;
	_margin-bottom: -18px;
}

*+html #serchTest {
	*margin-top: -20px;
	*margin-bottom: -18px;
}

.test a{vertical-align:center;display: block;float: left; height: 20px;padding-bottom: 2px;border: 1px solid #EFEFEF;}
		.test a:link{text-decoration: none; color:#000;}
		.test a:active{text-decoration: none;color:#000}
		.test a:visited{text-decoration: none;color:#000}
		.test a:hover{text-decoration: none;color:#000; background:#eaf2ff; border: 1px solid #dddddd;}    
#user,#temp{
	visibility: hidden;
}
#toolbar,#orderAdd,#ff,#temp,#user{
				visibility: hidden;
}
.xx21{margin:10px 10px 10px 10px; border:1px solid #91ABD1;border-radius:8px;}
.ww{border:1px solid #95B8E7}
</style>
</head>
<body style="background-color:white;padding:0px;">
	<table style="float:left;" id="order">
	</table>
	<div id="toolbar" class="test">
		<table width="100%">
					<table>
						<tr>
							<td>	</td>
						</tr>
					</table>
		</table>
	</div>
	
	
<form id="orderAdd" method="post">
	<div class="xx21">
		<table style="margin-left:10px; margin-top:5px; margin-bottom:5px;" align="center">
			<tr style="line-height: 20px;" >
				<td colspan="2">
					<div>
						受 &nbsp;&nbsp;&nbsp;访 &nbsp;&nbsp;页&nbsp;&nbsp;&nbsp; 面： <span id="page"
							name="page" size="20" />
					</div>
				</td>
			</tr>
			<tr style="line-height: 20px;">
				<td>
					<div style="width:350px;">
						浏&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;览&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;量： <span id="pageviews"
							name="pageviews" size="20"></span>
					</div></td>
				<td><div style="width:350px;">
						访&nbsp;&nbsp;&nbsp;&nbsp; 客 &nbsp;&nbsp;&nbsp;&nbsp;数： <span id="visitors" name="visitors" size="20"></span>
					</div></td>
			</tr>
			<tr style="line-height: 20px;">
				<td>
					<div>
						ip&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;数： <span
							id="ips" name="ips" size="20"></span>
					</div></td>
				<td><div>
						域&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名： <span id="domain" name="domain" size="20"></span>
						
					</div></td>
			</tr>
			<tr style="line-height: 20px;">
				<td>
					<div>
						统&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;计&nbsp;&nbsp;日&nbsp;&nbsp;&nbsp;&nbsp;期： <span id="recordTime" name="recordTime" size="20"></span>
						
					</div></td>
				<td><div>
					退&nbsp;&nbsp;&nbsp;&nbsp; 出 &nbsp;&nbsp;&nbsp;&nbsp;率： <span id="exitRate" name="exitRate" size="20"></span>
					</div></td>
			</tr>
			<tr style="line-height: 20px;">
				<td>
					<div>
						平 均 停 留 时 间： <span id="stayTime" name="stayTime" size="20"></span>
					</div></td>
				<td><div>
						入 口 页 次 数： <span id="entrances" name="entrances" size="20"></span>
					</div></td>
			</tr>
			<tr style="line-height: 20px;">
				<td>
					<div>
						
						贡 献 下 游 流 量： <span id="outwards" name="outwards" size="20"></span>
						
					</div></td>
				<td><div>
						退 出 页 次 数： <span id="exits" name="exits" size="20"></span>
					</div></td>
			</tr>
		</table>
		</div>
	</form>
	
	<div id="edit" class="easyui-window" title="查看详细信息"
		style="overflow:hidden;background:#E2EDFF;width:700px;*width:800px;height:auto;" iconCls="icon-edit"
		closed="true" maximizable="false" minimizable="false"
		data-options="modal:true,closed:true" minimizable="false"
		collapsible="false">
		<div id="ee"></div>
		<div>&nbsp;</div>
	</div>	
		
</body>
</html>
