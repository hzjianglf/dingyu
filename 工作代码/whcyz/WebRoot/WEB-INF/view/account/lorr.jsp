<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
  <head>
	<base href="<%=basePath%>">
	<meta charset="utf-8">
    <title>故障录波联网系统-登录</title>
  <!--   <meta name="keywords" content="威海创业者">
    <meta name="description" content="威海创业者"> -->
     <meta name="keywords" content="故障录波"><!-- 为搜索引擎提供的关键字列表 -->
    <meta name="description" content="故障录波"><!--  Description用来告诉搜索引擎你的网站主要内容。 -->
    <meta name="content-type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
	<meta name="renderer" content="webkit"><!-- 加了这个 标签 下次打开 会自动切换为 急速模式  -->
	<meta http-equiv="Cache-Control" content="no-siteapp" /><!-- 设置这个之后通过手机百度搜索打开网页时,百度会为你的网页进行转码 -->
	<link rel="icon" type="image/png" href="assets/i/favicon.ico">
	<link rel="stylesheet" href="assets/css/bootstrap.min.css">
	<link rel="stylesheet" href="assets/css/bootstrap-theme.min.css">
    <link rel="stylesheet" type="text/css" href="assets/css/app.css">
    <link rel="stylesheet" type="text/css" href="assets/css/logreg.css">
	<script src="assets/js/ie10-viewport-bug-workaround.js"></script>
	<script src="assets/js/ie-emulation-modes-warning.js"></script>
	<!--[if lt IE 9]>
	  <script src="assets/js/html5shiv.min.js"></script>
	  <script src="assets/js/respond.min.js"></script>
	<![endif]-->
  </head>
  
  <body>
  <div class="appLoading" style="display: none;" tabindex="-1" id="appLoading">
  <div class="loading-dialog">
    <div class="loading-msg" id="appLoadingMsg">正在处理...</div>
    <div class="loading-icon" id="appicon"></div>
  </div>
