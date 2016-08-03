<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="../common/header.html"%>
<link rel="stylesheet" type="text/css" href="assets/css/article.css">
<!-- 中间部分 -->
<div class="main">
	<ol class="breadcrumb">
		<li><a href="home"><i class="glyphicon glyphicon-home pr10"></i>首页</a></li>
		<li><a href="article?category=${category}"><c:choose>
					<c:when test="${category==1 }">创业故事</c:when>
					<c:when test="${category==2 }">创业项目</c:when>
					<c:when test="${category==3 }">创业视点</c:when>
					<c:when test="${category==4 }">活动交流</c:when>
				</c:choose></a></li>
		<li class="active">列表</li>
	</ol>
	<div class="leftbox clearfix">
		<div class="articlediv">
			<ul>
				<c:forEach items="${articleInfo.list }" var="article">
					<li>
						<div class="article shadowarticlefont">
							<div class="fleft clearfix">
								<a href="article/detail/${article.category }-${article.id}"><img
									class="lazy" src="assets/css/imgs/news.png.thumb.jpg"
									data-original='${article.imgUrl==null?"assets/css/imgs/news.png":article.imgUrl}.thumb.jpg' /></a>
							</div>
							<div class="fleft atcontent">
								<div class="atitle">
									<a href="article/detail/${article.category }-${article.id}">${article.title }</a>
								</div>
								<div class="authorandtime">
									<span>${article.author }</span> <em><fmt:formatDate
											value="${article.postTime }" pattern='yyyy年MM月dd日  HH:mm:ss' /></em>
								</div>
								<div class="content">${article.smcontent }</div>
							</div>
						</div>
					</li>
				</c:forEach>
			</ul>
		</div>
		<div class="page fleft">
			<a class="fleft prepage clearfix"
				href="article/page/${category}-${articleInfo.pageNumber>1?articleInfo.pageNumber-1:1 }"
				title="上一页"></a>
			<div class="fleft pageinfo clearfix">
				<span>共${articleInfo.totalRow }条</span><span>&nbsp;页
					${articleInfo.pageNumber }</span><span>${articleInfo.totalPage }</span>
			</div>
			<a class="fright nextpage clearfix"
				href="article/page/${category}-${articleInfo.pageNumber<articleInfo.totalPage?articleInfo.pageNumber+1:articleInfo.totalPage }"
				title="下一页"></a>
		</div>
		<form action="" method="post" id="pageForm">
			<input type="hidden" name="pager.currentPage" id="currentPage" />
		</form>
	</div>
	<div class="rightbox">
		<%@include file="../common/qunqrcode.html"%>
		<%@include file="../common/hotarticle.html"%>
	</div>
</div>
<div class="clearfix"></div>
<%@include file="../common/footer.html"%>
</body>
</html>
