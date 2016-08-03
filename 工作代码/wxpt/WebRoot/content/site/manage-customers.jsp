<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
<script type="text/javascript" src="../manager/js/customers.js"></script>
<script type="text/javascript" language="javascript"
	src="../manager/js/WebCalendar.js"></script>

<style type="text/css">
#serchTest,#updatecustomers,#addcustomers,#ff,#addCustomers,#customer,#edit,#toolbar{
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

<body class="easyui-layout" data-options="border:false"
	style="background-color:white;padding:0px;overflow-y:hidden;">
	<div id="toolbar" class="test">

		<table width="100%">
			<tr>
				<td>
					<table align="left">
						<tr>
							<td><span>按</span> <select class="easyui-combobox"
								name="industry" id="industry" style="width:100px;">
									<option value="-1">全部行业</option>
							</select> <select class="easyui-combobox" name="area" id="area"
								style="width:100px;">
									<option value="-1">全部区域</option>
							</select> <select class="easyui-combobox" name="canton" id="canton"
								style="width:100px;">
									<option value="-1">全部行政区域</option>
							</select>
							</td>
							<td><a href="javascript:judgeQuery()">
									<div style="padding-top: 5px;"></div>&nbsp;<span
									class="icon-search">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;查询</span>&nbsp;&nbsp;
							</a></td>
						</tr>
					</table></td>
				<td>
					<table align="right">
						<tr>
							<td><a href="javascript:AddCustomers()"><div
										style="padding-top: 5px;"></div>&nbsp;<span class="icon-add">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;添加</span>&nbsp;&nbsp;</a>
								<div class="datagrid-btn-separator"></div></td>
							<td><a href="javascript:UpdateCustomers()"><div
										style="padding-top: 5px;"></div>&nbsp;<span class="icon-add">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;修改</span>&nbsp;&nbsp;</a>
								<div class="datagrid-btn-separator"></div></td>
							<td><a href="javascript:DeleteCustomers()"><div
										style="padding-top: 5px;"></div>&nbsp;<span class="icon-edit">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;删除</span>&nbsp;&nbsp;</a>
								<div class="datagrid-btn-separator"></div></td>

						</tr>
					</table></td>
			</tr>
		</table>
	</div>


	<div data-options="region:'center',split:true">
		<table style="float:left;" id="tt">
		</table>
		<div id="addcustomers" class="easyui-window" title="添加客户"
			style="overflow:hidden;background:#E2EDFF;width:630px;*width:668px;height:auto;*height:390px;top:100px;"
			iconCls="icon-edit" closed="true" maximizable="false"
			minimizable="false" data-options="modal:true,closed:true"
			minimizable="false" collapsible="false">
			<div id="eeXL"></div>
			<div>&nbsp;</div>
			<div align="center">
				<a class="easyui-linkbutton"
					style="position:relative;top:-13px;top:-23px\9;top:-13px\9\0;" iconCls="icon-ok"
					href="javascript:void(0)" onClick="add()">添加</a> <a
					class="easyui-linkbutton"
					style="position:relative;top:-13px;top:-23px\9;top:-13px\9\0;"
					iconCls="icon-cancel" href="javascript:void(0)" onClick="close1()">取消</a>
			</div>
		</div>

		<div id="updatecustomers" class="easyui-window" title="客户修改"
			style="overflow:hidden;background:#E2EDFF;width:630px;*width:668px;height:auto;*height:390px;top:100px;"
			iconCls="icon-save" closed="true" maximizable="false"
			data-options="modal:true,closed:true" minimizable="false"
			collapsible="false">
			<div id="update"></div>
			<div>&nbsp;</div>
			<div align="center">
				<a class="easyui-linkbutton"
					style="position:relative;top:-13px;top:-23px\9;top:-13px\9\0;" iconCls="icon-ok"
					href="javascript:void(0)" onClick="update()">修改</a> <a
					class="easyui-linkbutton"
					style="position:relative;top:-13px;top:-23px\9;top:-13px\9\0;"
					iconCls="icon-cancel" href="javascript:void(0)" onClick="close1()">取消</a>
			</div>
		</div>


		<!-- 编辑 -->




		<form id="addCustomers" method="post">
			<div class="xx21" style="*width:630px;">
				<table style="margin-left:10px; margin-top:5px; margin-bottom:5px;"
					align="center">
					<tr style="line-height: 20px;">
						<td colspan="2">
							<div style="*margin-top:10px; *margin-left:10px;">
								<span >客户编号：</span>
								<input type="text" id="customersNo" name="customersNo"  size="20" class="ww" /><font color="red">*必填</font>
							</div>
						</td>
					</tr>
					<tr style="line-height: 20px; ">
						<td><div  style=" *margin-left:10px;">
								客户名称： <input type="text" name="customersName"
									id="customersName" size="20" class="ww"><font color="red">*必填</font>
							</div>
						</td>
						<td>
							<div style="width:350px;">
								所属行业： <select class="easyui-combobox" name="industryId"
									id="industryId" class="ww" style="width:126px;">
									<option value="-1">全部行业</option>
								</select><font color="red">*必须</font>
							</div>
						</td>
					</tr>
					<tr style="line-height: 20px;">
						<td><div style="width:350px;*margin-left:10px;">
								所属区域： <select class="easyui-combobox" name="areaId" id="areaId" class="ww"
									style="width:124px;">
									<option value="-1" >全部区域</option>
								</select><font color="red">*必须</font>

							</div>
						</td>
						<td>
							<div>
								行政区域： <select class="easyui-combobox" name="cantonId" class="ww"
									id="cantonId" style="width:126px;">
									<option value="-1" >全部行政区域</option>
								</select>
							</div>
						</td>
					</tr>
					<tr style="line-height: 20px;">
						<td><div style="width:350px;*margin-left:10px;">
								规格型号： <input id="customersModel" name="customersModel" class="ww"
									size="20" type="text">
							</div>
						</td>
						<td>
							<div style="position: relative; right: -2px;">
								数&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;量： <input
									id="customersCount" name="customersCount" size="20" class="ww"
									type="text"><font color="red">*必填</font>
							</div>
						</td>
					</tr>
					<tr style="line-height: 20px;">
						<td><div style="width:350px;position: relative; right: -2px;*margin-left:8px;">
								纬&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;度：  <input id="locationx" class="ww"
									name="locationx" size="20" type="text">
							</div>
						</td>
						<td>
							<div style="position: relative; right: -2px;">
								经&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;度： <input id="locationy" class="ww"
									name="locationy" size="20" type="text">
							</div>
						</td>
					</tr>
					<tr style="line-height: 20px;">
						<td colspan="2">
							<div style=" *margin-left:10px;">
								客户地址： <input id="customersAddress" name="customersAddress" class="ww"
									style="width: 478px;" type="text"><input type="hidden" name="customersId" id="customersId">
							</div>
						</td>
					</tr>
					<tr style="line-height: 20px;">
						<td colspan="2">
							<div style=" *margin-left:10px;">
								<span style="position: relative; top: -80px;">客户介绍：</span>
								<textarea id="customersIntroduce" name="customersIntroduce" cols="92" rows="6" class="ww" style="width: 478px;"></textarea>
							</div>
						</td>
					</tr>
					<tr style="line-height: 20px;">
						<td colspan="2">
							<div style=" *margin-left:10px; margin-bottom: 10px;">
								<span>客户图片：</span>
								<input id="customersImage" name="customersImage" class="ww" type="file"/>
								<span style="color: red;">上传图片宽600像素,高200像素</span>
							</div>
						</td>
					</tr>
				</table>
			</div>
		</form>

		<div id="edit" class="easyui-window" title="查看详细信息"
			style="overflow:hidden;background:#E2EDFF;width:750px;*width:850px;height:auto;top:100px;"
			iconCls="icon-edit" closed="true" maximizable="false"
			minimizable="false" data-options="modal:true,closed:true"
			minimizable="false" collapsible="false">
			<div id="ee"></div>
			<div>&nbsp;</div>
		</div>

		<form id="customer" method="post">
			<div class="xx21">
				<table style="margin-left:10px; margin-top:5px; margin-bottom:5px;"
					align="center">
					<tr style="line-height: 20px;">
						<td colspan="2">
							<div>
								客户编号：<span id="customer_no" name="customer_no"
									size="20" />
							</div>
						</td>
					</tr>
					<tr style="line-height: 20px;">
						<td><div>
								客户名称： <span id="customer_name" name="customer_name" size="20"></span>
							</div>
						</td>
						<td>
							<div style="width:350px;">
								所属行业： <span id="customer_industry" name="pageviews" size="20"></span>
							</div>
						</td>
					</tr>
					<tr style="line-height: 20px;">
						<td><div style="width:350px;">
								所属区域： <span id="customer_area" name="customer_area" size="20"></span>
							</div>
						</td>
						<td>
							<div>
								行政区域： <span id="customer_canton" name="customer_canton"
									size="20"></span>
							</div>
						</td>
					</tr>
					<tr style="line-height: 20px;">
						<td><div style="width:350px;">
								规格型号： <span id="customer_model" name="customer_model" size="20"></span>
							</div>
						</td>
						<td>
							<div>
								数&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;量： <span
									id="customer_count" name="customer_count" size="20"></span>
							</div>
						</td>
					</tr>
					<tr style="line-height: 20px;">
						<td><div>
								纬&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;度： <span id="location_x"
									name="location_x" size="20"></span>
							</div>
						</td>
						<td>
							<div style="width:350px;">
								经&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;度： <span id="location_y"
									name="location_y" size="20"></span>
							</div>
						</td>
					</tr>
					<tr style="line-height: 20px;">
						<td colspan="2">
							<div>
								客户地址：<span id="customer_address" name="customer_address"
									size="20" />
							</div>
						</td>
					</tr>
					<tr style="line-height: 20px;">
						<td colspan="2">
							<div >
								客户介绍：<span id="customer_introduce" name="customer_introduce"
									size="10" />
							</div>
						</td>
					</tr>
					<tr style="line-height: 20px;">
						<td colspan="2">
							<div>
								<span style="position: relative; top: -185px;">客户图片：</span><span id="customer_image" name="customer_image"
									size="20"   /></span>
							</div>
						</td>
					</tr>
				</table>
			</div>
		</form>

	</div>




</body>
</html>
