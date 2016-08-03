<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script src="../../web/js/jquery-1.8.3.js"></script>
	<link rel="stylesheet" href="../../web/css/style.css" />
    <title>商品分类</title>
  </head>
  <body onload="pagenum()">
  <jsp:include page="top.jsp"></jsp:include>
  <div style="margin-top:60px">
  <h2 class="common_title veins2">
			<input type="hidden" id="typeId"name= "typeId" value="${productType.productTypeId }"/>
				<span class="ico1"><span style="color:#6C6C6C; margin-left:13px;font-size: 21px;" class="ico2">${productType.typeName }</span>
				</span>
			</h2>
			</div>
		<ul>
			<li>
				<ul>
				<s:iterator  var="pt" value="protTypeList" id="pt">
					<li id="li2" ><span id="list1" style="text-align: center;">
					<a href="product!getTypePro?typeId=<s:property value="#pt.productTypeId"/>" style="text-align: center;font-size: 20px;"><s:property value="#pt.typeName"/></a></span></li>
					<li id="li1"><img src="../../web/images/jiantou.png" /></li>
					<div id="div1"><img src="../../web/images/xian.png"  width="100%"/></div>
					</s:iterator>
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
				</ul>
			</li>
		</ul>
		<jsp:include page="bottom.jsp"></jsp:include>
  </body>
  <script >
  function lastPage(){
  	var page = ${page }-1;
  	var typeId = document.getElementById("typeId").value;
  	window.location.href="product!getMinType?typeId="+typeId +"&page="+page;
  } 
  function nextPage(){
  	var page = ${page }+1;
  	var typeId = document.getElementById("typeId").value;
  	window.location.href="product!getMinType?typeId="+typeId +"&page="+page;
  }
  function selectPage(){
  	var page = document.getElementById("curpage").value;
  	var typeId = document.getElementById("typeId").value;
  	window.location.href="product!getMinType?typeId="+typeId +"&page="+page;
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
