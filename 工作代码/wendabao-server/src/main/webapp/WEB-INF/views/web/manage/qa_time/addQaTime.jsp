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
String split = Constants.SPLITOR;

%>
<html lang="zh-CN">

<head>
 <%@include file="/common.jsp" %>

<title>新建套餐</title>
</head>
<body>
    <div class="container" style="width:100%;">
            <nav class="navbar navbar-default navbar-fixed-top row" style="border:0" >
              <ol class="breadcrumb">
                      <li style="*float:left"><a href="#">销售项目</a></li>
                      <li style="*float:left"><a href="#">新建套餐</a></li>
              </ol>
            </nav>
    </div>
    <div class="panel panel-default container rightsidebar_box" >
    <form action="#" role="form" class="form-horizontal " style="padding-top: 70px;">	
                    <div class="form-group">
                        <label for="name" class="col-xs-2 control-label" >所属区域</label>
                        <div class="col-xs-2">
                            <select class="form-control zs-select-input" id="region1">
                            </select>
                        </div>
                        <div class="col-xs-2">
                            <select class="form-control zs-select-input" id="region2">
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                            <label class="col-xs-2 control-label">分钟数</label>
                            <div class="col-xs-2">
                                    <input type="text" class="form-control" id="time" />
                            </div>
                    </div>
                    <div class="form-group">
                            <label class="col-xs-2 control-label">销售价格</label>

                            <div class="col-md-2">
                                    <input type="text" class="form-control" id="price" />
                            </div>
                    </div>
                    <div class="form-group">
                            <label class="col-xs-2 control-label">状态</label>
                            <div class="col-xs-2">
                                <select class="form-control zs-select-input" id="status">
                                    <option value="1" selected>暂存</option>
                                    <option value="2">生效</option>
                                    <option value="3">停止</option>
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
           region1: $("#region1").val(),
           region2: $("#region2").val(),
           time: $("#time").val(),
           price: $("#price").val(),
           status: $("#status").val()
        };
        
        sendRequest(param, "/bm/qa_time/saveQaTime.do", function(json) {
            if (json.header.success === true) {
                showDialog("保存成功！", "info", {
                   onOk: function() {
                       setTimeout(function() {
                            window.location = toServerPageUrl("/bm/qa_time/qaTimeList.do");
                       }, 800);
                   } 
                });
            }
        });
    });
    
    $(function() {
       function initRegion() {
           
       }
       
       initRegion();
    });
});
</script>

</body>
</html>