<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>商品类别管理</title>
    <jsp:include page="testCookie.jsp" flush="true" />
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>manager/css/default.css">
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>manager/themes/default/easyui.css" />
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>manager/themes/icon.css">
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>manager/css/demo.css">
<link id="easyuiTheme" rel="stylesheet"
	href="<%=basePath%>manager/themes/default/easyui.css"
	type="text/css">
<script type="text/javascript"
	src="<%=basePath%>manager/js/jquery-1.8.0.min.js"></script>
<script type="text/javascript"
	src="<%=basePath%>manager/js/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="<%=basePath%>manager/js/jquery.cookie.js"></script>
	<script type="text/javascript" src="<%=basePath%>manager/js/WebCalendar.js"></script>
<script type="text/javascript"
	src="<%=basePath%>manager/js/productType.js"></script>
  
<style type="text/css">

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
.xx21 {
	margin: 10px 10px 10px 10px;
	border: 1px solid #91ABD1;
	border-radius: 8px;
	
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

.kehus{
/* background:#FFFFFF; */

background:#E2EDFF;
border:none;

}

.kehustable{
   width:700px;
    border-style: dotted; 
    border-color: #CCCCCC; 
    border-width: 1px; 
     margin:10px auto;
   

  }
  
  * html .kehustable{
   width:700px;
    border-style: dotted; 
    border-color: #CCCCCC; 
    border-width: 1px; 
     margin:10px auto;
}

  *+html .kehustable{
   width:700px;
    border-style: dotted; 
    border-color: #CCCCCC; 
    border-width: 1px; 
     margin:10px auto;
}

  .kehustable td{ 
    border-style: dotted; 
    border-color: #CCCCCC; 
    border-width: 1px;}

.liuyanban{
    width:700px;
    border-style: dotted; 
    border-color: #CCCCCC; 
    border-width: 1px; 
    margin:10px auto;

  }
  
  .liuyanban td{ 
    border-style: dotted; 
    border-color: #CCCCCC; 
    border-width: 1px;}
    
    .beijing{
  background:#E5F0F4;
    
    }
    .ww{border:1px solid #26A0DA}
    
    #toolbar,#addUI,#addSmallUI,#updateSmallUI,#updateUI{

	visibility: hidden;

}
</style>

  </head>
  
  <body style="background-color:white;padding:0px;">
   <table id="product">
	</table>

<div id="toolbar" class="test">

		<table width="100%">
			<tr>
				<td><form id="query"><table align="left">
						<!-- <tr>
							<td>
							<span style="color:#3967a3 ">商品名称:</span><input type="text" name="proName" id="proName" value="" class="ww">
							</td>
							<td>
							<span style="color:#3967a3 ">商品编号:</span><input type="text" name="proNum" id="proNum" value=""class="ww">
							</td>
							<td>
							<span style="color:#3967a3 ">上架时间:</span><input type="text" name="proTime" id="proTime" value=""onclick="SelectDate(this,'yyyy/MM/dd');" class="ww">
							</td>
							<td align="left"><a href="javascript:query()"><div style="padding-top: 5px;"></div>&nbsp;<span
												class="icon-search">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;查询</span>&nbsp;&nbsp;</a>
							</td>
						</tr> -->
					</table></form>
				</td>
				<td><table align="right">
						<tr>
							<td><a href="javascript:AddUI()"><div
										style="padding-top: 5px;"></div>&nbsp;<span class="icon-add">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;添加大类别</span>&nbsp;&nbsp;</a>
							</td>
									<td><a href="javascript:AddSmallUI()"><div
										style="padding-top: 5px;"></div>&nbsp;<span class="icon-add">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;添加小类别</span>&nbsp;&nbsp;</a>
							</td>
							<td><a href="javascript:updateUI()"><div
										style="padding-top: 5px;"></div>&nbsp;<span class="icon-edit">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;修改</span>&nbsp;&nbsp;</a>
							</td>
							<!-- <td><a href="javascript:deleteUI()"><div
										style="padding-top: 5px;"></div>&nbsp;<span class="icon-edit">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;删除</span>&nbsp;&nbsp;</a>
							</td> -->

							
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</div>


<div id="addUI" class="easyui-window" title="添加大类别"
		style="width:265px;*width:280px;height:145px;[;height:150px;];overflow:hidden;background:#e0edff;top:0px;left: 0px;"
		iconCls="icon-edit" closed="true"
		data-options="modal:true,closed:true" maximizable="false"
		minimizable="false" collapsible="false" draggable="true">
		<div id="xx" class="xx21" >	
		<table border="0" style="margin-left: 10px;">
		<tr height="45px";>
		<td>大类别名称:</td>
		<td ><input type="text" id="addBigType" class="ww"/></td>
		</tr> 	
		</table>	
		</div>
		<div align="center">	   
			<a class="easyui-linkbutton" iconCls="icon-ok"	
				href="javascript:void(0)" onClick="AddBigType();" style="position:relative;top:5px;">保存</a> <a
				class="easyui-linkbutton" iconCls="icon-cancel"
				href="javascript:addclose()" style="position:relative;top:5px;">取消</a>		
		
		</div>
	</div>
	
	
	<div id="addSmallUI" class="easyui-window" title="添加小类别"
		style="padding: 0px;width:265px;*width:250px;width:230px\9\0;height:170px;[;height:168px;];*height:173px;height:171px\9\0;overflow:hidden;background:#e0edff;top:0px;*left:600px;left: 0px;"
		iconCls="icon-edit" closed="true"
		data-options="modal:true,closed:true" maximizable="false"
		minimizable="false" collapsible="false" draggable="true">
		<div id="xx" class="xx21" >
		<table border="0" style="margin: 10px;*height: 80px;height: 80px\9\0;">	
		<tr>		
		<td>
		类别名称:
		</td>
		<td>
		<select id="typeSelect" style="width: 100px;" class="ww">	
		</select>
		</td>		
		</tr>
		<tr>		
		<td>添加小类别:</td>		
		<td><input type="text" id="addSmallType" style="width: 100px;"class="ww"/>
		</td>	
		</tr>	
		</table>	
		</div>
		<div align="center">
		<a class="easyui-linkbutton" iconCls="icon-ok"			
				href="javascript:void(0)" onClick="AddSmallType();" style="position:relative;top:5px;">保存</a> <a
				class="easyui-linkbutton" iconCls="icon-cancel"				
				href="javascript:addBigclose()" style="position:relative;top:5px;">取消</a>
	
		</div>
	</div>
	
	<!-- 
	
获取id，进行修改页面 -->

	
		<!-- 修改类别的页面 -->
	<div id="updateSmallUI" class="easyui-window" title="修改类别名称"
		style="width:235px;*width:240px;height:160px;[;height:168px;];*height:182px;height:170px\9\0;overflow:hidden;background:#e0edff;top:0px;left: 0px;"
		iconCls="icon-edit" closed="true"
		data-options="modal:true,closed:true" maximizable="false"
		minimizable="false" collapsible="false" draggable="true">
		<div id="xx" class="xx21" >
		<input id="updateid"   type="hidden" >	
		<table border="0" style="margin: 10px;*height: 80px;height: 80px\9\0;">	
		<tr>
		<td>
		类别名称
		</td>
		<td>
		<select id="updatetypeSelect" onchange="changeSelect()" style="width: 100px;" class="ww">
		</select>
		</td>
		</tr>
		<tr>	
		<td>小类别名称:</td>	
		<td>
		<input type="text" id="updateSmallType" style="width: 100px;" class="ww">
		</td>
		</tr>	
		</table>	
		</div>
		<div align="center">
			<a class="easyui-linkbutton" iconCls="icon-ok"

				href="javascript:void(0)" onClick="updateMethodSmall();">保存</a> <a
				class="easyui-linkbutton" iconCls="icon-cancel" href="javascript:updateSmallUI()">取消</a>
		
		</div>
		
	</div>
	
	<div id="updateUI" class="easyui-window" title="修改类别名称"
		style="width:265px;*width:280px;height:145px;[;height:145px;];*height:155px;height:132px\9\0;overflow:hidden;background:#e0edff;top:200px;left: 0px;"
		iconCls="icon-edit" closed="true"
		data-options="modal:true,closed:true" maximizable="false"
		minimizable="false" collapsible="false" draggable="true">
		<div id="xx21" class="xx21" style="height:50px;">
		<table border="0" style="margin: 10px;*height: 40px;height: 30px\9\0;">	
		<tr>
		<td>大类别名称:</td>
		<td><input type="text" id="updateBigType1" class="ww"/></td>		
		</tr>	
		</table>	
		</div>
		<div align="center">
			<a class="easyui-linkbutton" iconCls="icon-ok"
				
				href="javascript:void(0)" onClick="updateMethod();">保存</a> <a
				class="easyui-linkbutton" iconCls="icon-cancel"
				
		href="javascript:updateUIClose()">取消</a>
		
		</div>
	</div>
  </body>
</html>
