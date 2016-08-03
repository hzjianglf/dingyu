<%@page import="com.whcyz.model.Account"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script type="text/javascript">
	var action="${action}";
	var url="/account/"+action;
	function submitMe(callback){
		waittingform("accountForm","正在验证和提交数据，请稍后...");
        var pass=checkInput($("#accountForm"));
        if(!pass){
        	closeWaittingForm("accountForm");
        }else{
        	$.ajax({
    			type:"POST",
    			url:url,
    			data:$("#accountForm").serialize(),//jQuery的serialize()方法通过序列化表单值，创建url编码文本字符串 我们就可以选择一个或多个表单元素（可以直接选择form将其序列化)
    			                                  //把序列化的值传给ajax()作为url的参数，
    			datatype:"json",
    			success:function(result){
    				if(callback){
    					callback(result);
    				}
    			}
    		});
        }
		
	
	}
	


</script>
<div class="appwaitting" style="display: none;" tabindex="-1" id="formwaitting"><div class="loading-dialog"><div class="loading-msg">正在验证和提交数据，请稍后...</div><div class="loading-icon"></div></div></div>
 <div style="display: none;">
 <form id="avatarForm" method="post"   enctype="multipart/form-data" >
  <input type="file" id="accountImgUlr" accept="image/*" check="string&filepath" onchange="uploadAvatar()"  tips="请选择一个头像"  name="imgUrl" >
 </form>
 </div>
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
	        	}else{
	        		appErrorMsg("上传失败！"+data.msg==null?data:data.msg, 1000);
	        	}
	        }  
	  });
	  
  }
  </script>
<form class="form-horizontal" role="form" name="accountForm" id="accountForm">
<input type="hidden" name="account.id" id="accountId" value="${account.id }">
<input type="hidden" name="action" id="action" value="${action}">
<fieldset>
  <legend>基本信息</legend>
  <div class="row">
  <div class="col-lg-3 col-md-3 col-xs-12 col-sm-3 tcenter">
  <span class="t-red">点击选择头像</span>
  <input type="hidden" id="imgUrlhidden" name="account.imgUrl" value="${account.imgUrl==null?'assets/css/imgs/defaultavatar.png':account.imgUrl }">
  <img id="imgPreview" class="cursorpointer" onclick="$('#accountImgUlr').click();" src="${account.imgUrl==null?'assets/css/imgs/defaultavatar.png':account.imgUrl }" height="100" width="100"/>
  </div>
  
  <div class="col-lg-9 col-md-9 col-xs-12 col-sm-9 tleft">
  <div class="form-group">
    <label for="username" class="col-sm-3 control-label">用户名</label>
    <div class="col-sm-9">
      <input type="text" autofocus="autofocus" class="form-control" check="string" maxlength="40" name="account.username" value="${account.username }" id="username" placeholder="填写完用户名">
    </div>
  </div>
  <c:if test="${action eq 'add' }">
	  <div class="form-group">
	    <label for="password" class="col-sm-3 control-label">密码</label>
	    <div class="col-sm-9">
	      <input type="password" class="form-control"  check="string" maxlength="20"  name="account.password" id="password" placeholder="填写密码">
	    </div>
	  </div>
  </c:if>
   <div class="form-group">
    <label for="permission" class="col-sm-3 control-label">权限组</label>
    <div class="col-sm-9">
      <select class="form-control" check="pint" id="permission" name="account.permission">
		  <option value="0">=选择权限组=</option>
		  <option value="<%=Account.PERMISSION_ADMIN%>">管理员</option>
		  <option value="<%=Account.PERMISSION_EDITOR%>">网站编辑</option>
		  <option value="<%=Account.PERMISSION_NORMAL%>">普通用户</option>
	  </select>
	  <script type="text/javascript">
	  setSelectValue("permission",${permission});
	  </script>
    </div>
  </div>
  </div>
  </fieldset>
  <fieldset>
  <legend>联系方式</legend>
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
  </fieldset>
</form>
