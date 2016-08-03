<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.handany.bm.model.*"%>
<%@page import="java.lang.String"%>
<%@page import="com.github.pagehelper.PageInfo"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
PageInfo<BmOrder> pageInfo = (PageInfo<BmOrder>)request.getAttribute("data");
List<BmOrder> list = (List<BmOrder>)pageInfo.getList();
String status = (String)request.getParameter("status");

/*一共有多少页  */
int totalPage = pageInfo.getPages();
/* 当前页显示的数量  */
int size = pageInfo.getSize();
/* 当前页  */
int currentPage = pageInfo.getPrePage() + 1;
/* 一页显示多少条  */
int length = pageInfo.getPageSize();
/* 是否有下一页  */
boolean isNext = pageInfo.isHasNextPage();
/* 页数数组 */
int[] data = pageInfo.getNavigatepageNums();

String tokenId = (String)request.getAttribute("tokenId");
%>
<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en" class="no-js">
<!--<![endif]-->
<!-- BEGIN HEAD -->
<head>
<meta charset="utf-8"/>

	<!-- 为了让 IE 浏览器运行最新的渲染模式下，建议将此 <meta> 标签加入到你的页面中：-->
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	<!--将下面的 <meta> 标签加入到页面中，可以让部分国产浏览器默认采用高速模式渲染页面：-->
	<meta name="renderer" content="webkit">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->

	<meta content="" name="description"/>
	<meta content="" name="author"/>
	
	<title>采供网</title>
	<!-- BEGIN GLOBAL MANDATORY STYLES -->
	<%@include file="/common.jsp" %>
	<link rel="stylesheet" href="<%=basePath %>/css/mall.css">
</head>
<body>


<div class="container" style="width:100%;">

	<div class="row" style="border:0;margin-top:-10px;">
		<div class="col-sm-12">
			<h3 class="zs-iframe-title">历史订单</h3>
		</div>
	</div>

	<div class="row" style="margin-top:10px;">
		<div class="col-sm-2"  style="padding-left:0px;text-align:right;">
			<label for="name" style=" text-align:center;padding-top:7px;">客户手机号</label>
		</div>
		<div class="col-sm-2"  style=" padding-left:0px;">
			<input type="text" id="name" name="name" class="form-control" style="float:left" >
		</div>

		<div class=" col-sm-2" align="left">
			<button type="button" class="btn btn-default zs-btn-default" onclick="search()">查询</button>
		</div>

	</div>
</div>

<div style="margin-top:10px;">
	<div class="col-sm-12">
	 <table class="table table-hover">
   		<thead>
          <tr>
            <th width="110">订单编号</th>
            <th width="110">用户昵称</th>
            <th width="110">付款时间</th>  
            <th width="110">订单金额</th>
            <th width="110">操作</th>            
          </tr>
        </thead>
        <tbody>
	        <%
	        	if(null != list && list.size() != 0){
	        		for(int i = 0; i < list.size(); i ++){
	        			BmOrder order = list.get(i);
	        			%>
				          <tr>           
					        <td align="center"><%=order.getId() %></td>
					        <td align="center"><%=order.getUserName() %></td>
					        <td align="center"><%=order.getPayTime() %></td> 
					        <td align="center"><%=order.getRealTotal() %></td>
					        <td align="center">
					        	<a onclick="showOrderDetail('<%=order.getId() %>')">查看详情</a>

					        	<!--<button type="button" class="btn btn-default"><%="1".equals(order.getStatus()) ? "待付款" : "" %><%="2".equals(order.getStatus()) ? "待发货" : "" %><%="3".equals(order.getStatus()) ? "待收货" : "" %><%="4".equals(order.getStatus()) ? "已完成" : "" %></button>				        	-->
					        </td>  		      
					      </tr>
	        			<%
	        		}
	        	}
	        %>
   
        </tbody>
  </table>
  
  <nav style="float:right;">	
		  <ul class="pagination" >
		    <li>
		      <a href="javascript:prePage()" aria-label="Previous">
		        <span aria-hidden="true" >上一页</span>
		       
		      </a>
		    </li>			    
		    <%
		    	if(null != data && data.length != 0){
		    		for(int x = 0; x < data.length; x ++){
		    			
		    			if(currentPage == data[x]){
		    	 			%>
		    	 			<li><a style="background:#E0E0E0" href="javascript:toPage(<%="'" + data[x] + "'"%>)"><%=data[x] %></a></li>
		    	 			<%
		    	 		}else{			    			
		    			%>
		    			<li><a href="javascript:toPage(<%="'" + data[x] + "'"%>)"><%=data[x] %></a></li>
		    			<%  
		    	 		}
		    		}
		    	}
		    %>
		   
		    <li>
		      <a href="javascript:nextPage()" aria-label="Next">
		        <span aria-hidden="true">下一页</span>
		      </a>
		    </li>
		  </ul>
		</nav>		
  
	</div>
