<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.handany.bm.model.*"%>
<%@page import="java.lang.String"%>
<%@page import="com.github.pagehelper.PageInfo"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en" class="no-js">
<!--<![endif]-->
<!-- BEGIN HEAD -->
<head>
<meta charset="utf-8" />

<!-- 为了让 IE 浏览器运行最新的渲染模式下，建议将此 <meta> 标签加入到你的页面中：-->
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<!--将下面的 <meta> 标签加入到页面中，可以让部分国产浏览器默认采用高速模式渲染页面：-->
<meta name="renderer" content="webkit">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->

<meta content="" name="description" />
<meta content="" name="author" />

<title>采供网</title>
<!-- BEGIN GLOBAL MANDATORY STYLES -->
<%@include file="/common.jsp"%>

<link rel="stylesheet" href="<%=basePath%>/css/mall.css">
<script src="<%=basePath%>js/mallDialog.js"></script>
<script src="<%=basePath%>js/num-format.js"></script>
<script src="<%=basePath%>js/xcConfirm.js"></script>
<link href="<%=basePath%>css/xcConfirm.css" rel="stylesheet">
<script src="<%=basePath%>js/bootstrap-datepicker.js"></script>
<link rel="stylesheet" href="<%=basePath%>/css/datepicker.css">
<style>
.order-status {
	border-radius: 4px;
	color: white;
	padding: 5px;
	font-size: 14px;
}

.order-status:hover {
	color: white;
}

.order-status-1 {
	background-color: #d58512;
	/*border: 1px solid #777777;*/
}

.order-status-2 {
	background-color: #c12e2a;
	/*border: 1px solid #777777;*/
}

.order-status-3 {
	background-color: #1c9439;
	/*border: 1px solid #777777;*/
}

.order-status-4 {
	background-color: #66afe9;
	/*border: 1px solid #777777;*/
}
</style>

<script type="text/javascript">
$(function(){
	initTime();
})
	function toPage(start) {
		if ($("#queryBtn").hasClass("load")) {
			return;
		}
		$("#queryBtn").addClass("load").html("loading...");
		if (start == undefined || start == null) {
			start = 0;
		}
		var beginTime = $("#time_begin").val();
		var endTime = $("#time_end").val();
		if(beginTime&&endTime){
			if(!dateCompare(beginTime,endTime)) {
				showDialog("结束日期不能小于开始日期", "toast", "", "");
				_this.removeClass("load").html("查询");
				return;
			}
		}
	
		
		sendRequest(
				{
					beginTime:beginTime,
					endTime:endTime,
					status : $("#status").val(),
					start : start
				},
				"/bm/proxy/getProxyOrderCount.do",
				function(json) {
					var list = json.data.list;
					$("#data_content").empty();
					var printBtnHtml = "";
					for (var i = 0; i < list.length; i++) {
						var item = list[i];
						$("#data_content").append(
								[
										"    <tr>           ",
										"			        <td align=\"center\">"
												+ item.shopId + "</td>",
										"			        <td align=\"center\">"
												+ item.shopName + "</td>",
										"			        <td align=\"center\">"
												+ item.orderNum+"</td>",
										"			        <td align=\"center\">"
												+ NumberFormat.formatNumber(item.orderPriceSum,1)+"</td>",
										"			      </tr>" ].join(""));
					}
					$("#navArea").html(showNav(json.data));
					$("#queryBtn").removeClass("load").html("查询");
				}, function(json) {
					showDialog(json.header.message, "toast", "", "");
					$("#queryBtn").removeClass("load").html("查询");
				});
	}
	//时间初始化
	function initTime() {
		$('#time_begin').datepicker({
			format : 'yyyy-mm-dd'
		}).on('changeDate', function(ev) {
			$('#time_begin').datepicker('hide');
		});
		$('#time_end').datepicker({
			format : 'yyyy-mm-dd'
		}).on('changeDate', function(ev) {
			$('#time_end').datepicker('hide');
		});
	}
/* 
	function toStatus(status) {
		var txt = "";
		if (status == "1") {
			txt = "待付款";
		} else if (status == "2") {
			txt = "待发货";
		} else if (status == "3") {
			txt = "待收货";
		} else if (status == "4") {
			txt = "已收货";
		} else if (status == "5") {
			txt = "平台已划款";
		} else if (status == "6") {
			txt = "申诉中";
		} else if (status == "7") {
			txt = "申诉处理完成";
		}
		return txt;
	} */


/* 	function showPrintDeliveryList(orderId) {
		window.location = toServerPageUrl("/bm/order/toPrintDeliveryList.do?orderId="
				+ orderId);
	}

	function showOrderDetail(orderId) {

		window.location = toServerPageUrl("/bm/order/querySellerOrderDetail.do?orderId="
				+ orderId);

	} */
</script>

</head>
<body>


	<div class="container" style="width: 100%;">

		<div class="row" style="border: 0; margin-top: -10px;">
			<div class="col-sm-12">
				<h3 class="zs-iframe-title">订单统计</h3>
			</div>
		</div>
		<div class="row" style="margin-top: 10px;">
			<div class="col-sm-2 col-md-2 ">
				<label style="padding-top: 5px; float: right">下单时间</label>
			</div>
			<div class="col-sm-2 col-md-2">
				<input type="text" id="time_begin" class="form-control" value=''
					style="float: left" readonly="">
			</div>
			<div class="col-sm-2 col-md-2">
				<input type="text" id="time_end" class="form-control" value=''
					style="float: left" readonly="">
			</div>
		</div>
		<div class="row" style="margin-top: 10px;">
			<div class="col-sm-2" style="padding-left: 0px; text-align: right;">
				<label for="status" style="text-align: right; padding-top: 7px;">订单状态</label>
			</div>
			<div class=" col-sm-3" style="">

				<select class="form-control zs-select-input" name="status"
					id='status'>
					<option value='-1'>所有状态</option>
					<option value='1'>待付款</option>
					<option value='2'>待发货</option>
					<option value='3'>待收货</option>
					<option value='4'>买家已收货</option>
					<option value='5'>平台已划款</option>
					<option value='6'>申诉中订单</option>
					<option value='7'>申诉处理完成订单</option>
				</select>
			</div>
			<div class=" col-sm-2" align="left">
				<button type="button" class="btn btn-default zs-btn-default"
					id="queryBtn" onclick="toPage(0)">查询</button>
			</div>

		</div>

	</div>

	<div style="margin-top: 10px;">
		<div class="col-sm-12">
			<table class="table table-hover">
				<thead>
					<tr>
						<th width="110">商铺编号</th>
						<th width="110">商铺名称</th>
						<th width="110">订单数量</th>
						<th width="110">订单总价</th>
					</tr>
				</thead>
				<tbody id="data_content">


				</tbody>
			</table>
		</div>
		<div class="col-sm-12" id="navArea">
			<nav class="data-nav">
				<ul class="pagination">
					<li class="disabled"><a href="#" aria-label="Previous"> <span
							aria-hidden="true">上一页</span>

					</a></li>

					<li class="disabled"><a href="#" aria-label="Next"> <span
							aria-hidden="true">下一页</span>
					</a></li>
				</ul>
			</nav>

		</div>
	</div>
	<script>
		
	</script>

</body>