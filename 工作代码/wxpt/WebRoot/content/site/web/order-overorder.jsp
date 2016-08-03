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
<script type="text/javascript" src="../../web/js/jquery-1.8.3.js"></script>
<link href="../../web/css/shoppingstyle.css" rel="stylesheet" type="text/css">

 <style> 
        .black_overlay{ 
            display: none; 
            position:fixed; 
            top: 50%; 
           	left: 40%; 
           /*  right: 25%;
            bottom: 40%; */
            width: 200px; 
            height: 150px;  
            background-color: #ADADAD; 
            z-index:1001; 
            -moz-opacity: 0.8; 
            margin-left:-50px;
			margin-top:-50px;
        } 
        .black_result{ 
            display: none; 
            position: absolute; 
             top: 40%; 
            left: 30%; 
            right: 40%;
            bottom: 40%;
             width: 47%; 
            height: 12%; 
            padding-top:5%;
            text-align:center;
            background-color: #ADADAD; 
            z-index:1001; 
            -moz-opacity: 0.8; 
        }
    </style> 
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
             <p class="my_name"><a> ${memberName } </a></p>
             <p class="my_address"><a href="../../site/web/order!getaddress">收货地址管理</a></p>
</h3> 
<ul class="buyer_stat" style="margin-top: 10px;">
    	<li class="btn_1"><a href="../../site/web/order!getAllNewOrder">待付款</a><span>待付款</span></li>        
        <li class="btn_2"><a href="../../site/web/order!getAllSendOrder">待发货</a><span>待发货</span></li>
        <li class="btn_3"><a href="../../site/web/order!getAllTakeOrder">待收货</a><span>待收货</span></li>
        <li class="btn_4 active"><a href="../../site/web/order!getAllOverOrder">已完成</a><span>已完成</span></li> 

      

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
				url : "/wxpt/site/web/order!getChangeOverPage",
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
				url : "/wxpt/site/web/order!getChangeOverPage",
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
				url : "/wxpt/site/web/order!getChangeOverPage",
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
				url : "/wxpt/site/web/order!getChangeOverPage",
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
function showOpen(){
	document.getElementById("appresult").style.display="block";
}
function showClose(){
	document.getElementById("appresult").style.display="none";
}
function appraiseOpen(id,oid){
	$("#productId").val(id);
	$("#oId").val(oid);
	document.getElementById("fade").style.display="block";
}
function appraiseClose(){
	var curPage = $("#dangqian").val();
	document.getElementById("fade").style.display="none";
	window.location.href="/wxpt/site/web/order!getAllOverOrder?currentpage="+curPage;;
}
function appraiseSave(){
			var curPage = $("#dangqian").val();
			var appraiseContent = $("#appraiseContent").val();
			var id = $("#productId").val();
			var oid= $("#oId").val();
			$.ajax({
				type : "POST",
				url : "/wxpt/site/web/appraise!sava",
				data : {
					"appContent" : appraiseContent,
					"productId" : id,
					"orderId" : oid,
				},
				success : function(text) {
				if(text){
				appraiseClose();
					showOpen();
					document.getElementById("show").innerHTML = "\<h2\>评价成功！\</h2\>";
					window.location.href="/wxpt/site/web/order!getAllOverOrder?currentpage="+curPage;
				}else{
				appraiseClose();
					showOpen();
					document.getElementById("show").innerHTML = "\<h2\>评价失败，请稍后评价！\</h2\>";
					window.location.href="/wxpt/site/web/order!getAllOverOrder?currentpage="+curPage;
				}
				$("#appraiseContent").val("");
				},
				error : function(text) {
				if(text){
					showOpen();
					document.getElementById("show").innerHTML = "\<h2\>评价成功！\</h2\>";
					window.location.href="/wxpt/site/web/order!getAllOverOrder?currentpage="+curPage;
				}else{
					showOpen();
					document.getElementById("show").innerHTML = "\<h2\>评价失败，请稍后评价！\</h2\>";
					window.location.href="/wxpt/site/web/order!getAllOverOrder?currentpage="+curPage;
				}
				$("#appraiseContent").val("");
				}
			});
}
</script>
<div class="wrap" >
<input type="hidden" id="productId"/>
<input type="hidden" id="oId"/>
	<div  id="changepage">
           <div class="public">
            	<s:if test="size!=0">
            		<s:iterator value="lo3" var="lo3">
          		<div class="order_form">
                    <p class="num">订单号: <s:property value="#lo3.order.orderNum"/></p>
                    <s:iterator value="#lo3.lbp2" var="lbp">
                     <div class="con">
                      	<p class="ware_pic"><a href="product!getPro?productId=<s:property value='#lbp.product.productId'/>" target="_blank"><img src="../../web/images/<s:property value="#lbp.product.productXimage"/>" width="80" height="80"></a></p>
                        <p class="ware_text"><a href="product!getPro?productId=<s:property value='#lbp.product.productId'/>" target="_blank"><s:property value="#lbp.product.productName"/></a><br><span class="attr"></span></p>
                       	<s:if test="#lbp.type==1">
                       		<p class="ware_text"><a style="color:red;">已评价</a></p>
                       	</s:if>
                       	<s:if test="#lbp.type==0">
                       		<p class="ware_text"><a href="javascript:appraiseOpen(<s:property value="#lbp.product.productId"/>,<s:property value="#lbp.order.orderId"/>)" style="color:red;">立即评价</a></p>
                       	</s:if>
                       	
                        <p class="price">价格: <span>¥<s:property value="#lbp.product.cheapPrice"/></span></p>
                        <p class="amount">数量: <span><s:property value="#lbp.buyProductSum"/></span></p>
                       <%--  <p class="ware_text"><a href="javascript:appraiseOpen(<s:property value="#lbp.product.productId"/>)" style="color:red;">立即评价</a></p> --%>
 					</div>
                    </s:iterator>
                    					<div class="clear"></div>
                    <div class="foot">
                        <p class="time">添加时间: <s:property value="#lo3.order.orderTime"/></p>
                                                <div class="handle">
                            <div style="float:left;">
                                订单总价: <b id="order402_order_amount">¥<s:property value="#lo3.order.payMoney"/>&nbsp;&nbsp;</b>
                            </div>                            
                            <a target="_blank" id="order402_action_pay" class="btn">已完成</a>
                            <a href="../../site/web/order!getSelectOrder?ordernum=<s:property value='#lo3.order.orderNum'/>" target="_blank">查看订单</a>
                       		<%-- <a href="../../site/web/order!getDeleteOrder?ordernum=<s:property value='#lo3.order.orderNum'/>" target="_blank" id="order402_action_pay" class="btn">删除</a> --%>
                        	<a href="../../site/web/order!getDeleteOrder?ordernum=<s:property value='#lo3.order.orderNum'/>" target="_blank" id="order402_action_pay"  class="btn" onclick="JavaScript:return confirm('确定删除吗？')">删除</a>
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
		<input type="hidden" id="dangqian" value="<s:property value='currentpage' />">
            </div> 
          
            
            <!-- <div class="wrap_bottom"></div> -->
            
            
       <div align="center" class="sort_page" style="margin-top: 15px;">
				<span>共<s:property value='listCount' />条，<s:property
				value='currentpage' />/<s:property value='pageCount' />页</span>
		<s:if test="currentpage==1">
			<a class="shouye1" disabled="disabled">首页</a>
		</s:if>
		<s:else>
			<!-- <a href="/wxpt/site/web/order!getAllNewOrder?currentpage=1">首页</a> -->
			<a href="javascript:firstpage()">首页</a>
		</s:else>
		<s:if test="currentpage==1">
			<a class="shouye1" disabled="disabled">上一页</a>
		</s:if>
		<s:else>
			<%-- <a href="/wxpt/site/web/order!getAllNewOrder?currentpage=<s:property value='currentpage-1'/>">上一页</a> --%>
			<a href="javascript:uppage()">上一页</a>
		</s:else>
		<s:if test="currentpage==pageCount||pageCount==0">
			<a class="shouye1" disabled="disabled">下一页</a>
		</s:if>
		<s:else>
			<%-- <a href="/wxpt/site/web/order!getAllNewOrder?currentpage=<s:property value='currentpage+1'/>">下一页</a> --%>
			<a href="javascript:downpage()">下一页</a>
		</s:else>
		<s:if test="currentpage==pageCount||pageCount==0">
			<a class="shouye1" disabled="disabled">尾页</a>
		</s:if>
		<s:else>
			<%-- <a href="/wxpt/site/web/order!getAllNewOrder?currentpage=<s:property value='pageCount'/>">尾页</a> --%>
			<a href="javascript:lastpage()">尾页</a>
		</s:else>
		</div> 
		</div>    
        </div>     
       <!--  <div class="clear"></div> -->
</div>
	<div id="fade" class="black_overlay">
		 <table>
			 <tr>
			 	<td><a >评价：</a></td>
			 </tr>
		 <tr>
			 <td>
			 	<textarea rows="4" cols="20" id="appraiseContent"></textarea>
			 </td>
		 </tr>
		 <tr>
		 	<td>
		 		<div style="width:50px; float:left; margin-left:5%;"><a href="javascript:appraiseSave()">确认</a></div>
		 		<div style="float:right; margin-right:5%;"><a href="javascript:appraiseClose()">取消</a></div>
		   </td>
		 </tr>
		 </table>
	 </div> 
 <div id = "appresult" class ="black_result">
 <div id = "show"></div>
 <div><a href="javascript:showClose()">确认</a></div>
 </div>
<jsp:include page="/web/bottom.jsp" />
</body></html>
