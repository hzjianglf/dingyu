<%@page import="com.whcyz.model.Account"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script type="text/javascript">

	function submitMe(){
		waittingform("accountPwdForm","正在验证和提交数据，请稍后...");
        var pass=checkInput($("#accountPwdForm"));
        if(!pass){
        	closeWaittingForm("accountPwdForm");
        }else{
        	$.ajax({
    			type:"POST",
    			url:"/account/modifyPwd",
    			data:$("#accountPwdForm").serialize(),
    			datatype:"json",
    			success:function(result){
    				if(result.success){
    					$("#modalHtml").empty();
    					$("#modal").modal("hide");
    					closeWaittingForm("accountPwdForm");
    					appSuccessMsg("密码修改成功！", 1000);
    				}else{
    					closeWaittingForm("accountPwdForm");
    					appErrorMsg(result.msg==null?result:result.msg, 1000);
    				}
    			}
    		});
        }
		
	
	}


</script>
<div class="appwaitting" style="display: none;" tabindex="-1" id="formwaitting"><div class="loading-dialog"><div class="loading-msg">正在验证和提交数据，请稍后...</div><div class="loading-icon"></div></div></div>
<form class="form-horizontal" role="form" name="accountPwdForm" id="accountPwdForm">
  <div class="form-group">
    <label for="password" class="col-sm-2 control-label">原密码</label>
    <div class="col-sm-10">
      <input type="password" autofocus="autofocus" class="form-control" check="string" maxlength="40" name="password"  id="password" placeholder="填写原密码">
    </div>
  </div>
  <div class="form-group">
    <label for="newPassword" class="col-sm-2 control-label">新密码</label>
    <div class="col-sm-10">
      <input type="password"  class="form-control" check="string" maxlength="40" name="newPassword"  id="newPassword" placeholder="填写新密码">
    </div>
  </div>
</form>
