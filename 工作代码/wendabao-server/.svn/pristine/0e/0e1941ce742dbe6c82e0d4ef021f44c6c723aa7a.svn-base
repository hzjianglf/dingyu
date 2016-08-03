<%@page import="com.handany.base.common.Constants"%>
<%@page import="com.handany.base.common.ApplicationConfig"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@page import="com.handany.bm.model.BmOrder"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    String imageServer = ApplicationConfig.getConfig("image_server");
    %>
<!DOCTYPE html>
<html lang="zh-CN">
 <%@include file="/common.jsp" %>
 <%-- 
 	商家新建订单
 --%>
<title>新建订单</title>
<style type="text/css">
	.table>tbody>tr>td{padding-top:10px;}
	.table>tbody>tr>td>button{margin-top:-8px}
	.modal-header{background:#f4f5fe}
	.list-group-item{padding:0px;}
	.col-sm-3{padding:0px; margin:0px;border:0px solid #ff0000}
	.row{border:0px solid #ff0000}
	.productName{font-weight:bold;}
	.price{color:#ff0000;font-weight:bold}
	.detail{color:#ff0000;font-weight:bold}
	.detail>span{margin-right:20px;}
	#orderSumitModal .row{margin-top:10px;}
	#orderSumitModal .row>.col-sm-3{padding-left:15px;padding-top:5px;}
	#order_itemList .list-group .list-group-item{height:30px;}
</style>
	<script src="<%=basePath%>js/order/newOrder.js"></script>
 <link rel="stylesheet" href="<%=basePath %>/css/mall.css">
 <script src="<%=basePath%>js/mallDialog.js"></script>
<script type="text/javascript">
$(function () {
	NewOrderDo.bathpath ='<%=imageServer%>'+'/';
    NewOrderDo.init();
});

	
</script>
 <style>
	 .inner-tab{
		 border-bottom: solid 5px #c9302c;
		 padding: 10px;
		 width: 50%;
		 text-align: center;
	 }
 </style>
</head>
<body>
 <div class="container" style="width:100%" >
<!--<nav class="navbar navbar-default navbar-fixed-top" style="padding:10px;background:#fff;margin:0px;padding:0px">-->
<!--<label>新建订单</label>-->

	<div class="row" style="border:0;margin-top:-10px;">
		<div class="col-sm-12">
			<h3 class="zs-iframe-title">新建订单</h3>
		</div>
	</div>

   <div class="row"  style="margin-top:10px;padding-bottom: 10px;border-bottom: 1px solid #DCD1D1;">
 	  <div class="col-sm-2"  style="margin-top:5px;">
      	<label style="float:right">下单客户：</label>
      </div>
      <div class="col-sm-2"  style="padding:0px;">
      	<input type="text" name="userName" id="userName" readonly class="form-control" style="background:#fff;" >
	      <input type="hidden" name="buyerId" id="buyerId"> 
      	
      </div>
      <div class="col-sm-2"  style="padding-left:5px">
      	<button type="button" class="btn btn-default zs-btn-default " onclick="NewOrderDo.showCustSelect()">选择</button>
      </div>
     <div class="col-sm-6"  style="padding-left:5px;">
      	<label>当前订单总价：<span style="color:#ff0000;margin-right:20px;" id="orderTotal">￥0.00</span></label>
      	
      	<button type="button" class="btn  btn-default zs-btn-green" onclick="NewOrderDo.showOrder()">提交订单</button>
	 </div>
</div>


<div class="row" style="margin-top: 10px;">
<div class="col-sm-6" ><span class="inner-tab">我的商品</span><span style="color:#ff0000;margin-left:20px;" id="productMsg"></span></div>
<div class="col-sm-6" style="padding-left: 30px;"><span class="inner-tab">订单内容</span></div>
</div>
</nav>

	<div class="row" style="margin-top:20px;">
		<div class="col-sm-6"  style="padding:0px;border-right:1px dotted #f3f4f3">
			
			<div  id="productList" >
				
			</div>
		</div>
		<div class="col-sm-6"  style="border-left:0px dotted #f3f4f3">
		
		<div   id="orderItems">
			
		</div>
		</div>
	</div>
</div>


</body>


<!-- <div class="modal fade" id="custSelectModal">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header" style="border-bottom:0px" style="background:#f4f5fe">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" style="margin:20px;">客户选择</h4>
           <div class="row" >
      <div class="col-sm-1"  style="padding-left:5px">
      </div>
      	 <div class="col-sm-8"  style="padding-left:5px">
      	 	<input type="text" class="form-control" placeholder="请输入客户的手机号" id="buyerPhone">
      	 </div>
      	 <div class="col-sm-3"  style="padding-left:5px">
      	 <button type="button" class="btn btn-primary" onclick="NewOrderDo.queryBuyer()">查询</button>
      	 </div>
      </div>
      </div>
      <div class="modal-body" style="padding:1px;height:25em;overflow-y:scroll">
	  	 <div class="list-group" id="custList">	</div>
      </div>
      <div class="modal-footer" style="border-top:0px">
        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
      </div>
    </div>/.modal-content
  </div>/.modal-dialog
</div>/.modal -->

<%-- 
<div class="modal fade" id="productType" tabindex="-1" role="dialog" 
   aria-labelledby="myModalLabel" aria-hidden="true">
   <div class="modal-dialog">
      <div class="modal-content">
         <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" 
               aria-hidden="true">×
            </button>
            <h5 class="modal-title" id="productType_title">
           		  选择商品
            </h5>
         </div>
         <div class="modal-body">
           <div class="row" style="border-bottom:1px dotted #f3f4fe;margin-bottom:10px;">
         	 <div class="col-sm-3"  style="padding-left:5px">
         	 <img src="<%=basePath %>image/icon.png" class="img-responsive" id="productType_img" style="height:100px;width:100px">
         	 </div>
         	 <div class="col-sm-9"  style="padding-left:5px">
         	<h4 class="productName" id="productType_pname"></h4>
         	<h5 class="" id="productType_desc" ></h5>
			<h5 class="price" >￥<span id="productType_price"></span></h5>
         	 </div>
         </div>
         <div class="row">
         	 <div class="col-sm-3"  style="padding-left:5px;padding-top:10px;">
         	 	<label style="float:right;">型号</label>
         	 </div>
         	 <div class="col-sm-9 "  style="padding-left:5px">
         	 
         	 <input type="hidden" id="selected_productId">
	         	<input type="hidden" id="selected_weight">
	         	<input type="hidden" id="selected_price">
	         	<input type="hidden" id="selected_typeId">
				<div class="btn-group" role="group" id="productType_group">
				
				</div>
         	 </div>
         </div>
         <div class="row"   style="padding-left:5px;padding-top:10px;">
         	 <div class="col-sm-3"  style="padding-left:5px;padding-top:10px;"><label style="float:right">数量</label></div>
         	 <div class="col-sm-9"  style="padding-left:5px">
         	  <button type="button" class="btn btn-primary" style="float:left;width:40px" onclick="alterProductTypeNumber(-1)">-</button> 
            	&nbsp;<input type="text" id="productType_number" class="form-control" readonly style="background:#fff;;width:60px;float:left;text-align: right;margin-left:2px;margin-right:2px;" value="1">
            	&nbsp;<button type="button" class="btn btn-primary" style="float:left;width:40px" onclick="alterProductTypeNumber(1)">+</button>
         	 </div>
         </div>
         
         
         <div class="modal-footer">
         	<span id="appendOrderMsg" style="color:#ff0000"></span>
            <button type="button" class="btn btn-default" 
               data-dismiss="modal">关闭
            </button>
            <button type="button" class="btn btn-primary" onclick="appendToOrder(this)">
              			 加入订单
            </button>
         </div>
        </div>
      </div><!-- /.modal-content -->
   </div><!-- /.modal-dialog -->
</div><!-- /.modal --> --%>



</html>