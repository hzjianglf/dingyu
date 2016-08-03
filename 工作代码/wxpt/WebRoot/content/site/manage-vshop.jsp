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
 <base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 <meta http-equiv="X-UA-Compatible" content="IE=8,9,10" >
<link rel="stylesheet" type="text/css" href="<%=basePath%>manager/css/default.css">
<link rel="stylesheet" type="text/css" href="<%=basePath%>manager/themes/icon.css">
<link rel="stylesheet" type="text/css" href="<%=basePath%>manager/css/demo.css">
<link id="easyuiTheme" rel="stylesheet"
	href="<%=basePath%>manager/themes/default/easyui.css" type="text/css">
<script type="text/javascript" src="<%=basePath%>manager/js/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="<%=basePath%>manager/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=basePath%>manager/js/jquery.cookie.js"></script>
<script type="text/javascript" src="<%=basePath%>manager/js/hd.js"></script>
<script type="text/javascript" src="<%=basePath%>manager/js/jquery.form.js"></script>
<script type="text/javascript" src="<%=basePath%>manager/js/vshop.js"></script>
<script type="text/javascript" src="<%=basePath%>manager/js/ajaxfileupload.js"></script>

<script type="text/javascript" language="javascript"
	src="<%=basePath%>manager/js/WebCalendar.js"></script>

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
				<td>
				</td>
				<td><table align="right">
						<tr>
						<td><a href="javascript:upload()"><div
										style="padding-top: 5px;"></div>&nbsp;<span class="icon-add">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;上传banner</span>&nbsp;&nbsp;</a>
							</td>
						  <!-- <td><a href="javascript:AddLo()"><div
										style="padding-top: 5px;"></div>&nbsp;<span class="icon-add">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>添加&nbsp;&nbsp;</a>
							</td>  
							
							 <td><a href="javascript:UpdateUser()"><div
										style="padding-top: 5px;"></div>&nbsp;<span class="icon-edit">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;修改</span>&nbsp;&nbsp;</a>
							</td>  -->
							 <!-- <td><a href="javascript:Delete()"><div
										style="padding-top: 5px;"></div>&nbsp;<span class="icon-remove">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>删除&nbsp;&nbsp;</a>
							</td>  -->

							
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
			<table>
				<tr style="line-height: 20px;">
					<td><span>企业名称:</span></td>
					<td><input type="text" name="enterName"  class="ww" id="enterName"   />
				</tr>
				 <tr style="line-height: 20px;">
					<td><span>法人:</span></td>
					<td><input type="text" name="name"  class="ww" id="name"   />
					</td>
				</tr>
				<tr style="line-height: 20px;">
					<td><span>联系方式:</span></td>
					<td><input type="text" name="content"  class="ww" id="content"   />
					</td>
				</tr>
				<tr style="line-height: 20px;">
					<td><span>地址:</span></td>
					<td><input type="text" name="address"  class="ww" id="address"   />
					</td>
				</tr>
				
						<input type="hidden" name="payId"  id="payId">
				
				
			</table>
			<span style="color: red;margin-left: 70px; margin-bottom: 10px;" ></span>
			</div>
			
		</div>
		<input type="hidden" id="userId" name="userId">
	</form>
	<div id="updateuser" class="easyui-window" title="修改"
		style=" top:20px;left:380px;overflow:hidden;background:#E2EDFF;width:275px;height:220px;[;height:220px;];*height:220px;height:215px\9\0;"
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
	<div id="adduser" class="easyui-window" title="添加"
		style=" top:20px;left:380px;overflow:hidden;background:#E2EDFF;width:275px;height:220px;[;height:220px;];*height:220px;height:215px\9\0;"
		iconCls="icon-edit" closed="true" maximizable="false"
		minimizable="false" collapsible="false">
		<div id="add"></div>
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
<div id="ad" scroll="no" class="easyui-window" title="banner上传" style="background:#E2EDFF;hidden;width:300px;height:130px;left:380px;top:20px;[;height:130px;];*height:100px;height:140px\9\0;"
        iconCls="icon-edit" data-options="modal:true,closed:true"
		closed="true" maximizable="false" minimizable="false"
		collapsible="false">
		
		<div class="xx21">
			<div  class="test" style="margin-top: 5px; margin-bottom: 5px; margin-left: 10px;*margin-top:10px; *margin-bottom: 10px; *margin-left: 15px;">
			<table>
				<tr style="line-height: 20px;">
					<td><input type="file" name="banner" id="banner" class="ww"/>
					</td>
				</tr>
				<tr style="line-height: 20px;">
					<td>
		           <a href="javascript:uploads()">
	            <div style="padding-top: 5px;"></div>&nbsp;<span class="icon-save">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;上传</span>&nbsp;&nbsp;</a>
			
					</td>
				</tr>
				
		</table>
			

			</div>
		</div>
		</div>

</body>
</html>