<%@ page language="java" contentType="text/html; charset=utf-8"
pageEncoding="utf-8"%>
<%@page import="com.handany.bm.model.*"%>
<%@page import="java.lang.String"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>

<%
String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
+ request.getContextPath() + "/";

List
<BmDelieverProvider> list = (List
    <BmDelieverProvider>)request.getAttribute("data");
        String tokenId = (String)request.getAttribute("tokenId");

        %>
        <!DOCTYPE html>
        <html lang="zh-CN">

        <head>
            <%@include file="/common.jsp" %>
            <script src="<%=basePath%>js/shop/delieverProviderManage.js"></script>
            <link rel="stylesheet" href="<%=basePath %>/css/mall.css">
            <title>采供网</title>

        </head>
        <body>

        <div class="container" style="width:100%;">
            <div class="row" style="border:0;margin-top:-10px;">
                <div class="col-sm-12">
                    <h3 class="zs-iframe-title">物流费用维护</h3>
                </div>
            </div>
            <!--<ul class="nav nav-tabs">-->
                <!--<li role="presentation" class="active" id="tab-base"><a-->
                        <!--href="javascript:DelieverProviderDo.showContainer('tab-base','costContainer')">物流费用维护</a></li>-->
                <!--<li role="presentation" id="tab-img"><a-->
                        <!--href="javascript:DelieverProviderDo.showContainer('tab-img','companyContainer')">物流公司维护</a></li>-->
            <!--</ul>-->

            <div class="container contentContainer" id="costContainer" style="width:100%;">
                <div class="row" style="margin-top:20px;">
                    <div class="col-sm-2" style="text-align: right">
                        <label style="margin-top: 5px;">物流公司名称</label>
                    </div>
                    <div class="col-sm-4">
                        <select class="form-control form-group-lg" id="add_companySelect">
                            <option value="">== 请选择 ==</option>
                        </select>
                    </div>
                    <div class="col-sm-2">
                        <button class="btn btn-default zs-btn-default" id="showDeliverCastInfo">查看物流费用</button>
                    </div>
                </div>
                <div class=" row" style="margin-top:20px;">
                    <div class="form-group ">
                        <div class="col-md-12 type_sum"></div>
                    </div>
                    <div class="form-group">
                        <div class="col-md-12">
                            <button type="button" class="btn btn-default type_add zs-btn-green" style="float: right">添加</button>
                        </div>
                    </div>
                </div>
            </div>

            <!--<div class="container contentContainer" id="companyContainer" style="width:100%;">-->
                <!--<div class="row" style="margin-top:10px;">-->
                    <!--<div class="col-sm-12">-->
                        <!--<button class="btn btn-default zs-table-top-btn zs-btn-green" onclick="addCompany()" style="float:right ">新增</button>-->
                    <!--</div>-->
                <!--</div>-->
                <!--<div class=" row" style="margin-top:10px;">-->
                    <!--<div class="col-sm-12">-->
                        <!--<table class="table table-hover" id="deliversTable">-->
                            <!--<thead>-->
                            <!--<tr>-->
                                <!--<th width="110">物流公司名称</th>-->
                                <!--<th width="110">联系人</th>-->
                                <!--<th width="110">电话</th>-->
                                <!--<th width="110">操作</th>-->
                            <!--</tr>-->
                            <!--</thead>-->
                            <!--<tbody></tbody>-->
                        <!--</table>-->
                    <!--</div>-->

                <!--</div>-->
            <!--</div>-->

        </div>

        <!-- 编辑物流商 -->
        <!--<div class="modal fade" id="myModal_update" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">-->
            <!--<div class="modal-dialog" role="document">-->
                <!--<div class="modal-content">-->
                    <!--<div class="modal-header">-->
                        <!--<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>-->
                        <!--<h4 class="modal-title">物流公司编辑</h4>-->
                    <!--</div>-->
                    <!--<div class="modal-body">-->
                        <!--<form id="update" class="form-horizontal" method="post">-->
                            <!--<div class="form-group">-->
                                <!--<label for="update_companyName" class="col-sm-3 control-label">物流公司名称</label>-->

                                <!--<div class="col-sm-5">-->
                                    <!--<input type="text" class="form-control" id="update_companyName"/>-->
                                <!--</div>-->
                            <!--</div>-->
                            <!--<div class="form-group">-->
                                <!--<label for="update_contactorName" class="col-sm-3 control-label">联系人</label>-->

                                <!--<div class="col-sm-5">-->
                                    <!--<input type="text" class="form-control" id="update_contactorName"/>-->
                                <!--</div>-->
                            <!--</div>-->
                            <!--<div class="form-group">-->
                                <!--<label for="update_telephone" class="col-sm-3 control-label">电话</label>-->

                                <!--<div class="col-sm-5">-->
                                    <!--<input type="text" class="form-control" id="update_telephone">-->
                                <!--</div>-->
                                <!--<div id="updateError" class="col-sm-3"></div>-->
                            <!--</div>-->
                            <!--<input type="hidden" id="update_provider_id">-->
                        <!--</form>-->
                    <!--</div>-->
                    <!--<div class="modal-footer">-->
                        <!--<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>-->
                        <!--<button type="button" class="btn btn-primary" onclick="updateDeliever()">保存</button>-->
                    <!--</div>-->

                <!--</div>-->
            <!--</div>-->
        <!--</div>-->

        <!-- 添加物流商 -->
        <!--<div class="modal fade" id="myModal_add" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">-->
            <!--<div class="modal-dialog" role="document">-->
                <!--<div class="modal-content">-->
                    <!--<div class="modal-header">-->
                        <!--<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>-->
                        <!--<h4 class="modal-title">物流公司添加</h4>-->
                    <!--</div>-->
                    <!--<div class="modal-body">-->
                            <!--<div class="form-group row">-->
                                <!--<label for="add_companySelect" class="col-sm-3 control-label">物流公司名称</label>-->

                                <!--<div class="col-sm-5">-->
                                    <!--&lt;!&ndash;<input type="text" class="form-control" id="add_companyName"/>&ndash;&gt;-->
                                    <!--<select id="add_companySelect" class="form-control" ></select>-->
                                <!--</div>-->
                            <!--</div>-->
                            <!--<div class="form-group row">-->
                                <!--<label for="add_contactorName" class="col-sm-3 control-label">联系人</label>-->

                                <!--<div class="col-sm-5">-->
                                    <!--<input type="text" class="form-control" id="add_contactorName"/>-->
                                <!--</div>-->
                            <!--</div>-->
                            <!--<div class="form-group row">-->
                                <!--<label for="add_telephone" class="col-sm-3 control-label">电话</label>-->

                                <!--<div class="col-sm-5">-->
                                    <!--<input type="text" class="form-control" id="add_telephone">-->
                                <!--</div>-->
                                <!--<div id="addError" class="col-sm-3"></div>-->
                            <!--</div>-->
                            <!--<input type="hidden" id="add_provider_id">-->
                    <!--</div>-->
                    <!--<div class="modal-footer">-->
                        <!--<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>-->
                        <!--<button type="button" class="btn btn-primary" onclick="submitDeliever()">保存</button>-->
                    <!--</div>-->
                    <!-- -->
                <!--</div>-->
            <!--</div>-->
        <!--</div>-->
        <input type="hidden" id="tokenId" value="<%=tokenId %>"/>
        <script type="text/javascript">
            var tokenId = $("#tokenId").val();
            DelieverProviderDo.init();



