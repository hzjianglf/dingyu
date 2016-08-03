$(function(){

 $('#toolbar').datagrid({
    title:'用户信息管理',
    iconCls:'icon-save',
    fit:true,
    pageSize:10,
    pageList:[5,10,15,20],
    nowrap:false,
    striped: true,
    collapsible:true,
    singleSelect:false,
    url:'../../site/manage/manage-user!getPageList',
    loadMsg:'数据装载中......',
    sortName:'code',
    sortOrder:'desc',
    remoteSort:false,
    onRowContextMenu:onRowContextMenu,
    columns:[[
     {title:'用户ID',field:'userId',width:fixWidthTable(0.15),rowspan:2,align:'center'},
     {title:'用户姓名',field:'userName',width:fixWidthTable(0.20),rowspan:2,align:'center'}
     /*{title:'用户类别',field:'userType',width:fixWidthTable(0.10),rowspan:2,align:'center'},
     {title:'企业名称',field:'enterpriseName',width:fixWidthTable(0.50),rowspan:2,align:'center'}*/
    ]],
    pagination:true,
    rownumbers:true,
    toolbar:"#toolbar" ,
    toolbar:[

     {
       text:'修改密码',
       iconCls:'icon-edit',
       handler:judgeGetSelect
     },'-',{
       text:'删除用户',
       iconCls:'icon-remove',
       handler:judgeDel
     }
     ,'-',{  
        text:'查找', 
         iconCls:'icon-search', 
         handler:judgeSearch
     } ,'-',{  
        text:'添加宣传用户', 
         iconCls:'icon-search', 
         handler:judgeAddUser
     } 
    ]
   });
 var p = $('#toolbar').datagrid('getPager');  
 $(p).pagination({  
     pageSize: 10,//每页显示的记录条数，默认为10  
     pageList: [5,10,15,20],//可以设置每页记录条数的列表  
     beforePageText: '第',//页数文本框前显示的汉字  
     afterPageText: '页    共 {pages} 页',  
     displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录'
}); 
  });

function judgeSearch(){


 	  //判断是否有此权限
 	 var userName=$.cookie('uniqyw');
 	 //alert(userName);
 	  var privilegeId=196;//需传入
 	 	$.ajax({
 	          type:"POST",
 	         url:'../../site/manage/manage-user!judgePrivilege',
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
 	        		 
 	        		search();
 	        	 }else{
 	        		 alert("你没有此权限");
 	        	 }
 	        	 
 	         }
 	       });
 	  

}

