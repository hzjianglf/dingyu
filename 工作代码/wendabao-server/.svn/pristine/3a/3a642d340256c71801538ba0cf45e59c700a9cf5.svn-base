<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@page import="java.lang.String"%>
<%
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath() + "/";
%>
<!DOCTYPE html>
<html lang="zh-CN">

<head>
<%@include file="/common.jsp"%>
<link rel="stylesheet" href="<%=basePath%>/css/mall.css">
<script src="<%=basePath%>js/mallDialog.js"></script>
<script src="<%=basePath%>js/xcConfirm.js"></script>
<script src="<%=basePath %>js/jquery.PrintArea.js" type="text/javascript"></script>
<link href="<%=basePath%>css/xcConfirm.css" rel="stylesheet">

<title>订单详情</title>
	<style type="text/css">
		thead{background-color: transparent}
		table,thead,th,td,.table>thead>tr>th{border:1px solid #333333;}
	</style>
</head>

<body>
<div style="display: block; width: 100%;" class="container">
	<div class="row" style="border: 0; margin-top: -10px;">
		<div class="col-sm-12">
			<h3 class="zs-iframe-title">打印发货单</h3>
		</div>
	</div>
</div>
<div style="margin-top:10px;" class="container" id="printArea">
	<style type="text/css">

	</style>
	<div class="row" style="border: 0;text-align: center">
		<h4>餐饮彩购网订单明细表</h4>
	</div>
	<div class="row" style="border: 0;margin-top:20px;">
		<div class="col-xs-4"><label>收货人名称：</label><span id="receiver"></span></div>
		<div class="col-xs-4"><label>电话：</label><span id="receiverPhone"></span></div>
		<div class="col-xs-4"><label>订单编号：</label><span id="orderNo"></span></div>
	</div>
	<div class="row" style="margin-top:10px;">
		<div class="col-sm-12">
			<table class="table table-hover" id="itemsTable" style="border: solid 1px;">
				<thead>
				<tr>
					<th width="30">序号</th>
					<th width="110">商品名称</th>
					<th width="110">规格</th>
					<th width="110">单价</th>
					<th width="110">数量</th>
					<th width="110">金额</th>
				</tr>
				</thead>
				<tbody></tbody>
			</table>
		</div>

	</div>
	<div class="row" style="border: 0;margin-top:20px;">
		<div class="col-xs-12"><label>买家留言：</label><span id="buyerRemark"></span></div>
	</div>
	<div class="row" style="border: 0;margin-top:20px;">
		<div class="col-xs-5"><label>卖家名称：</label><span id="shopName"></span></div>
		<div class="col-xs-4"><label>电话：</label><span id="shopPhone"></span></div>
	</div>
	<div class="row" style="border: 0;margin-top:20px;">
		<div class="col-xs-12" style="text-align: right;"><span id="time"></span></div>
	</div>
</div>

<div style="margin-top:10px;" class="container" >
	<a style="float: right;margin-right: 10px;" class="btn btn-default zs-btn-green" id="print" onclick="print()">打印</a>
</div>


<script type="text/javascript">

	var orderId = getUrlParam("orderId");
	getOrderDetail();

	function getOrderDetail(){
		sendRequest({"orderId":orderId},"/bm/order/querySellerOrderDetail.do",function(json){
			var data = json.data;
			var items = data.items;
			var deliverType = data.deliverType;
			var receiver = data.userName;
			var receiverPhone = "";
			if("2" == deliverType){
				receiver = data.receiver;
				receiverPhone = data.receiverPhone;
			}
			$("#orderNo").text(data.id);
			$("#receiver").text(receiver);
			$("#receiverPhone").text(receiverPhone);
			$("#buyerRemark").text(data.remark);
			$("#shopName").text(data.shopName);
			$("#buyerRemark").text(data.remark);

			// 商品列表
			$(items).each(function(i,ite){
				var html =
						"<tr>"+
						"	<td align='center'>"+(i+1)+"</td>"+
						"	<td>"+ite.productName+"</td>"+
						"	<td>"+ite.productTypeName+"</td>"+
						"	<td align='right'>"+powAmount(ite.price,2)+"</td>"+
						"	<td align='right'>"+ite.number+"</td>"+
						"	<td align='right'>"+powAmount(ite.price *　ite.number,2)+"</td>"+
						"</tr>";
				$("#itemsTable>tbody").append(html);
			});

			// 合计
			var count =
					"<tr>"+
					"	<td colspan='6' align='right'><label>合计：</label>"+powAmount(data.total,2)
					+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  <label>实付：</label>"+powAmount(data.realTotal,2)+"</td>"+
					"</tr>";
			$("#itemsTable>tbody").append(count);
			$("#time").text(getDate());// 日期

		});
	}

	/**
	 * 打印
	 */
	function print(){
		$("#printArea").printArea(".table>thead>tr>th{border-bottom:1px solid #333333 !important;} " +
				"thead{background-color: transparent} " +
				"table,thead,th,td,.table>thead>tr>th{border:1px solid #333333;} " +
				"th,td,label,span{font-size: 10px;}");
	}

	function getDate(){
		var mydate = new Date();
		var str = "" + mydate.getFullYear() + "年";
		str += (mydate.getMonth()+1) + "月";
		str += mydate.getDate() + "日";
		return str;
	}
</script>
</body>
</html>