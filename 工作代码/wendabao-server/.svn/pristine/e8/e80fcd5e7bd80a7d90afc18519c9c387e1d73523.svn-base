<!DOCTYPE html>
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

	String version = request.getParameter("version");
        String deviceType = request.getParameter("type");
%>
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en" class="no-js">
<!--<![endif]-->
<!-- BEGIN HEAD -->
<head>
<%@include file="/common.jsp" %>
<script src="<%=basePath%>js/region.js" type="text/javascript"></script>
<title>客户端版本详情</title>
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
<body>
<div class="container-fluid">
	<div class="row" style="border: 0; margin-top: -10px;">
		<div class="col-sm-12">
			<h3 class="zs-iframe-title">客户端版本详情</h3>
		</div>
	</div>
	<div class="container contentContainer" id="baseContainer"
		style="display: block;">
		<form class="form-horizontal" role="search">
			<div class="form-group">
				<label class="col-sm-2 col-md-2 control-label">客户端类型</label>
				<div class=" col-sm-10 col-md-10">
					<span type="text" class="form-group" id="devicetype"></span>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 col-md-2 control-label">版本号</label>
				<div class="col-sm-10 col-md-10">
					<span rows="5" class="form-group" id="version"></span>
				</div>
			</div>

			<div class="form-group ">
				<label class="col-sm-2 col-md-2 control-label">下载地址</label>

				<div class="col-sm-10 col-md-10 ">
					<span rows="5" class="form-group" id="downloadUrl"></span>
				</div>
			</div>
			<div class="form-group ">
				<label class="col-sm-2 col-md-2 control-label">是否强制更新</label>

				<div class="col-sm-10 col-md-10 ">
					<span rows="5" class="form-group" id="forceUpdate"></span>
				</div>
			</div>
                        <div class="form-group ">
				<label class="col-sm-2 col-md-2 control-label">发布日期</label>

				<div class="col-sm-10 col-md-10 ">
					<span rows="5" class="form-group" id="publishdate"></span>
				</div>
			</div>
		</form>

	</div>

	<div class="container contentContainer" id="recordContainer" style="display: none;"></div>

	<script type="text/javascript">
		$(function(){
			sendRequest({
                            version: '<%=version%>',
                            type: '<%=deviceType%>'
                        }, "/sys/sysInfo/detail.do", 
                        function(json){
                                var info = json.info;
                                $("#devicetype").text(function(devicetype) {
                                    switch (devicetype) {
                                        case '3': 
                                            return "安卓学生端";
                                        case '4':
                                            return "苹果学生端";
                                        case '5':
                                            return "安卓教师端";
                                        case '6':
                                            return "苹果教师端";
                                        case '7':
                                            return "安卓管理端";
                                    }
                                }(info.devicetype));
                  
                                $("#version").text(undefinedHandler(info.version));
                                $("#downloadUrl").text(undefinedHandler(info.downloadUrl));
                                $("#forceUpdate").text("F" === info.forceUpdate ? "否" : "是");
                                $("#publishdate").text(undefinedHandler(info.publishdate));
                        },function(json){

                        });
		});
		
	</script>
</div>
</body>
</html>