<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
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
<html lang="zh-CN">

<head>
<%@include file="/common.jsp"%>
<title>商铺管理列表</title>
<script src="<%=basePath%>js/mallDialog.js"></script>
<script src="<%=basePath%>js/num-format.js"></script>
<script src="<%=basePath%>js/xcConfirm.js"></script>
<link href="<%=basePath%>css/xcConfirm.css" rel="stylesheet">
<link rel="stylesheet" href="<%=basePath%>/css/mall.css">
</head>
<style>

</style>
<body>

	<div class="container" style="width: 100%;">

		<div class="row" style="border: 0; margin-top: -10px;">
			<div class="col-sm-12">
				<h3 class="zs-iframe-title">入驻商铺情况</h3>
			</div>
		</div>

		<div class="row">
			<label for="name" class="col-sm-2"
				style="text-align: right; padding-top: 7px;">商铺名称</label>
			<div class="col-sm-4" style="padding-left: 0px;">
				<input type="text" id="shopName" class="form-control"
					style="float: left"
					value="">
			</div>
		</div>

		<div class="row" style="margin-top: 10px;">

			<label for="pClass" class="col-sm-2"
				style="text-align: right; padding-top: 7px;">地区</label>
			<div class="col-xs-2">
				<select class="form-control zs-select-input" id='province'
					onchange='searchCity()'></select>
			</div>
			<div class="col-xs-2">
				<select class="form-control zs-select-input" id='city'
					onchange='searchCounty()'></select>
			</div>
			<div class="col-xs-2">
				<select class="form-control zs-select-input" id='county'></select>
			</div>

			<div class=" col-sm-2">
				<button type="button" data_type="1" id="queryBtn" class="btn btn-default zs-btn-default"
					onclick="toPage(0)">查询</button>
			</div>

		</div>

	
		<div class="data_content row" style="margin-top: 10px;">
			<div class="col-sm-12">
				<table class="table table-hover" style="border: 1px solid #ddd;">
					<thead>
						<tr>
							<th width="110">商铺名称</th>
							<th width="110">联系方式</th>
							<th width="220">经营范围</th>
							<th width="110">地区</th>
							<th width="110">认证时间</th>
						</tr>
					</thead>
					<tbody id="data_content">
						

					</tbody>

				</table>
				</div>
			  <div class="col-sm-12" id="navArea">
			  <nav class="data-nav" >	
				  <ul class="pagination" >
				    <li class="disabled">
				      <a href="#" aria-label="Previous">
				        <span aria-hidden="true" >上一页</span>
				       
				      </a>
				    </li>			    
					   
					    <li  class="disabled">
					      <a href="#" aria-label="Next">
					        <span aria-hidden="true">下一页</span>
					      </a>
					    </li>
					  </ul>
					</nav>		
			  
				</div>
			

		</div>
	</div>

	<script src="<%=basePath%>js/region.js"></script>
	<script type="text/javascript">
		function toPage(start) {
			if($("#queryBtn").hasClass("loading")){
				return;
			}
			$("#queryBtn").addClass("loading").html("loading..");
			$("#data_content").empty();
				//$("#queryBtn").attr("disabled","disabled");
				if (start == undefined || start == null) {
					start = 0;
				}
				var param ={};
				var province = $("#province").val();
				var city = $("#city").val();
				var district = $("#county").val();
				var shopName = $("#shopName").val();
				plog("province"+province);
				plog("city"+city);
				plog("district"+district);
				plog("shopName"+shopName);
				if(province&&province!="0"){
					param.region1 = province;
					if(city&&city!="0"){
						param.region2= city;
						if(district&&district!="0"){
							param.region3= district;
							param = {
									start : start,
									name : shopName,
									region1:province,
									region2:city,
									region3:district
							}
						}else{
							param = {
									start : start,
									name : shopName,
									region1:province,
									region2:city
							}
						}
					}else{
						param = {
								start : start,
								name : shopName,
								region1:province
						}
					}
					
				}else{
					param = {
							start : start,
							name : shopName,
					}
				}
				sendRequest(param, "/bm/proxy/getProxyShopList.do", function(json) {
					var data = json.data;
					var shopList = data.list;
					 $(shopList).each(function (i, shop) {
						 var shopItem = ["<tr>",
										 "							<td align=\"center\">"+shop.name+"</td>",
										 "							<td align=\"center\">"+shop.telephone+"</td>",
										 "							<td align=\"center\">"+shop.busiScope+"</td>",
										 "							<td align=\"center\">"+shop.region1Name+shop.region2Name+shop.region3Name+"</td>",
										 "							<td align=\"center\">"+shop.identiTime+"</td>",
										 "						</tr>"].join("");
						 $("#data_content").append(shopItem);
					 });
					 $("#queryBtn").removeClass("loading").html("查询");
					 $("#navArea").html(showNav(data));
				}, function(json) {
					showDialog(json.header.message, "toast", "", "");
					$("#queryBtn").removeClass("load").html("查询");
				});
				
			}
		</script>
</body>
</html>