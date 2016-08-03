<%@page import="com.handany.base.common.ApplicationConfig"%>
<%@page import="com.handany.rbac.common.UserContextManager"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.handany.bm.model.*"%>
<%@page import="java.lang.String"%>
<%@page import="com.github.pagehelper.PageInfo"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

String shopId = UserContextManager.getLoginUser().getGroupId();

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
<meta charset="utf-8" />

<!-- 为了让 IE 浏览器运行最新的渲染模式下，建议将此 <meta> 标签加入到你的页面中：-->
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<!--将下面的 <meta> 标签加入到页面中，可以让部分国产浏览器默认采用高速模式渲染页面：-->
<meta name="renderer" content="webkit">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->

<meta content="" name="description" />
<meta content="" name="author" />

<title>采供网</title>
<script src="<%=basePath%>js/mallDialog.js"></script>
<!-- BEGIN GLOBAL MANDATORY STYLES -->
<%@include file="/common.jsp"%>
<style>
.prodPic {
	height: 100px;
	width: 100px
}

.data-nav {
	float: right
}

.data-nav-info {
	float: left
}

td>a {
	display: block;
}
</style>

<script type="text/javascript">
	function toPage(start){
	
		if(start == undefined || start == null){
			start = 0;
		}
		var status=$("#status").val();
		var orderId = $(".orderId").val();
		//var shopId = $("#shopId").val();
		sendRequest({orderId:orderId,status:status,start:start},"/bm/order/queryAllAppealOrders.do",
				function(json){
					var list = json.data.list;
					$("#data_content").empty();
					
					if(list.length == 0){
						showDialog("暂无申诉","toast","","");
						$("#queryBtn").removeClass("isLoading").html("查询");
						return;
					}
					$(list).each(function(i,item){
						var showStatus = "";
						var operate ="";
						if(item.status=="6"){
							showStatus = "未处理";
							operate = "处理";
						}else if(item.status=="7"){
							showStatus = "已处理";
								operate = "查看";
						}
						$("#data_content").append(
								["    <tr class=\"item\" data_type=\""+item.status+"\" data_id=\""+item.id+"\" data_result=\""+item.appealResult+"\" data_overtime=\""+item.appealOverTime+"\">           ",
								 "			        <td align=\"center\">"+item.id+"</td>",
								 "			        <td align=\"center\">"+item.appealReason+"</td>",
								 "			        <td align=\"center\">"+item.appealTime+"</td>",
								 "			        <td align=\"center\">"+item.appealPhone+"</td> ",
								 "			        <td align=\"center\">"+showStatus+"</td>",
								 "			        <td align=\"center\">",
								 "			       <a  class=\"do_it\" >"+operate+"</a>",
								 "			        </td>",
								 "			      </tr>"].join("")
								);
					});
					bindDetail();
					$("#queryBtn").removeClass("isLoading").html("查询");
					$("#navArea").html(showNav(json.data));
				},
				function() {
					showDialog("查询失败", "toast", "", "");
					$("#queryBtn").removeClass("isLoading").html("查询");
					}
				);
	}
	function bindDetail(){
		$(".do_it").unbind("click");
		$(".do_it").bind("click",function(){
			var _this = $(this);
			var orderId = _this.parents(".item").attr("data_id");
			var type = _this.parents(".item").attr("data_type");
			if(type=="7"){
				var overTime = _this.parents(".item").attr("data_overtime");
				var result = _this.parents(".item").attr("data_result");
			}
			if($("#result").length != 0){
				$("#result").remove();
			}
				var modalHtml = 
					["<div class=\"modal fade\" id=\"result\" tabindex=\"-1\" role=\"dialog\" aria-labelledby=\"myModalLabel\">",
					 "    <div class=\"modal-dialog\" role=\"document\">",
					 "        <div class=\"modal-content\">",
					 "            <div class=\"modal-header\">",
					 "                <button type=\"button\" class=\"close\" onclick=\"closeModel()\" aria-label=\"Close\"><span",
					 "                        aria-hidden=\"true\">&times;</span></button>",
					 "                <h4 class=\"modal-title\" id=\"myModalLabel\">处理结果</h4>",
					 "            </div>",
					 "            <div class=\"modal-body\">",
					 "                <form id=\"update\" class=\"form-horizontal\" method=\"post\">",
					 "                    <div class=\"form-group\">",
					 "                        <label for=\"orderId\" class=\"col-sm-2 control-label\">订单编号</label>",
					 "                        <div class=\"col-sm-8\">",
					 "                            <span type=\"text\" class=\"form-control orderId\" ></span>",
					 "                        </div>",
					 "                    </div>",
					 "                    <div class=\"form-group\" id=\"result_txt_div\">",
					 "                        <label for=\"result_txt\" class=\"col-sm-2 control-label\">处理结果</label>",
					 "                        <div class=\"col-sm-8\">",
					 "                            <textarea class=\"form-control\" rows=\"7\" id=\"result_txt\"",
					 "                                      style=\"font-size: 18px;margin-top: 10px\"></textarea>",
					 "                        </div>",
					 "                    </div>",
		
					 "                    <div class=\"form-group\" id=\"operate_time_div\">",
					 "                        <label for=\"operate_man\" class=\"col-xs-2 control-label\">处理时间</label>",
					 "                        <div class=\"col-sm-8\">",
					 "                            <input type=\"text\" class=\"form-control\" id=\"operate_time\"/>",
					 "                        </div>",
					 "                    </div>", 
					 "                    <div class=\"modal-footer\">",
					 "                        <button type=\"button\" class=\"btn btn-primary commit\" >提交</button>",
					 "                        <button type=\"button\" class=\"btn btn-primary\" onclick=\"closeModel()\">关闭</button>",
					 "                    </div>",
					 "                </form>",
					 "            </div>",
					 "        </div>",
					 "    </div>",
					 "</div>"].join("");
				$("body").append(modalHtml);
				if(type=="6"){
					$("#operate_time_div").hide();
				}else{
					$("#result_txt").val(result);
					$("#operate_time").val(overTime);
					$("#result_txt").attr("disabled","disabled");
					$("#operate_time").attr("disabled","disabled");
					$("#operate_time_div").show();
					$(".commit").hide();
				}
			$("#result").modal("show");
			bindCommit();
		/* 	 $('#dateStr').datepicker({format:'yyyy-mm-dd'}).on('changeDate', function(ev){
			        $('#dateStr').datepicker('hide');
			    }); */
			// $("#dateStr").val(getDate());
			//$(".form_datetime").datetimepicker({format: 'yyyy-mm-dd hh:ii'});
			$("#result").find(".orderId").html(orderId);
		});
	}
	function bindSearch(){
		$("#queryBtn").bind("click",function(){
			if($("#queryBtn").hasClass("isLoading")){
				return;
			}
			$("#queryBtn").addClass("isLoading").html("loading...");
			toPage(0);
		});
	}
	function bindCommit(){
		$(".commit").bind("click",function(){
			var id = $("#result").find(".orderId").html();
			var txt = $("#result_txt").val();
			
			if(!txt){
				showDialog("请输入处理结果","toast","","");
				return;
			}
			sendRequest({appealResult:txt,id:id}, "/bm/order/appealProcess.do", function(json){
				showDialog("提交成功","toast","","");
				$("#result").modal("hide");
				setTimeout(function(){
					toPage(0);
				},1000)
			})
		
		});
	}
	function dimissModal(){
		$("#result").modal("hide");
		toPage(0);
	}
	function closeModel(){
		$("#result").modal("hide");
	}
	 function getDate(){
	        var mydate = new Date();
	        var str = "" + mydate.getFullYear() + "-";
	        str += (mydate.getMonth()+1) + "-";
	        str += mydate.getDate();
	        return str;
	    }
	$(document).ready(function() {
		toPage(0);
		bindSearch();
	});
