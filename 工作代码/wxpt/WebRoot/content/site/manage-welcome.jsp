<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
      <%@ taglib prefix="s" uri="/struts-tags"%>
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

<link rel="stylesheet" type="text/css" href="<%=basePath %>manager/css/default.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath %>manager/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath %>manager/css/demo.css">
	<link id="easyuiTheme" rel="stylesheet"  href="<%=basePath %>manager/themes/default/easyui.css" type="text/css">
	<script type="text/javascript" src="<%=basePath %>manager/js/jquery-1.8.0.min.js"></script>
	<script type="text/javascript" src="<%=basePath %>manager/js/jquery.easyui.min.js"></script>
<%-- 	<script type="text/javascript" src="<%=basePath %>manager/js/cookie.js"></script>--%>	<script type="text/javascript" >
$(function(){
	 $('#welcome').datagrid({
	    title: '欢迎界面',
	    loadMsg: "数据加载中，请稍后……",
	    nowrap: false,
	    striped: true,
	    width:1000,
	    height:300,
	    //fit: true,//自动大小
	    fitColumns: true,
	    collapsible: true,
	    singleSelect:true,
	    remoteSort: false,
	    columns: [[
	        { field: 'RoleCode', title: '欢迎您进入微聚家后台管理界面',width: 120, align: 'center', sortable: true },

	    ]],
	    rownumbers: true
	}); 

	
});
</script>
	<!-- 屏蔽浏览器右键菜单功能- -->
 <script type="text/javascript">
document.oncontextmenu=function(){
var selection="";//定义鼠标拖选值
if(document.selection){//***** IE
selection=document.selection.createRange().text;
}else{//***** 其他浏览器
selection=document.getSelection();
}
//if来判断拖选值、被单击的区域是不是表单域值
if(selection==""&&(event.srcElement.value==undefined||event.srcElement.value==0)&&event.srcElement.value!=0)return false;
}

function onRowContextMenu(e,rowIndex,rowData){
	/*e.preventDafault();*/
	e.preventDefault();
}
</script> 
	
</head>
<body style="background-color:white;padding:20px;">
	
			<table style="float:left;" id="welcome" >
				
            </table>
            

        <!-- 另一个 -->
        <div>&nbsp;</div>
        <div id="p" class="easyui-panel" title="待办事项" style="width:400px;height:200px;padding:10px;float:left;"
				data-options="iconCls:'icon-save'">
			<font style="color:#3967a3 ">这里是待办事项</font>
		</div>
		
		<!--  -->
		<div class="easyui-tabs" style="width:560px;height:200px;margin-top:-200px;position:relative;left:440px;top:0px;">
		<div title="关于企业信息" style="padding:10px;">
			<font style="color:#3967a3 ">这里是企业信息</font>
		</div>
		<div title="目录展示" style="padding:10px;">
			<ul class="easyui-tree" data-options="url:'tree_data1.json',animate:true"></ul>
		</div>
		<div title="帮助" data-options="iconCls:'icon-help',closable:true" style="padding:10px;">
			这里是帮助信息
		</div>
	</div>
<%-- <table>
			<tr>
			<td colspan="2" >发布信息共<a href="prompt!proEnterInfor" style="color: red">${AllCount}</a>条，审核通过<a href="prompt!proEnterInfor?inforState=1" style="color: red">${PassCount}</a>条，未通过<a href="prompt!proEnterInfor?inforState=0" style="color: red">${notPassCount}</a>条</td>
			</tr>
			<tr><td>最新发布：</td><td></td></tr>
			<s:iterator value="enterInforList" var="el">
			<tr><td colspan="2"><a href="prompt!proEnterInfor?inforId=${el.informationId}" style="color: red">${el.informationTitle}</a></td></tr>
			</s:iterator>
			</table> --%>
</body>
</html>