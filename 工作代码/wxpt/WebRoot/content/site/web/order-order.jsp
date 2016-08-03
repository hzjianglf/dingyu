<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html><html><head>
<meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0">
<meta http-equiv="Content-Type" content="text/html;charset=utf-8">
<title>会员中心</title>
<meta name="description" content="">
<meta name="keywords" content="">
<link href="../../web/css/shoppingstyle.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="../../web/js/jquery-1.8.3.js" charset="utf-8"></script>
<!-- <script type="text/javascript" src="./会员中心_html/1215906.php"></script>
<script type="text/javascript" src="./会员中心_html/4911469.js" charset="utf-8"></script>
<script type="text/javascript" src="./会员中心_html/9493744.js" charset="utf-8"></script>
<script type="text/javascript" src="./会员中心_html/8374122.dev.js" charset="utf-8"></script> -->
<script type="text/javascript">
//<!CDATA[
var SITE_URL = "http://vstore.hishop.com.cn";
var REAL_SITE_URL = "http://vstore.hishop.com.cn";
var PRICE_FORMAT = '¥%s';

$(function(){
    var span = $("#child_nav");
    span.hover(function(){
        $("#float_layer:not(:animated)").show();
    }, function(){
        $("#float_layer").hide();
    });
});
//]]>
</script>
<script charset="utf-8" type="text/javascript" src="./会员中心_html/3064601.js" id="dialog_js"></script><link href="./会员中心_html/11668813.css" rel="stylesheet" type="text/css">
<script charset="utf-8" type="text/javascript" src="./会员中心_html/11446626.ui.js"></script>
<script charset="utf-8" type="text/javascript" src="./会员中心_html/15316019.js"></script>
<script charset="utf-8" type="text/javascript" src="./会员中心_html/537374.validate.js"></script>
<link rel="stylesheet" type="text/css" href="./会员中心_html/13625440.ui.css"><link>

<style type="text/css">
	

/*火狐*/
@-moz-document url-prefix() {

.sort_page .shouye1{color:gray;}

.sort_page a.shouye1:hover{color:gray;}
}
/*火狐 end*/

 /*谷歌*/
@media screen and (-webkit-min-device-pixel-ratio:0) {

.sort_page .shouye1{color:gray;}

.sort_page a.shouye1:hover{color:gray;}


}
 /*谷歌 end*/
</style> 
</head>

<body>

<div id="head">
<img src="./会员中心_html/3191668.jpg" height="50">
</div>
<div id="nav">
	<ul class="navlist">
    	<li id="n_0"><span></span>
        	<ul class="submenu">
                                                                        <li>
                            <a class="block_ico" href="http://vstore.hishop.com.cn/index.php?app=store&amp;id=8&amp;act=search&amp;cate_id=15">网络直销</a>
                            <ul>
                                                                <li><a href="http://vstore.hishop.com.cn/index.php?app=store&amp;id=8&amp;act=search&amp;cate_id=19">快店通</a></li>
                                                                <li><a href="http://vstore.hishop.com.cn/index.php?app=store&amp;id=8&amp;act=search&amp;cate_id=20">移动电商</a></li>
                                                            </ul>
                        </li>
                                                                                                <li><a class="none_ico" href="http://vstore.hishop.com.cn/index.php?app=store&amp;id=8&amp;act=search&amp;cate_id=16">网络分销</a></li>
                                                                                                <li><a class="none_ico" href="http://vstore.hishop.com.cn/index.php?app=store&amp;id=8&amp;act=search&amp;cate_id=18">营销辅助</a></li>
                                                                    </ul>
        </li>
        <li id="n_1" class="r"><a href="http://vstore.hishop.com.cn/index.php?app=store&amp;id=8"><span></span></a></li>
        <li id="n_2" class="r active"><a href="http://vstore.hishop.com.cn/index.php?app=memberbuy&amp;store_id=8"><span></span></a></li>
        <li id="n_3" class="r"><a href="http://vstore.hishop.com.cn/index.php?app=cart&amp;store_id=8"><span></span></a><i></i></li>
    </ul>
    <script type="text/javascript">
    	$(".navlist > li#n_0").click(function(){
			$(this).toggleClass("active");
		});
		$(".navlist > li.r a").each(function() {
            href="http://vstore.hishop.com.cn/"+$(this).attr("href");
			whref=window.location.href;
			//alert(href+"和"+window.location.href);
			//alert();
			if(whref.indexOf(href)!='-1'){
				$(this).parent("li").addClass("active");
			}
        });
    </script>
</div><div class="content">
<h3 class="membertop">
             <p class="my_name"><a href="http://vstore.hishop.com.cn/index.php?app=memberbuy&amp;act=profile&amp;store_id=8">
                             xuegee                          </a></p>
             <p class="my_address"><a href="http://vstore.hishop.com.cn/index.php?app=my_address&amp;store_id=8">收货地址管理</a></p>
</h3>
<ul class="buyer_stat">
    	<li class="btn_1"><a href="http://localhost:8080/vshop/site/web/order!getAllNewOrder">待付款</a><span>待付款</span></li>        
        <li class="btn_2"><a href="http://localhost:8080/vshop/site/web/order!getAllSendOrder">待发货</a><span>待发货</span></li>
        <li class="btn_3"><a href="http://localhost:8080/vshop/site/web/order!getAllTakeOrder">待收货</a><span>待收货</span></li>
        <li class="btn_4"><a href="http://localhost:8080/vshop/site/web/order!getAllOverOrder">已完成</a><span>已完成</span></li> 

      

