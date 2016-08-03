<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div class="appwaitting" style="display: none;" tabindex="-1" id="formwaitting"><div class="loading-dialog"><div class="loading-msg">正在验证和提交数据，请稍后...</div><div class="loading-icon"></div></div></div>
<div class="container">
<div class="row ">
	<div class="col-lg-12 tright">
		<a class="btn btn-default navitem" id="backBtn" href="/articlemgr" onclick="return false;" >返回列表</a>
		<button class="btn btn-primary"  onclick="submitMe()">提交修改</a>
	</div>
</div>
<div class="hline1 mb20"><div></div></div>
<div style="display: none;">
 <form id="articleImgForm" method="post" method="/upload/article"  enctype="multipart/form-data" >
  <input type="file" id="articleImgUlr" accept="image/*" check="string&filepath" onchange="uploadArticleImg()"  tips="请选择一个封面图"  name="imgUrl" >
 </form>
 </div>
  <script type="text/javascript">
  function uploadArticleImg(){
	  appLoading("正在上传图像...");
	  $("#articleImgForm").ajaxSubmit({
		  url : "upload/article",
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
<form class="form-horizontal" role="form" name="articleForm" id="articleForm">
<div class="row">
<div class="col-lg-6">
<input type="hidden" name="article.id" id="articleId" value="${article.id }">
<input type="hidden" name="action" id="action" value="${action}">
  <div class="form-group">
    <label for="articleTitle" class="col-sm-2 control-label">标题</label>
    <div class="col-sm-10">
      <input type="text"   class="form-control" check="string" maxlength="100" name="article.title" value="${article.title }" id="articleTitle" placeholder="填写文章标题">
    </div>
  </div>
  <div class="form-group">
    <label for="articleAuthor" class="col-sm-2 control-label">作者</label>
    <div class="col-sm-10">
      <input type="text" class="form-control"   maxlength="40" name="article.author" value="${article.author==null?user.username:article.author}" id="articleAuthor" placeholder="填写作者姓名">
    </div>
  </div>
  <div class="form-group">
    <label for="articlePostTime" class="col-sm-2 control-label">发布时间</label>
    <div class="col-sm-10">
    <input  type="text" class="form-control"  readonly="readonly" maxlength="100" name="article.postTime" id="articlePostTime" placeholder="填写发布时间" value="<fmt:formatDate value='${article.postTime }' pattern='yyyy-MM-dd HH:mm:ss'/>" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" >
    </div>
  </div>
  
  <div class="form-group">
    <label for="articleCategory" class="col-sm-2 control-label">文章分类</label>
    <div class="col-sm-10">
       <select class="form-control"  check="pint" maxlength="40" name="article.category" value="${article.category }" id="articleCategory" >
		  <option value="0">=选择文章分类=</option>
		  <option value="1">创业故事</option>
		  <option value="2">创业项目</option>
		  <option value="3">创业视点</option>
		  <option value="4">活动交流</option>
	  </select>
    </div>
 
  </div>
  <div class="form-group">
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
  </div>
 <div class="form-group">
    <label for="smcontent" class="col-sm-2 control-label">文章摘要</label>
    <div class="col-sm-10">
    <textarea class="form-control" maxlength="100" rows="3" id="smcontent" name="article.smcontent" placeholder="填写文章摘要，如果不填写，系统会自动匹配前100个有效字符">${article.smcontent }</textarea>
    </div>
  </div>
</div>
<div class="col-lg-6 tcenter">
<div class="form-group">
    <div class="col-sm-12">
	  <input type="hidden" id="imgUrlhidden" name="article.imgUrl" value="${article.imgUrl==null?'assets/css/imgs/articledefaultimg.png':article.imgUrl }">
	  <img id="imgPreview" class="cursorpointer" onclick="$('#articleImgUlr').click();" src="${article.imgUrl==null?'assets/css/imgs/articledefaultimg.png':article.imgUrl }" style="border:1px solid #DDD;width: 500px;height:250px;"/>
    </div>
    <div class="t-red">点击选择文章封面图 </div>
  </div>
</div>
</div>

<div class="row">
<div class="col-lg-12">
<center>
 <!-- 加载编辑器的容器 -->
    <script id="articleContainer"    name="article.content" type="text/plain">${article.content}</script>
    </center>
     <!-- 配置文件 -->
    <script type="text/javascript" src="assets/ueditor/ueditor.config.js"></script>
	  <!-- 编辑器源码文件 -->
    <script type="text/javascript" src="assets/ueditor/ueditor.all.min.js"></script>
    <!-- 实例化编辑器 -->
    <script type="text/javascript">
    var aid=$("#articleId").val();
	if(aid){
		 setSelectValue("articleCategory","${article.category}");
	}else{
		 setSelectValue("articleCategory","${category}");
	}
	var articleue;
	  setTimeout(function(){
	 		appLoading("正在初始化编辑器，请稍后...",2000);
		   articleue= UE.getEditor('articleContainer');
		   articleue.addListener( 'ready', function( editor ) {
		   			closeAppLoading();
			 } );
	   }, 1000);
    var action="${action}";
	var url="/article/"+action;
	function submitMe(){
		if(!articleue.hasContents()){
			appErrorMsg("请输入文章正文内容后提交！",1000);
			return false;
		}
		appLoading("正在验证和提交数据，请稍后...");
        var pass=checkInput($("#articleForm"));
        if(!pass){
        	closeAppLoading();
        }else{
        	$.ajax({
    			type:"POST",
    			url:url,
    			data:$("#articleForm").serialize(),
    			datatype:"json",
    			success:function(result){
    				console.log(result);
    				if(result.success){
    					appSuccessMsg("提交成功", 800);
    					if(confirm("提交成功，是否跳转到列表页面？")){
    						$("#backBtn").click();
    					}else{
    						var u=window.location.href;
    						if(u.indexOf("id=0")!=-1&&action=='add'){
    							window.location.href=u.replace("id=0", "id="+result.data);
    						}
    					}
    				}else{
    					appErrorMsg(result.msg==null?result:result.msg, 2000);
    				}
    			}
    		});
        }
		
	
	}
    </script>
</div>
</div>
</form>
</div>