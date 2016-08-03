<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div class="appwaitting" style="display: none;" tabindex="-1" id="formwaitting"><div class="loading-dialog"><div class="loading-msg">正在验证和提交数据，请稍后...</div><div class="loading-icon"></div></div></div>
<div class="container">
<div class="row ">
	<div class="col-lg-12 tright">
		<a class="btn btn-default navitem" id="backBtn" href="/companymgr" onclick="return false;" >返回列表</a>
		<button class="btn btn-primary" onclick="submitMe()">提交修改</a>
	</div>
</div>
<div class="hline1 mb20"><div></div></div>
<div style="display: none;">
 <form id="companyImgForm" method="post" method="/upload/company"  enctype="multipart/form-data" >
  <input type="file" id="companyImgUlr" accept="image/*" check="string&filepath" onchange="uploadcompanyImg()"  tips="请选择一个形象图"  name="imgUrl" >
 </form>
 </div>
  <script type="text/javascript">
  function uploadcompanyImg(){
	  appLoading("正在上传图像...");
	  $("#companyImgForm").ajaxSubmit({
		  url : "upload/company",
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
<form class="form-horizontal" role="form" name="companyForm" id="companyForm">
<div class="row">
<div class="col-lg-6">
<input type="hidden" name="company.id" id="companyId" value="${company.id }">
<input type="hidden" name="simple"  value="false">
<input type="hidden" name="action" id="action" value="${action}">
  <div class="form-group">
    <label for="companyName" class="col-sm-2 control-label">公司名称</label>
    <div class="col-sm-10">
      <input type="text"  class="form-control" check="string" maxlength="40" name="company.name" value="${company.name }" id="companyName" placeholder="填写公司名称">
    </div>
  </div>
  <div class="form-group">
    <label for="companyIndustry" class="col-sm-2 control-label">所属行业</label>
    <div class="col-sm-10">
      <input type="text" class="form-control" check="string" maxlength="40" name="company.industry" value="${company.industry }" id="companyIndustry" placeholder="填写所属行业名称">
    </div>
  </div>
  <div class="form-group">
    <label for="companyStage" class="col-sm-2 control-label">融资阶段</label>
    <div class="col-sm-10">
       <select class="form-control"  maxlength="40" name="company.stage" value="${company.stage }" id="companyStage" >
		  <option value="未融资">=选择融资阶段=</option>
		  <option value="初创型(天使轮)">初创型(天使轮)</option>
		  <option value="成长型(A轮)">成长型(A轮)</option>
		  <option value="成长型(B轮)">成长型(B轮)</option>
		  <option value="成熟型(C轮)">成熟型(C轮)</option>
		  <option value="成熟型(D轮以及以上)">成熟型(D轮以及以上)</option>
		  <option value="已上市">已上市</option>
	  </select>
    </div>
  </div>
  <div class="form-group">
    <label for="companyAddress" class="col-sm-2 control-label">地址</label>
    <div class="col-sm-10">
      <input type="text" class="form-control" check="string" maxlength="100" name="company.address" value="${company.address }" id="companyAddress" placeholder="填写公司所在地址">
    </div>
  </div>
  <div class="form-group">
    <label for="companyWebsite" class="col-sm-2 control-label">官网</label>
    <div class="col-sm-10">
      <input type="url" class="form-control" maxlength="255" name="company.website" value="${company.website }" id="companyWebsite" placeholder="填写公司官网网址 必须以http://开头">
    </div>
  </div>
    <div class="form-group">
    <label for="companyRank" class="col-sm-2 control-label">显示顺序</label>
    <div class="col-sm-10">
      <input type="number" min="1" class="form-control" check="int" maxlength="5" name="company.rank" value="${company.rank }" id="companyRank" placeholder="填写显示顺序 必须为正数">
    </div>
  </div>

</div>
<div class="col-lg-6 tcenter">
<div class="form-group">
    <div class="col-sm-12">
	  <input type="hidden" id="imgUrlhidden" name="company.imgUrl" value="${company.imgUrl==null?'assets/css/imgs/companydefaultimg.png':company.imgUrl }">
	  <img id="imgPreview" class="cursorpointer" onclick="$('#companyImgUlr').click();" src="${company.imgUrl==null?'assets/css/imgs/companydefaultimg.png':company.imgUrl }" style="border:1px solid #DDD;width:250px;height:250px;"/>
    </div>
    <div class="t-red">点击选择公司形象图 建议 按照 比例长宽1:1 250*250</div>
  </div>
</div>
</div>
<div class="row ">
<div class="col-lg-12">
<center>
 <!-- 加载编辑器的容器 -->
    <script id="companyContainer" autofocus="autofocus"  name="company.content" type="text/plain">${company.content}</script>
</center>
     <!-- 配置文件 -->
    <script type="text/javascript" src="assets/ueditor/ueditor.config.js"></script>
      <!-- 编辑器源码文件 -->
    <script type="text/javascript" src="assets/ueditor/ueditor.all.min.js"></script>
    <!-- 实例化编辑器 -->
    <script type="text/javascript">
    var rank=$("#companyId").val();
	if(!rank){
		$("#companyRank").val(1);
	}else{
		 setSelectValue("companyStage","${company.stage==null?'未融资':company.stage}");
	}
      var companyue;
  	  setTimeout(function(){
  	 		appLoading("正在初始化编辑器，请稍后...",2000);
  	 		companyue= UE.getEditor('companyContainer');
  	 		companyue.addListener( 'ready', function( editor ) {
  		   			closeAppLoading();
  			 } );
  	   }, 1000);
        var action="${action}";
    	var url="/company/"+action;
    	function submitMe(){
    		if(!companyue.hasContents()){
    			appErrorMsg("请输入正文内容后提交！",1000);
    			return false;
    		}
    		appLoading("正在验证和提交数据，请稍后...");
            var pass=checkInput($("#companyForm"));
            if(!pass){
            	closeAppLoading();
            }else{
            	$.ajax({
        			type:"POST",
        			url:url,
        			data:$("#companyForm").serialize(),
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