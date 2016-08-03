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
<link href="<%=basePath%>manager/js/kindeditor/themes/default/default.css"/>
<script src="<%=basePath%>manager/js/kindeditor/kindeditor.js" type="text/javascript"></script>
<script src="<%=basePath%>manager/js/kindeditor/kindeditor/lang/zh_CN.js" type="text/javascript"></script>

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
<script type="text/javascript" src="../manager/js/page.js"></script>


<style type="text/css">
#toolbar,#cardType,#card_tt,#card_toolbar,#cardTypeForm,#addCardType,#updateCardType,#cardForm,#updateCard,#addCard,#cardUrl,#cardUrlForm,#errorUrlForm,#errorUrl
	{
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

<body>
	<table style="float:left;" id="tt">
	</table>
	<div id="toolbar" class="test">
		<table width="100%">
			<tr>
				<td>
					<table align="right">
						<tr>
							<td><a href="javascript:add()">
									<div style="padding-top: 5px;"></div>&nbsp;<span
									class="icon-add">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;添加</span>&nbsp;&nbsp;</a>
							</td>
							<td><a href="javascript:update()"><div
										style="padding-top: 5px;"></div>&nbsp;<span class="icon-edit">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;修改</span>&nbsp;&nbsp;</a>
							</td>
							<td><a href="javascript:deletePage()"><div
										style="padding-top: 5px;"></div>&nbsp;<span class="icon-edit">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;删除</span>&nbsp;&nbsp;</a>
							</td>
							<td><a href="javascript:lookCard()"><div
										style="padding-top: 5px;"></div>&nbsp;<span class="icon-edit">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;查看链接</span>&nbsp;&nbsp;</a>
							</td>
						</tr>
					</table></td>
			</tr>
		</table>
	</div>	
	<form id="cardForm" target="hidden_frame" enctype="multipart/form-data"
		method="post">
		<div class="xx21">
			<table style="margin-top:10px;margin-bottom:10px; margin-left:10px;">
				<tr style="display: none;">
					<td>文章标题</td>
					<td><input type="text" id="pageTitle" name="pageTitle" style="width: 200px;"/></td>
				</tr>
				<tr style="display: none;">
					<td>作&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;者&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
					<td><input type="text" id="pageAutor" name="pageAutor" style="width: 200px;"/></td>
				</tr >
				<tr style="display: none;">
					<td>账号名称</td>
					<td><input type="text" id="pageNoName" name="pageNoName" style="width: 200px;"/></td>
				</tr>
				<tr style="display: none;">
					<td valign="top">
						概&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;述&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</td>
					<td><textarea id="pageMetaValue" name="pageMetaValue"
							rows="5" cols="50" class="ww"></textarea></td>
				</tr>
				<tr style="display: none;">
					<td valign="top">
						内&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;容&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</td>
					<td id="idmetaValue"><textarea id="pageContent" name="pageContent"
							rows="5" cols="50" class="ww"></textarea></td>
				</tr>
				<tr style="display: none;">
					<td>文章配图&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
					<td><input type="file" id="pageImageFile" name="pageImageFile" /><font
						style="color: red">*360像素 * 200像素</font>
					</td>
				</tr>
				<tr style="display: none;">
					<td>原文链接</td>
					<td><input type="text" id="pageUrl" name="pageUrl" style="width: 400px;" value="http://"/>
					</td>
				</tr>
				<tr>
					<td>分享链接</td>
					<td><input type="text" id="pageJumpUrl" name="pageJumpUrl" style="width: 500px;" value="http://"/>
					</td>
				</tr>
				<tr>
					<td><input type="hidden" name="pageId" id="pageId" />
					</td>
				</tr>
			</table>
		</div>
	</form>
	<div id="addCard" class="easyui-window" title="添加"
		style="top:50px; ;overflow:hidden;background:#E2EDFF;width:650px;height:140px;[;height:140px;];*height:140px;height:140px\9\0;"
		iconCls="icon-add" data-options="modal:true,closed:true"
		maximizable="false" closed="true" maximizable="false"
		minimizable="false" collapsible="false">
		<div id="addCardEeXL"></div>
		<div>&nbsp;</div>
		<div align="center">
			<a class="easyui-linkbutton" iconCls="icon-ok"
				href="javascript:void(0)"
				style="position:relative;top:-15px; *top:-22px;" onClick="AddPage()">添加</a>
			<a class="easyui-linkbutton" iconCls="icon-cancel"
				href="javascript:void(0)"
				style="position:relative;top:-15px;*top:-22px;"
				onClick="closeWindow('addCard')">取消</a>
		</div>
	</div>
	<div id="updateCard" class="easyui-window" title="修改"
		style="top:150px; ;overflow:hidden;background:#E2EDFF;width:650px;height:140px;[;height:140px;];*height:140px;height:140px\9\0;"
		iconCls="icon-add" data-options="modal:true,closed:true"
		maximizable="false" closed="true" maximizable="false"
		minimizable="false" collapsible="false">
		<div id="updateCardEeXL"></div>
		<div>&nbsp;</div>
		<div align="center">
			<a class="easyui-linkbutton" iconCls="icon-ok"
				href="javascript:void(0)"
				style="position:relative;top:-15px; *top:-22px;"
				onClick="cardUpdate()">修改</a> <a class="easyui-linkbutton"
				iconCls="icon-cancel" href="javascript:void(0)"
				style="position:relative;top:-15px;*top:-22px;"
				onClick="closeWindow('updateCard')">取消</a>
		</div>
	</div>
	<div id="cardUrl" class="easyui-window" title="访问路径" 
		style="top:100px; ;overflow:hidden;background:#E2EDFF;width:500px;height:100px;[;height:100px;];*height:100px;height:100px\9\0;"
		iconCls="icon-add" data-options="modal:true,closed:true"
		maximizable="false" closed="true" maximizable="false"
		minimizable="false" collapsible="false">
		<div id="eeXL1"></div>
		<div>&nbsp;</div>
	</div>
	<form id="cardUrlForm" target="hidden_frame"
		enctype="multipart/form-data" method="post">
		<div class="xx21" >
			<div>
				<table>
					<tr>
						<td>
							<span id="urlStr"></span>
						</td>
					</tr>
				</table>
			</div>
		</div>
	</form>
</body>
</html>
