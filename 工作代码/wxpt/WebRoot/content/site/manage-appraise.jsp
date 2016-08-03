<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
 <base href="<%=basePath%>">
 <jsp:include page="testCookie.jsp" flush="true" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 <meta http-equiv="X-UA-Compatible" content="IE=8,9,10" >
<link rel="stylesheet" type="text/css" href="<%=basePath%>manager/css/default.css">
<link rel="stylesheet" type="text/css" href="<%=basePath%>manager/themes/icon.css">
<link rel="stylesheet" type="text/css" href="<%=basePath%>manager/css/demo.css">
<link id="easyuiTheme" rel="stylesheet"
	href="<%=basePath%>manager/themes/default/easyui.css" type="text/css">
<script type="text/javascript" src="<%=basePath%>manager/js/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="<%=basePath%>manager/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=basePath%>manager/js/jquery.cookie.js"></script>
<script type="text/javascript" src="<%=basePath%>manager/js/hd.js"></script>
<script type="text/javascript" src="<%=basePath%>manager/js/jquery.form.js"></script>
<script type="text/javascript" src="<%=basePath%>manager/js/appraise.js"></script>
<script type="text/javascript" language="javascript"
	src="<%=basePath%>manager/js/WebCalendar.js"></script>

<style type="text/css">
#serchTest,#edit,#add,#ff,#addUser,#adduser,#updateuser,#toolbar{
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
<style type="text/css">
#serchTest,#edit,#add,#ff,#addCheckForm,#updatequestion,#addcheckdiv,#toolbar,#huiyuanxingqing {
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
    .ww{border:1px solid #26A0DA}
</style>

</head>
<body style="background-color:white;padding:0px;">

	<table style="float:left;" id="tt">
	</table>

	<div id="toolbar" class="test">

		<table width="100%">
			<tr>
				<td>
				</td>
				<td><table align="right">
						<tr>
						 <!-- <td><a href="javascript:AddUser()"><div
										style="padding-top: 5px;"></div>&nbsp;<span class="icon-add">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>添加&nbsp;&nbsp;</a>
							</td>  -->
							
							<!-- <td><a href="javascript:UpdateUser()"><div
										style="padding-top: 5px;"></div>&nbsp;<span class="icon-edit">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;修改</span>&nbsp;&nbsp;</a>
							</td> -->
							 <td><a href="javascript:Delete()"><div
										style="padding-top: 5px;"></div>&nbsp;<span class="icon-remove">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;删除</span>&nbsp;&nbsp;</a>
							</td> 

							
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</div>

	<!--双击显示div  -->
 <div id="app" class="easyui-window" scroll="no"
		title="商品评价及回复"
		style="background:#E2EDFF;width:800px;height:330px;[;height:340px;] top:20px;visibility:hidden;"
		iconCls="icon-edit" data-options="modal:true,closed:true"
		closed="true" maximizable="false" minimizable="false"
		collapsible="false">



<!-- [;height:490px;];*height:490px;*top:40;height:470px\9\0;visibility:hidden;
	<div id="huiyuanxingqing" scroll="no" class="easyui-window" title="会员详情及留言回复" style="background:#E2EDFF;width:800px;height:500px;top:20px;"
    iconCls="icon-add" closed="true" data-options="modal:true,closed:true"
     maximizable="false" minimizable="false" collapsible="false"> -->
	   
	   <div style="border:1px solid #7CB7DD;border-radius:8px;width:760px;margin:10px auto;*position:relative;*left:10px; ">
	     <table class="kehustable"  cellpadding="0" cellspacing="0" style="*position:relative;*left:4%;">
	     <input type="hidden" id="huiyuanid" name="huyuanid" />
	     <tr>
	     	<td class="beijing">
             产品名称：
	        </td>
	        <td>
	        <input type="text" id="productName" name="productName" readonly="true" class="kehus" />
	        </td>
	        
	        	<td class="beijing">
             产品价格：
	        </td>
	        <td>
	        <input type="text" id="price" name="price" readonly="true" class="kehus"  />
	        </td>
	     </tr>
	     
	      <tr>
	     	<td class="beijing">
             会员：
	        </td>
	        <td>
	        <input type="text" id="member" name="member" readonly="true" class="kehus"  />
	        </td>
	        
	        	<td class="beijing">
              电话：
	        </td>
	        <td>
	        <input type="text" id="phone" name="phone" readonly="true" class="kehus"  />
	        </td>
	     </tr>
	     
	     <tr>
	     	<td class="beijing">
            地址：
	        </td>
	        <td>
	        <input type="text" id="address" name="address" readonly="true" class="kehus"  />
	        </td>
	        
	        	
	     </tr>
	     </table>
	     </div>
	     
	     
	     <!--留言表  -->
	     <div  style="border:1px solid #7CB7DD;border-radius:8px;width:760px;height:180px;margin:10px auto;overflow-y:scroll;*position:relative;*left:10px;  ">
	         
	         
	         <div id="liuyanban"></div>
	         <!--  <table border="1"><tr><td>dddd</td></tr></table> -->
	       <!--留言表  -->
	     </div>
	     
     </div>
<!--双击显示div  end  -->
	


</body>
</html>