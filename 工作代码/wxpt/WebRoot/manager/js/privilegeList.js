$(function(){
 $('#ff').hide();
 $('#toolbar').datagrid({
	 
    title:'角色管理',
    iconCls:'icon-save',
    //fit:true,
    fitColumns: true,
    fit:true,
    pageSize:5,
    pageList:[5,10,15,20],
    nowrap:false,
    striped: true,
    collapsible:true,
    singleSelect:true,
    url:'../../site/manage/manage-privilege!getRp',
    loadMsg:'数据装载中......',
    sortName:'code',
    sortOrder:'desc',
    remoteSort:false,
    columns:[[
      {title:'角色编号',field:'roleId',width:fixWidthTable(0.20),rowspan:2,align:'center'},   
     {title:'角色',field:'roleName',width:fixWidthTable(0.20),rowspan:2,align:'center'},
     {title:'权限列表',field:'realLimitList',width:fixWidthTable(0.60),rowspan:2,align:'left'}
    ]],
    pagination:true,
    rownumbers:true,
    toolbar:"#toolbar" ,
    toolbar:[{
       text:'用户权限修改',
       iconCls:'icon-edit',
       handler:judgeGetSelect3 
       },'-',{
    	 text:'角色添加',
    	 iconCls:'icon-edit',
    	 handler:judgeAdd
     },'-',{
    	 text:'角色删除',
    	 iconCls:'icon-remove',
         handler:judgeDel
     }
    ]
   });
 var p = $('#toolbar').datagrid('getPager');  
 $(p).pagination({  
     pageSize: 5,//每页显示的记录条数，默认为10  
     pageList: [5,10,15,20],//可以设置每页记录条数的列表  
     beforePageText: '第',//页数文本框前显示的汉字  
     afterPageText: '页    共 {pages} 页',  
     displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录'
}); 
  });
function fixWidthTable(percent){  
    return $(window).width() * percent;  
}  

function judgeDel(){


	  //判断是否有此权限
	 var userName=$.cookie('schooloa');
	 //alert(userName);
	  var privilegeId=4;//需传入
	 	$.ajax({
	          type:"POST",
	         url:'../../site/manage/manage-privilege!judgePrivilege',
	          data:{
	          	'userName':userName,
	          	'privilegeId':privilegeId
	          },
	         dataType:"text",
	         success:function (data){
	        	 var MyCookie = $.cookie('canClick');
	        	 //alert(MyCookie);
	        	// alert(MyCookie);
	        	 if(MyCookie=="1"){
	        		 
	        		del();
	        	 }else{
	        		 alert("你没有此权限")
	        	 }
	        	 
	         }
	       });
	  

}

function del(){
	   var selected = $('#toolbar').datagrid('getSelected');
	   if(selected){
	    $.messager.confirm('warning','确认删除么?',function(id){
	    if(id){
	     id = selected.roleId;
	    // alert(id);
	     $.ajax({
	             type:"POST",
	            url:"../../site/manage/manage-privilege!delete",
	             data:{
	            	 'roleId':id
	             },
	            dataType:"xml",
	            success:function callback(){
	            	//$('#toolbar').datagrid('reload');
	            }
	          });
	     

	    }
	    $('#toolbar').datagrid('reload');
	   },'flush');
	    
	   }else{
	    $.messager.alert('warning','请选择一行数据','warning');
	   }
	  }

function judgeAdd(){


	  //判断是否有此权限
	 var userName=$.cookie('schooloa');
	 //alert(userName);
	  var privilegeId=4;//需传入
	 	$.ajax({
	          type:"POST",
	         url:'../../site/manage/manage-privilege!judgePrivilege',
	          data:{
	          	'userName':userName,
	          	'privilegeId':privilegeId
	          },
	         dataType:"text",
	         success:function (data){
	        	 var MyCookie = $.cookie('canClick');
	        	 //alert(MyCookie);
	        	// alert(MyCookie);
	        	 if(MyCookie=="1"){
	        		 
	        		add();
	        	 }else{
	        		 alert("你没有此权限")
	        	 }
	        	 
	         }
	       });
	  

}

