<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.lang.String"%>
<%@page import="com.github.pagehelper.PageInfo"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	//PageInfo<Map> pageInfo = (PageInfo<Map>)request.getAttribute("data");
	//List<Map> list = (List<Map>)pageInfo.getList();
	List<Map> list = (List<Map>) request.getAttribute("list");

	String tokenId = (String) request.getAttribute("tokenId");
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

<title></title>
<!-- BEGIN GLOBAL MANDATORY STYLES -->
<%@include file="/common.jsp"%>
    <script src="<%=basePath%>js/modalDialog.js"></script>
<link rel="stylesheet" href="<%=basePath%>/css/custom.css">
</head>
<body>


	<div class="container" style="width: 100%;">
		<div class="row" style="border: 0; margin-top: -10px;">
			<div class="col-sm-12">
				<h3 class="zs-iframe-title">系统参数</h3>
			</div>
		</div>
		<%--<nav class="navbar navbar-default navbar-fixed-top row" style="border:0" >--%>
		<%--<ol class="breadcrumb">--%>
		<%--<li style="*float:left"><a href="#">系统管理员</a></li>--%>
		<%--<li style="*float:left"><a href="#">意见反馈</a></li>	  --%>
		<%--</ol>--%>
		<%--</nav>--%>
		<div class="data_content row" style="margin-top: 10px;">
			<div class="col-sm-12">
				<table class="table table-hover">
					<thead>
						<tr>
							<th width="110">参数名称</th>
							<th width="110">参数值</th>
							<th width="110">操作</th>
						</tr>
					</thead>
					<tbody>

						<%
							if (list != null && list.size() != 0) {
								for (int i = 0; i < list.size(); i++) {
						%>

						<tr
							data_paramId='<%=list.get(i).get("code") == null ? "" : list.get(i).get("code")%>'
							class="item">
							<td align="center" class="item_a"><%=list.get(i).get("name") == null ? "" : list.get(i).get("name")%></td>
							<td align="center" class="item_b"><%=list.get(i).get("value") == null ? "" : list.get(i).get("value")%></td>
							<td align="center"><a class="edit"
								onclick="bindEdit('<%=list.get(i).get("code")%>','<%=list.get(i).get("name")%>','<%=list.get(i).get("value")%>')">编辑</a>
						</tr>
						<%
							}
							}
						%>

					</tbody>
				</table>

			</div>

		</div>
	</div>
	<!-- 编辑物流商 -->
	<div class="modal fade" id="myModal_update" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">系统参数编辑</h4>
				</div>
				<div class="modal-body">
					<form id="update" class="form-horizontal" method="post">
						<div class="form-group">
							<label for="update_companyName" class="col-sm-3 control-label" >参数名称</label>
							<div class="col-sm-5">
								<input type="text" class="form-control" id="param_name" disabled />
							</div>
						</div>
						<div class="form-group">
							<label for="update_companyName" class="col-sm-3 control-label">参数值</label>
							<div class="col-sm-5">
								<input type="text" class="form-control" id="param_value" />
							</div>
						</div>
						<input type="hidden" id="param_code">
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
					<button type="button" class="btn btn-primary" onclick="save()">保存</button>
				</div>
				</form>
			</div>
		</div>
	</div>

	<script>
		function bindEdit(code, name, value) {
			$("#param_name").val(name);
			$("#param_value").val(value);
			$("#param_code").val(code);
			$('#myModal_update').modal('show')

		}
		function save() {
			sendRequest({
				"code" : $("#param_code").val(),
				"value" : $("#param_value").val(),
			}, "/bm/busiParam/save.do", function(json) {
				if(json.header.success == true){
					showDialog("修改成功","toast","","");
					setTimeout(function(){
						  $("#changeIframe",parent.document).attr("src", toServerPageUrl("/bm/busiParam/selectAll.do"));
					},2000);
					$('#myModal_update').modal('hide')
				}else{
					showDialog("保存失败","toast","","");
				}
				plog(json);
			});
		}
		/* 	function bindDelete(){
		 $(".delete").bind("click",function(){
		 var _this = $(this)
		 var code = _this.parents(".item").attr("data_paramId");
		 showDialog("确定要删除此条参数？","confirm",{
		 onOk:function(){
		 sendRequest({}, requestUrl, successFunc, failFunc);
		 }
		 },"")
		 });
		 } */
	</script>

</body>