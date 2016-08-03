$(function(){
 $('#ff').hide();
 $('#toolbar').datagrid({
    title:'用户权限管理',
    iconCls:'icon-save',
    //fit:true,
    //fitColumns: true,
    fit:true,
    pageSize:5,
    onDblClickRow : see,
    toolbar:"#toolbar" ,
    pageList:[5,10,15,20],
    nowrap:false,
    striped: true,
    collapsible:true,
    singleSelect:true,
    url:'../../site/manage/manage-role!getRolePageList',
    loadMsg:'数据装载中......',
    sortName:'code',
    sortOrder:'desc',
    remoteSort:false,
    onRowContextMenu:onRowContextMenu,
    columns:[[
     {title:'用户ID',field:'userId',width:fixWidthTable(0.15),rowspan:2,align:'center'},
     {title:'用户姓名',field:'userName',width:fixWidthTable(0.15),rowspan:2,align:'center'},
     {title:'角色',field:'roleNameList',width:fixWidthTable(0.20),rowspan:2,align:'center'},
     {title:'权限',field:'privilegeNameList',width:fixWidthTable(0.45),rowspan:2,align:'left'}
    ]],
    pagination:true,
    rownumbers:true,
    toolbar:[{
       text:'用户角色修改',
       iconCls:'icon-edit',
       handler:judgeGetSelect
     },{
         text:'添加用户',
         iconCls:'icon-add',
         handler:function(){
       	  //判断是否有此权限
        $("#ff3").css("visibility","visible");
       	 var userName=$.cookie('schooloa');
       	 //alert(userName);
       	 if(!userName==""){
       		var privilegeId=52;//需传入
       	 	$.ajax({
       	          type:"POST",
       	         url:'../../site/manage/manage-role!judgePrivilege',
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
       	        		 
       	        		 $('#add').window('open');
          	           	  $('#ff3').show();
          	           	  $('#ff3').form('clear');
          	           	  $('#ff3').appendTo('#aa');
       	        	 }else{
       	        		 alert("你没有此权限")
       	        	 }
       	        	 
       	         }
       	       });
       	 }else{
       		 alert("登录失效，请刷新页面重新登录");
       	 }
       	  
       	  }
        },'-',{
            text:'删除用户',
            iconCls:'icon-remove',
            handler:judgeDel
          },'-', {
              text:'修改密码',
              iconCls:'icon-edit',
              handler:judgeGetSelected
            }
         /* ,'-', {
                text:'用户信息导入',
               // iconCls:'icon-edit',
                handler:judgeUserInput
              },'-', {
                  text:'用户信息导出',
                  //iconCls:'icon-edit',
                  handler:judgeUserOutput
                }*/

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
	function judgeUserInput(){


	 	  //判断是否有此权限
	 	 var userName=$.cookie('schooloa');
	 	 //alert(userName);
	 	 if(!userName==""){
	 		var privilegeId=193;//需传入
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
	 	        		 
	 	        		userInput();
	 	        	 }else{
	 	        		 alert("你没有此权限")
	 	        	 }
	 	        	 
	 	         }
	 	       });
	 	 }else{
	 		 alert("登录失效，请刷新页面重新登录");
	 	 }
	 	  
	 	  

		}
	
	
	function userInput(){
		//alert("此功能还没写完呢");
		 $('#userInput').window('open');
	}

	function judgeUserOutput(){


	 	  //判断是否有此权限
	 	 var userName=$.cookie('schooloa');
	 	 //alert(userName);
	 	 if(!userName==""){
	 		var privilegeId=194;//需传入
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
	 	        		 
	 	        		userOutput();
	 	        	 }else{
	 	        		 alert("你没有此权限")
	 	        	 }
	 	        	 
	 	         }
	 	       });
	 	 }else{
	 		 alert("登录失效，请刷新页面重新登录");
	 	 }
	 	  
	 	  

	}
	
	
