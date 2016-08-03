<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"><head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>微聚家后台管理系统</title>
</head>


<style>
/*火狐*/
@
-moz-document
 
url-prefix
 
()
{
}
/*谷歌*/
@media screen and (-webkit-min-device-pixel-ratio:0) {
}

.ww {
	border: 1px solid #025398;
	height: 18px;
	width: 150px;
	background-color: #dfdfdf;
}

.y {
	border: 1px solid #025398;
	height: 18px;
	background-color: #dfdfdf;
}
    .ww2{border:1px solid #26A0DA}
/*.myd {
	background: #77887E;
	height: 100%;
	left: 0%;
	right: 0%;
	width: 100%;
	position: absolute;
	z-index: 10000;
} 
*/
.pd {
	background: #c2ee11;
	height: 300px;
	width: 450px;
	position: absolute;
	z-index: 10000000;
}

 
 

</style>
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
<link rel="stylesheet" type="text/css" href="../manager/css/default.css">
<link rel="stylesheet" type="text/css" href="../manager/themes/icon.css">
<link rel="stylesheet" type="text/css" href="../manager/css/demo.css">
<link id="easyuiTheme" rel="stylesheet" href="../manager/themes/default/easyui.css" type="text/css">
<script type="text/javascript" src="../manager/js/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="../manager/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="../manager/js/jquery.cookie.js"></script>
<script language="JavaScript" type="text/javascript">
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
		var enterId = $("#enterId").val();
		if (pwd == "" || uname == "") {
			alert("账号密码不能为空");
			return;
		}
		$.ajax({
			url : 'check!checkYan?yzm=' + yzm + '&uname=' + uname + '&pwd='
					+ pwd + '&enterId=' + enterId + '&cookie=' + cookie,
			type : 'post',
			dataType : "json",
			success : function(data) {
				if (data != "false") {
					if (data == "login_error") {
						alert("用户名或密码错误!");
						stateYzm = "true";
					} else {
						if (data == "1") {
							//alert("1212121");
							window.location.href = "manage?enterId=" + enterId
									+ "&enId=" + enterId;
							return;
						}
						if (data == "4") {
							$.ajax({
								type : "post",
								url : '../site/check!getChile',
								data : {
									'enterId' : enterId
								},
								success : function(date) {
								  
								  $('#pd').window('open');
									$("#pd").css("display", "block");
									$("#qyId").html(date);
									
									//pop();
								},
								error : function(date) {
								}
							});
							//document.getElementById("chile").style.display = "";
							//$("#chile").show();	
						}
						if(data=="tiyan"){
						alert("您的账号体验时间已到期！请联系");
						}
						
						
						

					}
				} else {
					stateYzm = "false";
					alert("验证码错误");
					//window.location.href = "admin";
					return;
				}
			},
			error : function(a, x, e) {
				alert("网路异常！");
			}
		});

	}

	function qiehuan() {
		var enterId = $("#enterId").val();
		var id = $("#qyId").val();
		if(id==-1){
		$.messager.alert('消息', '请选择企业!');
		}else{
		var date = new Date();
							date.setTime(date.getTime() - 10000);
							document.cookie = "wxpts" + "=a;expires="
									+ date.toGMTString() + "; path=/";
		$
				.ajax({
					type : "POST",
					url : "../site/manage!qiehuan",
					data : {
						'enterId' : id,
						'enId' : enterId
					},
					dataType : "json",
					success : function() {
						/* top.location.href = "http://www.uniqyw.com/wxpt/site/manage?enterId="
								+ enterId
								+ "&type=1&qiehuan=1&enId="+enId; */
								/* window.location.href = "manage?enterId=" + enterId
									+ "&type=1&enId=" + enterId; */
									
						window.location.href = "manage?enterId="+ id + "&enId=" + enterId;
						  $('#pd').window('close');
						// top.location.replace("http://localhost:8080/wxpt/site/manage?enterId="+enterId+"&type=1");
					},
					error : function() {
						$.messager.alert('消息', '网络异常!');
						close();
						$('#tt').datagrid('reload');
						close1();

					}
				});
				}

	}
	function quxiao() {
	var enterId = $("#enterId").val();
	window.location.href = "manage?enterId=" + enterId+ "&enId=" + enterId;
	 $('#pd').window('close');
	}

	function reset() {
		$("#uname").val("");
		$("#pwd").val("");
		$("#rand").val("");
	}
	document.onkeydown = function(e) {
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


 <script type="text/javascript">
document.oncontextmenu=function(){
var selection="";//定义鼠标拖选值
if(document.selection){//***** IE
selection=document.selection.createRange().text;
}else{//***** 其他浏览器
selection=document.getSelection();
}
//if来判断拖选值、被单击的区域是不是表单域值
if(selection==""&&(event.srcElement.value==undefined||event.srcElement.value==0)&&event.srcElement.value!=0)
return false;
}


</script>  
<style type="text/css">
#bb {
	background: #014DA3;
	overflow: hidden;
	overflow: hidden !important;
	_overflow: hidden;
	_overflow: hidden !important;
}

 *html #bb{
