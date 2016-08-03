<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@page import="com.handany.bm.model.BmProduct"%>
<%@page import="java.lang.String"%>
<%@page import="com.github.pagehelper.PageInfo"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%@page import="com.handany.base.common.Constants"%>
<%
String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
+ request.getContextPath() + "/";
String picPath = Constants.IMAGE_SERVER;
String split =Constants.SPLITOR;

String productId = (String)request.getAttribute("productId");

%>
<!DOCTYPE html>
<html lang="zh-CN">

<head>
 <%@include file="/common.jsp" %>

<title>代理商管理</title>
</head>
<body>
<div class="container" style="width:100%;">
	<nav class="navbar navbar-default navbar-fixed-top row" style="border:0" >
	  <ol class="breadcrumb">
		  <li style="*float:left"><a href="#">我的商铺</a></li>
		  <li style="*float:left"><a href="javascript:goProductManage()">商品管理</a></li>
		  <li style="*float:left"><a href="#">添加商品</a></li>
	  </ol>
	</nav>
</div>
	<div class="panel panel-default container rightsidebar_box" >
	<form action="#" role="form" class="form-horizontal " style="padding-top: 70px;">	
			<div class="form-group">
			   <label for="name" class="col-md-2 control-label" >选择分类</label>
				<div class="col-md-2">
					<select class="form-control zs-select-input" id='selectOne' onchange='selectTowSort()'></select>
				</div>
				<div class="col-md-2">
					<select class="form-control zs-select-input" id='selectTwo' onchange='selectThreeSort()'></select>
				</div>
				<div class="col-md-2">
					<select class="form-control zs-select-input" id='selectThree'></select>
				</div>
			</div>

			<div class="form-group">
				<label class="col-md-2 control-label">上传图片</label>	
						
				<div class="col-md-2">	
					
				<div id="div1" class="div1 text-center">
				    <div id="imgShow" class="div2">				    
				    	<a id="img1" style="font-size:30px; !important" href="javascript:void(0)">上传图片</a>		    
				    </div>				    
				    <input type="file" name="myfile" id="myfile" onchange="upload()" class="inputstyle"/>  								    
				</div>									
				</div>
				<div class="col-md-2"></div>
			</div><br/>	
			<div class="container">
				<div class="col-md-offset-2 col-md-8">
					<div class="form-group" id="productImg">
			
					</div>
				</div>
			</div>
			<div class="form-group">
				<label class="col-md-2 control-label">编码</label>

				<div class="col-md-6">
					<input type="text" class="form-control" id="code">
				</div>
			</div>
			<div class="form-group">
				<label class="col-md-2 control-label">标题</label>

				<div class="col-md-6">
					<input type="text" class="form-control" id="name">
				</div>
			</div>
			<div class="form-group">
				<label class="col-md-2 control-label">描述</label>

				<div class="col-md-6">
					<textarea rows="5" class="form-control" id="describe"></textarea>
				</div>
			</div>
			
			<div class="form-group">
				<label class="col-md-2 control-label">型号</label>

				<div class="col-md-8">
					<table class="table table-bordered" id="appendModal">
						<tr>
							<td width="100">型号</td>
							<td width="100">价格</td>
							<td width="100">库存</td>
							<td width="100">重量(kg)</td>						
							<td width="100">操作</td>
						</tr>
						<tr>
							<td><input type="text" class="form-control" name="modal_type"></td>
							<td><input type="text" class="form-control" name="modal_price"></td>
							<td><input type="text" class="form-control" name="modal_number"></td>
							<td><input type="text" class="form-control" name="modal_weight"></td>							
							<td><a class='del_a'>删除</a></td>
						</tr>
					</table>
				</div>
			</div>
			<div class="form-group">
                <div class="col-md-3 col-md-offset-2">
                    <button type="button" class="btn btn-default" onclick="addModal()">+添加型号</button>
                </div>
            </div>
			<div class="form-group">
				<label class="col-md-2 control-label">商品参数</label>

				<div class="col-md-8">
					<table class="table table-bordered" id="appendParameter1">
						<tr>
							<td width="100">名称</td>
							<td width="100">属性</td>							
							<td width="100">操作</td>
						</tr>
						<tr>
							<td><input type="text" name="parameter_name" class="form-control"></td>
							<td><input type="text" name="parameter_attr" class="form-control"></td>							
							<td><a class="del_a">删除</a></td>
						</tr>
					</table>
				</div>
			</div>
            <div class="form-group">
                <div class="col-md-3 col-md-offset-2">
                    <button type="button" class="btn btn-default" onclick="addParameter()">+添加参数</button>
                </div>
            </div>
            <div class="form-group">
                <div class="col-md-3 col-md-offset-5">
                    <button type="button" class="btn btn-danger btn-block" onclick="submitData()">提交</button>
                </div>
            </div>
            </form>
		</div>
<input type="hidden" value="<%=picPath %>" id="picPath" />
<input type="hidden" value="<%=productId %>" id="productId" />
<input type="hidden" value="<%=split %>" id="split" />
<script type="text/javascript">
var picPath = $("#picPath").val();
var productId = $("#productId").val();
var split = $("#split").val();

