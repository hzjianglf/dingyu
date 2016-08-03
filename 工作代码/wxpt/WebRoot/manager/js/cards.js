$(function() {

	$("#ff").css("visibility", "visible");
	$('#ff').hide();
	$('#tt')
			.datagrid(
					{

						title : '获奖幸运粉丝',
						iconCls : 'icon-save',
						fit : true,
						pagination : true,// 设置分页条显示
						pageSize : 10,
						pageList : [ 5, 10, 15, 20 ],
						nowrap : false,
						striped : true,
						collapsible : true,
						singleSelect : true,
						url : '../site/manage-user!queryImage2',
						loadMsg : '数据装载中......',
						sortName : 'code',
						sortOrder : 'desc',
						remoteSort : false,
						onRowContextMenu:onRowContextMenu,
						columns : [ [
								{
									field : 'Opt',
									title : '<div id=\"option\"><input type=\"checkbox\" name=\"caozuo\" id=\"caozuo\"  onclick=\"selectAll()\" /></div>',
									width : 40,
									align : 'center',
									formatter : function(value, rec) {
										return "<input type=\"checkbox\" name=\"xuanze\" value="
												+ rec.cardRecordsId + "  />";
									}
								} ,
								{
									title : '图片',
									field : 'imageTemp',
									width : '410',
									align : 'center',
									visibility : 'hidden'
								},
								
								{
									title : '图片类型',
									field : 'cardTypeDesc',
									width : '420',
									align : 'center',
									visibility : 'hidden'
								},
								{
									title : '图片数目',
									field : 'cardCount',
									width : '340',
									align : 'center',
									visibility : 'hidden'
								} ] ],
						onClickRow : function(value, rec) {
							var str = $("#option").html();
							if (s_caocuo == "true") {
								str = $("#option").html().replace(
										"id=\"caozuo\"",
										"id=\"caozuo\" checked=\"checked\"");
							} else {
								str = "<input type=\"checkbox\" name=\"caozuo\" id=\"caozuo\"  onclick=\"selectAll()\" />";
							}
							$("#option").html(str);
						},
						/*onDblClickRow : function() {
							var selected = $('#tt').datagrid('getSelected');
							if (selected) {
								// window.open("DataView.action?Id="+selected.ID);
								UpdateQuestion();
							}
						},*/
						rownumbers : true,

						toolbar : "#toolbar"
					});
	displayMsg();
});
function displayMsg() {

	$('#tt').datagrid('getPager').pagination({
		pageSize : 10,// 每页显示的记录条数，默认为10
		pageList : [5,10, 15, 20 ],// 可以设置每页记录条数的列表
		beforePageText : "<span style='color:#3967a3'>第</span>",// 页数文本框前显示的汉字
		afterPageText : "<span style='color:#3967a3'>页    共 </span><span style='color:#ff9c00'>{pages}</span> <span style='color:#3967a3'>页</span>",
		displayMsg : "<span style='color:#3967a3'>当前显示 </span><span style='color:#ff9c00'>{from}</span><span style='color:#3967a3'> - </span><span style='color:#ff9c00'>{to}</span> <span style='color:#3967a3'>条记录   共</span> <span style='color:#ff9c00'>{total}</span><span style='color:#3967a3'> 条记录</span>"
	});
}
function getSelect() {
	$("#tt").datagrid("selectRow", 0);
	var select = $('#tt').datagrid('getSelected');
	$('#edit').window('open');
	$('#ff').show();
	$("#edit").css("visibility", "visible");
	$('#ff').appendTo('#ee');
	$('#uploadImage').val(select.uploadImage);
	$('#uploadTime').val(select.uploadTime);
}

function add() {

	var opt = {
		url : "../site/manage-card!getuploadImage",
		success : function(text) {
			$.messager.alert('warning', " 修改成功...");
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			$.messager.alert('warning', " 系统繁忙，请稍后再试...");
		}
	};
	$("#ff").ajaxSubmit(opt);
	close1();
	$('#tt').datagrid('reload');
	
}

function close1() {
	$('#edit').window('close');
	$('#edit1').window('close');
	$('#edit2').window('close');
	
}

function SetPrizeChance(){
	$.ajax({
		type : "POST",
		url : '../site/manage-card!checkMoveTime',
		dataType : "text",
		success : function(data) {
			if(data==0){
				alert("活动已开启不可以再更改");
			}else{
				$('#editPrize').window('open');
				$('#ffPrize').show();
				$("#editPrize").css("visibility", "visible");
				$("#ffPrize").css("visibility", "visible");
				$('#ffPrize').appendTo('#eePrize');
				$("#prizeCountNum").val("0");
			}
		}
	});
}
function setChance(){
	var opt = {
		url : "../site/manage-card!setPrizeChance",
		success : function(text) {
			alert("设置成功");
			$('#tt').datagrid("reload");
			closePrizeChance();
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			$.messager.alert('warning', " 系统繁忙，请稍后再试...");
		}
	};
	$("#ffPrize").ajaxSubmit(opt);
	closePrizeChance();
}
/*中奖概率自定义设置开始*/
function closePrizeChance(){
	$('#editPrize').window('close');
}
function countNum(){
	var a1=$("#onePrizeCount").val();
	
	var a2=$("#twoPrizeCount").val();
	var a3=$("#threePrizeCount").val();
	var a4=$("#fourPrizeCount").val();
	var a5=$("#fivePrizeCount").val();
	var a6=$("#sixPrizeCount").val();
	var a7=$("#sevenPrizeCount").val();
	var a8=$("#eightPrizeCount").val();
	var a9=$("#ninePrizeCount").val();
	var a10=$("#tenPrizeCount").val();
	var a0=Number(a1)+Number(a2)+Number(a3)+Number(a4)
	 +Number(a5)+Number(a6)+Number(a7)+Number(a8)+Number(a9)+Number(a10);
	$("#prizeCountNum").val(a0);
}
/*中奖概率自定义设置结束*/
function close2() {

	$('#p').progressbar('setValue', 100);
	$('#p').progressbar('setValue', 100);
	$('#p').progressbar('setValue', 100);
	$('#p').progressbar('setValue', 100);
	$('#p').progressbar('setValue', 100);
	$('#p').progressbar('setValue', 100);
	$('#p').progressbar('setValue', 100);
	$('#p').progressbar('setValue', 100);
	alert("修改成功");
	// $.messager.alert('warning','修改成功','warning');
	$('#p').progressbar('setValue', 100);
	// alert("close");
	// $('#edit').window('close');
	$('#tt').datagrid('reload');
	window.location.reload();
}