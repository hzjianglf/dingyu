<%@page import="com.whcyz.model.Account"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<link rel="stylesheet" type="text/css" href="assets/css/dataTables.bootstrap.css">
<script type="text/javascript" language="javascript" src="assets/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" language="javascript" src="assets/js/dataTables.bootstrap.js"></script>
<script type="text/javascript" charset="utf-8">
$(function() {
	//配置弹出框modal
	var modal="#modal";
	var modalTitle="#modalTitle";
	var modalHtml="#modalHtml";
	var modalSubmitBtn="#submitModalBtn";
	
	var linkTable = $('#linkTable').DataTable({
		"oLanguage": {
			"sUrl": "/assets/js/dtcn.json"
		},
		"stateSave": true,
		"processing": true,
		"bProcessing": true,
		"serverSide": true,
		"ajaxSource": "/link/ajax?rand="+Math.random(),
		"columns": [
		    //第一排的序号 
			{
				"searchable": false,
			    "orderable" : false,
			    "sClass": "tcenter",
			    "data" : "index",
			    "defaultContent": "",
			    "targets" : 0
			},
			{ "data": "name", "sClass": "tcenter"},
			{ "data": null, "sClass": "tcenter",
				 "orderable": false,
				"mRender": function (obj) {
					return "<a title='"+obj.url+"' href='"+obj.url+"' target='_blank'>官网</a>";
				}	
			},
			{ "data": "rank", "sClass": "tcenter"},
			{ "data": "createTime", "sClass": "tcenter"},
            //放置最后一排的操作按钮
            {
	            "targets": -1,
	            "data": null,
	            "sClass": "tcenter",
	            "orderable": false,
	            "mRender": function (obj) {
	            	var opt = '<a class="cus_editbtn" objId = "'+obj.id+'"  href="javascript:void(0)" title="修改"><img src="/assets/css/imgs/update.png" align="absmiddle" /></a>&nbsp';
					// 异步删除
            		opt += '<a class="cus_delbtn" objId = "'+obj.id+'" href="javascript:void(0)" title="删除"><img src="/assets/css/imgs/delete.png" align="absmiddle" /></a>&nbsp';
	            	return opt;
	            }
	        }
        ],
        "order": [[ 3, 'asc' ]],	//默认排序行
        "fnRowCallback": function( nRow, aData, iDisplayIndex ) {
			$('td:eq(0)', nRow).html((linkTable.page()*linkTable.page.len())+iDisplayIndex+1);
			return nRow;
		}
	});
	
	//给查询框绑定回车事件
	linkTable.on('init.dt', function (e, settings) {
		$(modalSubmitBtn).unbind("click");
		/* $(".cus_editbtn").unbind("click");
		$(".cus_delbtn").unbind("click");
		$(".cus_contenteditbtn").unbind("click"); */
		$(".dataTables_filter input").unbind();
		$(".dataTables_filter input").attr("placeholder","敲回车查询");
		$(".dataTables_filter input").keyup( function (event) {
			if(event.keyCode == 13){
				linkTable.search($(this).val()).draw();
			}
	    });
	
		$(".linkpage").on("click",'#linkTable tbody tr',function () {
        	linkTable.$('tr.selected').removeClass('selected');
            $(this).addClass('selected');
    });
	
	//删除事件
	$(".linkpage").on("click",'#linkTable .cus_delbtn',function () {
		if(confirm('确定删除?')){
			appLoading("正在删除信息,请稍后...");
			var _this = $(this);
			$.ajax({
				type:"POST",
				url:"/link/delete",
				data: {id:_this.attr("objId")},
				datatype:"json",
				success:function(result){
					if(result.success){
						linkTable.row('.selected').remove().draw( false );
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
		closeWaittingForm("accountForm");
		if(data.success){
			$(modal).modal("hide");
			appSuccessMsg("操作成功", 800);
			setTimeout(function(){
			linkTable.draw( false );
			},800);
		}else{
			appErrorMsg(data.msg==null?data:data.msg, 2000);
		}
	}
	
	$(modalSubmitBtn).click(function () {
		submitMe(function(data){
			modalSubmitCallback(data);
		});
});
	//编辑事件
	$(".linkpage").on("click",".cus_editbtn",function () {
			appLoading("正在执行,请稍后...");
			var _this = $(this);
			$(modalTitle).text("友情链接信息编辑");
			var id=_this.attr("objId");
			var url="/link/edit?id="+id+"&rand="+Math.random();
			$(modalHtml).empty();
			$.get(url,function(data){
				closeAppLoadingNow();
				$(modalHtml).html(data);
				$(modal).modal("show");
				
			});
			
	});
	
	});
	
	
		
});
</script>
<div class="linkpage">
<div class="row ">
	<div class="col-lg-12 tright">
	<div class="btn-group">
		<button class="btn btn-primary cus_editbtn" objId="0">新增友链</button>
	</div>
	</div>
</div>
<div class="hline1"><div></div></div>
<div class="row pt10">
	<div class="col-lg-12">
		<table id="linkTable" cellpadding="0" cellspacing="0" border="0" class="table table-striped table-bordered " id="example">
		<thead>
		  <tr>
			 	<th >序号</th>
				<th>友链名称</th>
				<th>URL</th>
				<th>排序</th>
				<th>收录时间</th>
				<th>操作</th>
			</tr>
		<thead>
		<tfoot>
			<tr>
			 	<th >序号</th>
				<th>友链名称</th>
				<th>URL</th>
				<th>排序</th>
				<th>收录时间</th>
				<th>操作</th>
			</tr>
		</tfoot>
		</table>
	</div>
</div>
</div>