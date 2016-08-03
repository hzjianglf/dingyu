<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@page import="com.handany.base.common.ApplicationConfig"%>
<%@page import="com.handany.bm.model.BmContactor"%>
<%@page import="java.lang.String"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String imageServer = ApplicationConfig.getConfig("image_server");
String tokenId = (String)request.getAttribute("tokenId");
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

    <meta content="" name="description"/>
    <meta content="" name="author"/>

    <title>采供网</title>
    <!-- BEGIN GLOBAL MANDATORY STYLES -->
    <%@include file="/common.jsp" %>
    <link rel="stylesheet" href="<%=basePath %>/css/mall.css">
    <link rel="stylesheet" href="<%=basePath %>/css/swiper3.1.0.min.css">
    <script src="<%=basePath %>/js/swiper3.1.0.min.js"></script>
    <script src="<%=basePath %>/js/mallDialog.js"></script>
    <script src="<%=basePath %>js/adRegion.js" type="text/javascript"></script>
    <style>
        .fontypeface a:hover, .fontypeface a:active, .fontypeface a:focus {
            text-decoration: none;
            color: #c60a1e
        }

        .padding-top-bottom {
            padding: 10px 0;
        }

        .padding-left img {
            max-width: 100px;
            max-height: 100px;
        }

        .advertisement, .advertisement .advertisement_adleft, .advertisement .advertisement_adright, .advertisement .advertisement_adright .advertisement_dw_left, .advertisement .advertisement_adright .advertisement_dw_rigt {
            padding: 0 0;
        }

        .advertisement {
            width: 100%;
        }

        .advertisement .advertisement_adright .advertisement_up {
            border-bottom: 0.1em solid #dedfe0
        }

        .advertisement .advertisement_adleft img, .advertisement .advertisement_adright .advertisement_up img, .advertisement .advertisement_adright .advertisement_dw_left img,
        .advertisement .advertisement_adright .advertisement_dw_rigt img {
            width: 100%
        }

        .advertisement .advertisement_adleft {
            border-right: 0.1em solid #dedfe0;
        }

        input[type="file"] {
            display: none;
        }

        .fileBtn {
            font-weight: 300;
            display: block;
            border: 1px solid #cccccc;
            margin-right: 10px;
            height: 46px;
            text-align: center;
            padding-top: 10px;
            margin-top: 5px;
            border-radius: 6px;
        }

        .uploadDiv {
            height: 60px;
        }

        .highlightDiv {
            border: 5px dashed #ff0000 !important;
        }

    </style>
    <script>
        var sizes = ["宽720px，高262px", "宽720px，高262px", "宽720px，高262px",
            "宽450px，高540px", "宽450px，高320px", "宽333px，高320px",
            "宽333px，高320px", "宽360px，高131px", "宽360px，高131px",
            "宽360px，高131px", "宽360px，高131px", "宽720px，高170px"]
        function showPath() {
            $("#filePath").text($("#fileInput").val());
        }

        /**
         * 高亮被选中的区域
         */
        function highlightShowArea() {
            var value = $("#showIndex").val();
            $("div").removeClass("highlightDiv");
            $("." + value).addClass("highlightDiv");

            var selectIndex = $('#showIndex').prop('selectedIndex') - 1;
            $("#imageSize").text(sizes[selectIndex]);
        }

        function uploadImg() {
            var targetType = $('input:radio[name="target"]:checked').val();// 链接类型
            var flag = $("#showIndex").val();// 显示位置
            var targetUrl = $("#targetUrl").val();// 链接URL
            var province = $("#province1").val();// 显示区域省
            var city = $("#city1").val();// 显示区域市
            var region;// 显示区域
            if ("" == flag) {
                new MallDialog("toast", "请选择广告图片显示位置");
                return;
            }
            if ("" == targetType || undefined == targetType || null == targetType) {
                new MallDialog("toast", "请选择链接URL类型");
                return;
            } else if ("" == targetUrl) {
                if ("shop" == targetType) {
                    new MallDialog("toast", "请输入链接商铺ID");
                    return;
                } else {
                    new MallDialog("toast", "请输入链接商品ID");
                    return;
                }
            }
            if ("" == province) {
                new MallDialog("toast", "请选择展示区域");
                return;
            }
            if ("000000" == province || ("" != province && ("" == city || "999999" == city))) {
                region = province;
            } else {
                region = city;
            }
            console.log(province + "  " + city + "  " + flag + " " + targetUrl);
            var params = {flag: flag, itemId: targetUrl, model: "homepage", region: region, type: targetType};
            var imgVal = $("#fileInput").val();
            if (imgVal != "") {
                $.ajaxFileUpload({
                    url: toServerUrl("/bm/advert/save.do?flag=" + flag + "&itemId=" + targetUrl + "&model=homepage&region=" + region + "&type=" + targetType),
                    type: "post",
                    fileElementId: "fileInput",
                    dataType: "text",
                    success: function (data) {
                        $("#filePath").text("");
                        var text = $(data).html();
                        var obj = JSON.parse(text);
                        var pics = obj.data;
                        for (var i = 0; i < pics.length; i++) {
                            var d = document.getElementById("div1");
                            d.style.backgroundColor = "white";
                            $("#imgShow").html("<img class='img-circle' style='width:100px;height:100px' src='" + picPath + pics[0].url + "'/>");

                        }
                    }
                });
            } else {
                alert("请至少选择一个文件执行上传操作");
            }
        }


    </script>
