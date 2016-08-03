<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  <jsp:include page="testCookie.jsp" flush="true" />
    <base href="<%=basePath%>"> 
    <title>商品订单管理</title>
    
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
	src="<%=basePath%>manager/js/ordermanage.js"></script>
  <link href="<%=basePath%>manager/js/kindeditor/themes/default/default.css"/>
<script src="<%=basePath%>manager/js/kindeditor/kindeditor.js" type="text/javascript"></script>
<script src="<%=basePath%>manager/js/kindeditor/kindeditor/lang/zh_CN.js" type="text/javascript"></script>
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
    
    
    
        
    #orderUI,#toolbar{

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
					<tr>
							<td>
							<span style="color:#3967a3 ">用户名:</span><input type="text" name="proName" id="proName" value="" class="ww">
							</td>
							<td>
							<span style="color:#3967a3 ">订单编号:</span><input type="text" name="proNum" id="proNum" value=""class="ww">
							</td>
							<!-- <td>
							<span style="color:#3967a3 ">上架时间:</span><input type="text" name="proTime" id="proTime" value=""onclick="SelectDate(this,'yyyy/MM/dd');" class="ww">
							</td> -->
							<td align="left"><a href="javascript:query()"><div style="padding-top: 5px;"></div>&nbsp;<span
												class="icon-search">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;查询</span>&nbsp;&nbsp;</a>
							</td>
						</tr> 
					</table></form>
				</td>
				<td><table align="right">
						<tr>
							<td><a href="javascript:lookOder()"><div
										style="padding-top: 5px;"></div>&nbsp;<span class="icon-add">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;查看订单信息</span>&nbsp;&nbsp;</a>
							</td>
									<td><a href="javascript: sendproduct();"><div
										style="padding-top: 5px;"></div>&nbsp;<span class="icon-add">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;商家发货</span>&nbsp;&nbsp;</a>
							</td>
	<!--  <td><a href="javascript:updateUI()"><div
										style="padding-top: 5px;"></div>&nbsp;<span class="icon-edit">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;取消发货</span>&nbsp;&nbsp;</a>
							</td> -->

							
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</div>

	
	<div id="orderUI" class="easyui-window" title="商品订单管理"
		style="background:#E2EDFF;hidden;width:700px;height:550px;left:300px;top:20px;[;height:550px;];*height:550px;height:550px; "
		iconCls="icon-edit" closed="true"
		data-options="modal:true,closed:true" maximizable="false"
		minimizable="false" collapsible="false" draggable="true">
		<div id="xx" class="xx21" >
		<table border="0" style="width: 560px;" align="center">
		<tr>
		
		<td >
		类别名称:
		</td>
	
		<td style="width: 100px;">
		<span id="productName" style="width: 100px;"></span>
		</td>
			<td width="100px;"></td>
		<td >
		商品订单:
		</td>
		<td style="width: 150px;"><span id="product_num"  ></span></td>
		</tr>
<!-- 2 -->
		<tr>
		<td>
		下单日期:
		</td>
		<td style="width: 150px;">
		<span id="order_time"  ></span>	
		</td>
		<td width="100px;">
		</td>
		<td>支付金额:</td>
		<td style="width: 150px;">
		<span id="pay_money" ></span>
		</td>

		</tr>
		<!-- 3 -->
		
		<tr>
		<td>
		支付日期:
		</td>
		<td style="width: 150px;">
		<span id="pay_time"  ></span>	
		</td>
		<td width="110px;">
		</td>
		<td>支付状态:</td>
		<td style="width: 150px;">
		<span id="pay_type" ></span>
		</td>

		</tr>
		
		
		<!-- 4 -->
			<tr>
		<td>
		收货人:
		</td>
		<td style="width: 150px;">
		<span id="receive_person"  ></span>	
		</td>
		<td width="110px;">
		</td>
			<td>
	订单状态:
		</td>
		<td style="width: 150px;">
		<span id="order_type"  ></span>	
		</td>

		</tr>
		
		
		<!-- 5 -->
			<tr>
		<td>
		收货状态
		</td>
		<td style="width: 150px;">
		<span id="takeType"  ></span>	
		</td>
		<td width="110px;">
		</td>
		<td>收货地址:</td>
		<td style="width: 150px;">
		<span id="receive_address" ></span>
		</td>

		</tr>
		<!-- 6 -->
			<tr>
		<td>
	收货人电话:
		</td>
		<td style="width: 150px;">
		<span id="receive_phone"  ></span>	
		</td>
		<td width="110px;">
		</td>
		<td>发货状态:</td>
		<td style="width: 150px;">
		<span id="send_type" ></span>
		</td>

		</tr>
		
			<!-- 7 -->
			<tr>
		<td>
	物流方式:
		</td>
		<td style="width: 150px;">
		<span id="Logistics_name"  ></span>	
		</td>
		<td width="110px;">
		</td>
		<td>会员名称:</td>
		<td style="width: 150px;">
		<span id="memberName" ></span>
		</td>

		</tr>
		<tr>
		<td >
		发货时间
		</td>
		<td >
		<span id="sendTime" style="width: 150px;"></span>
		</td>
		</tr>

		<tr> 
					<td align="right">
					订单备注：
					</td>
					<td colspan="4" width="400px;">
					<!-- <textarea disabled="disabled" class="shuangji" cols=70 rows=4 id="Details2" name="Details2" style="overflow: auto;width:374px"></textarea> -->
					<div id="pro"></div>
					</td>
				</tr>
				
				<tr> 
					<td align="right">
					卖家留言：
					</td>
					<td colspan="4" width="400px;">
						<div id="pro1"></div>
					<!-- <textarea disabled="disabled" class="shuangji" cols=70 rows=4 id="order_leave" name="order_leave" style="overflow: auto;width:374px"></textarea> -->
					</td>
				</tr>
		</table>
		
		</div>
		
	</div>
	

	

  </body>
</html>
