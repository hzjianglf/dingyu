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
	String split = Constants.SPLITOR;

	String productId = (String) request.getAttribute("productId");
	PmUser user = UserContextManager.getLoginUser();
	String userType = user.getUserType();
	String userId = user.getId();
%>
<!DOCTYPE html>
<html lang="zh-CN">

<head>
<%@include file="/common.jsp"%>
<script type="text/javascript"
	src="<%=basePath%>js/admin/regionSearch.js"></script>
<title>注册学生管理</title>
</head>
<body>
	<div class="container" style="width: 100%;">
		<div class="row" style="border:0;margin-top:-10px;">
		<div class="col-sm-12">
			<h3 class="zs-iframe-title">学生管理</h3>
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
			<label  style="padding-top:7px;">学生名称</label>
		</div>
		<div class="col-sm-4"  style=" padding-left:0px;">
			<input type="text" id="name"  class="form-control" style="float:left" >
		</div>
	</div>
	<div class="row" style="margin-top:10px;margin-left:5px;">
		<div class="col-sm-1"  style=" padding-left:0px;text-align:right;">
			<label  style=" padding-top:7px;">地区</label>
		</div>
		<div class="col-sm-3" style=" padding-left:0px;"><select class="form-control zs-select-input" id='provinceSearch' onchange='searchConditionCity()'></select></div>
        <div class="col-sm-3" style=" padding-left:0px;"><select class="form-control zs-select-input" id='citySearch' onchange='searchConditionCounty()'></select></div>
        <div class="col-sm-3" style=" padding-left:0px;"><select class="form-control zs-select-input" id='countySearch'></select></div>
		<div class="col-sm-2"  style=" padding-left:0px;text-align:right;">
			<button type="button" class="btn btn-default zs-btn-default " id="queryBtn" onclick="toPage(0)">查询</button>
		</div>
	</div>
	
	
	
<div style="margin-top:10px;">
	<div class="col-sm-12">
	 <table class="table table-hover">
   		<thead>
          <tr>
          	<th width="100">用户名</th>
			<th width="100">学生名称</th>
            <th width="100">年级</th>
            <th width="100" >学校</th>
            <th width="100" >地区</th>
            <th width="100" >剩余时长</th>
            <th width="147">操作</th>
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
</body>
<script type="text/javascript">
	//记住上次查询的地址
	var region1S;
	var region2S;
	var region3S ;
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
			region1S = PermanentCache.getItem("region1S");
			region2S = PermanentCache.getItem("region2S");
			region3S = PermanentCache.getItem("region3S");
			PermanentCache.removeItem("region1S");
			PermanentCache.removeItem("region2S");
			PermanentCache.removeItem("region3S");
			if(!region1S){
				region1S = 0;
			}
			if(!region2S){
				region2S = 0;
			}
			if(!region3S){
				region3S = 0;
			}
			setDefaultSelect(region1S,region2S,region3S);
			
			toPage(0);
			}
	});
	
	function toPage(start){
		if($("#queryBtn").hasClass("isLoading")){
			return;
		}
		$("#queryBtn").addClass("isLoading").html("loading...");
		if(!start){
			start = 0;
		}
		var name = $("#name").val();
		var phone = $("#phone").val();
		region1 = $("#provinceSearch").val();
		 region2 = $("#citySearch").val();
		 region3 = $("#countySearch").val();
		if(!region3||region3==0){
			region3 = "";
		}
		if(!region2||region2==0){
			region2 = "";
		}
		if(!region1||region1==0){
			region1 = "";
		}
		sendRequest(
				{
					name:name,
					mobile:phone,
					start:start,
					region1:region1,
					region2:region2,
					region3:region3
				},
				"/bm/student/queryStudents.do", 
				function(json){
					var list = json.data.list;
					if(list.length == 0){
						showDialog("暂无符合条件的学生信息","toast","","");
						$("#queryBtn").removeClass("isLoading").html("查询");
						return;
					}
					region1S = region1;
					region2S = region2;
					region3S = region3;
					$("#data_content").empty();
					for(var i=0;i<list.length;i++){
						var item = list[i];
						$("#data_content").append(
								 "    <tr>"+
								 "			        <td align=\"center\">"+undefinedHandler(item.user?item.user.loginName:"")+"</td>"+
							 	 "			        <td align=\"center\">"+undefinedHandler(item.name)+"</td>"+
								 "			        <td align=\"center\">"+undefinedHandler(item.grade)+"</td> "+
								 "			        <td align=\"center\">"+ undefinedHandler(item.school)+"</td>"+
								 "					<td align=\"center\">"+
								 	undefinedHandler(item.region1Txt)+undefinedHandler(item.region2Txt)+undefinedHandler(item.region3Txt)+
								 "					</td>"+
								 "			        <td align=\"center\">"+ second2Min(undefinedHandler(item.qaTime?item.qaTime:"0"))+"</td>"+
								 "			        <td align=\"center\">"+
								 "			       		<a onclick=\"showStudentDetail('"+item.id+"')\" >查看详情</a>"+
								 "			        </td>"+
								 "			      </tr>"	
						);
						$("#queryBtn").removeClass("isLoading").html("查询");
					}
					$("#navArea").html(showNav(json.data));
				}, function(json){
					showDialog("查询失败","toast","","");
					$("#queryBtn").removeClass("isLoading").html("查询");
				})
	}
	$("#queryBtn").bind("click",function(){
		toPage(0);
	});
	function showStudentDetail(id){
		PermanentCache.setItem("region1S", region1S);
		PermanentCache.setItem("region2S", region2S);
		PermanentCache.setItem("region3S", region3S);
		window.location=toServerPageUrl("/bm/student/getStudentDetail.do?id="+id);
	}
</script>
</html>