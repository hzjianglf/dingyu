$(function() {
	$("#toolbar").css("visibility", "visible");	
	$("#ff").css("visibility", "visible");
	$('#ff').hide();
	$('#tt')
			.datagrid(
					{

						title : '关键字及对应回复',
						iconCls : 'icon-save',
						fit : true,
						pagination : true,// 设置分页条显示
						pageSize : 10,
						pageList : [ 5, 10, 15, 20 ],
						nowrap : false,
						striped : true,
						collapsible : true,
						singleSelect : true,
						url : '../site/manage-key',
						loadMsg : '数据装载中......',
						sortName : 'code',
						sortOrder : 'desc',
						remoteSort : false,
						onRowContextMenu:onRowContextMenu,
						columns : [ [{
							field : 'Opt',
							title : '<div id=\"option\"><input type=\"checkbox\" name=\"caozuo\" id=\"caozuo\"  onclick=\"selectAll()\" /></div>',
							width : 50,
							align : 'center',
							formatter : function(value, rec) {
								return "<input type=\"checkbox\" name=\"xuanze\" value="
										+ rec.explicitId + "  />";
							}
						} ,
								{
									title : '关键字',
									field : 'keyName',
									width : '120',
									align : 'center',
									visibility : 'hidden'
								},
								{
									title : '消息内容',
									field : 'econtent',
									width : '200',
									align : 'center',
									visibility : 'hidden'
								},
								{
									title : '类型',
									field : 'ekey',
									width : '80',
									align : 'center',
									visibility : 'hidden'
								},
								{
									title : '标题',
									field : 'title',
									width : '200',
									align : 'center',
									visibility : 'hidden'
								},
								{
									title : '概要',
									field : 'einstruction',
									width : '200',
									align : 'center',
									visibility : 'hidden'
								},
								{
									title : '连接',
									field : 'url',
									width : '170',
									align : 'center',
									visibility : 'hidden'
								},
								{
									title : '添加时间',
									field : 'addTime',
									width : '170',
									align : 'center',
									visibility : 'hidden'
								}
								] ],
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

var id = "";

function AddWordexplicit() {
	$("#addexplicit").css("visibility", "visible");
	$('#addexplicit').window('open');
	$('#addExplicit').form('clear');
	$("#addExplicit").css("visibility", "visible");
	$("#addExplicit").show();
	$("#addExplicit").appendTo('#eeXL');
}

function add() {
	var opt = {
		url : "../site/manage-keywordsexplicit!save",
		success : function(text) {
			$.messager.alert('消息',text);
			close2();
			$('#tt').datagrid('reload');
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			$.messager.alert('消息'," 系统繁忙，请稍后再试...");
			close2();
			$('#tt').datagrid('reload');
		}
	};
	$("#addExplicit").ajaxSubmit(opt);

}

function UpdateWordexplicit() {
	
	var select = $('#tt').datagrid('getSelected');
	if (select) {
		id = select.explicitId;
		$("#updateexplicit").css("visibility", "visible");
		$('#updateexplicit').window('open');
		$('#addExplicit').form('clear');
		$("#addExplicit").css("visibility", "visible");
		
		$("#keyName").val(select.keyName);
		$("#keywordID").val(select.keyId);
		
		if(select.ekey=="文本"){
			$("#word1").val(select.econtent);
		}
		if(select.ekey=="音频"){
			$("#vodioTitle").val(select.title);
			$("#vodioValue").val(select.einstruction);
		}
		if(select.ekey=="图文"){
			$("#title").val(select.title);
			$("#imageValue").val(select.einstruction);
			$("#url").val(select.url);
		}
		$("#explicitId").val(id);
		$("#addExplicit").show();
		$("#addExplicit").appendTo('#ee');
	} else {
		$.messager.alert('消息',"请选中一行");
	}
}
function update() {
	var opt = {
			url : "../site/manage-keywordsexplicit!update",
			success : function(text) {
				$.messager.alert('消息',text);
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				$.messager.alert('消息'," 系统繁忙，请稍后再试...");
			}
		};
		$("#addExplicit").ajaxSubmit(opt);
}

function close1() {
	$('#addquestion').window('close');
	$('#add').window('close');
	$('#updatequestion').window('close');
}

// 删除教室信息

function DeleteQuestion() {

	var checks = document.getElementsByName("xuanze");

	var ids = "";
	for ( var i = 0; i < checks.length; i++) {
		if (checks[i].checked == true) {
			ids += checks[i].value + ",";

		}

	}
	if (ids == "") {
		$.messager.alert('消息', '请选中要删除的数据', 'warning');
		return;
	}

	$.messager.confirm('消息', '确认删除么?', function(id) {

		if (id) {
			$.ajax({
				type : "POST",
				url : "../site/manage-key!deletKeywordexplicit",
				data : {
					'explicitIds' : ids
				},
				secureuri : false,// 一般设置为false
				// dataType:"xml",
				success : function() {
					$.messager.alert('消息',"删除成功!");
					$('#tt').datagrid('reload');
					window.location.reload();
				},
				error : function() {
					$.messager.alert('消息', '删除失败!');
					$('#tt').datagrid('reload');
					window.location.reload();
				}
			});
		}
		$('#tt').datagrid('reload');
	});

}
var s_caocuo = "false";
function selectAll() {
	var checks = document.getElementsByName("xuanze");
	// 测试全选
	var str = $("#option").html();
	// alert(s_caocuo);
	// alert(str.indexOf("checked"));//值为-1
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
function changeDiv() {
	if ($("#type").val() == 0) {
		$("#textareaDiv").hide();
		$("#fileDivs").show();
		$("#titleTr").show();
	}
	if ($("#type").val() == 1) {
		$("#textareaDiv").show();
		$("#fileDivs").hide();
		$("#titleTr").hide();
	}
}
function getFileName() {
	$("#vidoName").val($("#vodio").val());
}
function callback(message, success) {
	$.messager.alert('消息',message);
	close();
	$('#tt').datagrid('reload');
	close2();
	//window.location.reload();
}


function queryKeywords() {
	$("#lan").css("visibility", "visible");
	$('#lan').window('open');
}
$(function() {
	$('#la').datagrid({
		iconCls : 'icon-save',
		fit : true,
		pagination : true,// 设置分页条显示
		pageSize : 10,
		pageList : [ 5, 10, 15, 20 ],
		nowrap : false,
		striped : true,
		collapsible : true,
		singleSelect : true,
		url : '../site/manage-key!key',
		loadMsg : '数据装载中......',
		sortName : 'code',
		sortOrder : 'desc',
		remoteSort : false,
		onRowContextMenu:onRowContextMenu,
		columns : [ [ 
		             { field: 'Opt', title: '<div id=\"option_unit\"><input type=\"checkbox\" name=\"caozuo_unit\" id=\"caozuo_unit\"  onclick=\"selectAll_unit()\" /></div>', width:'30', align: 'center',
				         formatter: function (value, rec) {    	 	
				             return "<input type=\"checkbox\" name=\"xuanze_unit\" value="+rec.keywordId+"  />";
				         }
				     },
		      {
			title : '规则',
			field : 'rule',
			width : 240,
			rowspan : 2,
			align : 'center'
		}, {
			title : '内容',
			field : 'keywordcontent',
			width : 240,
			rowspan : 2,
			align : 'center'
		} ] ],

		onClickRow:function(value,rec)
	    {	
	    	
	    	var str= $("#option_unit").html();  	
	    	 if(s_caocuo_unit=="true"){	   		
	   		  str= $("#option_unit").html().replace("id=\"caozuo_unit\"","id=\"caozuo_unit\" checked=\"checked\"");
	   	  }else{  		
	   		  str="<input type=\"checkbox\" name=\"caozuo_unit\" id=\"caozuo_unit\"  onclick=\"selectAll_unit()\" />";
	   	  }
	    	$("#option_unit").html(str);
	    },
	    onDblClickRow : function() {
			var selected = $('#la').datagrid('getSelected');
			if (selected) {
				// window.open("DataView.action?Id="+selected.ID);
				judgeGetSelected();
			}
		},
		rownumbers : true,
		toolbar : "#toolbar1"
	});
	displayMsg1();

});

//全选操作
//全选操作
var s_caocuo_unit="false";
function selectAll_unit(){
	 //alert(str);
	  var checks = document.getElementsByName("xuanze_unit");
	  var str = $("#option_unit").html();
	  //alert(s_caocuo_unit);
	// alert(str.indexOf("checked"));//值为-1
	  if(s_caocuo_unit=="true"){	
		  s_caocuo_unit="false";
		  str="<input type=\"checkbox\" name=\"caozuo_unit\" id=\"caozuo_unit\"  onclick=\"selectAll_unit()\" />";
	  }else{
		  s_caocuo_unit="true";
		str="<input type=\"checkbox\" name=\"caozuo_unit\" id=\"caozuo_unit\" checked=checked  onclick=\"selectAll_unit()\" />";
		 
	  }
	
	  $("#option_unit").html(str);
		  if(str.indexOf("checked")!=-1||str.indexOf("CHECKED")!=-1){		 		 		
			//  checks2[0].checked=true;
		      for(var i=0;i<checks.length;i++){	
		    	  //alert("已经选择");
				   checks[i].checked=true;			
		     } 
		     
		  }else{			  
			  for(var i=0;i<checks.length;i++){		
				  //alert("为选择");
					   checks[i].checked=false;		  
			     } 
		  }
	
}


//关键字功能
//删除操作
function del(){
	
	  var checks = document.getElementsByName("xuanze_unit");
	  var ids="";
	  for(var i=0;i<checks.length;i++){
		   if(checks[i].checked==true){
			 ids+= checks[i].value+",";			
		   }		  
	  }
	  //alert(ids);
	  if(ids==""){
		  $.messager.alert('warning','请选中要删除的数据','warning');
		  return ;
	  }
	  s_caocuo_unit="false";
 var selected = $('#la').datagrid('getSelected');
  $.messager.confirm('warning','确认删除么?',function(id){
   //alert(id);
  	if(id){
   $.ajax({	   
  	    async:false,
           type:"POST",
           url:"../site/manage-key!deletKeywords",
           data:{'idsKeys':ids},
           secureuri : false,//一般设置为false
          //dataType:"xml",
          success:function (data){ 
        	if(data=="2"){
        		$.messager.alert('warning','此关键字正在使用,请先删除该关键字下内容！');	
        		return;
        	}else if(data=="1"){
        		$.messager.alert('warning','删除成功！');
        		$('#la').datagrid('reload');
            	   var item = $('#la').datagrid('getRows');              
                   if (item) {
                       for (var i = item.length - 1; i >= 0; i--) {
                           var index = $('#la').datagrid('getRowIndex', item[i]);
                           $('#la').datagrid('deleteRow', index);
                       }
                   }
               	
               	  $('#la').datagrid('reload');
               	  return;
        	}else if(data=="0"){
        		$.messager.alert('warning',"删除失败");
        		$('#la').datagrid('reload');
        		return;
        	}
          	
          },error:function(){
 	    	 $.messager.alert('warning',"网路异常");
 	    	$('#la').datagrid('reload');
 	    	 return;
 	     }
        });
  	}
 });
  $("#option").html( "\<input type=\"checkbox\" name=\"caozuo_unit\" id=\"caozuo_unit\"  onclick=\"selectAll_unit()\" />");
  
}


function judgeGetSelected(){
	
	var select = $('#la').datagrid('getSelected');
	if(select){
		$('#lan').window('close');
		$("#keyName").val(select.keywordcontent);
		$("#keywordID").val(select.keywordId);
	}
}
function DeleteQuestion() {
	var checks = document.getElementsByName("xuanze");

	var ids = "";
	for ( var i = 0; i < checks.length; i++) {
		if (checks[i].checked == true) {
			ids += checks[i].value + ",";

		}

	}
	if (ids == "") {
		$.messager.alert('消息', '请选中要删除的数据', 'warning');
		return;
	}

	$.messager.confirm('消息', '确认删除么?', function(id) {

		if (id) {
			$.ajax({
				type : "POST",
				url : "../site/manage-key!deletKeywordexplicit",
				data : {
					'explicitIds' : ids
				},
				secureuri : false,// 一般设置为false
				// dataType:"xml",
				success : function() {
					$.messager.alert('消息',"删除成功!");
					$('#tt').datagrid('reload');
					//window.location.reload();
				},
				error : function() {
					$.messager.alert('消息', '删除失败!');
					$('#tt').datagrid('reload');
					//window.location.reload();
				}
			});
		}
		$('#tt').datagrid('reload');
	});

}

function displayMsg1() {

	$('#la').datagrid('getPager').pagination({
		beforePageText : '第',// 页数文本框前显示的汉字
		afterPageText : '页    共 {pages} 页',
		displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录'
	});
}
function openKeywords() {
	$("#ff1").css("visibility", "visible");
	$("#add1").css("visibility", "visible");
	$('#ff1').show();
	$('#ff1').form('clear');
	$('#ff1').appendTo('#aaa');
	$('#add1').window('open');
}
function addKeywords() {
	$.ajax({
		type : "POST",
		url : "../site/manage-keywords!save",
		data : {
			'keywordsContent' : $("#keywordcontent").val(),
			'rule' : $("#rule").val()
		},
		secureuri : false,// 一般设置为false
		// dataType:"xml",
		success : function() {
			$.messager.alert('消息',"添加成功!");
			$('#la').datagrid('reload');
			$('#update1').window('close');
			$('#add1').window('close');
			close1();
		},
		error : function() {
			$.messager.alert('消息', '添加失败!');
			$('#la').datagrid('reload');
			$('#update1').window('close');
			$('#add1').window('close');
			close1();
			
		}
	});
}
function getUpdate() {
	var select = $('#la').datagrid('getSelected');
	if (select) {
		$("#ff1").css("visibility", "visible");
		$("#update1").css("visibility", "visible");
		$('#ff1').show();
		$('#ff1').form('clear');
		$('#ff1').appendTo('#eee');
		$('#update1').window('open');
		$("#rule").val(select.rule);
		$("#rule").val(select.rule);
		$("#keywordcontent").val(select.keywordcontent);
		$("#keywordId").val(select.keywordId);
	} else {
		$.messager.alert('warning', '请选择一行数据', 'warning');
	}
}
function updateKeywords(){
	$.ajax({
		type : "POST",
		url : "../site/manage-keywords!updateKey",
		data : {
			'keywordsContent' : $("#keywordcontent").val(),
			'rule' : $("#rule").val(),
			'keywordId':$("#keywordId").val()
		},
		secureuri : false,// 一般设置为false
		// dataType:"xml",
		success : function() {
			$.messager.alert('消息',"修改成功!");
			$('#la').datagrid('reload');
			close1();
		},
		error : function() {
			$.messager.alert('消息', '修改失败!');
			$('#la').datagrid('reload');
			close1();
		}
	});
}
function close1(){
	$('#update1').window('close');
	$('#add1').window('close');
}
function close2(){
	$('#addexplicit').window('close');
	$('#updateexplicit').window('close');
}