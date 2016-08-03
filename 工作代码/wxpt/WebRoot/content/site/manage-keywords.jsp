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
<script type="text/javascript" src="../manager/js/keywords.js"></script>


<style type="text/css">
#serchTest,#edit,#add,#ff,#lan,#addExplicit,#updateQuestion,#add1,#ff1,#addexplicit,#updateexplicit,#toolbar  {
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
	*width: 462px;
}
.ww{border:1px solid #26A0DA}
</style>

</head>
<body style="background-color:white;padding:0px;">

	<table style="float:left;" id="tt">
	</table>

	<div id="toolbar" class="test">

		<table width="100%" >
			<tr>
				<td><table align="left">
						<tr>
							<td><a href="javascript:queryKeywords()"><div
										style="padding-top: 5px;"></div>&nbsp;<span class="icon-add">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;查看关键字</span></a>
							</td>
						</tr>
					</table></td>
				<td><table align="right">
						<tr>
							<td><a href="javascript:AddWordexplicit()"><div
										style="padding-top: 5px;"></div>&nbsp;<span class="icon-add">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;添加</span>&nbsp;&nbsp;</a>
							</td>
							<td><a href="javascript:UpdateWordexplicit()"><div
										style="padding-top: 5px;"></div>&nbsp;<span class="icon-edit">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;修改</span>&nbsp;&nbsp;</a>
							</td>

							<td><a href="javascript:DeleteQuestion()"><div
										style="padding-top: 5px;"></div>&nbsp;<span
									class="icon-remove">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;删除</span>&nbsp;&nbsp;</a>
							</td>
						</tr>
					</table></td>
			</tr>
		</table>
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

					<td>
						<table align="right">
							<tr>
							    <td><a href="javascript:openKeywords()">
										<div style="padding-top: 5px;"></div>&nbsp;<span
										class="icon-add">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;添加&nbsp;&nbsp;</span>
								</a>
								</td>
								<td id="xzDomain"><a href="javascript:getUpdate();"><div
											style="padding-top: 5px;"></div>&nbsp;<span
										class="icon-edit">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;修改</span>&nbsp;&nbsp;</a>
								</td>
		                          <td><a href="javascript:del()">
										<div style="padding-top: 5px;"></div>&nbsp;<span
										class="icon-remove">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;删除&nbsp;&nbsp;</span>
								</a>
								</td>
							</tr>
						</table></td>
				</tr>
			</table>
		</div>
	</div>
	<form id="ff1" target="hidden_frame" enctype="multipart/form-data"
		method="post">
		<div class="xx21">
			<table
				style="margin-top: 5px; margin-bottom: 5px; margin-left: 10px;">
				<tr style="line-height: 20px;">
					<td><span>规则:</span></td>
					<td><input type="text" name="rule" id="rule" />
					</td>
				</tr>
				<tr style="line-height: 20px;">
					<td><span>关键字：</span>
					</td>
					<td><input type="text" name="keywordcontent"
						id="keywordcontent" /></td>
				</tr>
			</table>
		</div>
		<input type="hidden" id="keywordId" name="keywordId">
	</form>
	<div id="add1" class="easyui-window" title="添加关键字"
		style="overflow:hidden;background:#E2EDFF;width:250px;height:150px;[;height:154px;];*height:153px;"
		iconCls="icon-add" data-options="modal:true,closed:true"
		maximizable="false" closed="true" maximizable="false"
		minimizable="false" collapsible="false">
		<div id="aaa"></div>
		<div>&nbsp;</div>
		<div align="center">
			<a class="easyui-linkbutton" iconCls="icon-ok"
				href="javascript:void(0)"
				style="position:relative;top:-15px;*top:-23px;"
				onClick="addKeywords()">添加</a> <a class="easyui-linkbutton"
				iconCls="icon-cancel" href="javascript:void(0)"
				style="position:relative;top:-15px;*top:-23px;" onClick="close1()">取消</a>
		</div>
	</div>
	<div id="update1" class="easyui-window" title="修改关键字"
		style="overflow:hidden;background:#E2EDFF;width:250px;height:150px;[;height:154px;];*height:153px;"
		iconCls="icon-add" data-options="modal:true,closed:true"
		maximizable="false" closed="true" maximizable="false"
		minimizable="false" collapsible="false">
		<div id="eee"></div>
		<div>&nbsp;</div>
		<div align="center">
			<a class="easyui-linkbutton" iconCls="icon-ok"
				href="javascript:void(0)"
				style="position:relative;top:-15px;*top:-23px;"
				onClick="updateKeywords()">修改</a> <a class="easyui-linkbutton"
				iconCls="icon-cancel" href="javascript:void(0)"
				style="position:relative;top:-15px;*top:-23px;" onClick="close1()">取消</a>
		</div>
	</div>

	<div id="addexplicit" class="easyui-window" title="添加" 
		style="top:50px; ;overflow:hidden;background:#E2EDFF;width:500px;height:460px;[;height:438px;];*height:412px;height:421px\9\0;"
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
				style="position:relative;top:-15px;*top:-22px;" onClick="close2()">取消</a>
		</div>
	</div>
	<div id="updateexplicit" class="easyui-window" title="修改"
		style="top:50px; ;overflow:hidden;background:#E2EDFF;width:500px;height:460px;[;height:438px;];*height:412px;height:421px\9\0;"
		iconCls="icon-add" data-options="modal:true,closed:true"
		maximizable="false" closed="true" maximizable="false"
		minimizable="false" collapsible="false">
		<div id="ee"></div>
		<div>&nbsp;</div>
		<div align="center">
			<a class="easyui-linkbutton" iconCls="icon-ok"
				href="javascript:void(0)"
				style="position:relative;top:-15px; *top:-22px;" onClick="update()">修改</a>
			<a class="easyui-linkbutton" iconCls="icon-cancel"
				href="javascript:void(0)"
				style="position:relative;top:-15px;*top:-22px;" onClick="close2()">取消</a>
		</div>
	</div>
	<form id="addExplicit" target="hidden_frame"
		enctype="multipart/form-data" method="post">
		<div class="xx21" >
	  <div>
			<table 
				style="margin-top: 5px; margin-bottom: 5px; margin-left: 50px; [;margin-left: 50px; ];  margin-left: 50px\9\0;width: 100%" border="0" bordercolor="#E2EDFF" cellpadding="0" cellspacing="0">
				<tr style="line-height: 30px; " >
				
					<td colspan="2"><span >关键字:</span> <input class="ww" type="text" name="keyName"
						id="keyName" readonly="readonly" onclick="queryKeywords()" /><input
						type="hidden" id="keywordID" name="keywordID"><font color="red">*点击文本框选择关键字</font>
					</td>
				</tr>
				<tr style="line-height: 30px;">
					<td>文本消息</td>
					<td><span style="position: relative;top: -20px;">消息内容：</span>
					<textarea style="*width: 135px;[;width: 127px;];width:139px\9\0;"  class="ww" id="word1" name="word1"></textarea></td>
				</tr>
				<tr style="line-height: 30px;" >
					<td>音频消息</td>
					<td><span>添加文件：</span> 
					<input type="file" style="width: 139px;[;width: 130px;];*width:132px;width:142px\9\0; " class="ww" id="vodio" name="vodio" onchange="getFileName()"/>
					<input type="hidden" name="vidoName"  class="ww" id="vidoName"><br>
						标&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;题:
						<input type="text"  class="ww" id="vodioTitle" size="22" name="vodioTitle" style="position: relative;left: 10px;[;left: 7px;];*left: 9px;"/><br>
						描&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;述:
						<input type="text"  class="ww" id="vodioValue" size="22" name="vodioValue" style="position: relative;left: 10px;[;left: 7px;];*left: 9px;"/>
					</td>
				</tr>
				<tr style="line-height: 30px;" >
					<td >图名消息：</td>
					<td >图文类型：
					
						<select   name="type" style="width: 140px;[;width: 130px;];*width: 135px;[;margin-left: 2px;];*margin-left: 0px;margin-left: 2px\9\0;">
							<option selected="selected" value="0">单图文消息</option>
							<option  value="1">多图文消息</option>
						</select>
						<br>
						图&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;片：
						<input type="file"  class="ww"  style="width: 134px;[;width: 128px;];*width:134px;width:140px\9\0; *margin-left: -3px;margin-left: 1px\9\0;" id="images1" name="images1" /><br> 
						标&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;题:
						<input type="text" id="title" size="22"  class="ww" name="title" style="position: relative;left: 12px;[;left:10px;];*left:11px;"/><br>
						<span style="position: relative;top: -20px;">描&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;述:</span>
						<textarea id="imageValue" class="ww" 
						name="imageValue" style="position: relative;left: 12px;width: 137px;[;width: 127px;];*width:129px;"></textarea><br>
						文本链接:<input type="text"  class="ww" id="url" size="22" name="url" style="position: relative;left: 16px;[;left: 14px;];left:15px;"/>
					</td>
				</tr>
			</table>
		</div>
		</div>
		<input type="hidden" id="keywordId" name="keywordId">
		<input type="hidden" id="explicitId" name="explicitId">
	</form>
</body>
</html>