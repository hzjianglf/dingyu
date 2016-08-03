<!DOCTYPE html>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<meta name="viewport"
	content="width=device-width,minimum-scale=1.0,maximum-scale=1.0">
<meta http-equiv="Content-Type" content="text/html;charset=utf-8">
<title>微商城</title>
<meta name="description" content="">
<meta name="keywords" content="">
<script src="../../web/js/jquery-1.8.3.js"></script>
<link href="../../web/css/shoppingstyle.css" rel="stylesheet" type="text/css">

</head>
<body>
<div id="content">
<jsp:include page="/web/top.jsp" />
<form method="post" action="../../site/web/order!getCannelOrderEnter?ordernum=${order.orderNum }" target="iframe_post">
	<div class="order_cancel_box" style="margin-top: 50px;">
	    <h1>您是否确定要取消以下订单？</h1>
	    <p>订单号:<span>${order.orderNum }</span></p>
	    <dl>
	        <dt>取消原因:</dt>
	        <dd>
	            <div class="li"><input type="radio" checked="" name="cancel_reason" id="d1" value="改选其他商品"> <label for="d1">改选其他商品</label></div>
	            <div class="li"><input type="radio" name="cancel_reason" id="d2" value="改选其他配送方式"> <label for="d2">改选其他配送方式</label></div>
	            <div class="li"><input type="radio" name="cancel_reason" id="d3" value="改选其他卖家"> <label for="d3">改选其他卖家</label></div>
	            <div class="li"><input type="radio" name="cancel_reason" flag="other_reason" id="d4" value="其他原因"> <label for="d4">其他原因</label></div>
	        </dd>
	        <dd id="other_reason" style="display:none">
	            <textarea class="text" id="other_reason_input" style="width:200px;" name="remark"></textarea>
	        </dd>
	    </dl>
	</div>
    <div class="btn">
        <input type="submit" id="confirm_button" class="btn1 enter" value="确认">
    </div>
    </form>
</div>
<jsp:include page="/web/bottom.jsp" />
</body></html>
