<%@page import="com.handany.bm.model.BmProxyRegion"%>
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
	BmShop shop = (BmShop) request.getAttribute("shop");
	List<BmProxyRegion> list = (List<BmProxyRegion>) request.getAttribute("region");
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
<script type="text/javascript"
	src="<%=basePath%>js/admin/regionSearch.js"></script>
<script src="<%=basePath%>js/bootstrap-datepicker.js"></script>
<link rel="stylesheet" href="<%=basePath%>/css/datepicker.css">
<title>订单详情</title>
</head>
<style type="text/css">
body {
	font: 400 16px/28px "microsoft yahei", Arial;
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
</style>

<script type="text/javascript">
	
</script>


<body>

	<div id="o_detail_content" style="display: block; width: 100%;"
		class="container">
		<div class="row" style="border: 0; margin-top: -10px;">
			<div class="col-sm-12">
				<h3 class="zs-iframe-title">代理商详情</h3>
			</div>
		</div>
		<div class="row" style="border: 0; margin-top: -10px;">
			<div class="col-sm-12">
				<span>申请状态:</span><span id="proxyStatus"> <%
 	if ("1".equals(shop.getIsProxy())) {
 %>申请中<%
 	} else if ("2".equals(shop.getIsProxy())) {
 %>已通过<%
 	} else if ("3".equals(shop.getIsProxy())) {
 %>失败<%
 	}
 %>
				</span>
			</div>
		</div>
	</div>
	<ul class="nav nav-tabs" style="margin-top: 10px; width: 83%;">
		<li id="shopBaseTop" role="presentation" class="active"><a>商户基本信息</a></li>
		<li id="areaProxyTop" role="presentation"><a>代理区域</a></li>
	</ul>
	<div id="shopBase">

		<div class="row" style="margin-top: 70px">
			<div class="col-sm-2 col-md-2 ">
				<label style="float: right">商铺编号</label>
			</div>
			<div class="col-sm-4 col-md-4"><%=(shop.getId() == null ? "" : shop.getId())%></div>
			<div class="col-sm-4 col-md-4"></div>
		</div>
		<div class="row" style="margin-top: 20px">
			<div class="col-sm-2 col-md-2 ">
				<label style="padding-top: 5px; float: right">商铺名称</label>
			</div>
			<div class="col-sm-4 col-md-4">
				<span class="form-control" id="shopName"><%=(shop.getName() == null ? "" : shop.getName())%>
				</span>
			</div>
			<div class="col-sm-4 col-md-4"></div>
		</div>

		<br />
		<div class="row">
			<div class="col-sm-2 col-md-2 ">
				<label style="padding-top: 5px; float: right">经营项目</label>
			</div>
			<div class="col-sm-4 col-md-4">
				<span class="form-control" style="width: 400px; height: 70px;"><%=shop.getBusiScope() == null ? "" : shop.getBusiScope()%></span>
			</div>
			<div class="col-sm-4 col-md-4"></div>
		</div>
		<br />

		<div class="row">
			<div class="col-xs-2 col-md-2 ">
				<label style="padding-top: 5px; float: right">商铺地址</label>
			</div>

			<div class="col-xs-2">
				<span class="form-control zs-select-input"><%=shop.getRegion1Name() == null ? "" : shop.getRegion1Name()%></span>
			</div>
			<div class="col-xs-2">
				<span class="form-control zs-select-input"><%=shop.getRegion2Name() == null ? "" : shop.getRegion2Name()%></span>
			</div>
			<div class="col-xs-2">
				<span class="form-control zs-select-input"><%=shop.getRegion3Name() == null ? "" : shop.getRegion3Name()%></span>
			</div>
			<div class="col-xs-4 col-md-4"></div>
		</div>
		<br />

		<div class="row">
			<div class="col-sm-2 col-md-2 ">
				<label style="padding-top: 5px; float: right">详细地址</label>
			</div>
			<div class="col-sm-4 col-md-4">
				<div class="info">
					<span class="form-control"><%=shop.getAddress() == null ? "" : shop.getAddress()%></span>
				</div>
			</div>
			<div class="col-sm-4 col-md-4"></div>
		</div>
		<br />
		<div class="row">
			<div class="col-sm-2 col-md-2 ">
				<label style="padding-top: 5px; float: right">客服电话</label>
			</div>
			<div class="col-sm-4 col-md-4">
				<span class="form-control" id="telephone" type="text"><%=shop.getTelephone() == null ? "" : shop.getTelephone()%></span>
			</div>
			<div class="col-sm-4 col-md-4"></div>
		</div>
	</div>
	<div id="areaProxy" data_shopId='<%=shop.getId()%>'>
		<div class="row">
			<div class="col-sm-2 col-md-2 ">
				<label style="padding-top: 5px; float: right">选择代理区域</label>
			</div>
			<div class="col-sm-4 col-md-4"></div>
		</div>

		

		<div class="row">
			<div class="col-sm-2 col-md-2 ">
				<label style="padding-top: 5px; float: right">选择范围</label>
			</div>
			<div class="col-sm-3">
				<select class="form-control zs-select-input" id='provinceSearch'
					onchange='searchConditionCity()'></select>
			</div>
			<div class="col-sm-3">
				<select class="form-control zs-select-input" id='citySearch'
					onchange='searchConditionCounty()'></select>
			</div>
			<div class="col-sm-2">
				<select class="form-control zs-select-input" id='countySearch'></select>
			</div>
			<div class="col-sm-1">
				<button type="button" class="btn btn-default" id="region_add">添加</button>
			</div>
		</div>
		<br>
		<div class="row ">
			<div class="col-sm-2 col-md-2 ">
				<label style="padding-top: 5px; float: right">已代理范围</label>
			</div>
		</div>
		<div class="region_have">
			<%
				for (BmProxyRegion region : list) {
			%>
			<div class="row">
				<div class="col-sm-2 col-sm-offset-2 region1">
					<span code=<%=region.getRegion1() == null ? "-1" : region.getRegion1()%>><%=region.getRegion1Name() == null ? "" : region.getRegion1Name()%></span>
				</div>
				<div class="col-sm-2 region2">
					<span  code=<%=region.getRegion2() == null ? "-1" : region.getRegion2()%>><%=region.getRegion2Name() == null ? "" : region.getRegion2Name()%></span>
				</div>
				<div class="col-sm-2 region3">
					<span  code=<%=region.getRegion3() == null ? "-1" : region.getRegion3()%>><%=region.getRegion3Name() == null ? "" : region.getRegion3Name()%>
					</span>
				</div>
				<div class="col-sm-1">
					<a class="del_region">x</a>
				</div>
			</div>
			<%
				}
			%>
		</div>
		<br>
		<div class="row">
			<div class="col-sm-2 col-md-2 ">
				<label style="padding-top: 5px; float: right">代理开始时间</label>
			</div>
			<div class="col-sm-4 col-md-4">
				<input type="text" id="time_begin" class="form-control"
					value='<%=shop.getProxyStartTime() == null ? "" : shop.getProxyStartTime()%>'
					style="float: left" readonly="">
			</div>
			<div class="col-sm-1"></div>
		</div>
		<br>
		<div class="row">
			<div class="col-sm-2 col-md-2 ">
				<label style="padding-top: 5px; float: right">代理结束时间</label>
			</div>
			<div class="col-sm-4 col-md-4">
				<input type="text" id="time_end" class="form-control"
					value="<%=shop.getProxyEndTime() == null ? "" : shop.getProxyEndTime()%>"
					style="float: left" readonly>
			</div>
			<div></div>
		</div>
		<br>
		<div class="row">
			<div class="col-sm-2 col-md-2 col-sm-offset-2 col-md-offset-2">
				<button id="operate_btn"  class=" btn btn-default
					<%if ("1".equals(shop.getIsProxy())) {%> doing
					<%} else if ("2".equals(shop.getIsProxy())) {%>
					done
					<%} else if ("3".equals(shop.getIsProxy())) {%>
					fail
					<%} else if ("4".equals(shop.getIsProxy())) {%>
					cancel<%}%>" ></button>
			</div>
			<div class="col-sm-2 col-md-2">
				<button id="refuse_btn" class="btn btn-default">拒绝</button>
			</div>
		</div>
	</div>
	<br>
</body>
<script type="text/javascript">
	$(function() {
		bindContentShow();
		showShopBase();
		initTime();
		bindRegionAdd();
		bindTimeSave();
		btnShow();
		bindRefuse();
		bindDelRegion();
		
	})
	//操作按钮的显示文字
	function btnShow() {
		var button=$("#operate_btn");
		if (button.hasClass("doing")) {
			button.html("设为代理");
			$("#refuse_btn").show();
			$("#proxyStatus").html("申请中");
		} else if (button.hasClass("done")) {
			button.html("取消代理");
			$("#refuse_btn").hide();
			$("#proxyStatus").html("申请通过");
		} else if (button.hasClass("fail")) {
			button.html("设为代理");
			$("#refuse_btn").hide();
			$("#proxyStatus").html("失败");
		} else if (button.hasClass("cancel")) {
			button.html("重新设为代理");
			$("#refuse_btn").hide();
			$("#proxyStatus").html("代理已取消");
		}
	}
	//时间比较
	function dateCompare(startdate, enddate) {
		var arr = startdate.split("-");
		var starttime = new Date(arr[0], arr[1], arr[2]);
		var starttimes = starttime.getTime();

		var arrs = enddate.split("-");
		var lktime = new Date(arrs[0], arrs[1], arrs[2]);
		var lktimes = lktime.getTime();

		if (starttimes >= lktimes) {
			return false;
		} else
			return true;

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
	//取消掉操作按钮的class
	function cancelBtnClass(_this){
		if(_this.hasClass("doing")){
			_this.removeClass("doing");
		}
		if(_this.hasClass("done")){
			_this.removeClass("done");
		}
		if(_this.hasClass("fail")){
			_this.removeClass("fail");
		}
		if(_this.hasClass("cancel")){
			_this.removeClass("cancel");
		}
	}
	//拒绝
	function bindRefuse(){
		$("#refuse_btn").bind("click",function(){
			var _this = $(this);
			var oldTxt = _this.html();
			if(_this.hasClass("load")){
				return;
			}
			_this.addClass("load").html("loading...");
			var isProxy = "3";
			var shopId = $("#areaProxy").attr("data_shopId");
			if (!shopId) {
				showDialog("shopId不存在", "toast", "", "");
				_this.removeClass("load").html(oldTxt);
				return;
			}
			var param = {
					"id" : shopId,
					"isProxy" : isProxy
				};
			sendRequest(param, "/pm/admin/applyProxy.do", function(json) {
				showDialog("操作成功", "toast", "", "");
				var shop = json.data;
				_this.removeClass("load").html(oldTxt);
				if (shop.isProxy == "4") {
					//取消代理状态
					cancelBtnClass(_this);
					_this.addClass("cancel");
				} else if (shop.isProxy == "2") {
					//申请通过状态
					cancelBtnClass(_this);
					_this.addClass("done");
				}else if(shop.isProxy == "3"){
					//拒绝状态
					cancelBtnClass(_this);
					_this.addClass("fail");
				}
				btnShow();
			},function(json){
				_this.removeClass("load").html(oldTxt);
			});
		});
	}
	//删除地区
	function bindDelRegion(){
		$(".del_region").unbind("click");
		$(".del_region").bind("click",function(){
			var _this = $(this);
			var parent = _this.parent().parent();
			var region1Div = parent.find(".region1");
			var region2Div = parent.find(".region2");
			var region3Div = parent.find(".region3");
			var region1Span = region1Div.find("span");
			var region2Span = region2Div.find("span");
			var region3Span = region3Div.find("span");
			var region1 = region1Span.attr("code");
			var region2 = region2Span.attr("code");
			var region3 = region3Span.attr("code");
			var shopId = $("#areaProxy").attr("data_shopId");
			var param = {
					"region1":region1,
					"region2":region2,
					"region3":region3,
					"id":shopId
			};
			sendRequest(param, "/pm/admin/deleteRegion.do", function(json){
				parent.fadeOut(function(){
					$(this).remove();
				});
			});
		});
	}
	//提交申请，保存时间
	function bindTimeSave() {
		$("#operate_btn").bind("click", function() {
			var _this = $(this);
			var oldTxt = _this.html();
			if(_this.hasClass("load")){
				return;
			}
			_this.addClass("load").html("loading...");
			var isProxy = "";
			var timeBegin = "";
			var timeEnd = "";
			if (_this.hasClass("doing")) {
				isProxy = "2";
				timeBegin = $("#time_begin").val();
				timeEnd = $("#time_end").val();
				if (!timeBegin) {
					showDialog("请选择开始日期", "toast", "", "");
					_this.removeClass("load").html(oldTxt);
					return;
				}
				if (!timeEnd) {
					showDialog("请选择结束日期", "toast", "", "");
					_this.removeClass("load").html(oldTxt);
					return;
				}
				if (!dateCompare(timeBegin, timeEnd)) {
					showDialog("结束日期不能小于开始日期", "toast", "", "");
					_this.removeClass("load").html(oldTxt);
					return;
				}
			} else if (_this.hasClass("done")) {
				isProxy = "4";
			} else if (_this.hasClass("fail")) {
				isProxy = "2";
				timeBegin = $("#time_begin").val();
				timeEnd = $("#time_end").val();
				if (!timeBegin) {
					showDialog("请选择开始日期", "toast", "", "");
					_this.removeClass("load").html(oldTxt);
					return;
				}
				if (!timeEnd) {
					showDialog("请选择结束日期", "toast", "", "");
					_this.removeClass("load").html(oldTxt);
					return;
				}
				if (!dateCompare(timeBegin, timeEnd)) {
					showDialog("结束日期不能小于开始日期", "toast", "", "");
					_this.removeClass("load").html(oldTxt);
					return;
				}
			} else if (_this.hasClass("cancel")) {
				isProxy = "2";
				timeBegin = $("#time_begin").val();
				timeEnd = $("#time_end").val();
				if (!timeBegin) {
					showDialog("请选择开始日期", "toast", "", "");
					_this.removeClass("load").html(oldTxt);
					return;
				}
				if (!timeEnd) {
					showDialog("请选择结束日期", "toast", "", "");
					_this.removeClass("load").html(oldTxt);
					return;
				}
				if (!dateCompare(timeBegin, timeEnd)) {
					showDialog("结束日期不能小于开始日期", "toast", "", "");
					_this.removeClass("load").html(oldTxt);
					return;
				}
			}
			var shopId = $("#areaProxy").attr("data_shopId");
			if (!shopId) {
				showDialog("shopId不存在", "toast", "", "");
				_this.removeClass("load").html(oldTxt);
				return;
			}
			var param = {
				"id" : shopId,
				"proxyStartTime" : timeBegin,
				"proxyEndTime" : timeEnd,
				"isProxy" : isProxy
			};
			sendRequest(param, "/pm/admin/applyProxy.do", function(json) {
				showDialog("操作成功", "toast", "", "");
				var shop = json.data;
				if (shop.isProxy == "4") {
					//取消代理状态
					cancelBtnClass(_this);
					_this.addClass("cancel");
				} else if (shop.isProxy == "2") {
					//申请通过状态
					cancelBtnClass(_this);
					_this.addClass("done");
				}else if(shop.isProxy == "3"){
					//拒绝状态
					cancelBtnClass(_this);
					_this.addClass("fail");
				}
				btnShow();
			},function(json){
				_this.removeClass("load").html(oldTxt);
			});
		});
	}
	//添加地区
	function bindRegionAdd() {
		$("#region_add")
				.bind(
						"click",
						function() {
							var region1 = $("#provinceSearch").val();
							var region2 = $("#citySearch").val();
							var region3 = $("#countySearch").val();
							var shopId = $("#areaProxy").attr("data_shopId");

							if (!region1 || region1 == "0") {
								showDialog("请至少选择到市级地区", "toast", "", "");
								return;
							}
							if (!region2 || region2 == "0") {
								showDialog("请至少选择到市级地区", "toast", "", "");
								return;
							}
							if (!region3 || region3 == "0") {
								region3 = "-1";
							}
							if (!shopId) {
								showDialog("shopId不存在", "toast", "", "");
								return;
							}
							var param = {
								"region1" : region1,
								"region2" : region2,
								"region3" : region3,
								"id" : shopId
							}

							sendRequest(
									param,
									"/pm/admin/addRegionForShop.do",
									function(json) {
										var region = json.region;
										var html = [
												"<div class=\"row\">",
												"				<div class=\"col-sm-2 col-sm-offset-2 region1\">",
												"					<span code=\""+undefinedHandler(region.region1)+"\">"
														+ undefinedHandler(region.region1Name)
														+ "</span>",
												"				</div>",
												"				<div class=\"col-sm-2 region2\">",
												"					<span code=\""+undefinedHandler(region.region2)+"\">"
														+ undefinedHandler(region.region2Name)
														+ "</span>",
												"				</div>",
												"				<div class=\"col-sm-2 region3\">",
												"					<span code=\""+undefinedHandler(region.region3)+"\">"
														+ undefinedHandler(region.region3Name)
														+ "</span>",
												"				</div>",
												"				<div class=\"col-sm-1\">",
												"					<a class=\"del_region\">x</a>",
												"				</div>", "			</div>" ]
												.join("");
										$(".region_have").append(html);
										bindDelRegion();
									});

						});
	}
	//绑定导航栏 切换
	function bindContentShow() {
		$("#shopBaseTop").bind("click", function() {
			showShopBase();
		});
		$("#areaProxyTop").bind("click", function() {
			showProxy();
		});
	}
	//导航栏 切换
	function showShopBase() {
		$("#shopBase").show();
		$("#areaProxy").hide();

		$("#shopBaseTop").addClass("active");
		$("#areaProxyTop").removeClass("active");

	}
	//导航栏 切换
	function showProxy() {
		$("#areaProxy").show();
		$("#shopBase").hide();

		$("#areaProxyTop").addClass("active");
		$("#shopBaseTop").removeClass("active");
	}
</script>
</html>