function userOutput(){
	//$('#loading').show();
	//$('#userOutput').window('open');
	$.ajax({
        type:"get",
       url:'../../site/manage/manage-role!uploadLeadOut2',
        
       dataType:"text",
       success:function (data){
    	 //  $('#userOutput').window('close');
      	//$('#loading').hide();
       	 parent.window.location.href="/uniqyw/web/images/userinfo.xls";
       },
       error:function(data){
       	alert("服务器正忙，请稍后再试....");
       }
     });
	
}

function leadOut(){
	//alert("hereherhere");
	$("#fileNameOut").val($("#fileLeadOut").val());
	//alert($("#fileNameOut").val());
	var opt = {
			success : showSuccess,
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				alert(" 系统繁忙，请稍后再试...");
			}
		};
		$("#upOut").ajaxSubmit(opt);
}


function uploadFile() { 
	//alert("dfdfd");
	$("#fileName").val($("#fileLead").val());
	//alert($("#fileName").val());
	var opt = {
			
		success : showSuccess,
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert(" 系统繁忙，请稍后再试...");
		}
	};
	$("#up").ajaxSubmit(opt);
	alert("数据导入成功");
	$('#userInput').window('close');
	$('#toolbar').datagrid('reload');
	
}

function showSuccess() {
	alert("数据导入成功");
	$('#userInput').window('close');
	$('#toolbar').datagrid('reload');
}
function see(){
	//alert("ddd");
	  $("#ffsee").css("visibility","visible");
	  var select = $('#toolbar').datagrid('getSelected');
	  if(select){
		  $('#editSee').window('open');
	      $('#ffsee').show();
	      $('#ffsee').appendTo('#eeSee');
		    $('#seeUserName').html(select.userName);
		    $('#seeRole').html(select.roleNameList);
		    $('#seePrivilege').html(select.privilegeNameList);
		    $('#seeUserRealName').html(select.userRealName);
		   id = select.id;
		   }else{
		    $.messager.alert('warning','请选择一行数据','warning');
		   }
	  
	
}
function judgeGetSelected(){

 	  //判断是否有此权限
	
	 $("#fform").css("visibility","visible");
 	 var userName=$.cookie('uniqyw');
 	 //alert(userName);
 	 if(!userName==""){
 		var privilegeId=65;//需传入
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
 	        		 
 	        		getSelected();
 	        	 }else{
 	        		 alert("你没有此权限")
 	        	 }
 	        	 
 	         }
 	       });
 	 }else{
 		 alert("登录失效，请刷新页面重新登录");
 	 }
 	  
 	  
}

function getSelected(){
	   var select = $('#toolbar').datagrid('getSelected');
	   if(select){
		   $('#editPwd').window('open');
	       $('#ff2').show();
	       $('#ff2').appendTo('#editPwd');
	       id=select.userId;
	  	$.ajax({
	          type:"POST",
	         url:'../../site/manage/manage-role!getPasswords',
	          data:{
	          	'userId':id
	          },
	         dataType:"text",
	         success:function (data){
	        	 
	        	
	        	 
	        	 $('#userNameOn').val(select.userName);
	        	 $('#userId').val(select.userId);
	        	
	             
	         }
	       });
	 }else{
	    $.messager.alert('warning','请选择一行数据','warning');
	   }
	  }
		function editPwd(){
		
		    $('#fform').form('submit',{
		    url: '../../site/manage/manage-role!updateUser',
		    onSubmit:function(){
		    	return $('#fform').form('validate');
		    	},
		       success:function(){
		         close2();
		        }
		   });
		   $.messager.alert('close2','密码修改成功了!!!','info',function(){
			   $('#editPwd').window('close');
		    $('#toolbar').datagrid('reload');
		    displayMsg();
		   });
		
		}
		function  closePwd(){
			   $('#editPwd').window('close');
		  }
