<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
  <%@include file="../common/header.html" %>
    <link rel="stylesheet" type="text/css" href="assets/css/person.css">
  <!-- 中间部分 -->
  <div class="main">
	<ol class="breadcrumb">
	  <li><a href="home"><i class="glyphicon glyphicon-home pr10"></i>首页</a></li>
	  <li><a href="person">录波器</a></li>
	  <li class="active" id="currentbreadcrumb">列表</li>
	</ol>
  	<div class="leftbox clearfix">
  		<div class="persondiv">
	  		<%-- <ul>
	  		<c:forEach items="${personInfo.list }" var="person">
	  			<li><div class="person"><a href="person/detail/${person.id }"><img class="lazy" src="assets/css/imgs/defaultavatar.png" data-original="${person.imgUrl==null?'assets/css/imgs/defaultavatar.png':person.imgUrl }" /><span class="name">${person.name }</span></a></div><a href="person/detail/${person.id }" class="imgbg"></a></li>
	  		</c:forEach>
	  		</ul> --%>
	  	</div>
	  	<div class="page fleft">
	  		<a class="fleft prepage clearfix" href="person/page/${personInfo.pageNumber>1?personInfo.pageNumber-1:1 }" title="上一页"></a>
	  		<div class="fleft pageinfo clearfix"><span>共${personInfo.totalRow }条</span><span>&nbsp;页 ${personInfo.pageNumber }</span><span>${personInfo.totalPage }</span></div>
	  		<a class="fright nextpage clearfix" href="person/page/${personInfo.pageNumber<personInfo.totalPage?personInfo.pageNumber+1:personInfo.totalPage }"  title="下一页"></a>
	  	</div>
	  	<form action="" method="post" id="pageForm">
	  		<input type="hidden" name="pager.currentPage" id="currentPage" />
	  	</form>
  	</div>
  	<div class="rightbox">
  	 <%@include file="../common/qunqrcode.html" %>
  	<%@include file="../common/pyindex.html" %>
  	<%@include file="../common/hotperson.html" %> 
  	</div>
  </div>
  <div class="clearfix"></div>
  	<%@include file="../common/footer.html" %>
  </body>
</html>