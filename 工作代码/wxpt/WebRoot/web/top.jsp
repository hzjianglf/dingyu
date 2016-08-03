<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<!--自适应宽度,并不允许缩放-->
<script src="js/html5.js"></script>
<script src="js/html5media.min.js"></script>
<style>
nav, ul{ margin: 0px; padding: 0;}
ul{ list-style: none; }
/* nav .menu .parent ul {
	background: #F93;
	border-radius: 0 0 10px 10px;
	display: none;
	opacity: 0.9;
	width: 92%;
	padding-bottom: 6px;
}
nav .menu .parent li {
    float: none;
} */
nav a, nav a:visited {
	margin-top:-13px;
	text-align: center;
    color: #E4F9FF;
    display: block;
    font-size: 14px;
    font-weight: 700;
    padding: 0 0;
}
body {
	margin: 0;
	padding: 0;
}

header {
	margin-left: 20px;
}

nav ul li a:link {
	color: #FFFFFF;
	text-decoration: none
}
nav {
	position:absolute;
	z-index:1000;
	width: 100%;
	margin: 0 auto;
	color: #FFFFFF;
	font-size: 13px;
	font-family: 微软雅黑;
	background: #F93;
	height: 45px;
    line-height: 30px;
	position: fixed;
	top: 0px;
}

nav ul {
	width: 90%;
	margin: 0 auto;
}
nav ul li {
	float: left;
	text-align: center;
	list-style: none;
	padding-top: 20px;
	width: 25%;
	height: 40px;
	margin-left: 0px;
}
.child{
	width: 100%;
}
nav ul li a {
	color: #FFFFFF;
	text-decoration: none
}
</style>
<body>
	<nav>
		<ul class="menu">
			<li class="item-001 current active"> <a href="index">首页</a></li>
			<li class="item-002 deeper parent"><a href="product!getMaxType">商品分类</a>
				<!-- <ul>
				<s:iterator  var="pt" value="protTypeList" id="pt">
					<li class="child"><a href="#"><s:property value="#pt.typeName"/></a></li>
					</s:iterator>
				</ul></li> -->
			<li class="item-003"><a href="../../site/web/order!getAllNewOrder">我的订单</a></li>
			<li class="item-004" id="item1" style="display: none;"> <a href="../../site/web/order!getShopping">购物车</a></li>
			<li class="item-004" id="item2" style="display: none;"> <a href="../../site/web/order!getShopping">购物车(<span id="size" style="color: red;"></span>)</a></li>
		</ul>
	</nav>
</body>
<script>
jQuery(function(){
    jQuery("nav .menu .parent").mouseover(function(){
        jQuery(this).find("ul").show();
    });
    jQuery("nav .menu .parent").mouseout(function(){
        jQuery(this).find("ul").hide();
    });
    var id1 = new Object();
	var id2 = new Object();
	id1=$("#item1");
	id2=$("#item2");
    $.ajax({
				url : "order!getAllShopping",
				type : 'post',
				success : function(json) {
				
					if(json=="0"){
					id1.slideDown("slow");
					}else{
					id2.slideDown("slow");
					$("#size").html(json);
					}
				},
				error : function(){
					id1.slideDown("slow");
				}
			});
});
</script>
