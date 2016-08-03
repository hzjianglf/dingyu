<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script type="text/javascript">
	var action="${action}";
	var url="/link/"+action;
	function submitMe(callback){
		waittingform("linkForm","正在验证和提交数据，请稍后...");
        var pass=checkInput($("#linkForm"));
        if(!pass){
        	closeWaittingForm("linkForm");
        }else{
        	$.ajax({
    			type:"POST",
    			url:url,
    			data:$("#linkForm").serialize(),
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
<form class="form-horizontal" role="form" name="linkForm" id="linkForm">
<input type="hidden" name="link.id" id="linkId" value="${link.id }">
<input type="hidden" name="action" id="action" value="${action}">
  <div class="form-group">
    <label for="linkname" class="col-sm-2 control-label">链接名称</label>
    <div class="col-sm-10">
      <input type="text" autofocus="autofocus" class="form-control" check="string" maxlength="40" name="link.name" value="${link.name }" id="linkname" placeholder="填写链接显示文字">
    </div>
  </div>
  <div class="form-group">
    <label for="linkurl" class="col-sm-2 control-label">链接地址</label>
    <div class="col-sm-10">
      <input type="url" class="form-control" check="string" maxlength="255" name="link.url" value="${link.url }" id="linkurl" placeholder="填写链接URL 必须以 http:// 开头">
    </div>
  </div>
  <div class="form-group">
    <label for="linkrank" class="col-sm-2 control-label">显示顺序</label>
    <div class="col-sm-10">
      <input type="number" min="1" class="form-control" check="int" maxlength="5" name="link.rank" value="${link.rank }" id="linkrank" placeholder="填写显示顺序 必须为正数">
    </div>
    <script type="text/javascript">
    var rank=$("#linkId").val();
    	if(!rank){
    		$("#linkrank").val(1);
    	}
    </script>
  </div>
</form>
