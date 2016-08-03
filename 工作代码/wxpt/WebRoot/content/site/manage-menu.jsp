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
<script type="text/javascript" src="../manager/js/menu.js"></script>
<style type="text/css">
#serchTest,#edit,#add,#ff,#lan,#addExplicit,#updateQuestion,#laPage,#update2,#add2,#update,,#add1,#ff1,#addexplicit,#updateexplicit,#toolbar  {
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

	<div id="toolbar" class="test" >

		<table width="100%">
			<tr>
				
				<td><table align="right">
						<tr>
							<td><a href="javascript:Addmenu()"><div	style="padding-top: 5px;"></div>&nbsp;<span class="icon-add">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;添加主菜单</span>&nbsp;&nbsp;</a>
							</td>
							<td><a href="javascript:Editmenu()"><div
										style="padding-top: 5px;"></div>&nbsp;<span class="icon-edit">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;修改主菜单</span>&nbsp;&nbsp;</a>
							</td>
							<td><a href="javascript:Seemenu()"><div
										style="padding-top: 5px;"></div>&nbsp;<span class="icon-edit">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;查看子菜单</span>&nbsp;&nbsp;</a>
							</td>
							<td><a href="javascript:Deletemenu()"><div
										style="padding-top: 5px;"></div>&nbsp;<span
									class="icon-remove">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;删除</span>&nbsp;&nbsp;</a>
							</td>
							<td><a href="javascript:createMenu()"><div
										style="padding-top: 5px;"></div>&nbsp;<span
									class="icon-remove">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;生成菜单</span>&nbsp;&nbsp;</a>
							</td>
						</tr>
					</table></td>
			</tr>
		</table>
	</div>
	
	<form id="ff1" target="hidden_frame" enctype="multipart/form-data"
		method="post" style=" visibility: hidden;">
		<div class="xx21">
		<div style="margin-top: 5px; margin-bottom: 5px; margin-left: 10px;*margin-top:10px; *margin-bottom: 10px; *margin-left: 15px;">
			<table>
				<tr style="line-height: 20px;">
					<td><span>菜&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;单     ：</span></td>
					<td><span id="menuNum"></span>				
					</td>
				</tr>
				
				<tr style="line-height: 20px;" >
				<td>菜&nbsp;&nbsp;&nbsp;单&nbsp;&nbsp;&nbsp;名 ：</td>
					<td  >
						<input type="text"  class="ww"  value="" id="menuName" name="menuName" style="width: 120px;" >
					</td>
				</tr>
				<tr style="line-height: 20px; display: none;" id="type">
				<td>子菜单类型 ：</td>
				<td>
					<select onchange="change()" id="menuType" name="menuType" class="ww" style="width: 122px;" >
					<option >--选择类型--</option>
						<option value="view">view</option>
						<option value="click">click</option>
					</select>
				</td>
				</tr>
				<tr style="line-height: 20px; display: none;" id="url">
				<td>u&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;r&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;l  ：</td>
					<td  >
						<input type="text"  class="ww"  value="" id="menuUrl" name="menuUrl" style="width: 120px;" >
					</td>
				</tr>
				<tr style="line-height: 20px; display: none;" id="key" >
				<td>K&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;e&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;y  ：</td>
					<td  >
						<input type="text"  class="ww"  value="" id="menuKey" name="menuKey" style="width: 120px;" >
					</td>
				</tr>
			</table>
			</div>
		</div>
		<input type="hidden" id="pId" name="pId">
	</form>
	<div id="add1" class="easyui-window" title="添加主菜单"
		style=" top:20px;left:380px;overflow:hidden;background:#E2EDFF;width:270px;height:auto;[;height:auto;];*height:auto; visibility: hidden;"
		iconCls="icon-add" data-options="modal:true,closed:true"
		maximizable="false" closed="true" maximizable="false"
		minimizable="false" collapsible="false">
		<div id="aaa"></div>
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
		<div id="update" class="easyui-window" title="修改主菜单"
		style=" top:20px;left:380px;overflow:hidden;background:#E2EDFF;width:270px;height:auto;[;height:auto;];*height:auto;visibility: hidden;"
		iconCls="icon-add" data-options="modal:true,closed:true"
		maximizable="false" closed="true" maximizable="false"
		minimizable="false" collapsible="false">
		<div id="update1"></div>
		<div>&nbsp;</div>
		<div align="center">
			<a class="easyui-linkbutton" iconCls="icon-ok"
				href="javascript:void(0)"
				style="position:relative;top:-15px;*top:-23px;"
				onClick="updateMenu()">修改</a> <a class="easyui-linkbutton"
				iconCls="icon-cancel" href="javascript:void(0)"
				style="position:relative;top:-15px;*top:-23px;" onClick="close1()">取消</a>
		</div>
	</div>
	<div id="add2" class="easyui-window" title="添加子菜单"
		style=" top:20px;left:380px;overflow:hidden;background:#E2EDFF;width:270px;height:auto;[;height:auto;];*height:auto; visibility: hidden;"
		iconCls="icon-add" data-options="modal:true,closed:true"
		maximizable="false" closed="true" maximizable="false"
		minimizable="false" collapsible="false">
		<div id="aaa2"></div>
		<div>&nbsp;</div>
		<div align="center">
			<a class="easyui-linkbutton" iconCls="icon-ok"
				href="javascript:void(0)"
				style="position:relative;top:-15px;*top:-23px;"
				onClick="add2()">添加</a> <a class="easyui-linkbutton"
				iconCls="icon-cancel" href="javascript:void(0)"
				style="position:relative;top:-15px;*top:-23px;" onClick="close1()">取消</a>
		</div>
	</div>
		<div id="update2" class="easyui-window" title="修改子菜单"
		style=" top:20px;left:380px;overflow:hidden;background:#E2EDFF;width:270px;height:auto;[;height:auto;];*height:auto; visibility: hidden;"
		iconCls="icon-add" data-options="modal:true,closed:true"
		maximizable="false" closed="true" maximizable="false"
		minimizable="false" collapsible="false">
		<div id="update12"></div>
		<div>&nbsp;</div>
		<div align="center">
			<a class="easyui-linkbutton" iconCls="icon-ok"
				href="javascript:void(0)"
				style="position:relative;top:-15px;*top:-23px;"
				onClick="updateChilMenu()">修改</a> <a class="easyui-linkbutton"
				iconCls="icon-cancel" href="javascript:void(0)"
				style="position:relative;top:-15px;*top:-23px;" onClick="close1()">取消</a>
		</div>
	</div>
	
	<div id="laPage" class="easyui-window" title="查看子菜单"
		style="padding:0;width:800px;height:300px; visibility: hidden;" iconCls="icon-save"
		closed="true" maximizable="false"
		data-options="modal:true,closed:true" minimizable="false"
		collapsible="false">
		<table style="float:left;" id="pageTt">
		</table>
		<div id="pageToolbar" class="test">

			<table width="100%">
				<tr>

					<td>
						<table align="right">
							
							<tr>
								<td id="xzDomain"><a href="javascript:AddChilMenu()"><div 
										style="padding-top: 5px;"></div>&nbsp;<span
										class="icon-remove">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;添加子菜单</span>
								</a></td>
								<td id="xzDomain"><a href="javascript:EditChilMenu()"><div 
										style="padding-top: 5px;"></div>&nbsp;<span
										class="icon-remove">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;修改</span>
								</a></td>
								<td id="xzDomain"><a href="javascript:DeleChilMenu()"><div 
										style="padding-top: 5px;"></div>&nbsp;<span
										class="icon-remove">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;删除&nbsp;&nbsp;</span>
								</a></td>
								<input type="text" style="display: none;" id="parentId">
							</tr>
						</table></td>
				</tr>
			</table>
		</div>
	</div>
</body>
</html>