</head>


<div class="container" style="width:100%;">
    <div class="row" style="border:0;margin-top:-10px;">
        <div class="col-sm-12">
            <h3 class="zs-iframe-title">首页广告设置</h3>
        </div>
    </div>
    <ul class="nav nav-tabs">
        <li role="presentation" class="active" id="tab-base"><a
                href="javascript:showContainer('tab-base','baseContainer')">广告设置</a></li>
        <li role="presentation" id="tab-img"><a
                href="javascript:showContainer('tab-img','previewContainer')">分区域预览</a></li>
    </ul>
    <div class="container contentContainer" id="baseContainer">
        <div class="data_content row" style="margin-top:10px;">
            <div class="col-sm-7 form-group-lg">
                <div class="row padding-top-bottom">
                    <div class="col-xs-3 col-md-3"><label style="padding-top:5px;float:right">显示位置</label></div>
                    <div class="col-xs-4 col-md-4">
                        <select class="form-control zs-select-input" id='showIndex' onchange="highlightShowArea()">
                            <option value="">== 请选择 ==</option>
                            <option value="home-banner-1">轮播广告1</option>
                            <option value="home-banner-2">轮播广告2</option>
                            <option value="home-banner-3">轮播广告3</option>
                            <option value="home-a-1">A区广告位1</option>
                            <option value="home-a-2">A区广告位2</option>
                            <option value="home-a-3">A区广告位3</option>
                            <option value="home-a-4">A区广告位4</option>
                            <option value="home-b-1">B区广告位1</option>
                            <option value="home-b-2">B区广告位2</option>
                            <option value="home-b-3">B区广告位3</option>
                            <option value="home-b-4">B区广告位4</option>
                            <option value="home-c-1">C区广告位1</option>
                        </select>
                    </div>
                    <div class="col-xs-4 col-md-4">
                        <span style="vertical-align: middle;font-weight: 500">建议图片大小：<br> <span id="imageSize"></span></span>
                    </div>
                </div>
                <div class="row padding-top-bottom">
                    <div class="col-xs-3 col-md-3 "><label style="padding-top:15px;float:right;">广告图片</label></div>
                    <div class="uploadDiv col-md-9">
                        <input type="file" id="fileInput" accept="image/*" onchange="showPath()" name="fileInput">
                        <label for="fileInput" class="fileBtn col-sm-4">选择图片</label>
                        <span id="filePath" style="line-height: 46px;vertical-align: middle;"></span>
                    </div>
                </div>
                <div class="row padding-top-bottom">
                    <div class="col-xs-3 col-md-3"><label style="padding-top:25px;float:right">链接URL</label></div>
                    <div class="col-xs-8 col-md-8">
                        <div style="margin-bottom: 10px;">
                            <input type="radio" value="shop" id="targetShop" name="target" class="btn">
                            <label for="targetShop">商铺</label>
                            <input type="radio" value="product" id="targetProduct" name="target" class="btn">
                            <label for="targetProduct">商品</label>
                            <input type="radio" value="other" id="targetOther" name="target" class="btn">
                            <label for="targetOther">其它</label>
                        </div>

                        <input type="text" class="form-control" id="targetUrl">
                    </div>
                </div>
                <div class="row padding-top-bottom">
                    <div class="col-xs-3 col-md-3"><label style="padding-top:10px;float:right">展示区域</label></div>
                    <div class="col-xs-4 col-md-4"><select class="form-control zs-select-input" id='province1' onchange='searchCity(1)'></select></div>
                    <div class="col-xs-4 col-md-4"><select class="form-control zs-select-input" id='city1'></select></div>
                </div>
                <div class="row padding-top-bottom">

                    <div class="col-md-offset-3 col-md-8">
                        <button style="float: right" class="btn btn-default zs-btn-default" onclick="uploadImg()">提交</button>
                    </div>
                </div>

            </div>
            <div class="col-sm-5" style="text-align: right">
                <h3>示例</h3>

                <div style="width: 300px;float: right;">
                    <div class="swiper-container home-banner-1 home-banner-2 home-banner-3" style="margin-top:20px;">
                        <div class="swiper-wrapper">
                            <div class="swiper-slide" id="swiper-slidea">
                                <div data-swiper-parallax="-100"><img style="width:100%;height: 100%;" class="img_banner" src="<%=basePath %>/image/image_banner.png"></div>
                            </div>
                            <div class="swiper-slide" id="swiper-slideb">
                                <div data-swiper-parallax="-100"><img style="width:100%;height: 100%;" class="img_banner" src="<%=basePath %>/image/image_banner_moren.png"></div>
                            </div>
                            <div class="swiper-slide" id="swiper-slidec">
                                <div data-swiper-parallax="-100"><img style="width:100%;height: 100%;" class="img_banner" src="<%=basePath %>/image/image_banner_mor.png"></div>
                            </div>
                        </div>
                        <div id="bannerpagination1" class="swiper-pagination"><span class="swiper-pagination-switch swiper-visible-switch swiper-active-switch"></span><span
                                class="swiper-pagination-switch"></span></div>
                    </div>

                    <div class="advertisement container">
                        <div class="advertisement_adleft col-xs-6 home-a-1"><a href="#"><img style="width:100%;height: 100%;" src="<%=basePath %>/image/imgae_a.png"/></a></div>
                        <div class="advertisement_adright col-xs-6">

                            <div class="advertisement_up home-a-2"><a href="#"><img style="width:100%;height: 100%;" src="<%=basePath %>/image/image-2.png"/></a></div>
                            <div class="advertisement_dw">
                                <div class="advertisement_dw_left col-xs-6 home-a-3"><a href="#"><img style="width:100%;height: 100%;" src="<%=basePath %>/image/image-4.png"/></a></div>
                                <div class="advertisement_dw_rigt col-xs-6 home-a-4"><a href="#"><img style="width:100%;height: 100%;" src="<%=basePath %>/image/image-3.png"/></a></div>
                            </div>
                        </div>
                    </div>
                    <div class="container advertisement" style="background-color:#fff; border:#ddd solid 1px; margin-top:10px;">
                        <div class="row ">
                            <div class="col-xs-6 home-b-1" style="border-right:1px solid #ddd;padding-right: 0px;">
                                <img style="width:100%; height:auto;" src="<%=basePath %>/image/image_banner.png">
                            </div>
                            <div class="col-xs-6 home-b-2" style="padding-left: 0px;">
                                <img style="width:100%; height:auto;" src="<%=basePath %>/image/image_banner_moren.png">
                            </div>
                        </div>
                    </div>
                    <div class="container advertisement" style="background-color:#fff; border:#ddd solid 1px; border-top:0; margin-bottom:10px;">
                        <div class="row ">
                            <div class="col-xs-6 home-b-3" style="border-right:1px solid #ddd;padding-right: 0px;">
                                <img style="width:100%; height:auto;" src="<%=basePath %>/image/image_banner_mor.png">
                            </div>
                            <div class="col-xs-6 home-b-4" style="padding-left: 0px;">
                                <img style="width:100%; height:auto;" src="<%=basePath %>/image/image_banner.png">
                            </div>
                        </div>
                    </div>
                    <div style="width:100%" class="home-c-1"><img style="width:100%" src="<%=basePath %>/image/image_banner_01.png"></div>
                </div>
            </div>
        </div>
    </div>
    <div class="container contentContainer" id="previewContainer" style="display: none;">
        <div class="row padding-top-bottom">
            <div class="col-xs-2 col-md-2"><label style="padding-top:5px;float:right">请选择展示区域</label></div>
            <div class="col-xs-2 col-md-2"><select class="form-control zs-select-input" id='province2' onchange='searchCity(2)'></select></div>
            <div class="col-xs-2 col-md-2"><select class="form-control zs-select-input" id='city2'></select></div>
            <button class="btn btn-default zs-btn-default" onclick="previewAdvertisement()">预览</button>
        </div>
        <div class="row padding-top-bottom">
            <div class="col-xs-6 ">
                <div style="width: 400px;float: right;">
                    <div class="swiper-container" style="margin-top:20px;">
                        <div class="swiper-wrapper">
                            <div class="swiper-slide" id="swiper-slide1">
                                <div data-swiper-parallax="-100"><img id="home-banner-1" style="width:100%;height: 100%;" class="img_banner"></div>
                            </div>
                            <div class="swiper-slide" id="swiper-slide2">
                                <div data-swiper-parallax="-100"><img id="home-banner-2" style="width:100%;height: 100%;" class="img_banner"></div>
                            </div>
                            <div class="swiper-slide" id="swiper-slide3">
                                <div data-swiper-parallax="-100"><img id="home-banner-3" style="width:100%;height: 100%;" class="img_banner"></div>
                            </div>
                        </div>
                        <div id="bannerpagination" class="swiper-pagination"><span class="swiper-pagination-switch swiper-visible-switch swiper-active-switch"></span><span
                                class="swiper-pagination-switch"></span></div>
                    </div>

                    <div class="advertisement container">
                        <div class="advertisement_adleft col-xs-6"><a href="#"><img id="home-a-1" style="width:100%;height: 100%;"/></a></div>
                        <div class="advertisement_adright col-xs-6">

                            <div class="advertisement_up"><a href="#"><img id="home-a-2" style="width:100%;height: 100%;"/></a></div>
                            <div class="advertisement_dw">
                                <div class="advertisement_dw_left col-xs-6"><a href="#"><img id="home-a-3"style="width:100%;height: 100%;"/></a></div>
                                <div class="advertisement_dw_rigt col-xs-6"><a href="#"><img id="home-a-4"style="width:100%;height: 100%;"/></a></div>
                            </div>
                        </div>
                    </div>
                    <div class="container advertisement" style="background-color:#fff; border:#ddd solid 1px; margin-top:10px;">
                        <div class="row ">
                            <div class="col-xs-6" style="border-right:1px solid #ddd;padding-right: 0px;">
                                <img id="home-b-1" style="width:100%; height:auto;">
                            </div>
                            <div class="col-xs-6" style="padding-left: 0px;">
                                <img id="home-b-2" style="width:100%; height:auto;">
                            </div>
                        </div>
                    </div>
                    <div class="container advertisement" style="background-color:#fff; border:#ddd solid 1px; border-top:0; margin-bottom:10px;">
                        <div class="row ">
                            <div class="col-xs-6" style="border-right:1px solid #ddd;padding-right: 0px;">
                                <img id="home-b-3" style="width:100%; height:auto;">
                            </div>
                            <div class="col-xs-6" style="padding-left: 0px;">
                                <img id="home-b-4" style="width:100%; height:auto;">
                            </div>
                        </div>
                    </div>
                    <div style="width:100%"><img id="home-c-1" style="width:100%"></div>
                </div>
            </div>
        </div>


    </div>
