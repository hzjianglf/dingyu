<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePathindex = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>

<html>
<head>
<meta charset="UTF-8">
<title>传播统计平台</title>
<link rel="stylesheet" type="text/css"
	href="<%=basePathindex%>manager/css/default.css">
<link rel="stylesheet" type="text/css"
	href="<%=basePathindex%>manager/themes/default/easyui.css" />
<link rel="stylesheet" type="text/css"
	href="<%=basePathindex%>manager/themes/icon.css">
<link rel="stylesheet" type="text/css"
	href="<%=basePathindex%>manager/css/demo.css">
<link id="easyuiTheme" rel="stylesheet"
	href="<%=basePathindex%>manager/themes/default/easyui.css"
	type="text/css">
<script type="text/javascript"
	src="<%=basePathindex%>manager/js/jquery-1.8.0.min.js"></script>
<script type="text/javascript"
	src="<%=basePathindex%>manager/js/jquery.easyui.min.js"></script>
<%-- <script type="text/javascript"  src="<%=basePathindex %>manager/js/jquery.cookie.js"></script>  --%>
<script type="text/javascript" src="<%=basePathindex%>manager/js/hd.js"></script>
<style type="text/css">
.hidden {
	visibility: hidden;
}

#north,#south,#mainPanle {
	visibility: hidden;
}

#mm {
	visibility: hidden;
}

#w {
	visibility: hidden;
}

.xx21 {
	margin: 5px 5px 5px 5px;
	border: 1px solid #91ABD1;
	border-radius: 8px;
}
</style>
<script type="text/javascript">
	$(function(){
	$("#north").css("visibility","visible");
	$("#south").css("visibility","visible");
	$("#mainPanle").css("visibility","visible");
	$(".easyui-accordion .hidden").css("visibility","visible");
	$("#tabs").append('<div title="我的主页" style="width:100%;height:100%;padding:0;margin:0;" id="home">');
	addTab("我的主页","<%=basePathindex%>site/manage-welcome2","mainFrame1");
	InitLeftMenu();
	tabClose();
	tabCloseEven();
	

});

function out(){
	$.messager.confirm('系统提示', '您确定要退出本次登录吗?', function(r) {
           if (r) {
           		var date=new Date();
				 date.setTime(date.getTime()-10000);
		     	 document.cookie="wxpt"+"=a;expires="+date.toGMTString()+"; path=/";
               window.location.href="<%=basePathindex%>site/manage!manage";
           }
	});
}
function close(){
	$('#xiugai').window('close');
}
function edit(){
	  $("#xiugai").css("visibility","visible");
	  $("#ff").css("visibility","visible");
	  $('#xiugai').window('open');
	  $('#ff').show();
	  $('#ff').form('clear');
	  $('#ff').appendTo('#xx');
}
function change(id){
	if($("#pwd1").val()==""){
		alert("旧密码不能为空!");
	}else if(($("#pwd2").val() != $("#pwd3").val())||$("#pwd2").val()==""||$("#pwd3").val()==""){
		alert("两次密码输入不一致!");
	}else{
		$.ajax({
			type:"post",
			url:"<%=basePathindex%>site/manage-user!getUp",
			data : {
				'pwd2':$("#pwd2").val(),
				'pwd1':$("#pwd1").val(),
				'userName':id
			},
			success:function(text){
				var str = eval(text);
				if(str.name == 0){
					alert("登陆失效");
					window.location.href="<%=basePathindex%>site/manage!login";
				}else{
					alert(str.name);
					$('#xiugai').window('close');
				}
			},
			error:function (){
			}
		});
	}
}



function shouye(){
window.location.href="<%=basePathindex%>site/manage!login";

	}
	function refesh() {

		window.location.reload();

	}
</script>
<style>
#ff,#xiugai {
	visibility: hidden;
}

