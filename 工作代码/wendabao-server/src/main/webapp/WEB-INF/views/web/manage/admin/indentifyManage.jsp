<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.handany.bm.model.*"%>
<%@page import="java.lang.String"%>
<%@page import="com.github.pagehelper.PageInfo"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%@page import="com.handany.base.common.Constants"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
PageInfo<BmShop> pageInfo = (PageInfo<BmShop>)request.getAttribute("data");
List<BmShop> list = (List<BmShop>)pageInfo.getList();
String picPath = Constants.IMAGE_SERVER;
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
    <title>采供网</title>
<%@include file="/common.jsp" %>
<meta content="" name="description"/>
<meta content="" name="author"/>
	<link rel="stylesheet" href="<%=basePath %>/css/mall.css">
	<script src="<%=basePath %>/js/mallDialog.js"></script>
	<style type="text/css">
	.show-large-image{
		max-width: 400px;
	}
	</style>
</head>
<body>
<div class="container" style="width:100%;">
	<div class="row" style="border:0;margin-top:-10px;">
	<div class="col-sm-12">
	<h3 class="zs-iframe-title">商户认证审批</h3>
	</div>
	</div>
<div class="data_content row" style="margin-top:10px;">
	<div class="col-sm-12">
	 <table class="table table-hover">
    <thead>
          <tr>
            <th width="114">商铺名称</th>
           
            <th width="110">联系方式</th>    
             <th width="110">区域</th>  
             <th width="110">缴纳保证金</th> 
              <th width="110">申请时间</th>  
            <th width="110">操作</th>     
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
		            <td align="center"><%=(shop.getRegion1Name() == null ? "" : shop.getRegion1Name()) + (shop.getRegion2Name() == null ? "" : shop.getRegion2Name()) + (shop.getRegion3Name() == null ? "" : shop.getRegion3Name()) %></td>  
		            <th align="center"><%=shop.getAssureFee() == null ? "0" : shop.getAssureFee()%></th> 
		            <td align="center"><%=shop.getLastModified() == null ? "" : shop.getLastModified()%></td>			           
		            <td align="center">		            
		            	<a onclick="shopDetailShow(<%="'" + shop.getId() + "'" %>)">详情</a>
		            	<%-- <button type="button" class="btn btn-default" onclick="updateStatus(<%="'" + shop.getId() + "'" %>)">通过</button>	 --%>            
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
</div>



<!-- 商铺详情Modal -->
<div class="modal fade" id="shopDetailModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">商铺认证详情</h4>
      </div>
      <div class="modal-body">
      <div class="container">
        <div class="row">
        	<label class="col-sm-2 control-label">名称</label>
        	<div class="col-sm-4">        	
        		<input class="form-control" id="shopName" type="text" disabled>       	
			</div>
        </div><br />
        
	   <div class="row">
        	<label class="col-sm-2 control-label">联系方式</label>
        	<div class="col-sm-4">
				<input class="form-control" id="telephone" type="text" disabled> 
			</div>
        </div> <br />
        
        <div class="row">
        	<label class="col-sm-2 control-label">经营范围</label>
        	<div class="col-sm-4">
        	
        		<textarea class="form-control" id="busiScope" rows="3" disabled></textarea>
        	
			</div>
        </div> <br />
        
        <div class="row">
        	<label class="col-sm-2 control-label">代理区域</label>
        	<div class="col-sm-4">
				<input class="form-control" id="address" type="text" disabled> 
			</div>
        </div><br />
        <div class="row">
        	<label class="col-sm-2 control-label">商铺门头照</label>
        	<div class="col-sm-4"  id="shop_lic">
				
			</div>
        </div><br />
        <div class="row">
        	<label class="col-sm-2 control-label">业主身份证</label>
        	<div class="col-sm-4"  id="shop_tax">
				
			</div>
        </div><br />
        <div class="row">
        	<label class="col-sm-2 control-label">营业执照</label>
        	<div class="col-sm-4"  id="shop_org">
        						
			</div>
        </div>
        
	</div>
      </div>
      <div class="modal-footer">
      
      		<div class="row">        	
	        	<div class="col-sm-offset-3 col-sm-2">
	        		<button type="button" class="btn btn-default" data-dismiss="modal" onclick="updateCloseStatus()">拒绝</button>				
				</div>
				<div class="col-sm-offset-1 col-sm-2">
	        		<button type="button" class="btn btn-default" onclick="updateOpenStatus()">通过</button>			
				</div>
       		</div>
      
      </div>
    </div>
  </div>