</div>
<script>
    selectProvinceWithIndex(1);
    selectProvinceWithIndex(2);
    showContainer('tab-base', 'baseContainer');
    previewAdvertisement();

    $('input:radio[name="target"]').on("click", function () {
        var targetType = $('input:radio[name="target"]:checked').val();// 链接类型
        if (targetType == "shop") {
            $("#targetUrl").attr("placeholder", "请输入商铺id");
        } else {
            $("#targetUrl").attr("placeholder", "请输入商品id");
        }

    })

    // tab切换
    function showContainer(tab, container) {
        $("[role='presentation']").each(function (i) {
            $(this).removeClass("active");
        });
        $("#" + tab).addClass("active");
        $(".contentContainer").each(function (i) {
            $(this).hide();
        });
        $("#" + container).show();


    }

    // 预览广告
    function previewAdvertisement(){
        var province = $("#province2").val();// 显示区域省
        var city = $("#city2").val();// 显示区域市
        var region = "000000";// 显示区域

        if ("000000" == province || "999999" == city) {
            region = province;
        } else if("" != city && null != city){
            region = city;
        }
        sendRequest({model: "homepage", region: region},"/bm/advert/open/list.do",function(json){
            $(json.list).each(function(i, ite) {
                var picUrl = '<%=imageServer%>'+"/"+ite.picUrl;
                $("img#"+ite.flag).attr("src", picUrl);
                var mySwiper2 = new Swiper('.swiper-container', {
                    autoplay: 1000,//可选选项，自动滑动
                    speed: 1000,
                    loop: true,
                    autoplayDisableOnInteraction: false,

                    // 如果需要分页器
                    pagination: '.swiper-pagination',
                    paginationClickable: true,

                });
            });
        });
    }

    $(document).ready(function(){
        //导航banner
        var mySwiper1 = new Swiper('.swiper-container', {
            autoplay: 1000,//可选选项，自动滑动
            speed: 1000,
            loop: true,
            autoplayDisableOnInteraction: false,

            // 如果需要分页器
            pagination: '.swiper-pagination',
            paginationClickable: true,

        });
    });

</script>
