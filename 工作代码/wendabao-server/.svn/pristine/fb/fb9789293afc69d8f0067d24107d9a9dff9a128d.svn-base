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
    <script src="<%=basePath%>js/bootstrap-datepicker.js"></script>
    <link rel="stylesheet" href="<%=basePath %>/css/datepicker.css">
    <script src="<%=basePath%>js/mallDialog.js"></script>
    <script src="<%=basePath%>js/num-format.js"></script>
    <script src="<%=basePath %>js/jquery.PrintArea.js" type="text/javascript"></script>
</head>
<body>

<div class="container" style="width:100%;">
    <div class="row" style="border:0;margin-top:-10px;">
        <div class="col-sm-12">
            <h3 class="zs-iframe-title">回款清单</h3>
        </div>
    </div>
    <div class="row" style="margin-top:10px;">
        <div class="col-sm-2"  style="padding-left:0px;text-align:right;">
            <label for="dateStr" style=" padding-top:7px;">选择截至日期:</label>
        </div>
        <div class="col-sm-2"  style=" padding-left:0px;">
            <input type="text" id="dateStr" class="form-control" style="float:left" readonly>
        </div>
        <div class=" col-sm-8" align="left">
            <button type="button" class="btn btn-default zs-btn-default" onclick="toPage()">查询</button>
            <button type="button" class="btn btn-default zs-btn-default" onclick="javascript: $('#dateStr').val('');">清空查询条件</button>
        </div>
    </div>
    <div class="container contentContainer" style="width:100%;" id="printArea">
        <div class=" row" style="margin-top:10px;">
            <div class="col-sm-12" style="padding-left: 0px;padding-right: 0px;">
                <table class="table table-hover" id="transferListTable">
                    <thead>
                    <tr>
                        <th width="110">编号</th>
                        <th width="50">订单量</th>
                        <th width="110">订单总金额</th>
                        <th width="110">服务费</th>
                        <th width="110">服务费率</th>
                        <th width="110">实际回款金额</th>
                        <th width="50">回款状态</th>
                        <th width="110">操作</th>
                    </tr>
                    </thead>
                    <tbody></tbody>
                </table>
            </div>

        </div>

        <div class="row">
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
    $('#dateStr').datepicker({format:'yyyy-mm-dd'}).on('changeDate', function(ev){
        $('#dateStr').datepicker('hide');
    });
    var shopId = "";
    queryShopId();
    function queryShopId(){
        sendRequest({"tokenId":tokenId},"/pm/user/queryByTokenId.do",function(json){
            shopId = json.shop.id;
            toPage();
        });
    }

    // 查询回款单
    function toPage(start){
        if(start == undefined || start == null){
            start = 0;
        }
        var date = $("#dateStr").val();
        $("#transferListTable>tbody").empty();
        sendRequest({"shopId":shopId, "transfer_time":date,start:start},"/bm/order/querySellerTransfer.do",function(json){
            $(json.page.list).each(function(i,ite){
                var transferStatus = "";
                if(ite.status == "1"){
                    transferStatus = "未回款";
                }else if(ite.status == "2"){
                    transferStatus = "部分回款";
                }else if(ite.status == "3"){
                    transferStatus = "已回款";
                }
               var html =
                       "<tr>"+
                       "    <td align=\"center\">"+ite.transfer_time+"</td>"+
                       "    <td align=\"right\">"+ite.order_num+"</td>"+
                       "    <td align=\"right\">"+NumberFormat.formatNumber(ite.total_fee,1)+"</td>"+
                       "    <td align=\"right\">"+NumberFormat.formatNumber(ite.service_fee,1)+"</td>"+
                       "    <td align=\"right\">"+ite.service_fee_rate+"%</td>"+
                       "    <td align=\"right\">"+NumberFormat.formatNumber(ite.transfer_fee,1)+"</td>"+
                       "    <td align=\"center\">"+transferStatus+"</td>"+
                       "    <td align=\"center\">"+
                       "        <a onclick=\"toShopTransferListDetail('"+ite.shop_id+"','"+ite.transfer_time+"')\" style=\"display: block\">查看详情</a>"+
                       "    </td>"+
                       "</tr>";
               $("#transferListTable>tbody").append(html);
            });

            $("#navArea").html(showNav(json.page));
        });
    }


    // 划款单详情
    function toShopTransferListDetail(shopId, dateStr){
        window.location=toServerPageUrl("/bm/report/toShopTransferDetailView.do?shopId=" + shopId+"&date="+dateStr);
    }

    function print(){
        var html = $("#printArea").html();
        html = "<h4>商户划款清单("+$("#dateStr").val()+")</h4>"+html;
        html = html.replace(/hiddenClumn/g, "hidden");
        html = html.replace(/showClumn hidden/g, "");
        $("#printArea").printArea(".table>thead>tr>th{border-bottom:1px solid #333333 !important;} " +
                "thead{background-color: transparent} " +
                "table,thead,th,td,.table>thead>tr>th{border:1px solid #333333;} " +
                "th,td,label,span{font-size: 10px;}",html);
    }

    function getDate(){
        var mydate = new Date();
        var str = "" + mydate.getFullYear() + "-";
        str += (mydate.getMonth()+1) + "-";
        str += mydate.getDate();
        return str;
    }

</script>


</body>
</html>