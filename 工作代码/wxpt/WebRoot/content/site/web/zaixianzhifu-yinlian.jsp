<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

 <!DOCTYPE HTML>
<html  class="ui-mobile">
  <head>
    
    <title>确定支付？</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="../../web/css/jquery.mobile-1.2.0.min.css" />
    <script src="../../web/js/jquery-1.8.3.js"></script>
    <script src="../../web/js/jquery.mobile-1.2.1.min.js"></script>
    <script src="../../web/js/zaixianzhifu.js"></script>
  </head>
  
  <body  class="ui-mobile-viewport">
  <div data-role="header" data-theme="b">
    			<h1>您选择了银联支付</h1>
    			<%-- <a href="../../site/web/vip-web?enterId=${enterId }&cardId=${cardId }" style="text-decoration: none;float: right;">返回</a>
    			<a href="../../site/web/vip-web!getList?enterId=${enterId }&id=<s:property value='integ.memberId' />&cardId=${cardId }" style="text-decoration: none;float: right;">查看历史</a> --%>
  </div>
     <div ><!-- style="display:none" -->
  	<form  id="frm_order"  action="http://payment.chinapay.com/pay/TransGet"  method="post"> <!--（这里 action  的内容为提交交易数据的URL 地址） -->

		<input  type="text"    name="MerId"   value="${merid}"/> <!--（MerId 为ChinaPay 统一分配给商户的商户号，15 位长度，必填）--> 
		<input  type="text"    name="OrdId"   value="${orderno}"/> <!--（商户提交给 ChinaPay 的交易订单号，16 位长度，必填） -->
		<input  type="text"    name="TransAmt"  value="${amount}"/> <!--（订单交易金额，12 位长度，左补0，必填,单位为分） -->
		<input  type="text"    name="CuryId"   value="${currencycode}"/> <!--（订单交易币种，3 位长度，固定为人民币 156， 必填） -->
		<input  type="text"    name="TransDate"   value="${transdate}"/> <!--（订单交易日期，8 位长度，必填） -->
		<input  type="text"    name="TransType"   value="${transtype}"/> <!--（交易类型，4 位长度，必填） -->
		<input  type="text"    name="Version"   value="${version}"/> <!--（支付接入版本号，必填） -->
		<!-- <input  type="text"    name="BgRetUrl"   value="http://www.uniqyw.com/uniqyw/site/web/netpay-client!receiveOrder"/> （后台交易接收URL ，长度不要超过80 个字节，必填）
		
		<input  type="text"    name="PageRetUrl"  value="http://www.uniqyw.com/uniqyw/site/web/netpay-client!findSuccess"/> （页面交易接收 URL ，长度不要超过80 个字节，必填） -->
				 <input  type="text"    name="BgRetUrl"   value="http://211.154.224.228:8080/vshop/site/web/zaixianzhifu!receiveOrder"/> <!-- （后台交易接收URL ，长度不要超过80 个字节，必填） -->
		<input  type="text"    name="PageRetUrl"  value="http://211.154.224.228:8080/vshop/site/web/zaixianzhifu!findSuccess"/> <!-- （页面交易接收 URL ，长度不要超过80 个字节，必填）  -->
		
		<input  type="text"    name="GateId"   value=""/> <!--（支付网关号，可选） -->
		<input  type="text"    name="Priv1"   value="${Priv1}"/> <!--（商户私有域，长度不要超过60 个字节） -->
		<input  type="text"    name="ChkValue" value="${checkvalue}"/> <!--（256 字节长的ASCII 码,为此次交易提交关键数据的数字签名，必填） -->		
    
    </form>  
  </div>
 
  <div class="pay">
  <!--   <a href="javascript:goToPay();"><img src="../../web/images/join_btn1.jpg"  class="btn_pay" style="margin-left:20px;"/></a>
    <a href="/uniqyw/site/web/index!find"><img src="../../web/images/back.jpg"  class="btn_pay" /></a> -->
    
     <a href="javascript:goToPay();">确认付款</a>
    <a href="/uniqyw/site/web/index!find">返回首页</a>
    
   </div>
   
   			 <div data-role="footer" data-theme="b" data-position="fixed" data-tap-toggle="false">
    <h4>微商城支付页面</h4>
   </div>
  </body>
</html>
