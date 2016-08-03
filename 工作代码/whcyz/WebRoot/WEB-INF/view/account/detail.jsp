<%@page import="com.whcyz.model.Account"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="../common/header.html" %>
    <link rel="stylesheet" type="text/css" href="assets/css/accountdetail.css">
  <!-- 中间部分 -->
  <div class="main">
	<ol class="breadcrumb">
	  <li><a href="#"><i class="glyphicon glyphicon-home pr10"></i>首页</a></li>
	  <li class="active">个人信息完善</li>
	</ol>
<div class="mainbox">
	  		 <div style="display: none;">
 <form id="avatarForm" method="post"   enctype="multipart/form-data" >
  <input type="file" id="accountImgUlr" accept="image/*" check="string&filepath" onchange="uploadAvatar()"  tips="请选择一个头像"  name="imgUrl" >
 </form>
 </div>
 <script src="assets/js/jquery.form.js"></script>
  <script type="text/javascript">
  function uploadAvatar(){
	  appLoading("正在上传图像...");
	  $("#avatarForm").ajaxSubmit({
		  url : "upload/avatar",
		  type: "post",
		  datatype:"json",
		  success: function(data){
	        	if(data.success){
	        		$("#imgUrlhidden").val(data.data);
	        		$("#imgPreview").attr("src",data.data);
	        		appSuccessMsg("上传成功！", 1000);
	        		modifyinfo();
	        	}else{
	        		appErrorMsg("上传失败！"+data.msg==null?data:data.msg, 1000);
	        	}
	        }  
	  });
	  
  }
  </script>
  <div class="row">
  <div class="col-lg-5">
 	<div class="panel panel-info">
 			<div class="panel-heading">
		    <h3 class="panel-title">当前登录用户信息</h3>
		  </div>
		  <div class="panel-body">
		  <div class="list-group">
				  <li class="list-group-item navitem">当前用户<span class="badge">${session.user.nickname }</span></li>
				  <li class="list-group-item navitem">权限组<span class="badge" id="userpermission"></span></li>
				  <li class="list-group-item navitem">本次登录时间 <span class="badge"><fmt:formatDate value="${session.user.loginTime }" pattern='yyyy年MM月dd日  HH:mm:ss'/></span></li>
				  <li class="list-group-item navitem">本次登录时IP<span class="badge">${session.user.loginIp }</span></li>
				  <li class="list-group-item navitem">上次登录时间 <span class="badge"><fmt:formatDate value="${session.user.lastLoginTime }" pattern='yyyy年MM月dd日  HH:mm:ss'/></span></li>
				  <li class="list-group-item navitem">上次登录时IP<span class="badge">${session.user.lastLoginIp }</span></li>
				</div>
		  </div>
		  <script type="text/javascript">
				   var pstr="未知";
		        	 switch(${session.user.permission}){
		        	 case 1:
		        		 pstr="超级管理员";
		        		break;
		        	 case 2:
		        		 pstr="普通用户";
		        		break;
		        	 case 3:
		        		 pstr="网站编辑";
		        		break;
		        	 }
		        	 $("#userpermission").text(pstr);
				  </script>
		  </div>
 	</div>
 	<div class="col-lg-7" >
 	<div class="panel panel-info">
 			<div class="panel-heading">
		    <h3 class="panel-title">用户信息修改</h3>
		  </div>
		  <div class="panel-body">
