<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.handany.rbac.model.PmUser"%>
<%@page import="java.lang.String"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
PmUser user = (PmUser)request.getAttribute("user");
String tokenId = (String)request.getAttribute("tokenId");
%>
<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en" class="no-js">
<!--<![endif]-->
<!-- BEGIN HEAD -->
<head>
<meta charset="utf-8"/>

	<!-- 为了让 IE 浏览器运行最新的渲染模式下，建议将此 <meta> 标签加入到你的页面中：-->
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	<!--将下面的 <meta> 标签加入到页面中，可以让部分国产浏览器默认采用高速模式渲染页面：-->
	<meta name="renderer" content="webkit">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>采供网</title>

    <!-- Bootstrap -->
    <!-- 新 Bootstrap 核心 CSS 文件 -->
	<link rel="stylesheet" href="<%=basePath %>css/bootstrap.min.css">
	
	<!-- 可选的Bootstrap主题文件（一般不用引入） -->
	<link rel="stylesheet" href="<%=basePath %>css/bootstrap-theme.min.css">
	
	<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
	<script src="<%=basePath %>js/jquery-1.11.3.min.js"></script>	
	<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
	<script src="<%=basePath %>js/bootstrap.min.js"></script>
	<script src="<%=basePath %>js/md5-min.js"></script>
	
<meta content="" name="description"/>
<meta content="" name="author"/>

<title>采供网</title>
<!-- BEGIN GLOBAL MANDATORY STYLES -->

