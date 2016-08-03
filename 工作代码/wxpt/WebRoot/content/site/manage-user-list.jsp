<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link rel="stylesheet" type="text/css" href="../../manager/css/default.css">
	<link rel="stylesheet" type="text/css" href="../../themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="../../manager/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="../../manager/css/demo.css">
	<link id="easyuiTheme" rel="stylesheet"  href="../../manager/themes/default/easyui.css" type="text/css">
 
	<script type="text/javascript" src="../../manager/js/jquery-1.8.0.min.js"></script>
	<script type="text/javascript" src="../../manager/js/jquery.easyui.min.js"></script>
	<script type="text/javascript"  src="../../manager/js/jquery.cookie.js"></script> 

	<script type="text/javascript" src="../../manager/js/hd.js"></script>
		<script type="text/javascript" src="../../manager/js/userList.js"></script>
		
		
		<style type="text/css">
		 #searchForm,#ff3,#fform,#chaxun,#tianjia{visibility: hidden;}
		
		
	</style>
		
</head>
<body style="background-color:white;padding:0px;">
		<div id="search" class="easyui-window" data-options="modal:true,closed:true" title="请输入用户名" closed="true">
		<form id="searchForm" method="post">
		<div style="margin:10px 10px 10px 10px;">
		<input type="text" id="userName3" name="userName3"/><a class="easyui-linkbutton" iconCls="icon-search" href="javascript:void(0)" onClick="search2()">查询</a>
		</div>
		</form>
		</div>
	
	<table id="toolbar" style="float:left;">
		
	</table>
<div id="addUser" class="easyui-window" data-options="modal:true,closed:true" title="添加宣传用户" style="padding: 10px;width: 230px;height:160px;overflow:hidden;"
    iconCls="icon-edit" closed="true" maximizable="false" minimizable="false" collapsible="false">
    <form id="ff3" method="post">
     <div align="center">
               用户姓名:<input  class="easyui-validatebox" type="text" id="userName" name="userName"></input>
           </div>
           <div align="center">
                用户密码:<input  class="easyui-validatebox" type="text" id="password" name="password"></input>
           </div>
           <div align="center">
                再次输入:<input  class="easyui-validatebox" type="text" id="repeatPassword" name="repeatPassword"></input>
           </div>
            <div align="center"  style="margin-top:10px;">
           <a class="easyui-linkbutton" iconCls="icon-ok" href="javascript:void(0)" onClick="add()">添加</a>
     	   <a class="easyui-linkbutton" iconCls="icon-cancel" href="javascript:void(0)" onClick="closeAddUser()">取消</a>
     	   </div>
  </form>
  </div>
  
  
  
  
    <div id="add" class="easyui-window" title="添加" style="padding: 10px;width: 250px;height:220px;"
    iconCls="icon-add" closed="true" maximizable="false" minimizable="false" collapsible="false">
     <div id="aa">
    </div>
    
    
     <div>&nbsp;</div>
     <div id="tianjia">
     <a class="easyui-linkbutton" iconCls="icon-ok" href="javascript:void(0)" onClick="add()">添加</a>
     <a class="easyui-linkbutton" iconCls="icon-cancel" href="javascript:void(0)" onClick="close1()">取消</a>
     </div>
    </div>
    <div id="edit" class="easyui-window" data-options="modal:true,closed:true" title="密码修改" style="padding: 10px;width: auto;height:auto; overflow:hidden;"
    iconCls="icon-edit" closed="true" maximizable="false" minimizable="false" collapsible="false">
     
  <form id="fform" method="post">
  <div  align="center">
  	 用&nbsp;户&nbsp;姓&nbsp;名: <input class="easyui-validatebox" type="text" id="userNameOn" name="userNameOn">
           
           <input type="hidden" class="easyui-validatebox" id="userId" name="userId" />
           
            <br>输入新密码: <input class="easyui-validatebox" type="text" id="passwords" name="passwords" value="${passwords }"></input>
          <br>
          
                再&nbsp;次&nbsp;输&nbsp;入: <input  class="easyui-validatebox" type="text" id="repeatChange" name="repeatChange" ></input>
       </div>   
       <div  align="center">  
           		<a class="easyui-linkbutton" iconCls="icon-ok" href="javascript:void(0)" style="margin-top:10px;" onClick="edit()">修改</a>
     			<a class="easyui-linkbutton" iconCls="icon-cancel" href="javascript:void(0)" onClick="closePwd()">取消</a>
     	</div>
          </form>
  
     </div>
     
    
    </div>
    <div id="query" class="easyui-window" title="查询" style="padding: 10px;width: 360px;height:100;"
    iconCls="icon-search" closed="true" maximizable="false" minimizable="false" collapsible="false">
     <div>
      <form id="chaxun">
       	<tr>
        <td>用户ID:</td>&nbsp;&nbsp;&nbsp;&nbsp;
        <td>&nbsp;&nbsp;&nbsp;<input type="text" name="userId" id="userId" class="easyui-numberbox"></td>
        </tr>
       	<tr><br>
        <td>用户姓名:</td>&nbsp;&nbsp;&nbsp;&nbsp;
        <td><input type="text" name="userName" id="userName" class="easyui-numberbox" ></td>
       	</tr>
       <br>
       
       <input type="button" value="查询" onClick="query()">
       <input type="button" value="取消">
      </form>
     </div>
    </div>
</body>
</html>