//            querySysDelivers();

//            function querySysDelivers(){
//
//                sendRequest({"length": "1000"},"/bm/delieverProvider/list.do",function(json){
//                    $(json.data.list).each(function(i,ite){
//                        var html = "<option id=\""+ite.id+"\">"+ite.name+"</option>";
//                        $("#add_companySelect").append(html);
//                    });
//                });
//            }






//            queryMyDelivers();

//            /*查询我的物流商*/
//            function queryMyDelivers(){
//                sendRequest({"tokenId": tokenId},"bm/delieverProvider/queryByShopId.do",function(json){
//                    $(json.data).each(function(i,ite){
//                        var html = "<tr>"+
//                                "    <td align=\"center\">"+ite.companyName+"</td>"+
//                                "    <td align=\"center\">"+ite.contactorName+"</td>"+
//                                "    <td align=\"center\">"+ite.telephone+"</td>"+
//                                "    <td align=\"center\">"+
//                                "        <a onclick=\"modalShow("+ite.id+")\">编辑</a>"+
//                                "        <a class=\"zs-table-delete\" onclick=\"deleteDeliever('"+ite.id+"')\">删除</a>"+
//                                "    </td>"+
//                                "</tr>";
//                        $("#deliversTable>tbody").append(html);
//                    });
//                });
//            }



