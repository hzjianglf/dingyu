<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@page import="java.lang.String"%>
<%@page import="com.github.pagehelper.PageInfo"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%@page import="com.handany.base.common.Constants"%>
<%@page import="com.handany.rbac.model.PmUser"%>
<%@page import="com.handany.rbac.common.UserContextManager"%>
<%
String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
+ request.getContextPath() + "/";
String picPath = Constants.IMAGE_SERVER;
String split =Constants.SPLITOR;

PmUser user = UserContextManager.getLoginUser();
String userType = user.getUserType();
String userId = user.getId();
%>
<!DOCTYPE html>
<html lang="zh-CN">

<head>
 <%@include file="/common.jsp" %>
<script type="text/javascript"
	src="<%=basePath%>js/admin/regionSearch.js"></script>
<title>教室管理</title>
</head>
<body>
<div class="container" style="width:100%;">
	<div class="row" style="border:0;margin-top:-10px;">
		<div class="col-sm-12">
			<h3 class="zs-iframe-title">教室管理</h3>
		</div>
	</div>
	<div class="row" style="margin-top:10px;margin-left:5px;">
		<div class="col-sm-3">
			目前在线:&nbsp;&nbsp;&nbsp;<span id="online">
				</span>
		</div>
		<div class="col-sm-2">
			总共:&nbsp;&nbsp;&nbsp;<span id="sum">
				</span>
		</div>
		<div class="col-sm-1 col-sm-offset-1"  style=" padding-left:0px;text-align:right;">
			<label  style=" padding-top:7px;">状态</label>
		</div>
		<div class="col-sm-3" style=" padding-left:0px;">
			<select class="form-control zs-select-input"  id='status' >
				<option value='' selected = "selected">请选择教室状态</option>
				<option value='1'  >创建</option>
				<option value='2' >普通</option>
				<option value='3' >在线</option>
				<option value='0' >删除</option>
			</select>
		</div>
	</div>
	<div class="row" style="margin-top:10px;margin-left:5px;">
		<div class="col-sm-1"  style=" padding-left:0px;text-align:right;">
			<label  style=" padding-top:7px;">地区</label>
		</div>
		<div class="col-sm-2">
				<select class="form-control zs-select-input" id='provinceSearch'
					onchange='searchConditionCity()'></select>
		</div>
		<div class="col-sm-2">
				<select class="form-control zs-select-input" id='citySearch'
					onchange='searchConditionCounty()'></select>
		</div>
		<div class="col-sm-2">
				<select class="form-control zs-select-input" id='countySearch'></select>
		</div>
		<div class="col-sm-2  col-sm-offset-3"  style=" padding-left:0px;text-align:right;">
			<button type="button" class="btn btn-default zs-btn-default " id="queryBtn" >查询</button>
		</div>
	</div>
<div style="margin-top:10px;">
	<div class="col-sm-12">
	 <table class="table table-hover">
   		<thead>
          <tr>
          	<th width="100">指导教师</th>
			<th width="100">教室名称</th>
            <th width="100" >所在学校</th>
            <th width="100" >辅导年级</th>
            <th width="100" >辅导课程</th>
            <th width="100">教室状态</th>
            <!-- <th width="100">操作</th> -->
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
var region1;
var region2;
var region3;
//记住上次查询的地址
var region1T ;
var region2T;
var region3T ;
var userType = '<%=userType%>';
var userId = '<%=userId%>';

