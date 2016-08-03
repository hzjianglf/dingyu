<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@page import="com.handany.bm.model.*"%>
<%@page import="java.lang.String"%>
<%@page import="com.github.pagehelper.PageInfo"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
PageInfo<BmShop> pageInfo = (PageInfo<BmShop>)request.getAttribute("data");
List<BmShop> list = (List<BmShop>)pageInfo.getList();

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
<html lang="zh-CN">

<head>
 <%@include file="/common.jsp" %>
<title>商铺管理列表</title>
	<link rel="stylesheet" href="<%=basePath %>/css/mall.css">

</head>
<body>

<div class="container" style="width:100%;">

	<div class="row" style="border:0;margin-top:-10px;">
	<div class="col-sm-12">
	<h3 class="zs-iframe-title">入驻商铺情况1</h3>
	</div>
	</div>


	<div class="row" >
	<label for="name" class="col-xs-2 col-sm-2" style=" text-align:right;padding-top:7px;">商铺名称</label>
	<div class="col-sm-4 col-xs-4"  style=" padding-left:0px;">
	<input type="text" id="shopName" class="form-control" style="float:left" value="<%=(String)request.getParameter("shopName") == null ? "" : (String)request.getParameter("shopName") %>" >
	</div>
	</div>


	<div class="row" style="margin-top: 10px;">

	<label for="pClass" class="col-xs-2 col-sm-2" style="text-align:right; padding-top:7px;">地区</label>
	<div class="col-xs-2 col-sm-2"><select class="form-control zs-select-input" id='province' onchange='searchCity()'></select></div>
	<div class="col-xs-2 col-sm-2"><select class="form-control zs-select-input" id='city'  onchange='searchCounty()'></select></div>
	<div class="col-xs-2 col-sm-2"><select class="form-control zs-select-input" id='county' ></select> </div>

	<div class="col-xs-2 col-sm-2">
	<button type="button" class="btn btn-default zs-btn-default" onclick="search()">查询</button>

	</div>

	</div>

<%--<nav class="navbar navbar-default navbar-fixed-top row" style="border:0" >--%>
  <%--<ol class="breadcrumb">--%>
	  <%--<li style="*float:left"><a href="#">系统管理员</a></li>--%>
	  <%--<li style="*float:left"><a href="#">入驻商铺情况</a></li>--%>
	  <%----%>
  <%--</ol>--%>
    <%----%>
  <%----%>
