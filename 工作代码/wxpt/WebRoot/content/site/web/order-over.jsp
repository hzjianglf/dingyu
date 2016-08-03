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
                	var enter=${time};
                	$.ajax({
		          type:"POST",
		          url:"../../site/order-manage!getOrder",
				 data : {
					  	'orderNum':enter
					  },
		         success:function (){
		         $.messager.alert('消息', '付款成功');
		         	window.location.href="../../site/web/order!getAllNewOrder";
		        /*  }else{
		         
		         	alert("订单中某商品数量超过库存量");
		         } */
		        	  
		        		   
		         },
			  	 error:function (){
			  	 }
		       });
                	/* $.ajax({
                		type:"POST",
			          url:"../../site/order-manage!getOrder",
					  data : {
					  	'orderNum':enter
					  },
					  success:function (){
					  $.messager.alert('消息', '付款成功');
			        		   window.location.href="/wxpt/site/web/order!getAllNewOrder";
			         }
                	
                	
                	});s */
                	
                }
            
                function zhifu(){
                $("#goto_pay").submit();
                }
                </script>
</head>
<body>
<div id="content">
<jsp:include page="/web/top.jsp" />
    <form action="http://payment.chinapay.com/pay/TransGet" method="POST" id="goto_pay">
        <div class="step_main" style="margin-top: 80px;">
            <div class="clue_on"><h4>订单提交成功！</h4><p>您的订单已成功生成，选择您想用的支付方式进行支付</p></div>
            <div class="order_information">
         <input  type="hidden"    name="MerId"   value="${merid}"/> <!--（MerId 为ChinaPay 统一分配给商户的商户号，15 位长度，必填）--> 
		<input  type="hidden"    name="OrdId"   value="${ordernos}"/> <!--（商户提交给 ChinaPay 的交易订单号，16 位长度，必填） -->
		<input  type="hidden"    name="TransAmt"  value="${amount}"/> <!--（订单交易金额，12 位长度，左补0，必填,单位为分） -->
		<input  type="hidden"    name="CuryId"   value="${currencycode}"/> <!--（订单交易币种，3 位长度，固定为人民币 156， 必填） -->
		<input  type="hidden"    name="TransDate"   value="${transdate}"/> <!--（订单交易日期，8 位长度，必填） -->
		<input  type="hidden"    name="TransType"   value="${transtype}"/> <!--（交易类型，4 位长度，必填） -->
		<input  type="hidden"    name="Version"   value="${versions}"/> <!--（支付接入版本号，必填） -->
		<!-- <input  type="text"    name="BgRetUrl"   value="http://www.uniqyw.com/uniqyw/site/web/netpay-client!receiveOrder"/> （后台交易接收URL ，长度不要超过80 个字节，必填）
		
		<input  type="text"    name="PageRetUrl"  value="http://www.uniqyw.com/uniqyw/site/web/netpay-client!findSuccess"/> （页面交易接收 URL ，长度不要超过80 个字节，必填） -->
				 <input  type="hidden"    name="BgRetUrl"   value="http://211.154.224.228:8080/vshop/site/web/zaixianzhifu!receiveOrder"/> <!-- （后台交易接收URL ，长度不要超过80 个字节，必填） -->
		<input  type="hidden"    name="PageRetUrl"  value="http://211.154.224.228:8080/vshop/site/web/zaixianzhifu!findSuccess"/> <!-- （页面交易接收 URL ，长度不要超过80 个字节，必填）  -->
		
		<input  type="hidden"    name="GateId"   value=""/> <!--（支付网关号，可选） -->
		<input  type="hidden"    name="Priv1"   value="${Priv1}"/> <!--（商户私有域，长度不要超过60 个字节） -->
		<input  type="hidden"    name="ChkValue" value="${ChkValue}"/> <!--（256 字节长的ASCII 码,为此次交易提交关键数据的数字签名，必填） -->		
            
                    <p>订单号&nbsp;:&nbsp;<span>${time }</span></p>
                    订单总价&nbsp;:&nbsp;<span>¥${zongjia }(含运费)</span>
            </div>

            <div class="buy">
                <h3>选择支付方式并付款</h3>
                               <dl class="defray">
                    <dt>在线支付</dt>
                                        <!-- <dd>
                        <p class="radio"><input id="payment_alipay" type="radio" name="1" id="1" value="1"></p>
                        <p class="logo"><label for="payment_alipay"><img src="../../web/images/zhifubao.png" alt="支付宝-" title="支付宝-"></label></p>
                        <p class="explain"></p>
                    </dd> -->
                    <dd>
                        <p class="radio"><input id="payment_alipay" type="radio" name="1" id="1" value="1"></p>
                        <p class="logo"><label for="payment_alipay"><img src="../../web/images/yinlian.gif" alt="支付宝-" title="支付宝-"></label></p>
                        <p class="explain"></p>
                    </dd>
                                    </dl> 
               <!--                                  <dl class="defray">
                    <dt>线下支付</dt>
                                        <dd>
                        <p class="radio"><input type="radio" id="payment_bank" name="payment_id" value="4"></p>
                        <p class="logo"><label for="payment_bank"><img alt="银行汇款-" title="银行汇款-" src="../../web/images/yinhanghuikuan.png"></label></p>
                        <p class="explain"></p>
                    </dd>
                                        <dd>
                        <p class="radio"><input type="radio" id="payment_cod" name="payment_id" value="3"></p>
                        <p class="logo"><label for="payment_cod"><img alt="货到付款-" title="货到付款-" src="../../web/images/huodaofukuan.png"></label></p>
                        <p class="explain"></p>
                    </dd>
                                    </dl> -->
                <!--  <fieldset data-role="controlgroup">
        <input type="radio" name="1" id="1" value="1" />
        <label for="1" style="width:100%;" ><span><img alt="支付宝支付" src="../../web/images/zhifubao.gif" style=""></span></label><br>
        <input type="radio" name="1" id="2" value="2"  />
        <label for="2" style="width:100%;"><img alt="银联支付" src="../../web/images/yinlian.gif"></label>
       
    </fieldset> -->
            </div>
            <div class="make_sure">
                <p>
                    <a href="javascript:zhifu()" class="btn enter">确认支付</a>
                </p>
            </div>
            <div class="remark">
       
                注：商品将于5工作日内送达。<!-- <a href="#">配送范围请查看</a> --><br />
                您可以在 <a href="../../site/web/order!getAllNewOrder">我的订单</a>  中查看或取消您的订单，由于系统需进行订单预处理，您可能不会立刻查询到刚提交的订单。<br />
                如果您现在不方便支付，可以随后到 <a href="../../site/web/order!getAllNewOrder">我的订单</a>  完成支付，我们会在7工作日内为您保留未支付的订单。<br/>
       卖家发货后15个工作日后，如果您没有确认收货，系统默认会为您确认收货。         
            </div>
            <div class="clear"></div>
        </div>
    </form>
</div>
<jsp:include page="/web/bottom.jsp" />
</body></html>