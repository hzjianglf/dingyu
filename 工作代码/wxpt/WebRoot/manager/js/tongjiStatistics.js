$(function() {
	$("#toolbar").css("visibility", "visible");

	$('#tt')
			.datagrid(
					{
						onRowContextMenu : onRowContextMenu,
						title : '统计视图',
						iconCls : 'icon-save',
						fitColumns : false,
						fit : true,
						pagination : true,// 设置分页条显示
						pageSize : 10,
						pageList : [ 5, 10, 15, 20 ],
						nowrap : false,
						striped : true,
						collapsible : true,
						singleSelect : true,
						url : '../site/manage-tongji-statistics',
						loadMsg : '数据装载中......',
						sortName : 'code',
						sortOrder : 'desc',
						onRowContextMenu : onRowContextMenu,
						remoteSort : false,
						columns : [ [
								 {
									title : '标题',
									field : 'pageTitle',
									width : '300',
									align : 'center',
									visibility : 'hidden'
								},{
									title : '分享总人数',
									field : 'forwardUserCount',
									width : '100',
									align : 'center',
									visibility : 'hidden'
								}, {
									title : '转发好友总数',
									field : 'sendUserCount',
									width : '230',
									align : 'center',
									visibility : 'hidden'
								}, {
									title : '分享朋友圈总数',
									field : 'sendFriendCount',
									width : '300',
									align : 'center',
									visibility : 'hidden'
								}, {
									title : '分享微博总数',
									field : 'sendWeiboCount',
									width : '300',
									align : 'center',
									visibility : 'hidden'
								}] ],
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