$(function(){
	if(userType == 3){
		//代理商的话，地区不能选择，根据其代理区域查询教师
		sendRequest({id:userId}, "/bm/agent/getAgentDetailByUserId.do", function(json){
			var agent = json.data;
			region1 = agent.region1;
			region2 = agent.region2;
			region3 = agent.region3;
			setDefaultSelect(region1,region2,region3);
			if(region1){
				$("#provinceSearch").attr("disabled","disabled");
			}else{
				$("#provinceSearch").css("display","none");
			}
			if(region2){
				$("#citySearch").attr("disabled","disabled");
			}else{
				$("#citySearch").css("display","none");
			}
			if(region3){
				$("#countySearch").attr("disabled","disabled");
			}else{
				$("#countySearch").css("display","none");
			}
			toPage(0);
		});
	}else{
		toPage(0);
	}
	//count();
})
$("#queryBtn").bind("click",function(){
	toPage(0);

});
function count(){
	sendRequest("",
			"/bm/classroom/queryClassrooms.do",
			function(json){
				$("#sum").html(json.data.total);
			});
	sendRequest({status:3},
			"/bm/classroom/queryClassrooms.do",
			function(json){
				$("#online").html(json.data.total);
			});
}
function toPage(start){
	if($("#queryBtn").hasClass("isLoading")){
			return;
		}
	$("#queryBtn").addClass("isLoading").html("loading...");
	if(start == undefined || start == null){
			start = 0;
		}
	var status=$("#status").val();
	//var name = $("#name").val();
	//var phone = $("#phone").val();
	
 	var region1 = $("#provinceSearch").val();
	var region2 = $("#citySearch").val();
	var region3 = $("#countySearch").val();
	if(!region3||region3==0){
		region3 = "";
	}
	if(!region2||region2==0){
		region2 = "";
	}
	if(!region1||region1==0){
		region1 = "";
	} 

sendRequest({status:status,start:start,region1:region1,region2:region2,region3:region3},
			"/bm/classroom/queryClassrooms.do",
			function(json){
				var list = json.data.list;
				/* if(list.length == 0){
					showDialog("暂无符合条件的教室","toast","","");
					$("#queryBtn").removeClass("isLoading").html("查询");
					return;
				} */
				$("#data_content").empty();
				for(var i=0;i<list.length;i++){
						var item = list[i];
						var picUrl = "";
						var operate = "";
						if(item.status!=1){
							operate = "<a onclick=\"showTeacherDetail('"+item.id+"')\" >查看详情</a>";
						}
						var showCourse = "";
						$(item.classroomCourses).each(function(i,course){
							showCourse += courseId2Name(course.courseId)+",";
						});
						if(showCourse.lastIndexOf(',') == showCourse.length-1){
							showCourse = showCourse.substr(0,showCourse.length-1);
						}
						var showGrade = "";
						$(item.classroomGrades).each(function(i,grade){
							showGrade += courseId2Name(grade.gradeId)+",";
						});
						if(showGrade.lastIndexOf(',') == showGrade.length-1){
							showGrade = showGrade.substr(0,showGrade.length-1);
						}
						$("#data_content").append(
						["    <tr>           ",
						 "			        <td align=\"center\">"+undefinedHandler(item.teacherName)+"</td>",
						 "			        <td align=\"center\">"+undefinedHandler(item.name)+"</td>",
						 "			        <td align=\"center\">"+undefinedHandler(item.school)+"</td>",
						 "			        <td align=\"center\">"+undefinedHandler(showGrade)+"</td>",
						 "			        <td align=\"center\">"+undefinedHandler(showCourse)+"</td> ",
						 "			        <td align=\"center\">"+undefinedHandler(showStatus(item.status))+"</td>",
						// "			        <td align=\"center\">",
					//	 operate,
						// "			        </td>",
						 "			      </tr>"].join("")
						);
						
				}
				$("#queryBtn").removeClass("isLoading").html("查询");
				$("#navArea").html(showNav(json.data));
			},function(){
				showDialog("查询失败","toast","","");
				$("#queryBtn").removeClass("isLoading").html("查询");
			}
	);
}
function showStatus(status){
	if(status == 1){
		return "创建";
	}
	if(status == 2){
		return "普通";
	}
	if(status == 3){
		return "在线";
	}
	if(status == 4){
		return "删除";
	}
}

function showTeacherDetail(id){
	window.location=toServerPageUrl("/bm/teacher/getTeacherDetail.do?teacherId="+id);
}
</script>

</body>
</html>