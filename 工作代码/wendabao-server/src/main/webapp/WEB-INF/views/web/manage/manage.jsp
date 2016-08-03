<!DOCTYPE html>
<%@page import="com.handany.rbac.model.PmUser"%>
<%@page import="com.handany.rbac.common.UserContextManager"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path;
	String tokenId = request.getParameter("tokenId");
	String role = (String) request.getAttribute("role");

	PmUser user = UserContextManager.getLoginUser();
%>
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en" class="no-js">
<!--<![endif]-->
<!-- BEGIN HEAD -->
<head>
<%@include file="/common.jsp"%>

<meta charset="utf-8" />
<!-- 为了让 IE 浏览器运行最新的渲染模式下，建议将此 <meta> 标签加入到你的页面中：-->
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<!--将下面的 <meta> 标签加入到页面中，可以让部分国产浏览器默认采用高速模式渲染页面：-->
<meta name="renderer" content="webkit">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->

<title>问答宝</title>


<title></title>
<!-- BEGIN GLOBAL MANDATORY STYLES -->
<style type="text/css">
.menu {
	padding: 0px;
	margin: 10px;
	border: 0px solid #000;
	font-size: 14px;
	border-top: 1px solid #ccc;
	border-bottom: 1px solid #ccc;
	font-family:  Microsoft Yahei, sans;
}

.panel {
	margin-bottom: 0px;
	border-radius: 0px;
	border: 0px;
	border-left: 1px solid #ccc;
	border-right: 1px solid #ccc;
}

.panel-default>.panel-heading {
	color: #080808;
	border-top-left-radius: 0px;
	border-top-right-radius: 0px;
	border-left: solid 10px;
	cursor: pointer;
}

.nav > li:hover {
	text-decoration: none;
	color: #000;
}

.nav > li > a {
	border-radius: 0px;
	color: #080808;
	padding: 10px 25px;
}
</style>
<script type="text/javascript">
	$(function() {
		/*    $("#changeIframe").load(function(){
<%--var mainheight = $(this).contents().find("body").height()+30;--%>
	$(this).height(2000);
		   }); */

//		$("#changeIframe")
//				.attr("src", toServerPageUrl("/bm/manage/console.do"));
	});
</script>
</head>

