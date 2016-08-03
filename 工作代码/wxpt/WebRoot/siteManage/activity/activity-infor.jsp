<%@page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<script src="<%=basePath %>manager/js/TQEditor/TQEditor.full.min.js"
	type="text/javascript"></script>
<script src="<%=basePath %>manager/js/TQEditor/TQEditor.min.js" 
	type="text/javascript"></script>
<link href="<%=basePath %>manager/js/TQEditor/TQEditor.css" rel="stylesheet"
	type="text/css" />
<link href="<%=basePath %>manager/js/TQEditor/QuirksMode.css" rel="stylesheet"
	type="text/css" />
	<script src="<%=basePath %>manager/js/TQEditor/TQEditor.full.min.js"
	type="text/javascript"></script>
<script src="<%=basePath %>manager/js/TQEditor/TQEditor.min.js"
	type="text/javascript"></script>
<link href="<%=basePath %>manager/js/TQEditor/TQEditor.css" rel="stylesheet"
	type="text/css" />
<link href="<%=basePath %>manager/js/TQEditor/QuirksMode.css" rel="stylesheet"
	type="text/css" />
	<script src="<%=basePath %>manager/js/TQEditor/TQEditor.js" type="text/javascript"></script>
		<link rel="stylesheet" type="text/css" href="<%=basePath %>manager/css/default.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath %>manager/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath %>manager/css/demo.css">
	<link id="easyuiTheme" rel="stylesheet"  href="<%=basePath %>manager/themes/default/easyui.css" type="text/css">
	<script type="text/javascript" src="<%=basePath %>manager/js/jquery-1.8.0.min.js"></script>
	<script type="text/javascript" src="<%=basePath %>manager/js/jquery.form.js"></script>
	<script type="text/javascript" src="<%=basePath %>manager/js/jquery.easyui.min.js"></script>
	<script type="text/javascript"  src="<%=basePath %>manager/js/jquery.cookie.js"></script> 
	<script type="text/javascript" src="<%=basePath %>manager/js/hd.js"></script>
	<script type="text/javascript" src="<%=basePath %>manager/js/activity/activity.js"></script>
	<script type="text/javascript" src="<%=basePath %>manager/js/activity/WebCalendar.js"></script>
<style type="text/css">
* {
	font-size: 12px;
}

