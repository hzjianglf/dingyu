<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<link rel="stylesheet" type="text/css" href="assets/css/dataTables.bootstrap.css">
<script type="text/javascript" language="javascript" src="assets/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" language="javascript" src="assets/js/dataTables.bootstrap.js"></script>
<script type="text/javascript" charset="utf-8">
$(function() {
	//配置弹出框modal  //配置弹出框modal modal是bootstrap打开一个模式窗口插件，所谓模式窗口就是打开子窗口后父窗口内的内容不可以被操作，只能操作子窗口中的内容
	var modal="#modal";
	var modalTitle="#modalTitle";
	var modalHtml="#modalHtml";
	var modalSubmitBtn="#submitModalBtn";
	
	var companyTable = $('#companyTable').DataTable({
		"oLanguage": {
			 "sUrl": "/assets/js/dtcn.json"//从配置文件中读取语言包  默认英文显示 
		},
		"stateSave": true,//开关，是否打开客户端状况记录功能。这个数据是记录在cookies中的，打开了这个记录后，即使刷新一次页面，或从头打开浏览器，之前的状况都是保存下来的
		"processing": true,
		"bProcessing": true,//开关，以指定当正在处理数据的时候，是否显示“正在处理”这个提示信息
		"serverSide": true,//在服务器端整理数据
		"ajaxSource": "/company/ajax?rand="+Math.random(),//使用ajax，在服务器端整理数据
		"columns": [
		    //第一排的序号 
			{
				"searchable": false,//设置列的数据是否过滤
			    "orderable" : false,//设置列是否可以排序
			    "sClass": "tcenter",//设置列的class属性值
			    "data" : "index",//设置单元格里的值
			    "defaultContent": "",//设置列的默认值
			    "targets" : 0//  "targets": 0,//第一列隐藏     "targets": -2,//编辑     "targets": -1,//删除
			},
			{ "data": "industry", "sClass": "tcenter"},
			/* { "data": null, "sClass": "tcenter",
			 	"orderable": false,//设置列是否可以排序
				"mRender": function (obj) {
					return "<a href='company/detail/"+obj.id+"' target='_blank' >"+obj.name+"</a>";
				}	
			}, */
			{ "data": "industry", "sClass": "tcenter"},
			{ "data": "stage", "sClass": "tcenter"},
			{ "data": "foundTime", "sClass": "tcenter"},
			{ "data": "address", "sClass": "tcenter"},
			{ "data": null, "sClass": "tcenter",
				 "orderable": false,
					"mRender": function (obj) {
						return "<a title='"+obj.website+"' href='"+obj.website+"' target='_blank'>官网</a>";
					}	
			},
			{ "data": "rank", "sClass": "tcenter"},
            //放置最后一排的操作按钮
            {
	            "targets": -1,//"targets": -1,//删除
	            "data": null,//设置单元格里的值
	            "sClass": "tcenter",//设置列的class属性值
	            "orderable": false,//设置列是否可以排序
	            "mRender": function (obj) {
	            	var opt = '<a class="cus_editbtn" objId = "'+obj.id+'" permission="'+obj.permission+'" href="javascript:void(0)" title="修改基本信息"><img src="/assets/css/imgs/update.png" align="absmiddle" /></a>&nbsp';
					// 修改简介内容
            		/* opt += '<a class="cus_contenteditbtn" objId = "'+obj.id+'" href="javascript:void(0)" title="修改公司简介"><img src="/assets/css/imgs/info.png" align="absmiddle" /></a>&nbsp'; */
					// 异步删除
            		opt += '<a class="cus_delbtn" objId = "'+obj.id+'" href="javascript:void(0)" title="删除"><img src="/assets/css/imgs/delete.png" align="absmiddle" /></a>&nbsp';
	            	return opt;
	            }
	        }
        ],
        "order": [[ 7, 'asc' ]],	//默认排序行
        "fnRowCallback": function( nRow, aData, iDisplayIndex ) {
			$('td:eq(0)', nRow).html((companyTable.page()*companyTable.page.len())+iDisplayIndex+1);
			return nRow;
		}
	});
	
	//给查询框绑定回车事件
	companyTable.on('init.dt', function (e, settings) {
		$(modalSubmitBtn).unbind("click");
		/* $(".cus_editbtn").unbind("click");
		$(".cus_delbtn").unbind("click");
		$(".cus_contenteditbtn").unbind("click"); */
		$(".dataTables_filter input").unbind();
		$(".dataTables_filter input").attr("placeholder","敲回车查询");
		$(".dataTables_filter input").keyup( function (event) {
			if(event.keyCode == 13){
				companyTable.search($(this).val()).draw();
			}
	    });
		
		$(".companypage").on("click",'table tr',function () {
        	companyTable.$('tr.selected').removeClass('selected');
            $(this).addClass('selected');
    });
	
	//删除事件
	$(".companypage").on("click",'#companyTable .cus_delbtn',function () {
		if(confirm('确定删除?')){
			appLoading("正在删除信息,请稍后...");
			var _this = $(this);
			$.ajax({
				type:"POST",
				url:"/company/delete",
				data: {id:_this.attr("objId")},
				datatype:"json",
				success:function(result){
					if(result.success){
						companyTable.row('.selected').remove().draw( false );
						closeAppLoading();
					}else{
						appErrorMsg(result.msg==null?result:result.msg,1000);
					}
				}
			});
		}else{
			closeAppLoadingNow();
		}
	});
	function modalSubmitCallback(data){
		closeWaittingForm("companyForm");
		if(data.success){
			$(modal).modal("hide");
			appSuccessMsg("操作成功", 800);
			setTimeout(function(){
			companyTable.draw( false );
			},800);
		}else{
			appErrorMsg(data.msg==null?data:data.msg, 2000);
		}
	}
	
	
	$(modalSubmitBtn).click(function(){
		submitMe(function(data){
			modalSubmitCallback(data);
		});
	});
	//编辑
	$(".companypage").on("click",".cus_editbtn",function () {
			appLoading("正在执行,请稍后...");
			var _this = $(this);
			$(modalTitle).text("变电站基本信息编辑");
			var id=_this.attr("objId");
			var url="/company/edit?id="+id+"&rand="+Math.random();
			$(modalHtml).empty();
			$.get(url,function(data){
				closeAppLoadingNow();
				$(modalHtml).html(data);
				$(modal).modal("show");
				
			});
			
	});
	
	//编辑简介
	$(".companypage").on("click",".cus_contenteditbtn",function () {
			appLoading("正在执行,请稍后...");
			var _this = $(this);
			var id=_this.attr("objId");
			var url="/company/editContent?id="+id+"&rand="+Math.random();
			 window.location.hash=url;
			    $.get(url,function(data,status){
			    	closeAppLoading();
			    	$("#mainContainer").empty();
			    	if(status=="success"){
			    		$("#mainContainer").html(data);
			    	}else{
			    		alert("读取失败！");
			    	}
			    });
			
	});
	
	});
	
});
</script>
<div class="companypage">
<div class="row ">
	<div class="col-lg-12 tright">
	<div class="btn-group">
		<button class="btn btn-primary cus_editbtn" objId="0">新增变电站</button>
	</div>
	</div>
</div>
<div class="hline1"><div></div></div>
<div class="row pt10">
	<div class="col-lg-12">

	
	
		<table id="companyTable" cellpadding="0" cellspacing="0" border="0" class="table table-striped table-bordered " id="example">
		<thead>
		<tr>
			 	<th >序号</th>
				<th>电压等级</th>
				<th>变电站</th>
				<th>所属主站</th>
				<th>建立时间</th>
				<th>地址</th>
				<th>官网</th>
				<th>排序</th>
				<th>操作</th>
			</tr>
		<thead>
		<tfoot>
			<tr>
			 	<th >序号</th>
				<th>电压等级</th>
				<th>变电站</th>
				<th>所属主站</th>
				<th>建立时间</th>
				<th>地址</th>
				<th>官网</th>
				<th>排序</th>
				<th>操作</th>
			</tr>
		</tfoot>
		</table>
		
	</div>
</div>
</div>