function add(){
	  
	  $('#roleAdd').window('open');
	  $('#roleAdd').css("visibility","visible");

	  
}
function judgeGetSelect3(){


	  //判断是否有此权限
	 var userName=$.cookie('schooloa');
	// alert(userName);
	  var privilegeId=4;//需传入
	 	$.ajax({
	          type:"POST",
	         url:'../../site/manage/manage-privilege!judgePrivilege',
	          data:{
	          	'userName':userName,
	          	'privilegeId':privilegeId
	          },
	         dataType:"text",
	         success:function (data){
	        	 var MyCookie = $.cookie('canClick');
	        	// alert(MyCookie);
	        	// alert(MyCookie);
	        	 if(MyCookie=="1"){
	        		 
	        		 getSelect3();
	        	 }else{
	        		 alert("你没有此权限");
	        	 }
	        	 
	         }
	       });
	  

}

function getSelect3Test(){
	
	var selecteds = $('#toolbar').datagrid('getSelected');
	 if(selecteds){
		
		$('#quanxian').window('open');
		// alert("开始");
		$('#ttss').tree({
			url:'../../site/manage/manage-privilege!getPrivileges',
			onBeforeOpen:function(){
				loadMsg:'数据装载中......';
			},
			animate:true,
			checkbox:true
		});
		//alert("正在加载");
		$('#getCheck').css("visibility","visible");
	 }else{
		    $.messager.alert('warning','请选择一行数据','warning');
		   }
}

	function getSelect3(){
		 
		var selecteds = $('#toolbar').datagrid('getSelected');
		 if(selecteds){
			
			$('#quanxian').window('open');
			$('ttss2').tree('reload');
			//alert("正在加载");
			$('#getCheck').css("visibility","visible");
		 }else{
			    $.messager.alert('warning','请选择一行数据','warning');
			   }
}
	
	
	function getChecked(){  
	    var nodes = $('#ttss').tree('getChecked');  
	    var s = '';  
	    for(var i=0; i<nodes.length; i++){  
	        if (s != '') s += ',';  
	        s += nodes[i].id;  
	    }  
	    //alert(s); 
	    var selected = $('#toolbar').datagrid('getSelected');
	    var roleId=selected.roleId;
	    //alert(roleId+"    roleId");
	    $.ajax({
            type:"POST",
            url:"../../site/manage/manage-privilege!updateLimit",
            data:{
           	 'limits':s,
           	 'roleId':selected.roleId
            },
           dataType:"text",
           success:function (data){
        	   alert("权限修改成功");
        	   loadMsg:'更新数据......';
        	   $('#toolbar').datagrid('reload');
           }
         });
	    $('#quanxian').window('close');	
	    
	    
	    
	  


	}  

  
  function displayMsg(){
   $('#toolbar').datagrid('getPager').pagination({displayMsg:'当前显示从{from}到{to}共{total}记录'});
  }
  
  
  function closeAddRole(){
	  $('#roleAdd').window('close');
  }
  function close1(){
   $('#roleAdd').window('close');
  }
  function close2(){
   $('#edit').window('close');
  }
  function close3(){
	  $('#quanxian').window('close');
  }
  
  
  function addRole(){//角色添加  提交数据
	  
    $('#ffRole').form('submit',{
    url: '../../site/manage/manage-privilege!addRole',
    onSubmit:function(){ return $('#ffRole').form('validate');},
       success:function(){
    	   alert("添加信息成功");
    	   $('#roleAdd').window('close');
    	   
         close1();
        }
   });
   $.messager.alert('close1','添加信息成功!!!','info',function(){
	   $('#toolbar').datagrid('reload');
       $('#roleAdd').window('close');
    displayMsg();
   });
  }
  var id;

  function getSelect(){
   var select = $('#toolbar').datagrid('getSelected');
   
   if(select){
	   
    $('#edit').window('open');
   // $('#ff').show();
    $('#ff').appendTo('#ee');
    $('#menuName').val(select.menuName);
    $('#optionName').val(select.optionName);
    $('#optionValue').val(select.optionValue);
    id = select.id;
   }else{
    $.messager.alert('warning','请选择一行数据','warning');
   }
  }
  function edit(){
    $('#ff').form('submit',{
    url: 'datagrid_data2.json',
    onSubmit:function(){ return $('#ff').form('validate');},
       success:function(){
         $.messager.alert('edit','修改信息成功!!!','info');
         close2();
        }
   });
   $('#toolbar').datagrid({
    url:'../../site/manage/manage-privilege!getRp',
    loadMsg:'更新数据......'
   });
   
  }
