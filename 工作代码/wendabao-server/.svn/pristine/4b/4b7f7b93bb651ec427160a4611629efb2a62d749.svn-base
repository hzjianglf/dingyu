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
            <h3 class="zs-iframe-title">商户划款清单</h3>
        </div>
    </div>
    <div class="row" style="margin-top:10px;">
        <div class="col-sm-2"  style="padding-left:0px;text-align:right;">
            <label for="dateStr" style=" padding-top:7px;">选择截至日期:</label>
        </div>
        <div class="col-sm-2"  style=" padding-left:0px;">
            <input type="text" id="dateStr" class="form-control" style="float:left" readonly>
        </div>
        <div class=" col-sm-2" align="left">
            <button type="button" class="btn btn-default zs-btn-default" onclick="queryTransferList()">查询</button>
        </div>
        <div class=" col-sm-6" align="right">
            <button type="button" class="btn btn-default zs-btn-green" onclick="createTransferList()">生成划款单</button>
        </div>
    </div>
    <div class="container contentContainer" style="width:100%;" id="printArea">
        <div class=" row" style="margin-top:10px;">
            <div class="col-sm-12" style="padding-left: 0px;padding-right: 0px;">
                <table class="table table-hover" id="transferListTable">
                    <thead>
                    <tr>
                        <th width="110">商铺名称</th>
                        <th width="50" class="hiddenClumn">订单量</th>
                        <th width="110" class="hiddenClumn">订单总金额</th>
                        <th width="110" class="hiddenClumn">服务费</th>
                        <th width="110" class="hiddenClumn">服务费率</th>
                        <th width="110">实际划款金额</th>
                        <th width="50">划款状态</th>
                        <th width="110" class="hiddenClumn">操作</th>
                        <th width="110" class="showClumn hidden">收款账号开户行</th>
                        <th width="110" class="showClumn hidden">收款账号</th>
                        <th width="110" class="showClumn hidden">收款账号户名</th>
                    </tr>
                    </thead>
                    <tbody>
                    </tbody>
                </table>
            </div>

        </div>
        <div class="row hiddenClass" style="margin-top:10px;">
            <div class=" col-sm-12" align="left">
                <label>划款清单汇总信息</label>
            </div>
        </div>

        <div class=" row hiddenClass" style="margin-top:10px;">
            <div class="col-sm-12" style="padding-left: 0px;padding-right: 0px;">
                <table class="table table-hover" id="transferCountTable">
                    <thead>
                    <tr>
                        <th width="110">划款状态</th>
                        <th width="110">订单总量</th>
                        <th width="110">订单总额</th>
                        <th width="110">服务费总额</th>
                        <th width="110">划款总额</th>
                    </tr>
                    </thead>
                    <tbody>
                    </tbody>
                </table>
            </div>

        </div>

    </div>
    <div class="row" style="margin-top:10px;">
        <div class=" col-sm-12" align="right">
            <button type="button" class="btn btn-default zs-btn-green"  id="transferAllBtn" style="margin-right: 10px;" onclick="transferNow('ALL')">全部划款</button>
            <button type="button" class="btn btn-default zs-btn-default"  id="printBtn" onclick="print()">打印</button>
        </div>
    </div>
</div>