</ul>
<script type="text/javascript">
$(function(){
$(".buyer_stat > li a").each(function() {
            href=$(this).attr("href");
			if(window.location.href==href){
				$(this).parent("li").addClass("active");
			}
        });
});


</script>
<div class="wrap" >
           <div class="public">
            	<s:if test="size!=0">
            		<s:iterator value="lo3" var="lo3">
          		<div class="order_form">
                    <p class="num">订单号: <s:property value="#lo3.order.orderNum"/></p>
                    <s:iterator value="#lo3.lbp" var="lbp">
                                        <div class="con">
                        <p class="ware_pic"><a href="http://vstore.hishop.com.cn/index.php?app=goods&amp;id=0" target="_blank"><img src="../../web/images/<s:property value="#lbp.product.productDimage"/>" width="80" height="80"></a></p>
                        <p class="ware_text"><a href="http://vstore.hishop.com.cn/index.php?app=goods&amp;id=0" target="_blank"><s:property value="#lbp.product.productName"/></a><br><span class="attr"></span></p>
                        <p class="price">价格: <span>¥<s:property value="#lbp.product.cheapPrice"/></span></p>
                        <p class="amount">数量: <span><s:property value="#lbp.buyProductSum"/></span></p>
                    </div>
                    </s:iterator>
                    					<div class="clear"></div>
                    <div class="foot">
                        <p class="time">添加时间: <s:property value="#lo3.order.orderTime"/></p>
                                                <div class="handle">
                            <div style="float:left;">
                                订单总价: <b id="order402_order_amount">¥<s:property value="#lo3.order.payMoney"/>&nbsp;&nbsp;</b>
                            </div>                            
                            <a href="http://vstore.hishop.com.cn/index.php?app=cashier&amp;order_id=402&amp;store_id=8" target="_blank" id="order402_action_pay" class="btn">付款</a>
                            <a href="http://vstore.hishop.com.cn/index.php?app=buyer_order&amp;act=confirm_order&amp;order_id=402&amp;store_id=8" id="order402_action_confirm" style="display:none">确认收货</a>
                            <a href="http://vstore.hishop.com.cn/index.php?app=buyer_order&amp;act=cancel_order&amp;order_id=402&amp;store_id=8" id="order402_action_cancel"> 取消订单</a>
                            <a href="http://vstore.hishop.com.cn/index.php?app=buyer_order&amp;act=view&amp;order_id=402&amp;store_id=8" target="_blank">查看订单</a>
                        </div>
                    </div>
                </div>
                </s:iterator>
            	</s:if>
            	<s:if test="size==0">
            		<div class="order_form member_no_records">
                    <span>没有符合条件的记录</span>
                </div>	
            	</s:if>
               	<!-- <div class="order_form_page">
                    <div class="page"></div>
                </div>
                <div class="clear"></div> -->
            </div> 
          
            
            <!-- <div class="wrap_bottom"></div> -->
            
            
       <div align="center" class="sort_page" style="margin-top: 15px;">
				<span>共<s:property value='listCount' />条，<s:property
				value='currentpage' />/<s:property value='pageCount' />页</span>

		<s:if test="currentpage==1">
			<a class="shouye1" disabled="disabled">首页</a>
		</s:if>
		<s:else>
			<a href="/vshop/site/web/order!getAllNewOrder?currentpage=1">首页</a>
		</s:else>
		<s:if test="currentpage==1">
			<a class="shouye1" disabled="disabled">上一页</a>
		</s:if>
		<s:else>
			<a href="/vshop/site/web/order!getAllNewOrder?currentpage=<s:property value='currentpage-1'/>">上一页</a>
		</s:else>
		<s:if test="currentpage==pageCount||pageCount==0">
			<a class="shouye1" disabled="disabled">下一页</a>
		</s:if>
		<s:else>
			<a href="/vshop/site/web/order!getAllNewOrder?currentpage=<s:property value='currentpage+1'/>">下一页</a>
		</s:else>
		<s:if test="currentpage==pageCount||pageCount==0">
			<a class="shouye1" disabled="disabled">尾页</a>
		</s:if>
		<s:else>
			<a href="/vshop/site/web/order!getAllNewOrder?currentpage=<s:property value='pageCount'/>">尾页</a>
		</s:else>
		</div>     
        </div>     
       <!--  <div class="clear"></div> -->
</div>
<div id="footer">
<p class="foot_nav">
<a href="http://vstore.hishop.com.cn/index.php?app=store&amp;id=8">商城首页</a> | <a href="http://vstore.hishop.com.cn/index.php?app=memberbuy&amp;store_id=8">会员中心</a> | <a href="http://vstore.hishop.com.cn/index.php?app=memberbuy&amp;store_id=8#">品牌介绍</a>
</p>
<div style="height:40px; background:#fff; padding:0; overflow:hidden;">
<div style="float:left; margin:5px 10px 0 0; display:inline;"><img src="../../web/images/vshop.png" height="20"></div>
<div style="line-height:40px; height:40px; display:inline-block; margin-left:10px; float:right; color:#aaa; font-size:14px;"></div>
</div>
</div>

</body></html>
