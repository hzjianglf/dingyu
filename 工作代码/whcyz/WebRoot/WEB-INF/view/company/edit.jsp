<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script type="text/javascript">
	var action="${action}";
	var url="/company/"+action;
	function submitMe(callback){
		waittingform("companyForm","正在验证和提交数据，请稍后...");
        var pass=checkInput($("#companyForm"));
        if(!pass){
        	closeWaittingForm("companyForm");
        }else{
        	$.ajax({
    			type:"POST",
    			url:url,
    			data:$("#companyForm").serialize(),
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
<form class="form-horizontal" role="form" name="companyForm" id="companyForm">
<input type="hidden" name="company.id" id="companyId" value="${company.id }">
<input type="hidden" name="simple"  value="true">
<input type="hidden" name="action" id="action" value="${action}">
   <div class="form-group">
    <label for="companyName" class="col-sm-2 control-label">电压等级</label>
    <div class="col-sm-10">
     <%--  <input type="text" class="form-control" check="string" maxlength="40"  name="company.name"  value="${company.name }" id="companyName" placeholder="电压等级"> --%>
      <select  class="form-control" check="string" maxlength="40"  name="company.name"  value="${company.name }" id="companyName" placeholder="电压等级">
      <option value="0">=选择电压等级=</option>
		  <option value="220kv">220kv</option>
		  <option value="110kv">110kv</option>
		  <option value="66kv">66kv</option>
		  <option value="500kv">500kv</option>
		  <option value="1000kv">1000kv</option>
		  <option value="330kv">330kv</option>
		  </select>
    </div>
  </div> 
  <div class="form-group">
    <label for="companyIndustry" class="col-sm-2 control-label">变电站</label>
    <div class="col-sm-10">
      <input type="text" class="form-control" check="string" maxlength="40" name="company.industry" value="${company.industry }" id="companyIndustry" placeholder="变电站">
    </div>
  </div>
  <div class="form-group">
    <label for="companyStage" class="col-sm-2 control-label">所属主站</label>
    <div class="col-sm-10">
       <select class="form-control"  maxlength="40" name="company.stage" value="${company.stage }" id="companyStage" >
		  <option value="0">=选择主站=</option>
		  <option value="历下变电站">历下变电站</option>
		  <option value="历城变电站">历城变电站</option>
		  <option value="高新变电站">高新变电站</option>
		  <option value="工业南变电站">工业南变电站</option>
		  <option value="槐荫变电站">槐荫变电站</option>
		  <option value="南外环变电站">南外环变电站</option>
	  </select>
    </div>
  </div>
  <div class="form-group">
    <label for="companyAddress" class="col-sm-2 control-label">地址</label>
    <div class="col-sm-10">
      <input type="text" class="form-control" check="string" maxlength="100" name="company.address" value="${company.address }" id="companyAddress" placeholder="地址">
    </div>
  </div>
  <div class="form-group">
    <label for="companyWebsite" class="col-sm-2 control-label">官网</label>
    <div class="col-sm-10">
      <input type="url" class="form-control" maxlength="255" name="company.website" value="${company.website }" id="companyWebsite" placeholder="网址 必须以http://开头">
    </div>
  </div>
    <div class="form-group">
    <label for="companyRank" class="col-sm-2 control-label">显示顺序</label>
    <div class="col-sm-10">
      <input type="number" min="1" class="form-control" check="int" maxlength="5" name="company.rank" value="${company.rank }" id="companyRank" placeholder="填写显示顺序 必须为正数">
    </div>
    <script type="text/javascript">
    var rank=$("#companyId").val();
    	if(!rank){
    		$("#companyRank").val(1);
    	}else{
    		 setSelectValue("companyStage","${company.stage==null?'未融资':company.stage}");
    	}
    </script>
  </div>
</form>
