<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@page import="com.handany.bm.model.BmShop"%>
<%@page import="com.handany.bm.model.BmPicture"%>
<%@page import="java.lang.String"%>
<%@page import="java.util.*"%>
<%@page import="com.handany.base.common.Constants"%>
<%@page import="com.handany.base.common.ApplicationConfig"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
BmShop shop = (BmShop)request.getAttribute("shop");
String tokenId = (String)request.getAttribute("tokenId");
String picPath = ApplicationConfig.getConfig("image_server");
List
<BmPicture> picList = (List
    <BmPicture>)request.getAttribute("picList");
        String p1 = null;
        String p2 = null;
        String p3 = null;
        String u1 = null;
        String u2 = null;
        String u3 = null;
        //门头照：shop_lic 身份证：shop_tax 营业执照：shop_org
        if(null != picList && picList.size() != 0){
        for(BmPicture pic : picList){
        String type = pic.getType();
        String url = pic.getUrl();
        if(type.equals("shop_lic")){
        p1 = type;
        u1 = url;
        }else if(type.equals("shop_tax")){
        p2 = type;
        u2 = url;
        }else if(type.equals("shop_org")){
        p3 = type;
        u3 = url;
        }

        }

        }
        %>
        <!DOCTYPE html>
        <!--[if IE 8]>
        <html lang="en" class="ie8 no-js"> <![endif]-->
        <!--[if IE 9]>
        <html lang="en" class="ie9 no-js"> <![endif]-->
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
            <title>采供网</title>
            <%@include file="/common.jsp" %>
            <script src="<%=basePath %>js/region.js" type="text/javascript"></script>
            <script src="<%=basePath %>js/mallDialog.js" type="text/javascript"></script>
            <script src="<%=basePath %>js/jquery.PrintArea.js" type="text/javascript"></script>
            <script src="<%=basePath%>js/num-format.js"></script>
            <meta content="" name="description"/>
            <meta content="" name="author"/>

            <link href="<%=basePath %>css/shop/layoutb.css" rel="stylesheet" type="text/css"/>
            <link rel="stylesheet" href="<%=basePath %>/css/mall.css">
            <script class="resources library" src="<%=basePath %>js/ajaxfileupload.js" type="text/javascript"></script>
            <style>
                .img-rounded{
                    width: 160px;
                    height: 160px;
                }
                input[type="file"]{
                    width: 160px;
                    height: 160px;
                    margin-top: 10px;
                }
            </style>
        </head>
        <div class="container-fluid">
            <div class="row" style="border:0;margin-top:-10px;">
                <div class="col-sm-12">
                    <h3 class="zs-iframe-title">商铺设置</h3>
                </div>
            </div>
            <%--
            <nav class="navbar navbar-default navbar-fixed-top row" style="border:0">--%>
                <%--
                <ol class="breadcrumb">--%>
                    <%--
                    <li style="*float:left"><a href="#">我的商铺</a></li>
                    --%>
                    <%--
                    <li style="*float:left"><a href="#">商铺设置</a></li>
                    --%>
                    <%--
                </ol>
                --%>
                <%----%>
                <%--
            </nav>
            --%>

            <!-- <h1>个人中心</h1>
            <hr style="height:1px;border:none;border-top:1px solid #555555;" />
             -->
            <!-- <h2>基础信息</h2> -->
            <ul class="nav nav-tabs" style="margin-top:10px;width: 83%;">
                <li id="shopBaseMsg" role="presentation" class="active"><a href="javascript:shopBaseMsg()">商户基本信息</a></li>
                <li id="shopIdentify" role="presentation"><a href="javascript:shopIdentify()">商户认证</a></li>
                <% if("2".equals(shop.getIdentification())){%>
                <li id="printReceipt" role="presentation"><a href="javascript:showPrintReceipt()">打印保证金收据</a></li>
                <% }%>
            </ul>

            <div id="include">

                <!-- 商户认证 -->
                <div id="shopBaseMsgFace" class="container-fluid" style="padding-top:20px;">


                    <div class="row">

                        <div class="col-sm-2 col-sm-2"><label style="padding-top:5px;float:right">上传头像</label></div>
                        <div class="col-sm-4 col-sm-4">

                            <div id="div1" class="div1 text-center">
                                <div id="imgShow" class="div2">
                                    <%
                                    if(shop != null && null != shop.getPicUrl()){
                                    %>

                                    <img class='img-circle' style='width:100px;height:100px' src='<%=picPath + shop.getPicUrl() %>'/>
                                    <%
                                    }else{
                                    %>
                                    <a id="img1" style="font-size:30px; !important" href="javascript:void(0)">上传头像</a>
                                    <%
                                    }
                                    %>
                                </div>
                                <!-- <input id="myfile" name="file" type="file" onchange="upload()" class="inputstyle"> -->
                                <input type="file" name="myfile" id="myfile" onchange="upload()" class="inputstyle">
                            </div>

                        </div>
                        <div class="col-sm-2 col-sm-2"></div>
                    </div>
                    <br/>

                    <div class="row" style="margin-top:70px">
                        <div class="col-sm-2 col-md-2 "><label style="float:right">商铺编号</label></div>
                        <div class="col-sm-4 col-md-4"><%= (shop.getId() == null ? "" : shop.getId()) %></div>
                        <div class="col-sm-4 col-md-4"></div>
                    </div>
                    <div class="row" style="margin-top:20px">
                        <div class="col-sm-2 col-md-2 "><label style="padding-top:5px;float:right">商铺名称</label></div>
                        <div class="col-sm-4 col-md-4"><input class="form-control" id="shopName" type="text" value="<%= (shop.getName() == null ? "" : shop.getName()) %>" /></div>
                        <div class="col-sm-4 col-md-4"></div>
                    </div>

                    <br/>
                     <div class="row">
                        <div class="col-sm-2 col-md-2 "><label style="padding-top:5px;float:right">商铺描述</label></div>
                        <div class="col-sm-4 col-md-4">
                            <textarea class="form-control" id="describe" style="width:400px; height:70px;" placeholder="请输入商铺描述" ><%=shop.getDescTxt() == null ? "" : shop.getDescTxt() %></textarea>
                        </div>
                        <div class="col-sm-4 col-md-4"></div>
                    </div>
                      <br/>
                    <div class="row">
                        <div class="col-sm-2 col-md-2 "><label style="padding-top:5px;float:right">经营项目</label></div>
                        <div class="col-sm-4 col-md-4">
                            <textarea class="form-control" id="busiScope" style="width:400px; height:70px;" placeholder="建议您如实填公司经营的项目！" data-ph="建议您如实填写详细收货地址，例如街道名称，门牌号码，楼层和房间号等信息"><%=shop.getBusiScope() == null ? "" : shop.getBusiScope() %></textarea>
                        </div>
                        <div class="col-sm-4 col-md-4"></div>
                    </div>
                    <br/>

                    <div class="row">
                        <div class="col-xs-2 col-md-2 "><label style="padding-top:5px;float:right">商铺地址</label></div>

                        <div class="col-xs-2"><select class="form-control zs-select-input" id='province' onchange='searchCity()'></select></div>
                        <div class="col-xs-2"><select class="form-control zs-select-input" id='city' onchange='searchCounty()'></select></div>
                        <div class="col-xs-2"><select class="form-control zs-select-input" id='county'></select></div>
                        <div class="col-xs-4 col-md-4"></div>
                    </div>
                    <br/>

                    <div class="row">
                        <div class="col-sm-2 col-md-2 "><label style="padding-top:5px;float:right">详细地址</label></div>
                        <div class="col-sm-4 col-md-4">
                            <div class="info">
                                <textarea class="form-control" id="address" style="width:400px; height:70px;" placeholder="建议您如实填公司详细地址，例如街道名称，门牌号码，楼层和房间号等信息"
                                          data-ph="建议您如实填写详细收货地址，例如街道名称，门牌号码，楼层和房间号等信息"><%=shop.getAddress() == null ? "" : shop.getAddress() %></textarea>
                            </div>
                        </div>
                        <div class="col-sm-4 col-md-4"></div>
                    </div>
                    <br/>

                    <div class="row">
                        <div class="col-sm-2 col-md-2 "><label style="padding-top:5px;float:right">客服电话</label></div>
                        <div class="col-sm-4 col-md-4">
                            <input class="form-control" value="<%=shop.getTelephone() == null ? "" : shop.getTelephone() %>" id="telephone" type="text" />
                        </div>
                        <div class="col-sm-4 col-md-4"></div>
                    </div>
                    <br/>

                    <div class="row">
                        <div class="col-sm-2 col-md-2 "><label style="padding-top:5px;float:right">认证状态</label></div>
                        <div class="col-sm-4 col-md-4">
                            <label style="padding-top:5px"><%="1".equals(shop.getIdentification()) ? "审核中" : ""%><%="2".equals(shop.getIdentification()) ? "认证成功" :
                                ""%><%="3".equals(shop.getIdentification()) ? "认证失败" : ""%></label>
                        </div>
                        <div class="col-sm-4 col-md-4"></div>
                    </div>
                    <br/>
                    <div class="row">
                        <div class="col-sm-2 col-md-2 "></div>
                        <div class="col-sm-4 col-md-4">
                            <button id="info_submit" type="button" class="btn btn-default zs-btn-default" style="float:right" onclick="submitShopBaseMsg()">保存</button>
                        </div>
                        <div class="col-sm-4 col-md-4"></div>
                    </div>
                    <br/>

                </div>


                <div id="shopIdentifyFace">

                    <div class="row" style="padding-top:20px;">
                        <div class="col-sm-2 col-md-2 "><label style="padding-top:5px;float:right">商铺门头照片</label></div>
                        <div class="col-sm-4 col-md-4">
                            <div id="div1" class="div1 text-center">
                                <div id="imgShow1" class="div2">
                                    <%
                                    if(p1 != null ){
                                    %>

                                    <img class='img-rounded identify' src='<%=picPath + u1 %>'/>
                                    <%
                                    }else{
                                    %>
                                    <a id="img1" style="font-size:30px; !important" href="javascript:void(0)">上传</a>
                                    <%
                                    }
                                    %>
                                </div>
                                <!-- <input id="myfile" name="file" type="file" onchange="upload()" class="inputstyle"> -->
                               <!--  <input type="file" name="myfile" id="shop_lic" onchange="uploadShopIdentify('shop_lic')" class="inputstyle"/> -->
                            </div>
                        </div>
                        <div class="col-sm-4 col-md-4"></div>
                    </div>
                    <br/>
                    <!-- 代码部分begin -->

                    <div class="row" style="margin-top:80px">
                        <div class="col-sm-2 col-md-2 "><label style="padding-top:5px;float:right">身份证</label></div>
                        <div class="col-sm-4 col-md-4">
                            <div id="div1" class="div1 text-center">
                                <div id="imgShow2" class="div2">
                                    <%
                                    if(p2 != null){
                                    %>

                                    <img class='img-rounded identify' src='<%=picPath + u2 %>'/>
                                    <%
                                    }else{
                                    %>
                                    <a id="img1" style="font-size:30px; !important" href="javascript:void(0)">上传</a>
                                    <%
                                    }
                                    %>
                                </div>
                                <!-- <input id="myfile" name="file" type="file" onchange="upload()" class="inputstyle"> -->
                               <!--  <input type="file" name="myfile" id="shop_tax" onchange="uploadShopIdentify('shop_tax')" class="inputstyle"/> -->
                            </div>
                        </div>
                        <div class="col-sm-4 col-md-4">

                        </div>
                    </div>
                    <br/>

                    <div class="row" style="margin-top:80px">
                        <div class="col-sm-2 col-md-2 "><label style="padding-top:5px;float:right">营业执照</label></div>
                        <div class="col-sm-4 col-md-4">
                            <div id="div1" class="div1 text-center">
                                <div id="imgShow3" class="div2">
                                    <%
                                    if(p3 != null){
                                    %>
                                    <img class='img-rounded identify' src='<%=picPath + u3 %>'/>
                                    <%
                                    }else{
                                    %>
                                    <a id="img1" style="font-size:30px; !important" href="javascript:void(0)">上传</a>
                                    <%
                                    }
                                    %>
                                </div>
                                <!-- <input id="myfile" name="file" type="file" onchange="upload()" class="inputstyle"> -->
                               <!--  <input type="file" name="myfile" id="shop_org" onchange="uploadShopIdentify('shop_org')" class="inputstyle"/> -->
                                
                            </div>
                        </div>
                        <script>
                        	$(".identify").bind("click",function(){
                        		var url = $(this).attr("src");
                        		showDialog(url, "image", "", "");
                        	});
                        </script>
                        <div class="col-sm-4 col-md-4"></div>
                    </div>
                    <input type="hidden" name="type" value="shopI"/>
                    <input type="hidden" name="tokenId" value="<%=tokenId %>"/>

               <!--      <div class="row" style="margin-top:150px">
                        <div class="col-sm-2 col-md-2 "></div>
                        <div class="col-sm-4 col-md-4">
                            <button type="button" class="btn btn-default zs-btn-default" style="float:right" onclick="submitShopIdentifyMsg()">提交</button>
                        </div>
                    </div> -->
                    <div class="col-sm-4 col-md-4"></div>
                </div>
                <br/>

                <div id="printReceiptFace" class="container-fluid" style="padding-top:20px;" hidden>
                    <div class="row" id="printArea">
                        <canvas id="myCanvas" width="860px" height="500px" style="display:none"></canvas>
                        <img id="_image_img">
                    </div>
                    <div class="row" style="width:860px;margin-top:10px">
                        <a style="float: right;" class="btn btn-default zs-btn-default" id="save" onclick="savePrint()" download="山东竞业餐饮服务有限公司保证金收据">保存</a>
                        <a style="float: right;margin-right: 10px;" class="btn btn-default zs-btn-green" id="print" onclick="print()">打印</a>
                        <div class="row" style="margin-top:50px">
                        </div>
                    </div>
                </div>

            </div>

            <input type="hidden" id="tokenId" value="<%=tokenId %>"/>
            <input type="hidden" id="shopId" value="<%=shop.getId() %>"/>
            <input type="hidden" id="picPath" value="<%=shop.getPicUrl() %>"/>

            <input type="hidden" id="region1" value="<%=shop.getRegion1() %>"/>
            <input type="hidden" id="region2" value="<%=shop.getRegion2()  %>"/>
            <input type="hidden" id="region3" value="<%=shop.getRegion3() %>"/>
            <%
            if(shop != null && shop.getName() != null){
            %>
            <input type="hidden" id="shopName" value="<%=shop.getName() %>"/>
            <%
            }
            %>
            <%--
            <script class="resources library" src="<%=basePath %>js/shop/area.js" type="text/javascript"></script>
            --%>
            <%--
            <script class="resources library" src="<%=basePath %>js/admap.js" type="text/javascript"></script>
            --%>

            <script>
                var tokenId = $("#tokenId").val();
                var shopId = $("#shopId").val();
                var shopName = $("#shopName").val();
                $("#shopIdentifyFace").hide();
                var region1 = $("#region1").val();
                var region2 = $("#region2").val();
                var region3 = $("#region3").val();
                $(document).ready(function () {

                    setDefaultSelect(region1, region2, region3);
                });

                function shopBaseMsg() {
                    $("#shopBaseMsgFace").show();
                    $("#shopIdentifyFace").hide();

                    $("#shopBaseMsg").addClass("active");
                    $("#shopIdentify").removeClass("active");

                    $("#printReceiptFace").hide();
                    $("#printReceipt").removeClass("active");
                }

                function shopIdentify() {
                    if (typeof(shopName) != 'undefined') {

                        $("#shopIdentify").addClass("active");
                        $("#shopIdentifyFace").show();

                        $("#shopBaseMsgFace").hide();
                        $("#shopBaseMsg").removeClass("active");

                        $("#printReceiptFace").hide();
                        $("#printReceipt").removeClass("active");
                    } else {
                        alert("先完善商铺基本信息！");
                    }

                }

                function showPrintReceipt() {
                    $("#printReceiptFace").show();
                    $("#printReceipt").addClass("active");

                    $("#shopBaseMsgFace").hide();
                    $("#shopBaseMsg").removeClass("active");

                    $("#shopIdentifyFace").hide();
                    $("#shopIdentify").removeClass("active");
                }


                function submitShopBaseMsg() {
                	if($("#info_submit").hasClass("isLoading")){
                		return;
                	}
                	$("#info_submit").addClass("isLoading").html("loading...");

                    var shopName = $("#shopName").val();
                    var busiScope = $("#busiScope").val();
                    var address = $("#address").val();
                    var telephone = $("#telephone").val();
                    var region1 = $("#province").find("option:selected").val();
                    /* region1 = region1.substr(region1.indexOf("|") + 1); */

                    var region2 = $("#city").find("option:selected").val();
                    /* region2 = region2.substr(region2.indexOf("|") + 1); */
                    var region3 = $("#county").find("option:selected").val();
                    var desc = $("#describe").val();
                    /* region3 = region3.substr(region3.indexOf("|") + 1); */

                    /* var status=$("#workStatus").find("option:selected").val(); */
                    if(!shopName){
                    	showDialog("请输入商铺名称","toast" , "", "");
                    	$("#info_submit").removeClass("isLoading").html("保存");
                    	return;
                    }
                    if(!busiScope){
                    	showDialog("请输入经营项目","toast" , "", "");
                    	$("#info_submit").removeClass("isLoading").html("保存");
                    	return;
                    }
                    if(!region1||region1=="0"){
                    	showDialog("请选择省份","toast" , "", "");
                    	$("#info_submit").removeClass("isLoading").html("保存");
                    	return;
                    }
                    if(!region2||region2=="0"){
                    	showDialog("请选择城市","toast" , "", "");
                    	$("#info_submit").removeClass("isLoading").html("保存");
                    	return;
                    }
                    if(!region3||region3=="0"){
                    	showDialog("请选择区县","toast" , "", "");
                    	$("#info_submit").removeClass("isLoading").html("保存");
                    	return;
                    }
                    if(!address){
                    	showDialog("请输入详细地址","toast" , "", "");
                    	$("#info_submit").removeClass("isLoading").html("保存");
                    	return;
                    }
                    if(!telephone){
                    	showDialog("请输入联系电话","toast" , "", "");
                    	$("#info_submit").removeClass("isLoading").html("保存");
                    	return;
                    }
                    if(!checkDataReg(REG_TEL, telephone)){
                    	showDialog("请输入正确的电话格式","toast" , "", "");
                    	$("#info_submit").removeClass("isLoading").html("保存");
                    	return
                    }
                    var param = {
                    		"id": shopId,
                            "shopName": shopName,
                            "busiScope": busiScope,
                            "descTxt":desc,
                            "region1": region1,
                            "region2": region2,
                            "region3": region3,
                            "address": address,
                            "telephone": telephone,
                    };
                    sendRequest(param, "/bm/shop/saveShopBaseMsg.do", function(json){
                    	$("#info_submit").removeClass("isLoading").html("保存");
                   	 	showDialog("提交成功！","toast","","");
                    }, function(json){
                    	$("#info_submit").removeClass("isLoading").html("保存");
                   	 showDialog(json.header.message,"toast","","");
                    });
                }


                function upload() {
                    var myfile = $("#myfile").val();
                    var path = '<%=picPath%>';
                    if (myfile != "") {
                        $.ajaxFileUpload({
                            url: toServerUrl("/bm/picture/centerPicUpload.do?type=shopHeader&relateId=" + shopId),
                            type: "post",
                            fileElementId: "myfile",
                            dataType: "text",
                            success: function (data) {
                                var text = $(data).html();
                                var obj = JSON.parse(text);
                                var pics = obj.data;
                                for (var i = 0; i < pics.length; i++) {

                                    var d = document.getElementById("div1");

                                    d.style.backgroundColor = "white";
                                    setTimeout(function(){
                                    	 $("#imgShow").html("<img class='img-circle' style='width:100px;height:100px;' src='"+path+ pics[0].url + "'/>");
                                    }, 2000);
                                }
                            }
                        });
                    } else {
                        alert("请至少选择一个文件执行上传操作");
                    }
                }

                function uploadShopIdentify(obj) {
                    var file = "";
                    var url_img = "";
                    if (obj == 'shop_lic') {
                        myfile = $("#shop_lic").val();
                        url_img = 'shop_lic';
                    }
                    if (obj == 'shop_tax') {
                        myfile = $("#shop_tax").val();
                        url_img = 'shop_tax';
                    }
                    if (obj == 'shop_org') {
                        myfile = $("#shop_org").val();
                        url_img = 'shop_org';
                    }


                    if (myfile != "") {
                        $.ajaxFileUpload({
                            url: toServerUrl("/bm/picture/centerPicUpload.do?type=" + obj + "&relateId=" + shopId),
                            type: "post",
                            fileElementId: obj,
                            dataType: "text",
                            success: function (data) {
                                var text = $(data).html();
                                var obj = JSON.parse(text);
                                var pics = obj.data;
                                for (var i = 0; i < pics.length; i++) {

                                    var d = document.getElementById("div1");

                                    d.style.backgroundColor = "white";
                                    if (url_img == 'shop_lic') {

                                        $("#imgShow1").html("<img class='img-rounded'  src='" + picPath + pics[0].url + "'/>");
                                    }
                                    if (url_img == 'shop_tax') {
                                        $("#imgShow2").html("<img class='img-rounded'  src='" + picPath + pics[0].url + "'/>");
                                    }
                                    if (url_img == 'shop_org') {
                                        $("#imgShow3").html("<img class='img-rounded'  src='" + picPath + pics[0].url + "'/>");
                                    }

                                }
                            }
                        });
                    } else {
                        alert("请至少选择一个文件执行上传操作");
                    }
                }

                function submitShopIdentifyMsg() {
                    $.ajax({
                        url: toServerUrl("/bm/shop/submitShopIdentifyMsg.do"),
                        data: {
                            "id": shopId,
                            "identification": 1,
                        },
                        type: "post",
                        dataType: "json",
                        success: function (data) {
                            if (true == data.header.success) {

                                alert("提交申请成功！");
                            } else {
                                alert("提交申请失败！");
                            }
                        }
                    });
                }
                getSAF();

                function getSAF(){
                    sendRequest({"shopId":shopId},"/bm/shop/open/queryById.do",function(json){
                        var saf = "";
                        if(undefined != json.data.assureFee){
                            saf = NumberFormat.formatNumber(json.data.assureFee, 1);
                        }
                        var safTime = json.data.assureFeeTime;
                        if(undefined != safTime && safTime.length>10){
                            safTime = safTime.substr(0,4) +"年"+ safTime.substr(5,2) + "月" + safTime.substr(8,2) + "日";
                        }
                        initPrint(saf, safTime, json.data.assureFeeStatus);// 0未提交保证金 1 已提交保证金 2 已退还保证金
                    });
                }

                function initPrint(saf, safTime, status) {
                    var ctx;
                    var canvas = document.getElementById("myCanvas");

                    if (canvas.getContext) {
                        ctx = canvas.getContext("2d");
                        ctx.strokeStyle = 'black';
                        ctx.strokeRect(10, 90, 840, 300);
                        ctx.fillStyle = 'black';
                        ctx.font = "24px 宋体";
                        ctx.fillText("山东竟业餐饮服务有限公司", 300, 30);
                        ctx.fillText("保证金·收据", 370, 70);
                        ctx.fillText(safTime, 670, 70, 700);
                        ctx.font = "20px 微软雅黑";
                        var text = "今收到 "+shopName+" 入驻餐饮彩购网保证金";;
                        ctx.fillText(text, 100, 150, 700);
//                        text = "入驻餐饮彩购网保证金";
//                        ctx.fillText(text, 100, 180, 700);
                        text = "金额（大写）："+NumberFormat.arabiaToChinese(saf);
                        ctx.fillText(text, 100, 180, 700);
                        text = "（小写）：￥"+saf+"元。";
                        ctx.fillText(text, 140, 210, 700);

                        ctx.fillText("收款单编号：SAF-"+shopId, 100, 270, 700);
                        ctx.font = "16px 微软雅黑";
                        ctx.fillText("注：该收据请收藏或打印，待退出餐饮彩购平台后，凭此退款。", 100, 330, 700);

                        var image = new Image();//创建一张图片
                        image.src = "<%=basePath %>image/official_seal.png";//设置图片的路径
                        image.onload = function () {//当图片加载完成后
                            ctx.globalCompositeOperation = "source-over";
                            ctx.drawImage(image, 340, 30,185,185);
                            ctx.save();

                            var img = document.getElementById("_image_img");
                            img.src = canvas.toDataURL("image/png");
                        };
                    }
                }

                function savePrint() {
                    var save = document.getElementById("save");
                    var img = document.getElementById("_image_img");
                    save.href = img.src;
                }

                function print(){
                    $("#printArea").printArea("","","padding-left:60px;padding-top:30px;");
                }

            </script>
