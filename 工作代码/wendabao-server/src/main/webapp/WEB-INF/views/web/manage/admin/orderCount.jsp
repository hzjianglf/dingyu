<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@page import="com.handany.bm.model.*"%>
<%@page import="com.handany.rbac.model.*"%>
<%@page import="java.lang.String"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%
String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
+ request.getContextPath() + "/";
String tokenId = (String)request.getAttribute("tokenId");
List<BmProxyRegion> list = (List<BmProxyRegion>)request.getAttribute("data");
double total = (Double)request.getAttribute("allOrderFee");
int count = (Integer)request.getAttribute("orderCount");

%>
<!DOCTYPE html>
<html lang="zh-CN">

<head>
 <%@include file="/common.jsp" %>
 <script src="<%=basePath %>js/jquery-1.11.3.min.js"></script>	
 <script src="<%=basePath %>js/admin/jquery-ui-1.10.4.custom.min.js"></script>
 <script src="<%=basePath %>js/admin/jquery-ui-timepicker-addon.js"></script>
 <script src="<%=basePath %>js/admin/jquery-ui-timepicker-zh-CN.js"></script>
 <script src="<%=basePath %>js/admin/jquery.ui.datepicker-zh-CN.js"></script>
<link rel="stylesheet" href="<%=basePath %>css/admin/jquery-ui.css">
<title>商铺统计管理</title>
<style type="text/css">
	.headImg{
		width:50px;
		height:50px;
		margin:-7px;
	}
</style>
</head>
<body>
<div class="container" style="width:100%;padding:10px;">

<label>商铺订单统计  </label>
<div class="panel panel-info">
  <!-- Default panel contents -->

  <div class="panel-heading" >

   <div class="row" >
      <div class="col-sm-1"  style="">
         <label for="name"  style="padding-top:5px;float:right;" >起始时间</label>
        
      </div>
      <div class="col-sm-3"  style="">
      	 <input name="act_start_time"  id="start_time" type="text" class="text-box" value="" placeholder="开始时间" title="开始时间" readonly="readonly" style="cursor:pointer;"/>
       
      </div>
      <div class=" col-sm-1"   style="">
         <label for="pClass"  style="padding-top:5px;float:right" >结束时间</label>
      </div>
      <div class=" col-sm-3"  style="">
      	 <input name="act_stop_time" id="end_time" type="text" class="text-box" value="" placeholder="结束时间" title="结束时间" readonly="readonly" style="cursor:pointer;"/>       	
      </div>
      <div class="col-sm-2"><button type="button" class="btn btn-primary" onclick="search()">查询</button>    </div>
   </div><br/>
	
		
  </div>
</div>
  <!-- Table -->
  <table class="table table-hover">
    <thead>
          <tr>
            
            <th>订单总数</th>
            <th>总金额</th>
            
          </tr>
        </thead>
        <tbody>
         
          <tr >          
          <td id = "count"><%=count %>单</td>
            <td id = "total"><%=total %>元</td>          
          </tr>
          
       
        </tbody>
  </table>
</div>
</div>
<input type="hidden" id="tokenId" value="<%=tokenId %>"/>
<script type="text/javascript">
	$( "input[name='act_start_time'],input[name='act_stop_time']" ).datetimepicker();
	var tokenId = $("#tokenId").val();
	function search(){
		var start = $("#start_time").val();
		var end = $("#end_time").val();
		$.ajax({
			url:"orderCountByCondition.do?date=" + new Date(),
			data:{
				"start":start,
				"end":end,
				"tokenId":tokenId,
			},
			type:"post",
			dataType:"json",
			success:function(data){
				if(data.header.success == true){
					var count = data.orderCount;
					var total = data.allOrderFee;
					$("#count").html(count);
					$("#total").html(total);
				}
			}
		});
	}
</script>
</body>
</html>














