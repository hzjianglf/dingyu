<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>商品显示</title>
    <jsp:include page="testCookie.jsp" flush="true" />
    <meta http-equiv="X-UA-Compatible" content="IE=8,9,10" >
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" type="text/css" href="<%=basePath%>manager/css/default.css">
<link rel="stylesheet" type="text/css" href="<%=basePath%>manager/themes/icon.css">
<link rel="stylesheet" type="text/css" href="<%=basePath%>manager/css/demo.css">
<link id="easyuiTheme" rel="stylesheet"href="<%=basePath%>manager/themes/default/easyui.css" type="text/css">
<script type="text/javascript" src="<%=basePath%>manager/js/jquery-1.8.0.min.js"></script>

<script src="<%=basePath%>manager/js/TQEditor/TQEditor.full.min.js"
	type="text/javascript"></script>
<script src="<%=basePath%>manager/js/TQEditor/TQEditor.min.js" 
	type="text/javascript"></script>
<link href="<%=basePath%>manager/js/TQEditor/TQEditor.css" rel="stylesheet"
	type="text/css" />
<link href="<%=basePath%>manager/js/TQEditor/QuirksMode.css" rel="stylesheet"
	type="text/css" />
	<script src="<%=basePath%>manager/js/TQEditor/TQEditor.full.min.js"
	type="text/javascript"></script>
<script src="<%=basePath%>manager/js/TQEditor/TQEditor.min.js"
	type="text/javascript"></script>
<link href="<%=basePath%>manager/js/TQEditor/TQEditor.css" rel="stylesheet"
	type="text/css" />
<link href="<%=basePath%>manager/js/TQEditor/QuirksMode.css" rel="stylesheet"
	type="text/css" />


<link href="<%=basePath%>manager/js/kindeditor/themes/default/default.css"/>
<script src="<%=basePath%>manager/js/kindeditor/kindeditor.js" type="text/javascript"></script>
<script src="<%=basePath%>manager/js/kindeditor/kindeditor/lang/zh_CN.js" type="text/javascript"></script>
<script type="text/javascript" src="<%=basePath%>manager/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=basePath%>manager/js/jquery.cookie.js"></script>
<script type="text/javascript" src="<%=basePath%>manager/js/hd.js"></script>
<script type="text/javascript" src="<%=basePath%>manager/js/jquery.form.js"></script>
<script type="text/javascript" src="<%=basePath%>manager/js/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript" src="<%=basePath%>manager/js/WebCalendar.js"></script>
<script type="text/javascript" src="<%=basePath%>manager/js/vproduct.js"></script>
	<script type="text/javascript" src="<%=basePath%>manager/js/ajaxfileupload.js"></script>
  
<style type="text/css">


#input-file { 
position: relative; /* 保证子元素的定位 */ 
width: 120px; 
height: 30px; 
background: #eee; 
border: 1px solid #ccc; 
text-align: center; 
cursor: pointer; 
} 
#text { 
display: inline-block; 
margin-top: 5px; 
color: #666; 
font-family: "黑体"; 
font-size: 18px; 
} 
#file { 
display: block; 
position: absolute; 
top: 0; 
left: 0; 
width: 120px; /* 宽高和外围元素保持一致 */ 
height: 30px; 
opacity: 0; 
-moz-opacity: 0; /* 兼容老式浏览器 */ 
filter: alpha(opacity=0); /* 兼容IE */ 
} 







#select,#addProduct,#update,#toolbar{

	visibility: hidden;

}

.serchTest {
	_margin-top: -20px;
	_margin-bottom: -18px;
}

*+html #serchTest {
	*margin-top: -20px;
	*margin-bottom: -18px;
}

.test a {
	vertical-align: center;
	display: block;
	float: left;
	height: 20px;
	padding-bottom: 2px;
	border: 1px solid #EFEFEF;
}

.test a:link {
	text-decoration: none;
	color: #000;
}

.test a:active {
	text-decoration: none;
	color: #000
}

.test a:visited {
	text-decoration: none;
	color: #000
}

.test a:hover {
	text-decoration: none;
	color: #000;
	background: #eaf2ff;
	border: 1px solid #dddddd;
}

.xx21 {
	margin: 10px 10px 10px 10px;
	border: 1px solid #91ABD1;
	border-radius: 8px;
	
}

.kehus{
/* background:#FFFFFF; */

background:#E2EDFF;
border:none;

}

