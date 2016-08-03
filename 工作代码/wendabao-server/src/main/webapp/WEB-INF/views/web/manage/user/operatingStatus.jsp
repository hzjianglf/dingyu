<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.handany.bm.model.BmShop"%>
<%@page import="com.handany.bm.model.BmPicture"%>
<%@page import="java.lang.String"%>
<%@page import="java.util.*"%>
<%@page import="com.handany.base.common.Constants"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
BmShop shop = (BmShop)request.getAttribute("shop");
String tokenId = (String)request.getAttribute("tokenId");

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
    <title>采供网</title>
	<%@include file="/common.jsp" %>
   
<meta content="" name="description"/>
<meta content="" name="author"/>
	<link rel="stylesheet" href="<%=basePath %>/css/mall.css">
</head>


	<div class="container-fluid">

	<div class="row" style="border:0;margin-top:-10px;">
	<div class="col-sm-12">
	<h3 class="zs-iframe-title">营业状态设置</h3>
	</div>
	</div>
		<%--<nav class="navbar navbar-default navbar-fixed-top row" style="border:0" >--%>
	  <%--<ol class="breadcrumb">--%>
	  <%--<li style="*float:left"><a href="#">个人中心</a></li>--%>
	  <%--<li style="*float:left"><a href="#">营业状态设置</a></li>--%>
	  <%--</ol>--%>
	<%----%>
	<%--</nav>--%>
	
	<div id="include" style="margin-top:10px;">
	
	
	<div id="workModel1" class="container-fluid" style="padding-top:20px;">			
		<div class="row">			
			<div class="col-sm-2 col-md-2"><label  style="padding-top:7px;float:right" >营业状态</label></div>
			<div class="col-sm-2 col-md-2">
				<select class="form-control" id="workStatus">
					<option value="3" <%="3".equals(shop.getStatus()) ? "selected" : "" %>>营业中</option>
			  		<option value="4" <%="4".equals(shop.getStatus()) ? "selected" : "" %>>休息中</option>						  	  
				</select>
			</div>
			<div class="col-sm-2 col-md-2"><button class="btn btn-default zs-btn-default" type="button" onclick="saveWorkStatus()">保存</button></div>
		</div>

	</div>
</div>

<input type="hidden" id="tokenId" value="<%=tokenId %>" />
<input type="hidden" id="shopId" value="<%=shop.getId() %>" />
<input type="hidden" id="picPath" value="<%=shop.getPicUrl() %>" />

<script>
var tokenId = $("#tokenId").val();
var shopId = $("#shopId").val();

	function saveWorkStatus(){
		var status=$("#workStatus").find("option:selected").val();
		$.ajax({
			url:toServerUrl("/bm/shop/updateWorkStatus.do"),
			data:{
				"status":status,
			},
			type:"post",
			dataType:"json",
			success:function(data){
				if(true == data.header.success){
					alert("修改工作状态成功！");
				}else{
					alert("修改失败！");
				}
				
				
			}
			
		});
	}
	
</script>
