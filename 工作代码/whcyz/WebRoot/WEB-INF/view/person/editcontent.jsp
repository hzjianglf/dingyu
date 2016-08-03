<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div class="appwaitting" style="display: none;" tabindex="-1" id="formwaitting"><div class="loading-dialog"><div class="loading-msg">正在验证和提交数据，请稍后...</div><div class="loading-icon"></div></div></div>
<div class="container">
<div class="row ">
	<div class="col-lg-12 tright">
		<a class="btn btn-default navitem" id="backBtn" href="/personmgr" onclick="return false;" >返回列表</a>
		<button class="btn btn-primary" onclick="submitMe()">提交修改</a>
	</div>
</div>
<div class="hline1 mb20"><div></div></div>
<div style="display: none;">
 <form id="personImgForm" method="post" method="/upload/person"  enctype="multipart/form-data" >
  <input type="file" id="personImgUlr" accept="image/*" check="string&filepath" onchange="uploadpersonImg()"  tips="请选择一个形象图"  name="imgUrl" >
 </form>
 </div>
  <script type="text/javascript">
  function uploadpersonImg(){
	  appLoading("正在上传图像...");
	  $("#personImgForm").ajaxSubmit({
		  url : "upload/person",
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
<form class="form-horizontal" role="form" name="personForm" id="personForm">
<div class="row">
<div class="col-lg-6">
<input type="hidden" name="person.id" id="personId" value="${person.id }">
<input type="hidden" name="simple"  value="false">
<input type="hidden" name="action" id="action" value="${action}">
  <div class="form-group">
    <label for="personName" class="col-sm-2 control-label">人物姓名</label>
    <div class="col-sm-10">
      <input type="text" autofocus="autofocus" class="form-control" check="string" maxlength="40" name="person.name" value="${person.name }" id="personName" placeholder="填写人物姓名">
    </div>
  </div>
  <div class="form-group">
    <label class="col-sm-2 control-label">性别</label>
    <div class="col-sm-10">
		<label class="radio-inline">
		  <input type="radio" name="person.sex" checked="checked" id="sex1" value="男"> 男
		</label>
		<label class="radio-inline">
		  <input type="radio" name="person.sex" id="sex2" value="女"> 女
		</label>
    </div>
  </div>
  <div class="form-group">
    <label for="personCompany" class="col-sm-2 control-label">所在公司</label>
    <div class="col-sm-10">
      <input type="text" class="form-control"  maxlength="40" name="person.companyName" value="${person.companyName }" id="personCompany" placeholder="填写所在公司">
      <input type="hidden" name="person.companyId" id="companyId" value="${person.companyId }">
    </div>
  </div>
  <div class="form-group">
    <label for="personJobTitle" class="col-sm-2 control-label">职务</label>
    <div class="col-sm-10">
      <input type="text" class="form-control"  maxlength="40" name="person.jobTitle" value="${person.jobTitle }" id="personJobTitle" placeholder="填写职务">
    </div>
  </div>

  <div class="form-group">
    <label for="personAddress" class="col-sm-2 control-label">地址</label>
    <div class="col-sm-10">
      <input type="text" class="form-control"  maxlength="100" name="person.address" value="${person.address }" id="personAddress" placeholder="填写地址">
    </div>
  </div>
  <div class="form-group">
    <label for="personWebsite" class="col-sm-2 control-label">相关网址</label>
    <div class="col-sm-10">
      <input type="url" class="form-control" maxlength="255" name="person.website" value="${person.website }" id="personWebsite" placeholder="填写个人相关网址 必须以http://开头">
    </div>
  </div>
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
</div>
<div class="col-lg-6 tcenter">
<div class="form-group">
    <div class="col-sm-12">
	  <input type="hidden" id="imgUrlhidden" name="person.imgUrl" value="${person.imgUrl==null?'assets/css/imgs/persondefaultimg.png':person.imgUrl }">
	  <img id="imgPreview" class="cursorpointer" onclick="$('#personImgUlr').click();" src="${person.imgUrl==null?'assets/css/imgs/persondefaultimg.png':person.imgUrl }" style="border:1px solid #DDD;width:250px;height:250px;"/>
    </div>
    <div class="t-red">点击选择人物形象图 建议 按照 比例长宽1:1 250*250</div>
  </div>
</div>
</div>
<div class="row">
<div class="col-lg-12">
<center>
 <!-- 加载编辑器的容器 -->
    <script id="personContainer" autofocus="autofocus"  name="person.content" type="text/plain">${person.content}</script>
    </center>
      <!-- 配置文件 -->
    <script type="text/javascript" src="assets/ueditor/ueditor.config.js"></script>
      <!-- 编辑器源码文件 -->
    <script type="text/javascript" src="assets/ueditor/ueditor.all.min.js"></script>
    <!-- 实例化编辑器 -->
    <script type="text/javascript">
    var rank=$("#personId").val();
	if(!rank){
		$("#personRank").val(1);
	}else{
		if("${person.sex}"=="女"){
			$("#sex2").click();
		}
		if("${person.frontshow}"=="true"){
			$("#frontshow1").click();
		}
	}
	
        var personue;
    	  setTimeout(function(){
    	 		appLoading("正在初始化编辑器，请稍后...",2000);
    	 		personue= UE.getEditor('personContainer');
    	 		personue.addListener( 'ready', function( editor ) {
    		   			closeAppLoading();
    			 } );
    	   }, 1000);
        
        
        var action="${action}";
    	var url="/person/"+action;
    	function submitMe(){
    		if(!personue.hasContents()){
    			appErrorMsg("请输入文章正文内容后提交！",1000);
    			return false;
    		}
    		appLoading("正在验证和提交数据，请稍后...");
            var pass=checkInput($("#personForm"));
            if(!pass){
            	closeAppLoading();
            }else{
            	$.ajax({
        			type:"POST",
        			url:url,
        			data:$("#personForm").serialize(),
        			datatype:"json",
        			success:function(result){
        				if(result.success){
        					appSuccessMsg("修改成功", 800);
        					if(confirm("修改成功，是否跳转到列表页面？")){
        						$("#backBtn").click();
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