</script>

</head>
<body>


	<div class="container" style="width: 100%;">

		<div class="row" style="border: 0; margin-top: -10px;">
			<div class="col-sm-12">
				<h3 class="zs-iframe-title">订单申诉</h3>
			</div>
		</div>

		<div class="row search" style="margin-top: 10px;">
			<div class="col-sm-2" style="padding-left: 0px; text-align: right;">
				<label  >订单编号</label>
			</div>
			<div class="col-sm-3" style="padding-left: 0px;">
				<input type="text" class="orderId" class="form-control"
					>
			</div>
			<div class="col-sm-1" style="padding-left: 0px; text-align: right;">
				<label for="pClass" >状态</label>
			</div>
			<div class=" col-sm-3" style="">
				 <select class="form-control zs-select-input" name="status" style="margin-top:-3px" id='status'>
					<option value=''>请选择申诉状态</option>
					<option value='6' selected="selected">未处理</option>
					<option value='7'>已处理</option>
				</select>
			</div>
			<div class=" col-sm-2" align="left">
				<button type="button" class="btn btn-default zs-btn-default "
					id="queryBtn"  style="margin-top:-5px">查询</button>
			</div>

		</div>

	</div>

	<div style="margin-top: 10px;">
		<div class="col-sm-12">
			<table class="table table-hover">
				<thead>
					<tr>
						<th width="100">订单编号</th>
						<th width="300">申诉理由</th>
						<th width="200" >申诉时间</th>
						<th width="100" >联系电话</th>
						<th width="100">状态</th>
						<th width="100">操作</th>
					</tr>
				</thead>
				<tbody id="data_content">



				</tbody>
			</table>
		</div>
		<div class="col-sm-12" id="navArea">
			<nav class="data-nav">
				<ul class="pagination">
					<li class="disabled"><a href="#" aria-label="Previous"> <span
							aria-hidden="true">上一页</span>

					</a></li>
					<li class="disabled"><a href="#" aria-label="Next"> <span
							aria-hidden="true">下一页</span>
					</a></li>
				</ul>
			</nav>

		</div>
	</div>
	<script>
		
	</script>

</body>