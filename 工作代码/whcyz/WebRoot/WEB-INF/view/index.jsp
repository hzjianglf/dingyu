<%@page import="com.whcyz.model.Article"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="common/header.html" %>
<link rel="stylesheet" type="text/css" href="assets/css/dataTables.bootstrap.css">
<script type="text/javascript" language="javascript" src="assets/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" language="javascript" src="assets/js/dataTables.bootstrap.js"></script>
<script type="text/javascript">
if(navigator.platform.indexOf('Win32')==-1&&navigator.platform.indexOf('Win64')==-1){ 
    window.location.href="<%=basePath%>m";
} 

	


</script>





 <link rel="stylesheet" type="text/css" href="assets/css/article.css">
<!-- 中间部分 -->
<div class="main pt10">
	<%-- <div class="leftbox clearfix">
	<%@include file="common/1.html" %>
</div> --%>
	<%-- <center>
<div id="carousel-example-captions" style="width: 630px;height:350px;margin: 0 atuo;" class="carousel slide" data-ride="carousel">
<ol class="carousel-indicators">
  <c:forEach items="${articles }" var="article" varStatus="index">
<c:if test="${index.index<5 }">
<li data-target="#carousel-example-captions" data-slide-to="${index.index}" class="${index.first?'active':'' }"></li>
</c:if>
</c:forEach>
</ol>
<div class="carousel-inner" role="listbox" style="width: 630px;height:350px;">
<c:forEach items="${articles }" var="article" varStatus="index">
<c:if test="${index.index<5 }">
<div class="item ${index.first?'active':'' }" style="width: 630px;height:350px;">
<a class="slidehref" href="article/detail/${article.category }-${article.id}">
<img data-src="holder.js/600x350/auto/#777:#777" alt="600x350" src="${article.imgUrl }">
<div class="carousel-caption" style="width:400px;height: 300px;padding-top: 160px;" >
<h3>${article.title }</h3>
<p><script type="text/javascript">document.write("${article.smcontent }".substring(0,50))</script></p>
  </div>
</a>
</div>
</c:if>
</c:forEach>
  </div>
   <a class="left carousel-control" href="#carousel-example-captions" role="button" data-slide="prev">
    <span class="glyphicon glyphicon-chevron-left"></span>
    <span class="sr-only">Previous</span>
  </a> 
  <a class="right carousel-control" href="#carousel-example-captions" role="button" data-slide="next">
    <span class="glyphicon glyphicon-chevron-right"></span>
    <span class="sr-only">Next</span>
  </a>
</div>
</center> --%>
<%-- <div class="articlediv">
	<ul>
	<c:forEach items="${articles }" var="article" varStatus="index">
<c:if test="${index.index>=5 }">
	<li>
<div class="article shadowarticlefont">
	<div class="fleft clearfix">
		<a href="article/detail/${article.category }-${article.id}"><img class="lazy" src="assets/css/imgs/news.png.thumb.jpg" data-original='${article.imgUrl==null?"assets/css/imgs/news.png":article.imgUrl}.thumb.jpg' /></a>
</div>
<div class="fleft atcontent">
	<div class="atitle"><a href="article/detail/${article.category }-${article.id}">${article.title }</a></div>
<div class="authorandtime">
<span>${article.author }</span>
<em><fmt:formatDate value="${article.postTime }" pattern='yyyy年MM月dd日  HH:mm:ss'/></em>
</div>
<div class="content">
	${article.smcontent }
		</div>
	</div>
</div>
</li>
</c:if>
</c:forEach>
		</ul>
	</div> --%>
 <!-- <form action="" method="post" id="pageForm">
		<input type="hidden" name="pager.currentPage" id="currentPage" />
	</form>  
	<div class="companypage"> -->

<div class="hline1"><div></div></div>

</div>
	
	

<%-- <div class="rightbox">
<%@include file="common/qunqrcode.html" %>
<%@include file="common/hotperson.html" %>
	</div> --%>
</div>
 <div class="clearfix"></div>
<%@include file="common/footer.html" %>
  </body>
</html>