background:#014DA3;

overflow:hidden;


}
 
*+html #bb {
	background: #014DA3;
	overflow: hidden;
}
</style>

<body id="bb" >
	<!--  background:url(/wxpt/manager/images/xingxinghemohu.png) no-repeat;-->
	<div id="xingxinghemohu" 
		style="background:url(/wxpt/manager/images/xingxinghemohu.png) no-repeat;position:absolute; height:120%;width:150%;top:-20px;left:-300px;">
		<img src="/wxpt/manager/images/xiabolangxian.png"
			style="position:absolute;top:70%;left:50%;" />下波浪线
		<div
			style="background:url(/wxpt/manager/images/youshangjiao.png) no-repeat;position:absolute; height:300px;width:300px;top:0px;left:69%;"></div>
		<div
			style="width:520px;height:520px; margin:0 auto; background:url(/wxpt/manager/images/xiaobeijing.png) no-repeat;position:absolute;top:30%;left:37%;">
			<!--登陆小页面-->
			<div id="login">
				<input type="hidden" id="cookie" name="cookie">
				<div id="center">
					<div id="center_left"></div>
					<div id="center_middle">
						<table align="center" style="padding-top:20px;" cellspacing="6"
							cellpadding="0">
							<tr>
								<img src="/wxpt/manager/images/xitonglogo.png" />
							</tr>
							<tr id="user">
								<td><b>用户名:</b></td>
								<td><input class="ww" type="text" name="username"
									id="uname" />
								</td>
							</tr>
							<tr id="password">
								<td><b>密&nbsp;&nbsp;码:</b></td>
								<td><input type="password" class="ww" name="password"
									id="pwd" />
								</td>
							</tr>
							<tr id="wz">
								<td><b>验证码:</b></td>
								<td><input name=rand type=text value="" class="y"
									style="width:82px;[;width:82px;];width:86px\9\0;" id="rand" />
									<a href="#"><IMG id="yzm"
										style="margin-bottom: -5px;*margin-bottom: -2px;"
										border="none"
										onclick="this.src = this.src + '?' + new Date().getTime();"
										alt="点击刷新验证码" src="../verify.jsp" id="qiehuan"> </a>
								</td>

							</tr>


							<tr id="btn">
								<input id="enterId" value="${enterId}" type="hidden" />
								<td></td>
								<td colspan="2"><a href="#" id="input11" onClick="save()" /><img
									border="none" src="/wxpt/manager/images/denglu.png" /></a> &nbsp;<a
									href="#" onClick="reset()"><img border="none"
										src="/wxpt/manager/images/qingkong.png" /> </a></td>
							</tr>
						</table>
						<div></div>
					</div>
					<div id="center_right"></div>
				</div>
			</div>
		</div>


		<div
			style="background:url(/wxpt/manager/images/xiabianshangxian.png) repeat-x;position:absolute;top:81%;height:33px;width:125%;">

			<img src="/wxpt/manager/images/xiabianguangying.png"
				style="width:100%;" /> <span
				style="position: relative;top:-175px;[;top:-183px;];*top:-185px;  _top:-120px;left:33%;  font-size: 12px;*font-size: 12px;">版本信息:
				微聚家 2013 v1.0</span>
			<!-- top:-175px;[;top:-183px;];*top:-185px;  _top:-155px; -->
		</div>
	</div>
	<!-- 遮盖层 -->
	<div class="myd" style="display: none" id="md"></div>

	<!-- 弹出层 -->
	
    <div id="pd" class="easyui-window" title="是否切换登陆"
		style="overflow:hidden; background:#E2EDFF;height:150px;width:270px;  display:none;"
		iconCls="icon-edit" data-options="modal:true,closed:true"
		maximizable="false" closed="true" maximizable="false"  minimizable="false" closable="false"


		 collapsible="false" >
        <div style="margin:20px;">
		<div id="chile">
			<table>
				<tr>
					<td  >切换企业：</td>
					<td><select id="qyId" name="qyId" class="ww2" style=" width:130px;" >
					</select>
					</td>
				</tr>
                  </table>
                    <div style="position:relative;left:25px; margin-top:20px; " >	
                 <a class="easyui-linkbutton" style="" iconCls="icon-ok"
                    href="#" onClick="qiehuan();">切换</a> <a
                    class="easyui-linkbutton" iconCls="icon-cancel"
                     href="#" onClick="quxiao();">取消</a></div>
			</div>
          
           
		</div>
	</div>
	<br>

</body>
</html>
