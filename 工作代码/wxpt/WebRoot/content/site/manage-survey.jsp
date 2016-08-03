<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="testCookie.jsp" flush="true" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="../manager/css/default.css">
<link rel="stylesheet" type="text/css" href="../manager/themes/icon.css">
<link rel="stylesheet" type="text/css" href="../manager/css/demo.css">
<link id="easyuiTheme" rel="stylesheet"
	href="../manager/themes/default/easyui.css" type="text/css">
<script type="text/javascript" src="../manager/js/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="../manager/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="../manager/js/jquery.cookie.js"></script>
<script type="text/javascript" src="../manager/js/hd.js"></script>
<script type="text/javascript" src="../manager/js/jquery.form.js"></script>
<script type="text/javascript" src="../manager/js/survey.js"></script>
<script type="text/javascript" src="../manager/js/ajaxfileupload.js"></script>
<script type="text/javascript" language="javascript"
	src="../manager/js/WebCalendar.js"></script>



<style type="text/css">
#serchTest,#edit,#add,#ff,#addUser,#adduser,#add,#toolbar {
	visibility: hidden;
}

.serchTest {
	_margin-top: -20px;
	_margin-bottom: -18px;
}

*+html #serchTest {
	*margin-top: -20px;
	*margin-bottom: -18px;
}

.test a {
	vertical-align: center;
	display: block;
	float: left;
	height: 20px;
	padding-bottom: 2px;
	border: 1px solid #EFEFEF;
}

.test a:link {
	text-decoration: none;
	color: #000;
}

.test a:active {
	text-decoration: none;
	color: #000
}

.test a:visited {
	text-decoration: none;
	color: #000
}

.test a:hover {
	text-decoration: none;
	color: #000;
	background: #eaf2ff;
	border: 1px solid #dddddd;
}

.xx21 {
	margin: 10px 10px 10px 10px;
	border: 1px solid #91ABD1;
	border-radius: 8px;
}

.ww {
	border: 1px solid #26A0DA
}
</style>

</head>
<body style="background-color:white;padding:0px;">

	<table style="float:left;" id="tt">
	</table>

	<div id="toolbar" class="test">

		<table width="100%">
			<tr>
				<td></td>
				<td><table align="right">
						<tr>
							<td><a href="javascript:queryQuestionnaire1()"><div
										style="padding-top: 5px;"></div>&nbsp;<span class="icon-edit">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;查看问卷</span>&nbsp;&nbsp;</a>
							</td>
							<!-- <td><a href="javascript:UpdateQuestionnaire()"><div
										style="padding-top: 5px;"></div>&nbsp;<span class="icon-add">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;修改问卷</span>&nbsp;&nbsp;</a>
							</td> -->
							<td><a href="javascript:DeleteSurvey()"><div
										style="padding-top: 5px;"></div>&nbsp;<span
									class="icon-remove">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;删除用户</span>&nbsp;&nbsp;</a>
							</td> 


						</tr>
					</table>
				</td>
			</tr>
		</table>
	</div>
	<form id="addUser" target="hidden_frame" enctype="multipart/form-data"
		method="post">

		<div class="xx21">
			<div
				style="margin-top: 5px; margin-bottom: 5px; margin-left: 10px;*margin-top:10px; *margin-bottom: 10px; *margin-left: 15px;">
				<table>
					<tr style="line-height: 20px;">
						<td><span>用户名:</span></td>
						<td><input type="text" name="surveyUserName" class="ww"
							id="surveyUserName" />
						</td>
						<td><span>粉丝名:</span></td>
						<td><input type="text" name="surveyUser" class="ww"
							id="surveyUser" />
						</td>
						
						
						
					</tr>
                    	<tr style="line-height: 20px;">
                    	<td><span>性别:</span></td>
						<td><input type="text" name="surveyUerSex" class="ww"
							id="surveyUerSex" />
						</td>
							<td><span>年龄:</span></td>
						<td><input type="text"   name="surveyUserAge" class="ww"
							id="surveyUserAge" />
						</td>
										
					</tr>
                    		
                      	<tr style="line-height: 20px;">
						<td><span>QQ:</span></td>
						<td><input type="text" name="surveyUserQq" class="ww"
							id="surveyUserQq" />
						</td>
						<td><span>时间:</span></td>
						<td><input type="text" name="surveyTime" class="ww"
							id="surveyTime" />
						</td>
					</tr>
					<tr style="line-height: 20px;">
						<td><span>教育:</span></td>
						<td><input type="text" name="surveyUserEdu" class="ww"
							id="surveyUserEdu" />
						</td>
						<td><span>职业:</span></td>
						<td><input type="text" name="surveyUserWork" class="ww"
							id="surveyUserWork" />
						</td>
					</tr>
					 
					   	<tr style="line-height: 20px;">
					   <td><span>手机号:</span></td>
						<td><input type="text" name="surveyUserPhone" class="ww"
							id="surveyUserPhone" />
						</td>	
						<td><span>邮箱:</span></td>
						<td><input type="text" name="surveyUserEmail" class="ww"
							id="surveyUserEmail" />
						</td>
						
					</tr>
					<input type="hidden" id="surveyId" name="surveyId">
				</table>
                </div>
             </div>
                	
				<div class="xx21"  id="dtr" style="display: none;">
					
				</div>
			
			
		
	</form>

	<div id="updateuser" class="easyui-window" title="查看答卷"
		style=" top:20px;left:380px;background:#E2EDFF;width:430px;height:500px;"
		iconCls="icon-edit" closed="true" maximizable="false"
		minimizable="false" collapsible="false">
		<div id="update"></div>
		<div>&nbsp;</div>
	
	</div>



	<div id="add" class="easyui-window" title="等级设置"
		style=" top:20px;left:380px;overflow:hidden;background:#E2EDFF;width:275px;height:415px;[;height:385px;];*height:388px;height:370px\9\0;"
		iconCls="icon-edit" closed="true" maximizable="false"
		minimizable="false" collapsible="false">
		<div id="add1"></div>
		<div>&nbsp;</div>
		<div align="center">
			<a class="easyui-linkbutton" iconCls="icon-ok"
				href="javascript:void(0)"
				style="position:relative;top:-15px;*top:-22px;" onClick="add()">添加</a>
			<a class="easyui-linkbutton" iconCls="icon-cancel"
				href="javascript:void(0)"
				style="position:relative;top:-15px;*top:-22px;" onClick="close1();">取消</a>
		</div>
	</div>

</body>
</html>