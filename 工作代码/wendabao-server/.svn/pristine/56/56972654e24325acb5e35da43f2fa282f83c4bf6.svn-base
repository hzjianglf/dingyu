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
String tokenId = (String)request.getAttribute("tokenId");
%>
<!DOCTYPE html>
<html lang="zh-CN">

<head>
    <%@include file="/common.jsp" %>
    <title>采供网</title>
    <script src="<%=basePath%>js/mallDialog.js"></script>
    <script src="<%=basePath%>js/num-format.js"></script>
</head>
<body>

<div class="container" style="width:100%;">
    <div class="row" style="border:0;margin-top:-10px;">
        <div class="col-sm-12">
            <h3 class="zs-iframe-title">逾期未收货订单处理</h3>
        </div>
    </div>
    <div class="row" style="margin-top:10px;">
        <div class="col-sm-4"  style="padding-left:0px;text-align:right;">
            <label for="hours" style=" padding-top:7px;">距离发货完成时间（小时）:</label>
        </div>
        <div class="col-sm-2"  style=" padding-left:0px;">
            <input type="number" id="hours" class="form-control" style="float:left" value="48">
        </div>
        <div class=" col-sm-2" align="left">
            <button type="button" class="btn btn-default zs-btn-default" onclick="toPage(0)">查询</button>
        </div>
    </div>
    <div class="container contentContainer" style="width:100%;">
        <div class=" row" style="margin-top:10px;">
            <div class="col-sm-12">
                <table class="table table-hover" id="orderTable">
                    <thead>
                    <tr>
                        <th width="30"></th>
                        <th width="110">订单编号</th>
                        <th width="110">商铺名称</th>
                        <th width="110">订单金额（元）</th>
                        <th width="110">发货日期</th>
                        <th width="110">操作</th>
                    </tr>
                    </thead>
                    <tbody></tbody>
                </table>
            </div>

        </div>
        <div class="data_content row" style="margin-top:10px;text-align: left">
            <div class="col-sm-12">
                <button id="allCheck" class="btn btn-default zs-btn-default notChecked">全选</button>
                <button id="handleOrders" class="btn btn-default zs-btn-green">批量处理</button>
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
</div>


<input type="hidden" id="tokenId" value="<%=tokenId %>"/>
<script type="text/javascript">
    var tokenId = $("#tokenId").val();
    toPage();
    // 全选按钮事件
    $("#allCheck").on("click", function(){
        console.log($("#allCheck").attr('checked'));
        if($("#allCheck").hasClass("notChecked")){
            $("#allCheck").text("取消全选");
            $("#allCheck").removeClass("notChecked");
            $("input[name='orderCheck']").prop('checked',true);
        }else{
            $("#allCheck").text("全选");
            $("#allCheck").addClass("notChecked");
            $("input[name='orderCheck']").prop('checked',false);
        }

    });

    // 批量处理按钮事件
    $("#handleOrders").on("click",function(){
        var checkedOrders = $("input[name='orderCheck']:checked");
        var ids = "";
        $(checkedOrders).each(function(i, ite){
            ids += $(ite).attr("value");
            ids += SPECIAL_CHAR;
        });
        ids = removeFirstLastChar(ids, SPECIAL_CHAR);
        adminHandleNotReceivingOrders(ids);
    });

    // 查询逾期未收货订单
    function toPage(start){
        var delayHours = $("#hours").val();
        if(""==delayHours){
            delayHours = 48;
        }
        if(checkDataReg(REG_INTEGER, delayHours)){
            $("#orderTable>tbody").empty();
            if(start == undefined || start == null){
                start = 0;
            }
            sendRequest({"delayHours":delayHours, start:start},"/bm/report/queryDeplayOrderList.do",function(json){
                $(json.data.list).each(function(i,ite){
                    var html  =
                            "<tr>"+
                            "    <td align=\"center\"><input type=\"checkbox\" name=\"orderCheck\" value='"+ite.id+"'></td>"+
                            "    <td align=\"center\">"+ite.id+"</td>"+
                            "    <td align=\"left\">"+ite.shopName+"</td>"+
                            "    <td align=\"right\" style='margin-right: 10px;'>"+NumberFormat.formatNumber(ite.realTotal,2)+"</td>"+
                            "    <td align=\"center\">"+ite.sendTime+"</td>"+
                            "    <td align=\"center\">" +
                            "		<a onclick=\"adminHandleNotReceivingOrders('"+ite.id+"')\" >处理为已收货</a>"
                    "    </td>"+
                    "</tr>";
                    $("#orderTable>tbody").append(html);
                });
                $("#navArea").html(showNav(json.data));
            });
        }else{
            showDialog("【距离发货完成时间】只能输入整数。","toast");
            $("#hours").val("");
        }
    }

    // 处理多个订单
    function adminHandleNotReceivingOrders(orderIds){
        var msg = "";
        if(orderIds.indexOf(SPECIAL_CHAR) > -1){
            msg = "确定将这些订单置为已收货订单吗？";
        }else{
            msg = "确定将订单"+orderIds+"置为已收货订单吗？";
        }
        showDialog(msg, "confirm", {
            onOk:function(){
                sendRequest({"orderIds":orderIds},"/bm/order/updateDeplayOrders.do",function(json){
                    showDialog("订单状态修改成功！","toast");
                    toPage(0);
                });
            }
        });
    }

</script>


</body>
</html>