<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.handany.rbac.model.PmUser"%>
<%@page import="com.handany.base.common.ApplicationConfig"%>
<%@page import="java.lang.String"%>
<%@page import="com.handany.base.common.Constants"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	PmUser user = (PmUser) request.getAttribute("user");
	String tokenId = (String) request.getAttribute("tokenId");
	String picPath = Constants.IMAGE_SERVER;
	String imageServer = ApplicationConfig.getConfig("image_server");

	String productId;
	boolean isEdit = true;
	if (null == request.getParameter("productId")) {
		productId = (String) request.getAttribute("productId");
		isEdit = false;
	} else {
		productId = request.getParameter("productId");
		isEdit = true;
	}
%>
<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en" class="no-js">
<!--<![endif]-->
<!-- BEGIN HEAD -->
<head>
<meta charset="utf-8" />

<!-- 为了让 IE 浏览器运行最新的渲染模式下，建议将此 <meta> 标签加入到你的页面中：-->
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<!--将下面的 <meta> 标签加入到页面中，可以让部分国产浏览器默认采用高速模式渲染页面：-->
<meta name="renderer" content="webkit">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
<title>采供网</title>
<script src="<%=basePath%>js/jquery-1.11.3.min.js"></script>
<script src="<%=basePath%>js/common.js"></script>
<script src="<%=basePath%>js/mallDialog.js"></script>
<link rel="stylesheet" href="<%=basePath%>css/mall.css">
<script src="<%=basePath%>js/product/product.js"></script>
<script src="<%=basePath%>js/constant.js"></script>
<script src="<%=basePath%>js/xcConfirm.js"></script>
<script src="<%=basePath%>js/ajaxfileupload.js"></script>
<link href="<%=basePath%>css/bootstrap.min.css" rel="stylesheet">

<link href="<%=basePath%>css/xcConfirm.css" rel="stylesheet">

<style type="text/css">
.contentContainer {
	margin-top: 15px;
}

.contentContainer .form-tr {
	margin-top: 15px;
}

.contentContainer .form-title {
	text-align: right;
	padding-top: 5px;
}

.td_input {
	border: white;
	align: center;
}

td {
	width: 100px;
}

img {
	width: 200px;
	height: 200px;
	border: none;
	vertical-align: bottom;
}

.product_image {
	padding: 2px;
}

.img_action {
	position: absolute;
	bottom: 24px;
	z-index: 1;
	background-color: rgba(128, 128, 128, .8);
	width: 100%;
	font-size: 14px;
	font-color: #fff;
	display: none;
}

.btn_modify_txt {
	bottom: 0;
	left: 0;
	border-top: 1px solid #ccc;
	background: #fff;
	opacity: .9;
	font-size: 14px;
	width: 100%;
	position: absolute;
	line-height: 24px;
}

.icon_add {
	position: absolute;
	bottom: 0;
	left: 0;
	width: 100%;
	text-align: center;
	color: #be0c0c;
	font-size: 14px;
	line-height: 24px;
}

.head_picture {
	position: absolute;
	top: 2px;
	left: 2px;
	padding width: 100%;
	background: url(<%=basePath%>/image/head.png) 95% center no-repeat;
}

.isHead {
	position: absolute;
	top: 2px;
	left: 2px;
	padding width: 100%;
}

.icon_modify {
	bottom: 0;
	left: 0;
	background: url(<%=basePath%>/css/images/icon_modify.png) 95% center
		no-repeat;
	font-size: 14px;
	text-indent: 5px;
	min-height: 24px;
	width: 100%;
	position: absolute;
	color: #434343;
}

.img_action_item {
	color: #fff;
	width: 50%;
	text-align: center;
	padding-left: 30px;
}

.del_img {
	padding-left: 50px;
}

ul {
	margin: 0;
	padding: 0;
	font-weight: 400;
	list-style: none;
	display: block;
	/* font-style: normal; */
}

.img_li {
	border: 1px solid #ccc;
	margin-right: 20px;
	position: relative;
	width: 200px;
	height: 200px;
	margin-bottom: 25px;
	float: left;
}