<body style="height: 100%; min-width: 1280px;">
	<div class="container" style="width: 100%; margin: 0px;">
		<div class="row"
			style="border-bottom: 1px solid #eaeaea; height: 90px">
			<div class="col-lg-1"></div>
			<div class="col-lg-10" style="padding: 0px;">
				<img src="../../image/icon.png"
					style="height: 80px; width: auto; float: left; margin-right: 5px; margin-top: 5px;">

				<p class="text-left"
					style="margin-top: 10px;; color: #000; font-size: 20px;">
					<span style="font-weight: bold;">问答宝</span><br/><span>管理控制台</span> <span
						style="float: right; padding-top: 5px; font-size: 14px;"><a href="javascript:goAction('/pm/user/userCenter.do')"
						><%=user.getName()%></a> | <a
						href="javascript:logout()">退出</a></span>
				</p>


			</div>
			<div class="col-lg-1"></div>
		</div>

		<div class="row">

			<div class="col-lg-1"></div>

			<div class="col-lg-2 col-sm-2 col-xs-2 menu">

				<div class="panel panel-default">
	
					<ul class="nav nav-pills nav-stacked">
						<li role="presentation"><a
							href="javascript:goAction('/bm/manage/console.do')">首页</a></li>
					</ul>
				</div>

				<div class="panel panel-default">
					<div class="panel-heading" id="sysAdmin" style="border-left-color: #ec971f">注册用户管理</div>

					<ul class="nav nav-pills nav-stacked sysAdmin">
						<li role="presentation"><a
							href="javascript:goAction('/bm/student/getStudentList.do')">注册学生管理</a></li>
						<li role="presentation"><a href="javascript:goAction('/bm/teacher/getTeacherList.do')">注册教师管理</a></li>
						<% if("4".equals(user.getUserType())){ %>
						<li role="presentation"><a
							href="javascript:goAction('/bm/agent/agentList.do')">代理商管理</a></li>
							<%} %>
						<li role="presentation"><a
							href="javascript:goAction('/bm/classroom/getClassroomList.do')">教室管理</a></li>
					</ul>
				</div>
				<div class="panel panel-default">
					<div class="panel-heading" id="sysAdmin" style="border-left-color: #ec971f">活动管理</div>

					<ul class="nav nav-pills nav-stacked sysAdmin">
						<li role="presentation"><a
							href="javascript:goAction('/bm/sales_promotion/addPromotion.do')">新建活动</a></li>
						<li role="presentation"><a href="javascript:goAction('/bm/sales_promotion/promotionMgt.do')">活动管理</a></li>
						<li role="presentation">
					</ul>
				</div>
                                <div class="panel panel-default">
					<div class="panel-heading" id="sysAdmin" style="border-left-color: #ec971f">销售项目</div>

					<ul class="nav nav-pills nav-stacked sysAdmin">
						<li role="presentation"><a
							href="javascript:goAction('/bm/qa_time/addQaTime.do')">新建套餐</a></li>
						<li role="presentation"><a href="javascript:goAction('/bm/qa_time/qaTimeList.do')">套餐管理</a></li>
						<li role="presentation">
					</ul>
				</div>
				<div class="panel panel-default">
					<div class="panel-heading" id="sysAdmin" style="border-left-color: #ec971f">系统管理</div>

					<ul class="nav nav-pills nav-stacked sysAdmin">
						<li role="presentation"><a
							href="javascript:goAction('/pm/feedback/queryList.do')">查看意见反馈</a></li>
						<li role="presentation"><a href="javascript:goAction('/bm/busiParam/selectAll.do')">系统参数设定</a></li>
						<li role="presentation"><a href="javascript:goAction('/sys/sysInfo/sysInfoList.do')">客户端版本管理</a></li>
					</ul>
				</div>
				
				<div class="panel panel-default">
						<!-- Default panel contents -->
						<div class="panel-heading" id="personerCenter" style="border-left-color: #ec971f">个人中心</div>
	
						<ul class="nav nav-pills nav-stacked personerCenter">
							<li role="presentation"><a
								href="javascript:goAction('/pm/user/userCenter.do')">基本信息</a></li>
							<li role="presentation"><a
								href="javascript:goAction('/pm/user/password.do')">修改密码</a></li>
							<li role="presentation"><a
								href="javascript:goAction('/pm/feedback/feedback.do')">意见反馈</a></li>
						</ul>
				</div>
			</div>


			<div class="col-lg-8 col-sm-8 col-xs-8"
				style="margin: 10px 0px; padding: 0px;">
				<!-- 打开时默认的界面 -->

				<iframe id="changeIframe" frameborder="0" scrolling="yes"
					marginheight="0" marginwidth="0" src=''
					style="height: 1500px; width: 100%"> </iframe>

			</div>
			<div class="col-lg-1 col-sm-1"></div>
		</div>
	</div>
	<script>
		// 这两个变量是为了记住当前子页面，防止刷新后又回到首页
		var currentAction = "";
		var localAction = sessionStorage.getItem("iframeAction");

		if (null == localAction || "" == localAction || "null" == localAction) {
			currentAction = "/bm/manage/console.do";
		} else {
			currentAction = localAction;
		}
		function goAction(url) {
			sessionStorage.setItem("iframeAction", url);
			document.getElementById("changeIframe").src = toServerPageUrl(url);
		}

		$("#changeIframe").attr("src", toServerPageUrl(currentAction));


		$(".panel-heading").on("click", function() {
			var id = $(this).attr("id");
			$(".nav-stacked." + id).slideToggle();
		});
		
		function logout(){
			$.getJSON(toServerUrl("/pm/user/logout.do"),function(){});
			window.top.location.href="<%=basePath%>/login.html";
		}
	</script>

</body>
</html>