</div>

<input type="hidden" id = "currentPage" value="<%=currentPage %>"> 
<input type="hidden" id = "isNext" value="<%=isNext %>"> 
<input type="hidden" id = "length" value="<%=length %>"> 
<input type="hidden" id="tokenId" value="<%=tokenId %>"/>  
<input type="hidden" id="picPath" value="<%=picPath %>"/>       
<script>
var tokenId = $("#tokenId").val();
var currentPage = $("#currentPage").val();
var isNext = $("#isNext").val();
var length = $("#length").val();
var picPath = $("#picPath").val();
var update_shopId = null;
function shopDetailShow(shopId){
	clearData();
	$.ajax({
		url:toServerUrl("/bm/shop/open/queryById.do"),
		data:{
			shopId:shopId,
		},
		type:"post",
		dataType:"json",
		success:function(data){
			if(data.header.success){
				var shop = data.data;
				update_shopId = shop.id;
				$("#shopName").val(shop.name);
				
				$("#telephone").val(shop.telephone);
				
				$("#address").val((shop.region1Name == null ? "" : shop.region1Name) + (shop.region2Name == null ? "" : shop.region2Name) + (shop.region3Name == null ? "" : shop.region3Name));
				$("#busiScope").html(shop.busiScope == null ? "" : shop.busiScope);				
			}
		}
	});
	
	$.ajax({
		
		url:toServerUrl("/bm/picture/queryShopIdentifyPic.do"),
		data:{
			shopId:shopId,
		},
		type:"post",
		dataType:"json",
		success:function(data){
			if(data.header.success){
				var pics = data.data;
				if(pics != null && pics.length != 0){
					for(var i = 0; i < pics.length; i ++){
						var pic = pics[i];
						//门头照：shop_lic 身份证：shop_tax 营业执照：shop_org
						if(pic.type == 'shop_lic'){
							$("#shop_lic").html("<img class='img-rounded show-large-image'  src='"+picPath + pic.url +"' realUrl='"+pic.realUrl+"'/>");
						}
						if(pic.type == 'shop_tax'){
							$("#shop_tax").html("<img class='img-rounded show-large-image'  src='"+picPath + pic.url +"' realUrl='"+pic.realUrl+"'/>");
						}
						if(pic.type == 'shop_org'){
							$("#shop_org").html("<img class='img-rounded show-large-image'  src='"+picPath + pic.url +"' realUrl='"+pic.realUrl+"'/>");
						}


					}

					$(".show-large-image").on("click",function(){
						var imgPath = $(this).attr("realUrl");
						new MallDialog("image", picPath + imgPath);
					})
				}			
			}
		}
	});
	
	$("#shopDetailModal").modal("show");
}
/* 
function updateStatus(shopId){	
	window.location='updateShopIdentifyStatus.do?tokenId='+tokenId + '&shopId=' + shopId;	
} */
function clearData(){
	$("#shop_lic").empty();
	$("#shop_tax").empty();
	$("#shop_org").empty();
	$("#shopName").val("");
	$("#telephone").val("");
	$("#busiScope").html("");
	$("#address").val();
}
/* 通过  */
function updateOpenStatus(){
	clearData();
	window.location=toServerPageUrl("/bm/shop/updateShopIdentifyOpenStatus.do?shopId=" + update_shopId);	
}
/* 拒绝   */
function updateCloseStatus(){	
	clearData();
	window.location=toServerPageUrl("/bm/shop/updateShopIdentifyCloseStatus.do?shopId=" + update_shopId);	
}

/* 页数跳转 */
function toPage(data){
	var start = (data - 1) * length;
	window.location=toServerPageUrl("/bm/shop/queryIndentifyShop.do?length="  + length + "&start=" + start);
}
/* 下一页  */
function nextPage(data){
	if(isNext == 'true'){
		var start = currentPage * length;
		window.location=toServerPageUrl("/bm/shop/queryIndentifyShop.do?length="  + length + "&start=" + start);
	}
}
/* 上一页  */
function prePage(data){
	if(currentPage == '1'){
		
	}else{
		var start = (currentPage - 2) * length;
		window.location=toServerPageUrl("/bm/shop/queryIndentifyShop.do?length="  + length + "&start=" + start);
	}
}

</script>       
	
</body>
