<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.lang.String"%>
<%@page import="com.github.pagehelper.PageInfo"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%@page import="com.handany.base.common.Constants"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
PageInfo<Map> pageInfo = (PageInfo<Map>)request.getAttribute("data");
List<Map> list = (List<Map>)pageInfo.getList();

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

String picPath = Constants.IMAGE_SERVER;
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
	<h3 class="zs-iframe-title">历史客户</h3>
	</div>
	</div>

<div class="data_content row" style="margin-top:10px;">
	<div class="col-sm-12" style="magin-left:10px; ! important">
	 <table class="table table-hover" >
    <thead style="vertical-align: left;">
       <tr>
         <th width="110">顾客头像</th>
         <th width="110">顾客昵称</th>
         <th width="110">联系方式</th>
         <th width="110">时间</th>           
       </tr>
     </thead>
     <tbody>
        <%
        	if(null != list && list.size() != 0){
        		for(int i = 0; i < list.size(); i ++){
        			Map map = list.get(i);
        			%>
        			 <tr>           
				        <td align="center">
				          <%
				          	if(map.get("picUrl") != null){
				          		%>
				          		<img class="img-circle" style="width:50px; height:50px;" src="<%=picPath + (String)map.get("picUrl") %>">
				          		<%
				          	}else{
				          		%>
				          		<img class="img-circle" style="width:50px; height:50px;" src="<%=basePath %>image/hd.png">
				          		<%
				          	}
				          %>
				        </td>
				        <td align="center"><%=(String)map.get("name") == null ? "" : (String)map.get("name")%></td>
				        <td align="center"><%=(String)map.get("phone") == null ? "" : (String)map.get("phone")%></td>  
				        <td align="center"><%=(String)map.get("lastModified") == null ? "" : (String)map.get("lastModified")%></td> 		      
				      </tr>
        			
        			
        			<%
        		}
        	}
        %>
      <%-- <tr>           
        <td align="center"><img style="width:50px; height:50px;" src="<%=basePath %>image/hd.png"></td>
        <td align="center">cy114110</td>
        <td align="center">18766177609</td>  
        <td align="center">2015-11-20</td> 		      
      </tr> --%>
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
</div>


<input type="hidden" id = "currentPage" value="<%=currentPage %>"> 
<input type="hidden" id = "isNext" value="<%=isNext %>"> 
<input type="hidden" id = "length" value="<%=length %>"> 
    
<script>

var currentPage = $("#currentPage").val();
var isNext = $("#isNext").val();
var length = $("#length").val();


/* 页数跳转 */
function toPage(data){
	var start = (data - 1) * length;
	window.location=toServerPageUrl("/bm/stored/queryByShopId.do?length=" +  length + "&start=" + start);
}
/* 下一页  */
function nextPage(data){
	if(isNext == 'true'){
		var start = currentPage * length;
		window.location=toServerPageUrl("/bm/stored/queryByShopId.do?length=" +  length + "&start=" + start);
	}
}
/* 上一页  */
function prePage(data){
	if(currentPage == '1'){
		
	}else{
		var start = (currentPage - 2) * length;
		window.location=toServerPageUrl("/bm/stored/queryByShopId.do?length=" +  length + "&start=" + start);
	}
}

</script>       
	
</body>