function search(){
	$('#search').window('open');
}
	function search2(){
		//alert($("#userName3").val());
	
		$('#toolbar').datagrid({
		url : '../../site/manage/manage-user!getSearch?userName3='+$("#userName3").val()
	});
	displayMsg();
	$('#search').window('close');
	}
  
  function displayMsg(){
   $('#toolbar').datagrid('getPager').pagination({
	   
	   beforePageText: '第',//页数文本框前显示的汉字  
	     afterPageText: '页    共 {pages} 页',  
	     displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录'
   });
  }
  function close1(){
   $('#add').window('close');
  }
  function close2(){
   $('#edit').window('close');
  }
  
  function judgeAddUser(){


   	  //判断是否有此权限
   	 var userName=$.cookie('uniqyw');
   	 //alert(userName);
   	  var privilegeId=195;//需传入
   	 	$.ajax({
   	          type:"POST",
   	         url:'../../site/manage/manage-user!judgePrivilege',
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
   	        		 
   	        		addUser();
   	        	 }else{
   	        		 alert("你没有此权限");
   	        	 }
   	        	 
   	         }
   	       });
   	  
  
  }
  function addUser(){
	  $('#addUser').window('open');
	  
  }
  function closeAddUser(){
	  $('#addUser').window('close');
  }
  function add(){
	  var nameList = ["jm","ifinding","www","email","bbs","blog","news","api","admin"];
	  var checkIsName=0;
	  var userName=$('#userName').val();
	  $.ajax({
		  async:false,
	    url:'../../site/manage/manage-user!isExistName',
	    type:'post',
	    data:{'userName':userName},
	    //dataType:'json',
	    success:function(data){
	    	for(var i=0;i<nameList.length;i++){
	    		if($('#userName').val()==nameList[i]){
	    			//alert("用户名 "+name+"不允许注册,请换用其他用户名");
	    			checkIsName=2;
	    			return;
	    		}
	    	}
		     if(data==1){
		      //alert("用户名已存在!请输入其他用户名");
		       $("#checkIsName").val("1");//cunzaiwei  1
		       checkIsName=0;
		     }else{
		    	 $("#checkIsName").val("0");//bucunzaiwei  0
		    	 checkIsName=1;
		     }
	    },
	    error:function(){
	      alert("服务器繁忙请稍后");
	      $('#addUser').window('close');
	    }
	  });
	  //alert(checkIsName+"hhhhhhhhhhhhhhhhhhhhh");
	  var password=$('#password').val();
	  var repeatPassword=$('#repeatPassword').val();
	 // alert(password+"   "+repeatPassword);
	  if(checkIsName==1){
		  if(password!=repeatPassword){
			  alert("两次输入的密码不一样，请重新输入");
			  $('#password').val("");
			  $('#repeatPassword').val("");
			  }else{
				  $('#ff3').form('submit',{
					    url: '../../site/manage/manage-user!saveUser',
					    onSubmit:function(){ return $('#ff3').form('validate');},
					       success:function(){
					         close1();
					        }
					   });
					   $.messager.alert('close1','添加信息成功了!!!','info',function(){
						   $('#addUser').window('close');
						   $('#password').val("");
						   $('#userName').val();
						$('#repeatPassword').val("");
					    $('#toolbar').datagrid('reload');
					    displayMsg();
					   }); 
			  }
	  }else if(checkIsName==2){
		  alert("用户名"+$('#userName').val()+"不允许使用!请换其他用户名");
		  $('#password').val("");
		   $('#repeatPassword').val("");
	  }else{
		  alert("用户名已存在!请输入其他用户名");
		  $('#password').val("");
		   $('#repeatPassword').val("");
	  }
	 
	  
    
  }
  
  function judgeGetSelect(){

   	  //判断是否有此权限
   	 var userName=$.cookie('uniqyw');
   	 //alert(userName);
   	  var privilegeId=53;//需传入
   	 	$.ajax({
   	          type:"POST",
   	         url:'../../site/manage/manage-user!judgePrivilege',
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
   	        		 alert("你没有此权限");
   	        	 }
   	        	 
   	         }
   	       });
   	  
  }
  var id;
  function getSelect(){
   var select = $('#toolbar').datagrid('getSelected');
   if(select){
	   $('#edit').window('open');
     //  $('#ff2').show();
       //$('#ff2').appendTo('#edit');
       id=select.userId;
  	$.ajax({
          type:"POST",
         url:'../../site/manage/manage-user!getPasswords',
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
 
  	
  
  function edit(){
	  var passwords=$('#passwords').val();
	  var repeatChange=$('#repeatChange').val();
	  //alert(passwords+"   "+repeatChange);
	  if(passwords!=repeatChange){
		  alert("两次输入的密码不一样，请重新输入");
		  $('#passwords').val("");
		  $('#repeatChange').val("");
		  }else{
			  $('#fform').form('submit',{
				    url: '../../site/manage/manage-user!updateUser',
				    onSubmit:function(){ return $('#fform').form('validate');},
				       success:function(){
				         close2();
				        }
				   });
				   $.messager.alert('close2','密码修改成功了!!!','info',function(){
					   $('#edit').window('close');
				    $('#toolbar').datagrid('reload');
				    displayMsg();
				   });
		  }
	  

	   
	   
	   
	   
//	  var v=$('#userId').val();
//	  var v2=$('#passwords').val();
//	  alert(v2);
//	  $.ajax({
//          type:"POST",
//          url:'../../site/manage/manage-user!updateUser',
//          data:{
//          	'userId':v,
//          	'passwords':v2
//          },
//         dataType:"text",
//         success:function (data){
//        	 
//        	 $.messager.alert('close2','修改信息成功!!!','info');
//             close2();
//        	
//             
//         }
//       });
////    $('#ff2').form('submit',{
////    url: '../../site/manage/manage-user!updateUser',
////    onSubmit:function(){ return $('#ff2').form('validate');},
////       success:function(){
////         $.messager.alert('close2','修改信息成功!!!','info');
////         close2();
////        }
////   });
//    $('#toolbar').datagrid('reload');
//   
  }
  function  closePwd(){
	   $('#edit').window('close');
  }
  function fixWidthTable(percent){  
	    return $(window).width() * percent;  
	} 
  
  function judgeDel(){


   	  //判断是否有此权限
   	 var userName=$.cookie('uniqyw');
   	 //alert(userName);
   	  var privilegeId=54;//需传入
   	 	$.ajax({
   	          type:"POST",
   	         url:'../../site/manage/manage-user!judgePrivilege',
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
           url:"../../site/manage/manage-user!delete",
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
  function flush(){
	  $('#toolbar').datagrid('reload');
  }
  
  
  function query(){
  var queryParams = $('#toolbar').datagrid('options').queryParams;
  queryParams.queryWord = $('#qq').val();
  $('#toolbar').datagrid({
   url:'../../site/manage/manage-user!findSum'
  });
  displayMsg();
  $('#query').window('close');
  }

