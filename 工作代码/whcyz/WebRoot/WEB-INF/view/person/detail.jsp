<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
  <%@include file="../common/header.html" %>
   <script type="text/javascript">
	 document.title="${person.name}—"+document.title;
</script>
  <link rel="stylesheet" type="text/css" href="assets/css/showinfodetail.css">
  <!-- 中间部分 -->
  <div class="main">
	<ol class="breadcrumb">
	  <li><a href="home"><i class="glyphicon glyphicon-home pr10"></i>首页</a></li>
	  <li><a href="person">创业人物</a></li>
	  <li class="active">${person.name }</li>
	</ol>
  	<div class="leftbox">
  		<div class="showinfo">
  			<div class="info-top">
  				<div class="imgbox"><div class="imgbg"></div><img data-original="${person.imgUrl }"  class="lazy"  src="${person.imgUrl==null?'assets/css/imgs/defaultavatar.png':person.imgUrl }" width="140" height="140"/></div>
  				<div class="infobox">
  					<div class="info-title">${person.name }</div>
  					 <div class="infos"><span>公司名称</span><span>${person.companyName }</span></div>
 					<div class="infos"><span>担任职务</span><span>${person.jobTitle }</span></div>
  					<div class="infos"><span>收录时间</span><span><fmt:formatDate value="${person.foundTime }" pattern='yyyy年MM月dd日'/></span></div>
  				</div>
  			</div>
  			<div class="info-contentsm">
  			简介：
  			</div>
  			<div class="info-content">${person.content }</div>
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
					summary:"",
					title:"创业人物：${person.name}[${person.sex}]${person.jobTitle}",
					shortUrl:false,
					hideMore:false
				}
				</script>
				<script type="text/javascript" src="http://v3.jiathis.com/code/jia.js" charset="utf-8"></script>
				<!-- JiaThis Button END -->
	  		</div>
  	</div>
  	<div class="rightbox">
  	<%@include file="../common/relcompany.html" %>
  	<%@include file="../common/hotperson.html" %>
  	<%@include file="../common/hotarticle.html" %>
  	</div>
  </div>
  <div class="clearfix"></div>
  	<%@include file="../common/footer.html" %>
  </body>
</html>