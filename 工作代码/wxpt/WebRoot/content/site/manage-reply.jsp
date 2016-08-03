<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePathindex = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
  <jsp:include page="testCookie.jsp" flush="true" />
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" type="text/css" href="<%=basePathindex %>manager/css/default.css">
	<link rel="stylesheet" type="text/css" href="<%=basePathindex %>themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="<%=basePathindex %>manager/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="<%=basePathindex %>manager/css/demo.css">
	<link id="easyuiTheme" rel="stylesheet"  href="<%=basePathindex %>manager/themes/default/easyui.css" type="text/css">
<!--  	<link type="text/css" rel="stylesheet" href="../../manager/js/easyrt.css"/> -->
	<script type="text/javascript" src="<%=basePathindex %>manager/js/jquery-1.8.0.min.js"></script>	  
	<script type="text/javascript" src="<%=basePathindex %>manager/js/jquery.easyui.min.js"></script>
	<script type="text/javascript"  src="<%=basePathindex %>manager/js/jquery.cookie.js"></script> 
    <script type="text/javascript" src="<%=basePathindex %>manager/js/jquery.form.js"></script>
	<script type="text/javascript" src="<%=basePathindex %>manager/js/hd.js"></script>
	<script type="text/javascript" src="<%=basePathindex %>manager/js/reply.js"></script>
	<style type="text/css">
		 #ff,#updatereply,#addReply,#addreply,#lan,#toolbar{visibility: hidden;}
		
		.test a{vertical-align:center;display: block;float: left; height: 20px;padding-bottom: 2px;border: 1px solid #EFEFEF;}
		.test a:link{text-decoration: none; color:#000;}
		.test a:active{text-decoration: none;color:#000}
		.test a:visited{text-decoration: none;color:#000}
		.test a:hover{text-decoration: none;color:#000; background:#eaf2ff; border: 1px solid #dddddd;}   
		.ww{border:1px solid #95B8E7}
		
		select{width: 200px}
	</style>
</head>
  
  <body class="easyui-layout" data-options="border:false" 
  style="background-color:white;padding:0px;overflow-y:hidden;">
  <div id="toolbar" class="test">

		<table width="100%">
			<tr>
				<td>
						<table align="right">
							<tr>
								 <td>
									<a href="javascript:ClearReply()"><div style="padding-top: 5px;"></div>&nbsp;<span class="icon-edit">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>取消&nbsp;&nbsp;</a><div class="datagrid-btn-separator"></div>
								</td>
								<td>
									<a href="javascript:UpdateReply()"><div style="padding-top: 5px;"></div>&nbsp;<span class="icon-add">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;修改</span>
									&nbsp;&nbsp;</a><div class="datagrid-btn-separator"></div>
								</td>
								<!-- <td>
									<a href="javascript:DeleteArea()"><div style="padding-top: 5px;"></div>&nbsp;<span class="icon-edit">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>删除区域&nbsp;&nbsp;</a><div class="datagrid-btn-separator"></div>
								</td> -->
								
							</tr>
						</table>
					</td>
			</tr>
		</table>
	</div>
  
  
  <div  data-options="region:'center',split:true"> 
    <table style="float:left;" id="tt">
    </table>
    <div id="addreply" class="easyui-window" title="查看回复内容"
		style="overflow:hidden;background:#E2EDFF;width:245px;height:140px;top:100px;" iconCls="icon-save"
		closed="true" maximizable="false"
		data-options="modal:true,closed:true" minimizable="false"
		collapsible="false">
		<div id="eeXL"></div>
		<div><span id="add"></span></div>
	</div>
    
    <div id="updatereply" class="easyui-window" title="回复修改"
		style="overflow:hidden;background:#E2EDFF;width:300px;height:140px;top:100px;" iconCls="icon-save"
		closed="true" maximizable="false"
		data-options="modal:true,closed:true" minimizable="false"
		collapsible="false">
		<div id="update"></div>
		<div>&nbsp;</div>
		<div align="center">
			<a class="easyui-linkbutton"  style="position:relative;top:-18px;top:-23px\9;" iconCls="icon-ok"
				href="javascript:void(0)" onClick="update()">修改</a> <a
				class="easyui-linkbutton"  style="position:relative;top:-18px;top:-23px\9;" iconCls="icon-cancel"
				href="javascript:void(0)" onClick="close1()">取消</a>
		</div>
	</div>
             
    
    <!-- 编辑 -->
    
          
           
          
     <form id="addReply" method="post">
		<div style="margin:10px 10px 10px 10px; border:1px solid #91ABD1;border-radius:8px;">
		<div style="line-height: 25px; margin-top: 5px; margin-left: 18px;">
		<span>回复内容:</span>
		
		<input size=25  type="hidden" id="keywordID" name="keywordID" />
		<input size=25  type="text" id="keyName" name="keyName" readonly="readonly" onclick="chooseKey()" /><br><font color="red">*双击文本框选择关键字</font>		</div>
		</div>
	</form>       
            
            
    
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

					<td></td>
				</tr>
			</table>
		</div>
	</div>
    
    
   
  </body>
</html>
