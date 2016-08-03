<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script type="text/javascript">
	var action="${action}";
	var url="/person/"+action;
	function submitMe(callback){
		waittingform("personForm","正在验证和提交数据，请稍后...");
        var pass=checkInput($("#personForm"));
        if(!pass){
        	closeWaittingForm("personForm");
        }else{
        	$.ajax({
    			type:"POST",
    			url:url,
    			data:$("#personForm").serialize(),//序列化（将数据分解成字节流，以便存储在文件中或在网络上传输）
    			datatype:"json",
    			success:function(result){
    				if(callback){
    					callback(result);
    				}
    			}
    		});
        }
		
	
	}
	
	
	$(function(){
		$('#personCompany').autocomplete({
	        source:function(query,process){
	            var matchCount = this.options.items;//返回结果集最大数量
	            $.post("company/autolist",{"matchInfo":query,"matchCount":matchCount},function(respData){
	                return process(respData);
	            });
	        },
	        formatItem:function(item){
	            return item["name"]+" ["+item["pinyin"]+"]";
	        },
	        setValue:function(item){
	        	$("#companyId").val(item["id"]);
	            return {'data-value':item["name"],'real-value':item["id"]};
	        }
	    }).keyup(function(){
	    	var value=$.trim($(this).val());
	    	if(!value||value.length==0){
	    		$("#companyId").val(0);
	    	}
	    });
	})

</script>
<div class="appwaitting" style="display: none;" tabindex="-1" id="formwaitting"><div class="loading-dialog"><div class="loading-msg">正在验证和提交数据，请稍后...</div><div class="loading-icon"></div></div></div>
<form class="form-horizontal" role="form" name="personForm" id="personForm">
 <input type="hidden" name="person.id" id="personId" value="${person.id }">
<input type="hidden" name="simple"  value="true">
<input type="hidden" name="action" id="action" value="${action}"><!-- 这个是form表单中的input标签,标签名为action标签类型为hidden(隐藏的信息)标签id为action表现值为action,如果提交表单的话就是把值value提交给指定的页面. -->
  <div class="form-group">
    <label for="personName" class="col-sm-2 control-label">主站名</label>
    <div class="col-sm-10">
      <input type="text" autofocus="autofocus" class="form-control" check="string" maxlength="40" name="person.name" value="${person.name }" id="personName" placeholder="主站名">
    </div>
  </div>
  <div class="form-group">
    <label class="col-sm-2 control-label">外部编号</label>
    <div class="col-sm-10">
		<label class="radio-inline">
		  <input type="radio" name="person.sex" checked="checked" id="sex1" value="1"> 1
		</label>
		<label class="radio-inline">
		  <input type="radio" name="person.sex" id="sex2" value="2"> 2
		</label>
    </div>
  </div>
 <%--  <div class="form-group">
    <label for="personCompany" class="col-sm-2 control-label">主站名</label>
    <div class="col-sm-10">
      <input type="text" class="form-control"  maxlength="40" name="person.companyName" value="${person.companyName }" id="personCompany" placeholder="填写主站名">
      <input type="hidden" name="person.companyId" id="companyId" value="${person.companyId }">
    </div>
  </div> --%>
  <div class="form-group">
    <label for="personJobTitle" class="col-sm-2 control-label">外部名称</label>
    <div class="col-sm-10">
      <input type="text" class="form-control"  maxlength="40" name="person.jobTitle" value="${person.jobTitle }" id="personJobTitle" placeholder="外部名称">
    </div>
  </div>

  <div class="form-group">
    <label for="personAddress" class="col-sm-2 control-label">数据服务器</label>
    <div class="col-sm-10">
      <input type="text" class="form-control"  maxlength="100" name="person.address" value="${person.address }" id="personAddress" placeholder="数据服务器">
    </div>
  </div>
  <%-- <div class="form-group">
    <label for="personWebsite" class="col-sm-2 control-label">数据库服务器</label>
    <div class="col-sm-10">
      <input type="url" class="form-control" maxlength="255" name="person.website" value="${person.website }" id="personWebsite" placeholder="数据库服务器">
    </div>
  </div> --%>
    <div class="form-group">
    <label for="personRank" class="col-sm-2 control-label">显示顺序</label>
    <div class="col-sm-10">
      <input type="number" min="1" class="form-control" check="int" maxlength="5" name="person.rank" value="${person.rank }" id="personRank" placeholder="填写显示顺序 必须为正数">
    </div>
  </div>
    <div class="form-group">
    <label class="col-sm-2 control-label">前台显示</label>
    <div class="col-sm-10">
		<label class="radio-inline">
		  <input type="radio" name="person.frontshow"  id="frontshow1" value="true"> 显示
		</label>
		<label class="radio-inline">
		  <input type="radio" name="person.frontshow" id="frontshow2" checked="checked" value="false"> 不显示
		</label>
    </div>
  </div>
     <script type="text/javascript">
    var rank=$("#personId").val();
    	if(!rank){
    		$("#personRank").val(1);
    	}else{
    		if("${person.sex}"=="2"){
    			$("#sex2").click();
    		}
    		if("${person.frontshow}"=="true"){
    			$("#frontshow1").click();
    		}
    	}
    </script>
</form>
