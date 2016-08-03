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
			/* PageInfo<BmShop> pageInfo = (PageInfo<BmShop>)request.getAttribute("data");
			//List<BmShop> list = (List<BmShop>)pageInfo.getList();
			/*一共有多少页  */
			//int totalPage = pageInfo.getPages();
			/* 当前页显示的数量  */
			//int size = pageInfo.getSize();
			/* 当前页  */
			//int currentPage = pageInfo.getPrePage() + 1;
			/* 一页显示多少条  */
			//int length = pageInfo.getPageSize();
			/* 是否有下一页  */
			//boolean isNext = pageInfo.isHasNextPage();
			/* 页数数组 */
			//int[] data = pageInfo.getNavigatepageNums();

	//String tokenId = (String)request.getAttribute("tokenId"); */
%>
<!DOCTYPE html>
<html lang="zh-CN">

<head>
<%@include file="/common.jsp"%>
<script src="<%=basePath%>js/mallDialog.js"></script>
<script src="<%=basePath%>js/num-format.js"></script>
<script src="<%=basePath%>js/xcConfirm.js"></script>
<link href="<%=basePath%>css/xcConfirm.css" rel="stylesheet">
<title>商铺管理列表</title>
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
					value="<%=(String) request.getParameter("shopName") == null ? "" : (String) request.getParameter("shopName")%>">
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

		<%--<nav class="navbar navbar-default navbar-fixed-top row" style="border:0" >--%>
		<%--<ol class="breadcrumb">--%>
		<%--<li style="*float:left"><a href="#">系统管理员</a></li>--%>
		<%--<li style="*float:left"><a href="#">入驻商铺情况</a></li>--%>
		<%----%>
		<%--</ol>--%>
		<%--<div style="width:100%; margin:auto">--%>
		<%--<form id="searchForm" action="" method="post">	--%>
		<%--<div class="query_area" >--%>
		<%--<label for="name" class="col-sm-1" style=" text-align:center;padding-top:7px;">商铺名称</label>--%>
		<%--<div class="col-sm-1"  style=" padding-left:0px;">--%>
		<%--<input type="text" id="shopName" class="form-control" style="float:left" value="<%=(String)request.getParameter("shopName") == null ? "" : (String)request.getParameter("shopName") %>">--%>
		<%--</div>--%>
		<%--<label for="pClass" class="col-sm-1" style="text-align:right; padding-top:7px;">地区</label>--%>
		<%--<div class="col-xs-1"><select class="form-control zs-select-input" id='province' onchange='searchCity()'></select></div>--%>
		<%--<div class="col-xs-2"><select class="form-control zs-select-input" id='city'  onchange='searchCounty()'></select></div>--%>
		<%--<div class="col-xs-2"><select class="form-control zs-select-input" id='county' ></select> </div>--%>
		<%----%>
		<%--<div class=" col-sm-2" align="right">--%>
		<%--<button type="button" class="btn btn-default" onclick="search()">查询</button>      	--%>
		<%--</div>--%>
		<%----%>
		<%--</div>--%>
		<%--</form>--%>
		<%--</div>--%>
		<%----%>
		<%--</nav>--%>
		
	
		<div class="data_content row" style="margin-top: 10px;">
			<div class="col-sm-12">
				<table class="table table-hover" style="border: 1px solid #ddd;">
					<thead>
						<tr>
							<th width="110">商铺名称</th>
							<th width="110">联系方式</th>
					
							<th width="110">地区</th>
							<th width="110">认证时间</th>
							<th width="110">服务费率（%）</th>
            				<th width="110">货到付款</th>
							<th width="110">操作</th>
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
	var common="";
	var shopId="";
	var pageCur="";
	var modal=["	<div class=\"modal fade\" id=\"myModal_update\" tabindex=\"-1\" role=\"dialog\"",
	           "		aria-labelledby=\"myModalLabel\">",
	           "		<div class=\"modal-dialog\" role=\"document\">",
	           "			<div class=\"modal-content\">",
	           "				<div class=\"modal-header\">",
	           "					<button type=\"button\" class=\"close\" data-dismiss=\"modal\"",
	           "						aria-label=\"Close\">",
	           "						<span aria-hidden=\"true\">&times;</span>",
	           "					</button>",
	           "					<h4 class=\"modal-title\">修改服务费率和付款方式</h4>",
	           "				</div>",
	           "				<div class=\"modal-body\">",
	           "					<form id=\"update\" class=\"form-horizontal\" method=\"post\">",
	           "						<div class=\"form-group\">",
	           "							<label for=\"serviceFee\" class=\"col-sm-3 control-label\" >服务费率（%）</label>",
	           "							<div class=\"col-sm-5\">",
	           "								<input type=\"text\" class=\"form-control\" id=\"serviceFee\" value=\"\" />",
	           "							</div>",
	           "						</div>",
	           "						<div class=\"form-group\">",
	           "							<label for=\"payType\" class=\"col-sm-3 control-label\">货到付款</label>",
	           "							<div class=\"col-sm-5\">",
	           "								<select id=\"payType\" style=\"width:100px;\">",
	           "									<option value=\"F\" >不支持</option>",
	           "									<option value=\"T\" >支持</option>",
	           "								</select>",
	           "							</div>",
	           "						</div>",
	           "					</form>",
	           "				</div>",
	           "				<div class=\"modal-footer\">",
	           "					<button type=\"button\" class=\"btn btn-default\" data-dismiss=\"modal\">取消</button>",
	           "					<button type=\"button\" class=\"btn btn-primary\" onclick=\"save()\">保存</button>",
	           "				</div>",
	           "			</div>",
	           "		</div>",
	           "	</div>"].join("");
	function save(){
		var receivePay = "F";
		if($("#payType").val()=="T"){
			receivePay = "T";
		}
		var serviceFee = $("#serviceFee").val();
		if(!serviceFee){
			showDialog("服务费率不能为空", "toast", "", "");
			return;
		}
		var param = {
				id:shopId,
				receivePay:receivePay,
				serviceFee:serviceFee,
		}
		sendRequest(param, "/bm/shop/saveShopBaseMsg.do", function(json){
			$("#myModal_update").modal("hide");
			toPage(pageCur);
		});
	}
	$(function(){
		sendRequest({code:"SPER"},"/bm/busiParam/query.do", function(json){
			common = json.param.value;
		});
	})
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
									shopName : shopName,
									region1:province,
									region2:city,
									region3:district
							}
						}else{
							param = {
									start : start,
									shopName : shopName,
									region1:province,
									region2:city
							}
						}
					}else{
						param = {
								start : start,
								shopName : shopName,
								region1:province
						}
					}
					
				}else{
					param = {
							start : start,
							shopName : shopName,
					}
				}
				sendRequest(param, "/pm/admin/sysShopManage.do", function(json) {
					var data = json.data;
					var shopList = data.list;
					 $(shopList).each(function (i, shop) {
						 var shopHtml = "";
						 
						 				shopHtml+=	"<tr class=\"item\">";
						 				shopHtml+="								<td align=\"center\">"+shop.name+"</td>";
						 				shopHtml+= "							<td align=\"center\">"+shop.telephone+"</td>";
						 				shopHtml+= "							<td align=\"center\">"+shop.region1Name+shop.region2Name+shop.region3Name+"</td>";
						 				shopHtml+= "							<td align=\"center\">"+shop.identiTime+"</td>";
						 				if(shop.serviceFee){
						 					shopHtml+= "						<td  class=\"service\" data_fee=\""+shop.serviceFee+"\" align=\"center\">"+shop.serviceFee+"</td>";
						 				}else{
						 					shopHtml+= "						<td class=\"service\"  data_fee=\""+common+"\" align=\"center\">"+common+"</td>";
						 				}
						 				if(shop.receivePay == "T"){
						 					shopHtml+= "						<td  class=\"pay\"  data_pay=\"T\" align=\"center\">支持</td>";
						 				}else{
						 					shopHtml+= "						<td class=\"pay\"  data_pay=\"F\"   align=\"center\">不支持</td>";
						 				}
						 				shopHtml+= "						<td align=\"center\"><a class=\"operate\" data_shopId=\""+shop.id+"\">操作</a></td>";
						 				shopHtml+= "						</tr>"
						 $("#data_content").append(shopHtml);
					 });
					 bindOperate();
					 $("#queryBtn").removeClass("loading").html("查询");
					 $("#navArea").html(showNav(data));
					 pageCur = data.startRow;
				});
				
			}
	function bindOperate(){
		$(".operate").bind("click",function(){
			var _this = $(this);
			var parent =_this.parents(".item");
		 	shopId = _this.attr("data_shopId");
			var fee = parent.find(".service").attr("data_fee");
			var pay = parent.find(".pay").attr("data_pay");
			if($("#myModal_update").length == 0){
				$("body").append(modal);
			}else{
				$("#myModal_update").modal("show");
			}
			$("#serviceFee").val(fee);
			 $("option[value=" + pay + "]").attr("selected", "selected");
			
		});
	}
		</script>
</body>
</html>