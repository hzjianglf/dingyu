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

	String tokenId = (String) request.getAttribute("tokenId");
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
<title>采供网</title>
<%@include file="/common.jsp"%>
<script src="<%=basePath%>js/mallDialog.js"></script>
<script type="text/javascript"
	src="<%=basePath%>js/admin/regionSearch.js"></script>
	<script src="<%=basePath%>js/bootstrap-datepicker.js"></script>
<link rel="stylesheet" href="<%=basePath%>/css/datepicker.css">
<script type="text/javascript" src="<%=basePath%>js/admin/proxyMgmt.js"></script>

<style type="text/css">
.mfont {
	font-size: 15px;
}

.s-top {
	margin-top: 100px;
}
</style>
<meta content="" name="description" />
<meta content="" name="author" />

<title>采供网</title>
<link rel="stylesheet" href="<%=basePath%>/css/mall.css">
</head>
<body>

	<div class="container" style="width: 100%;">
		<div class="row" style="border: 0; margin-top: -10px;">
			<div class="col-sm-12">
				<h3 class="zs-iframe-title">代理商管理</h3>
			</div>
		</div>

		<div class="row">
			<label for="mobile" class="col-sm-2"
				style="text-align: right; padding-top: 7px;">状态</label>
			<div class="col-sm-4" style="padding-left: 0px;">
				<select class="form-control zs-select-input" id='status'>
					<option value=''>请选择代理商申请状态</option>
					<option value='1' selected="selected">申请中</option>
					<option value='2'>申请通过</option>
					<option value='3'>申请失败</option>
					<option value='4'>取消代理</option>
				</select>
			</div>
		</div>

		<div class="row" style="margin-top: 10px;">
			<label for="provinceSearch" class="col-sm-2"
				style="text-align: right; padding-top: 7px;">地区</label>
			<div class="col-xs-2">
				<select class="form-control zs-select-input" id='provinceSearch'
					onchange='searchConditionCity()'></select>
			</div>
			<div class="col-xs-2">
				<select class="form-control zs-select-input" id='citySearch'
					onchange='searchConditionCounty()'></select>
			</div>
			<div class="col-xs-2">
				<select class="form-control zs-select-input" id='countySearch'></select>
			</div>
			<div class="col-sm-2">
				<button type="button" id="queryBtn"
					class="btn btn-default zs-btn-default">查询</button>
			</div>
		</div>

		<div class="data_content row" style="margin-top: 10px;">
			<div class="col-sm-12">
				<table class="table table-hover" style="border: 1px solid #ddd;">
					<thead>
						<tr>
							<th width="110">商铺名称</th>
							<th width="110">联系方式</th>
							<th width="110">申请时间</th>
							<th width="110">缴纳保证金</th>
							<th width="110">申请备注</th>
							<th width="110">状态</th>
							<th width="110">操作</th>
						</tr>
					</thead>
					<tbody>
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
	</div>
</body>

<script>
	$(function() {
		toPage(0);
		bindQuery();
	})
	var tokenId = $("#tokenId").val();
	var currentPage = $("#currentPage").val();
	var isNext = $("#isNext").val();
	var length = $("#length").val();
	var userId = "";
	function bindQuery() {
		$("#queryBtn").bind("click", function() {
			toPage(0);
		});
	}
	function toPage(start) {
		if ($("#queryBtn").hasClass("isLoading")) {
			return;
		}
		$("#queryBtn").addClass("isLoading").html("loading...");
		if (start == undefined || start == null) {
			start = 0;
		}
		var province = $("#provinceSearch").val();
		var city = $("#citySearch").val();
		var district = $("#countySearch").val();
		if (province == '0' || !province) {
			province = '-1';
			city = '-1';
			district = '-1';
		}
		if (city == '0' || !city) {
			city = '-1';
			district = '-1';
		}
		if (district == '0' || !district) {
			district = '-1';
		}
		var status = $("#status").val();
		if (!status) {
			showDialog("请选择申请状态", "toast", "", "");
			$("#queryBtn").removeClass("isLoading").html("查询");
			return;
		}
		var param = {
			isProxy : status,
			region1 : province,
			region2 : city,
			region3 : district,
			start : start,
		};
		sendRequest(
				param,
				"/bm/proxy/getProxyList.do",
				function(json) {
					var list = json.data.list;
					$("tbody").empty();
					if(list.length == 0){
						showDialog("暂无数据","toast","","");
						$("#queryBtn").removeClass("isLoading").html("查询");
						return;
					}
					$(list).each(
									function(i, item) {
										var showStatus = "";
										if (item.isProxy == "1") {
											showStatus = "申请中";
										} else if (item.isProxy == "2") {
											showStatus = "通过";
										} else if (item.isProxy == "3") {
											showStatus = "失败";
										}else if (item.isProxy == "4") {
											showStatus = "取消";
										}
										var htmlProxy = "";
										htmlProxy += "    <tr class=\"item\" data_shopId = \""+item.id+"\">         ";
										htmlProxy += "			        <td align=\"center\">"+ item.name + "</td>";
										htmlProxy += "			        <td align=\"center\">"+ item.telephone + "</td> ";
										htmlProxy += "			        <td align=\"center\">"+ item.proxyApplyTime + "</td>";
										htmlProxy += "					<td align=\"center\">"+ item.proxyFee + "</td>";
										htmlProxy += "					<td align=\"center\">"+ item.proxyApplyRemark+ "</td>";
										htmlProxy += "					<td align=\"center\">"+ showStatus + "</td>";
										htmlProxy += "			        <td align=\"center\">";
										if (item.isProxy == "1") {
											htmlProxy += "			        	<a class=\"area_set\" style=\"display:block;  \" >处理</a>";
										} else if (item.isProxy == "2") {
											htmlProxy += "			        	<a class=\"area_set\" style=\"display:block; \" >查看详情</a>";
										} else if (item.isProxy == "3") {
											htmlProxy += "			        	<a class=\"area_set\"  style=\"display:block; \" >查看详情</a>";
										}else if (item.isProxy == "4") {
											htmlProxy += "			        	<a class=\"area_set\"  style=\"display:block; \" >查看详情</a>";
										}
										htmlProxy += "			        </td>  		      ";
										htmlProxy += "			      </tr>";
										$("tbody").append(htmlProxy);
										
									});
					bindAreaSet();
					$("#queryBtn").removeClass("isLoading").html("查询");
					$("#navArea").html(showNav(json.data));
				}, function() {
					showDialog("查询失败", "toast", "", "");
					$("#queryBtn").removeClass("isLoading").html("查询");
				});

	}
	function bindAreaSet() {
		$(".area_set").unbind("click");
		$(".area_set").bind("click", function() {
			var shopId = $(this).parents(".item").attr("data_shopId");
			window.location=toServerPageUrl("/bm/proxy/proxyDetail.do?shopId=" + shopId);
/* 
			 $('#time_begin').datepicker({format:'yyyy-mm-dd'}).on('changeDate', function(ev){
		        $('#time_begin').datepicker('hide');
		    });
			 $('#time_end').datepicker({format:'yyyy-mm-dd'}).on('changeDate', function(ev){
			        $('#time_end').datepicker('hide');
			    }); */
		});
	}
	function dimissAdmin(){
		$("#area_set_m").modal("hide");
	}
	
</script>

</body>