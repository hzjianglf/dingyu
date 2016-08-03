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
            <script src="<%=basePath%>js/mallDialog.js"></script>
            <link rel="stylesheet" href="<%=basePath %>/css/mall.css">
            <title>采供网</title>
            <script src="<%=bPath%>js/page.js"></script>
        </head>
        <body>

        <div class="container" style="width:100%;">
            <div class="row" style="border:0;margin-top:-10px;">
                <div class="col-sm-12">
                    <h3 class="zs-iframe-title">物流信息维护</h3>
                    <button class="btn btn-default zs-table-top-btn zs-btn-green" onclick="addCompany()" style="float:right ">新增</button>
                </div>
            </div>
            <div class="data_content row" style="margin-top:10px;">
                <div class="col-sm-12">
                    <table class="table table-hover" id="deliversTable">
                        <thead>
                        <tr>
                            <th width="110">物流公司名称</th>
                            <th width="110">操作</th>
                        </tr>
                        </thead>
                        <tbody></tbody>
                    </table>
                </div>

            </div>
            <div class="data_content row" style="margin-top:10px;text-align: right">
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

        <!-- 编辑物流商 -->
        <div class="modal fade" id="myModal_update" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                        <h4 class="modal-title">物流公司编辑</h4>
                    </div>
                    <div class="modal-body">
                        <form id="update" class="form-horizontal" method="post">
                            <div class="form-group">
                                <label for="update_companyName" class="col-sm-3 control-label">物流公司名称</label>
                                <div class="col-sm-5">
                                    <input type="text" class="form-control" id="update_companyName"/>
                                </div>
                            </div>
                            <input type="hidden" id="update_provider_id">
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                        <button type="button" class="btn btn-primary" onclick="updateDeliever()">保存</button>
                    </div>
                    </form>
                </div>
            </div>
        </div>

        <!-- 添加物流商 -->
        <div class="modal fade" id="myModal_add" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                        <h4 class="modal-title">物流公司添加</h4>
                    </div>
                    <div class="modal-body">
                        <form id="update" class="form-horizontal" method="post">
                            <div class="form-group">
                                <label for="add_companyName" class="col-sm-3 control-label">物流公司名称</label>

                                <div class="col-sm-5">
                                    <input type="text" class="form-control" id="add_companyName"/>
                                </div>
                            </div>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                        <button type="button" class="btn btn-primary" onclick="submitDeliever()">保存</button>
                    </div>
                    </form>
                </div>
            </div>
        </div>
        <input type="hidden" id="tokenId" value="<%=tokenId %>"/>
        <script type="text/javascript">
            var tokenId = $("#tokenId").val();
            toPage();
            /*查询物流商*/
            function toPage(start){
                $("#deliversTable>tbody").empty();
                if(start == undefined || start == null){
                    start = 0;
                }
                sendRequest({start:start},"/bm/delieverProvider/list.do",function(json){
                    $(json.data.list).each(function(i,ite){
                        var html =
                                "<tr>"+
                                "    <td align=\"center\">"+ite.name+"</td>"+
                                "    <td align=\"center\">"+
                                "        <a onclick=\"modalShow('"+ite.id+"','"+ite.name+"')\">编辑</a>"+
                              "        <a class=\"zs-table-delete\" onclick=\"deleteDeliever('"+ite.id+"')\">删除</a>"+
                                "    </td>"+
                                "</tr>";
                        $("#deliversTable>tbody").append(html);
                    });
                    $("#navArea").html(showNav(json.data));
                });
            }

            /* 编辑物流商  */
            function modalShow(id, name) {
                $("#update_companyName").val(name);
                $("#update_provider_id").val(id);
                $("#myModal_update").modal('show');
            }

            /* 提交修改内容  */
            function updateDeliever() {
                var companyName = $("#update_companyName").val();
                var id = $("#update_provider_id").val();
                $.ajax({
                    url: toServerUrl("/bm/delieverProvider/save.do"),
                    data: {"id": id, "name": companyName},
                    type: "post",
                    dataType: "json",
                    success: function (data) {
                        if (data.header.success == true) {
                            toPage();
                            $('#myModal_update').modal('hide')
                        }
                    }
                });
            }
            function deleteDeliever(id){
            	showDialog("确认删除此物流信息","confirm",{
            		onOk:function(){
            			$.ajax({
            				url:toServerUrl("/bm/delieverProvider/deleteDelieverProvider.do"),
            				type:"post",
            				data:{"id":id},
            				dataType:"json",
            				success:function(data){
            					if(data.header.success == true){
            						toPage();
            					}
            				}
            			})
            		}
            	},"")
            }

            /* 添加物流商  */
            function addCompany() {
                $("#myModal_add").modal('show');
            }

            /* 提交添加物流商  */
            function submitDeliever() {
                var companyName = $("#add_companyName").val();
                $.ajax({
                    url: toServerUrl("/bm/delieverProvider/save.do"),
                    data: {"name": companyName},
                    type: "post",
                    dataType: "json",
                    success: function (data) {
                        if (data.header.success == true) {
                            toPage();
                            $('#myModal_add').modal('hide')
                        }
                    }
                });
            }
        </script>
        </body>
        </html>