<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>广告管理</title>
    <jsp:include page="testCookie.jsp" flush="true" />
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>manager/css/default.css">
<link rel="stylesheet" type="text/css" href="<%=basePath%>manager/themes/icon.css">
<link rel="stylesheet" type="text/css" href="<%=basePath%>manager/css/demo.css">
<link id="easyuiTheme" rel="stylesheet"href="<%=basePath%>manager/themes/default/easyui.css" type="text/css">
<script type="text/javascript" src="<%=basePath%>manager/js/jquery-1.8.0.min.js"></script>
<link href="<%=basePath%>manager/js/kindeditor/themes/default/default.css"/>
<script src="<%=basePath%>manager/js/kindeditor/kindeditor.js" type="text/javascript"></script>
<script src="<%=basePath%>manager/js/kindeditor/kindeditor/lang/zh_CN.js" type="text/javascript"></script>
<script type="text/javascript" src="<%=basePath%>manager/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=basePath%>manager/js/jquery.cookie.js"></script>
<script type="text/javascript" src="<%=basePath%>manager/js/hd.js"></script>
<script type="text/javascript" src="<%=basePath%>manager/js/jquery.form.js"></script>
<script type="text/javascript" src="<%=basePath%>manager/js/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript" src="<%=basePath%>manager/js/WebCalendar.js"></script>
<script type="text/javascript" src="<%=basePath%>manager/js/ads.js"></script>
	<script type="text/javascript" src="<%=basePath%>manager/js/ajaxfileupload.js"></script>
  
<style type="text/css">


#ad,#ads{

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
    
  .shuangji{
   background:#E2EDFF; 
   BORDER:1px solid #91ABD1;
   }
</style>

  </head>
  
  <body style="background-color:white;padding:0px;">
   <table id="ads">
	</table>

<div id="toolbar" class="test">

		<table width="100%">
			<tr>
				<%--<td><form id="query"><table align="left">
						<tr>
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
						</tr>
					</table></form>
				</td>
				--%><td><table align="right">
						<tr>
						<td><a href="javascript:upload()"><div
										style="padding-top: 5px;"></div>&nbsp;<span class="icon-add">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;上传banner</span>&nbsp;&nbsp;</a>
							</td>
							<td><a href="javascript:AddPro()"><div
										style="padding-top: 5px;"></div>&nbsp;<span class="icon-add">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;添加</span>&nbsp;&nbsp;</a>
							</td>
							<td><a href="javascript:UpdatePro()"><div
										style="padding-top: 5px;"></div>&nbsp;<span class="icon-edit">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;修改</span>&nbsp;&nbsp;</a>
							</td>

							<td><a href="javascript:DeletePro()"><div
										style="padding-top: 5px;"></div>&nbsp;<span
									class="icon-remove">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;删除</span>&nbsp;&nbsp;</a>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</div>
	
		<div id="ad" scroll="no" class="easyui-window" title="banner上传" style="background:#E2EDFF;hidden;width:300px;height:320px;left:380px;top:20px;[;height:140px;];*height:140px;height:120px\9\0;"
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
