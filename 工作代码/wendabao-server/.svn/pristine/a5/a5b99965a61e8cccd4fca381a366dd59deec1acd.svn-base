<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@page import="java.lang.String"%>
<%@page import="com.github.pagehelper.PageInfo"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%@page import="com.handany.base.common.Constants"%>
<%
String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
+ request.getContextPath() + "/";
String picPath = Constants.IMAGE_SERVER;
String split =Constants.SPLITOR;

%>
<html lang="zh-CN">

<head>
 <%@include file="/common.jsp" %>

<title>代理商管理</title>
</head>
<body>
<div class="container" style="width:100%;">
	<nav class="navbar navbar-default navbar-fixed-top row" style="border:0" >
	  <ol class="breadcrumb">
		  <li style="*float:left"><a href="#">系统管理</a></li>
		  <li style="*float:left"><a href="javascript:goProductManage()">客户端版本管理</a></li>
		  <li style="*float:left"><a href="#">新建版本</a></li>
	  </ol>
	</nav>
</div>
	<div class="panel panel-default container rightsidebar_box" >
	<form action="#" role="form" class="form-horizontal " style="padding-top: 70px;">	
			<div class="form-group">
			   <label for="name" class="col-md-2 control-label" >客户端类型</label>
				<div class="col-md-2">
                                    <select class="form-control zs-select-input" id="devicetype">
                                        <option value="3">安卓学生端</option>
                                        <option value="4">苹果学生端</option>
                                        <option value="5">安卓教师端</option>
                                        <option value="6">苹果教师端</option>
                                        <option value="7">安卓管理端</option>
                                    </select>
				</div>
			</div>
			<div class="form-group">
				<label class="col-md-2 control-label">版本号</label>
				<div class="col-md-2">
					<input type="text" class="form-control" id="version" />
				</div>
			</div>
			<div class="form-group">
				<label class="col-md-2 control-label">下载地址</label>

				<div class="col-md-6">
					<input type="text" class="form-control" id="downloadUrl" value="http://download.handany.cn/" />
				</div>
			</div>
			<div class="form-group">
				<label class="col-md-2 control-label">是否强制更新</label>

				<div class="col-md-2">
                                    <select class="form-control zs-select-input" id="forceUpdate">
                                        <option value="F" selected>否</option>
                                        <option value="T">是</option>
                                    </select>
				</div>
			</div>
            
                        <div class="form-group">
                            <div class="col-md-3 col-md-offset-3">
                                <button type="button" class="btn btn-danger btn-block" id="submitBtn">提交</button>
                            </div>
                        </div>
            </form>
</div>
<script src="./js/modalDialog.js"></script>
<script>
$(function() {
    $("#submitBtn").click(function() {
        var param = {
           devicetype: $("#devicetype").val(),
           version: $("#version").val(),
           downloadUrl: $("#downloadUrl").val(),
           forceUpdate: $("#forceUpdate").val()
        };
        
        sendRequest(param, "/sys/sysInfo/saveSystemInfo.do", function(json) {
            if (json.header.success === true) {
                showDialog("保存成功！", "info", {
                   onOk: function() {
                       setTimeout(function() {
                            window.location = toServerPageUrl("/sys/sysInfo/sysInfoList.do");
                       }, 800);
                   } 
                });
            }
        });
    });
});
</script>

</body>
</html>