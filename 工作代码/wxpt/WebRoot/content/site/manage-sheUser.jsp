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
<script type="text/javascript" src="../manager/js/sheUser.js"></script>
<script type="text/javascript" src="../manager/js/ajaxfileupload.js"></script>

<style type="text/css">
#serchTest,#edit,#add,#ff,#lan,#addExplicit,#updateQuestion,#add1,#ff1,#addexplicit,#updateexplicit ,#showImg{
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
							<td><a href="javascript:UpdateUser()"><div
										style="padding-top: 5px;"></div>&nbsp;<span class="icon-add">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;修改</span>&nbsp;&nbsp;</a>
							</td>
						</tr>
					</table></td>
			</tr>
		</table>
	</div>
	
	<form id="addUser" style="visibility: hidden;" target="hidden_frame" enctype="multipart/form-data"
		method="post">
		<div class="xx21">
			<table
				style="margin-top: 5px; margin-bottom: 5px; margin-left: 10px;">
				
				<!-- <tr style="line-height: 20px;">
					<td><span>FansName：</span>
					</td>
					<td><input type="text" name="fansName"
						id="fansName" /></td>
				</tr> -->
				<tr style="line-height: 20px;">
					<td><span>昵称：</span>
					</td>
					<td><input type="text" name="nickname"
						id="nickname"  style="width: 140px"/></td>
				</tr>
				<tr style="line-height: 20px;">
					<td><span>个性签名：</span>
					</td>
					<td><textarea name="signature" id="signature" style="width: 140px"></textarea></td>
				</tr>
				<tr style="line-height: 20px;">
					<td rowspan="2"><input type="file" name="touxiang" id="touxiang"/>
					</td>
				</tr>
				<tr>
				<td>
		           <a href="javascript:uploads()" style="text-decoration: none; color: #000">
	            <div style="padding-top: 5px;"></div>&nbsp;<span class="icon-save">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;上传</span>&nbsp;&nbsp;</a>
			
					</td>
				</tr>
				
			</table>
		</div>
		<input type="hidden" id="fansName" name="fansName">
	</form>
	<div id="updateuser" scroll="no" class="easyui-window" title="修改"
		style="overflow:hidden;background:#E2EDFF;width:450px; width:410px\9; height:200px; height:190px\9;"
		iconCls="icon-edit"  data-options="modal:true,closed:true" closed="true" maximizable="false"
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
	
	
	
	
	
	
	
	
	
	
	<div id="showImg" align="center" class="easyui-window" title="查看大图" style="padding: 0px;height:300px;width:500px; "
    iconCls="icon-search" closed="true" data-options="modal:true,closed:true" maximizable="false" minimizable="false" collapsible="false">
     <span id="imgshow" class="showimage"></span>
    </div>
</body>
</html>