<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.handany.rbac.model.PmUser"%>
<%@page import="java.lang.String"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
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
    <title></title>
	<%@include file="/common.jsp" %>
    
<meta content="" name="description"/>
<meta content="" name="author"/>
	<link rel="stylesheet" href="<%=basePath %>/css/custom.css">
</head>
<body>
	<div class="container-fluid">
	<div class="row" style="border:0;margin-top:-10px;">
	<div class="col-sm-12">
	<h3 class="zs-iframe-title">意见反馈</h3>
	</div>
	</div>
	<%--<nav class="navbar navbar-default navbar-fixed-top row" style="border:0" >--%>
	  <%--<ol class="breadcrumb">--%>
	  <%--<li style="*float:left"><a href="#">个人中心</a></li>--%>
	  <%--<li style="*float:left"><a href="#">更换手机号</a></li>--%>
	  <%--</ol>	--%>
	<%--</nav>--%>
	
		<!-- <h1>个人中心</h1>
		<hr style="height:1px;border:none;border-top:1px solid #555555;" /> -->
		<%--<div class="row" style="margin-top:70px;">--%>
			<%--<div class="col-md-6 text-center mfont"><label  style="padding-top:5px;float:center" >意见反馈</label></div>--%>
		<%--</div>--%>
		<%--<br />--%>
		<div class="row" style="margin-top:10px;">
			<div class="col-md-6 col-sm-6 text-center ">
				<textarea id="opinion"  style="width:600px; height:200px;"  placeholder="欢迎写下您的宝贵意见！"  ></textarea><span style="color:red;" id="extendFont">只能输入125个汉字</span>
			</div>
		</div><br />
		<div class="row">
			<div class="col-md-6  col-sm-6 text-center">
				<button class="btn btn-default zs-btn-default" onclick="submitFeedBack()" style="float:right;">提交</button>
			</div>
		</div>
	
	</div>
	<input type="hidden" id="tokenId" value="<%=tokenId %>">
<script type="text/javascript">
$("#extendFont").hide();
function submitFeedBack(){
	var tokenId = $("#tokenId").val();
	var opinion = $("#opinion").val();
	if(opinion.length > 125){
		$("#extendFont").show();
		return;
	}
	$.ajax({
		url:toServerUrl("/pm/feedback/save.do"),
		data:{
			"opinion":opinion,
		},
		type:"post",
		dataType:"json",
		success:function(data){
			if(data.header.success == true){
				$("#extendFont").hide();
				alert("提交成功！");
			}
		}
		
	});
}


</script>
	
</body>
