<!DOCTYPE html>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
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
<link href="../../web/css/product.css" rel="stylesheet" type="text/css">
<script src="../../web/js/product.js" charset="utf-8"></script> 

<style type="text/css">
.stk {
	margin: 0px;
	padding: 5px;
	background: #e5eecc;
	text-align: center;
	border: solid 1px #c3c3c3;
}
</style>
</head>
<body>

	<jsp:include page="top.jsp"></jsp:include>
	<div id="content">
		<div id="head">
			<img src="../../web/images/${product.productXimage }" height="50">
			<input value="${product.productId }" name="productId" id="productId" type="hidden"/>
		</div>
		<div class="ware_text">
			<div class="rate">
				<h1 class="ware_title">${product.productName }</h1>
				<span class="letterprice">售价: </span><span class="fontColor3"
					ectype="goods_price" style="text-decoration:line-through;">¥${product.price }</span><br> <span
					class="letterprice">会员价: </span><span class="fontColor3"
					ectype="goods_price">¥${product.cheapPrice }</span><br>
					<span>产品概述：${product.productOverview}</span><br> 
				销售情况: 售出 ${product.sellNum }件（<span class="c3b8">
				<strong>${appNum}</strong> </span> 条评论）<br>
			</div>
			<div class="handle">
				<ul>
					<li class="handle_title">购买数量:</li>
					<li><input type="text" class="text width1" name=""
						id="quantity" width="200px" value="1"> 件（库存<span class="stock"
						ectype="goods_stock">${product.inventoryNum }</span>件）</li>
				</ul>
			</div>
			<p class="stk">立刻购买</p>
			<div id="shopcarBuy" style="display:none">

					<div class="ware_cen">
							<h1>该商品已经在购物车中了</h1>
							<a class="enter"
								href="../../site/web/order!getShopping">现在去结算</a>
					</div>	

			</div>
			<div id="shopcarFail" style="display:none">

					<div class="ware_cen">
							<h1>加入购物车失败，请稍后再试！（请确定您是否已经一键激活会员）</h1>
							<a class="enter" href="javascript:failclose()">继续挑选</a>
					</div>
			</div>
			<div id="checkNum" style="display:none">

					<div class="ware_cen">
							<h1>购买数量超出库存量，请重新选取！</h1>
							<a class="enter" href="javascript:checkclose()">继续挑选</a>
					</div>
			</div>
			<div id="shopcar" class="ware_cen" style="display:none">
				<div>
					<div align="center" >
						<h1>商品已成功添加到购物车</h1> 
					</div>
					<div style="padding-top:10px;">
						<p class="ware_text_p">
							 购物车内共有 <span class="bold_num" id="prosum">0</span> 种商品 <!-- 共计 <span class="bold_mly">658.00</span> -->
						</p>
						<p class="ware_text_btn">
							<input type="submit" class="btn1" name="" value="查看购物车"
								onClick="location.href='order!getShopping'"> <input
								type="submit" class="btn2" name="" value="继续挑选商品"
								onClick="$('.ware_cen').css({'display':'none'});">
						</p>
					</div>
				</div>
			</div>
		</div>
		<ul class="user_menu">
			<li><a class="active" href="javascript:showUrl();"><span>商品详情</span>
			</a></li>
			<li><a class="active"  href="javascript:showApp()"><span>商品评价</span>
			</a></li>
		</ul>

		<div class="option_box">
			<div class="default" id="default" >
			<p style="line-height: 0px;font-size:16px; color: #333;">${product.productDetails}</p>
				<p>
					<s:iterator value="urlList" var="ul">
						<img src="../../web/images/${ul }" alt="商品详情">
					</s:iterator>
				</p>
			</div>
			<div id="appraise" >
			<div id ="apptable">	
			</div>
				<div align="center">
<a>共<span id="pageCount"></span>页<span id="listCount"></span>条记录，当前为第<input type="text" id="page" value="1" style="border: 0; width: 15px; background: #EEEEEE;"><!-- <span id="page"></span> -->页</a>
					<a id= "last" style="color:#000000;" href="javascript:lastApp()">上一页</a>
					<a id= "next" style="color:#000000;" href="javascript:nextApp()">下一页</a>
</div>
			</div>
		</div>
	</div>
	<input type="hidden" id="invent" value="${product.inventoryNum }" />
	<jsp:include page="bottom.jsp"></jsp:include>
</body>
</html>