//            /* 编辑物流商  */
//            function modalShow(id) {
//                $.ajax({
//                    url: toServerUrl("/bm/delieverProvider/queryById.do"),
//                    data: {
//                        "id": id,
//                    },
//                    type: "post",
//                    dataType: "json",
//                    success: function (data) {
//                        if (data.header.success == true) {
//                            var provider = data.data;
//                            $("#update_provider_id").val(provider.id);
//                            $("#update_companyName").val(provider.companyName);
//                            $("#update_contactorName").val(provider.contactorName);
//                            $("#update_telephone").val(provider.telephone);
//                        }
//                    }
//
//                });
//                $("#myModal_update").modal('show');
//            }
//
//            /* 删除  */
//            function deleteDeliever(id) {
//                if (confirm("确定要删除该物流商吗？")) {
//                    $.ajax({
//                        url: toServerUrl("/bm/delieverProvider/deleteDelieverProvider.do"),
//                        data: {
//                            "id": id,
//                        },
//                        type: "post",
//                        dataType: "json",
//                        success: function (data) {
//                            if (data.header.success == true) {
//                                window.location = toServerPageUrl("/bm/delieverProvider/queryByShopId.do");
//                            }
//                        }
//
//                    });
//                }
//
//            }
//            /* 提交修改内容  */
//            function updateDeliever() {
//                var numbers = /^1\d{10}$/;
//                var val = $('#update_telephone').val().replace(/\s+/g, ""); //获取输入手机号码
//                if (!numbers.test(val) || val.length == 0) {
//                    if ($('#updateError').find('span').length == 0) {
//                        $('#updateError').html('<span class="error" style="color:red;">手机号格式不对！</span>');
//                    }
//                    return false;
//                }
//
//
//                var id = $("#update_provider_id").val();
//                var companyName = $("#update_companyName").val();
//                var contactorName = $("#update_contactorName").val();
//                var telephone = $("#update_telephone").val();
//                $.ajax({
//                    url: toServerUrl("/bm/delieverProvider/updateDelieverProvider.do"),
//                    data: {
//                        "id": id,
//                        "companyName": companyName,
//                        "contactorName": contactorName,
//                        "telephone": telephone,
//                    },
//                    type: "post",
//                    dataType: "json",
//                    success: function (data) {
//                        if (data.header.success == true) {
//                            window.location = toServerPageUrl("/bm/delieverProvider/queryByShopId.do");
//                        }
//                    }
//
//                });
//
//            }
//
//            /* 添加物流商  */
//            function addCompany() {
//                $("#myModal_add").modal('show');
//            }
//
//            /* 提交添加物流商  */
//            function submitDeliever() {
//                var numbers = /^1\d{10}$/;
//                var val = $('#add_telephone').val().replace(/\s+/g, ""); //获取输入手机号码
//                if (!numbers.test(val) || val.length == 0) {
//                    if ($('#addError').find('span').length == 0) {
//                        $('#addError').html('<span class="error" style="color:red;">手机号格式不对！</span>');
//                    }
//                    return false;
//                }
//
//
//                var companyName = $("#add_companyName").val();
//                var contactorName = $("#add_contactorName").val();
//                var telephone = $("#add_telephone").val();
//                $.ajax({
//                    url: toServerUrl("/bm/delieverProvider/addDelieverProvider.do"),
//                    data: {
//                        "companyName": companyName,
//                        "contactorName": contactorName,
//                        "telephone": telephone,
//                    },
//                    type: "post",
//                    dataType: "json",
//                    success: function (data) {
//                        if (data.header.success == true) {
//
//                            window.location = toServerPageUrl("/bm/delieverProvider/queryByShopId.do");
//                        }
//                    }
//
//                });
//
//
//            }

        </script>


        </body>
        </html>