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
<script src="../manager/js/TQEditor/TQEditor.full.min.js"
	type="text/javascript"></script>
<script src="../manager/js/TQEditor/TQEditor.min.js"
	type="text/javascript"></script>
<link href="../manager/js/TQEditor/TQEditor.css" rel="stylesheet"
	type="text/css" />
<link href="../manager/js/TQEditor/QuirksMode.css" rel="stylesheet"
	type="text/css" />
<script src="../manager/js/TQEditor/TQEditor.full.min.js"
	type="text/javascript"></script>
<script src="../manager/js/TQEditor/TQEditor.min.js"
	type="text/javascript"></script>
<link href="../manager/js/TQEditor/TQEditor.css" rel="stylesheet"
	type="text/css" />
<link href="../manager/js/TQEditor/QuirksMode.css" rel="stylesheet"
	type="text/css" />

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
<script type="text/javascript" src="../manager/js/greetingCard.js"></script>
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
					<table align="left">
						<tr>
							<td><a href="javascript:cardTypeEdit()">
									<div style="padding-top: 5px;"></div>&nbsp;<span
									class="icon-add">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;查看</span>&nbsp;&nbsp;</a>
							</td>
						</tr>
					</table></td>
				<td>
					<table align="right">
						<tr>
							<td><a href="javascript:addCard()">
									<div style="padding-top: 5px;"></div>&nbsp;<span
									class="icon-add">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;添加</span>&nbsp;&nbsp;</a>
							</td>
							<td><a href="javascript:updateCard()"><div
										style="padding-top: 5px;"></div>&nbsp;<span class="icon-edit">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;修改</span>&nbsp;&nbsp;</a>
							</td>
							<td><a href="javascript:deleteCard()"><div
										style="padding-top: 5px;"></div>&nbsp;<span class="icon-edit">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;删除</span>&nbsp;&nbsp;</a>
							</td>
							<td><a href="javascript:seeMenu()"><div
										style="padding-top: 5px;"></div>&nbsp;<span class="icon-edit">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;设置默认</span>&nbsp;&nbsp;</a>
							</td>
							<td><a href="javascript:seeErrorUrl()"><div
										style="padding-top: 5px;"></div>&nbsp;<span class="icon-edit">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;设置跳转链接</span>&nbsp;&nbsp;</a>
							</td>
							<td><a href="javascript:lookCard()"><div
										style="padding-top: 5px;"></div>&nbsp;<span class="icon-edit">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;查看链接</span>&nbsp;&nbsp;</a>
							</td>
						</tr>
					</table></td>
			</tr>
		</table>
	</div>
	<div id="cardType" class="easyui-window" title="查看类型"
		style="padding:0;width:600px;height:350px;" iconCls="icon-save"
		closed="true" maximizable="false"
		data-options="modal:true,closed:true" minimizable="false"
		collapsible="false">
		<table style="float:left;" id="card_tt">
		</table>
		
	</div>
	
	<div id="addCardType" class="easyui-window" title="添加"
		style="top:150px; ;overflow:hidden;background:#E2EDFF;width:300px;height:150px;[;height:150px;];*height:140px;height:140px\9\0;"
		iconCls="icon-add" data-options="modal:true,closed:true"
		maximizable="false" closed="true" maximizable="false"
		minimizable="false" collapsible="false">
		<div id="eeXL"></div>
		<div>&nbsp;</div>
		<div align="center">
			<a class="easyui-linkbutton" iconCls="icon-ok"
				href="javascript:void(0)"
				style="position:relative;top:-15px; *top:-22px;" onClick="add()">添加</a>
			<a class="easyui-linkbutton" iconCls="icon-cancel"
				href="javascript:void(0)"
				style="position:relative;top:-15px;*top:-22px;"
				onClick="closeWindow('addCardType')">取消</a>
		</div>
	</div>
	<div id="updateCardType" class="easyui-window" title="修改"
		style="top:150px; ;overflow:hidden;background:#E2EDFF;width:300px;height:150px;[;height:150px;];*height:140px;height:140px\9\0;"
		iconCls="icon-add" data-options="modal:true,closed:true"
		maximizable="false" closed="true" maximizable="false"
		minimizable="false" collapsible="false">
		<div id="updateeeXL"></div>
		<div>&nbsp;</div>
		<div align="center">
			<a class="easyui-linkbutton" iconCls="icon-ok"
				href="javascript:void(0)"
				style="position:relative;top:-15px; *top:-22px;" onClick="update()">修改</a>
			<a class="easyui-linkbutton" iconCls="icon-cancel"
				href="javascript:void(0)"
				style="position:relative;top:-15px;*top:-22px;"
				onClick="closeWindow('updateCardType')">取消</a>
		</div>
	</div>
	<form id="cardForm" target="hidden_frame" enctype="multipart/form-data"
		method="post">
		<div class="xx21">
			<table style="margin-top:10px;margin-bottom:10px; margin-left:10px;">
				<tr>
					<td>选择模板</td>
					<td><input type="text" id="cardTmplateName" name="cardTmplateName" onclick="cardTypeEdit()"/><font
						style="color: red">*请双击选择</font><input type="hidden" id="cardTemplateId" name="cardTemplateId"/></td>
				</tr>
				<tr>
					<td valign="top">
						名&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;称&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</td>
					<td><input type="text" id="cardName" name="cardName" /></td>
				</tr>
				<tr>
					<td valign="top">
						内&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;容&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</td>
					<td id="idmetaValue"><textarea id="cardValue" name="cardValue"
							rows="5" cols="50" class="ww"></textarea></td>
				</tr>

				<tr>
					<td>背景图片&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
					<td><input type="file" id="cardBgImage" name="cardBgImage" /><font
						style="color: red">*建议640*1016</font>
					</td>
				</tr>
				<tr>
					<td>文字背景&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
					<td><input type="file" id="cardTxtImage" name="cardTxtImage" /><font
						style="color: red">*建议289*184</font>
					</td>
				</tr>
				<tr>
					<td>分享图片&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
					<td><input type="file" id="cardTitleImage" name="cardTitleImage" /><font
						style="color: red">*建议100*100,png格式</font>
					</td>
				</tr>
				<tr>
					<td><input type="hidden" name="cardId" id="cardId" />
					</td>
				</tr>
			</table>
		</div>
	</form>
	<div id="addCard" class="easyui-window" title="添加"
		style="top:150px; ;overflow:hidden;background:#E2EDFF;width:600px;height:320px;[;height:320px;];*height:320px;height:320px\9\0;"
		iconCls="icon-add" data-options="modal:true,closed:true"
		maximizable="false" closed="true" maximizable="false"
		minimizable="false" collapsible="false">
		<div id="addCardEeXL"></div>
		<div>&nbsp;</div>
		<div align="center">
			<a class="easyui-linkbutton" iconCls="icon-ok"
				href="javascript:void(0)"
				style="position:relative;top:-15px; *top:-22px;" onClick="AddCard()">添加</a>
			<a class="easyui-linkbutton" iconCls="icon-cancel"
				href="javascript:void(0)"
				style="position:relative;top:-15px;*top:-22px;"
				onClick="closeWindow('addCard')">取消</a>
		</div>
	</div>
	<div id="updateCard" class="easyui-window" title="修改"
		style="top:150px; ;overflow:hidden;background:#E2EDFF;width:600px;height:320px;[;height:320px;];*height:320px;height:320px\9\0;"
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
	<div id="cardUrl" class="easyui-window" title="微贺卡访问路径" 
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
	<div id="errorUrl" class="easyui-window" title="链接设置" 
		style="top:100px; ;overflow:hidden;background:#E2EDFF;width:500px;height:110px;[;height:110px;];*height:110px;height:110px\9\0;"
		iconCls="icon-add" data-options="modal:true,closed:true"
		maximizable="false" closed="true" maximizable="false"
		minimizable="false" collapsible="false">
		<div id="errorEEXL"></div>
		<div>&nbsp;</div>
		<div align="center">
			<a class="easyui-linkbutton" iconCls="icon-ok"
				href="javascript:void(0)"
				style="position:relative;top:-15px; *top:-22px;"
				onClick="errorUrl()">添加/修改</a> <a class="easyui-linkbutton"
				iconCls="icon-cancel" href="javascript:void(0)"
				style="position:relative;top:-15px;*top:-22px;"
				onClick="closeWindow('errorUrl')">取消</a>
		</div>
	</div>
	<form id="errorUrlForm" target="hidden_frame"
		enctype="multipart/form-data" method="post">
		<div class="xx21" >
			<div>
				<table>
					<tr>
						<td>
							默认跳转链接：<input id = "errorUrlStr" name = "errorUrlStr" type="text" value="http://" size="60"/>
						</td>
					</tr>
				</table>
			</div>
		</div>
	</form>
</body>
</html>
