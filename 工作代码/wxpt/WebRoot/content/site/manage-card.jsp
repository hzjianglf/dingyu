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
<script type="text/javascript" src="../manager/js/card.js"></script>


<style type="text/css">
#serchTest,#edit,#editPrize,#add,#ff,#ffPrize,#lan,#addExplicit,#updateQuestion,#add1,#ff1,#addexplicit,#updateexplicit {
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
<body style="background-color:white;padding:0px;">

	<table style="float:left;" id="tt">
	</table>

	<div id="toolbar" class="test">

		<table width="100%">
			<tr>
				<td></td>
				<td><table align="right">
						<tr>
							<td><a href="javascript:getSelect()"><div
										style="padding-top: 5px;"></div>&nbsp;<span class="icon-add">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;添加修改</span>&nbsp;&nbsp;</a>
							</td>
						
							 <td><a href="javascript:SetPrizeChance()"><div
										style="padding-top: 5px;"></div>&nbsp;<span class="icon-add">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;设定中奖人数</span>&nbsp;&nbsp;</a>
							</td> 
						</tr>
					</table></td>
			</tr>
		</table>
	</div>
	<form id="ff" method="post">
		<div class="xx21">
			<table
				style="margin-top: 5px; margin-bottom: 5px; margin-left: 10px;[;margin-left: 25px;]; *margin-left:60px;margin-left: 20px\9\0;">
				
							<!-- <input type="hidden" id="cardName"	name="cardName" /> -->
				
				<tr style="line-height: 20px;">
					<td>
						<span style="*margin-left: 15px;">选择图片：</span>
							<input type="file" id="cardImage"	style="width: 120px;[;width: 150px;];*width: 180px;width: 170px\9\0; *margin-left: 15px;"  name="cardImage" />
					</td>
				</tr>
				<tr style="line-height: 20px;">
					<td>
						<span style="font-size: 12px; color: red;*margin-left: 15px;">图片尺寸必须为：375*268</span>
					</td>
				</tr>
				<tr style="line-height: 20px;">
					<td>
						<span style="*margin-left: 15px;">图片类型：</span>
							<select type="text" id="cardType"	name="cardType" />
							<option value="1">一等奖</option>
							<option value="2">二等奖</option>
							<option value="3">三等奖</option>
							<option value="4">四等奖</option>
							<option value="5">五等奖</option>
							<option value="6">六等奖</option>
							<option value="0">未中奖</option>
							<option value="-1">重复刮奖</option>
							<option value="-2">其他1</option>
							<option value="-3">其他2</option>
					</td>
				</tr>
			</table>
		</div>
		
	</form>
	
	<!-- --中奖概率设置开始 -->
	<div id="editPrize" class="easyui-window" title="刮刮乐中奖人数设置"
		style="overflow:hidden; background:#E2EDFF;width:250px;height:390px;[;height:410px;];*height:378px;height:385px\9\0;"
		iconCls="icon-add" data-options="modal:true,closed:true"
		maximizable="false" closed="true" maximizable="false"
		minimizable="false" collapsible="false">
		<div id="eePrize">
		<form id="ffPrize" method="post">
		<div class="xx21">
			<table
				style="margin-top: 5px; margin-bottom: 5px; margin-left: 10px;">
				<tr style="line-height: 20px;">
					<td>
						<span style="*margin-left: 15px;">	一等奖<input type="text" id="onePrizeCount" class="ww"	name="onePrizeCount" onblur="countNum()"/>人</span>
					</td>
				</tr>
				<tr style="line-height: 20px;">
					<td>
						<span style="*margin-left: 15px;">	二等奖<input type="text" id="twoPrizeCount"  class="ww"	name="twoPrizeCount" onblur="countNum()"/>人</span>
					</td>
				</tr>
				<tr style="line-height: 20px;">
					<td>
						<span style="*margin-left: 15px;">	三等奖<input type="text" id="threePrizeCount"	  class="ww" name="threePrizeCount" onblur="countNum()"/>人</span>
					</td>
				</tr>
				<tr style="line-height: 20px;">
					<td>
						<span style="*margin-left: 15px;">	四等奖<input type="text" id="fourPrizeCount"	 class="ww" name="fourPrizeCount" onblur="countNum()"/>人</span>
					</td>
				</tr>
				<tr style="line-height: 20px;">
					<td>
						<span style="*margin-left: 15px;">	五等奖<input type="text" id="fivePrizeCount"	 class="ww" name="fivePrizeCount" onblur="countNum()"/>人</span>
					</td>
				</tr>
				<tr style="line-height: 20px;">
					<td>
						<span style="*margin-left: 15px;">	六等奖<input type="text" id="sixPrizeCount"	 class="ww" name="sixPrizeCount" onblur="countNum()"/>人</span>
					</td>
				</tr>
				<tr style="line-height: 20px;">
					<td>
						<span style="*margin-left: 15px;">	七等奖<input type="text" id="sevenPrizeCount"  class="ww"	name="sevenPrizeCount" onblur="countNum()"/>人</span>
					</td>
				</tr>
				<tr style="line-height: 20px;">
					<td>
						<span style="*margin-left: 15px;">	八等奖<input type="text" id="eightPrizeCount"  class="ww"	name="eightPrizeCount" onblur="countNum()"/>人</span>
					</td>
				</tr>
				<tr style="line-height: 20px;">
					<td>
						<span style="*margin-left: 15px;">	九等奖<input type="text" id="ninePrizeCount"	 class="ww" name="ninePrizeCount" onblur="countNum()"/>人</span>
					</td>
				</tr>
				<tr style="line-height: 20px;">
					<td>
						<span style="*margin-left: 15px;">	十等奖<input type="text" id="tenPrizeCount"	 class="ww" name="tenPrizeCount" onblur="countNum()"/>人</span>
					</td>
				</tr>
				<tr style="line-height: 20px;">
					<td>
						<span style="*margin-left: 15px;">	总计中奖人数为<input type="text" id="prizeCountNum"	 class="ww" name="prizeCountNum" disabled="disabled" readonly="readonly" style="margin-left: 37px;*margin-left: 49px;"/></span>
					</td>
				</tr>
			</table>
		</div>
		
	</form>
		</div>
		<div>&nbsp;</div>
		<div align="center">
			<a class="easyui-linkbutton" iconCls="icon-ok"
				href="javascript:void(0)"
				style="position:relative;top:-15px;*top:-23px;"
				onClick="setChance()">确定</a> <a class="easyui-linkbutton"
				iconCls="icon-cancel" href="javascript:void(0)"
				style="position:relative;top:-15px;*top:-23px;" onClick="closePrizeChance()">取消</a>
		</div>
	</div>
	
	
	<!-- 中奖概率设置结束 -->
	
	
	<div  id="edit" class="easyui-window" title="刮刮乐图片上传"
	style="overflow:hidden;background:#E2EDFF;width:250px;height:180px;[;height:200px;];*height:186px;height:194px\9\0;"
	iconCls="icon-add" data-options="modal:true,closed:true"
		maximizable="false" closed="true" maximizable="false"
		minimizable="false" collapsible="false">
		<div id="ee"></div>
		<div>&nbsp;</div>
		<div align="center">
			<a class="easyui-linkbutton" iconCls="icon-ok"
				href="javascript:void(0)"
				style="position:relative;top:-15px;*top:-23px;"
				onClick="add()">添加修改</a> <a class="easyui-linkbutton"
				iconCls="icon-cancel" href="javascript:void(0)"
				style="position:relative;top:-15px;*top:-23px;" onClick="close1()">取消</a>
		</div>
	</div>
	${result}
</body>
</html>