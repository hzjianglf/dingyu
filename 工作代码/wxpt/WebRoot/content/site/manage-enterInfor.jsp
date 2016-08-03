<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="s" uri="/struts-tags" %>
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
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="../manager/css/default.css">
<link rel="stylesheet" type="text/css" href="../manager/themes/icon.css">
<link rel="stylesheet" type="text/css" href="../manager/css/demo.css">
<link id="easyuiTheme" rel="stylesheet"
	href="../manager/themes/default/easyui.css" type="text/css">
<script type="text/javascript" src="../manager/js/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="../manager/js/jquery.easyui.min.js"></script>
<!--s <cript type="text/javascript" src="../manager/js/jquery.cookie.js"></script> -->
<script type="text/javascript" src="../manager/js/hd.js"></script>
<script type="text/javascript" src="../manager/js/jquery.form.js"></script>
<script type="text/javascript" language="javascript"
	src="../manager/js/WebCalendar.js"></script>

<script type="text/javascript" src="../manager/js/enterInfor.js"></script>

<style type="text/css">
#serchTest,#edit,#add,#ff,#addUser,#adduser,#updateuser,#toolbar {
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
<style type="text/css">
#serchTest,#edit,#add,#ff,#addCheckForm,#updatequestion,#addcheckdiv,#toolbar,#huiyuanxingqing
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

.kehus { /* background:#FFFFFF; */
	background: #E2EDFF;
	border: none;
}

.kehustable {
	width: 700px;
	border-style: dotted;
	border-color: #CCCCCC;
	border-width: 1px;
	margin: 10px auto;
}

* html .kehustable {
	width: 700px;
	border-style: dotted;
	border-color: #CCCCCC;
	border-width: 1px;
	margin: 10px auto;
}

*+html .kehustable {
	width: 700px;
	border-style: dotted;
	border-color: #CCCCCC;
	border-width: 1px;
	margin: 10px auto;
}

.kehustable td {
	border-style: dotted;
	border-color: #CCCCCC;
	border-width: 1px;
}

.liuyanban {
	width: 700px;
	border-style: dotted;
	border-color: #CCCCCC;
	border-width: 1px;
	margin: 10px auto;
}

.liuyanban td {
	border-style: dotted;
	border-color: #CCCCCC;
	border-width: 1px;
}

