<%@ page language="java" contentType="text/html; charset=utf-8"
pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
String tokenId = request.getParameter("tokenId");
%>
<!DOCTYPE html>
<html>
<head>
    <!-- 为了让 IE 浏览器运行最新的渲染模式下，建议将此 <meta> 标签加入到你的页面中：-->
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <!--将下面的 <meta> 标签加入到页面中，可以让部分国产浏览器默认采用高速模式渲染页面：-->
    <meta name="renderer" content="webkit">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>餐饮采购网</title>
    <!-- Bootstrap -->
    <!-- 新 Bootstrap 核心 CSS 文件 -->
    <%@include file="/common.jsp"%>
    <link rel="stylesheet" href="<%=basePath %>/css/mall.css">
    <!--<script src="<%=basePath %>/js/echarts.js"></script>-->
    <style>
        .shop-survey{
            padding-left: 5px;
            height:120px;
            background-color: #f85e57;
            width: 32%;
            /*text-align: center;*/
        }
        .shop-survey-icon{
            padding-left: 5px;
            text-align: center;
            height: 100%;
            /*display: table-cell;*/
        }
        .shop-survey-img{
            width: auto;
            height:50%;
            margin-top: 20px;
        }
        .shop-survey-txt{
            height: 20%;
            width:100%;
            display: block;
            margin-top: 6px;
            color: white;
            text-align: center;
            font-size: 14px;

        }
    </style>
</head>
<body>

<div class="container-fluid" style="margin:0px;">
    <div class="row" style="border:0;margin-top:-10px;">
        <div class="col-sm-12 col-xs-12">
            <h3 class="zs-iframe-title">商铺概况</h3>
        </div>
    </div>
    <div class="row" style="padding-left: 15px;padding-right: 15px;">
        <div class="col-sm-4 col-xs-4 shop-survey" style="background-color: #f85e57;margin-right: 2%;">
            <div class="col-sm-4 col-xs-4 shop-survey-icon">
                <img src="<%=basePath %>/image/icon_order_status.png" class="shop-survey-img">
                <div class="shop-survey-txt">
                    <span>订单信息</span>
                </div>
            </div>
            <div class="col-sm-8 col-xs-8" style="padding-left:20px;color: white;padding-top: 15px;">
                <div style="height: 30px;display: block;line-height:30px;">
                    <span>待发货：<span id="orderStatus2"></span></span>
                </div>
                <div style="height: 30px;display: block;line-height:30px;">
                    <span>待收货：<span id="orderStatus3"></span></span>
                </div>
                <div style="height: 30px;display: block;line-height:30px;">
                    <span>待收款：<span id="orderStatus4"></span></span>
                </div>
            </div>
        </div>
        <div class="col-sm-4 col-xs-4 shop-survey" style="background-color: #ffcc55;margin-right: 2%;">
            <div class="col-sm-4 col-xs-4 shop-survey-icon">
                <img src="<%=basePath %>/image/icon_commodity_status.png" class="shop-survey-img">
                <div class="shop-survey-txt">
                    <span>商品信息</span>
                </div>
            </div>
            <div class="col-sm-8 col-xs-8" style="padding-left:20px;color: white;padding-top: 25px;">
                <div style="height: 35px;display: block;line-height:35px;">
                    <span>出售中：<span id="productT"></span></span>
                </div>
                <div style="height: 35px;display: block;line-height:35px;">
                    <span>已下架：<span id="productF"></span></span>
                </div>
            </div>
        </div>
        <div class="col-sm-4 col-xs-4 shop-survey" style="background-color: #5CACEE;">
            <div class="col-sm-4 col-xs-4 shop-survey-icon">
                <img src="<%=basePath %>/image/icon_operating_status.png" class="shop-survey-img">
                <div class="shop-survey-txt">
                    <span>营业状态</span>
                </div>
            </div>
            <div class="col-sm-8 col-xs-8" style="text-align: center;color: white;font-size: 26px;padding-top: 42px;">
                <span id="shopStatus"></span>
            </div>
        </div>
    </div>

    <div class="row" style="border:0;margin-top:20px;">
        <div class="col-sm-12">
            <h3 class="zs-iframe-title" style="border-color: #28b779">销售统计</h3>
        </div>
    </div>
    <div class="row" style="border:0;margin-top:20px;">
        <div class="col-sm-12">
            <div id="chartView" style="height:420px;width:100%;"></div>
        </div>
    </div>


