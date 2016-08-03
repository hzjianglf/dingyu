<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@page import="java.lang.String"%>
<%@page import="com.github.pagehelper.PageInfo"%>
<%@page import="com.handany.base.common.ApplicationConfig"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
    //PageInfo<Map> pageInfo = (PageInfo<Map>)request.getAttribute("data");
    //List<Map> list = (List<Map>)pageInfo.getList();

    String promotionId = (String) request.getAttribute("promotionId");
    String tokenId = (String) request.getAttribute("tokenId");
    String imageServer = ApplicationConfig.getConfig("image_server");
%>
<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en" class="no-js">
    <!--<![endif]-->
    <!-- BEGIN HEAD -->
    <head>
        <%@include file="/common.jsp" %>
        <script src="<%=basePath%>js/bootstrap-datepicker.js"></script>
    <link rel="stylesheet" href="<%=basePath %>/css/datepicker.css">
        <script type="text/javascript" src="<%=basePath%>js/admin/regionSearch.js"></script>
    </head>
    <body>
        <div class="container" style="width: 100%;">
            <div class="row" style="border: 0; margin-top: -10px;">
                <div class="col-sm-12">
                    <h3 class="zs-iframe-title">新建活动</h3>
                </div>
            </div>
            <div class="row" style="margin-top:10px;">
		        <div class="col-sm-2"  style="padding-left:0px;text-align:right;">
		            <label for="dateFrom" style=" padding-top:7px;">开始日期:</label>
		        </div>
		        <div class="col-sm-2"  style=" padding-left:0px;">
		            <input type="text" id="dateFrom" class="form-control" style="float:left" readonly>
		        </div>
		        <div class="col-sm-2"  style="padding-left:0px;text-align:right;">
		            <label for="dateTo" style=" padding-top:7px;">结束日期:</label>
		        </div>
		        <div class="col-sm-2"  style=" padding-left:0px;">
		            <input type="text" id="dateTo" class="form-control" style="float:left" readonly>
		        </div>
		    </div>
		    <div class="row" style="margin-top:10px;">
		        <div class="col-sm-2"  style="padding-left:0px;text-align:right;">
		            <label for="qaTime" style=" padding-top:7px;">购买时长:</label>
		        </div>
		        <div class="col-sm-2"  style=" padding-left:0px;">
		            <input type="number" id="qaTime" class="form-control" style="float:left">
		        </div>
		        <div class="col-sm-2"  style="padding-left:0px;text-align:right;">
		            <label for="qaTimeFree" style=" padding-top:7px;">赠送时长:</label>
		        </div>
		        <div class="col-sm-2"  style=" padding-left:0px;">
		            <input type="number" id="qaTimeFree" class="form-control" style="float:left">
		        </div>
		    </div>
            <div class="row" style="margin-top: 10px;">

                <label for="provinceSearch" class="col-sm-2" style="text-align:right; padding-top:7px;">地区:</label>

                <div class="col-xs-2"><select class="form-control zs-select-input" id='provinceSearch' onchange='searchConditionCity()'></select></div>
                <div class="col-xs-2"><select class="form-control zs-select-input" id='citySearch'  onchange='searchConditionCounty()'></select></div>
                <div class="col-xs-2"><select class="form-control zs-select-input" id='countySearch' ></select> </div>
            </div>
            <div class="row" style="margin-top:10px;">
                <div class="col-sm-2"  style="padding-left:0px;text-align:right;">
                    <label for="title" style=" padding-top:7px;">活动标题:</label>
                </div>
                <div class="col-sm-10"  style=" padding-left:0px;">
                    <input type="text" id="title" class="form-control" style="float:left">
                </div>

            </div>
            <div class="row" style="border: 0; margin-top: 10px;">
                <div class="col-sm-2"  style="padding-left:0px;text-align:right;">
                    <label for="content" style=" padding-top:7px;">活动内容:</label>
                </div>
                <div class="col-sm-10">
                    <textarea id="content" name="content" style="width:700px;height:300px;"></textarea>
                </div>

            </div>
            <div class="row" style="border: 0; margin-top: 10px;">

                <div class="col-sm-12" style="margin-top:20px;">
                    <button id="submitBtn" type="button" class="btn btn-default zs-btn-default" style="float:right;">提交审核</button>
                </div>
            </div>
        </div>
        <script>
            window.imageServer = '<%=imageServer %>';
        </script>
        <script charset="utf-8" src="<%=basePath%>kindeditor/kindeditor-all.js"></script>
        <script charset="utf-8" src="<%=basePath%>kindeditor/lang/zh-CN.js"></script>
        <script charset="utf-8" src="<%=basePath%>js/image.js"></script>
        <script>
            KindEditor.ready(function (K) {
                window.editor = K.create('#content',
                        {
                            width: '100%',
                            uploadJson: '<%=basePath%>bm/picture/centerPicUpload.do?tokenId=' + getUrlTokenId(),
                            extraFileUploadParams: {
                                type: 'promotionPic',
                                relateId: '<%=promotionId%>'
                            },
                            items: ['fullscreen', 'undo', 'redo', 'cut', 'copy', 'paste',
                                   'plainpaste', 'wordpaste', '|', 'justifyleft', 'justifycenter', 'justifyright',
                                   'justifyfull', 'insertorderedlist', 'insertunorderedlist', 'indent', 'outdent', '|', 'selectall', '-',
                                   'title', 'fontname', 'fontsize', '|', 'textcolor', 'bgcolor', 'bold',
                                   'italic', 'underline', 'strikethrough', 'removeformat', '|', 'image',
                                   'advtable', 'hr', 'emoticons', 'link', 'unlink', '|', 'about']
                        });
            });
            
            $('#dateFrom').datepicker({format:'yyyy-mm-dd'}).on('changeDate', function(ev){
                $('#dateFrom').datepicker('hide');
            });
            
            $('#dateTo').datepicker({format:'yyyy-mm-dd'}).on('changeDate', function(ev){
                $('#dateTo').datepicker('hide');
            });

            /**过滤HTML标签以及&nbsp;*/
            function removeHTMLTag(str) {
                str = str.replace(/<\/?[^>]*>/g,''); //去除HTML tag
                str = str.replace(/[ | ]*\n/g,'\n'); //去除行尾空白
                //str = str.replace(/\n[\s| | ]*\r/g,'\n'); //去除多余空行
                str=str.replace(/&nbsp;/ig,'');//去掉&nbsp;
                return str;
            }

            /**取得第一张图片的URL*/
            function getFirstImgUrl(html){
                var imageMath = /<img[^>]*src="([^"]*)"[^>]*\/>/;
                var matchs = html.match(imageMath);
                if(null!=matchs && matchs.length > 0){
                	var img = html.match(imageMath)[0];
                	var matchs2 = img.match(/src="([^"]*)"/);
                	if(null!=matchs2 && matchs2.length > 0){
                		return matchs2[0].replace("src=","").replace(/\"/g,"");
                	}else{
                    	return "";
                    }
                }else{
                	return "";
                }
            }

            $("#submitBtn").bind("click", function () {
                // 同步数据后可以直接取得textarea的value
                editor.sync();
                var html = $('#content').val(); // 活动内容
                var promotionId = '<%=promotionId%>';
                var dateFrom = $("#dateFrom").val(); // 活动开始时间
                var dateTo = $("#dateTo").val(); // 活动结束时间
                var title = $("#title").val(); // 活动标题
                var region1 = $("#provinceSearch").val(); // 参加活动的省
                var region2 = $("#citySearch").val(); // 参加活动的市
                var region3 = $("#countySearch").val(); // 参加活动的县区
                var intro = removeHTMLTag(html) + SPECIAL_CHAR +getFirstImgUrl(html) ;// 简介+首张图
                var qaTime = $("#qaTime").val();
                var qaTimeFree = $("#qaTimeFree").val();

                if(checkDataIsNull("dateFrom","请选择活动开始时间","dateFrom")
                        && checkDataIsNull("dateTo","请选择活动结束时间","dateTo")
                        && checkDataIsNull("qaTime","请填写购买答疑时间","qaTime")
                        && checkDataIsNull("qaTimeFree","请填写赠送答疑时间","qaTimeFree")
                        && checkDataIsNull("title","请填写标题","title")
                        && checkDataIsNull("content","活动内容不能为空","html")){
                    var params = {content:html,startTime:dateFrom,endTime:dateTo,
                        intro:intro,name:title,qaTime:qaTime,qaTimeFree:qaTimeFree,
                        region1:region1,region2:region2,region3:region3};
                    alert(intro);
                }


//                sendRequest(params, "/bm/sales_promotion/save.do", function(json){
//
//                },null);
            });

        </script>
    </body>
</html>