.beijing {
	background: #E5F0F4;
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
				<td><form id="query">
						<table align="left">
							<tr>
								<td><font style="color:#3967a3 "> 企业名:</font><input
									type="text" name="queryName" class="ww" id="queryName">
								</td>

								<td><span> <font style="color:#3967a3 ">注册时间:</font>
								</span> <input name="startTime" id="startTime" type="text" id="date"
									class="ww" onclick="SelectDate(this,'yyyy/MM/dd');"
									style="border: 1;">-- <input name="endTime"
									id="endTime" type="text" id="date" class="ww"
									onclick="SelectDate(this,'yyyy/MM/dd');" style="border: 1;">
								</td>
								<td align="left"><a href="javascript:query()"><div
											style="padding-top: 5px;"></div>&nbsp;<span
										class="icon-search">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;查询</span>&nbsp;&nbsp;</a>
								</td>
								<td><a href="javascript:UpdateRole()"><div
											style="padding-top: 5px;"></div>&nbsp;<span class="icon-edit">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;修改角色</span>&nbsp;&nbsp;</a>
								</td>
								<td><a href="javascript:UpdateQiXian()"><div
											style="padding-top: 5px;"></div>&nbsp;<span class="icon-edit">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;设置体验期限</span>&nbsp;&nbsp;</a>
								</td>
								<td><a href="javascript:UpdateState()"><div
											style="padding-top: 5px;"></div>&nbsp;<span class="icon-edit">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;修改状态</span>&nbsp;&nbsp;</a>
								</td>
								<!-- <td><a href="javascript:UpdatePrivilege()"><div
											style="padding-top: 5px;"></div>&nbsp;<span class="icon-edit">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;修改特定级别权限</span>&nbsp;&nbsp;</a>
								</td> -->
							</tr>
						</table>
					</form>
				</td>
				<td><table align="right">
						<tr>
							<!-- <td><a href="javascript:AddUser()"><div
										style="padding-top: 5px;"></div>&nbsp;<span class="icon-add">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>添加&nbsp;&nbsp;</a>
							</td> -->
							<!-- <td><a href="javascript:UpdateUser()"><div
										style="padding-top: 5px;"></div>&nbsp;<span class="icon-edit">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;修改</span>&nbsp;&nbsp;</a>
							</td> -->

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
<form id="updateQiXian" target="hidden_frame" enctype="multipart/form-data"
		method="post">
		<div class="xx21">
			<div
				style="margin-top: 5px; margin-bottom: 5px; margin-left: 10px;*margin-top:10px; *margin-bottom: 10px; *margin-left: 15px;">
				<table>
					<tr style="line-height: 20px;">
						<td><span>体验期限</span></td>
						<td><input type="text" name="qx" id="qx" value="null" />
						</td>
					</tr>
					<tr style="line-height: 20px;">
						<td rowspan="2"><span style="color: red;">以“月”为单位</span></td>
						<input type="hidden" name="enterId" id="enterId">
						</td>
					</tr>
				</table>
			</div>
				</div>
				</form>
	<!-- <form id="addUser" target="hidden_frame" enctype="multipart/form-data"
		method="post">
		<div class="xx21">
			<div
				style="margin-top: 5px; margin-bottom: 5px; margin-left: 10px;*margin-top:10px; *margin-bottom: 10px; *margin-left: 15px;">
				<table>
					<tr style="line-height: 20px;">
						<td><span>用户名:</span>
						</td>
						<td><input type="text" name="userName" id="userName" /></td>
					</tr>
					<tr style="line-height: 20px;">
					<td><span>企业用户名：</span>
					</td>
					<td><input type="text" name="enterName"
						id="enterName" /></td>
				</tr>
					<tr style="line-height: 20px;">
						<td><span>企业名称：</span></td>
						<td><input type="text" name="enter" id="enter" />
						</td>
					</tr>
					<tr style="line-height: 20px;">
						<td><span>url：</span></td>
						<td><input type="text" name="url" id="url" />
						</td>
					</tr>
					<tr style="line-height: 20px;">
						<td><span>Token：</span></td>
						<td><input type="text" name="token" id="token" />
						</td>
					</tr>
					<tr style="line-height: 20px;">
						<td><span>电话：</span></td>
						<td><input type="text" name="phone" id="phone" />
						</td>
					</tr>
					<tr style="line-height: 20px;">
						<td><span>Email：</span></td>
						<td><input type="text" name="email" id="email" />
						</td>
					</tr>
					<tr style="line-height: 20px;">
						<td><span>Address：</span></td>
						<td><input type="text" name="address" id="address" />
						</td>
						<input type="hidden" name="enterId" id="enterId">
					</tr>
					<tr style="line-height: 20px;">
						<td><span>授权码：</span></td>
						<td><input type="text" name="code" id="code" />
						</td>
						<input type="hidden" name="enterId" id="enterId">
					</tr>
					<tr style="line-height: 20px;">
						<td><span>授权状态：</span></td>
						<td><select id="state" name="state"
							style="width:140px;[;width:133px;];*width: 128px;width:133px\9\0">
								<option>授权未通过</option>
								<option>授权通过</option>
						</select></td>

					</tr>
				</table>
			</div>
		</div>
		<input type="hidden" id="userId" name="userId">
	</form> -->
	<div id="updateuser" class="easyui-window" title="修改"
		style=" top:20px;left:380px;overflow:hidden;background:#E2EDFF;width:300px;height:auto;[;height:auto;];*height:auto;height:auto\9\0;"
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
	<div id="updateTime" class="easyui-window" title="设置期限"
		style=" top:20px;left:380px;overflow:hidden;background:#E2EDFF;width:300px;height:auto;[;height:auto;];*height:auto;height:auto\9\0;"
		iconCls="icon-edit" closed="true" maximizable="false"
		minimizable="false" collapsible="false">
		<div id="time"></div>
		<div>&nbsp;</div>
		<div align="center">
			<a class="easyui-linkbutton" iconCls="icon-ok"
				href="javascript:void(0)"
				style="position:relative;top:-15px;*top:-22px;" onClick="updateQX()">修改</a>
			<a class="easyui-linkbutton" iconCls="icon-cancel"
				href="javascript:void(0)"
				style="position:relative;top:-15px;*top:-22px;" onclick="close1();">取消</a>
		</div>
	</div>
	<div id="enterInfor" class="easyui-window" scroll="no" title="企业详细信息"
		style="background:#E2EDFF;width:800px;height:auto; top:20px;visibility:hidden;"
		iconCls="icon-edit" data-options="modal:true,closed:true"
		closed="true" maximizable="false" minimizable="false"
		collapsible="false">



		<!-- [;height:490px;];*height:490px;*top:40;height:470px\9\0;visibility:hidden;
	<div id="huiyuanxingqing" scroll="no" class="easyui-window" title="会员详情及留言回复" style="background:#E2EDFF;width:800px;height:500px;top:20px;"
    iconCls="icon-add" closed="true" data-options="modal:true,closed:true"
     maximizable="false" minimizable="false" collapsible="false"> -->

		<div
			style="border:1px solid #7CB7DD;border-radius:8px;width:760px;margin:10px auto;*position:relative;*left:10px; ">
			<table class="kehustable" cellpadding="0" cellspacing="0"
				style="*position:relative;*left:4%;">
				<input type="hidden" id="huiyuanid" name="huyuanid" />
				<tr>
					<td class="beijing">用户名：</td>
					<td><input type="text" id="userName" name="userName"
						readonly="true" class="kehus" /></td>

					<td class="beijing">企业名：</td>
					<td><input type="text" id="enterName" name="enterName"
						readonly="true" class="kehus" /></td>
				</tr>

				<tr>
					<td class="beijing">Url：</td>
					<td><input type="text" id="url" name="url" readonly="true"
						class="kehus" /></td>

					<td class="beijing">Tooken：</td>
					<td><input type="text" id="tooken" name="tooken"
						readonly="true" class="kehus" /></td>
				</tr>

				<tr>
					<td class="beijing">电话：</td>
					<td><input type="text" id="phone" name="phone" readonly="true"
						class="kehus" /></td>

					<td class="beijing">邮箱：</td>
					<td><input type="text" id="email" name="email" readonly="true"
						class="kehus" /></td>
				</tr>

				<tr>
					<td class="beijing">地址：</td>
					<td><input type="text" id="address" name="address"
						readonly="true" class="kehus" /></td>

					<td class="beijing">授权码：</td>
					<td><input type="text" id="code" name="code" readonly="true"
						class="kehus" width="500px" /></td>
				</tr>


				<tr>
					<td class="beijing">appId：</td>
					<td><input type="text" id="appid" name="appid" readonly="true"
						class="kehus" /></td>

					<td class="beijing">appSecret：</td>
					<td><input type="text" id="appsecret" name="appsecret"
						readonly="true" class="kehus" /></td>
				</tr>



				<tr>
					<td class="beijing">角色：</td>
					<td><input type="text" id="role" name="role" readonly="true"
						class="kehus" /></td>

					<td class="beijing">状态：</td>
					<td><input type="text" id="zhuangtai" name="zhuangtai"
						readonly="true" class="kehus" /></td>
				</tr>

				<tr>
					<td class="beijing">查看：</td>
					<input type="text" style="display: none;" id="seeId">
					<td><a href="javascript:seeEnter()"><div
								style="padding-top: 5px;"></div>&nbsp;<span class="icon-edit">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;查看</span>&nbsp;&nbsp;</a>
					</td>
				</tr>
			</table>
		</div>




	</div>

	<div id="laPage" class="easyui-window" title="角色"
		style="padding:0;width:400px;height:300px;" iconCls="icon-save"
		closed="true" maximizable="false"
		data-options="modal:true,closed:true" minimizable="false"
		collapsible="false">
		<div id="pageToolbar" class="test" style="visibility: visible;">

			<table width="100%">
				<tr>

					<td>
						<table align="right">

							<tr>
								<td id="xzDomain"><a href="javascript:EditRole()"><div
											style="padding-top: 5px;"></div>&nbsp;<span
										class="icon-remove">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>修改</span> </a></td>
								<input type="text" style="display: none;" id="RenterId">
							</tr>
						</table></td>
				</tr>
			</table>
		</div>
		<table style="float:left;" id="pageTt" >
		</table>
		
	</div>
</body>
</html>