<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

 <!DOCTYPE HTML>
<html  class="ui-mobile">
 <head>
    
    <title>支付宝</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="../../web/css/jquery.mobile-1.2.0.min.css" />
    <script src="../../web/js/jquery-1.8.3.js"></script>
    <script src="../../web/js/jquery.mobile-1.2.1.min.js"></script>
 <script src="../../web/js/zaixianzhifu.js"></script>
  </head>

<body class="ui-mobile-viewport">

     <div data-role="page">
    		<div data-role="header" data-theme="b">
    			<h1>请选择支付方式</h1>
    			<a href="../../site/web/vip-web?enterId=${enterId }&cardId=${cardId }" style="text-decoration: none;float: right;">返回</a>
    			<a href="../../site/web/vip-web!getList?enterId=${enterId }&id=<s:property value='integ.memberId' />&cardId=${cardId }" style="text-decoration: none;float: right;">查看历史</a>
    		</div>
    		<div data-role="content" align="left">
    		
    		 <fieldset data-role="controlgroup">
        <input type="radio" name="1" id="1" value="1" />
        <label for="1" style="width:100%;" ><span><img alt="支付宝支付" src="../../web/images/zhifubao.gif" style=""></span></label>
        <input type="radio" name="1" id="2" value="2"  />
        <label for="2" style="width:100%;"><img alt="银联支付" src="../../web/images/yinlian.gif"></label>
       
    </fieldset>

   			</div>
<style type="text/css">
	.jiesuan1{
	 text-decoration: none;text-shadow: 0 1px 0 rgba(0, 0, 0, 0.4);
	}
	.jiesuan2:hover{background: -webkit-gradient(linear, left top, left bottom, from(#F5BA2D),to(#FF8400) );}
	.jiesuan2:active{background:-webkit-gradient(linear, left top, left bottom, from(#FF8400),to(#F5BA2D) );}
	.jiesuan2{
	border-radius:6px;
	width:100%;height:40px;
	background:-webkit-gradient(linear, left top, left bottom, from(#FF8400),to(#F5BA2D) );
	border: 1px solid rgba(0, 0, 0, 0.3);
	}
	
	
	.jiesuan3{
	width:70px;margin:4% auto;
	}
</style>
   			
   			<div style="width:90%;margin:0 auto;">
	   			<a href="#" class="jiesuan1" style=" color:#FFFFFF;">
		   			<div class="jiesuan2" onClick="qudingjiesuan()" >
			   			<div class="jiesuan3">
			   			       确定结算
			   			</div>
		   			</div>
	   			</a>
   			</div>
   			
   			
   			 <div data-role="footer" data-theme="b" data-position="fixed" data-tap-toggle="false">
    <h4>微商城支付页面</h4>
   </div><!-- /footer -->
    	</div>


</body>
</html> 

 
 
