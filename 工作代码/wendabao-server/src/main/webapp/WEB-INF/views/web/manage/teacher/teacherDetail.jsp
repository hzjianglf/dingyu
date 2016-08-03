<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.handany.rbac.model.PmUser"%>
<%@page import="com.handany.base.common.ApplicationConfig"%>
<%@page import="java.lang.String"%>
<%@page import="com.handany.base.common.Constants"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	PmUser user = (PmUser) request.getAttribute("user");
	String tokenId = (String) request.getAttribute("tokenId");
	String picPath = Constants.IMAGE_SERVER;
	String imageServer = ApplicationConfig.getConfig("image_server");

	String teacherId = request.getParameter("teacherId");
%>
<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en" class="no-js">
<!--<![endif]-->
<!-- BEGIN HEAD -->
<head>
<%@include file="/common.jsp" %>
<script src="<%=basePath%>js/region.js" type="text/javascript"></script>
<title>学生详情</title>

<style type="text/css">
.contentContainer {
	margin-top: 15px;
}

.contentContainer .form-tr {
	margin-top: 15px;
}

.contentContainer .form-title {
	text-align: right;
	padding-top: 5px;
}

.td_input {
	border: white;
	align: center;
}

td {
	width: 100px;
}

img {
	width: 200px;
	height: 200px;
	border: none;
	vertical-align: bottom;
}

td {
	text-align: center;
}
</style>
</head>
<div class="container-fluid">
	<div class="row" style="border: 0; margin-top: -10px;">
		<div class="col-sm-12">
			<h3 class="zs-iframe-title">教师详情<span class="showStatus"></span></h3>
		</div>
	</div>
	<div class="status" style="float:right;">
		
		<div class="operate" style="display:none;">
			<input type="text" id="backMsg" class="form-group" placeholder="反馈信息" />
			<button class="btn large btn-success" id="pass"> 通过</button>
			<button class="btn btn-danger" id="refuse"> 拒绝</button>
		</div>
	</div>
<ul class="nav nav-tabs">
		<li role="presentation" class="active" id="tab-img"><a
			href="javascript:showContainer('tab-img','imgContainer')">图片</a></li>
		<li role="presentation"  id="tab-base"><a
			href="javascript:showContainer('tab-base','baseContainer')">基本信息</a></li>
		<li role="presentation" id="tab-record"><a
			href="javascript:showContainer('tab-record','recordContainer')">答疑记录</a></li>
	</ul>
	
	<div class="container contentContainer" id="imgContainer"
	style="display: block;">
		<form class="form-horizontal" role="search">
			<div class="form-group">
				<label class="col-sm-2 col-md-2 control-label">头像</label>
				<div class=" col-sm-10 col-md-10">
					<img src="<%=basePath%>image/logo-1.png"  class="form-group" id="avator"/>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 col-md-2 control-label">教师资格证</label>
				<div class=" col-sm-10 col-md-10">
					<img src="<%=basePath%>image/logo-1.png" class="form-group" id="file"/>
				</div>
			</div>
		</form>
	</div>
	<div class="container contentContainer" id="baseContainer" style="display: none;">
		<form class="form-horizontal" role="search">
			<div class="form-group">
				<label class="col-sm-2 col-md-2 control-label">用户名</label>
				<div class=" col-sm-10 col-md-10">
					<span type="text" class="form-group" id="username"> </span>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 col-md-2 control-label">姓名</label>
				<div class="col-sm-10 col-md-10">
					<span rows="5" class="form-group" id="name">六十年</span>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 col-md-2 control-label">教龄</label>
				<div class=" col-sm-10 col-md-10">
					<span type="text" class="form-group" id="time">20</span>
				</div>
			</div>
			
			<div class="form-group ">
				<label class="col-sm-2 col-md-2 control-label">地区</label>

				<div class="col-sm-10 col-md-10 type_sum">
					<span rows="5" class="form-group" id="area">山东淄博博山</span>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 col-md-2 control-label">学校</label>
				<div class="col-sm-10 col-md-10 param_sum">
					<span rows="5" class="form-group" id="school">淄博一中</span>
				</div>
			</div>
			
			<div class="form-group ">
				<label class="col-sm-2 col-md-2 control-label">个人介绍</label>
				<div class="col-sm-10 col-md-10 type_sum">
					<span rows="5" class="form-group" id="introduce">苏打水dad撒旦撒旦撒旦三大啊</span>
				</div>
			</div>
			<div class="form-group ">
				<label class="col-sm-2 col-md-2 control-label">教学经历</label>
				<div class="col-sm-10 col-md-10 type_sum">
					<span rows="5" class="form-group" id="caree">撒大苏打撒三大</span>
				</div>
			</div>
		</form>
	</div>

	<div class="container contentContainer" id="recordContainer" style="display: none;">
	
		<div style="margin-top:10px;">
			<div class="col-sm-12">
	 		<table class="table table-hover">
		   		<thead>
		          <tr>
		          	<th width="100">教室Id</th>
		          	<th width="100">答疑时长（秒）</th>
		          	<th width="100">答疑开始时间</th>
					<th width="100">答疑结束时间</th>
					<th width="100" >学生Id</th>
		            <th width="100" >学生名称</th>
		          </tr>
		        </thead>
		        <tbody id="data_content">
		   
		   
		        </tbody>
  		</table>
  	</div>
  <div class="col-sm-12" id="navArea">
 	 <nav class="data-nav" >	
		  <ul class="pagination" >
		    <li class="disabled">
		      <a href="#" aria-label="Previous">
		        <span aria-hidden="true" >上一页</span>
		      </a>
		    </li>			    
		    <li  class="disabled">
		      <a href="#" aria-label="Next">
		        <span aria-hidden="true">下一页</span>
		      </a>
		    </li>
		  </ul>
		</nav>		
	</div>
