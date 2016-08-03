<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.handany.rbac.model.PmUser"%>
<%@page import="java.lang.String"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
PmUser user = (PmUser)request.getAttribute("user");
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

</head>


	<div class="container-fluid">
	<nav class="navbar navbar-default navbar-fixed-top row" style="border:0" >
	  <ol class="breadcrumb">
	  <li style="*float:left"><a href="#">个人中心</a></li>
	  <li style="*float:left"><a href="#">更换手机号</a></li>
	  </ol>
	
	</nav>
	<!-- 
		<h1>个人中心</h1>
		<hr style="height:1px;border:none;border-top:1px solid #555555;" />
		
		<h2>基础信息</h2>
		<ul class="nav nav-tabs">		 
		  <li id="changeMobile" role="presentation"><a href="javascript:mobileActive()">更换手机号</a></li>		  
		</ul> -->
	<!-- 	<ul class="list-inline">
		  <li><h4><a href="#" class="active">基础信息</a></h4></li>
		  <li><h4><a href="#">修改密码</a></h4></li>
		  <li><h5><a href="#">更换手机号</a></h5></li>
		</ul> -->
	<!--  -->
	<div id="include">
	
	<!-- 更换手机号  -->
	<div id="mobile" class="container-fluid" style="padding-top:70px;">
	
		<div class="row">			
			<div class="col-sm-1 col-md-1"><label  style="padding-top:5px;float:right" >旧手机号</label></div>
			<div class="col-sm-4 col-md-4"><input type="text" class="form-control" id="oldMobile" name="oldMobile"/></div>
			<div class="col-sm-4 col-md-4"></div>
		</div><br/>
		
		<div class="row">
			<div class="col-sm-1 col-md-1"><label  style="padding-top:5px;float:right" >验证码</label></div>
			<div class="col-sm-4 col-md-4"><input type="text" class="form-control" id="verifyCode" name="verifyCode"/></div>
			<div class="col-sm-4 col-md-4"><button class="btn btn-primary" onclick="getVerifyCode()">获取验证码</button></div>
		</div><br/>
		<div class="row">
			<div class="col-sm-1 col-md-1 "><label  style="padding-top:5px;float:right" >新手机号</label></div>
			<div class="col-sm-4 col-md-4">
			  <input type="text" class="form-control" name="loginName" id="newMobile" name="newMobile"/>
			</div>
			<div class="col-sm-4 col-md-4"></div>
		 </div><br />
		 <div class="row">
			<div class="col-sm-1 col-md-1 "></div>
			<div class="col-sm-4 col-md-4">
			  <button type="button" class="btn btn-primary" onclick="saveNewMobile()">保存</button>
			</div>
			<div class="col-sm-4 col-md-4"></div>
		 </div>
		 
	</div>
</div>

<input type="hidden" id="tokenId" value="<%=tokenId %>" />
<input type="hidden" id="userId" value="<%=user.getId() %>" />

<script>
var tokenId = $("#tokenId").val();
var userId = $("#userId").val();

	function getVerifyCode(){
		var oldMobile = $("#oldMobile").val();
		
		$.ajax({
			url:toServerUrl("/pm/user/getVerifyCode.do"),
			data:{
				"loginName":oldMobile,
				"sign":"2",
			},
			type:"post",
			dataType:"json",
			success:function(data){
				if(true == data.header.success){
					alert("验证码："+data.verifyCode);
				}else{
					alert("修改失败!");
				}
			}
			
		}); 
	}
	
	function saveNewMobile(){
		var oldMobile = $("#oldMobile").val();
		var verifyCode = $("#verifyCode").val();
		var newMobile = $("#newMobile").val();
		$.ajax({
			url:toServerUrl("/pm/user/saveNewMobile.do"),
			data:{
				"oldMobile":oldMobile,
				"verifyCode":verifyCode,
				"newMobile":newMobile,
			},
			type:"post",
			dataType:"json",
			success:function(data){
				if(true == data.header.success){
					alert("修改成功！");
				}else{
					alert("修改失败!");
				}
			}
			
		}); 
		
		
	}

	
</script>
