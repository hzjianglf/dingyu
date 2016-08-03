<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC"-//W3C//DTDXHTML1.0Strict//EN"   "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>微信通后台管理系统</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<link href="<%=basePath%>siteManage/images/login.css" rel="stylesheet" type="text/css" />
  </head>
  
  <script language="JavaScript">
	window.onUnload = function() {
		var ck = document.cookie.split(";");
		var re = /.*?=/;
		var ne = [];
		for (i = 0; i < ck.length; i++) {
			ne[i] = re.exec(ck[i])[0];
			document.cookie = ne[i] + ";expires=Sat, 20 Jun 2009 23:17:11 UTC";
		}
	};
	function correctPNG() {
		var arVersion = navigator.appVersion.split("MSIE")
		var version = parseFloat(arVersion[1])
		if ((version >= 5.5) && (document.body.filters)) {
			for ( var j = 0; j < document.images.length; j++) {
				var img = document.images[j];
				var imgName = img.src.toUpperCase();
				if (imgName.substring(imgName.length - 3, imgName.length) == "PNG") {
					var imgID = (img.id) ? "id='" + img.id + "' " : ""
					var imgClass = (img.className) ? "class='" + img.className
							+ "' " : ""
					var imgTitle = (img.title) ? "title='" + img.title + "' "
							: "title='" + img.alt + "' "
					var imgStyle = "display:inline-block;" + img.style.cssText
					if (img.align == "left")
						imgStyle = "float:left;" + imgStyle
					if (img.align == "right")
						imgStyle = "float:right;" + imgStyle
					if (img.parentElement.href)
						imgStyle = "cursor:hand;" + imgStyle
					var strNewHTML = "<span "
							+ imgID
							+ imgClass
							+ imgTitle
							+ " style=\""
							+ "width:"
							+ img.width
							+ "px; height:"
							+ img.height
							+ "px;"
							+ imgStyle
							+ ";"
							+ "filter:progid:DXImageTransform.Microsoft.AlphaImageLoader"
							+ "(src=\'" + img.src
							+ "\', sizingMethod='scale');\"></span>"
					img.outerHTML = strNewHTML
					j = j - 1
				}
			}
		}
	}
	window.attachEvent("onload", correctPNG);
</script>
<script type="text/javascript" src="../manager/js/jquery-1.8.3.js"></script>
<script type="text/javascript" src="../manager/js/jquery.cookie.js"></script>
<script>
	function save() {
		document.cookie = "cookietest=true";
		if (!navigator.cookieEnabled && !document.cookie) {
			$("#cookie").val("false");
			alert("cookie已关闭，请打开，否则将能访问!");
			$.cookie('cookietest', null);
			return;
		} else {
			$("#cookie").val("true");
			$.cookie('cookietest', null);
		}
		var yzm = $("#rand").val();
		var uname = $("#uname").val();
		var cookie = $("#cookie").val();
		var pwd = $("#pwd").val();
		if (pwd == "" || uname == "") {
			alert("账号密码不能为空");
			return;
		}
		$
				.ajax({
					url : 'check!checkYan?yzm=' + yzm
							+ '&uname=' + uname + '&pwd=' + pwd + '&cookie='
							+ cookie,
					type : 'post',
					dataType : "json",
					success : function(data) {
						if (data != "false") {
							if (data == "login_error") {
								alert("用户名或密码错误!");
								stateYzm = "true";
							} else {
								if(data=="0")
								{
									window.location.href = "manage!index2";
									return;
								}if(data=="1"){
									//window.location.href="http://www.uniqyw.com/xiangxd/enterprise/user/template/manage/template-property";
								   window.location.href = "manage";
								   return;
								}else{
								
								    //window.location.href="http://www.uniqyw.com/xiangxd/siteManage/agent-index.jsp";
								   return;
								}
							
							}
						} else {
							stateYzm = "false";
							alert("验证码错误");
							window.location.href = "admin";
						}
					},
					error : function(a, x, e) {
						alert("网路异常！");
					}
				});

	}
	
	function reset() {
		$("#uname").val("");
		$("#pwd").val("");
		$("#rand").val("");
	}
	document.onkeydown = function (e) { 
	var theEvent = window.event || e; 
	var code = theEvent.keyCode || theEvent.which; 
		var employeeUsernamev = document.getElementById("uname").value;
		var employeePasswordv = document.getElementById("pwd").value;
		var yzmv = document.getElementById("rand").value;
 		var employeeUsername = document.getElementById("uname");
		var employeePassword = document.getElementById("pwd");
		var yzm = document.getElementById("rand");
		var Submit = document.getElementById("input11");
		if (code == 13) {
			if (employeeUsernamev == null || employeeUsernamev == "") {
				employeeUsername.focus();
			} else if (employeePasswordv == null || employeePasswordv == "") {
				employeePassword.focus();
			} else if (yzmv == null || yzmv == "") {
				yzm.focus();
			} else {
				Submit.click();
			}
		}
} 
</script>
<body>

	<div id="login">
		<input type="hidden" id="cookie" name="cookie">
		<div id="top">
			<div id="top_left">
				<img src="../siteManage/images/login_03.gif" />
			</div>
			<div id="top_center"></div>
		</div>

		<div id="center">
			<div id="center_left"></div>
			<div id="center_middle">
				<table align="center" style="padding-top:20px;" cellspacing="6"
					cellpadding="0">
					<tr id="user">
						<td>用户</td>
						<td><input type="text" name="username" id="uname" />
						</td>
					</tr>
					<tr id="password">
						<td>密码</td>
						<td><input type="password" name="password" id="pwd" />
						</td>
					</tr>
					<tr id="wz">
						<td>验证码</td>
						<td><input name=rand type=text value="" maxLength=4 size=10
							id="rand" />
						</td>
					</tr>
					
					<tr id="yzm">
						<td colspan="2" align="center"><IMG
							onclick="this.src = this.src + '?' + new Date().getTime();"
							alt=点我换一张 src="<%=basePath%>verify.jsp" id="qiehuan"></td>
					</tr>
					<tr id="btn">
						<td colspan="2"><a href="#" id="input11" onclick="save()">登录</a><a
							href="#" onclick="reset()">清空</a></td>
					</tr>
				</table>
				<div></div>
			</div>
			<div id="center_right"></div>
		</div>
		<div id="down">
			<div id="down_left">
				<div id="inf">
					<span class="inf_text">版本信息</span> <span class="copyright">联通企业网
						2013 v2.0</span>
				</div>
			</div>
			<div id="down_center"></div>
		</div>

	</div>
</body>
</html>

