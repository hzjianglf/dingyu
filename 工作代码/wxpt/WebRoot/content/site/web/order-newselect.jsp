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
<script type="text/javascript">
                function getFukuan(){
                	var enter=${order.orderNum };
                	$.ajax({
                		type:"POST",
			          url:"../../site/manage/order-manage!getOrder",
					  data : {
					  	'orderNum':enter
					  },
					  success:function (){
					    $.messager.alert('消息', '付款成功');
			        		   window.location.href="/wxpt/site/web/order!getAllNewOrder";
			         }
                	
                	
                	})
                	
                }
            
                
                </script>
</head>
<body>
<div class="content">
<jsp:include page="/web/top.jsp" />
    <div class="particular">
        <div class="particular_wrap">
            <dl class="order_detail" style="margin-top: 50px;">
            	<dt class="til_font">订单详情</dt>
                <dt>订单状态&nbsp;:&nbsp;</dt>
                <s:if test="order.payType==0">
                	<dd>待付款</dd>
                </s:if>
                <s:if test="order.payType==1&&order.sendType==0">
                	<dd>待发货</dd>
                </s:if>
                <s:if test="order.payType==1&&order.sendType==1&&order.takeType==0">
                	<dd>待收货</dd>
                </s:if>
                <s:if test="order.payType==1&&order.sendType==1&&order.takeType==1">
                	<dd>已完成</dd>
                </s:if>
                <dt>订单号&nbsp;:&nbsp;</dt>
                <dd>${order.orderNum }</dd>
                <dt>下单时间&nbsp;:&nbsp;</dt>
                <dd>${order.orderTime }</dd>
            </dl>
            <dl class="order_info">
	            <dt class="til_font">卖家信息</dt>
	            <dt>店铺名&nbsp;:&nbsp;</dt>
                <dd>${enter.enterName }</dd>
                <dt>电话号码&nbsp;:&nbsp;</dt>
                <dd>${enter.enterPhone }</dd>
                <!-- <dt>QQ&nbsp;:&nbsp;</dt>
                <dd>800045239</dd>
                
                <dt>旺旺&nbsp;:&nbsp;</dt>
                <dd>-</dd>
                <dt>所在地区&nbsp;:&nbsp;</dt>
                <dd>中国	湖南	长沙	开福区</dd>
                <dt>手机号码&nbsp;:&nbsp;</dt>
                <dd>-</dd>
                <dt>MSN&nbsp;:&nbsp;</dt>
                <dd>-</dd> -->
                <dt>详细地址&nbsp;:&nbsp;</dt>
                <dd>${enter.enterAddress }</dd>
            </dl>
            <div class="ware_line">
                <div class="ware">
                	<s:iterator value="lp2" var="lbp">
                      <div class="ware_list">
                        <div class="ware_pic"><img src="../../web/images/<s:property value='#lbp.productXimage'/>" width="50" height="50"></div>
                        <div class="ware_text">
                            <div class="ware_text1">
                                <a href="product!getPro?productId=<s:property value='#lbp.productId'/>"><s:property value="#lbp.productName"/></a>
                                                                <br>
                                <span></span>
                            </div>
                            <div class="ware_text2">
                                <span>数量&nbsp;:&nbsp;<strong><s:property value="#lbp.sum"/></strong></span>
                                <s:if test="<s:property value='#lbp.cheapPrice'/>!=0">
                                <span>价格&nbsp;:&nbsp;<strong>¥<s:property value="#lbp.cheapPrice"/></strong></span>
                                </s:if>
                                <s:if test="<s:property value='#lbp.cheapPrice'/>==0">
                                <span>价格&nbsp;:&nbsp;<strong>¥<s:property value="#lbp.price"/></strong></span>
                                </s:if>
                            </div>
                        </div>
                    </div>
                    </s:iterator>
                                        <div class="transportation">
                    	<!-- 优惠打折&nbsp;:&nbsp;<span>¥0.00</span>
                    	<br> -->
                    	运费&nbsp;:&nbsp;<span>${logistic.logisticsPrice }<strong>(${logistic.logisticsWay })</strong></span>
                    	<br>
                    	总价&nbsp;:&nbsp;<b>¥${zongjia } (含运费)</b>
                    </div>
                   <!--  <ul class="order_detail_list">
                   <li>下单时间&nbsp;:&nbsp;2013-10-14 10:12:43</li>
                                        </ul> -->
                </div>
            </div>
			<dl>
			<dt class="til_font">物流信息</dt>
			<dt>收货地址&nbsp;:&nbsp;</dt>
			<dd>
				${order.receivePerson }, &nbsp;${order.receivePhone }	                ,&nbsp;${order.receiveAddress }	                			</dd>
			<dt>配送方式&nbsp;:&nbsp;</dt>
			<dd>${logistic.logisticsWay }</dd>
			                   
            </dl>
        </div>

        <div class="clear"></div>
        <s:if test="order.payType==0"> 
        <div class="btn_list">
        	<a class="order_cancel" href="../../site/web/order!getCannelOrder?ordernum=${order.orderNum }" id="order402_action_cancel"> 取消订单</a>
	        <a class="order_pay" href="javascript:getFukuan()" target="_blank" id="order402_action_pay">付款</a>
        </div>
        </s:if>
        <div class="adorn_right2"></div>
        <div class="adorn_right3"></div>
        <div class="adorn_right4"></div>
    </div>
    <div class="clear"></div>
</div>
<jsp:include page="/web/bottom.jsp" />
</body></html>