<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@page import="com.handany.bm.model.*"%>
<%@page import="java.lang.String"%>
<%@page import="com.github.pagehelper.PageInfo"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%@page import="java.math.BigDecimal"%>
<%@page import="com.handany.base.common.Constants"%>
<%
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath() + "/";
	BmOrder order = (BmOrder) request.getAttribute("data");
	String picPath = Constants.IMAGE_SERVER;
%>
<!DOCTYPE html>
<html lang="zh-CN">

<head>
<%@include file="/common.jsp"%>
<link rel="stylesheet" href="<%=basePath%>/css/mall.css">
<script src="<%=basePath%>js/order/order.js"></script>
<script src="<%=basePath%>js/mallDialog.js"></script>
<script src="<%=basePath%>js/xcConfirm.js"></script>
<link href="<%=basePath%>css/xcConfirm.css" rel="stylesheet">
	<script type="text/javascript" src="<%=basePath%>js/admin/regionSearch.js"></script>
<title>订单详情</title>
</head>
<style type="text/css">
body {
	font: 400 16px/28px "microsoft yahei", Arial;
}

.logistics_wrap {
	background: #eee;
	box-shadow: 1px 1px 1px 1px #e2e2e4;
	padding: 10px 30px;
	color: #aaa;
}

li {
	display: list-item;
	margin: 0;
	padding: 0;
	font-weight: 400;
	list-style: none;
	font-style: normal;
	margin: 0;
}

#sta_ul {
	line-height: 40px;
	font-size: 18px;
}

.red_txt {
	color: #b83126
}

#info_ul {
	left: -1px;
	background-color: #fcf6e3;
	border: 1px solid #d5bc8b;
	margin-top: 16px;
}

.info_li {
	padding: 5px 0 5px 10px;
}

#info_hd {
	border-bottom: 1px solid #f4e2b6;
	position: relative;
	height: 34px;
	line-height: 34px;
	padding: 5px 0 5px 10px;
}

.wrap {
	width: 100%;
}


.time_li {
	color: #c49859;
}

.time {
	margin-right: 50px;
}

#list_ul[margin-top : 16px; ]
.rel {
	position: relative;
}

#list_hd {
	height: 40px;
	padding-left: 0;
	font-size: 16px;
	margin-bottom: 10px;
	line-height: 40px;
	box-shadow: 1px 1px 1px 1px #e2e2e4;
	background-color: #fff;
}

.c_txt {
	text-align: center;
}

td {
	text-align: center;
}

tr {
	border-top: 1px dashed #dadae0;
	box-shadow: 1px 1px 1px 1px #e2e2e4;
	background-color: #fff;
}

thead {
	box-shadow: 1px 1px 1px 1px #e2e2e4;
	background-color: #fff;
}
.old_price{
text-decoration: line-through;color: gray;
}
</style>