</div>
<script src="<%=basePath %>/js/dist/echarts.js"></script>
<script type="text/javascript">
    $(document).ready(function() {

        var shopId = "";

        getMyShop();

        function getMyShop(){
            sendRequest({},"/bm/shop/currentShop.do",function(json){
                if("3" == json.shop.status){
                    $("#shopStatus").text("营业中");
                }else{
                    $("#shopStatus").text("休息中");
                }
                shopId = json.shop.id;
                getProductStatus();
                getOrderStatus();
                getCountData();
            });
        }

        /**
         * 取得订单状态数据
         */
        function getOrderStatus(){
            sendRequest({shopId: shopId},"/bm/report/order01.do",function(json){
                $(json.data).each(function (i, ite) {
                    if("2" == ite.STATUS){
                        // 待发货
                        $("#orderStatus2").text(ite.orderCount);
                    }else if("3" == ite.STATUS){
                        // 待收货
                        $("#orderStatus3").text(ite.orderCount);
                    }else if("4" == ite.STATUS){
                        // 待收款
                        $("#orderStatus4").text(ite.orderCount);
                    }
                });
            });
        }

        /**
         * 取得产品情况数据
         */
        function getProductStatus(){
            sendRequest({shopId: shopId},"/bm/report/product01.do",function(json){
                $(json.data).each(function (i, ite) {
                    if("T" == ite.STATUS){
                        $("#productT").text(ite.productCount);
                    }else if("F" == ite.STATUS){
                        $("#productF").text(ite.productCount);
                    }
                });
            });
        }

        /**
         * 取得销售统计数据
         */
        function getCountData(){
            var nowYear = new Date().getFullYear();
            sendRequest({shopId: shopId, year: nowYear},"/bm/report/order02.do",function(json){
                showChart(json.orderCount, json.orderSum);
            });
        }

        /**
         * 显示图表
         * @param orderCount
         * @param orderSum
         */
        function showChart(orderCount, orderSum){
            require.config({
                paths: {
                    echarts: '<%=basePath %>/js/dist'
                }
            });

            require(
                    [
                        'echarts',
                        'echarts/chart/bar',
                        'echarts/chart/line'
                    ],
                    function (ec) {
                        //--- 折柱 ---
                        var myChart = ec.init(document.getElementById('chartView'));
                        myChart.setOption({
                            tooltip : {
                                trigger: 'axis'
                            },
                            legend: {
                                data:['订单量','销售额'],
                                selectedMode: 'multiple'
                            },
                            toolbox: {
                                show : true,
                                feature : {
                                    mark : {show: false},
                                    dataView : {show: false, readOnly: false},
                                    magicType : {show: false, type: ['line', 'bar']},
                                    restore : {show: true},
                                    saveAsImage : {show: true}
                                }
                            },
                            xAxis : [
                                {
                                    type : 'category',
                                    data : ['1月','2月','3月','4月','5月','6月','7月','8月','9月','10月','11月','12月']
                                }
                            ],
                            yAxis : [
                                {
                                    type : 'value',
                                    name : '销售额',
                                    show: 'show',
                                    axisLabel : {
                                        formatter: '{value} 元'
                                    }
                                },
                                {
                                    type : 'value',
                                    name : '订单量',
                                    show: 'show',
                                    axisLabel : {
                                        formatter: '{value}'
                                    },
                                    splitArea : {show : true}
                                }
                            ],
                            series : [
                                {
                                    name:'销售额',
                                    type:'bar',
                                    data:orderSum
                                },
                                {
                                    name:'订单量',
                                    type:'line',
                                    yAxisIndex: 1,
                                    data:orderCount
                                }
                            ]
                        });

                        $("body").onresize = function() {
                            myChart.resize();
                        }
                    }
            );
        }
    });

</script>
</body>
</html>