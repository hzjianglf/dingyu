<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>采供网登录系统</title>
<link href="<%=basePath %>css/shop/lanren.css" type="text/css" rel="stylesheet" />
</head>
<body>
<!--代码部分begin-->
<div class="form">
	
	<div class="div-phone">
		<label for="phone">手机</label><input style="width:200px; !important" type="text" id="phone" class="infos" placeholder="请输入手机" />
		<a href="javascript:;" class="code" ></a>
	</div>
	<div class="div-ranks">
		<label for="ranks">密码</label><input  type="password" id="password" class="infos" placeholder="请输入密码"  />
	</div>
	<div class="div-conform">
		<a href="forgetPwd.do">忘记密码？</a><a href="javascript:sends.send()" class="conform" >登录</a>
	</div>
</div>
<script src="<%=basePath %>js/jquery-1.11.3.min.js"></script>
<script src="<%=basePath %>js/md5-min.js"></script>
<script>
var sends = {
	send:function(){
			var numbers = /^1\d{10}$/;
			var val = $('#phone').val().replace(/\s+/g,""); //获取输入手机号码

				if(!numbers.test(val) || val.length ==0){
					if($('.div-phone').find('span').length == 0){
						$('.div-phone').append('<span class="error">手机号格式不对！</span>');
					}					
					return false;
				}else{
					
					var password = hex_md5(document.getElementById("password").value);
					var loginName = document.getElementById("phone").value;
					
					$.ajax({
						url:"login.do",
						data:{
							"password":password,
							"loginName":loginName,
							},
						type:"post",
						dataType:"json",
						success:function(data){   		
						if(true == data.header.success){
							var tokenId = data.tokenId;
							window.top.location='manage.do?tokenId='+tokenId;
							//window.location.href="index.html?tokenId=" + tokenId;
						}else{
							if($('.div-phone').find('span').length == 0){
								$('.div-phone').append('<span class="error">用户名或密码错误</span>');
							}else{
								$('.div-phone').find('span').html("用户名或密码错误");
							}
						}

						}
				   });  
				}
			
			
	}
}
</script>
<!--代码部分end-->
</body>
</html>