<link href="<%=basePath %>css/shop/layoutb.css" rel="stylesheet" type="text/css" />
</head>


	<div class="container-fluid">
		<h1>个人中心</h1>
		<hr style="height:1px;border:none;border-top:1px solid #555555;" />
		
		<!-- <h2>基础信息</h2> -->
		<ul class="nav nav-tabs">
		  <li id="baseMsg" role="presentation" class="active"><a href="javascript:msgActive()">基础信息</a></li>
		  <li id="changePwd" role="presentation"><a href="javascript:pwdActive()">修改密码</a></li>
		  <li id="changeMobile" role="presentation"><a href="javascript:mobileActive()">更换手机号</a></li>
		   <li id="workModel" role="presentation"><a href="javascript:workModelActive()">工作模式</a></li>
		  <li id="shopIdentify" role="presentation"><a href="javascript:shopIdentifyActive()">商户认证</a></li>
		</ul>
	<!-- 	<ul class="list-inline">
		  <li><h4><a href="#" class="active">基础信息</a></h4></li>
		  <li><h4><a href="#">修改密码</a></h4></li>
		  <li><h5><a href="#">更换手机号</a></h5></li>
		</ul> -->
	<!--  -->
	<div id="include">
		<div id="msg" class="container-fluid" style="padding-top:20px;">
	
			<div class="row">
				
				<div class="col-md-1">名称</div>
			
				<div class="col-md-4"><input type="text" class="form-control" id="name" name="name" value="<%=user.getName() == null ? "" : user.getName() %>"/></div>
				
				<div class="col-md-4"></div>
			</div><br/>
			
			<div class="row">
				<div class="col-md-1 ">手机号</div>
				<div class="col-md-4"><input type="text" class="form-control" id="userMobile" name="mobile" value="<%=user.getMobile() == null ? "" : user.getMobile() %>"/></div>
				<div class="col-md-4"></div>
			</div><br/>
			<div class="row">
				<div class="col-md-1 ">性别</div>
				<div class="radio col-md-4">
				  <label>
				  <%
				  	if(user.getGender() == null){
				  		%>
				  		<input type="radio" name="blankRadio" id="blankRadio1" value="1">男  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				    	<input type="radio" name="blankRadio" id="blankRadio1" value="2">女
				  		<%
				  	}else if(user.getGender().equals("1")){
				  		%>
				  		<input type="radio" name="blankRadio" id="blankRadio1" value="1" checked>男  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				    	<input type="radio" name="blankRadio" id="blankRadio1" value="2">女
				  		<%
				  	}else{
				  		%>
				  		<input type="radio" name="blankRadio" id="blankRadio1" value="1">男  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				    	<input type="radio" name="blankRadio" id="blankRadio1" value="2" checked>女
				  		<%
				  	}
				  %>
				    
				  </label>
				</div>
				<div class="col-md-4"></div>
			 </div>
			 <button type="button" class="btn btn-warning" onclick="saveBaseMsg()">保存</button>
		</div>
	</div>
	<!-- 修改密码  -->
	<div id="pwd" class="container-fluid" style="padding-top:20px;">

		<div class="row">			
			<div class="col-md-1 ">当前密码</div>
			<div class="col-md-4"><input type="password" class="form-control" id="oldPwd" name="oldPwd" /></div>
			<div class="col-md-4"></div>
		</div><br/>
		
		<div class="row">
			<div class="col-md-1 ">新密码</div>
			<div class="col-md-4"><input type="password" class="form-control" id="newPwd" name="newPwd" "/></div>
			<div class="col-md-4"></div>
		</div><br/>
		<div class="row">
			<div class="col-md-1 ">确认密码</div>
			<div class="col-md-4" >
			  <input type="password" class="form-control" name="sureNewPwd" id="sureNewPwd"/>
			</div>
			<div class="col-md-4"><span id="turePassword" style="color:red;">密码不一致！</span></div>
		 </div>
		 <button type="button" class="btn btn-warning" onclick="saveNewPwd()">保存</button>
	</div>
	<!-- 更换手机号  -->
	<div id="mobile" class="container-fluid" style="padding-top:20px;">

		<div class="row">
			
			<div class="col-md-1">旧手机号</div>
			<div class="col-md-4"><input type="text" class="form-control" id="oldMobile" name="oldMobile"/></div>
			<div class="col-md-4"></div>
		</div><br/>
		
		<div class="row">
			<div class="col-md-1">验证码</div>
			<div class="col-md-4"><input type="text" class="form-control" id="verifyCode" name="verifyCode"/></div>
			<div class="col-md-4"><button onclick="getVerifyCode()">获取验证码</button></div>
		</div><br/>
		<div class="row">
			<div class="col-md-1 ">新手机号</div>
			<div class="col-md-4">
			  <input type="text" class="form-control" name="loginName" id="newMobile" name="newMobile"/>
			</div>
			<div class="col-md-4"></div>
		 </div>
		 <button type="button" class="btn btn-warning" onclick="saveNewMobile()">保存</button>
	</div>
	
		<!-- 工作模式 -->
	<div id="workModel1" class="container-fluid" style="padding-top:20px;">			
		<div class="row">			
			<div class="col-md-2">工作状态：</div>
			<div class="col-md-2">
				<select id="workStatus" style="width:100px;">
				  <option value="3">上班</option>
				  <option value="4">下班</option>	  
				</select>
			</div>
			<div class="col-md-2"><button type="button" onclick="saveWorkStatus()">保存</button></div>
		</div>
	
		
	</div>
	<!-- 商户认证 -->
	<div id="shopIdentify1" class="container-fluid" style="padding-top:20px;">
		<div class="row">
			<div class="col-md-2 ">商铺名称</div>
			<div class="col-md-4"><input id="shopName" type="text" /></div>
			<div class="col-md-4"></div>
		</div><br/>
		<div class="row">
			<div class="col-md-2 ">经营项目</div>
			<div class="col-md-4">
			<textarea id="busiScope"  style="width:400px; height:70px;"  placeholder="建议您如实填公司经营的项目！"  data-ph="建议您如实填写详细收货地址，例如街道名称，门牌号码，楼层和房间号等信息"></textarea>
			</div>
			<div class="col-md-4"></div>
		</div><br/>
		<div class="row">
			<div class="col-md-2 ">商铺地址</div>
			<div class="col-md-4">
				<div class="info">				
					<div>
						<select id="s_province" name="s_province"></select>  
					    <select id="s_city" name="s_city" ></select>  
					    <select id="s_county" name="s_county"></select>			    						    
				    </div>
				    <div id="show"></div>
				</div>
			</div>
			<div class="col-md-4"></div>
		</div><br/>
		
		<div class="row">
			<div class="col-md-2 ">详细地址</div>
			<div class="col-md-4">
				<div class="info">				
					<textarea id="address" style="width:400px; height:70px;"  placeholder="建议您如实填公司详细地址，例如街道名称，门牌号码，楼层和房间号等信息"  data-ph="建议您如实填写详细收货地址，例如街道名称，门牌号码，楼层和房间号等信息"></textarea>
				</div>
			</div>
			<div class="col-md-4"></div>
		</div><br/>
		
		<div class="row">
			<div class="col-md-2 ">客服电话</div>
			<div class="col-md-4">
				<input id="telephone" type="text" />
			</div>
			<div class="col-md-4"></div>
		</div><br/>
		<form name="form" action="../../bm/picture/shopIdentifyPicUpload.do" method="post" enctype="multipart/form-data">		
		<ul id="warp" class="list-inline">
		<div class="row">			
			<div class="col-md-2 ">上传营业执照</div>
			<div class="col-md-4">
			
				  <li>
				    <img id="imgShow_WU_FILE_0" width="100" height="100" />
				    <input type="file" name="file" id="up_img_WU_FILE_0" />		    
				  </li>				  
				
			</div>
			<div class="col-md-4"></div>
		</div><br/>
		<!-- 代码部分begin -->

		<div class="row">
			<div class="col-md-2 ">上传资料</div>
			<div class="col-md-4">
				
				  <li>
				    <img id="imgShow_WU_FILE_1" width="100" height="100" />
				    <input type="file" name="file" id="up_img_WU_FILE_1" />		    
				  </li>				  
				
				<!-- <input id="file2" name="file" type="file"/> -->
			</div>
			<div class="col-md-4">
				
			</div>
		</div><br/>
		<div class="row">
			<div class="col-md-2 ">上传资料</div>
			<div class="col-md-4">			 
				  <li>
				    <img id="imgShow_WU_FILE_2" width="100" height="100" />
				    <input type="file" name="file" id="up_img_WU_FILE_2" />		    
				  </li>				  				
			</div>
			<div class="col-md-4"></div>
		 </div>
		 <input type="hidden" id="relateId" name="relateId" value="<%=user.getId() %>" />
		 <input type="hidden" name="type" value="shopI" />
		 <input type="hidden"  name="tokenId" value="<%=tokenId %>" />
		 </form>
		 <button type="button" class="btn btn-warning" onclick="submitShopIdentifyMsg()">提交</button>
	</div>