function judgeDel(){

 	  //判断是否有此权限
 	 var userName=$.cookie('uniqyw');
 	 if(!userName==""){
 		var privilegeId=64;//需传入
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
 	 }else{
 		 alert("登录失效，请刷新页面重新登录");
 		//window.location.replace(document.referrer);
 	 }
 	 //alert(userName);
 	  
 	  
}
function del(){
//删除用户
	 var someData = $('#toolbar').datagrid("getSelections");
	if(someData.length > 0){
		 var aArray = new Array();
		 var strArray="";
		 for(var i=0;i<someData.length;i++){
			 strArray += someData[i].userId+";";
		 }
   $.messager.confirm('warning','确认删除么?',function(id){
	   if(id){
		   $.ajax({
	            type:"POST",
	           url:"../../site/manage/manage-role!delete",
	            data:{
	           	 'aArray':aArray,
	           	 'strArray':strArray
	            },
	           dataType:"text",
	           success:function callback(){
	           	$('#toolbar').datagrid('reload');
	           }
	         });
		   
	   }
	  
   $('#toolbar').datagrid('reload');
  },'flush');
   
  }else{
   $.messager.alert('warning','请选择一行数据','warning');
  }
 
}
function add(){
    $('#ff3').form('submit',{
    url: '../../site/manage/manage-role!saveUser',
    onSubmit:function(){ return $('#ff3').form('validate');},
       success:function(){
         close1();
        }
   });
   $.messager.alert('close1','添加信息成功了!!!','info',function(){
	   $('#add').window('close');
    $('#toolbar').datagrid('reload');
    displayMsg();
   });
  }
function close1(){
	   $('#add').window('close');
	  }
  
function displayMsg(){
	   $('#toolbar').datagrid('getPager').pagination({
		   
		   beforePageText: '第',//页数文本框前显示的汉字  
		     afterPageText: '页    共 {pages} 页',  
		     displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录'
	   });
	  }

  function fixWidthTable(percent){  
	    return $(window).width() * percent;  
	} 
  
  function judgeGetSelect(){

   	  //判断是否有此权限
   	 var userName=$.cookie('schooloa');
   	 //alert(userName);
   	 if(!userName==""){
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
   	        		 
   	        		getSelect();
   	        	 }else{
   	        		 alert("你没有此权限")
   	        	 }
   	        	 
   	         }
   	       });
   	 }else{
   		 alert("登录失效，请刷新页面重新登录");
   	 }
   	  
   	  
  }
  
  
  function getSelect(){
	  alert("ddddddddddddddd");
   $("#jueseedit").css("visibility","visible");
   var select = $('#toolbar').datagrid('getSelected');
   if(select){
	   $('#ff').hide;
    $('#edit').window('open');
    $('#ff').datagrid({
        nowrap:false,
        striped: true,
        collapsible:true,
        width:220,
        singleSelect:true,
        url:'../../site/manage/manage-role!getRoleLists',
        loadMsg:'数据装载中......',
        sortName:'code',
        sortOrder:'desc',
        remoteSort:false,
        columns:[[
         	     {title:'角色ID',field:'roleId',width:'100',rowspan:2,align:'center'},
         	     {title:'用户名称',field:'roleName',width:'100',rowspan:2,align:'center'}
         	    ]]
        
       
       });
    
     $('#ffs').appendTo('#ee');
    id = select.id;
   }else{
    $.messager.alert('warning','请选择一行数据','warning');
   }
  }
  function close2(){
	  $('#edit').window('close');
  }
  
  function edit(){
	  var selected = $('#ff').datagrid('getSelected');
	  roleId=selected.roleId;
	  
	  var selected2 = $('#toolbar').datagrid('getSelected');
	  userId=selected2.userId;
	  $.ajax({
          type:"POST",
         url:'../../site/manage/manage-role!saveRole',
          data:{
          	'myChoice':roleId,
          	'userId':userId
          },
         dataType:"text",
         success:function (data){
        	 alert("角色修改成功");
        	 $('#edit').window('close');	
     	    loadMsg:'更新数据......'
     	    	 $('#toolbar').datagrid("reload");
        	 
     	   
             
         }
       });
	  $('#toolbar').datagrid('reload');
   
   
  }


