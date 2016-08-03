<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@page import="com.handany.bm.model.*"%>
<%@page import="java.lang.String"%>
<%@page import="com.github.pagehelper.PageInfo"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en" class="no-js">
    <!--<![endif]-->
    <!-- BEGIN HEAD -->
    <head>
        <meta charset="utf-8"/>

        <!-- 为了让 IE 浏览器运行最新的渲染模式下，建议将此 <meta> 标签加入到你的页面中：-->
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <!--将下面的 <meta> 标签加入到页面中，可以让部分国产浏览器默认采用高速模式渲染页面：-->
        <meta name="renderer" content="webkit">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->

        <meta content="" name="description"/>
        <meta content="" name="author"/>

        <title></title>
        <!-- BEGIN GLOBAL MANDATORY STYLES -->
        <%@include file="/common.jsp" %>

        <link rel="stylesheet" href="<%=basePath%>/css/custom.css">
        <style>
            .order-status{
                border-radius: 4px;
                color: white;
                padding: 5px;
                font-size: 14px;
            }
            .order-status:hover{
                color: white;
            }
            .order-status-1{
                background-color: #d58512;
                /*border: 1px solid #777777;*/
            }
            .order-status-2{
                background-color: #c12e2a;
                /*border: 1px solid #777777;*/
            }
            .order-status-3{
                background-color: #1c9439;
                /*border: 1px solid #777777;*/
            }
            .order-status-4{
                background-color: #66afe9;
                /*border: 1px solid #777777;*/
            }
        </style>
    </head>
    <body>


        <div class="container" style="width:100%;">

            <div class="row" style="border:0;margin-top:-10px;">
                <div class="col-sm-12">
                    <h3 class="zs-iframe-title">客户端版本列表</h3>
                </div>
            </div>

            <div class="row" style="margin-top:10px;">

                <div class="col-sm-2"  style="padding-left:0px;text-align:right;">
                    <label for="deviceType"  style="text-align:right; padding-top:7px;">客户端类型</label>
                </div>
                <div class=" col-sm-3"  style="">
                    <select name="deviceType" class="form-control">
                        <option value="">全部</option>
                        <option value="3">安卓学生端</option>
                        <option value="4">苹果学生端</option>
                        <option value="5">安卓教师端</option>
                        <option value="6">苹果教师端</option>
                        <option value="7">安卓管理端</option>
                    </select>
                </div>
                <div class=" col-sm-2" align="left">
                    <button type="button" class="btn btn-default zs-btn-default" id="queryBtn" onclick="toPage(0)">查询</button>
                </div>
                <div class=" col-sm-5" align="right">
                    <button type="button" class="btn btn-default zs-btn-default" id="newBtn">新建版本</button>
                </div>
            </div>
        </div>

        <div style="margin-top:10px;">
            <div class="col-sm-12">
                <table class="table table-hover">
                    <thead>
                        <tr>
                            <th width="110">客户端类型</th>
                            <th width="110">版本号</th>
                            <th width="110">发布日期</th>  
                            <th width="110">是否强制更新</th>
                            <th width="110">操作</th>      
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
        <script>
            function toPage(start) {
                if ($("#queryBtn").hasClass("load")) {
                    return;
                }
                $("#queryBtn").addClass("load").html("loading...");

                if (start == undefined || start == null) {
                    start = 0;
                }
                sendRequest(
                        {
                           deviceType: $("#deviceType").val(), 
                           start: start}, "/sys/sysInfo/query.do", function (json) {
                    var list = json.data.list;
                    $("#data_content").empty();
                    var printBtnHtml = "";
                    for (var i = 0; i < list.length; i++) {
                        var item = list[i];
                        $("#data_content").append(
                                [   "   <tr>           ",
                                    "       <td align=\"center\">",
                                    toDeviceType(item.devicetype),
                                    "       </td>",
                                    "       <td align=\"center\">" + item.version + "</td>",
                                    "       <td align=\"center\">" + item.publishdate + "</td> ",
                                    "       <td align=\"center\">" + ("F" === item.forceUpdate ? "否" : "是") + "</td>",
                                    "       <td align=\"center\">",
                                    "           <a onclick=\"showDetail(\'" + item.version + "\', '" + item.devicetype + "')\">查看详情</a>",
                                    printBtnHtml,
                                    "       </td>",
                                    "</tr>"].join("")
                                );
                    }
                    $("#navArea").html(showNav(json.data));
                    $("#queryBtn").removeClass("load").html("查询");
                }, function () {
                    showDialog("查询失败", "toast", "", "");
                    $("#queryBtn").removeClass("load").html("查询");
                });
            }

            function toDeviceType(deviceType) {
                var txt = "";
                if (deviceType === "3") {
                    txt = "安卓学生端";
                } else if (deviceType === "4") {
                    txt = "苹果学生端";
                } else if (deviceType === "5") {
                    txt = "安卓教师端";
                } else if (deviceType === "6") {
                    txt = "苹果教师端";
                } else if (deviceType === "7") {
                    txt = "安卓管理端";
                }
                return txt;
            }
            
            function showDetail(version, deviceType) {
                window.location = toServerPageUrl("/sys/sysInfo/sysInfoDetail.do?version=" + version + "&type=" + deviceType);
            }
            
            $(function() {
               toPage(0);
               
               $("#newBtn").click(function() {
                   window.location = toServerPageUrl('/sys/sysInfo/addSystemInfo.do');
               });
            });
        </script>       
    </body>