<%--</nav>--%>
<div class="data_content row" style="margin-top:10px;">
	<div class="col-sm-12">
	 <table class="table table-hover">
    <thead>
          <tr>
            <th width="110">商铺名称</th>
            <th width="110">联系方式</th>
            <th width="220">经营范围</th>
            <th width="110">地区</th>
            <th width="110">认证时间</th> 
            <th width="110">服务费率</th>
            <th width="110">是否支持货到付款</th>
          </tr>
        </thead>
        <tbody>

	    <%
         	if(list != null && list.size() != 0){
         		for(int i = 0; i < list.size(); i ++){
         			BmShop shop = list.get(i);
         			%>
         		  <tr >           
		            <td align="center"><%=shop.getName() == null ? "" : shop.getName() %></td>
		            <td align="center"><%=shop.getTelephone() == null ? "" : shop.getTelephone() %></td>
		            <td align="center"><%=shop.getBusiScope() == null ? "" : shop.getBusiScope()%></td>
		            <td align="center"><%=(shop.getRegion1Name() == null ? "" : shop.getRegion1Name()) + (shop.getRegion2Name() == null ? "" : shop.getRegion2Name()) + (shop.getRegion3Name() == null ? "" : shop.getRegion3Name()) %></td>  
		            <td align="center"><%=shop.getIdentiTime() %></td>               
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
		    			%>
		    			<li><a style="<%=currentPage == data[x] ? "background:#E0E0E0" : "" %>" href="javascript:toPage(<%="'" + data[x] + "'"%>)"><%=data[x] %></a></li>
		    			
		    	 		<%
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
</div>


<input type="hidden" id = "currentPage" value="<%=currentPage %>"> 
<input type="hidden" id = "isNext" value="<%=isNext %>"> 
<input type="hidden" id = "length" value="<%=length %>"> 
<input type="hidden" id="tokenId" value="<%=tokenId %>" /> 

<input type="hidden" id="region1" value="<%=(String)request.getParameter("region1") %>" />
<input type="hidden" id="region2" value="<%=(String)request.getParameter("region2") %>" />
<input type="hidden" id="region3" value="<%=(String)request.getParameter("region3") %>" />
<script src="<%=basePath %>js/region.js"></script>
<script type="text/javascript">

var region1 = $("#region1").val();
var region2 = $("#region2").val();
var region3 = $("#region3").val();

$(document).ready(function(){	
	
	if(region1 != 'null' && region1 != '-1'){

		if('-1' == region2){
			region2 = '0';
		}
		if('-1' == region3){
			region3 = '0';
		}
		setDefaultSelect(region1, region2, region3);
	}		  			
}); 
function search(){
	var province = $("#province").val();
	var city = $("#city").val();
	var district = $("#county").val(); 
	var shopName = $("#shopName").val(); 
	if(province == '0' || typeof(province) == 'undefined'){
		province = '-1';
		city = '-1';
		district = '-1';
	}
	if(city == '0' || typeof(city) == 'undefined' || null == city){
		city = '-1';
		district = '-1';
	}
	if(district == '0' || typeof(district) == 'undefined' || null == district){
		district = '-1';
	}
	window.location=toServerPageUrl("/pm/admin/shopManage.do?region1=" + province + "&region2=" + city+ "&region3=" + district + "&shopName=" + shopName);
	
	
}


var tokenId = $("#tokenId").val();
var currentPage = $("#currentPage").val();
var isNext = $("#isNext").val();
var length = $("#length").val();
/* 页数跳转 */
function toPage(data){
	var province = $("#province").val();
	var city = $("#city").val();
	var district = $("#county").val(); 
	var shopName = $("#shopName").val(); 
	if(province == '0' || typeof(province) == 'undefined'){
		province = '-1';
		city = '-1';
		district = '-1';
	}
	if(city == '0' || typeof(city) == 'undefined' || null == city){
		city = '-1';
		district = '-1';
	}
	if(district == '0' || typeof(district) == 'undefined' || null == district){
		district = '-1';
	}
	var start = (data - 1) * length;
	window.location=toServerPageUrl("/pm/admin/shopManage.do?length=" +length + "&start=" + start + "&region1=" + province + "&region2=" + city+ "&region3=" + district + "&shopName=" + shopName);
}
/* 下一页  */
function nextPage(data){
	var province = $("#province").val();
	var city = $("#city").val();
	var district = $("#county").val(); 
	var shopName = $("#shopName").val(); 
	if(province == '0' || typeof(province) == 'undefined'){
		province = '-1';
		city = '-1';
		district = '-1';
	}
	if(city == '0' || typeof(city) == 'undefined' || null == city){
		city = '-1';
		district = '-1';
	}
	if(district == '0' || typeof(district) == 'undefined' || null == district){
		district = '-1';
	}
	if(isNext == 'true'){
		var start = currentPage * length;
		window.location=toServerPageUrl("/pm/admin/shopManage.do?length=" +length + "&start=" + start + "&region1=" + province + "&region2=" + city+ "&region3=" + district + "&shopName=" + shopName);
	}
}
/* 上一页  */
function prePage(data){
	var province = $("#province").val();
	var city = $("#city").val();
	var district = $("#county").val(); 
	var shopName = $("#shopName").val(); 
	if(province == '0' || typeof(province) == 'undefined'){
		province = '-1';
		city = '-1';
		district = '-1';
	}
	if(city == '0' || typeof(city) == 'undefined' || null == city){
		city = '-1';
		district = '-1';
	}
	if(district == '0' || typeof(district) == 'undefined' || null == district){
		district = '-1';
	}
	if(currentPage == '1'){
		
	}else{
		var start = (currentPage - 2) * length;
		window.location=toServerPageUrl("/pm/admin/shopManage.do?length=" +length + "&start=" + start + "&region1=" + province + "&region2=" + city+ "&region3=" + district + "&shopName=" + shopName);
	}
}

</script>


</body>
</html>