</div>
	</div>
	
<script type="text/javascript">
		var teacherUserId = "";
		var status = "";
		$(function(){
			var teacherId = '<%=teacherId%>';
			sendRequest({"id":teacherId},"/bm/teacher/queryById.do",
					function(json){
							var teacher = json.teacher;
							if(teacher.userPic){
								$("#avator").attr("src",'<%=imageServer%>'+teacher.userPic);
							}
							if(teacher.certificatePic){
								$("#file").attr("src",'<%=imageServer%>'+teacher.certificatePic);
							}
							$("#username").text(teacher.user?teacher.user.mobile:"暂无");
							$("#name").text(teacher.name?teacher.name:"暂无");
						 	$("#time").text(teacher.schoolAge?teacher.schoolAge:"暂无");
							$("#area").text((undefinedHandler(teacher.region1Txt)+
									undefinedHandler(teacher.region2Txt)+undefinedHandler(teacher.region3Txt))?
											(undefinedHandler(teacher.region1Txt)+undefinedHandler(teacher.region2Txt)+undefinedHandler(teacher.region3Txt)):"暂无"
									);
							$("#school").text(teacher.school?teacher.school:"暂无");
							$("#introduce").text(teacher.intro?teacher.intro:"暂无");
							$("#caree").text(teacher.resume?teacher.resume:"暂无"); 
							if( teacher.status == "3"){
								$(".operate").css("display","block");
							}
							status = teacher.status;
							$(".showStatus").html("("+showStatus(teacher.status)+")");
							 teacherUserId = teacher.user?teacher.user.id:"";
							 if(status == 6){
									toPage(0);
								}else{
									$("#recordContainer").html("暂无");
								}
					});
			
			
		});
		function toPage(start){
			if(!start){
				start = 0;
			}
			sendRequest({start:start}, "/bm/qa_log/getTeacherQaLog.do", function(json){
				var list = json.data.list;
				for(var i=0;i<list.length;i++){
					var item = list[i];
					$("#data_content").append(
							["    <tr>           ",
							 "			        <td align=\"center\">"+undefinedHandler(item.qaClassroom)+"</td>",
							 "			        <td align=\"center\">"+undefinedHandler(item.qaLength)+"</td>",
							 "			        <td align=\"center\">"+undefinedHandler(item.qaStart)+"</td>",
							 "			        <td align=\"center\">"+undefinedHandler(item.qaEnd)+"</td> ",
							 "			        <td align=\"center\">"+undefinedHandler(item.qaStudent)+"</td> ",
							 "			        <td align=\"center\">"+undefinedHandler(item.qaStudentName)+"</td>",
							 "			        <td align=\"center\">",
							 operate,
							 "			        </td>",
							 "			      </tr>"].join("")
							);
				}
				$("#navArea").html(showNav(json.data));
			});
		}
		function showStatus(status){
			if(status == 1){
				return "新注册";
			}
			if(status == 2){
				return "待提交资料";
			}
			if(status == 3){
				return "审批中";
			}
			if(status == 4){
				return "审批通过";
			}
			if(status == 5){
				return "审批未通过";
			}
			if(status == 6){
				return "正常使用";
			}
		}
        function showContainer(tab, container) {
	        $("[role='presentation']").each(function(i) {
	        		$(this).removeClass("active");
	        });
	        $("#" + tab).addClass("active");
	        $(".contentContainer").each(function(i) {
	       		 $(this).hide();
	        });
	        $("#" + container).show();
        }
        $("#pass").bind("click",function(){
        	
        	showDialog("确认通过吗？","confirm", {
        		onOk:function(){
        			
        			sendRequest({approvalInfo:$("#backMsg").val(),status:"1",teacherUserId:teacherUserId,}, "/bm/teacher/saveApprovalInfo.do", 
        					function(json){
        						showDialog("操作成功", "toast");
        						setTimeout(function(){
        							location.reload();
        						}, 2000)
        						
        			});
        		},
        		onCancel:function(){
        			
        		}
        	});
        });
        $("#refuse").bind("click",function(){
        	showDialog("确认拒绝吗？","confirm", {
        		onOk:function(){
        			alert(111);
        		},
        		onCancel:function(){
        		
        		}
        	});
        });
        </script>