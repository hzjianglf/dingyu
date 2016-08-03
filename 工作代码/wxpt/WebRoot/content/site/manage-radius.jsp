<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
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
<script type="text/javascript" src="../manager/js/radius.js"></script>
<script type="text/javascript" language="javascript"
	src="../manager/js/WebCalendar.js"></script>

<style type="text/css">
#serchTest,#addRadius,#updateradius,#ff,#addUser,#adduser,#updateuser,#toolbar{
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
						<table align="right">
							<tr>
								<td>
									<a href="javascript:getSelect()"><div style="padding-top: 5px;"></div>&nbsp;<span class="icon-add">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;修改</span>&nbsp;&nbsp;</a><div class="datagrid-btn-separator"></div>
								</td>
							</tr>
						</table>
					</td>
			</tr>
		</table>
	</div>
  
  
  <div  data-options="region:'center',split:true"> 
    <table style="float:left;" id="tt">
    </table>
    
    <div id="updateradius" class="easyui-window" title="检索范围修改"
		style="overflow:hidden;background:#E2EDFF;width:245px;height:220px;top:100px;" iconCls="icon-save"
		closed="true" maximizable="false"
		data-options="modal:true,closed:true" minimizable="false"
		collapsible="false">
		<div id="update"></div>
		<div>&nbsp;</div>
		<div align="center">
			<a class="easyui-linkbutton"  style="position:relative;top:-15px;top:-23px\9;top:-13px\9\0;" iconCls="icon-ok"
				href="javascript:void(0)" onClick="update()">修改</a> <a
				class="easyui-linkbutton"  style="position:relative;top:-15px;top:-23px\9;top:-13px\9\0;" iconCls="icon-cancel"
				href="javascript:void(0)" onClick="close1()">取消</a>
		</div>
	</div>
             
    
    <!-- 编辑 -->
    
          
           
          
     <form id="addRadius" method="post">
		<div style="margin:10px 10px 10px 10px; border:1px solid #91ABD1;border-radius:8px;">
		<div style="line-height: 25px; margin-top: 5px; margin-left: 25px;">
		<span>检索范围大小(单位：公里):</span>
		</div>
		<div style="line-height: 20px;margin-left: 25px; margin-bottom:10px;">
		<input size=25  type="text" id="radiusContent" name="radiusContent" class="ww" ></input>
		</div>
		</div>
		<div style="margin:10px 10px 10px 10px; border:1px solid #91ABD1;border-radius:8px;">
		<div style="line-height: 25px; margin-top: 5px; margin-left: 25px;">
		<span>城市名称:</span>
		</div>
		<div style="line-height: 20px;margin-left: 25px; margin-bottom:10px;">
		<input size=25  type="text" id="centerName" name="centerName" class="ww" ></input>
		</div>
		</div>
	</form>       
            
            
    
    </div> 
    
    
    
   
  </body>
</html>
