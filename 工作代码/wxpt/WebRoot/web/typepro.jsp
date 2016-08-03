<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script src="../../web/js/jquery-1.8.3.js"></script>
<link href="../../web/css/product.css" rel="stylesheet" type="text/css">
<title>${productType.typeName }</title>

</head>
<body onload="pagenum()">
	<jsp:include page="top.jsp"></jsp:include>

	<div id="content" style="margin-top: 60px">
		<div class="module_special">
			<h2 class="common_title veins2">
				<input type="hidden" id="typeId" name="typeId"
					value="${productType.productTypeId }" /> <span class="ico1"><span
					class="ico2">${productType.typeName }</span> </span>
			</h2>
			<div class="wrap">
				<div class="wrap_child">
					<div class="major">
						<ul class="list">
							<s:if test="productList.size==0">
								<li>
									<div class="pic">
										<a><img src="../../web/images/1.png"> </a>
									</div>
									<div class="good_content">
										<h3>
											<a>暂无产品</a>
										</h3>
										<p style="text-decoration:line-through;">原&nbsp;&nbsp;&nbsp;&nbsp;价：￥0</p>
										<p>会员价：￥0</p>
									</div>
								</li>
							</s:if>
							<s:else>
								<s:iterator var="pl" value="productList" id="pl">
									<li>
										<div class="pic">
											<a
												href="product!getPro?productId=<s:property value="#pl.productId"/>"
												target="_blank"><img
												src="../../web/images/<s:property value="#pl.productXimage"/>">
											</a>
										</div>
										<div class="good_content">
											<h3>
												<a
													href="product!getPro?productId=<s:property value="#pl.productId"/>"
													target="_blank"><s:property value="#pl.productName" />
												</a>
											</h3>
											<p style="text-decoration:line-through;">
												原&nbsp;&nbsp;&nbsp;&nbsp;价：￥
												<s:property value="#pl.price" />
											</p>
											<p>
												会员价：￥
												<s:property value="#pl.cheapPrice" />
											</p>
										</div>
									</li>
								</s:iterator>
							</s:else>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div align="center">
					<a style="font-size: 16px; color:#888888; ">共${pageCount }页${listCount }条记录，当前为第${page }页</a>
					<s:if test="page > 1">
					<a style="font-size: 16px; color:#000000;" href="javascript:lastPage()">上一页</a>
					</s:if>
					<s:if test="page<pageCount" >
						<a style="font-size: 16px; color:#000000;" href="javascript:nextPage()">下一页</a> 
					</s:if>
					<br>
					<span style="font-size: 16px;color:#888888;">第</span>
					<select id="curpage" name="curpage"style="width: 40px">
					</select>
					<span style="font-size: 16px;color:#888888;">页</span><a style="font-size: 16px;color:#000000;" href="javascript:selectPage()">&nbsp;跳转</a>
				</div>
	<jsp:include page="bottom.jsp"></jsp:include>
</body>
<script>
  function lastPage(){
  	var page = ${page }-1;
  	var typeId = document.getElementById("typeId").value;
  	var url = "product!getTypePro?typeId="+typeId+"&page="+page;
  	window.location.href="product!getTypePro?typeId="+typeId+"&page="+page;
  } 
  function nextPage(){
  	var page = ${page }+1;
  	var typeId = document.getElementById("typeId").value;
  	window.location.href="product!getTypePro?typeId= "+typeId+"&page="+page;
  }
  function selectPage(){
  	var page = document.getElementById("curpage").value;
  	var typeId = document.getElementById("typeId").value;
  	window.location.href="product!getTypePro?typeId= "+typeId+"&page="+page;
  }
  function pagenum(){
 
 	 for (var i=1;i <= ${pageCount };i++)
	{
		var value = i;
		document.getElementById("curpage").options.add(new Option(value,value));
	}
  }

</script>
</html>