function goProductManage(){
	window.location=toServerPageUrl("/bm/product/selectByShopId.do");
}
/* 添加型号  */
function addModal(){	
	var html = "";
	html = html + "<tr>";
	html = html + "<td><input type='text' class='form-control' name='modal_type'></td>";
	html = html + "<td><input type='text' class='form-control' name='modal_price'></td>";
	html = html + "<td><input type='text' class='form-control' name='modal_number'></td>";
	html = html + "<td><input type='text' class='form-control' name='modal_weight'></td>";
	html = html + "<td><a  class='append_a' >删除</a></td>";
	html = html + "</tr>";
	$("#appendModal").append(html);
	/* 绑定点击删除事件  */
	$(".append_a").bind("click", function () {		
		
	    var _this = $(this);		    
	  _this.parent().parent().remove();
	    
	});
}
/* 绑定删除  */
$(".del_a").bind("click", function () {			
    var _this = $(this);		    
  _this.parent().parent().remove();
    
});
/* 添加商品参数  */
function addParameter(){
	var html = "";
	html = html + "<tr>";
	html = html + "<td><input type='text' name='parameter_name' class='form-control'></td>";
	html = html + "<td><input type='text' name='parameter_attr' class='form-control'></td>";	
	html = html + "<td><a  class='append_a' >删除</a></td>";
	html = html + "</tr>";	
	$("#appendParameter1").append(html);
	/* 绑定点击删除事件  */
	$(".append_a").bind("click", function () {		
		
	    var _this = $(this);		    
	  _this.parent().parent().remove();
	    
	});
	
}
/* 上传商品图片  */
function upload(){
	var id = $("#productId").val();
	var myfile = $("#myfile").val();  			
	if(myfile!=""){  				
		$.ajaxFileUpload({
			url:toServerUrl("/bm/picture/picUpload.do?type=" + "product&relateId=" + id),
			
			type:"post",
			fileElementId:"myfile",
			dataType:"text",
			success:function(data){   
				
				var text = $(data).html();
				var obj = JSON.parse(text);						
				var pics = obj.data;
			    for(var i = 0; i < pics.length; i ++){					 
					var d = document.getElementById("div1");
					d.style.backgroundColor = "white"; 																			
					$("#productImg").append("<div class='col-md-2'>" + "<img class='img-rounded delImg' data-picId='" + pics[0].id +"' style='width:100px;height:100px' src='"+picPath + pics[0].url +"'/></div>");
					
				} 
			    /* 绑定事件之前先解除绑定  */
			    $(".delImg").unbind("click");
				$(".delImg").bind("click", function () {							
				    var _this = $(this);	
				    var picId = _this.attr("data-picId");					    
			    	if(confirm("确定要删除该图片吗？")){					    	
						$.ajax({
							url:toServerUrl("/bm/picture.do?method=delete"),	
							data:{
								pictureId:picId,
								relateId:productId,
								type:"product",
							},
							type:"post",
							dataType:"json",
							success:function(data){
								if(data.header.success == true){											
									 _this.parent().remove(); 
								}				
							}
						});						
				    }
				});
			    
			}
		});
	}else{
		alert("请至少选择一个文件执行上传操作");
	}
}

$(document).ready(function(){
	/* 初始化商品分类  */
	selectSort(); 
	/* 获取预分配商品id  */
	getProductId();
});

var productId = null;
function getProductId(){
	$.ajax({
		url:toServerUrl("/bm/product/nextProductId.do"),	
		type:"post",
		dataType:"json",
		success:function(data){
			if(data.header.success == true){
				productId = data.productId;
			}
		}
	});
	
	
}

