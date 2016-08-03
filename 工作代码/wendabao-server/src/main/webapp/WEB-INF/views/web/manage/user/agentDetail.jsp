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
	String agentId = request.getParameter("agentId");
%>
<!DOCTYPE html>
<html lang="zh-CN">

<head>
<%@include file="/common.jsp"%>

<script type="text/javascript"
	src="<%=basePath%>js/admin/regionSearch.js"></script>

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
			<!-- 		<div class="col-sm-12">
				<span>申请状态:</span><span id="proxyStatus"> 
				</span>
			</div> -->
		</div>
	</div>
	<!-- 	<ul class="nav nav-tabs" style="margin-top: 10px; width: 83%;">
		<li id="shopBaseTop" role="presentation" class="active"><a>商户基本信息</a></li>
		<li id="areaProxyTop" role="presentation"><a>代理区域</a></li>
	</ul> -->
	<div id="shopBase">
		<div class="row" style="margin-top: 20px">
			<div class="col-sm-2 col-md-2 ">
				<label style="padding-top: 5px; float: right">代理商名称</label>
			</div>
			<div class="col-sm-4 col-md-4">
				<input type="text" c class="form-control" id="agentname" />
			</div>
			<div class="col-sm-4 col-md-4"></div>
		</div>
		<br />
		<div class="row">
			<div class="col-sm-2 col-md-2 ">
				<label style="float: right">用户名</label>
			</div>
			<div class="col-sm-4 col-md-4">
				<!-- <select class="form-control zs-select-input"  id='username' >
					<option value='' >请选择手机号</option>
				</select> -->
				<input type="text" class="form-control" id="username"
					list="phoneList">
			</div>
			<datalist id="phoneList">
			</datalist>
		</div>
		<br />
		<div class="row">
			<div class="col-sm-2 col-md-2 ">
				<label style="padding-top: 5px; float: right">家庭地址</label>
			</div>
			<div class="col-sm-4 col-md-4">
				<input type="text" class="form-control" id="homeAddress" />
			</div>
			<div class="col-sm-4 col-md-4"></div>
		</div>
		<br />
		<div class="row">
			<div class="col-sm-2 col-md-2 ">
				<label style="padding-top: 5px; float: right">邮箱地址</label>
			</div>
			<div class="col-sm-4 col-md-4">
				<input type="text" c class="form-control" id="postAddress" />
			</div>
			<div class="col-sm-4 col-md-4"></div>
		</div>
		<br />

		<div class="row">
			<div class="col-xs-2 col-md-2 ">
				<label style="padding-top: 5px; float: right">联系方式</label>
			</div>

			<div class="col-sm-4 col-md-4">
				<input type="text" c class="form-control" id="phone" />
			</div>
			<div class="col-xs-4 col-md-4"></div>
		</div>
		<br />

		<div class="row">
			<div class="col-sm-2 col-md-2 ">
				<label style="padding-top: 5px; float: right">qq</label>
			</div>
			<div class="col-sm-4 col-md-4">
				<input type="text" c class="form-control" id="qq" />
			</div>
			<div class="col-sm-4 col-md-4"></div>
		</div>
		<br />
	</div>
	<div id="areaProxy">
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
			
		</div>
		<br />
		<div class="row">
			<div class="col-sm-2 col-md-2 col-sm-offset-2 ">
				<button id="update_btn" class=" btn btn-default">保存</button>
			</div>
			<div class="col-sm-2 col-md-2">
				<button id="delete_btn" class="btn btn-default">删除</button>
			</div>
		</div>
	</div>
	<br>