<script type="text/javascript">
	var deliverType1 = $("#deliverType1").val();
	/* 自取隐藏   */
	if (deliverType1 == "1") {
		$("#receiveMsg").hide();
		/* document.getElementById('container_div').style.marginTop = '360px'; */
	}

	/* 点击自取   */
	function receiveMsgHide() {
		$("#receiveMsg").hide();
		/* document.getElementById('container_div').style.marginTop = '360px'; */
	}
	/* 点击物流  */
	function addUserMsg() {
		$("#receiveMsg").show();
		/* document.getElementById('container_div').style.marginTop = '560px'; */
	}

	function updateOrder() {
		var deliverType = null;
		var delieverMethod = document.getElementsByName("shippingMethod");
		for (var i = 0; i < delieverMethod.length; i++) {
			if (delieverMethod[i].checked)
				var deliverType = delieverMethod[i].value;
		}
		var deliverProvider = $("#selectDeliever").find("option:selected")
				.val();
		var deliverProviderTxt = $("#selectDeliever").find("option:selected")
				.html();
		var deliverNumber = $("#deliverNumber").val();
		var receiver = $("#receiver").val();
		var receiverPhone = $("#receiverPhone").val();
		var addressTxt = $("#addressTxt").val();
		var orderId = $("#orderId").val();
		$.ajax({
			url : toServerUrl("/bm/order/updateOrder.do"),
			data : {
				orderId : orderId,
				receiver : receiver,
				receiverPhone : receiverPhone,
				addressTxt : addressTxt,
				deliverProvider : deliverProvider,
				deliverProviderTxt : deliverProviderTxt,
				deliverNumber : deliverNumber,
				deliverType : deliverType,
			},
			type : "post",
			dataType : "json",
			success : function(data) {
				if (data.header.success == true) {
					alert("保存成功！");

				}
			}

		});

	}

	function changeMsg() {

		$("#receiveMsg").show();
	}

	function updateMoney() {

		$("#updatePriceModal").modal("show");
	}

	function updateTotalPrice(orderId) {
		var realTotal = $("#updateTotalMoney").val();
		$.ajax({
			url : toServerUrl("/bm/order/updateOrder.do"),
			data : {
				orderId : orderId,
				realTotal : realTotal,
			},
			type : "post",
			dataType : "json",
			success : function(data) {
				if (data.header.success == true) {
					$("#totalPrice").val(realTotal);
					$("#updatePriceModal").modal("hide");
				}
			}

		});
	}

	function send() {
		var deliverType = null;
		var delieverMethod = document.getElementsByName("shippingMethod");
		for (var i = 0; i < delieverMethod.length; i++) {
			if (delieverMethod[i].checked)
				var deliverType = delieverMethod[i].value;
		}
		var orderId = $("#orderId").val();
		if (deliverType == null) {
			return;
		}
		$.ajax({
					url : toServerUrl("/bm/order/send.do"),
					data : {
						orderId : orderId,
						deliverType : deliverType,
					},
					type : "post",
					dataType : "json",
					success : function(data) {
						if (data.header.success == true) {
							window.location = toServerPageUrl("/bm/order/querySellerOrderDetail.do?orderId="
									+ orderId);

						}
					}

				});
	}
</script>


