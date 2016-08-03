<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.Map"%>
<%@page import="com.handany.rbac.model.PmUser"%>
<%
PmUser user = (PmUser)session.getAttribute("user");
%>
    
<form action="" name="editUserForm" id="editUserForm" method="post"> 
	<div class="container-fluid">
		<h1>基础资料<%=user.getId() %></h1>
		<hr style="height:1px;border:none;border-top:1px solid #555555;" />
		
		<!-- <h2>基础信息</h2> -->
		<ul class="nav nav-tabs">
		  <li id="baseMsg" role="presentation" class="active"><a href="javascript:msgActive()">基础信息</a></li>
		  <li id="changePwd" role="presentation"><a href="javascript:pwdActive()">修改密码</a></li>
		  <li id="changeMobile" role="presentation"><a href="javascript:mobileActive()">更换手机号</a></li>
		</ul>
	<!-- 	<ul class="list-inline">
		  <li><h4><a href="#" class="active">基础信息</a></h4></li>
		  <li><h4><a href="#">修改密码</a></h4></li>
		  <li><h5><a href="#">更换手机号</a></h5></li>
		</ul> -->
	<!--  -->
	<div id="include">
		<div id="msg" class="container-fluid" style="padding-top:20px;">
	
			<div class="row">
				
				<div class="col-md-1">名称</div>
				<div class="col-md-4"><input type="text" class="form-control" name="name" id = "name" value="<%=user.getName() %>"/></div>
				<div class="col-md-4"></div>
			</div><br/>
			
			<div class="row">
				<div class="col-md-1 ">电话</div>
				<div class="col-md-4"><input type="text" class="form-control" name="mobile" id="mobile1" value="<%=user.getMobile() %>"/></div>
				<div class="col-md-4"></div>
			</div><br/>
			<div class="row">
				<div class="col-md-1 ">性别</div>
				<div class="radio col-md-4">
				  <label>
				  <%
				  	if(user.getGender().equals("1")){
				  		%>
				  		<input type="radio" name="gender" id="blankRadio1" value="1" checked>男  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					    <input type="radio" name="gender" id="blankRadio1" value="2">女
					    
					    <%
				  	}else{
				  		%>
				  		<input type="radio" name="gender" id="blankRadio1" value="1">男  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					    <input type="radio" name="gender" id="blankRadio1" value="2" checked>女
				  		<%
				  	}
				  
				  %>
				    
				  </label>
				</div>
				<div class="col-md-4"></div>
			 </div>
			 <button type="button" class="btn btn-warning" onclick="baseMsg()">保存</button>
		</div>
	</div>
	<!-- 修改密码  -->
	<div id="pwd" class="container-fluid" style="padding-top:20px;">

		<div class="row">
			
			<div class="col-md-1 ">当前密码</div>
			<div class="col-md-4"><input type="text" class="form-control" name="loginName" value="济南花冠"/></div>
			<div class="col-md-4"></div>
		</div><br/>
		
		<div class="row">
			<div class="col-md-1 ">新密码</div>
			<div class="col-md-4"><input type="text" class="form-control" name="loginName" value="18766177609"/></div>
			<div class="col-md-4"></div>
		</div><br/>
		<div class="row">
			<div class="col-md-1 ">确认密码</div>
			<div class="radio col-md-4">
			  <input type="text" class="form-control" name="loginName" value="18766177609"/>
			</div>
			<div class="col-md-4"></div>
		 </div>
		 <button type="button" class="btn btn-warning">保存</button>
	</div>
	<!-- 更换手机号  -->
	<div id="mobile" class="container-fluid" style="padding-top:20px;">

		<div class="row">
			
			<div class="col-md-1 ">旧手机号</div>
			<div class="col-md-4"><input type="text" class="form-control" name="loginName" value="济南花冠"/></div>
			<div class="col-md-4"></div>
		</div><br/>
		
		<div class="row">
			<div class="col-md-1 ">验证码</div>
			<div class="col-md-4"><input type="text" class="form-control" name="loginName" value="18766177609"/></div>
			<div class="col-md-4"></div>
		</div><br/>
		<div class="row">
			<div class="col-md-1 ">新手机号</div>
			<div class="radio col-md-4">
			  <input type="text" class="form-control" name="loginName" value="18766177609"/>
			</div>
			<div class="col-md-4"></div>
		 </div>
		 <button type="button" class="btn btn-warning">保存</button>
	</div>
</div>
</form>
<script>
/* 用户中心 */
function msgActive(){
	$("#baseMsg").addClass("active");
	$("#changePwd").removeClass("active");
	$("#changeMobile").removeClass("active");
	$("#msg").show();
	$("#pwd").hide();
	$("#mobile").hide();
}
function pwdActive(){
	$("#changePwd").addClass("active");
	$("#baseMsg").removeClass("active");
	$("#changeMobile").removeClass("active");
	$("#baseMsg").removeClass("active");
	$("#msg").hide();     
	$("#pwd").show();
	$("#mobile").hide();
}
function mobileActive(){
	$("#baseMsg").removeClass("active");
	$("#changePwd").removeClass("active");
	$("#changeMobile").addClass("active");
	$("#msg").hide();
	$("#pwd").hide();
	$("#mobile").show();
}
	$("#pwd").hide();
	$("#mobile").hide();
	
	function baseMsg(){
		
		var gender = $('input[name="gender"]:checked').val();
		var name = document.getElementById("name").value;
		var mobile = document.getElementById("mobile1").value;
		
		$.ajax({
			url:"../pm/register.do?method=saveBaseMsg",
			data:{
				"gender":gender,
				"name":name,
				"mobile":mobile,
			},
			type:"post",
			dataType:"json",
			success:function(data){
				if(true == data.header.success){
					alert("修改成功！");
				}
			}
		});
		
		/* 
		$.ajax({
			url:"../../pm/user/login.do?data=" + new Date(),
			data:{
				"password":password,
				"loginName":loginName,
				},
			type:"post",
			dataType:"json",
			success:function(data){   		
			
			if(true == data.header.success){
				window.location.href="../index.jsp";
			}
				
				
			}
	   }); */
			
}	
	
	
	
	
	
	
	
	
	
	
	
	
</script>