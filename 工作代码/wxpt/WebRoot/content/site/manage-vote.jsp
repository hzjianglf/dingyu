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
<jsp:include page="testCookie.jsp" flush="true" />
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
<script type="text/javascript" src="../manager/js/vote.js"></script>
<script type="text/javascript" language="javascript"
	src="../manager/js/WebCalendar.js"></script>

<style type="text/css">
#serchTest,#edit,#add,#ff,#addUser,#adduser,#updateuser,#toolbar{
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

.test a {
	vertical-align: center;
	display: block;
	float: left;
	height: 20px;
	padding-bottom: 2px;
	border: 1px solid #EFEFEF;
}

.test a:link {
	text-decoration: none;
	color: #000;
}

.test a:active {
	text-decoration: none;
	color: #000
}

.test a:visited {
	text-decoration: none;
	color: #000
}

.test a:hover {
	text-decoration: none;
	color: #000;
	background: #eaf2ff;
	border: 1px solid #dddddd;
}

.xx21 {
	margin: 10px 10px 10px 10px;
	border: 1px solid #91ABD1;
	border-radius: 8px;
	
}
.ww{border:1px solid #26A0DA}
</style>

</head>
<body style="background-color:white;padding:0px;">

	<table style="float:left;" id="tt">
	</table>

	<div id="toolbar" class="test">

		<table width="100%">
			<tr>
				<td><form id="query"><table align="left">
						<tr>
							<td>
							<font style="color:#3967a3 ">选手姓名:</font><input type="text" name="queryName" id="queryName" class="ww">
							</td>

							<td><span>
							<font style="color:#3967a3 ">投票时间:</font></span>
<input name="startTime" id="startTime" type="text"  class="ww" id="date" onclick="SelectDate(this,'yyyy/MM/dd');" value=""
														style="border: 1;">--
<input name="endTime" id="endTime" type="text" id="date"  class="ww" onclick="SelectDate(this,'yyyy/MM/dd');" value=""
														style="border: 1;">
							</td>
							<td align="left"><a href="javascript:query()"><div style="padding-top: 5px;"></div>&nbsp;<span
												class="icon-search">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;查询</span>&nbsp;&nbsp;</a>
							</td>
						</tr>
					</table></form>
				</td>
				<td><table align="right">
						<tr>
							<td><a href="javascript:AddUser()"><div
										style="padding-top: 5px;"></div>&nbsp;<span class="icon-add">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;添加</span>&nbsp;&nbsp;</a>
							</td>
							<td><a href="javascript:UpdateUser()"><div
										style="padding-top: 5px;"></div>&nbsp;<span class="icon-edit">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;修改</span>&nbsp;&nbsp;</a>
							</td>

							<td><a href="javascript:DeleteUser()"><div
										style="padding-top: 5px;"></div>&nbsp;<span
									class="icon-remove">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;删除</span>&nbsp;&nbsp;</a>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</div>

	<form id="addUser" target="hidden_frame" enctype="multipart/form-data"
		method="post">
		<div class="xx21">
			<div style="margin-top: 5px; margin-bottom: 5px; margin-left: 10px;*margin-top:10px; *margin-bottom: 10px; *margin-left: 15px;">
			<table
>
				<tr style="line-height: 20px;">
					<td><span >编号:</span></td>
					<td><input type="text" name="userNum" id="userNum" class="ww"/>
					</td>
				</tr>
				<tr style="line-height: 20px;">
					<td><span>姓名：</span>
					</td>
					<td><input type="text" name="userName"
						id="userName"  class="ww" /></td>
				</tr>
			</table>
			</div>
		</div>
		<input type="hidden" id="userId" name="userId">
	</form>
	<div id="updateuser" class="easyui-window" title="修改"
		style=" top:20px;left:380px;overflow:hidden;background:#E2EDFF;width:250px;height:153px;[;height:158px;];*height:165px;"
		iconCls="icon-edit" closed="true" maximizable="false"
		minimizable="false" collapsible="false">
		<div id="update"></div>
		<div>&nbsp;</div>
		<div align="center">
			<a class="easyui-linkbutton" iconCls="icon-ok"
				href="javascript:void(0)"
				style="position:relative;top:-15px;*top:-22px;" onClick="update()">修改</a>
			<a class="easyui-linkbutton" iconCls="icon-cancel"
				href="javascript:void(0)"
				style="position:relative;top:-15px;*top:-22px;" onClick="close1()">取消</a>
		</div>
	</div>
	<div id="adduser" class="easyui-window" title="添加选手"
		style=" top:20px;left:380px;;overflow:hidden;background:#E2EDFF;width:250px;height:153px;[;height:158px;];*height:165px;"
		iconCls="icon-add" data-options="modal:true,closed:true"
		maximizable="false" closed="true" maximizable="false"
		minimizable="false" collapsible="false">
		<div id="eeXL"></div>
		<div>&nbsp;</div>
		<div align="center">
			<a class="easyui-linkbutton" iconCls="icon-ok"
				href="javascript:void(0)"
				style="position:relative;top:-15px;*top:-23px;"
				onClick="add()">添加</a> <a class="easyui-linkbutton"
				iconCls="icon-cancel" href="javascript:void(0)"
				style="position:relative;top:-15px;*top:-23px;" onClick="close1()">取消</a>
		</div>
	</div>
	<div id="lan" class="easyui-window" title="查看关键字"
		style="padding:0;width:550px;height:350px;" iconCls="icon-save"
		closed="true" maximizable="false"
		data-options="modal:true,closed:true" minimizable="false"
		collapsible="false">
		<table style="float:left;" id="la">
		</table>
		<div id="toolbar1" class="test">

			<table width="100%">
				<tr>
					<td><span style="color:#3967a3 " id="userNamename"></span>
					</td>
				</tr>
			</table>
		</div>
	</div>
</body>
</html>