</div>
  <!-- 中间部分 -->
  <div class="main">
  	<div class="login">
  	<!-- 	<div class="title shadowarticlefont" id="title">登录故障录波联网</div> -->
  		<div class="title2 tcenter "><span class="shadowfont">登录故障录波联网 </span></div>
  		<div class="hline1"><div></div></div>
  		<div class="form">
  		<form action="#" method="post" id="lorrform">
  			 <div class="form-group">
			    <div class="input-group">
			      <div class="input-group-addon"><span class="glyphicon glyphicon-user"></span></div>
			      <input class="form-control" autofocus="autofocus" type="text" id="username" name="username" placeholder="输入用户名">
			    </div>
			  </div>
  			 <div class="form-group">
			    <div class="input-group">
			      <div class="input-group-addon"><span class="glyphicon glyphicon-lock"></span></div>
			      <input class="form-control" type="password" id="password" name="password" placeholder="输入密码">
			    </div>
			  </div>
  			 <div class="form-group" style="display: none;" id="repwd">
			    <div class="input-group">
			      <div class="input-group-addon"><span class="glyphicon glyphicon-lock"></span></div>
			      <input class="form-control" type="password"  id="repassword" name="repassword" placeholder="再次输入密码">
			    </div>
			  </div>
  			 <div class="form-group form-inline" style="display: none;" id="captcha">
			    <div class="input-group fleft" style="width: 170px;">
			      <div class="input-group-addon"><span class="glyphicon glyphicon-lock"></span></div>
			      <input class="form-control" type="text" name="captchaCode" id="captchaCode" placeholder="输入验证码">
			    </div>
			    <div class="imgcode fleft ml10"><img id="imgcode" height="30" width="95" class="cursorhand" src="/account/img" alt="点击刷新" onclick="this.src='account/img?t='+new Date().getTime();"/>
			    <a onclick="$('#imgcode').attr('src','/account/img?t='+new Date().getTime());"  class="cursorhand ml10">换一个</a></div>
			 </div>
			  <div class="clearfix"></div>
			  <div class="mcenter tcenter pt20" style="width:300px;">
			  <button class="btn btn-primary " id="submitBtn" type="button" style="width: 120px;">登录</button>
			<!--   <button class="btn btn-link" type="button" action="reg" id="changeBtn" style="width:120px;">注册新用户</button> -->
			  </div>
			  <div class="clearfix"></div>
  		</form>
  		</div>
  		<div class="hline1 mcenter" style="width: 60%;"><div></div></div>
  		<div class="title2 tcenter ">—<span class="shadowfont">山东山大电力技术有限公司 </span>—</div>
  	</div>
  </div>
 
   <script src="assets/js/jquery.js"></script>
	<script src="assets/js/bootstrap.min.js"></script>
	<script src="assets/js/app.js"></script>
	<script type="text/javascript">
	$(function(){
		var actionstr="";
		var action="${action}";
		function toreg(){
			$("#submitBtn").text("注 册 ");
			$("#changeBtn").text(" 登 录 ");
			$("#repwd").show();
			$("#captcha").show();
			$("#changeBtn").attr("action","login");
			$("#title").text("注册故障录波");
			action="/account/reg";
			actionstr="注册新用户";
		}
		function tologin(){
			$("#submitBtn").text(" 登 录 ");
			$("#changeBtn").text(" 注 册 新 用 户  ");
			$("#repwd").hide();
			$("#captcha").hide();
			$("#changeBtn").attr("action","reg");//绑定属性值
			$("#title").text("登录故障录波");
			action="/account/login";
			actionstr="登录";
		}
		if(action&&action=="reg"){
			toreg();
		}else{
			tologin();
		}
		$("#changeBtn").click(function(){
			var action=$(this).attr("action");
			if(action=="reg"){
				toreg();
			}else if(action=="login"){
				tologin();
			}
		});
		function checkFormInput(){
			$("#submitBtn").text("正在检测输入...").attr("disabled","disabled");
			var username=$.trim($("#username").val());
			var password=$.trim($("#password").val());
			if(!username){
				alert("请输入用户名！");
				return false;
			}
			if(!password){
				alert("请输入密码！");
				return false;
			}
			
			if(action.indexOf("reg")!=-1){
				var repassword=$.trim($("#repassword").val());
				if(!repassword||(password!=repassword)){
					alert("两次输入密码不一致！");
					return false;
				}
				var captchaCode=$.trim($("#captchaCode").val());
				if(!captchaCode){
					alert("请输入验证码！");
					return false;
				}
			}
			return true;
		}
		
		
		function resetImgCode(){
			$("#imgcode").attr("src","/account/img?t="+new Date().getTime());
		}
		$("#submitBtn").click(function(){//登录事件具体的执行方法
			if(checkFormInput()){
				$("#submitBtn").text("正在"+actionstr+"...").attr("disabled","disabled");
				$.ajax({
					type:"post",
					url:action,
					data:$("#lorrform").serialize(),
	    			datatype:"json",
	    			success:function(result){
	    				resetImgCode();
	    				if(result.success){
		    				$("#submitBtn").text(actionstr+"成功，跳转中...").attr("disabled","disabled");
		    				setTimeout(function(){
		    					$("#submitBtn").text(actionstr).removeAttr("disabled");
		    				}, 3000);
		    				if(result.data){
		    					if(result.data==1||result.data==3){
				    				window.location.href="/admin";
		    					}else{
		    						window.location.href="/home#/articlemgr";
		    					}
		    				}else{
		    						window.location.href="/";
		    				}
	    				}else{
	    					alert(result.msg);
	    					$("#submitBtn").text(actionstr).removeAttr("disabled");
	    				}
	    				
	    			},
	    			error:function(){
	    				alert("登录失败，请重试!");
	    				$("#submitBtn").text(actionstr).removeAttr("disabled");
	    			}
				});
			}else{
				$("#submitBtn").text(actionstr).removeAttr("disabled");
			}
		});
		
		$("form").keyup(function(e){
			if(e.which==13){
				$("#submitBtn").click();
			}
		});
		
		
	});
	</script>
	<!--[if lt IE 8]>
	<script src="assets/js/ie.js"></script>
	<![endif]-->
  </body>
</html>