//  function del(){
//   var selected = $('#privilegeList').datagrid('getSelected');
//   if(selected){
//    $.messager.confirm('warning','确认删除么?',function(id){
//    if(id){
//     id = selected.id;
//     $.ajax({
//             type:"POST",
//            url:"easyDel.action",
//             data:"id="+id,
//            dataType:"xml",
//            success:function callback(){}
//          });
//     $('#privilegeList').datagrid('reload');
//    }
//   });
//   }else{
//    $.messager.alert('warning','请选择一行数据','warning');
//   }
//  }
  
 


  function query(){
  var queryParams = $('#privilegeList').datagrid('options').queryParams;
  queryParams.queryWord = $('#qq').val();
  $('#toolbar').datagrid({
   url:'../../site/manage/manage-privilege!getRp'
  });
  displayMsg();
  $('#query').window('close');
  }
  /**
	 * 扩展树表格级联勾选方法：
	 * @param {Object} container
	 * @param {Object} options
	 * @return {TypeName} 
	
	$.extend($.fn.treegrid.methods,{
		
		 * 级联选择
	     * @param {Object} target
	     * @param {Object} param 
		 *		param包括两个参数:
	     *			id:勾选的节点ID
	     *			deepCascade:是否深度级联
	     * @return {TypeName} 
		 */
//		cascadeCheck : function(target,param){
//			var opts = $.data(target[0], "treegrid").options;
//			if(opts.singleSelect)
//				return;
//			var idField = opts.idField;//这里的idField其实就是API里方法的id参数
//			var status = false;//用来标记当前节点的状态，true:勾选，false:未勾选
//			var selectNodes = $(target).treegrid('getSelections');//获取当前选中项
//			for(var i=0;i<selectNodes.length;i++){
//				if(selectNodes[i][idField]==param.id)
//					status = true;
//			}
//			//级联选择父节点
//			selectParent(target[0],param.id,idField,status);
//			selectChildren(target[0],param.id,idField,param.deepCascade,status);
//			/**
//			 * 级联选择父节点
//			 * @param {Object} target
//			 * @param {Object} id 节点ID
//			 * @param {Object} status 节点状态，true:勾选，false:未勾选
//			 * @return {TypeName} 
//			 */
//			function selectParent(target,id,idField,status){
//				var parent = $(target).treegrid('getParent',id);
//				if(parent){
//					var parentId = parent[idField];
//					if(status)
//						$(target).treegrid('select',parentId);
//					else
//						$(target).treegrid('unselect',parentId);
//					selectParent(target,parentId,idField,status);
//				}
//			}
			/**
			 * 级联选择子节点
			 * @param {Object} target
			 * @param {Object} id 节点ID
			 * @param {Object} deepCascade 是否深度级联
			 * @param {Object} status 节点状态，true:勾选，false:未勾选
			 * @return {TypeName} 
			 */
//			function selectChildren(target,id,idField,deepCascade,status){
//				//深度级联时先展开节点
//				if(!status&&deepCascade)
//					$(target).treegrid('expand',id);
//				//根据ID获取下层孩子节点
//				var children = $(target).treegrid('getChildren',id);
//				for(var i=0;i<children.length;i++){
//					var childId = children[i][idField];
//					if(status)
//						$(target).treegrid('select',childId);
//					else
//						$(target).treegrid('unselect',childId);
//					selectChildren(target,childId,idField,deepCascade,status);//递归选择子节点
//				}
//			}
//		}
//	}); */
