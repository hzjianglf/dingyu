$(function() {
	$("#toolbar").css("visibility", "visible");

	$('#tt')
			.datagrid(
					{

						title : '栏目设置',
						iconCls : 'icon-save',
						fit : true,
						pagination : true,// 设置分页条显示
						pageSize : 10,
						pageList : [ 5, 10, 15, 20 ],
						nowrap : false,
						striped : true,
						collapsible : true,
						singleSelect : true,
						url : '../site/manage-sheying',
						loadMsg : '数据装载中......',
						sortName : 'code',
						sortOrder : 'desc',
						remoteSort : false,
						columns : [ [
								{
									title : '图片',
									field : 'fansImageValue',
									width : '700',
									align : 'center',
									visibility : 'hidden'
								},
								{
									title : '上传时间',
									field : 'imageUpdateTime',
									width : '250',
									height : '300',
									align : 'center',
									visibility : 'hidden'
								},
								{
									title : '状态',
									field : 'checkState',
									width : '200',
									height : '300',
									align : 'center',
									visibility : 'hidden'
								}/*,
								{
									field : 'Opt',
									title : '<div id=\"option\"><input type=\"checkbox\" name=\"caozuo\" id=\"caozuo\"  onclick=\"selectAll()\" /></div>',
									width : '40',
									height : '300',
									align : 'center',
									formatter : function(value, rec) {
										return "<input type=\"checkbox\" name=\"xuanze\" value="
												+ rec.userName + "  />";
									}
								} */] ],
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
						onDblClickRow : seeImg,
						rownumbers : true,

						toolbar : "#toolbar"
					});
	displayMsg();
});
function displayMsg() {

	$('#tt')
			.datagrid('getPager')
			.pagination(
					{
						pageSize : 10,// 每页显示的记录条数，默认为10
						pageList : [ 5, 10, 15, 20 ],// 可以设置每页记录条数的列表
						beforePageText : "<span style='color:#3967a3'>第</span>",// 页数文本框前显示的汉字
						afterPageText : "<span style='color:#3967a3'>页    共 </span><span style='color:#ff9c00'>{pages}</span> <span style='color:#3967a3'>页</span>",
						displayMsg : "<span style='color:#3967a3'>当前显示 </span><span style='color:#ff9c00'>{from}</span><span style='color:#3967a3'> - </span><span style='color:#ff9c00'>{to}</span> <span style='color:#3967a3'>条记录   共</span> <span style='color:#ff9c00'>{total}</span><span style='color:#3967a3'> 条记录</span>"
					});
}

function seeImg() {
	var select = $('#tt').datagrid('getSelected');
	if (select) {
		$("#showImg").css("visibility", "visible");
		$('#showImg').window('open');
		$("#imgshow").html(select.fansImageValue);
	} else {
		$.messager.alert("消息", "请选择查看的数据");
	}
}
// 审核
function getSelect() {
	var select = $('#tt').datagrid('getSelected');
	if (select) {
		$.ajax({
			url : "../site/manage-sheying!check",
			type : "post",
			datetype : "json",
			data : {
				'fansIamgeId' : select.fansImageId,
				'checkState' : select.state
			},
			success : function(data){
				var str = eval(data);
				$.messager.alert("消息",str.message);
				$('#tt').datagrid('reload');
			},
			error : function(){
				$.messager.alert("消息", "网络异常");
			}
		});
	} else {
		$.messager.alert("消息", "请选择要审核的数据");
	}
}

var s_caocuo = "false";
function selectAll() {
	var checks = document.getElementsByName("xuanze");
	// 测试全选
	var caozuo = $("#caozuo")[0].checked;
	var str = $("#option").html();
	if (s_caocuo == "true") {
		s_caocuo = "false";
		str = "<input type=\"checkbox\" name=\"caozuo\" id=\"caozuo\"  onclick=\"selectAll()\" />";
	} else {
		s_caocuo = "true";
		str = $("#option").html().replace("id=\"caozuo\"",
				"id=\"caozuo\" checked=\"checked\"");

	}
	$("#option").html(str);
	if (str.indexOf("checked") != -1 || str.indexOf("CHECKED") != -1) {
		// checks2[0].checked=true;
		for ( var i = 0; i < checks.length; i++) {
			// alert("已经选择");
			checks[i].checked = true;
		}

	} else {
		for ( var i = 0; i < checks.length; i++) {
			// alert("为选择");
			checks[i].checked = false;
		}
	}

}