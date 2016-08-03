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
	<link rel="stylesheet" href="<%=basePath %>/css/mall.css">
</head>


	<div class="container-fluid">
	<div class="row" style="border:0;margin-top:-10px;">
	<div class="col-sm-12">
	<h3 class="zs-iframe-title">修改密码</h3>
	</div>
	</div>
	<%--<nav class="navbar navbar-default navbar-fixed-top row" style="border:0" >--%>
	  <%--<ol class="breadcrumb">--%>
	  <%--<li style="*float:left"><a href="#">个人中心</a></li>--%>
	  <%--<li style="*float:left"><a href="#">修改密码</a></li>--%>
	  <%--</ol>--%>
	<%----%>
	<%--</nav>--%>
		<!-- <h1>个人中心</h1>
		<hr style="height:1px;border:none;border-top:1px solid #555555;" />
		
		<h2>基础信息</h2>
		<ul class="nav nav-tabs">		  
		  <li id="changePwd" role="presentation"><a href="javascript:pwdActive()">修改密码</a></li>		  
		</ul> -->
	<!-- 	<ul class="list-inline">
		  <li><h4><a href="#" class="active">基础信息</a></h4></li>
		  <li><h4><a href="#">修改密码</a></h4></li>
		  <li><h5><a href="#">更换手机号</a></h5></li>
		</ul> -->
	<!--  -->
	<div id="include">
		
	<!-- 修改密码  -->
	<div id="pwd" class="container-fluid" style="padding-top:20px;">

		<div class="row">			
			<div class="col-sm-2 col-md-2 "><label  style="padding-top:5px;float:right" >当前密码</label></div>
			<div class="col-sm-4 col-md-4"><input type="password" class="form-control" id="oldPwd" name="oldPwd" /></div>
			<div class="col-sm-4 col-md-4"></div>
		</div><br/>
		
		<div class="row">
			<div class="col-sm-2 col-md-2 "><label  style="padding-top:5px;float:right" >新密码</label></div>
			<div class="col-sm-4 col-md-4"><input type="password" class="form-control" id="newPwd" name="newPwd" "/></div>
			<div class="col-sm-4 col-md-4"><span id="checkPwdForm" style="color:red;">6-16位字母或数字！</span></div>
		</div><br/>
		<div class="row">
			<div class="col-sm-2 col-md-2 "><label  style="padding-top:5px;float:right" >确认密码</label></div>
			<div class="col-sm-4 col-md-4" >
			  <input type="password" class="form-control" name="sureNewPwd" id="sureNewPwd"/>
			</div>
			<div class="col-sm-4 col-md-4"><span id="turePassword" style="color:red;">密码不一致！</span></div>
		</div><br />
		<div class="row">
			<div class="col-sm-2 col-md-2 "></div>
			<div class="col-sm-4 col-md-4" >
			  <button type="button" class="btn btn-default zs-btn-default" style="float:right" onclick="saveNewPwd()">保存</button>
			</div>
			<div class="col-sm-4 col-md-4"></div>
		</div>
		 
		 
	</div>
	</div>

<input type="hidden" id="tokenId" value="<%=tokenId %>" />
<input type="hidden" id="userId" value="<%=user.getId() %>" />

<script>

	$("#checkPwdForm").hide();
	$("#turePassword").hide();
	function saveNewPwd(){
		/* 密码校验  */
		var regModel = "^[A-Za-z0-9]+$";
		var reg = new RegExp(regModel);
		$("#checkPwdForm").hide();
		if(!reg.test($("#newPwd").val()) || $("#newPwd").val().length > 16 || $("#newPwd").val().length < 6){
			$("#checkPwdForm").show();
			return;
		}
		var oldPwd = hex_md5($("#oldPwd").val());
		var newPwd = hex_md5($("#newPwd").val());
		var sureNewPwd = hex_md5($("#sureNewPwd").val());
		var tokenId = $("#tokenId").val();
		var userId = $("#userId").val();
		
		if(newPwd != sureNewPwd){
			$("#turePassword").show();
			return false;
		}else{
			$("#turePassword").hide();
		}
		
		$.ajax({
			url:toServerUrl("/pm/user/saveNewPwd.do"),
			data:{
				"oldPwd":oldPwd,
				"newPwd":newPwd,
				"userId":userId,
			},
			type:"post",
			dataType:"json",
			success:function(data){
				if(true == data.header.success){
					alert(data.header.message);
				}else{
					alert(data.header.message);
				}
			}
			
		}); 
	}

	
	
</script>
