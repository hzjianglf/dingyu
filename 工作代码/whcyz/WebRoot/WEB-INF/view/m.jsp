<%@page import="com.whcyz.model.Article"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html class="no-js">
<head>
<base href="<%=basePath%>">
<meta charset="utf-8">
<title>威海创业者|本土创业者最权威交流分享平台</title>
<meta name="keywords" content="威海创业，创业组织，威海融资，威海创业联盟，威海创业资讯">
<meta name="description"
	content="威海创业者是威海当地的创业资讯、活动交流、资源分享的平台，为创业公司、创业者提供展示及推广服务，为投资商与项目建立合作机会，旨在帮助创业者更好的整合资源创造价值。">
<meta name="content-type" content="text/html; charset=UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<meta name="renderer" content="webkit">
<meta http-equiv="Cache-Control" content="no-siteapp" />
<script type="text/javascript">
if(navigator.platform.indexOf('Win32')!=-1||navigator.platform.indexOf('Win64')!=-1){ 
    window.location.href="<%=basePath%>";
 } 
</script>
<link rel="icon" type="image/png" href="assets/i/favicon.ico">
<link rel="stylesheet" href="assets/css/bootstrap.min.css">
<link rel="stylesheet" href="assets/css/bootstrap-theme.min.css">
<link rel="stylesheet" type="text/css" href="assets/css/app.css">
<link rel="stylesheet" type="text/css" href="assets/css/m.css">
<script src="assets/js/ie10-viewport-bug-workaround.js"></script>
<script src="assets/js/ie-emulation-modes-warning.js"></script>
<!--[if lt IE 9]>
	  <script src="assets/js/html5shiv.min.js"></script>
	  <script src="assets/js/respond.min.js"></script>
	<![endif]-->
<script src="assets/js/jquery.js"></script>
<script src="assets/js/bootstrap.min.js"></script>
<script src="assets/js/jquery.cookie.js"></script>
<script src="assets/js/app.js"></script>
</head>
<body>
<div class="box"><div class="shadowfont tcenter font-35 pd10">威海创业者</div></div>
	<!-- 中间部分 -->
	<div class="main">
		<div class="leftbox clearfix">
			<div class="articlediv">
				<ul>
					<c:forEach items="${articles }" var="article" varStatus="index">
						<li class="shadowarticlefont">
							<table class="box">
								<tr>
									<td style="width:110px;vertical-align: top;padding-right: 15px;"><a
										href="article/mdetail/${article.category }-${article.id}"><img
											class="lazy" src="assets/css/imgs/news.png.thumb.jpg" width="100" height="100"
											data-original='${article.imgUrl==null?"assets/css/imgs/news.png":article.imgUrl}.thumb.jpg'  /></a>
									</td>
									<td>
										<div class="atcontent tleft">
											<div class="atitle">
												<a href="article/mdetail/${article.category }-${article.id}">${article.title }</a>
											</div>
											<div class="authorandtime">
												<span>${article.author }</span> <em><fmt:formatDate
														value="${article.postTime }"
														pattern='yyyy/MM/dd HH:mm:ss' /></em>
											</div>
											<div class="content"><a href="article/mdetail/${article.category }-${article.id}">${article.smcontent }</a></div>
										</div>
									</td>
								</tr>
							</table>
						</li>
					</c:forEach>
				</ul>
			</div>
			<form action="" method="post" id="pageForm">
				<input type="hidden" name="pager.currentPage" id="currentPage" />
			</form>
		</div>
	</div>
	<div class="clearfix"></div>
	<%@include file="common/mfooter.html"%>
</body>
</html>