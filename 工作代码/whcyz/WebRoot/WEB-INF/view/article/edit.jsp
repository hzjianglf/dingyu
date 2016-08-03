<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script type="text/javascript">
	var action="${action}";
	var url="/article/"+action;
	function submitMe(callback){
		waittingform("articleForm","正在验证和提交数据，请稍后...");
        var pass=checkInput($("#articleForm"));
        if(!pass){
        	closeWaittingForm("articleForm");
        }else{
        	$.ajax({
    			type:"POST",
    			url:url,
    			data:$("#articleForm").serialize(),
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
		$('#articleCompany').autocomplete({
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
		$('#articlePerson').autocomplete({
	        source:function(query,process){
	            var matchCount = this.options.items;//返回结果集最大数量
	            $.post("person/autolist",{"matchInfo":query,"matchCount":matchCount},function(respData){
	                return process(respData);
	            });
	        },
	        formatItem:function(item){
	            return item["name"]+" ["+item["pinyin"]+"]";
	        },
	        setValue:function(item){
	        	$("#personId").val(item["id"]);
	            return {'data-value':item["name"],'real-value':item["id"]};
	        }
	    }).keyup(function(){
	    	var value=$.trim($(this).val());
	    	if(!value||value.length==0){
	    		$("#personId").val(0);
	    	}
	    });
	})
</script>
<div class="appwaitting" style="display: none;" tabindex="-1" id="formwaitting"><div class="loading-dialog"><div class="loading-msg">正在验证和提交数据，请稍后...</div><div class="loading-icon"></div></div></div>
<form class="form-horizontal" role="form" name="articleForm" id="articleForm">
<input type="hidden" name="article.id" id="articleId" value="${article.id }">
<input type="hidden" name="action" id="action" value="${action}">
  <div class="form-group">
    <label for="articleTitle" class="col-sm-2 control-label">主站</label>
    <div class="col-sm-10">
      <input type="text"  class="form-control" check="string" maxlength="100" name="article.title" value="${article.title }" id="articleTitle" placeholder="主站">
    </div>
  </div>
  <div class="form-group">
    <label for="articleAuthor" class="col-sm-2 control-label">录波器</label>
    <div class="col-sm-10">
      <input type="text" class="form-control"   maxlength="40" name="article.author" value="${article.author==null?user.username:article.author}" id="articleAuthor" placeholder="录波器">
    </div>
  </div>
  <div class="form-group">
    <label for="articlePostTime" class="col-sm-2 control-label">录波时间</label>
    <div class="col-sm-10">
    <input  type="text" class="form-control"  readonly="readonly" maxlength="100" name="article.postTime" id="articlePostTime" placeholder="录波时间" value="<fmt:formatDate value='${article.postTime }' pattern='yyyy-MM-dd HH:mm:ss'/>" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" >
    </div>
  </div>
  
  <div class="form-group">
    <label for="articleCategory" class="col-sm-2 control-label">变电站分类</label>
    <div class="col-sm-10">
       <select class="form-control"  check="pint" maxlength="40" name="article.category" value="${article.category }" id="articleCategory" >
		  <option value="0">=选择变电站分类=</option>
		  <option value="1">福州变电站</option>
		  <option value="2">龙岩变电站</option>
		  <option value="3">泉州变电站</option>
		  <option value="4">厦门变电站</option>
	  </select>
    </div>
        <script type="text/javascript">
    	var aid=$("#articleId").val();
    	if(aid){
    		 setSelectValue("articleCategory","${article.category}");
    	}else{
    		 setSelectValue("articleCategory","${category}");
    	}
    </script>
  </div>
 <%--  <div class="form-group">
    <label for="articleCompany" class="col-sm-2 control-label">关联公司</label>
    <div class="col-sm-10">
      <input type="text" class="form-control"  maxlength="40" name="article.relCompanyName" value="${article.relCompanyName }" id="articleCompany" placeholder="填写关联公司">
      <input type="hidden" name="article.relCompanyId" id="companyId" value="${article.relCompanyId }">
    </div>
  </div>
  <div class="form-group">
    <label for="articlePerson" class="col-sm-2 control-label">关联人物</label>
    <div class="col-sm-10">
      <input type="text" class="form-control"  maxlength="40" name="article.relPersonName" value="${article.relPersonName }" id="articlePerson" placeholder="填写关联人物">
      <input type="hidden" name="article.relPersonId" id="personId" value="${article.relPersonId }">
    </div>
  </div> --%>
</form>