</div>

 <!--添加 -->
<div class="modal fade" id="orderDetail" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" >
  <div class="modal-dialog" role="document">
    <div class="modal-content" id="modal-content">
      <div class="modal-header" style="height:30px;padding-bottom:60px; !important">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        
         <form class="navbar-form navbar-left" role="search">
         	<div class="form-group">
         		<label >搜索添加管理员</label>
         	</div>
	        <div class="form-group">
	          <input type="text" id="searchMobile" class="form-control" placeholder="Search">
	        </div>
	        <button type="button" class="btn btn-default" onclick="query()">Submit</button>
	      </form>
        
      </div>      
      <div class="modal-body" id="modal-body">
    	<form id="add"  class="form-horizontal" id="formAppend">
	     <input type="hidden" name="tokenId" value="<%=tokenId %>"/>
		  <div class="form-group">
		    <label for="inputEmail3" class="col-sm-2 control-label">姓名</label>
		    <div class="col-sm-4">
		      <label for="inputEmail3" id="contactorName" class="col-sm-4 control-label"></label>
		      <!-- <input type="text" class="form-control" id="contactorName" name="contactorName" placeholder="姓名" > -->
		    </div>
		  </div>
		  <div class="form-group">
		    <label for="inputPassword3" class="col-sm-2 control-label">联系电话</label>
		    <div class="col-sm-4">
		    	<label for="inputEmail3" id="telephone" class="col-sm-4 control-label"></label>
		     <!--  <input type="text" class="form-control" id="telephone" name="telephone" placeholder="联系电话" > -->
		    </div>
		  </div>
		 		  
		</form>
      </div>
      <div class="modal-footer" id="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal" >取消</button>
        <button type="button" class="btn btn-primary"  >保存</button>
      </div> 
    </div>
  </div>
</div> 


<input type="hidden" id = "currentPage" value="<%=currentPage %>"> 
<input type="hidden" id = "isNext" value="<%=isNext %>"> 
<input type="hidden" id = "length" value="<%=length %>"> 

<script>

function search(){
	$("#searchForm").attr("action",toServerPageUrl("/bm/order/querySellerOrderList.do"));
	$("#searchForm").submit();
	
}

function showOrderDetail(orderId){
	
	window.location=toServerPageUrl("/bm/order/querySellerOrderDetail.do?orderId=" + orderId);

}

var currentPage = $("#currentPage").val();
var isNext = $("#isNext").val();
var length = $("#length").val();

/* 页数跳转 */
function toPage(data){
	var status = $("#status").val();
	var start = (data - 1) * length;
	window.location=toServerPageUrl("/bm/order/querySellerOrderList.do?length=" +  length + "&start=" + start + "&status=" + status);
}
/* 下一页  */
function nextPage(data){
	var status = $("#status").val();
	if(isNext == 'true'){
		var start = currentPage * length;
		window.location=toServerPageUrl("/bm/order/querySellerOrderList.do?length=" +  length + "&start=" + start + "&status=" + status);
	}else{
		
	}
}
/* 上一页  */
function prePage(data){
	var status = $("#status").val();
	if(currentPage == '1'){
		
	}else{
		var start = (currentPage - 2) * length;
		window.location=toServerPageUrl("/bm/order/querySellerOrderList.do?length=" +  length + "&start=" + start + "&status=" + status);
	}
}

</script>       
	
</body>
