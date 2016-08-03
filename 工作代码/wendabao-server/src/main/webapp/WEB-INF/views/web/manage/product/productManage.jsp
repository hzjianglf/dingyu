<%@page import="com.handany.base.common.ApplicationConfig"%>
<%@page import="com.handany.rbac.common.UserContextManager"%>
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
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

String shopId = UserContextManager.getLoginUser().getGroupId();

String imageServer = ApplicationConfig.getConfig("image_server");

%>
<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en" class="no-js">
<!--<![endif]-->
<!-- BEGIN HEAD -->
<head>
<meta charset="utf-8"/>

	<!-- 为了让 IE 浏览器运行最新的渲染模式下，建议将此 <meta> 标签加入到你的页面中：-->
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	<!--将下面的 <meta> 标签加入到页面中，可以让部分国产浏览器默认采用高速模式渲染页面：-->
	<meta name="renderer" content="webkit">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->

	<meta content="" name="description"/>
	<meta content="" name="author"/>
	
	<title>采供网</title>
	<script src="<%=basePath%>js/mallDialog.js"></script>
	<!-- BEGIN GLOBAL MANDATORY STYLES -->
	<%@include file="/common.jsp" %>
	<style>
	.prodPic{height:100px;width:100px}

	.data-nav{float:right}
	.data-nav-info{float:left}
	td>a{
		display: block;
	}
	</style>
	
	<script type="text/javascript">
	function toPage(start){
		if($("#queryBtn").hasClass("isLoading")){
			return;
		}
		$("#queryBtn").addClass("isLoading").html("loading...");
		if(start == undefined || start == null){
			start = 0;
		}
		var status=$("#status").val();
		var name = $("#name").val();
		var shopId = $("#shopId").val();
		sendRequest({name:name,shopId:shopId,status:status,start:start},
				"/bm/product/selectByShopId.do",
				function(json){
					$("#queryBtn").removeAttr("disabled");
					var list = json.data.list;
					$("#data_content").empty();
					if(list.length == 0){
						showDialog("暂无商品","toast","","");
						$("#queryBtn").removeClass("isLoading").html("查询");
						return;
					}
					for(var i=0;i<list.length;i++){
							var item = list[i];
							var picUrl = "";
							if(item.picUrl){
								picUrl = "<%=imageServer%>/"+item.picUrl;
							}else{
								picUrl = '<%=basePath%>'+"image/hd.png";
							}
							$("#data_content").append(
							["    <tr>           ",
							 "			        <td align=\"left\">"+item.id+"</td>",
							 "			        <td align=\"center\"><img class='prodPic' src=\""+picUrl+"\"></td>",
							 "			        <td align=\"left\">"+item.name+"</td>",
							 "			        <td align=\"left\">"+showText(item.descText)+"</td> ",
							 "			        <td align=\"right\">"+powAmount(item.price,2)+"</td>",
							 "					<td align=\"center\">",
												 item.number,
							 "					</td>",
							 "			        <td align=\"center\">",
							 			showStatus( item.status),
							 "			        </td>  		      ",
							 "			        <td align=\"center\">",
							 "			       <a onclick=\"showProductDetail('"+item.id+"')\" >编辑商品</a>",
							 "			       <a onclick=\"updateStatus('"+item.id+"','"+reverseStatus(item.status)+"')\" >"+showStautsOperate(item.status)+"</a>",
							 "			        </td>",
							 "			      </tr>"].join("")
							);
							$("#queryBtn").removeClass("isLoading").html("查询");
					}
					$("#navArea").html(showNav(json.data));
				},function(){
					showDialog("查询失败","toast","","");
					$("#queryBtn").removeClass("isLoading").html("查询");
				}
		)
	}
	function addProduct(){
		window.location=toServerPageUrl("/bm/product/nextProductId.do");
	}
	function reverseStatus(status){
		if(status == "T"){
			return "F";
		}else{
			return "T";
		}
	}

	function updateStatus(id,status){
		
		$.ajax({
			url:toServerUrl("/bm/product/updateStatus.do"),
			data:{
				status:status,
				productId:id,
			},
			type:"post",
			dataType:"json",
			success:function(data){
				if(data.header.success == true){
					showDialog("商品状态修改成功","toast","","");
					setTimeout(function(){
						toPage(0);
					},1000);
				}
			}
		});
	}
	
	function showStautsOperate(status){
		if(status == "T"){
			return "下架";
		}else{
			return "上架";
		}
	}
	
	function showStatus(status){
		if(status == "T"){
			return "展示中";
		}else{
			return "已下架";
		}
	}
	
	
	function showText(txt){
		if(txt){
			return txt;
		}else{
			return "";
		}
	}
	function showProductDetail(id){
		window.location=toServerPageUrl("/bm/product/productDetail.do?productId=" + id);
	}
	$(document).ready(function(){
		toPage(0);
	});
	
	</script>
	
</head>
<body>


<div class="container" style="width:100%;">

	<div class="row" style="border:0;margin-top:-10px;">
		<div class="col-sm-12">
			<h3 class="zs-iframe-title">商品管理</h3>
		</div>
	</div>

	<div class="row" style="margin-top:10px;">
	<div class="col-sm-1"  style="padding-left:0px;text-align:right;">
	<label for="name" style=" padding-top:7px;">名称</label>
	</div>
	<div class="col-sm-2"  style=" padding-left:0px;">
	<input type="text" id="name" name="name" class="form-control" style="float:left" >
	</div>
	<div class="col-sm-1"  style="padding-left:0px;text-align:right;">
	<label for="pClass" style="padding-top:7px;">状态</label>
	</div>
	<div class=" col-sm-3"  style="">
	
	<input type="hidden" id="shopId" value="<%=shopId %>">
	<select class="form-control zs-select-input" name="status" id='status' >
	<option value='' >请选择商品状态</option>
	<option value='T'  selected = "selected">出售中</option>
	<option value='F' >已下架</option>
	</select>
	</div>
	<div class=" col-sm-2" align="left">
	<button type="button" class="btn btn-default zs-btn-default " id="queryBtn" onclick="toPage(0)">查询</button>
	</div>
	<div class=" col-sm-3" align="right">
	<button type="button" class="btn btn-default zs-btn-green" onclick="addProduct()">新增</button>
	</div>
	</div>

</div>

<div style="margin-top:10px;">
	<div class="col-sm-12">
	 <table class="table table-hover">
   		<thead>
          <tr>
			<th width="100">商品编号</th>
            <th width="160">封面</th>
            <th width="200" style="text-align:left">商品名称</th>
            <th width="300" style="text-align:left">商品描述</th>
            <th width="100">单价</th>
            <th width="100">库存</th>
			<th width="130">状态</th>
            <th width="147">操作</th>
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





<script>


</script>       
	
</body>
