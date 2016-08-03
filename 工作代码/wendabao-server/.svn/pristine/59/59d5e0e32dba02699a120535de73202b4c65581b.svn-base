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
	
	<title></title>
	<!-- BEGIN GLOBAL MANDATORY STYLES -->
	<%@include file="/common.jsp" %>
	<script type="text/javascript"
	src="<%=basePath%>js/admin/regionSearch.js"></script>
	<link rel="stylesheet" href="<%=basePath %>/css/custom.css">
	<style>
		
	</style>
</head>
<body>


<div class="container" style="width:100%;">

	<div class="row" style="border:0;margin-top:-10px;">
		<div class="col-sm-12">
			<h3 class="zs-iframe-title">代理商列表</h3>
		</div>
	</div>

	<div class="row" style="margin-top:10px;">
		<div class="col-sm-2"  style="padding-left:0px;text-align:right;">
			<label for="status"  style=" padding-top:7px;">代理商名称</label>
		</div>
		<div class=" col-sm-3"  style="">
			<input type="text" id="name" class="form-control"/>
		</div>
		<div class=" col-sm-1 col-sm-offset-4" align="left">
			<button type="button" class="btn btn-default zs-btn-default" id="queryBtn" onclick="toPage(0)">查询</button>
		</div>
		<div class=" col-sm-1 " align="left">
			<button type="button" class="btn btn-default zs-btn-default" id="addBtn" >添加</button>
		</div>
	</div>
	<div class="row" style="margin-top:10px;margin-left:5px;">
		<div class="col-sm-2"  style=" padding-left:0px;text-align:right;">
			<label  style=" padding-top:7px;">地区</label>
		</div>
		<div class="col-sm-3" style=" padding-left:0px;"><select class="form-control zs-select-input" id='provinceSearch' onchange='searchConditionCity()'></select></div>
        <div class="col-sm-3" style=" padding-left:0px;"><select class="form-control zs-select-input" id='citySearch' onchange='searchConditionCounty()'></select></div>
        <div class="col-sm-3" style=" padding-left:0px;"><select class="form-control zs-select-input" id='countySearch'></select></div>
	</div>
</div>

<div style="margin-top:10px;">
	<div class="col-sm-12">
	 <table class="table table-hover">
   		<thead>
          <tr>
            <th width="110">代理商名称</th>
            <th width="110">代理区域</th>
            <th width="110">登录账号</th>  
            <th width="110">电话</th>
			<!-- <th width="110">状态</th> -->
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
<script>
	var region1A;
	var region2A;
	var region3A ;
	$(function(){
		$("#addBtn").bind("click",function(){
			window.location=toServerPageUrl("/bm/agent/agentDetail.do");
		});
		region1A = PermanentCache.getItem("region1A");
		region2A = PermanentCache.getItem("region2A");
		region3A = PermanentCache.getItem("region3A");
		PermanentCache.removeItem("region1A");
		PermanentCache.removeItem("region2A");
		PermanentCache.removeItem("region3A");
		if(!region1A){
			region1A = 0;
		}
		if(!region2A){
			region2A = 0;
		}
		if(!region3A){
			region3A = 0;
		}
		setDefaultSelect(region1A,region2A,region3A);
		toPage(0);
	})
	function toPage(start){
		var name = $("#name").val();
		var region1 = $("#provinceSearch").val();
		var region2 = $("#citySearch").val();
		var region3 = $("#countySearch").val();
		if($("#queryBtn").hasClass("load")){
			return;
		}
		$("#queryBtn").addClass("load").html(".....");
		
		if(start == undefined || start == null){
			start = 0;
		}
		if(!region1){
			region1 = "0";
		}
		if(!region2){
			region2 = "0";
		}
		if(!region3){
			region3 = "0";
		}
		
		sendRequest({region1:region1,region2:region2,region3:region3,name:name,start:start}, "/bm/agent/queryAgent.do", function(json){
			var list = json.data.list;
			$("#data_content").empty();
			var printBtnHtml = "";
			for(var i=0;i<list.length;i++){
				var item = list[i];
				region1A = region1;
				region2A = region2;
				region3A = region3;

				var address1 = item.region1?getProvinceName(item.region1):"";
				var address2 = item.region2?getCityName(item.region2):"";
				var address3 = item.region3?getCountryName(item.region3):"";
				$("#data_content").append(
				["    <tr agentId=\'"+item.id+"\' userId =\'"+item.userId+"\'  >           ",
				 "			        <td align=\"center\">"+undefinedHandler(item.name)+"</td>",
				 "			        <td align=\"center\">"+ address1+address2+address3+"</td>",
				 "			        <td align=\"center\">"+undefinedHandler(item.user?item.user.loginName:"")+"</td> ",
				 "			        <td align=\"center\">"+undefinedHandler(item.tel)+"</td>",
				 "			        <td align=\"center\">",
				 "			        	<a style=\"display:block; \"class=\"detail\")\">查看详情</a>",
				 "			        </td>",
				 "			      </tr>"].join("")
				);
			}
			$("#navArea").html(showNav(json.data));
			$("#queryBtn").removeClass("load").html("查询");
			bindDetail();
		}, function(){
			showDialog("查询失败", "toast", "", "");
			$("#queryBtn").removeClass("load").html("查询");
		});
	}
	
	function bindDetail(){
		$(".detail").bind("click",function(){
			PermanentCache.setItem("region1A", region1A);
			PermanentCache.setItem("region2A", region2A);
			PermanentCache.setItem("region3A", region3A);
			var _this = $(this);
			var agentId = _this.parent().parent().attr("agentId");
			var userId = _this.parent().parent().attr("userId");
			window.location = toServerPageUrl("/bm/agent/agentDetail.do?agentId=" +agentId +"&userId"+userId);
		});
		
	}
</script>       
</body>