/* 商品一级分类  */
var selectData = null;
function selectSort(){	
	$.ajax({
		url:toServerUrl("/bm/product/classTree.do"),	
		type:"post",
		dataType:"json",
		success:function(data){
			if(data.header.success == true){
				
				var treeOne = data.tree.subNodes;
				selectData = treeOne;
				$("#selectOne").append("<option value='0'>选择分类</option>");
				for(var i = 0; i < treeOne.length; i ++){					
					var element = treeOne[i].element;
					var name = element.name;
					var id = element.id;
					var parentId = element.parentId;					
					$("#selectOne").append("<option value='"+id+"'>"+name+"</option>");
					
				}
			}
		}
	});
}
/* 商品二级分类  */
function selectTowSort(){
	alert(111);
	$("#selectTwo").empty();
	$("#selectThree").empty();
	var selectOne = $("#selectOne").val();	
	var selectTow = null;
	for(var m = 0 ; m < selectData.length; m ++){
		var element1 = selectData[m].element;
		if(element1.id == selectOne){
			var selectTowData = selectData[m].subNodes;			
			if(null != selectTowData && selectTowData.length != 0){
				$("#selectTwo").append("<option value='-1'>请选择分类</option>");
				for(var n = 0; n < selectTowData.length; n ++){
					var element2 = selectTowData[n].element;
					
					var name = element2.name;
					var id = element2.id;
					
					var parentId = element2.parentId;
					
					if(selectOne == parentId){
						$("#selectTwo").append("<option value='"+id+"'>"+name+"</option>");
					}
				}
			}						
		}		
	}	
}
/* 商品三级分类  */
function selectThreeSort(){
	$("#selectThree").empty();
	var selectTwo = $("#selectTwo").val();
	for(var m = 0 ; m < selectData.length; m ++){		
		var selectTowData = selectData[m].subNodes;			
		if(null != selectTowData && selectTowData.length != 0){
			
			for(var n = 0; n < selectTowData.length; n ++){
				var element2 = selectTowData[n];
				var parentId = element2.element.id;
				
				if(selectTwo == parentId){
					var selectThreeData = element2.subNodes;
					$("#selectThree").append("<option value='-1'>请选择分类</option>");
					for(var i = 0; i < selectThreeData.length; i ++){
						var element3 = selectThreeData[i].element;
						var name = element3.name;
						var id = element3.id; 
						
						$("#selectThree").append("<option value='"+id+"'>"+name+"</option>");
					}
				}

			}
		}
	}
	
}
/* 提交 */
function submitData(){
	
	var selectOne = $("#selectOne").val();
	var selectTow = $("#selectTow").val();
	var selectThree = $("#selectThree").val();
	if(null==selectThree || ""==selectThree){
		alert("选择商品分类！");
		return;
	} 
	var code = $("#code").val();
	var name = $("#name").val();
	if(name == null || name == ""){
		alert("标题不能为空");
		return;
	}
	var describe = $("#describe").val();
	var id = $("#productId").val();
	/* 型号 */
	var mts = "";
	var mps = "";
	var mns = "";
	var mws = "";	
	var mids = "";
	var modal_types = document.getElementsByName("modal_type");
	var modal_prices = document.getElementsByName("modal_price");
	var modal_numbers = document.getElementsByName("modal_number");
	var modal_weights = document.getElementsByName("modal_weight");
	if(modal_types.length == 0){
		alert("至少填写一个型号！");
		return;
	}
	
	if(null != modal_types && modal_types.length != 0){
		for(var j = 0; j < modal_types.length; j ++){
			
			if(modal_types[j].value == null || modal_types[j].value == ""){
				alert("第" + (j + 1) +"个型号不能为空！");
				return;
			}
			
			if(modal_prices[j].value == null || modal_prices[j].value == ""){
				alert("第" + (j + 1) +"个价格不能为空！");								
				return;
			}
			if(!isNumber(modal_prices[j].value)){
				alert("第" + (j + 1) +"个价格格式不对：数字！");
				return;
			}
			if(modal_numbers[j].value == null || modal_numbers[j].value == ""){
				alert("第" + (j + 1) +"个库存不能为空！");
				return;
			}
			if(!isNumber(modal_numbers[j].value)){
				alert("第" + (j + 1) +"个库存格式不对：数字！");
				return;
			}
			if(modal_weights[j].value == null || modal_weights[j].value == ""){
				alert("第" + (j + 1) +"个重量不能为空！");
				return;
			}
			if(!isNumber(modal_weights[j].value)){
				alert("第" + (j + 1) +"个重量格式不对：数字！");
				return;
			}
			
			
			mts = mts + modal_types[j].value + split;
			mps = mps + modal_prices[j].value + split;
			mns = mns + modal_numbers[j].value + split;
			mws = mws + modal_weights[j].value + split;	
			mids = mids + "0" + split;
		}
	}
	/* 商品参数  */
	var pns = "";
	var pas = "";	
	var pids = "";
	var parameter_names = document.getElementsByName("parameter_name");
	var parameter_attrs	= document.getElementsByName("parameter_attr");		
	if(null != parameter_names && parameter_names.length != 0){
		for(var i = 0; i < parameter_names.length; i ++){
			
			if(parameter_names[i].value == null || parameter_names[i].value == ""){
				alert("第" + (i + 1) +"个参数名称不能为空！");
				return;
			}
			if(parameter_attrs[i].value == null || parameter_attrs[i].value == ""){
				alert("第" + (i + 1) +"个参数属性不能为空！");
				return;
			}
			
			pns = pns +  parameter_names[i].value + split;			
			pas = pas + parameter_attrs[i].value + split;
			pids = pids + "0" + split;
		}
	}
	/* 提交  */
	$.ajax({
		url:toServerUrl("/bm/product/save.do"),	
		data:{
			code:code,
			descText:describe,
			id:id,
			name:name,
			productClass:selectThree,
			paramIds:pids,
			typeId:mids,
			paramNames:pns,
			paramValues:pas,
			typeName:mts,
			typeNumber:mns,
			typePrice:mps,
			typeWeight:mws,
			status:"T",			
		},
		type:"post",
		dataType:"json",
		success:function(data){
			if(data.header.success == true){
				alert("商品添加成功！");
			}			
		}
	});
	
	
	
}

</script>

</body>
</html>