.kehustable{
   width:700px;
    border-style: dotted; 
    border-color: #CCCCCC; 
    border-width: 1px; 
     margin:10px auto;
   

  }
  
  * html .kehustable{
   width:700px;
    border-style: dotted; 
    border-color: #CCCCCC; 
    border-width: 1px; 
     margin:10px auto;
}

  *+html .kehustable{
   width:700px;
    border-style: dotted; 
    border-color: #CCCCCC; 
    border-width: 1px; 
     margin:10px auto;
}

  .kehustable td{ 
    border-style: dotted; 
    border-color: #CCCCCC; 
    border-width: 1px;}

.liuyanban{
    width:700px;
    border-style: dotted; 
    border-color: #CCCCCC; 
    border-width: 1px; 
    margin:10px auto;

  }
  
  .liuyanban td{ 
    border-style: dotted; 
    border-color: #CCCCCC; 
    border-width: 1px;}
    
    .beijing{
  background:#E5F0F4;
    
    }
    
  .shuangji{
   background:#E2EDFF; 
   BORDER:1px solid #91ABD1;
   }
.ww{border:1px solid #26A0DA}
</style>

  </head>
  
  <body style="background-color:white;padding:0px;">
   <table id="product">
	</table>

<div id="toolbar" class="test">

		<table width="100%">
			<tr>
				<td><form id="query"><table align="left">
						<tr>
							<td>
							<span style="color:#3967a3 ">商品名称:</span><input type="text" name="proName" id="proName" value="" class="ww">
							</td>
							<td>
							<span style="color:#3967a3 ">商品编号:</span><input type="text" name="proNum" id="proNum" value=""class="ww">
							</td>
							<td>
							<span style="color:#3967a3 ">商品品牌:</span><input type="text" name="proTime" id="proTime" value="" class="ww">
							</td>
							<td align="left"><a href="javascript:query()"><div style="padding-top: 5px;"></div>&nbsp;<span
												class="icon-search">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;查询</span>&nbsp;&nbsp;</a>
							</td>
						</tr>
					</table></form>
				</td>
				<td><table align="right">
						<tr>
							<td><a href="javascript:AddPro()"><div
										style="padding-top: 5px;"></div>&nbsp;<span class="icon-add">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;添加</span>&nbsp;&nbsp;</a>
							</td>
							<td><a href="javascript:UpdatePro()"><div
										style="padding-top: 5px;"></div>&nbsp;<span class="icon-edit">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;修改</span>&nbsp;&nbsp;</a>
							</td>

							<td><a href="javascript:DeletePro()"><div
										style="padding-top: 5px;"></div>&nbsp;<span
									class="icon-remove">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;删除</span>&nbsp;&nbsp;</a>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</div>
	
	<!--双击查看  -->
	<div id="select" scroll="no" class="easyui-window" title="商品信息" style="background:#E2EDFF;hidden;width:600px;height:600px;left:380px;top:20px;[;height:550px;];*height:550px;height:550px\9\0;"
        iconCls="icon-edit" data-options="modal:true,closed:true"
		closed="true" maximizable="false" minimizable="false"
		collapsible="false">
     
   <div  style="width:550px;margin:0 auto; margin-top: 20px;*margin-left:15px; ">
			<div class="xx21" >
			  <table  class="test"  width="500px" height="100px"   border="0"  cellspacing="0" cellpadding="0" style="margin: 10px 10px 10px 10px;">
		     
		      <tr>
		        <td  height="30px"  align="right">商品编号：</td>
		        <td> <input type="text" id="productNum2" readonly="readonly" class="shuangji" name="product.productNum"  class="ww"/></td>
		           <td  height="30px"  align="right">商品名称：</td>
		        <td> <input type="text" id="productName2" readonly="readonly" class="shuangji" name="product.productName" /></td>
		      </tr>
		         <tr>
		        <td  height="30px"  align="right">商品品牌：</td>
		        <td> <input type="text" id="productBrand2"readonly="readonly" class="shuangji" name="product.productBrand" /></td>
		           <td  height="30px"  align="right">商品库存：</td>
		        <td> <input type="text" id="inventoryNum2" readonly="readonly" class="shuangji"name="product.inventoryNum" /></td>
		      </tr>
		        <tr>
		        <td  height="30px"  align="right">商品价格：</td>
		        <td> <input type="text" id="price2" readonly="readonly" class="shuangji" name="product.price" /></td>
		           <td  height="30px"  align="right">商品销量：</td>
		        <td> <input type="text" id="sellNum2" readonly="readonly" class="shuangji" name="product.sellNum" /></td>
		      </tr>
		        <tr>
		        <td  height="30px"  align="right">商品颜色：</td>
		        <td> <input type="text" id="productColor2"readonly="readonly" class="shuangji" name="product.productColor" /></td>
		           <td  height="30px"  align="right">上市时间：</td>
		        <td> <input type="text" id="productAddtime2"readonly="readonly" class="shuangji" name="product.productAddtime" /></td>
		      </tr>
		        <tr>
		        <td  height="30px"  align="right">商品类别：</td>
		        <td>
		        <input type="text" id="productTypes" value="${productTypes }"  class="shuangji" readonly="readonly" >
		        </td>
		        <td height="30px"  align="right">是否推荐：</td>
		        <td>
		        <input type="text" id="tuijians" class="shuangji" readonly="readonly">
		        </td>
		        </tr>
		        <tr>
		        <td height="30px"  align="right">优惠价格：</td>
		        <td><input type="text" id="cheapPrice2" readonly="readonly" class="shuangji" name="product.cheapPrice" /></td>
		        <td height="30px"  align="right">上架时间：</td>
		        <td><input type="text" id="productUpdateTime2" readonly="readonly" class="shuangji" name="product.productUpdateTime" /></td>
		        </tr>
		      <tr>
		        <td  height="30px"  align="right">商品图片：</td>
		        <td colspan="3"><span id="ts"></span></td>
		      </tr>
		        <tr>
		        <td  height="30px"  align="right">商品配图：</td>
		        <td colspan="3"><span id="pts"></span></td>
		      </tr>
		        <tr>
					<td align="right">
					商品概述：
					</td>
					<td colspan="3">
					<textarea readonly="readonly"   class="shuangji" cols=70 rows=7 id="productOverview" name="productOverview" style="overflow: auto;width:374px"></textarea>
					</td>
				</tr>
		       <tr>
					<td align="right">
					商品描述：
					</td>
					<td colspan="3">
					<textarea  class="shuangji" cols=70 rows=7 id="Details2" name="Details2" readonly="readonly" style="overflow: auto;width:374px"></textarea>
					</td>
				</tr>
				
		    </table>
	</div>

</div>
</div><%--
    <% 
      java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyyMMddHHmmss");
      String str_date2 = formatter.format(new Date()); //将Date型日期时间转换成字符串形式 
     %>
    
	--%><!--商品添加  -->
<div id="addProduct" scroll="no" class="easyui-window" title="商品添加" style="background:#E2EDFF;hidden;width:600px;height:600px;left:380px;top:20px;[;height:550px;];*height:550px;height:550px\9\0;"
    iconCls="icon-add" closed="true" data-options="modal:true,closed:true"
     maximizable="false" minimizable="false" collapsible="false">
   
     
 <form id="addProducts" enctype="multipart/form-data"
		method="post">
   <div  style="width:550px;margin:0 auto; margin-top: 20px;*margin-left:15px; ">
			<div class="xx21" >
			  <table  class="test"  width="500px" height="100px"   border="0"  cellspacing="0" cellpadding="0" style="margin: 10px 10px 10px 10px;">
		     
		      <tr>
		        <td  height="30px"  align="right">商品编号：</td>
		        <td> <input type="text" id="productNum" name="product.productNum" /></td>
		           <td  height="30px"  align="right">商品名称：</td>
		        <td> <input type="text" id="productName" name="product.productName" /></td>
		      </tr>
		          <tr>
		        <td  height="30px"  align="right">商品品牌：</td>
		        <td> <input type="text" id="productBrand" name="product.productBrand" /></td>
		           <td  height="30px"  align="right">商品库存：</td>
		        <td> <input type="text" id="inventoryNum" name="product.inventoryNum" /></td>
		      </tr>
		        <tr>
		        <td  height="30px"  align="right">商品价格：</td>
		        <td> <input type="text" id="price" name="product.price" /></td>
		           <td  height="30px"  align="right">商品销量：</td>
		        <td> <input type="text" id="sellNum" name="product.sellNum" /></td>
		      </tr>
		        <tr>
		        <td  height="30px"  align="right">商品颜色：</td>
		        <td> <input type="text" id="productColor" name="product.productColor" /></td>
		           <td  height="30px"  align="right">上市时间：</td>
		        <td> <input type="text" id="productAddtime" name="product.productAddtime" onclick="SelectDate(this,'yyyy/MM/dd');"/></td>
		      </tr>
		        <tr>
		        <td  height="30px"  align="right">商品类别：</td>
		        <td>
		        <select id="productType" name="productType" style="width: 70px;" >
			        <s:iterator value="listType" var="list">
			        <option value="<s:property value="#list.productTypeId"/>"><s:property value="#list.typeName"/> </option>
			        </s:iterator>
		        </select> 
		      
		         <select id="productType1" name="productType1" style="width: 70px;">
			        <s:iterator value="listType1" var="list1">
			        <option value="<s:property value="#list1.productTypeId"/>"><s:property value="#list1.typeName"/> </option>
			        </s:iterator>
		        </select> 
		        </td>
		        <td height="30px"  align="right">是否推荐：</td>
		        <td>
		        	<select id="tuijian" name="tuijian">
		        		<option value="未推荐">未推荐</option>
		        		<option value="已推荐">已推荐</option>
		        	</select>
		        
		        </td>
		        </tr>
		        <tr>
		        <td height="30px"  align="right">优惠价格：</td>
		        <td><input type="text" id="cheapPrice" name="product.cheapPrice" /></td>
		        </tr>
		        
		      <tr>
		        <td  height="30px"  align="right">商品图片：</td>
		        <td colspan="3"><input type="file" id="xpic" name="xpic" class="ww"/></td>
		      </tr>
		       <tr>
		       <td></td>
	            <td colspan="3"><a href="javascript:uploadXshopPic()">
	            <div style="padding-top: 5px;"></div>&nbsp;<span class="icon-save" style="color:red">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;上传 <font  style="color:red">*小图</font></span>&nbsp;&nbsp;</a>
	           
	            </td>
		      </tr>
		        <tr>
		        <td  height="30px"  align="right">商品配图：</td>
		        <td colspan="3"><input type="file" id="dpic1" name="dpic1" class="ww"/></td>
		      </tr>
		       <tr>
		       <td></td>
	            <td colspan="3"><a href="javascript:uploading()">
	            <div style="padding-top: 5px;"></div>&nbsp;<span class="icon-save" style="color:red">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;上传
	            <font style="color:red">*最多不能超过六张(连续上传即可)</font></span>&nbsp;&nbsp;</a>
	            
	            </td>
		      </tr>
		        <tr>
					<td align="right">
					商品概述：
					</td>
					<td colspan="3">
					<textarea   cols=75 rows=7 id="metaDetails" name="metaDetails" style="overflow: auto;width:374px"></textarea>
					</td>
				</tr>
		      <tr>
					<td align="right">
					商品描述：
					</td>
					<td colspan="3">
					<div id="pro"></div>
					</td>
				</tr>
		    </table>
				 
				 <div style="margin-left: 150px;">
				  <a class="easyui-linkbutton"  iconCls="icon-ok" href="javascript:saveProducts()">提交</a>
		   		 <a class="easyui-linkbutton" style=" margin-left:30px;"  iconCls="icon-cancel" href="javascript:void(0)" onClick="closeadd()">取消</a>
				</div>
						
			
	</div>

</div>
</form>
</div>
<!-- 修改 -->
<div id="update" scroll="no" class="easyui-window" title="商品修改" style="background:#E2EDFF;hidden;width:600px;height:600px;left:380px;top:20px;[;height:550px;];*height:550px;height:550px\9\0;"
    iconCls="icon-add" closed="true" data-options="modal:true,closed:true"
     maximizable="false" minimizable="false" collapsible="false">
     
     
 <form id="updateProducts" enctype="multipart/form-data"
		method="post">
		 <input type="hidden" id="productId" name="product.productId" />
   <div  style="width:550px;margin:0 auto; margin-top: 20px;*margin-left:15px; ">
			<div class="xx21" >
			  <table  class="test"  width="500px" height="100px"   border="0"  cellspacing="0" cellpadding="0" style="margin: 10px 10px 10px 10px;">
		    
		      <tr>
		        <td  height="30px"  align="right">商品编号：</td>
		        <td> <input type="text" id="productNum1" name="product.productNum" /></td>
		           <td  height="30px"  align="right">商品名称：</td>
		        <td> <input type="text" id="productName1" name="product.productName" /></td>
		      </tr>
		          <tr>
		        <td  height="30px"  align="right">商品品牌：</td>
		        <td> <input type="text" id="productBrand1" name="product.productBrand" /></td>
		           <td  height="30px"  align="right">商品库存：</td>
		        <td> <input type="text" id="inventoryNum1" name="product.inventoryNum" /></td>
		      </tr>
		        <tr>
		        <td  height="30px"  align="right">商品价格：</td>
		        <td> <input type="text" id="price1" name="product.price" /></td>
		           <td  height="30px"  align="right">商品销量：</td>
		        <td> <input type="text" id="sellNum1" name="product.sellNum" /></td>
		      </tr>
		        <tr>
		        <td  height="30px"  align="right">商品颜色：</td>
		        <td> <input type="text" id="productColor1" name="product.productColor" /></td>
		           <td  height="30px"  align="right">上架时间：</td>
		        <td> <input type="text" id="productAddtime1" name="product.productAddtime" onclick="SelectDate(this,'yyyy/MM/dd');"/></td>
		      </tr>
		     
		        <tr>
		        <td  height="30px"  align="right">商品类别：</td>
		        <td>
		        <select id="productType2" name="productType2" style="width: 70px;" >
		        
		        
		        </select> 
		        <select id="productType21" name="productType21" style="width: 70px;">
		        </select> 
		        </td>
		         <td height="30px"  align="right">是否推荐：</td>
		        <td>
		        	<select id="tuijian1" name="tuijian1">
		        		<option value="未推荐">未推荐</option>
		        		<option value="已推荐">已推荐</option>
		        	</select>
		        
		        </td>
		        </tr>
		         <tr>
		        <td height="30px"  align="right">优惠价格：</td>
		        <td><input type="text" id="cheapPrice1" name="product.cheapPrice" /></td>
		        </tr>
		      <tr>
		        <td  height="30px"  align="right">商品图片：</td>
		        <td colspan="3"><input type="file" id="xpic1" name="xpic1" class="ww"/>
		        <%--<div id="input-file"> 
				<span id="text">点击上传</span> 
				<input id="xpic1" type="file" name="xpic1"> 
				</div> 
		        
		        --%></td>
		      </tr>
		       <tr>
		       <td></td>
	            <td colspan="3"><a href="javascript:uploadXshopPic1()">
	            <div style="padding-top: 5px;"></div>&nbsp;<span class="icon-save" style="color:red">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;上传\
	            	            <font  style="color:red">*小图</font></span>&nbsp;&nbsp;</a>

	            </td>
		      </tr>
		        <tr>
		        <td  height="30px"  align="right">商品配图：</td>
		        <td colspan="3"><input type="file" id="dpic" name="dpic" class="ww"/></td>
		      </tr>
		       <tr>
		       <td></td>
	            <td colspan="3"><a href="javascript:uploading1()">
	            <div style="padding-top: 5px;"></div>&nbsp;<span class="icon-save" style="color:red">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;上传
	             <font style="color:red">*最多不能超过六张(连续上传即可)</font></span>&nbsp;&nbsp;</a>
	           
	            </td>
		      </tr>
		        <tr>
					<td align="right">
					商品概述：
					</td>
					<td colspan="3">
					<textarea  cols=75 rows=7 id="metaDetails1" name="metaDetails1" style="overflow: auto;width:374px"></textarea>
					</td>
				</tr>
		      
		      <tr>
					<td align="right">
					商品描述：
					</td>
					<td colspan="3">
					<div id="pro2"></div>
					</td>
				</tr>
		    </table>
				 
				 <div style="margin-left: 150px;">
				  <a class="easyui-linkbutton"  iconCls="icon-ok" href="javascript:updateProducts()">提交</a>
		   		 <a class="easyui-linkbutton" style=" margin-left:30px;"  iconCls="icon-cancel" href="javascript:void(0)" onClick="closeupdate()">取消</a>
				</div>
						
			
	</div>

</div>
</form>
</div>
<script>

$("#productType")
.change(
		function() {
				$.ajax({
					type : "post",
					url : "/vshop/site/manage/manage!getProType?parentId="
							+ $("#productType").val(),
					success : function(text) {
						$("#productType1").html(text);
					},
					error : function(xx) {
						alert("aa" + $(xx).html());
					}
			});
		});

$("#productType2")
.change(
		function() {
			//清空select
			var sel = document.getElementById('productType21');
			sel.options.length=0;
			//清空select
				$.ajax({
					type : "post",
					url : "/vshop/site/manage/manage!getProType?parentId="
							+ $("#productType2").val(),
					success : function(text) {
						$("#productType21").html(text);
					},
					error : function(xx) {
						alert("aa" + $(xx).html());
					}
			});
		});


</script>
  </body>
</html>