</body>
<script type="text/javascript">
	var agentId ;
	var userId ;
	$(function() {
		if(<%=agentId%>){
			agentId = '<%=agentId%>';
			//详情修改
			sendRequest({
				id : agentId
			}, "/bm/agent/getAgentDetail.do", function(json) {
				var agent = json.data;
				userId = agent.userId;
				$("#username").attr("disabled", "disabled");
				$("#agentname").val(agent.name);
				$("#username").val(agent.user.loginName);
				$("#homeAddress").val(agent.homeAddress);
				$("#phone").val(agent.tel);
				$("#qq").val(agent.qq);
				setDefaultSelect(agent.region1 ? agent.region1 : "0",
						agent.region2 ? agent.region2 : "0",
						agent.region3 ? agent.region3 : "0");
				bindDelete();
				bindSave();
			});

		} else {
			agentId = '';
			$("#delete_btn").hide();
			bindSave();
		}
	})

	//绑定删除按钮
	function bindDelete() {
		$("#delete_btn").bind("click", function() {

		});
	}
	//绑定保存按钮
	function bindSave() {
		$("#update_btn").bind(
				"click",
				function() {
					if ($("#update_btn").hasClass("load")) {
						return;
					}
					$("#update_btn").addClass("load").html("loading");
					var name = $("#agentname").val();
					var mobile = $("#username").val();
					var homeAddress = $("#homeAddress").val();
					var postAddress = $("#postAddress").val();
					var tel = $("#phone").val();
					var qq = $("#qq").val();
					var region1 = $("#provinceSearch").val();
					var region2 = $("#citySearch").val();
					var region3 = $("#countySearch").val();
					var param = {};
					if (!name) {
						showDialog("代理商名称不能为空", "toast");
						$("#update_btn").removeClass("load").html("保存");
						return;
					}
					if (!mobile) {
						showDialog("代理商登录名不能为空", "toast");
						$("#update_btn").removeClass("load").html("保存");
						return;
					}
					if (!region1 || region1 == "0") {
						showDialog("代理省不能为空", "toast");
						$("#update_btn").removeClass("load").html("保存");
						return;
					}
					param.name = name;
					param.mobile = mobile;
					param.homeAddress = homeAddress;
					param.postAddress = postAddress;
					param.tel = tel;
					param.qq = qq;
					param.region1 = region1;
					param.region1Txt = getProvinceName(region1);
					if (region2 && region2 != "0") {
						param.region2 = region2;
						param.region2Txt = getCityName(region2);
					}
					if (region3 && region3 != "0") {
						param.region3 = region3;
						param.region3Txt = getCountryName(region3);
					}
					if (agentId) {
						param.id = agentId;
						param.userId = userId;
						sendSaveOperation(param);
					} else {
						sendRequest({
							mobile : mobile
						}, "/pm/user/queryUserByMobile.do", function(json) {
							var user = json.data;
							if (user) {
								showDialog("所输入用户名已存在，是否继续设为代理商，将失去其本来的权限",
										"confirm", {
											onOk : function() {
												sendSaveOperation(param);
											},
											onCancel : function() {
												$("#update_btn").removeClass(
														"load").html("保存");
											}
										})
							} else {
								sendSaveOperation(param);
							}
						});
					}
				});
	}
	function sendSaveOperation(param) {
		sendRequest(param, "/bm/agent/saveAgent.do", function(json) {
			showDialog("添加成功", "toast");
			$("#update_btn").removeClass("load").html("保存");
			setTimeout(function() {
				window.location = toServerPageUrl("/bm/agent/agentList.do");
			}, 2000);
		}, function(json) {
			if(json.header.error_code == "0008"){
				showDialog(json.header.message, "toast");
			}
			$("#update_btn").removeClass("load").html("保存");
		});
	}
	function userList() {
		//sendRequest(requestBody, requestUrl, successFunc, failFunc)
	}
	//操作按钮的显示文字
	function btnShow() {
		var button = $("#operate_btn");
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
	function cancelBtnClass(_this) {
		if (_this.hasClass("doing")) {
			_this.removeClass("doing");
		}
		if (_this.hasClass("done")) {
			_this.removeClass("done");
		}
		if (_this.hasClass("fail")) {
			_this.removeClass("fail");
		}
		if (_this.hasClass("cancel")) {
			_this.removeClass("cancel");
		}
	}
	//拒绝
	function bindRefuse() {
		$("#refuse_btn").bind("click", function() {
			var _this = $(this);
			var oldTxt = _this.html();
			if (_this.hasClass("load")) {
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
			sendRequest(param, "/bm/proxy/applyProxy.do", function(json) {
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
				} else if (shop.isProxy == "3") {
					//拒绝状态
					cancelBtnClass(_this);
					_this.addClass("fail");
				}
				btnShow();
			}, function(json) {
				_this.removeClass("load").html(oldTxt);
			});
		});
	}
	//删除地区
	function bindDelRegion() {
		$(".del_region").unbind("click");
		$(".del_region").bind("click", function() {
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
				"region1" : region1,
				"region2" : region2,
				"region3" : region3,
				"id" : shopId
			};
			sendRequest(param, "/bm/proxy/deleteRegion.do", function(json) {
				parent.fadeOut(function() {
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
			if (_this.hasClass("load")) {
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
			sendRequest(param, "/bm/proxy/applyProxy.do", function(json) {
				showDialog("操作成功", "toast", "", "");
				_this.removeClass("load").html(oldTxt);
				var shop = json.data;
				if (shop.isProxy == "4") {
					//取消代理状态
					cancelBtnClass(_this);
					_this.addClass("cancel");
				} else if (shop.isProxy == "2") {
					//申请通过状态
					cancelBtnClass(_this);
					_this.addClass("done");
				} else if (shop.isProxy == "3") {
					//拒绝状态
					cancelBtnClass(_this);
					_this.addClass("fail");
				}
				btnShow();
			}, function(json) {
				showDialog(json.header.message, "toast", "", "");
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
									"/bm/proxy/addRegionForShop.do",
									function(json) {
										var region = json.region;
										var html = [
												"<div class=\"row\">",
												"				<div class=\"col-sm-2 col-sm-offset-2 region1\">",
												"					<span code=\""
														+ undefinedHandler(region.region1)
														+ "\">"
														+ undefinedHandler(region.region1Name)
														+ "</span>",
												"				</div>",
												"				<div class=\"col-sm-2 region2\">",
												"					<span code=\""
														+ undefinedHandler(region.region2)
														+ "\">"
														+ undefinedHandler(region.region2Name)
														+ "</span>",
												"				</div>",
												"				<div class=\"col-sm-2 region3\">",
												"					<span code=\""
														+ undefinedHandler(region.region3)
														+ "\">"
														+ undefinedHandler(region.region3Name)
														+ "</span>",
												"				</div>",
												"				<div class=\"col-sm-1\">",
												"					<a class=\"del_region\">x</a>",
												"				</div>", "			</div>" ]
												.join("");
										$(".region_have").append(html);
										bindDelRegion();
									},function(json){
										if(json.header.error_code == "0008"){
											showDialog(json.header.message, "toast");
										}
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