td {
	text-align: center;
}
</style>
</head>
<div class="container-fluid">
	<div class="row" style="border: 0; margin-top: -10px;">
		<div class="col-sm-12">
			<h3 class="zs-iframe-title">商品维护信息</h3>
		</div>
	</div>
	<ul class="nav nav-tabs">
		<li role="presentation" class="active" id="tab-base"><a
			href="javascript:showContainer('tab-base','baseContainer')">基本信息</a></li>
		<li role="presentation" id="tab-img"><a
			href="javascript:showContainer('tab-img','imageContainer')">商品图片</a></li>
	</ul>
	<div class="container contentContainer" id="baseContainer"
		style="display: block;">
		<form class="form-horizontal" role="search">
			<div class="form-group class_edit">
				<label for="name" class="control-label col-sm-2 col-md-2">选择分类</label>
				<div class="col-sm-10 col-md-10 ">
					<select id='selectOne' style="width: 30%;"></select> <select
						style="width: 30%; margin-left: 20px;" id='selectTwo'></select> <select
						id='selectThree' style="width: 30%; margin-left: 20px;"></select>
				</div>

			</div>
			<div class="form-group">
				<label class="col-sm-2 col-md-2 control-label">标题</label>
				<div class=" col-sm-10 col-md-10">
					<input type="text" class="form-control" id="name">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 col-md-2 control-label">描述</label>
				<div class="col-sm-10 col-md-10">
					<textarea rows="5" class="form-control" id="describe"></textarea>
				</div>
			</div>

			<div class="form-group ">
				<label class="col-sm-2 col-md-2 control-label">型号</label>

				<div class="col-sm-10 col-md-10 type_sum"></div>
			</div>
			<div class="form-group">
				<div class="col-sm-3 col-sm-offset-2 col-md-3 col-md-offset-2">
					<button type="button" class="btn btn-default type_add">+添加型号</button>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 col-md-2 control-label">商品参数</label>
				<div class="col-sm-10 col-md-10 param_sum"></div>
			</div>
			<div class="form-group">
				<div class="col-sm-3 col-sm-offset-2 col-md-3 col-md-offset-2">
					<button type="button" class="btn btn-default param_add">+添加参数</button>
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-10 col-sm-offset-2 col-md-10 col-md-offset-2">
					<button type="button" class="btn btn-danger btn-block submit">提交</button>
				</div>
			</div>
		</form>
	</div>

	<div class="container contentContainer" id="imageContainer"
		style="display: none;">
		<div class="row"
			style="margin-bottom: 15px; padding-bottom: 15px; border-bottom: 2px inset #eaeaea">
			<div class="col-md-4">
				<label for="myfile">请选择图片（推荐尺寸800*800）</label>
			</div>
			<div class="col-md-4">
				<input type="file" name="myfile" id="myfile" class="inputstyle" />
			</div>
			<div class="col-md-4">
				<input type="button" value="上传" class="zs-btn " id="upload">
			</div>
		</div>
		<div class="picture_sum">
			<%-- <div class="col-md-3">
					<div class="thumbnail"
						style="position: relative; opacity: 1; z-index: 0;">
						<img
							src="http://192.168.1.2:8888/201511/3578a179-2072-4b00-a9de-2026b7680d5d_small.jpg">
						<p style="margin-top: 10px; width: 100%; text-align: center" index="0" data_picid="0000000244">
						<a class="btn btn-primary as_head">设为封面</a> <a class="btn btn-default img_del">删除</a>
						</p>
						<div class="img_action">
							<a href="javascript:void(0)" class="img_action_item update_img"
								data-order="1">替换</a> <a href="javascript:void(0)"
								class="img_action_item del_img">删除</a>
						</div>
						<span class="btn_modify_txt">&nbsp;</span> <a href="#" rel="1"
							class="icon_modify">鱼死网破红...</a>
					</div>
				</div> --%>
		</div>

		<ul id="img_ul" class="ui-sortable">
			<!-- 			<li class="img_li rel img_inDB"
				data-url="http://wd.geilicdn.com/vshop339943262-1446019841036-62491-s1.jpg?w=720&amp;h=1280"
				data-title="" style="position: relative; opacity: 1; z-index: 0;">
				<img class="product_image"
				src="http://wd.geilicdn.com/vshop339943262-1446019841036-62491-s1.jpg?w=110&amp;h=110&amp;cp=1">
				<img class="head_picture">
				<div class="img_action">
					<a href="javascript:void(0)" class="img_action_item update_img">设为封面</a><a
						href="javascript:void(0)" class="img_action_item del_img">删除</a>
				</div> <span class="btn_modify_txt">&nbsp;</span> <a href="#" rel="4"
				class="icon_add">添加描述</a>
			</li> -->
		</ul>
	</div>
</div>
<script type="text/javascript">
	/**
	 * 从 file 域获取 本地图片 url
	 */
    function upload (){
    	alert(11);
    	 var id = ProductDo.productId;
			var myfile = $("#myfile").val();  			
			if(myfile!=""){  				
				$.ajaxFileUpload({
					url:toServerUrl("/bm/picture/picUpload.do?type=" + "product&relateId=" + id),  					
					type:"post",
					fileElementId:"myfile",
					dataType:"text",
					success:function(data){   
						plog(data);
						
//						var text = $(data).html();
//					var obj = JSON.parse(text);						
//					var pics = obj.data;
//					 for(var i = 0; i < pics.length; i ++){
//						 
//						 var d = document.getElementById("div1");
//
//						 d.style.backgroundColor = "white"; 
//						
//						$("#imgShow").html("<img class='img-circle' style='width:100px;height:100px' src='"+picPath + pics[0].url +"'/>");
//						
//					} 
					}
				});
			}else{
				alert("请至少选择一个文件执行上传操作");
			}
		}
</script>
<script type="text/javascript">
        <%--alert('<%=productId%>');--%>
        ProductDo.productId = '<%=productId%>';
		ProductDo.basePath ='<%=basePath%>';
		ProductDo.imageServer = '<%=imageServer%>'+'/';
		//alert("document size "+$("body").length);
		
		//alert($(document.body).length);
        if(<%=isEdit%>){
        ProductDo.isEdit = true;
        }else{
        ProductDo.isEdit = false;
        }
        ProductDo.init();
        function showContainer(tab, container) {
        $("[role='presentation']").each(function(i) {
        		$(this).removeClass("active");
        });
        $("#" + tab).addClass("active");
        $(".contentContainer").each(function(i) {
       		 $(this).hide();
        });
        $("#" + container).show();
        }
        </script>