<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
<script type="text/javascript" src="../manager/js/question.js"></script>

 <script type="text/javascript">

document.oncontextmenu=function(){
var selection="";//定义鼠标拖选值
if(document.selection){//***** IE
selection=document.selection.createRange().text;
}else{//***** 其他浏览器
selection=document.getSelection();
}
//if来判断拖选值、被单击的区域是不是表单域值
if(selection==""&&(event.srcElement.value==undefined||event.srcElement.value==0)&&event.srcElement.value!=0)return false;
}

function onRowContextMenu(e,rowIndex,rowData){
	/*e.preventDafault();*/
	e.preventDefault();
}
  
</script>
<style type="text/css">
#serchTest,#edit,#add,#ff,#addQuestion,#updatequestion,#addquestion,#toolbar {
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
.ww{border:1px solid #26A0DA}
</style>

</head>
<body style="background-color:white;padding:0px;">

	<table style="float:left;" id="tt">
	</table>

	<div id="toolbar" class="test">

		<table width="100%">
			<tr>
				<td><table align="right">
						<tr>
							<td><a href="javascript:AddQuestion()"><div
										style="padding-top: 5px;"></div>&nbsp;<span class="icon-add">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;添加</span>&nbsp;&nbsp;</a>
							</td>
							<td><a href="javascript:UpdateQuestion()"><div
										style="padding-top: 5px;"></div>&nbsp;<span class="icon-edit">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;修改</span>&nbsp;&nbsp;</a>
							</td>

							<td><a href="javascript:DeleteQuestion()"><div
										style="padding-top: 5px;"></div>&nbsp;<span
									class="icon-remove">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;删除</span>&nbsp;&nbsp;</a>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</div>

	<form id="addQuestion" 
		target="hidden_frame" enctype="multipart/form-data" method="post">
		<div class="xx21">
		<div >
			<table
				style="margin-top: 5px; margin-bottom: 5px; margin-left: 30px;" cellpadding="0" cellspacing="0">
				<tr style="line-height: 30px;">
					<td><span>简易程度:</span>
					
					<select  
						name="questionType" id="questionType" style=" width: 127px;">
					</select>
					
					</td>
					<td><font color="red">*必须</font></td>
				</tr>
				<tr  style="line-height: 30px;">
					<td><span>题目类型:</span>
						<select id="type" class="ww" name="type" style="width: 127px;height:20px;" onchange="changeDiv()">
							<option value="0">语音类型</option>
							<option value="1">文字类型</option>
							<option value="2">图文</option>
						</select>
					</td>
				</tr>
				<tr  style="line-height: 30px;" id="titleTr" >
					<td width="12%" colspan="2">题目标题:&nbsp;<input id="qustionTitle" class="ww"  name="qustionTitle"
						type="text" style="width: 122px;"/>
					</td>
				</tr>
				<tr  style="line-height: 30px;" >
					<td>题目内容:</td>
					<td >
					<div id="fileDivs" style="position: relative;left: -210px;[;left: -210px;];*left:-210px;left:-210px\9\0;">
					<input id="qustionContent" class="ww" name="qustionContent" type="file" style="width: 126px;[;width: 120px;];" onchange="getFileName()"/>
					<input type="hidden"   name="fileName" id="fileName"> </div>
				    <div id="textareaDiv" style="display: none;position: relative;left: -148px;">
				    <textarea style="height:40px; margin-left:20px;" name="content" id="content" cols="50"></textarea></div>
					</td>
				</tr>
				<tr  style="line-height: 30px;" >
					<td >&nbsp;答&nbsp;案&nbsp;A：<input id="answera" name="answera" class="ww" type="text" /></td>
				
					<td >&nbsp;答&nbsp;案&nbsp;B：<input id="answerb" name="answerb" class="ww" type="text" /></td>
				</tr>
				<tr  style="line-height: 30px;" >
					<td >&nbsp;答&nbsp;案&nbsp;C：<input id="answerc" name="answerc" class="ww" type="text" /></td>
				
					<td >&nbsp;答&nbsp;案&nbsp;D：<input id="answerd" name="answerd" class="ww" type="text" /></td>
				</tr>
				<tr  style="line-height: 30px;"	>
					<td colspan="2">&nbsp;正确答案：
						<input type="radio" value="a" name="rightAnswer" />A
						<input type="radio" value="b" name="rightAnswer" />B
						<input type="radio" value="c" name="rightAnswer" />C
						<input type="radio" value="d" name="rightAnswer" />D
						<input type="hidden" id="questionId" name="questionId">
					</td>
				</tr>
			</table>
			</div>
		</div>
	</form>
	<div id="updatequestion" class="easyui-window" title="修改"
 style="top:80px;*top:80px;left:250px;*left:400px;overflow:hidden;background:#E2EDFF;width:600px;[;height:313px;];*height:315px;_heigh:255px;height:310px\9\0;"
		iconCls="icon-edit" closed="true" maximizable="false"
		minimizable="false" collapsible="false">
		<div id="update"></div>
		<div>&nbsp;</div>
		<div align="center">
			<a class="easyui-linkbutton" iconCls="icon-ok"
				href="javascript:void(0)"
				style="position:relative;top:-15px;*top:-22px;" onClick="update()">修改</a>
			<a class="easyui-linkbutton" iconCls="icon-cancel"
				href="javascript:void(0)"
				style="position:relative;top:-15px;*top:-22px;" onClick="close1()">取消</a>
		</div>
	</div>
	<div id="addquestion" class="easyui-window" title="添加"
		style="top:80px;*top:80px;left:250px;*left:400px;overflow:hidden;background:#E2EDFF;width:600px;[;height:313px;];*height:315px;height:310px\9\0;"
		iconCls="icon-add" data-options="modal:true,closed:true"
		maximizable="false" closed="true" maximizable="false"
		minimizable="false" collapsible="false">
		<div id="eeXL"></div>
		<div>&nbsp;</div>
		<div align="center">
			<a class="easyui-linkbutton" iconCls="icon-ok"
				href="javascript:void(0)"
				style="position:relative;top:-15px; *top:-22px;" onClick="add()">添加</a>
			<a class="easyui-linkbutton" iconCls="icon-cancel"
				href="javascript:void(0)"
				style="position:relative;top:-15px;*top:-22px;" onClick="close1()">取消</a>
		</div>
	</div>
</body>
</html>