body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
	background-color: #EEF2FB;
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
#list,#a,#text{
display:none;
/* 	visibility: hidden; */
}
.xx21{margin:10px 10px 10px 10px; border:1px solid #91ABD1;border-radius:8px;}
.ww{border:1px solid #95B8E7}
</style>
<body>
<table style="float:left;" id="tt">
	</table>
	
	<div id="toolbar" class="test">
	
	<table  width="1300">
		<tr>
		<td>
				<table>
					<tr>
						<td><span>标题名称:</span><input type="text" name="seltitle" id="seltitle" size=10 class="ww"/></td>
					<td><span>活动地点:</span><input type="text" name="seladress" id="seladress" size=10 class="ww"/></td>
										<td><span>活动内容:</span><input type="text" name="selcontent" id="selcontent" size=10 class="ww"/></td>
										<td height="40">活动时间:<input name="startTime"
														type="text" id="startTime"size=10
														onclick="SelectDate(this,'yyyy/MM/dd');" value="点击选择时间"
														class="ww">--<input
														name="endTime" type="text" id="endTime"size=10
														onclick="SelectDate(this,'yyyy/MM/dd');" value="点击选择时间"
														 class="ww">
													</td>
													<td><a href="javascript:query()"><div style="padding-top: 5px;"></div>&nbsp;<span
												class="icon-search">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>查询&nbsp;&nbsp;</a>
										</td>
					</tr>
				</table>
			</td>
			<td>
			<div style="position:relative;right:-150px;">
				<table align="right">
					<tr>
						<td width="600px">
						<a href="javascript:seeReserve()"><div style="padding-top: 5px;"></div>&nbsp;<span
									class="icon-search">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>预定管理&nbsp;&nbsp;</a>
							<div class="datagrid-btn-separator"></div>
						<a href="javascript:openactivity()"><div style="padding-top: 5px;"></div>&nbsp;<span class="icon-add">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>添加信息&nbsp;&nbsp;</a>
							<div class="datagrid-btn-separator"></div> <a
								href="javascript:updateactivity()"><div style="padding-top: 5px;"></div>&nbsp;<span
									class="icon-edit">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>修改信息&nbsp;&nbsp;</a>
							<div class="datagrid-btn-separator"></div> <a
								href="javascript:delActivity()"><div style="padding-top: 5px;"></div>&nbsp;<span
									class="icon-remove">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>删除信息&nbsp;&nbsp;</a>
							<div class="datagrid-btn-separator"></div>
					</tr>
				</table>
				</div>
			</td>
		</tr>
	</table>
	</div>
	
	<div id="ad" class="easyui-window" title="查看所有活动预定"
		style="padding:0;width:800px;height:500px;" iconCls="icon-save"
		closed="true" maximizable="false"
		data-options="modal:true,closed:true" minimizable="false"
		collapsible="false">
		<table style="float:left;" id="tt1">
		</table>
		<div id="toolbar1" class="test">

			<table width="100%">
				<tr>
					<td>
						<table cellpadding="0" cellspacing="0" border="0">
							<tr>
								<td><span>姓名:</span><input type="text"
									name="ReserveName" id="ReserveName" size=10 class="ww"/></td>
								<td><span>微信账号:</span><input type="text"
									name="ReserveWeixin" id="ReserveWeixin" size=10 class="ww"/></td>
								<td><a href="javascript:queryReserve()"><div
											style="padding-top: 5px;"></div>&nbsp;<span
										class="icon-search">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>查询&nbsp;&nbsp;</a>
								</td>
							</tr>
						</table></td>
					<!-- <td>
						<table align="right" >
							<tr>
								<td><a href="javascript:delDomain()"><div
											style="padding-top: 5px;"></div>&nbsp;<span
										class="icon-remove">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>删除&nbsp;&nbsp;</a>
								</td>
							</tr>
						</table></td> -->
				</tr>
			</table>
		</div>
	</div>
	
	 <div id="add" class="easyui-window" title="添加信息" style="overflow:hidden;background:#E2EDFF;width:760px;height:580px; top:50px;"
    iconCls="icon-edit" closed="true" maximizable="false" minimizable="false" collapsible="false">
     <div id="infor">
     </div>
     <div>&nbsp;</div>
     <div align="center">
     <a class="easyui-linkbutton" iconCls="icon-ok" style="position:relative;top:-10px;" href="javascript:void(0)" onClick="addActivity()">确定</a>
     <a class="easyui-linkbutton" iconCls="icon-cancel" style="position:relative;top:-10px;" href="javascript:void(0)" onClick="close1()">取消</a>
     </div>
    </div>
    	<div id="update" class="easyui-window" title="修改信息" style="overflow:hidden;background:#E2EDFF;width:760px;height:580px; top:50px;"
    iconCls="icon-edit" closed="true" maximizable="false" minimizable="false" collapsible="false">
     <div id="infor1">
     </div>
     <div>&nbsp;</div>
     <div align="center">
     <a class="easyui-linkbutton" iconCls="icon-ok" style="position:relative;top:-10px;" href="javascript:void(0)" onClick="updateAct()">确定</a>
     <a class="easyui-linkbutton" iconCls="icon-cancel" style="position:relative;top:-10px;" href="javascript:void(0)" onClick="close2()">取消</a>
     </div>
    </div>
   <form id="activityForm" method="post" enctype="multipart/form-data">
	<div class="xx21">
		<table style="margin-top:10px;margin-bottom:10px; margin-left:10px;">
			<tr>
				<td valign="top">
			活动标题：
			    </td>
			    <td>
					<input type="text" id="title" name="title" style="width:400px; height:23px;" class="ww"/>
				</td>
			</tr>
			<tr>
				<td valign="top">
			活动时间：
			    </td>
			    <td>
			    <input type="text" id="time" name="time" style="width:400px; height:23px;" onclick="SelectDate(this,'yyyy/MM/dd');" value="点击此处选择时间"
														style="border: 0; width: 30%" class="ww"/>
				</td>
			</tr>
			<tr>
				<td valign="top">
			活动地点：
			    </td>
			    <td>
			    	<textarea  style="width:670px; height:100px;" name="adress"
					id="adress"  class="ww"></textarea>
				</td>
			</tr>
			<tr>
				<td valign="top">
			活动内容：
			    </td>
			    <td>
			    <div id = "idcontent">
			     <textarea  style="width:670px; height:230px;" name="content"
					id="content"  class="ww"></textarea>
					</div>
				</td>
			</tr>
			</table>
	</div>
	</form>
</body>