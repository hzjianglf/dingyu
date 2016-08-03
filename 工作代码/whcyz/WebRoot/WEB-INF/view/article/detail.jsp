<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="../common/header.html" %>  
    <script>
    document.title="${article.title}—"+document.title;
    if(navigator.platform.indexOf('Win32')==-1&&navigator.platform.indexOf('Win64')==-1){ 
            window.location.href="<%=basePath%>article/mdetail/${category}-${article.id}";
    } 
    $(function(){
		  $.ajax({
				 type:"get",
				 url:"article/addreadcount/${article.id}",
				 datatype:"json",
				 success:function(result){
					 if(result.success){
						 $("#readCount").text(Number($("#readCount").text())+1);
					 }
				 }
			 });
});
  </script>
    <link rel="stylesheet" type="text/css" href="assets/css/articledetail.css">
    <script type="text/javascript" src="assets/js/jquery.lazyload.min.js"></script>
  <!-- 中间部分 -->
  <div class="main">
	<ol class="breadcrumb">
	  <li><a href="#"><i class="glyphicon glyphicon-home pr10"></i>首页</a></li>
	    <li><a href="article?category=${category}">
	  <c:choose>
	  	<c:when test="${category==1 }">创业故事</c:when>
	  	<c:when test="${category==2 }">创业项目</c:when>
	  	<c:when test="${category==3 }">创业视点</c:when>
	  	<c:when test="${category==4 }">活动交流</c:when>
	  </c:choose>
	  </a></li>
	  <li class="active">${article.title }</li>
	</ol>
  	<div class="leftbox">
  		<div class="articledetail">
	  		<div class="ad-title">${article.title }</div>
	  		<div class="ad-author"><em><fmt:formatDate value="${article.postTime }" pattern='yyyy年MM月dd日  HH:mm:ss'/></em><span>来源：${article.author }</span><span>阅读[<span id="readCount" style="padding:0;margin: 0;">${article.readCount}</span>]<span><span>评论[${article.commentCount }]<span></div>
	  		<div class="ad-contentsm">
	  		<span style="font-weight: bold;">摘要：</span><span class="content">${article.smcontent }</span>
	  		</div>
	  		<div class="ad-content">
	  		<c:if test="${not empty article.imgUrl }">
	  		<p class="tcenter mt10">
	  		<img alt="" data-original="${article.imgUrl }"  class="lazy" >
	  		</p>
	  		</c:if>
	  		<p class="mt20">${article.content }</p>
	  		</div>
	  		<div class="ad-share">
		  		<!-- JiaThis Button BEGIN -->
				<div class="jiathis_style_32x32">
					<span class="jiathis_txt" style="font-size:14px;">分享到：</span>
						<a class="jiathis_button_qzone"></a>
						<a class="jiathis_button_tsina"></a>
						<a class="jiathis_button_tqq"></a>
						<a class="jiathis_button_weixin"></a>
						<a class="jiathis_button_renren"></a>
						<a class="jiathis_button_cqq"></a>
						<a class="jiathis_button_email"></a>
						<a class="jiathis_button_copy"></a>
						<a class="jiathis_button_print"></a>
						<a class="jiathis_button_fav"></a>
				</div>
			<script type="text/javascript" >
				var jiathis_config={
					url:window.location.href,
					summary:"${article.smcontent}",
					title:"${article.title}"+"    ${article.smcontent}",
					shortUrl:false,
					hideMore:false
				}
				</script>
				<script type="text/javascript" src="http://v3.jiathis.com/code/jia.js" charset="utf-8"></script>
				<!-- JiaThis Button END -->
	  		</div>
	  	</div>
	  	<%@include file="../common/comment.html" %>
  	</div>
  	<div class="rightbox">
  	<%@include file="../common/relperson.html" %>
  	<%@include file="../common/relcompany.html" %>
  	<%@include file="../common/hotarticle.html" %>
  	</div>
  </div>
  <div class="clearfix"></div>
  	<%@include file="../common/footer.html" %>
  </body>
</html>
    