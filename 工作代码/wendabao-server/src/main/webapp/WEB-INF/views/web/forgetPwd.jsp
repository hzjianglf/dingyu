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
<title>采供网用户注册系统</title>
<link href="<%=basePath %>css/shop/lanren.css" type="text/css" rel="stylesheet" />
</head>
<body>
<!--代码部分begin-->
<div class="form">
	<div class="div-mobile">
		<label for="name">手机号</label><input style="width:200px; !important" type="text" id="phone" class="infos" placeholder="请输入手机号" />
	</div>
	<div class="div-phone">
		<label for="phone">验证码</label><input style="width:115px; !important" type="text" id="code" class="infos" placeholder="请输入验证码" />
		<a href="javascript:;" class="send1" onclick="sends.send();">发送验证码</a>
	</div>
	<div class="div-ranks">
		<label for="ranks">设置密码</label><input type="password" id="password" class="infos" placeholder="输入新密码"  />
	</div>
	<div class="div-conform">
		<a href="javascript:conform();" class="conform">提交</a>
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
					if($('.div-mobile').find('span').length == 0){
						$('.div-mobile').append('<span class="error">手机格式错误</span>');
						return false;
					}
				}else{
					$('.error').hide();
					var loginName = document.getElementById("phone").value;
					$.ajax({
						url:"getVerifyCode.do",
						data:{							
							"loginName":loginName,
							"sign":"2",
							},
						type:"post",
						dataType:"json",
						success:function(data){   		
						if(true == data.header.success){
							alert("验证码：" + data.verifyCode);
						}else{
							/* if($('.div-phone').find('span').length == 0){
								$('.div-phone').append('<span class="error">用户名或密码错误</span>');
							}else{
								$('.div-phone').find('span').html("用户名或密码错误");
							} */
							alert("获取验证码失败！");
						}

						}
				     });  
				}
			
			if(numbers.test(val)){
				var time = 30;
				$('.div-phone span').remove();
				function timeCountDown(){
					if(time==0){
						clearInterval(timer);
						$('.div-phone a').addClass('send1').removeClass('send0').html("发送验证码");
						sends.checked = 1;
						return true;
					}
					$('.div-phone a').html(time+"S后再次发送");
					time--;
					return false;
					sends.checked = 0;
				}
				$('.div-phone a').addClass('send0').removeClass('send1');
				timeCountDown();
				var timer = setInterval(timeCountDown,1000);
			}
	}
	
}
function conform(){
	var numbers = /^1\d{10}$/;
	var val = $('#phone').val().replace(/\s+/g,""); //获取输入手机号码
	if(!numbers.test(val) || val.length ==0){
		if($('.div-mobile').find('span').length == 0){
			$('.div-mobile').append('<span class="error">手机格式错误</span>');
			
		}else{
			$('.error').show();
		}
		
		return false;
	}
	var password = hex_md5(document.getElementById("password").value);
	var code = document.getElementById("code").value;
	var loginName = document.getElementById("phone").value;
	var code = document.getElementById("code").value;
	$.ajax({
		url:"updateForgetPwd.do",
		data:{
			"password":password,
			"code":code,
			"loginName":loginName,
			},
		type:"post",
		dataType:"json",
		success:function(data){   		
		if(true == data.header.success){
			var tokenId = data.tokenId;
			
			window.location="loginFace.do";
		}else{
			/* if($('.div-phone').find('span').length == 0){
				$('.div-phone').append('<span class="error">用户名或密码错误</span>');
			}else{
				$('.div-phone').find('span').html("用户名或密码错误");
			} */
		}

		}
     });  
}
</script>
<!--代码部分end-->
</body>
</html>