<body>
	
	<div id="o_detail_content" style="display: block; width: 100%;"
		class="container">
		<div class="row" style="border: 0; margin-top: -10px;">
			<div class="col-sm-12">
				<h3 class="zs-iframe-title">订单详情</h3>
			</div>
		</div>

		<ul id="sta_ul" class="wrap">
			<li id="sta_hd">当前订单状态： <span id="order_current_sta"
				class="red_txt">
				</span>
			</li>
			<!-- 		<li class="sta_li font14">订单备注： <span id="show_order_note">无sad
			</span> -->

		</ul>
		<ul id="info_ul" class="wrap rel">
			<li id="info_hd">订单信息 &nbsp;&nbsp;</li>
			<li class="info_li font14 ">&nbsp;&nbsp;&nbsp;订单编号：<span><%=order.getId()%></span>
			</li>
			<li class="info_li font14 ">&nbsp;&nbsp;&nbsp;下单账号：<span><%=order.getUserName()%></span></li>
			<li class="info_li font14">收货人信息：
				<span class="reciver"><%=order.getReceiver() == null ? "暂无" : order.getReceiver()%></span>&nbsp;&nbsp;&nbsp;&nbsp;
				<span class="phone"><%=order.getReceiverPhone() == null ? "" : order.getReceiverPhone()%></span><br>
				<div style="margin-left:80px;">
					<span class="province" >
						<%=order.getRegion1Txt() == null ? "" : order.getRegion1Txt()%></span>&nbsp;&nbsp;&nbsp;
					<span class="city"><%=order.getRegion2Txt() == null ? "" : order.getRegion2Txt()%></span>&nbsp;&nbsp;&nbsp;
					<span class="country"><%=order.getRegion3Txt() == null ? "" : order.getRegion3Txt()%></span>&nbsp;&nbsp;&nbsp;
					<br><span class="address"><%=order.getAddressTxt() == null ? "" : order.getAddressTxt()%></span>
				</div>
			</li>
			<li class="info_li font14">&nbsp;&nbsp;买家留言：<%=order.getRemark()==null?"暂无": order.getRemark()%></li>
			<li class="time_li font14">&nbsp;&nbsp; 
			<span class="time time_submit"> 下单时间： <span
					class="submit_time_span"><%=order.getCreateTime() == null ? "" : order.getCreateTime()%></span>
			</span>
			 <span class="time time_pay">付款时间： <span
					class="submit_time_span"><%=order.getPayTime() == null ? "" : order.getPayTime()%></span>
			</span> 
			</li>
			<li class="time_li font14">&nbsp;&nbsp; 
			<span class="time time_send">发货时间： <span
					class="submit_time_span"><%=order.getSendTime() == null ? "" : order.getSendTime()%></span>
			</span>
			 <span class="time time_receive">收货时间： <span
					class="time submit_time_span"><%=order.getRecieveTime() == null ? "" : order.getRecieveTime()%></span>
			</span> 
			
			</li>
			<li class="time_li font14 appeal">&nbsp;&nbsp; 
			<span class="time time_appeal">申诉时间：
					<span class="appeal_time_span"><%=order.getAppealTime()== null ? "":order.getAppealTime()%></span>
			</span>
			<span class="time time_appeal_commit">申诉处理完成时间：
					<span class="appeal_commit_time_span"><%=order.getAppealOverTime()== null ? "":order.getAppealOverTime()%></span>
			</span>
			</li>
			<li class="time_li font14 appeal_txt reason">&nbsp;&nbsp; 
				<span class="time reason_appeal">申诉理由：
					<span class="reason_appeal_span"><%=order.getAppealReason()== null ? "暂无":order.getAppealReason()%></span>
				</span>
			</li>
			<li class="time_li font14 appeal_txt result">&nbsp;&nbsp; 
				<span class="time result_appeal">处理结果：
					<span class="result_appeal_span"><%=order.getAppealResult()== null ? "暂无":order.getAppealResult()%></span>
				</span>
			</li>
		</ul>


		<div class="logistics_wrap">
				<ul>
				<li class="font14" id="shipmethod">
					发货方式：<span>
							<input id="log" name="method" value="2" type="radio"/>商家配送
							<input id="self" name="method" value="1" type="radio"/>自提
						</span>
				</li>
			<div class="method_log">
				<li id="log_company" class="font14 ">物流公司： <span><%=order.getDeliverProviderTxt()==null?"暂无":order.getDeliverProviderTxt()%></span> <select
					class="form-control zs-select-input " id="selectDeliever">
						<option value="">==请选择==</option>
				</select>
				</li>
				<li id="log_number" class="font14 ">物流单号：<span><%=order.getDeliverNumber()==null?"暂无":order.getDeliverNumber()%></span>
					<input type="text" class="form-control " id="deliverNumber" />
				</li>
				<li id="log_receiver" class="font14">&nbsp;&nbsp;&nbsp;收货人：<span><%=order.getReceiver() == null ? "" : order.getReceiver()%></span> <input
					type="text" class="form-control " id="receiver" />
				</li>
				<li id="log_phone" class="font14">联系电话：<span><%=order.getReceiverPhone() == null ? "" :order.getReceiverPhone()%></span>
					<input type="text" class="form-control " id="receiverPhone" />
				</li>
				<li id="log_address" class="font14 ">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;地址：
				<span class="region_show" >
					<span id="province_show" code="<%=order.getRegion1() == null ? "" :order.getRegion1() %>"><%=order.getRegion1Txt() == null ? "" :order.getRegion1Txt() %></span>
					<span id="city_show" code="<%=order.getRegion2() == null ? "" :order.getRegion2() %>"style="padding-left:20px;" ><%=order.getRegion2Txt() == null ? "" :order.getRegion2Txt() %></span>
					<span id="country_show" code="<%=order.getRegion3() == null ? "" :order.getRegion3()%>"style="padding-left:20px;"><%=order.getRegion3Txt() == null ? "" :order.getRegion3Txt() %></span>
					<br><span id="address_show" style="padding-left:70px;"><%=order.getAddressTxt() == null ? "" :order.getAddressTxt() %></span>
				</span>
				<span class="region_edit">
					<br>
					<select style="width:30%;" class=" zs-select-input" id='provinceSearch' onchange='searchConditionCity()'>
						</select>
	                <select style="width:30%;"  id="citySearch"     onchange='searchConditionCounty()'>
						</select>
	                <select style="width:30%;" id="countySearch"></select><br>
	                <input type="text" id="address_edit"  placeholder="请输入详细地址" style="text-align:left;width:100%;margin-top:10px;">
						<span class="row">
								<button type="button" style="float:right;" class="btn btn-default "
									onclick="OrderDo.cancel()">取消</button>
								<button type="button" style="float:right;width:20%;margin-right:10px;" class="btn btn-warning" 
									onclick="OrderDo.saveLog()">完成</button>
						</span>
				</span>
				
				</li>
	</div>

			</ul>
		</div>
		<%
			if (order.STATUS_DFH.equals(order.getStatus())) {
				//待发货状态下的--可以填写物流
		%>
		<div style="margin-top:20px;">	
			<button type="button" class="btn btn-danger" style="float:right;" onclick="OrderDo.send()" >发货</button>
			<button type="button" id="operate_btn"  style="float:right;margin-right:10px;" class="btn btn-primary" onclick="OrderDo.editLogistics()">编辑物流信息</button>
		</div>
		
		<%
			}
		%>


		<!-- 	<ul> -->
		<!-- <li>送货方式</li> -->
		<!-- 	<li><input type="radio" name="shippingMethod"  class="hide"/>上门自提</li> -->
		<!-- 	<li><input type="radio" name="shippingMethod"/>物流 -->

		<!-- 	</ul> -->
		<ul class="scale hide" id="scale">
		</ul>


		<table class="table table-hover"
			style="border: 0px solid #ddd; margin-top: 100px;">
			<thead>
				<tr>
					<!-- <th width="110">商品封面</th> -->
					<td width="30%">商品名称</td>
					<td width="20%">型号</td>
					<td width="20%">单价</td>
					<td width="10%">购买数量</td>
					<td width="20%">合计</td>
				</tr>
			</thead>
			<tbody>
				<%
					List<BmOrderItem> items = order.getItems();
					BigDecimal totalPrice = new BigDecimal("0");
					if (items != null && items.size() != 0) {

						for (BmOrderItem item : items) {
							totalPrice = totalPrice.add(item.getPrice());
				%>
				<tr>
					<td><%=item.getProductName() == null ? "" : item.getProductName()%></td>
					<td><%=item.getProductTypeName() == null ? "" : item.getProductTypeName()%>
					</td>
					<td><%=item.getPrice() == null ? "" : item.getPrice()%></td>
					<td><%=item.getNumber()%></td>
					<td><%=new BigDecimal(item.getNumber() + "").multiply(new BigDecimal(item.getPrice() + ""))%></td>
				</tr>
				<%
					}
					}
				%>
				<tr>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td>合计：	
					<%
					if(order.getTotal().equals(order.getRealTotal())){
						%>
							<span class="one_price"><%=order.getTotal()%></span>
						<%
					} else{
						%>
							<span class="one_price" style ="text-decoration: line-through;color: gray;"><%=order.getTotal()%></span>
							<span class="two_price" ><%=order.getRealTotal()%></span>
						<%
					
					}%>
					</td>
				</tr>
				<tr colspan="5" align="right">
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td>
						<%
							if ("1".equals(order.getStatus())) {
						%>
						<button id="price_update">修改价格</button> <%
 	}
 %>
					</td>
				</tr>
			</tbody>
		</table>
	</div>

<script type="text/javascript">
	var status = '<%=order.getStatus()%>';
	var orderId ='<%=order.getId()%>';
	var method = '<%=order.getDeliverType()%>';
	var region1code = '<%=order.getRegion1()%>';
	var region2code = '<%=order.getRegion2()%>';
	var region3code = '<%=order.getRegion3()%>';
	
	OrderDo.status = status;
	OrderDo.orderId = orderId;
	OrderDo.method = method;
	OrderDo.region1code = region1code;
	OrderDo.region2code = region2code;
	OrderDo.region3code = region3code;
	OrderDo.init();
</script>
</body>
</html>