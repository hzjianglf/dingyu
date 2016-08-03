<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.handany.rbac.model.PmUser"%>
<%@page import="java.lang.String"%>
<%@page import="com.handany.base.common.Constants"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
PmUser user = (PmUser)request.getAttribute("user");
String tokenId = (String)request.getAttribute("tokenId");
String picPath = Constants.IMAGE_SERVER;
String userId = user.getId();
String userType = user.getUserType();
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
    <title></title>
 <%@include file="/common.jsp" %>
    <script type="text/javascript"
	src="<%=basePath%>js/admin/regionSearch.js"></script>
	
<meta content="" name="description"/>
<meta content="" name="author"/>

<title></title>
<!-- BEGIN GLOBAL MANDATORY STYLES -->

<link href="<%=basePath %>css/shop/layoutb.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="<%=basePath %>/css/custom.css">
</head>


	<div class="container-fluid">

	<%--<div class="container-fluid">--%>
	<div class="row" style="border:0;margin-top:-10px;">
		<div class="col-sm-12">
			<h3 class="zs-iframe-title">基本信息</h3>
		</div>
	</div>
			<div class="row" style="padding-top:50px">
				<div class="col-sm-2"><label  style="padding-top:5px;float:right" >用户名</label></div>
				<div class="col-sm-4">
				<%-- <label style="padding-top:5px;"><%=user.getLoginName() == null ? "" : user.getLoginName() %></label> --%>
				<input type="text" class="form-control" disabled  id="loginName" name="name" value="<%=user.getLoginName() == null ? "" : user.getLoginName() %>"/>
				</div>
				<div class="col-sm-7"></div>
			</div><br/>
			
			<div class="row">
				<div class="col-sm-2 "><label  style="padding-top:5px;float:right" >昵称</label></div>
				<div class="col-sm-4"><input type="text" class="form-control"  id="name" name="name" value="<%=user.getName() == null ? "" : user.getName() %>"/></div>
			</div><br />
			<div class="row">
				<div class="col-sm-2 "><label  style="padding-top:5px;float:right" >用户角色</label></div>
				<div class="col-sm-4" id="role" >
					
				</div>
			</div>
	<div class="row" style="margin-top:10px;margin-left:5px;" id="area">
		<div class="col-sm-2 "><label  style="padding-top:5px;float:right" >代理地区</label></div>
		<div class="col-sm-2">
				<select class="form-control zs-select-input" id='provinceSearch'
					onchange='searchConditionCity()'></select>
		</div>
		<div class="col-sm-2">
				<select class="form-control zs-select-input" id='citySearch'
					onchange='searchConditionCounty()'></select>
		</div>
		<div class="col-sm-2">
				<select class="form-control zs-select-input" id='countySearch'></select>
		</div>
	</div>
	<br/>
			<div class="row">
				<div class="col-sm-2 "></div>
				<div class="col-sm-4"><button type="button" class="btn btn-default zs-btn-default" onclick="saveBaseMsg()" >保存</button></div>
				<div class="col-sm-7"></div>
			</div>
</div>
<script>
var tokenId = $("#tokenId").val();
var userId = $("#userId").val();
var path = $("#path").val();
var picPath = $("#picPath").val();
var userId = '<%=userId%>';
var userType = '<%=userType%>';
$(function(){
	if(userType == "3"){
		//代理商
		$("#role").html("代理商");
		$("#area").show();
		sendRequest({id:userId}, "/bm/agent/getAgentDetailByUserId.do", function(json){
			var agent = json.data;
			region1 = agent.region1;
			region2 = agent.region2;
			region3 = agent.region3;
			setDefaultSelect(region1,region2,region3);
			$("#provinceSearch").attr("disabled","disabled");
			$("#citySearch").attr("disabled","disabled");
			$("#countySearch").attr("disabled","disabled");
		});
	}else if(userType == "4"){
		//管理员
		$("#role").html("管理员");
		$("#area").hide();
		
	}
});
	function saveBaseMsg(){
		var name = $("#name").val();		
		name = name.replace(/\s+/g,"");
		
		if(!name){
			showDialog("昵称不能为空","toast");
			return;
		}
		sendRequest({"name":name}, "/pm/user/saveBaseMsg.do", function(json){
			showDialog("修改成功","toast");
		});
	}
	
	function upload(){
   			var myfile = $("#myfile").val();  			
   			if(myfile!=""){  				
   				$.ajaxFileUpload({
   					url:toServerUrl("/bm/picture/centerPicUpload.do?type=" + "userHeader&relateId=" + userId),  					
   					type:"post",
   					fileElementId:"myfile",
   					dataType:"text",
   					success:function(data){   				
   						var text = $(data).html();
						var obj = JSON.parse(text);						
						var pics = obj.data;
						 for(var i = 0; i < pics.length; i ++){
							 
							 var d = document.getElementById("div1");

							 d.style.backgroundColor = "white"; 
							
							$("#imgShow").html("<img class='img-circle' style='width:100px;height:100px' src='"+picPath + pics[0].url +"'/>");
							
						} 
   					}
   				});
   			}else{
   				alert("请至少选择一个文件执行上传操作");
   			}
   		
   	
	
   		}
	
</script>
</html>
