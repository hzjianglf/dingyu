<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
   <%@include file="../common/header.html" %>
   <link rel="stylesheet" type="text/css" href="assets/css/company.css">
  <!-- 中间部分 -->
  <div class="main">
	<ol class="breadcrumb">
	  <li><a href="home"><i class="glyphicon glyphicon-home pr10"></i>首页</a></li>
	  <li><a href="company">一次设备</a></li>
	  <li class="active" id="currentbreadcrumb">列表</li>
	</ol>
  	<div class="leftbox clearfix">
	  	<%-- <div class="companydiv">
	  		<ul>
	  		<c:forEach items="${companyInfo.list }" var="company">
	  			<li><div class="company"><a href="company/detail/${company.id }"><img class="lazy" src="assets/css/imgs/defaultlogobg.jpg" data-original="${company.imgUrl==null?'assets/css/imgs/defaultlogobg.jpg': company.imgUrl}" /><span class="name">${company.name }</span></a></div><a href="company/detail/${company.id }" class="imgbg"></a></li>
	  		</c:forEach>
	  		</ul>
	  	</div> --%>
	  	
	  	
	  	
	  	<div class="page fleft">
	  		<a class="fleft prepage clearfix" href="company/page/${companyInfo.pageNumber>1?companyInfo.pageNumber-1:1 }" title="上一页"></a>
	  		<div class="fleft pageinfo clearfix"><span>共${companyInfo.totalRow }条</span><span>&nbsp;页 ${companyInfo.pageNumber }</span><span>${companyInfo.totalPage }</span></div>
	  		<a class="fright nextpage clearfix" href="company/page/${companyInfo.pageNumber<companyInfo.totalPage?companyInfo.pageNumber+1:companyInfo.totalPage }"  title="下一页"></a>
	  	</div>
	  <!-- 	<form action="" method="post" id="pageForm">
	  		<input type="hidden" name="pager.currentPage" id="currentPage" />
	  	</form> -->
  	</div>
  	<div class="rightbox">
  	<%@include file="../common/qunqrcode.html" %>
  	<%@include file="../common/pyindex.html" %>
  	<%@include file="../common/hotarticle.html" %>
  	</div>
  </div>
  <div class="clearfix"></div>
  	<%@include file="../common/footer.html" %>
  </body>
</html>

