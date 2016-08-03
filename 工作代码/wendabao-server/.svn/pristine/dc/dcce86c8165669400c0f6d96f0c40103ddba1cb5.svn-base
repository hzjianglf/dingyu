<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.handany.rbac.model.PmUser"%>
<%@page import="com.handany.base.common.ApplicationConfig"%>
<%@page import="java.lang.String"%>
<%@page import="com.handany.base.common.Constants"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	PmUser user = (PmUser) request.getAttribute("user");
	String tokenId = (String) request.getAttribute("tokenId");
	String picPath = Constants.IMAGE_SERVER;
	String imageServer = ApplicationConfig.getConfig("image_server");

	String studentId = request.getParameter("id");
%>
<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en" class="no-js">
<!--<![endif]-->
<!-- BEGIN HEAD -->
<head>
<%@include file="/common.jsp" %>
<script src="<%=basePath%>js/region.js" type="text/javascript"></script>
<title>学生详情</title>
<style type="text/css">
.contentContainer {
	margin-top: 15px;
}

.contentContainer .form-tr {
	margin-top: 15px;
}

.contentContainer .form-title {
	text-align: right;
	padding-top: 5px;
}

.td_input {
	border: white;
	align: center;
}

td {
	width: 100px;
}

img {
	width: 200px;
	height: 200px;
	border: none;
	vertical-align: bottom;
}

td {
	text-align: center;
}
</style>
</head>
<div class="container-fluid">
	<div class="row" style="border: 0; margin-top: -10px;">
		<div class="col-sm-12">
			<h3 class="zs-iframe-title">学生详情</h3>
		</div>
	</div>
	<ul class="nav nav-tabs">
		<li role="presentation" class="active" id="tab-base"><a
			href="javascript:showContainer('tab-base','baseContainer')">基本信息</a></li>
		<li role="presentation" id="tab-record"><a
			href="javascript:showContainer('tab-record','recordContainer')">购买记录</a></li>
	</ul>
	<div class="container contentContainer" id="baseContainer"
		style="display: block;">
		<form class="form-horizontal" role="search">
			<div class="form-group">
				<label class="col-sm-2 col-md-2 control-label">头像</label>
				<div class=" col-sm-10 col-md-10">
					<img  class="form-group" src="<%=basePath%>image/logo-1.png" id="userHead"/>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 col-md-2 control-label">用户名</label>
				<div class=" col-sm-10 col-md-10">
					<span type="text" class="form-group" id="username"></span>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 col-md-2 control-label">姓名</label>
				<div class="col-sm-10 col-md-10">
					<span rows="5" class="form-group" id="name"></span>
				</div>
			</div>

			<div class="form-group ">
				<label class="col-sm-2 col-md-2 control-label">年级</label>

				<div class="col-sm-10 col-md-10 ">
					<span rows="5" class="form-group" id="grade"></span>
				</div>
			</div>
			<div class="form-group ">
				<label class="col-sm-2 col-md-2 control-label">地区</label>

				<div class="col-sm-10 col-md-10 ">
					<span rows="5" class="form-group" id="area"></span>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 col-md-2 control-label">学校</label>
				<div class="col-sm-10 col-md-10 ">
					<span rows="5" class="form-group" id="school"></span>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 col-md-2 control-label">剩余时长</label>
				<div class="col-sm-10 col-md-10 ">
					<span rows="5" class="form-group" id="time"></span>
				</div>
			</div>
		</form>

	</div>

	<div class="container contentContainer" id="recordContainer" style="display: none;"></div>

	<script type="text/javascript">
		function showContainer(tab, container) {
			$("[role='presentation']").each(function(i) {
				$(this).removeClass("active");
			});
			$("#" + tab).addClass("active");
			$(".contentContainer").each(function(i) {
				$(this).hide();
			});
			$("#" + container).show();
		}
		var studentId = '<%=studentId%>'
		$(function(){
			sendRequest({id:studentId}, "/bm/student/queryById.do", 
				function(json){
						var student = json.student;
						$("#username").text(student.user?student.user.mobile:"暂无");
						$("#name").text(student.name?student.name:"暂无");
					 	$("#grade").text(student.grade?student.grade:"暂无");
						$("#area").text((undefinedHandler(student.region1Txt)+
								undefinedHandler(student.region2Txt)+undefinedHandler(student.region3Txt))?
										(undefinedHandler(student.region1Txt)+undefinedHandler(student.region2Txt)+undefinedHandler(student.region3Txt)):"暂无"
								);
						$("#school").text(student.school?teacher.school:"暂无");
						$("#time").text(second2Min(student.qaTime?student.qaTime:"0"));
						if(student.userPic){
							$("#userHead").attr("src",'<%=imageServer%>'+student.userPic);
						}
						
					},function(json){
						
					});
		})
		
	</script>