</ul>	
</div>

<input type="hidden" id="tokenId" value="<%=tokenId %>" />
<input type="hidden" id="userId" value="<%=user.getId() %>" />

<script class="resources library" src="<%=basePath %>js/shop/area.js" type="text/javascript"></script> 
<script class="resources library" src="<%=basePath %>js/shop/uploadPreview.js" type="text/javascript"></script>  
<script type="text/javascript">_init_area();</script>
<script>
var tokenId = $("#tokenId").val();
var userId = $("#userId").val();
$("#pwd").hide();
$("#mobile").hide();
$("#turePassword").hide();
$("#workModel1").hide();
$("#shopIdentify1").hide();
/* 地址设置 */
var Gid  = document.getElementById ;
var showArea = function(){
	Gid('show').innerHTML = "<h3>省" + Gid('s_province').value + " - 市" + 	
	Gid('s_city').value + " - 县/区" + 
	Gid('s_county').value + "</h3>"
							}
Gid('s_county').setAttribute('onchange','showArea()');

	/* 用户中心 */
	function msgActive(){
		$("#baseMsg").addClass("active");
		$("#changePwd").removeClass("active");
		$("#changeMobile").removeClass("active");
		$("#msg").show();
		$("#pwd").hide();
		$("#mobile").hide();
		
		$("#shopIdentify").removeClass("active");
		$("#workModel").removeClass("active");
		$("#shopIdentify1").hide();
		$("#workModel1").hide();
	}
	function pwdActive(){
		$("#changePwd").addClass("active");
		$("#baseMsg").removeClass("active");
		$("#changeMobile").removeClass("active");
		$("#baseMsg").removeClass("active");
		$("#msg").hide();     
		$("#pwd").show();
		$("#mobile").hide();
		
		$("#shopIdentify").removeClass("active");
		$("#workModel").removeClass("active");
		$("#shopIdentify1").hide();
		$("#workModel1").hide();
	}
	function mobileActive(){
		$("#baseMsg").removeClass("active");
		$("#changePwd").removeClass("active");
		$("#changeMobile").addClass("active");
		$("#msg").hide();
		$("#pwd").hide();
		$("#mobile").show();
		
		$("#shopIdentify").removeClass("active");
		$("#workModel").removeClass("active");
		$("#shopIdentify1").hide();
		$("#workModel1").hide();
	}
	
	function workModelActive(){
		$("#baseMsg").removeClass("active");
		$("#changePwd").removeClass("active");
		$("#changeMobile").removeClass("active");
		$("#shopIdentify").removeClass("active");
		$("#workModel").addClass("active");
		
		$("#msg").hide();
		$("#pwd").hide();
		$("#mobile").hide();
		$("#shopIdentify1").hide();
		$("#workModel1").show();
	}
	function shopIdentifyActive(){
		$("#baseMsg").removeClass("active");
		$("#changePwd").removeClass("active");
		$("#changeMobile").removeClass("active");
		$("#shopIdentify").addClass("active");
		$("#workModel").removeClass("active");
		
		$("#msg").hide();
		$("#pwd").hide();
		$("#mobile").hide();
		$("#shopIdentify1").show();
		$("#workModel1").hide();
	}
	

	
	function saveBaseMsg(){

		var name = $("#name").val();
		var mobile = $("#userMobile").val();
		var gender = "";
		var group = document.getElementsByName('blankRadio');
        for(var i = 0; i< group.length; i++){
          if(group[i].checked == true){
        	  gender = group[i].value;
        	  
           }
        } 
        $.ajax({
			url:"saveBaseMsg.do",
			data:{
				"name":name,
				"mobile":mobile,
				"gender":gender,
				"tokenId":tokenId,
				},
			type:"post",
			dataType:"json",
			success:function(data){   		
			if(true == data.header.success){
				alert("修改成功");
				//window.location='manage.do?tokenId='+tokenId;
				//window.location.href="index.html?tokenId=" + tokenId;
			}

			}
	   });  
	}
	
	function saveNewPwd(){
		var oldPwd = hex_md5($("#oldPwd").val());
		var newPwd = hex_md5($("#newPwd").val());
		var sureNewPwd = hex_md5($("#sureNewPwd").val());
		if(newPwd != sureNewPwd){
			$("#turePassword").show();
			return false;
		}else{
			$("#turePassword").hide();
		}
		
		$.ajax({
			url:"saveNewPwd.do?date="+new Date(),
			data:{
				"oldPwd":oldPwd,
				"newPwd":newPwd,
				"tokenId":tokenId,
				"userId":userId,
			},
			type:"post",
			dataType:"json",
			success:function(data){
				if(true == data.header.success){
					alert("修改成功！");
				}else{
					alert("修改失败!");
				}
			}
			
		}); 
	}
	
	function saveNewMobile(){
		var oldMobile = $("#oldMobile").val();
		var verifyCode = $("#verifyCode").val();
		var newMobile = $("#newMobile").val();
		$.ajax({
			url:"saveNewMobile.do?date="+new Date(),
			data:{
				"oldMobile":oldMobile,
				"verifyCode":verifyCode,
				"newMobile":newMobile,
				"tokenId":tokenId,
			},
			type:"post",
			dataType:"json",
			success:function(data){
				if(true == data.header.success){
					alert("修改成功！");
				}else{
					alert("修改失败!");
				}
			}
			
		}); 
		
		
	}
	
	function getVerifyCode(){
		var oldMobile = $("#oldMobile").val();
		$.ajax({
			url:"getVerifyCode.do?date="+new Date(),
			data:{
				"loginName":oldMobile,
				"sign":"2"
			},
			type:"post",
			dataType:"json",
			success:function(data){
				if(true == data.header.success){
					alert("修改成功！");
					alert("验证码："+data.verifyCode);
				}else{
					alert("修改失败!");
				}
			}
			
		}); 
	}
	
	function saveWorkStatus(){
		var status=$("#workStatus").find("option:selected").val();
		$.ajax({
			url:"../../bm/shop/updateWorkStatus.do?date="+new Date(),
			data:{
				"status":status,
				"tokenId":tokenId,
			},
			type:"post",
			dataType:"json",
			success:function(data){
				if(true == data.header.success){
					alert("修改工作状态成功！");
				}else{
					alert("修改失败！");
				}
				
				
			}
			
		});
	}
	function submitShopIdentifyMsg(){
		var f1 = $("#up_img_WU_FILE_0").val();
		var f2 = $("#up_img_WU_FILE_1").val();
		var f3 = $("#up_img_WU_FILE_2").val();
		//var file = document.getElementsByName("file");
		
		var shopName = $("#shopName").val();
		var busiScope = $("#busiScope").val();
		var address = $("#address").val();
		var telephone = $("#telephone").val();
		var region1=$("#s_province").find("option:selected").val();
		var region2=$("#s_city").find("option:selected").val();
		var region3=$("#s_county").find("option:selected").val();
		if("市、县级市" == region3){
			alert("请选择店铺地址！");
			return false;
		}

		if("" == f1 || "" == f2 || "" == f3){
			alert("资料图片必须上传！");
			return false;
		}
			
			$.ajax({
				url:"../../bm/shop/saveShopBaseMsg.do?date="+new Date(),
				data:{
					"shopName":shopName,
					"busiScope":busiScope,
					"region1":region1,
					"region2":region2,
					"region3":region3,
					"address":address,
					"telephone":telephone,		
					"tokenId":tokenId,
				},
				type:"post",
				dataType:"json",
				success:function(data){
					if(true == data.header.success){
						$("#relateId").val(data.shopId);
						document.form.submit();
						alert("提交成功！");
					}else{
						alert("保存商铺基本信息失败！");
					}
					
					
				}
				
			}); 


	}
	
	
</script>
