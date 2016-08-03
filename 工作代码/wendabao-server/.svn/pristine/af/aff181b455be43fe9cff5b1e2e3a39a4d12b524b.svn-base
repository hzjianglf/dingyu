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
</head>
<body>

<div class="container" style="width:100%;">
    <div class="row" style="border:0;margin-top:-10px;">
        <div class="col-sm-12">
            <h3 class="zs-iframe-title">回款清单详情</h3>
        </div>
    </div>
    <div class="row" style="margin-top:10px;">
        <div class="col-sm-1"  style="padding-left:30px;">
            <!--<label for="shopName" style=" padding-top:7px;">划款单编号：</label><span id="dateStr"></span>-->
        </div>
        <div class="col-sm-4"  style="padding-left:0px;">
            <label for="shopName" style=" padding-top:7px;">商铺名称：</label><span id="shopName"></span>
        </div>

        <div class="col-sm-4"  style="padding-left:0px;">
            <label for="timeStr" style=" padding-top:7px;">回款单截至时间：</label><span id="timeStr"></span>
        </div>


    </div>
    <div class="container contentContainer" style="width:100%;">
        <div class=" row" style="margin-top:10px;">
            <div class="col-sm-12" style="padding-left: 0px;padding-right: 0px;">
                <table class="table table-hover" id="transferDetailTable">
                    <thead>
                    <tr>
                        <th width="100">订单编号</th>
                        <th width="100">订单金额</th>
                        <th width="100">服务费</th>
                        <th width="100">服务费率</th>
                        <th width="100">实际回款金额</th>
                    </tr>
                    </thead>
                    <tbody>
                    </tbody>
                </table>
            </div>
        </div>
    </div>

</div>

<input type="hidden" id="tokenId" value="<%=tokenId %>"/>
<script type="text/javascript">
    var tokenId = $("#tokenId").val();
    var shopId = getUrlParam("shopId");
    var date = getUrlParam("date");
    queryShopTransferDetail();
    // 查询回款单
    function queryShopTransferDetail(){

        $("#transferDetailTable>tbody").empty();
        sendRequest({"transfer_time":date,"shopId":shopId},"/bm/order/queryTransferDetail.do",function(json){

      
            $(json.list).each(function(i,ite){
            	 // $("#dateStr").text(ite.transfer_time);
                if(i == 0 ){
                	 $("#timeStr").text(ite.transfer_time);
                }
               
               var html =
                       "<tr>"+
                       "    <td align=\"center\">"+ite.order_id+"</td>"+
                       "    <td align=\"right\">"+NumberFormat.formatNumber(ite.total_fee,1)+"</td>"+
                       "    <td align=\"right\">"+NumberFormat.formatNumber(ite.service_fee,1)+"</td>"+
                       "    <td align=\"right\">"+ite.service_fee_rate+"%</td>"+
                       "    <td align=\"right\">"+NumberFormat.formatNumber(ite.transfer_fee,1)+"</td>"+
                       "</tr>";
               $("#transferDetailTable>tbody").append(html);
            });

            var shop = json.shop;
            $("#shopName").text(shop.name);
        });
    }

</script>
</body>
</html>