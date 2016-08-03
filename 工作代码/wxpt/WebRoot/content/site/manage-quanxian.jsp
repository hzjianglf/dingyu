<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  
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
<script type="text/javascript" src="../manager/js/quanxian.js"></script>
<script type="text/javascript" language="javascript"
	src="../manager/js/WebCalendar.js"></script>


<style type="text/css">
	    #quanxian a{vertical-align:center;display: block;float: left; height: 20px;padding-bottom: 2px;border: 1px solid #fff;}
		#quanxian a:link{text-decoration: none; color:#000;}
		#quanxian a:active{text-decoration: none;color:#000}
		#quanxian a:visited{text-decoration:none;color:#000}
		#quanxian a:hover{text-decoration: none;color:#000; background:#eaf2ff; border: 1px solid #dddddd;}  
	.ww{border:1px solid #26A0DA}
	</style>
  </head>
  
  <body>
  <table id="jiaose"   cellpadding="0" cellspacing="0" border="0" style="background-color:white;padding:0px;height:100%;width:100%;_height:100%;_width:100%;*height:100%;*width:100%;">
  </table>  
  <div id="quanxian">
  	<table>
  		<tr>
  			<td>
  			<a id="43" href="javascript:openquanxian2()"><div style="padding-top:5px;"></div>&nbsp;<span class="icon-add" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;权限设置</span>&nbsp;&nbsp;</a> 
  			</td>
  			
  			<td>
  			<a id="44" href="javascript:openaddjiaose2()"><div style="padding-top:5px;"></div>&nbsp;<span class="icon-add" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;角色添加</span>&nbsp;&nbsp;</a> 
  			</td>
  			
  			<td>
  			<a id="49" href="javascript:openupdatequanxian2()"><div style="padding-top:5px;"></div>&nbsp;<span class="icon-add" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;角色修改</span>&nbsp;&nbsp;</a> 
  			</td>
  			
  			<td>
  			<a id="50" href="javascript:deljuese2()"><div style="padding-top:5px;"></div>&nbsp;<span class="icon-add" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;角色删除</span>&nbsp;&nbsp;</a> 
  			</td>
  		</tr>
  	</table>
 </div>
 
 
     
      <div id="addjuese" class="easyui-window"  title="角色添加" style="overflow:hidden;background:#E2EDFF;width:270px;*width:320px;height:200px;
    iconCls="icon-add" closed="true" data-options="modal:true,closed:true"
     maximizable="false" minimizable="false" collapsible="false">
      <table style="margin:0 auto;">
      <tr>
      <td>
     <div style=" *height:100px; width:230px;*width:265px;margin:12 auto;*margin-left:20px;border:1px solid #91ABD1;border-radius:8px; " >
    
     		<table >
     			<tr >
     				<td style="*padding: 10px;">角&nbsp;色&nbsp;名&nbsp;&nbsp;:</td>
     			    <td style="*padding: 10px;"> <input class="ww" type="text" id="juesename" name="juesename"  class="ww"/></td>
     			</tr>
     		
     			<tr>
     				<td style="*padding: 10px;">角色说明:</td>
     			    <td style="*padding: 10px;"> <input class="ww" type="text" id="jueseshuoming" name="jueseshuoming" class="ww" /></td>
     			</tr>
     		</table>
     	
     	</div>
     	</td>
     	</tr>
     	<tr>
	     	<td align="center">
	     	<div style="position:relative;top:-10px;*top:0px;">
	     	  <a class="easyui-linkbutton" iconCls="icon-ok" href="javascript:void(0)" onClick="addjiaose()">添加</a>
              <a class="easyui-linkbutton" iconCls="icon-cancel" href="javascript:void(0)" onClick="closeaddjuese()">取消</a>
	     	</div>
	     	</td>
	     	
     	</tr>
     	</table>
     	</div>   
        
        <!--add角色end   -->
        
        
        <!--  修改角色 -->
                  <div id="updatejuese" class="easyui-window"  title="角色修改" style="overflow:hidden;background:#E2EDFF;width:270px;*width:320px;height:200px;
    iconCls="icon-add" closed="true" data-options="modal:true,closed:true"
     maximizable="false" minimizable="false" collapsible="false">
     <div style="height:200ps;width:200px;margin:0 auto;" >
     
     <table style="margin:0 auto;">
      <tr>
      <td>
     <div style=" *height:100px; width:230px;*width:265px;margin:12 auto;*margin-left:20px;border:1px solid #91ABD1;border-radius:8px; " >
     
     
     
     		<table>
     			<tr>
     				<td style="*padding: 10px;">角&nbsp;色&nbsp;名&nbsp;&nbsp;:</td>
     				<input type="hidden" name="roleid" id="roleid" />
     			    <td style="*padding: 10px;">  <input class="ww" type="text" id="juesenamex" name="juesename" /></td>
     			</tr>
     		
     			<tr>
     				<td style="*padding: 10px;">角色说明:</td>
     			    <td style="*padding: 10px;"> <input class="ww" type="text" id="jueseshuomingx" name="jueseshuoming" /></td>
     			</tr>
     		</table>
     	</div>
     		</td>
     	</tr>
     	<tr>
	     	<td align="center">
	     	<div style="position:relative;top:-10px;*top:0px;">
	     	 <a class="easyui-linkbutton" iconCls="icon-ok" href="javascript:void(0)" onClick="updatejiaose()">修改</a>
                     <a class="easyui-linkbutton" iconCls="icon-cancel" href="javascript:void(0)" onClick="closeupdatejuese()">取消</a>
	     	</div>
	     	</td>
	     	
     	</tr>
     	</table>
     	</div>
     	</div>   
     	  <!-- 修改角色end --> 

        
        
      <!--权限  树-->
      
    <div id="wc" scroll="no" class="easyui-window" title="权限设置" style="background:#E2EDFF;width:280px;height:450px;top:100px;overflow-x:hidden;overflow-y:hidden; visibility:hidden;"
    iconCls="icon-add" closed="true" data-options="modal:true,closed:true"
     maximizable="false" minimizable="false" collapsible="false">
      
    <div style="width:260px;height:380px;overflow-y:scroll;SCROLLBAR-ARROW-COLOR: ff0000;"> 
     <table border="0">
     <tr>
	     <td>
	       <ul id="ttwc" class="easyui-tree" data-options="url:'tree!quanxian',animate:true,cascadeCheck:true,checkbox:true"></ul> 
	    </td>
     </tr>
     </table>
    </div>
     <div style="position:relative;top:4px;left:40px;background:#E2EDFF;">
	       <a class="easyui-linkbutton"  iconCls="icon-ok" href="javascript:quanxianguanli();">提交</a>
	       <a class="easyui-linkbutton"  iconCls="icon-cancel" href="javascript:void(0)" onClick="closewc();">取消</a>
	 </div>
   </div>  
  
  </body>
</html>