<form class="form-horizontal" role="form" name="accountForm" id="accountForm">
<input type="hidden" name="account.id" id="accountId" value="${account.id }">
<fieldset>
  <legend>用户头像</legend>
  <div class="row">
  <div class="col-lg-12 tcenter">
  <input type="hidden" id="imgUrlhidden" name="account.imgUrl" value="${account.imgUrl==null?'assets/css/imgs/defaultavatar.png':account.imgUrl }">
  <img id="imgPreview" class="cursorpointer" onclick="$('#accountImgUlr').click();" src="${account.imgUrl==null?'assets/css/imgs/defaultavatar.png':account.imgUrl }" height="100" width="100"/>
  <div class="t-red">点击选择头像</div>
  </div>
  </div>
  </fieldset>
 
  <fieldset>
  <legend>基本信息和联系方式</legend>
  <div class="form-group">
    <label for="username" class="col-sm-2 control-label">用户名</label>
    <div class="col-sm-10">
      <input type="text" autofocus="autofocus" class="form-control" check="string" maxlength="40" name="account.username" value="${account.username }" id="username" placeholder="填写完用户名">
    </div>
  </div>
     <div class="form-group">
	    <label for="nickname" class="col-sm-2 control-label">昵称</label>
	    <div class="col-sm-10">
	      <input type="text" class="form-control" name="account.nickname" value="${account.nickname }" id="nickname" placeholder="填写昵称">
	    </div>
  	</div>
     <div class="form-group">
	    <label for="phone" class="col-sm-2 control-label">手机号</label>
	    <div class="col-sm-10">
	      <input type="text" class="form-control" name="account.phone" value="${account.phone }"  id="phone" placeholder="填写手机号">
	    </div>
  	</div>
  	  <div class="form-group">
	    <label for="qq" class="col-sm-2 control-label">QQ号码</label>
	    <div class="col-sm-10">
	      <input type="text" class="form-control" name="account.qq" value="${account.qq }"  id="qq" placeholder="填写QQ号码">
	    </div>
  	</div>
  	  <div class="form-group">
	    <label for="email" class="col-sm-2 control-label">电子邮件</label>
	    <div class="col-sm-10">
	      <input type="email" class="form-control" name="account.email" value="${account.email }"  id="email" placeholder="填写电子邮箱地址">
	    </div>
  	</div>
   <div class="form-group">
   <div class="col-sm-12 tcenter">
   <button class="btn btn-primary" type="button" onclick="modifyinfo()">提交修改</button>
   <button class="btn btn-primary" type="reset">重置</button>
   </div>
   </div>
     <script type="text/javascript">
  function modifyinfo(){
	  appLoading("正在提交数据...");
	  var pass=checkInput($("#accountForm"));
      if(!pass){
    	  closeAppLoading();
      }else{
	  $("#accountForm").ajaxSubmit({
		  url : "account/updateDetail",
		  type: "post",
		  datatype:"json",
		  success: function(data){
	        	if(data.success){
	        		appSuccessMsg("修改成功！", 500);
	        		if(data.imgUrl&&data.imgUrl.length>0){
	        			$("#imgUrlhidden").val(data.imgUrl);
		        		$("#imgPreview").attr("src",data.imgUrl);
	        		}
	        	}else{
	        		appErrorMsg("修改失败！"+data.msg==null?data:data.msg, 2000);
	        	}
	        }  
	  });
      }
	  
  }
  </script>
  </fieldset>
</form>
  <fieldset>
  <legend>修改密码</legend>
<form class="form-horizontal" role="form" name="accountPwdForm" id="accountPwdForm">
  <div class="form-group">
    <label for="password" class="col-sm-2 control-label">原密码</label>
    <div class="col-sm-10">
      <input type="password"   class="form-control" check="string" maxlength="40" name="password"  id="password" placeholder="填写原密码">
    </div>
  </div>
  <div class="form-group">
    <label for="newPassword" class="col-sm-2 control-label">新密码</label>
    <div class="col-sm-10">
      <input type="password"  class="form-control" check="string" maxlength="40" name="newPassword"  id="newPassword" placeholder="填写新密码">
    </div>
  </div>
    <div class="form-group">
   <div class="col-sm-12 tcenter">
   <button class="btn btn-primary" type="button" onclick="modifyPwd()">提交修改</button>
   <button class="btn btn-primary" type="reset">重置</button>
   </div>
   </div>
</form>
 <script type="text/javascript">
  function modifyPwd(){
	  appLoading("正在提交数据...");
	  var pass=checkInput($("#accountPwdForm"));
      if(!pass){
    	  closeAppLoading();
      }else{
	  $("#accountPwdForm").ajaxSubmit({
		  url : "account/modifyPwd",
		  type: "post",
		  datatype:"json",
		  success: function(data){
	        	if(data.success){
	        		appSuccessMsg("修改成功！", 2000);
	        	}else{
	        		appErrorMsg("修改失败！"+data.msg==null?data:data.msg, 2000);
	        	}
	        }  
	  });
      }
	  
  }
  </script>
</fieldset>
</div>
</div>
</div>
</div>
</div>
  </div>
  <div class="clearfix"></div>
  <%@include file="../common/footer.html" %>
  </body>
</html>
    