<input type="hidden" id="tokenId" value="<%=tokenId %>"/>
<script type="text/javascript">
    var tokenId = $("#tokenId").val();
    $('#dateStr').datepicker({format:'yyyy-mm-dd'}).on('changeDate', function(ev){
        $('#dateStr').datepicker('hide');
    });
    $(".hiddenClass").hide();
    $("#transferAllBtn").hide();
    // 查询划款单
    function queryTransferList(){
        var date = $("#dateStr").val();
        if("" == date){
            showDialog("请选择截至日期","toast");
            return;
        }
        $("#transferListTable>tbody").empty();
        $("#transferCountTable>tbody").empty();
        $("#transferAllBtn").hide();
        sendRequest({"transfer_time":date},"/bm/order/queryTransfer.do",function(json){
            var showAll = false;
            $(json.list).each(function(i,ite){
                var transferStatus = "";
                var transferOperateStr = "<a onclick=\"transferNow('"+ite.shop_id+"')\"  style=\"display: block\">立即划款</a>";
                if(ite.status == "1"){
                    transferStatus = "未划款";
                    showAll = true;
                }else if(ite.status == "2"){
                    transferStatus = "部分划款";
                }else if(ite.status == "3"){
                    transferStatus = "已划款";
                    transferOperateStr = "";
                }
               var html =
                       "<tr>"+
                       "    <td>"+ite.shop_name+"</td>"+
                       "    <td align=\"right\" class=\"hiddenClumn\">"+ite.order_num+"</td>"+
                       "    <td align=\"right\" class=\"hiddenClumn\">"+NumberFormat.formatNumber(ite.total_fee,1)+"</td>"+
                       "    <td align=\"right\" class=\"hiddenClumn\">"+NumberFormat.formatNumber(ite.service_fee,1)+"</td>"+
                       "    <td align=\"right\" class=\"hiddenClumn\">"+ite.service_fee_rate+"%</td>"+
                       "    <td align=\"right\">"+NumberFormat.formatNumber(ite.transfer_fee,1)+"</td>"+
                       "    <td align=\"center\">"+transferStatus+"</td>"+
                       "    <td align=\"center\" class=\"hiddenClumn\">"+
                       "        <a onclick=\"toTransferListDetail('"+ite.shop_id+"')\" style=\"display: block\">查看详情</a>"+
                       transferOperateStr+
                       "    </td>"+
                       "    <td align=\"center\" class='showClumn hidden'>"+(ite.account_bank == undefined ? '' :ite.account_bank )+"</td>"+
                       "    <td align=\"center\" class='showClumn hidden'>"+(ite.account_no == undefined ?  '' :ite.account_no)+"</td>"+
                       "    <td align=\"center\" class='showClumn hidden'>"+(ite.account_per_name == undefined ?  '' :ite.account_per_name)+"</td>"+
                       "</tr>";
               $("#transferListTable>tbody").append(html);
            });

            $(json.sumInfo).each(function(i,ite){
                var transferStatus = "";
                if(ite.STATUS == "1"){
                    transferStatus = "未划款";
                }else if(ite.STATUS == "3"){
                    transferStatus = "已划款";
                }
                var html =
                        "<tr>"+
                        "    <td align=\"center\">"+transferStatus+"</td>"+
                        "    <td align=\"right\">"+ite.order_num+"</td>"+
                        "    <td align=\"right\">"+NumberFormat.formatNumber(ite.total_fee,1)+"</td>"+
                        "    <td align=\"right\">"+NumberFormat.formatNumber(ite.service_fee,1)+"</td>"+
                        "    <td align=\"right\">"+NumberFormat.formatNumber(ite.transfer_fee,1)+"</td>"+
                        "</tr>";
                $("#transferCountTable>tbody").append(html);
            });

            $(".hiddenClass").show();

            if(showAll){
                $("#transferAllBtn").show();
            }else{
                $("#transferAllBtn").hide();
            }
        });
    }

    // 生成划款单
    function createTransferList(){
        showDialog("每天只能生成一次划款单，确定现在生成划款单吗？","confirm",{
            onOk: function(){
                $("#transferListTable>tbody").empty();
                $("#transferCountTable>tbody").empty();
                $("#transferAllBtn").hide();
                $("#dateStr").val(getDate());
                sendRequest({},"/bm/order/createTransfer.do",function(json){
                    var showAll = false;
                    $(json.list).each(function(i,ite){
                        var transferStatus = "";
                        var transferOperateStr = "<a onclick=\"transferNow('"+ite.shop_id+"')\"  style=\"display: block\">立即划款</a>";
                        if(ite.status == "1"){
                            transferStatus = "未划款";
                            showAll = true;
                        }else if(ite.status == "2"){
                            transferStatus = "部分划款";
                        }else if(ite.status == "3"){
                            transferStatus = "已划款";
                            transferOperateStr = "";
                        }
                        var html =
                                "<tr>"+
                                "    <td>"+ite.shop_name+"</td>"+
                                "    <td align=\"right\" class=\"hiddenClumn\">"+ite.order_num+"</td>"+
                                "    <td align=\"right\" class=\"hiddenClumn\">"+NumberFormat.formatNumber(ite.total_fee,1)+"</td>"+
                                "    <td align=\"right\" class=\"hiddenClumn\">"+NumberFormat.formatNumber(ite.service_fee,1)+"</td>"+
                                "    <td align=\"right\" class=\"hiddenClumn\">"+ite.service_fee_rate+"%</td>"+
                                "    <td align=\"right\">"+NumberFormat.formatNumber(ite.transfer_fee,1)+"</td>"+
                                "    <td align=\"center\">"+transferStatus+"</td>"+
                                "    <td align=\"center\" class=\"hiddenClumn\">"+
                                "        <a onclick=\"toTransferListDetail('"+ite.shop_id+"')\" style=\"display: block\">查看详情</a>"+
                                transferOperateStr+
                                "    </td>"+
                                "    <td align=\"center\" class='showClumn hidden'>"+(ite.account_bank == undefined ? '' :ite.account_bank )+"</td>"+
                                "    <td align=\"center\" class='showClumn hidden'>"+(ite.account_no == undefined ?  '' :ite.account_no)+"</td>"+
                                "    <td align=\"center\" class='showClumn hidden'>"+(ite.account_per_name == undefined ?  '' :ite.account_per_name)+"</td>"+
                                "</tr>";
                        $("#transferListTable>tbody").append(html);
                    });

                    $(json.sumInfo).each(function(i,ite){
                        var transferStatus = "";
                        if(ite.STATUS == "1"){
                            transferStatus = "未划款";
                        }else if(ite.STATUS == "3"){
                            transferStatus = "已划款";
                            transferOperateStr = "";
                        }
                        var html =
                                "<tr>"+
                                "    <td align=\"center\">"+transferStatus+"</td>"+
                                "    <td align=\"right\">"+ite.order_num+"</td>"+
                                "    <td align=\"right\">"+NumberFormat.formatNumber(ite.total_fee,1)+"</td>"+
                                "    <td align=\"right\">"+NumberFormat.formatNumber(ite.service_fee,1)+"</td>"+
                                "    <td align=\"right\">"+NumberFormat.formatNumber(ite.transfer_fee,1)+"</td>"+
                                "</tr>";
                        $("#transferCountTable>tbody").append(html);
                    });

                    $(".hiddenClass").show();
                    if(showAll){
                        $("#transferAllBtn").show();
                    }else{
                        $("#transferAllBtn").hide();
                    }
                });
            }
        });
    }

    // 划款
    function transferNow(shopId){
        var date = $("#dateStr").val();
        var msg = "确定将该商户的划款状态置为已划款？";
        if(shopId == "ALL"){
            msg = "确定将所有商户的划款状态置为已划款？";
        }
        showDialog(msg,"confirm",{
            onOk: function(){
                sendRequest({"shopId":shopId,"transfer_time": date},"/bm/order/batchTransfer.do",function(json){
                    showDialog("划款状态修改成功！","toast");
                    queryTransferList();
                });
            }
        });
    }

    // 划款单详情
    function toTransferListDetail(shopId){
        window.location=toServerPageUrl("/bm/report/toTransferDetailView.do?shopId=" + shopId+"&date="+$("#dateStr").val());
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