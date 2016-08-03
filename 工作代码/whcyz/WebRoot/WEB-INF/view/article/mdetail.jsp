<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
   <!DOCTYPE html>
	<html class="no-js">
  <head>
    <base href="<%=basePath%>">
	<meta charset="utf-8">
    <title>${article.title }—威海创业者|本土创业者最权威交流分享平台</title>
    <meta name="keywords" content="威海创业，创业组织，威海融资，威海创业联盟，威海创业资讯">
    <meta name="description" content="威海创业者是威海当地的创业资讯、活动交流、资源分享的平台，为创业公司、创业者提供展示及推广服务，为投资商与项目建立合作机会，旨在帮助创业者更好的整合资源创造价值。">
    <meta name="content-type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
	<meta name="renderer" content="webkit">
	<meta http-equiv="Cache-Control" content="no-siteapp" />
	<link rel="icon" type="image/png" href="assets/i/favicon.ico">
	<script src="assets/js/jquery.js"></script>
    <link rel="stylesheet" type="text/css" href="assets/css/articlemdetail.css">
    <script type="text/javascript" src="assets/js/jquery.lazyload.min.js"></script>
    <script>
    if(navigator.platform.indexOf('Win32')!=-1||navigator.platform.indexOf('Win64')!=-1){ 
        window.location.href="<%=basePath%>article/detail/${category}-${article.id}";
     } 
    $(function(){
    	 //img lazyload 
		 $(window).on("load",function(){
			 $("img.lazy").show().lazyload({ 
			      effect:"fadeIn", //加载图片使用的效果(淡入)  
			      effect_speed: 1000
			});
		 })
		  $.ajax({
				 type:"get",
				 url:"article/addreadcount/${article.id}",
				 datatype:"json",
				 success:function(result){
					 if(result.success){
						 $("#readCount").text(Number($("#readCount").text())+1);
					 }
				 }
			 });
});
  </script>
   </head>
   <body>
 <!-- 中间部分 -->
  <div class="main">
        <div class="articledetail">
            <span class="ad-title">${article.title }</span>
            <div class="ad-author"><em><fmt:formatDate value="${article.postTime }" pattern='yyyy/MM/dd HH:mm'/></em><span>来源：${article.author }</span><span>阅读[<span id="readCount" style="padding:0;margin: 0;">${article.readCount}</span>]<span><span>评论[${article.commentCount }]<span></div>
            <div class="ad-contentsm">
            <span style="font-weight: bold;">摘要：</span><span class="content">${article.smcontent }</span>
            </div>
            <div class="ad-content">
            <c:if test="${not empty article.imgUrl }">
            <p class="tcenter mt10">
            <img alt="" data-original="${article.imgUrl }"  class="lazy" >
            </p>
            </c:if>
            <p class="mt10">${article.content }</p>
            <p class="mt10 tcenter" style="color:#3B27D3;font-size:16px;">威海创业者</p>
            <p class="tcenter" style="color:#3B27D3;font-size:16px;">www.whcyz.com</p>
            <p class="tcenter" style="color:#3B27D3;font-size:16px;">本土创业者最权威交流分享平台</p>
            <p class="tcenter" style="color:#3B27D3;font-size:16px;">
             <img src="assets/css/imgs/qunqrcode.jpg" width="160">
             <img src="assets/css/imgs/qrcode.jpg" width="160">
            </p>
            <p class="tcenter" style="color:#3B27D3;font-size:16px;">扫一扫加入微信群、关注公众号whcyz001</p>
            </div>
        </div>
  </div>
  </body>
</html>