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

String productId = (String)request.getAttribute("productId");

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
<title>注册教师管理</title>
</head>
<body>
<div class="container" style="width:100%;">
	<div class="row" style="border:0;margin-top:-10px;">
		<div class="col-sm-12">
			<h3 class="zs-iframe-title">教师管理</h3>
		</div>
	</div>
	<div class="row" style="margin-top:10px;margin-left:5px;">
		<div class="col-sm-1"  style="padding-left:0px;text-align:right;">
			<label style=" padding-top:7px;">手机号</label>
		</div>
		<div class="col-sm-4"  style=" padding-left:0px;">
			<input type="text" id="phone" class="form-control" style="float:left" >
		</div>
		<div class="col-sm-2"  style="padding-left:0px;text-align:right;">
			<label  style="padding-top:7px;">教师名称</label>
		</div>
		<div class="col-sm-4"  style=" padding-left:0px;">
			<input type="text" id="name"  class="form-control" style="float:left" >
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
		<div class="col-sm-1"  style=" padding-left:0px;text-align:right;">
			<label  style=" padding-top:7px;">状态</label>
		</div>
		<div class="col-sm-2" style=" padding-left:0px;">
			<select class="form-control zs-select-input"  id='status' >
				<option value='' >请选择教师状态</option>
				<option value='1'  >新注册</option>
				<option value='2' >待提交资料</option>
				<option value='3' selected = "selected">审批中</option>
				<option value='4' >审批通过</option>
				<option value='5' >审批未通过</option>
				<option value='6' >正常使用</option>
			</select>
		</div>
		<div class="col-sm-2"  style=" padding-left:0px;text-align:right;">
			<button type="button" class="btn btn-default zs-btn-default " id="queryBtn" >查询</button>
		</div>
	</div>
<div style="margin-top:10px;">
	<div class="col-sm-12">
	 <table class="table table-hover">
   		<thead>
          <tr>
          	<th width="100">用户名</th>
			<th width="100">教师名称</th>
            <th width="100" >学校</th>
            <th width="100" >地区</th>
            <th width="100" >状态</th>
            <th width="100">操作</th>
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
			region1T = PermanentCache.getItem("region1T");
			region2T = PermanentCache.getItem("region2T");
			region3T = PermanentCache.getItem("region3T");
			PermanentCache.removeItem("region1T");
			PermanentCache.removeItem("region2T");
			PermanentCache.removeItem("region3T");
			if(!region1T){
				region1T = 0;
			}
			if(!region2T){
				region2T = 0;
			}
			if(!region3T){
				region3T = 0;
			}
			setDefaultSelect(region1T,region2T,region3T);
			toPage(0);
		}
		
		
	})
$("#queryBtn").bind("click",function(){
	toPage(0);
});

function toPage(start){
	if($("#queryBtn").hasClass("isLoading")){
			return;
		}
	$("#queryBtn").addClass("isLoading").html("loading...");
	if(start == undefined || start == null){
			start = 0;
		}
	var status=$("#status").val();
	var name = $("#name").val();
	var phone = $("#phone").val();
	
	if(userType == "4"){
		 region1 = $("#provinceSearch").val();
		 region2 = $("#citySearch").val();
		 region3 = $("#countySearch").val();	
	}
	
	if(!region3||region3==0){
		region3 = "";
	}
	if(!region2||region2==0){
		region2 = "";
	}
	if(!region1||region1==0){
		region1 = "";
	}

sendRequest({name:name,mobile:phone,status:status,start:start,
				region1:region1,region2:region2,region3:region3},
			"/bm/teacher/queryTeachers.do",
			function(json){
				var list = json.data.list;
				if(list.length == 0){
					showDialog("暂无符合条件的教师","toast","","");
					$("#queryBtn").removeClass("isLoading").html("查询");
					return;
				}
				region1T = region1;
				region2T = region2;
				region3T = region3;
				$("#data_content").empty();
				for(var i=0;i<list.length;i++){
						var item = list[i];
						var picUrl = "";
						var operate = "";
						if(item.status!=1 &&item.status !=2){
							operate = "<a onclick=\"showTeacherDetail('"+item.id+"')\" >查看详情</a>";
						}
						$("#data_content").append(
						["    <tr>           ",
						 "			        <td align=\"center\">"+undefinedHandler(item.user?item.user.mobile:"暂无")+"</td>",
						 "			        <td align=\"center\">"+undefinedHandler(item.name )+"</td>",
						 "			        <td align=\"center\">"+undefinedHandler(item.school)+"</td>",
						 "			        <td align=\"center\">"+undefinedHandler(item.region1Txt)+undefinedHandler(item.region2Txt)+undefinedHandler(item.region3Txt)+"</td> ",
						 "			        <td align=\"center\">"+showStatus(item.status)+"</td>",
						 "			        <td align=\"center\">",
						 operate,
						 "			        </td>",
						 "			      </tr>"].join("")
						);
						$("#queryBtn").removeClass("isLoading").html("查询");
				}
				$("#navArea").html(showNav(json.data));
			},function(){
				showDialog("查询失败","toast","","");
				$("#queryBtn").removeClass("isLoading").html("查询");
			}
	);
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
function showTeacherDetail(id){

	PermanentCache.setItem("region1T", region1T);
	PermanentCache.setItem("region2T", region2T);
	PermanentCache.setItem("region3T", region3T);
	window.location=toServerPageUrl("/bm/teacher/getTeacherDetail.do?teacherId="+id);
}
</script>

</body>
</html>