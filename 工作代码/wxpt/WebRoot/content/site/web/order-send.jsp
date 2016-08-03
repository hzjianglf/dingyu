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
<div class="content">
<jsp:include page="/web/top.jsp" />
 <h3 class="membertop">
             <p class="my_name"><a >${memberName } </a></p>
             <p class="my_address"><a href="../../site/web/order!getaddress">收货地址管理</a></p>
</h3> 
<ul class="buyer_stat" style="margin-top: 10px;">
    	<li class="btn_1"><a href="../../site/web/order!getAllNewOrder">待付款</a><span>待付款</span></li>        
        <li class="btn_2 active"><a href="../../site/web/order!getAllSendOrder">待发货</a><span>待发货</span></li>
        <li class="btn_3"><a href="../../site/web/order!getAllTakeOrder">待收货</a><span>待收货</span></li>
        <li class="btn_4"><a href="../../site/web/order!getAllOverOrder">已完成</a><span>已完成</span></li> 

      

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
function firstpage(){
			var curPage = 1;
			$.ajax({
				type : "POST",
				url : "/wxpt/site/web/order!getChangeSendPage",
				data : {
					'currentpage' : curPage
				},
				success : function(text) {
					$("#changepage").html(text);
				},
				error : function(xx) {
				}
			});
}
function uppage(){

			var curPage = $("#uppage").val();
			$.ajax({
				type : "POST",
				url : "/wxpt/site/web/order!getChangeSendPage",
				data : {
					'currentpage' : curPage
				},
				success : function(text) {
					$("#changepage").html(text);
				},
				error : function(xx) {
				}
			});
}
function downpage(){
  
			var curPage = $("#downpage").val();
			$.ajax({
				type : "POST",
				url : "/wxpt/site/web/order!getChangeSendPage",
				data : {
					'currentpage' : curPage
				},
				success : function(text) {
					$("#changepage").html(text);
				},
				error : function(xx) {
				}
			});
}
function lastpage(){
			var curPage = $("#lastpage").val();
			$.ajax({
				type : "POST",
				url : "/wxpt/site/web/order!getChangeSendPage",
				data : {
					'currentpage' : curPage
				},
				success : function(text) {
					$("#changepage").html(text);
				},
				error : function(xx) {
				}
			});
}
</script>
<div class="wrap" >
	<div  id="changepage">
           <div class="public">
            	<s:if test="size!=0">
            		<s:iterator value="lo3" var="lo3">
          		<div class="order_form">
                    <p class="num">订单号: <s:property value="#lo3.order.orderNum"/></p>
                    <s:iterator value="#lo3.lbp" var="lbp">
                                        <div class="con">
                        <p class="ware_pic"><a href="product!getPro?productId=<s:property value='#lbp.product.productId'/>" target="_blank"><img src="../../web/images/<s:property value="#lbp.product.productXimage"/>" width="80" height="80"></a></p>
                        <p class="ware_text"><a href="product!getPro?productId=<s:property value='#lbp.product.productId'/>" target="_blank"><s:property value="#lbp.product.productName"/></a><br><span class="attr"></span></p>
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
                           <a target="_blank" id="order402_action_pay" class="btn" style="color:red;">已付款</a>
                            <a id="order402_action_cancel" style="color:red;"> 等待发货</a>
                            <a href="../../site/web/order!getSelectOrder?ordernum=<s:property value='#lo3.order.orderNum'/>" target="_blank">查看订单</a>
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
                <input type="hidden" id="uppage" value="<s:property value='currentpage-1'/>">
		<input type="hidden" id="downpage" value="<s:property value='currentpage+1'/>">
		<input type="hidden" id="lastpage" value="<s:property value='pageCount'/>">
            </div> 
          
            
            <!-- <div class="wrap_bottom"></div> -->
            
            
       <div align="center" class="sort_page" style="margin-top: 15px;">
				<span>共<s:property value='listCount' />条，<s:property
				value='currentpage' />/<s:property value='pageCount' />页</span>
		<s:if test="currentpage==1">
			<a class="shouye1" disabled="disabled">首页</a>
		</s:if>
		<s:else>
			<!-- <a href="/vshop/site/web/order!getAllNewOrder?currentpage=1">首页</a> -->
			<a href="javascript:firstpage()">首页</a>
		</s:else>
		<s:if test="currentpage==1">
			<a class="shouye1" disabled="disabled">上一页</a>
		</s:if>
		<s:else>
			<%-- <a href="/vshop/site/web/order!getAllNewOrder?currentpage=<s:property value='currentpage-1'/>">上一页</a> --%>
			<a href="javascript:uppage()">上一页</a>
		</s:else>
		<s:if test="currentpage==pageCount||pageCount==0">
			<a class="shouye1" disabled="disabled">下一页</a>
		</s:if>
		<s:else>
			<%-- <a href="/vshop/site/web/order!getAllNewOrder?currentpage=<s:property value='currentpage+1'/>">下一页</a> --%>
			<a href="javascript:downpage()">下一页</a>
		</s:else>
		<s:if test="currentpage==pageCount||pageCount==0">
			<a class="shouye1" disabled="disabled">尾页</a>
		</s:if>
		<s:else>
			<%-- <a href="/vshop/site/web/order!getAllNewOrder?currentpage=<s:property value='pageCount'/>">尾页</a> --%>
			<a href="javascript:lastpage()">尾页</a>
		</s:else>
		</div> 
		</div>    
        </div>     
       <!--  <div class="clear"></div> -->
</div>
<jsp:include page="/web/bottom.jsp" />

</body></html>