#admin_txt {
	float: right;
	font-size: 12px;
	text-decoration: none;
	position: 100%;
	line-height: 38px;
	font-family: Arial, Helvetica, sans-serif;
	margin-right: 20px;
}
</style>
</head>
<body style=" padding:0px;overflow-y: hidden;" class="easyui-layout">
	<div id="north" data-options="region:'north'"
		style="overflow: hidden;height: 100px;">
		<div
			style="overflow:hidden; height: 68px; 
        background: url(<%=basePathindex%>manager/images/top-bg.png) #7f99be repeat-x center 0%;
        line-height: 20px;color: #fff; font-family: Verdana, 微软雅黑,黑体">

			<img src="<%=basePathindex%>manager/images/logo.png" width="363"
				height="70" /> <span id="admin_txt" class="head"
				style="*margin-top: -70px"> <a href="javascript:void(0);"
				onClick="shouye();"> <img
					src="<%=basePathindex%>manager/images/shoye.png" border="none" />
			</a> <a href="javascript:void(0);" onClick="edit();"> <img
					src="<%=basePathindex%>manager/images/mima.png" border="none" />
			</a> <a href="javascript:void(0);" onClick="refesh();"> <img
					src="<%=basePathindex%>manager/images/refesh.png" border="none" />
			</a> <a href="javascript:void(0);" onClick="out();"><img
					src="<%=basePathindex%>manager/images/exit.png" border="none" />
			</a> </span>
		</div>

		<div id="bg"
			style="overflow:hidden; height: 34px;repeat-x center 0%
        background: url(<%=basePathindex%>manager/images/botom_bg.png);">
			&nbsp; <img alt="" src="<%=basePathindex%>manager/images/top_b1.png">
			<font color="#3967a3" style="font-size: 18px; font-family:  宋体;">欢迎您:&nbsp;</font><font
				color="red" style="font-size: 20px; font-family:  宋体;">系统管理员</font>
		</div>
	</div>
	<div data-options="region:'west',split:true" title="导航菜单"
		style="width:180px;" id="west">
		<div class="easyui-accordion" data-options="fit:true,border:false">

			<div title="我的主页" icon="icon-sys" data-options="selected:true"
				style="overflow:auto;">

				<ul class="hidden">
					<li>
						<div class="dd">
							<a target="mainFrame1"
								href="<%=basePathindex%>site/manage-welcome"> <span
								class="ss">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>我的主页</a>
						</div></li>
				</ul>
			</div>

			<div title="用户管理" icon="icon-sys" data-options="selected:true"
				style="overflow:auto;">
				
			</div>
			<div title="信息发送" icon="icon-sys" data-options="selected:true"
				style="overflow:auto;">
				<%-- <ul class="hidden">
					<li>
						<div class="dd">
							<a target="mainFrame2"
								href="<%=basePathindex%>site/manage!quanxian"> <span
								class="ss">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>权限管理</a>
						</div></li>

				</ul> --%>
			</div>

			<div title="传播统计" icon="icon-sys" data-options="selected:true"
				style="overflow:auto;">
				<ul class="hidden">
					<li>
						<div class="dd">
								<a target="mainFrame2"
									href="<%=basePathindex%>site/manage!tongji"> <span
									class="ss">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>统计demo</a>
							</div>
					</li>
					<li>
						<div class="dd">
								<a target="mainFrame3"
									href="<%=basePathindex%>site/manage!zontTongji"> <span
									class="ss">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>统计视图</a>
							</div>
					</li>
				</ul>
			</div>
			<div title="平台管理" icon="icon-sys" data-options="selected:true"
				style="overflow:auto;">
				
			</div>
		</div>
	</div>
	<div id="mainPanle" region="center"
		style="background: #eee; overflow-y:hidden">
		<div id="tabs" class="easyui-tabs" fit="true" border="false"
			overflow="hidden"></div>
	</div>

	<input type="hidden" id="basePath" name="basePath"
		value="<%=basePathindex%>" />
	<div id="mm" class="easyui-menu" style="width:150px;">
		<div style="border:0;" id="mm-tabclose">关闭</div>
		<div style="border:0;" id="mm-tabcloseall">全部关闭</div>
		<div style="border:0;" id="mm-tabcloseother">除此之外全部关闭</div>
		<div class="menu-sep"></div>
		<div style="border:0;" id="mm-tabcloseright">当前页右侧全部关闭</div>
		<div style="border:0;" id="mm-tabcloseleft">当前页左侧全部关闭</div>
		<div class="menu-sep"></div>
		<div style="border:0;" id="mm-exit">退出</div>
	</div>
	<div>
		<form id="ff" style="margin-left: 70px;">

			<table>
				<tr>
					<td>旧密码：</td>
					<td><input type="password" name="pwd1" id="pwd1">
					</td>
				</tr>
				<tr>
					<td>新密码：</td>
					<td><input type="password" name="pwd2" id="pwd2">
					</td>
				</tr>
				<tr>
					<td>确认新密码：</td>
					<td><input type="password" name="pwd3" id="pwd3">
					</td>
				</tr>
			</table>

		</form>
	</div>
	<div id="xiugai" class="easyui-window" title="修改"
		style="padding: 10px;width:400px;height:200px;background:#e0edff;top:0px;"
		iconCls="icon-edit" closed="true"
		data-options="modal:true,closed:true" maximizable="false"
		minimizable="false" collapsible="false" draggable="true">
		<div id="xx"></div>
		<div>&nbsp;</div>
		<div align="center">
			<a class="easyui-linkbutton" iconCls="icon-ok"
				style="position:relative;top:8px;" href="javascript:void(0)"
				onClick="change('${userName}')">修改</a> <a class="easyui-linkbutton"
				iconCls="icon-cancel"
				style="margin-left:40px; position:relative;top:8px;"
				href="javascript:close()">取消</a>
		</div>
	</div>
</body>
</html>