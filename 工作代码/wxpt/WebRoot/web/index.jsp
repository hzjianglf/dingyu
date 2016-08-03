<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html><html><head>
<meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0">
<meta http-equiv="Content-Type" content="text/html;charset=utf-8">
<script src="../../web/js/jquery-1.8.3.js"></script>
<link rel="stylesheet" href="../../web/css/index.css" />
<link href="../../web/css/product.css" rel="stylesheet" type="text/css">

<title>首页</title>
<meta name="description" content="">
</head>
<body>
 <jsp:include page="top.jsp"></jsp:include>
	<div style="margin-top: 40px">
		<img id="banner" name="banner" style="width:100%;height:20%px; " >
	</div>

	<div id="content">
		<div class="module_special">
			<h2 class="common_title veins2">
				<span class="ico1"><span class="ico2">推荐商品</span>
				</span>
			</h2>
			<div class="wrap">
				<div class="wrap_child">
					<div class="major">
						<ul class="list">
						<s:if test="recomProduct.size==0">
						<li>
								<div class="pic">
									<a><img src="../../web/images/1.png">
									</a>
								</div>
								<div class="good_content">
									<h3>
										<a>暂无产品</a>
									</h3>
									<p style="text-decoration:line-through;">原&nbsp;&nbsp;&nbsp;&nbsp;价：￥0</p>
									<p>会员价：￥0</p>
								</div></li>
						</s:if>
						<s:else>
						<s:iterator  var="rp" value="recomProduct" id="rp">
						<li>
								<div class="pic">
									<a
										href="product!getPro?productId=<s:property value="#rp.productId"/>"
										target="_blank"><img src="../../web/images/<s:property value="#rp.productXimage"/>">
									</a>
								</div>
								<div class="good_content">
									<h3>
										<a
											href="product!getPro?productId=<s:property value="#rp.productId"/>"
											target="_blank"><s:property value="#rp.productName"/> </a>
									</h3>
									<p style="text-decoration:line-through;">原&nbsp;&nbsp;&nbsp;&nbsp;价：￥<s:property value="#rp.price"/></p>
									<p>会员价：￥<s:property value="#rp.cheapPrice"/></p>
								</div></li>
						</s:iterator>
						</s:else>
						</ul>
					</div>
				</div>
			</div>
		</div>
		<div class="module_special tbr">
			<h2 class="common_title veins2">
				<span class="ico1"><span class="ico2">最新商品</span>
				</span>
			</h2>
			<div class="wrap">
				<div class="wrap_child">
					<div class="major">
						<ul class="list">
						<s:if test="newProduct.size==0">
						<li>
								<div class="pic">
									<a><img src="../../web/images/1.png">
									</a>
								</div>
								<div class="good_content">
									<h3>
										<a>暂无产品</a>
									</h3>
									<p style="text-decoration:line-through;">原&nbsp;&nbsp;&nbsp;&nbsp;价：￥0</p>
									<p>会员价：￥0</p>
								</div></li>
						</s:if>
						<s:else>
							<s:iterator  var="np" value="newProduct" id="np">
						<li>
								<div class="pic">
									<a
										href="product!getPro?productId=<s:property value="#np.productId"/>"
										target="_blank"><img src="../../web/images/<s:property value="#np.productXimage"/>">
									</a>
								</div>
								<div class="good_content">
									<h3>
										<a
											href="product!getPro?productId=<s:property value="#np.productId"/>"
											target="_blank"><s:property value="#np.productName"/> </a>
									</h3>
									<p style="text-decoration:line-through;">原&nbsp;&nbsp;&nbsp;&nbsp;价：￥<s:property value="#rp.price"/></p>
									<p>会员价：￥<s:property value="#rp.cheapPrice"/></p>
								</div> <span class="show_good"><a
									href="product!getPro?productId=<s:property value="#np.productId"/>"
									target="_blank"></a> </span></li>
						</s:iterator>
						</s:else>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</div>
		<jsp:include page="bottom.jsp"></jsp:include>
</body>
<script>
$(function() {
	$.ajax({
				type : "POST",
				url : "/wxpt/site/web/banner!getBanner",
				dataType : "json",
				success : function(text) {
					var obj = eval(text);
					show = obj[0].banner;
					$("#banner").attr("src","../../web/images/"+show);
				},
				error : function(text) {
					var obj = eval(text);
					show = obj[0].banner;
					$("#banner").attr("src","../../web/images/"+show);
					}
			});
});
</script>
</html>
