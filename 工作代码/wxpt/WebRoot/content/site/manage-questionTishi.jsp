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
<script type="text/javascript" src="../manager/js/questionTishi.js"></script>
<script type="text/javascript" src="../manager/js/ajaxfileupload.js"></script>
<script type="text/javascript" language="javascript"
	src="../manager/js/WebCalendar.js"></script>



<style type="text/css">
#serchTest,#edit,#add,#ff,#addUser,#adduser,#add,#toolbar {
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

.ww {
	border: 1px solid #26A0DA
}
</style>

</head>
<body style="background-color:white;padding:0px;">

	<table style="float:left;" id="tt">
	</table>

	<div id="toolbar" class="test">

		<table width="100%">
			<tr>
				<td></td>
				<td><table align="right">
						<tr>
							<td><a href="javascript:AddGrade()"><div
										style="padding-top: 5px;"></div>&nbsp;<span class="icon-edit">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;设置规则</span>&nbsp;&nbsp;</a>
							</td>
							<td><a href="javascript:UpdateGrade()"><div
										style="padding-top: 5px;"></div>&nbsp;<span class="icon-add">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;修改规则</span>&nbsp;&nbsp;</a>
							</td>
							<!-- <td><a href="javascript:DeleteGrade()"><div
										style="padding-top: 5px;"></div>&nbsp;<span
									class="icon-remove">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;删除等级</span>&nbsp;&nbsp;</a>
							</td> -->


						</tr>
					</table>
				</td>
			</tr>
		</table>
	</div>
	<form id="addUser" target="hidden_frame" enctype="multipart/form-data"
		method="post">

		<div class="xx21">
			<div
				style="margin-top: 5px; margin-bottom: 5px; margin-left: 10px;*margin-top:10px; *margin-bottom: 10px; *margin-left: 15px;">
				<table>
					<tr style="line-height: 20px;">
						<td><span>未中奖:</span></td>
						<td><textarea rows="5" cols="30" id="wei" name="wei"></textarea></td>
					</tr>
					<tr style="line-height: 20px;">
						<td><span>答错：</span>
						</td>
						<td><textarea rows="5" cols="30" id="dacuo" name="dacuo"></textarea>
						</td>
					</tr>
					<tr style="line-height: 20px;">
						<td><span>超时：</span>
						</td>
						<td><textarea rows="5" cols="30" id="chaoshi" name="chaoshi"></textarea></td>
					</tr>
					<tr style="line-height: 20px;">
						<td><span>已抽奖：</span>
						</td>
						<td><textarea rows="5" cols="30" id="choujiang" name="choujiang"></textarea></td>
					</tr>
					<tr style="line-height: 20px;">
						<td><span>答过：</span>
						</td>
						<td><textarea rows="5" cols="30" id="daguo" name="daguo"></textarea></td>
					</tr>
					<input type="hidden" id="tishiId" name="tishiId">
				</table>
			</div>

		</div>
	</form>

	<div id="updateuser" class="easyui-window" title="修改提示设置"
		style=" top:20px;left:380px;overflow:hidden;background:#E2EDFF;width:275px;height:auto;"
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
				style="position:relative;top:-15px;*top:-22px;" onclick="close1();">取消</a>
		</div>
	</div>



	<div id="add" class="easyui-window" title="提示设置"
		style=" top:20px;left:380px;overflow:hidden;background:#E2EDFF;width:275px;height:auto;"
		iconCls="icon-edit" closed="true" maximizable="false"
		minimizable="false" collapsible="false">
		<div id="add1"></div>
		<div>&nbsp;</div>
		<div align="center">
			<a class="easyui-linkbutton" iconCls="icon-ok"
				href="javascript:void(0)"
				style="position:relative;top:-15px;*top:-22px;" onClick="add()">添加</a>
			<a class="easyui-linkbutton" iconCls="icon-cancel"
				href="javascript:void(0)"
				style="position:relative;top:-15px;*top:-22px;" onclick="close1();">取消</a>
		</div>
	</div>

</body>
</html>