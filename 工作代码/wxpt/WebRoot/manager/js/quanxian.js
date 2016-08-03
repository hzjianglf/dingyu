$(function(){
 $('#ff').hide();
 $('#jiaose').datagrid({ 
    title:'权限角色管理',
    iconCls:'icon-save',
    fit:true,
    //fitColumns: true,
    //width:1000,
    //height:550,
    pageSize:5,
    pageList:[5,10,15,20],
    nowrap:false,
    striped: true,
    collapsible:true,
    singleSelect:true,
    //autoScroll:true,
    url:'/wxpt/site/manage-role!find',
    loadMsg:'数据装载中......',
    sortName:'code',
    sortOrder:'desc',
    remoteSort:false,
    columns:[[
     {title:'角色id',field:'roleId',width:'100',rowspan:2,align:'center',hidden:true},
     /*{title:'部门编号',field:'departmentNumber',width:'150',rowspan:2,align:'center'},*/
     {title:'角色名',field:'roleName',width:'150',rowspan:2,align:'center'},
     {title:'角色说明',field:'roleStatement',width:'100',rowspan:2,align:'center'},
     {title:'权限',field:'privilegeList',width:'900',rowspan:2,align:'center'}
    /*  {title:'部门修改时间',field:'departmentUpdatetime',width:'150',rowspan:2,align:'center'},
     {title:'所属部门',field:'departmentParentName',width:'150',rowspan:2,align:'center'},
     {title:'部门备注',field:'departmentRemark',width:'300',rowspan:2,align:'center'},*/
     /*{ field: 'Opt', title: '<div id=\"option\"><input type=\"checkbox\" name=\"caozuo\" id=\"caozuo\"  onclick=\"selectAll()\" /></div>', width:'80', align: 'center',
         formatter: function (value, rec) {    	 	
             return "<input type=\"checkbox\" name=\"xuanze\" value="+rec.roleId+"  />";
         }
     },*/
     
    ]],
    pagination:true,
    rownumbers : true,
	toolbar : "#quanxian"
   });
   displayMsg();

  });




function displayMsg() {

	$('#jiaose').datagrid('getPager').pagination({
		pageSize : 5,// 每页显示的记录条数，默认为10
		pageList : [5,10,15,20],// 可以设置每页记录条数的列表
		beforePageText : "<span style='color:#3967a3'>第</span>",// 页数文本框前显示的汉字
		afterPageText : "<span style='color:#3967a3'>页    共 </span><span style='color:#ff9c00'>{pages}</span> <span style='color:#3967a3'>页</span>",
		displayMsg : "<span style='color:#3967a3'>当前显示 </span><span style='color:#ff9c00'>{from}</span><span style='color:#3967a3'> - </span><span style='color:#ff9c00'>{to}</span> <span style='color:#3967a3'>条记录   共</span> <span style='color:#ff9c00'>{total}</span><span style='color:#3967a3'> 条记录</span>"
	});
}

//设置权限 的权限验证
function openquanxian(){
	
 	var yonghu = $('#yonghu').val();	
	   $.ajax({
		 secureuri : false,
      type:"POST",
      url:"tree!panduanqiuanxian",
      data:{'yonghu':yonghu,
      	  'gongnengid':43
   	      
     	
      },
    secureuri : false,//一般设置为false
   
     success:function (date){
   	if(date==1){
   	
   		openquanxian2();
		
   	}else if(date==0){
   		
 			$.messager.alert('warning','你没有此权限！');
   	 }
     },error:function callback(date){
  	   if(date==-1){
  		   $.messager.alert('warning','系统错误！');  
  	   }
     }
      
      
   }); 
	
}

//打开权限管理
function openquanxian2(){
	 var select = $('#jiaose').datagrid('getSelected');
	  if(select){
	$('#wc').window('open'); 
	$('#wc').css("visibility",""); 	
	  }else{
    	  $.messager.alert('warning','请选择角色','warning'); 
      }
}

//权限管理
function quanxianguanli(){
	  var nodes = $('#ttwc').tree('getChecked');  
/*if(nodes.length!=0){*/
	
      var s = '';  
      for(var i=0; i<nodes.length; i++){  
          if (s != '') 
        	  s += ',';  
         
     s += nodes[i].id;  
         // s += nodes[i].text2;  
        
      }  
      var select = $('#jiaose').datagrid('getSelected');
      var roleidshezhi = select.roleId;
     
    
	   $.ajax({
           type:"POST",
           url:"tree!shezhiquanxian",
           data:{'cunquanxian':s,
        	      'roleidshezhi':roleidshezhi
          	
           },
           secureuri : false,//一般设置为false
        
          success:function (date){
        	  if(date==1){
        	  $('#wc').window('close'); 
          	$.messager.alert('warning','设置成功');
          
          	$('#jiaose').datagrid('reload');
        //	window.location.reload();
        	  }
          },error:function callback(date){
        	  if(date==-1){
        	  $('#wc').window('close'); 
		    $.messager.alert('warning','设置失败');
        	  }
         }
           
           
        });   
	   /* } else{
    	
    	  $.messager.alert('warning','请选择权限','warning'); 
    } */
}


function closewc(){
	$('#wc').window('close'); 
}


/*function panduanqiuanxian(id){
	
	
	   $.ajax({
           type:"POST",
           url:"tree!panduanqiuanxian",
           data:{'cunquanxian':s,
        	      'roleidshezhi':roleidshezhi
          	
           },
           secureuri : false,//一般设置为false
        
          success:function (date){
        	
          },error:function callback(date){
        	
          }
           
           
        }); 
	
	
	
}*/


//验证添加角色权限
function openaddjiaose(){
	
 	var yonghu = $('#yonghu').val();	
	   $.ajax({
		 secureuri : false,
      type:"POST",
      url:"tree!panduanqiuanxian",
      data:{'yonghu':yonghu,
      	  'gongnengid':44
   	      
     	
      },
    secureuri : false,//一般设置为false
   
     success:function (date){
   	if(date==1){
   	
   		openaddjiaose2();
		
   	}else if(date==0){
   		
 			$.messager.alert('warning','你没有此权限！');
   	 }
     },error:function callback(date){
  	   if(date==-1){
  		   $.messager.alert('warning','系统错误！');  
  	   }
     }
      
      
   }); 
	
}

//打开添加角色
function openaddjiaose2(){
	 $('#addjuese').window('open');	
}



//添加角色
function addjiaose(){
	//alert("ssss");
   var juesename = $('#juesename').val();	
   var jueseshuoming = $('#jueseshuoming').val();	
	   $.ajax({
           type:"POST",
           url:"tree!addjiaose",
           data:{'juesename':juesename,
        	      'jueseshuoming':jueseshuoming	
           },
           secureuri : false,//一般设置为false
        
          success:function (date){
        	  if(date==1){
        	  $('#addjuese').window('close'); 
          	$.messager.alert('warning','添加成功');
          	
          	$('#jiaose').datagrid('reload');
        	  }
          },error:function callback(date){
        	  if(date==-1){
        	  $('#updatejuese').window('close'); 
		    $.messager.alert('warning','添加失败');
        	  }
         }
           
           
        }); 
	
}


function closeaddjuese(){
	 $('#addjuese').window('close');	
}
function closeupdatejuese(){
	 $('#updatejuese').window('close');	
	
}

//判断修改角色权限
function openupdatequanxian(){
	
 	var yonghu = $('#yonghu').val();	
	   $.ajax({
		 secureuri : false,
      type:"POST",
      url:"tree!panduanqiuanxian",
      data:{'yonghu':yonghu,
      	  'gongnengid':49
   	      
     	
      },
    secureuri : false,//一般设置为false
   
     success:function (date){
   	if(date==1){
   	
   		openupdatequanxian2();
		
   	}else if(date==0){
   		
 			$.messager.alert('warning','你没有此权限！');
   	 }
     },error:function callback(date){
  	   if(date==-1){
  		   $.messager.alert('warning','系统错误！');  
  	   }
     }
      
      
   }); 


	
	
	
}





//打开修改角色
function openupdatequanxian2(){

	  var select = $('#jiaose').datagrid('getSelected');
	   if(select){
		   $('#updatejuese').window('open');
		   
		   $('#roleid').val(select.roleId);
	       $('#juesenamex').val(select.roleName);
	       $('#jueseshuomingx').val(select.roleStatement);
	  
	    id = select.roleId;
	   }else{
	    $.messager.alert('warning','请选择一行数据','warning');
	   }
	  
}


//修改角色
function updatejiaose(){
	
	   var juesenamex = $('#juesenamex').val();	
	   var roleid = $('#roleid').val();
	   var jueseshuomingx = $('#jueseshuomingx').val();
	   $.ajax({
           type:"POST",
           url:"tree!updatejiaose",
           data:{ 
        	      'roleid':roleid,
        	      'juesenamex':juesenamex,
        	      'jueseshuomingx':jueseshuomingx	
           },
           secureuri : false,//一般设置为false
        
          success:function (date){
        	  if(date==1){
        	  $('#updatejuese').window('close'); 
          	$.messager.alert('warning','修改成功');
          	
          	$('#jiaose').datagrid('reload');
        	  }
          },error:function callback(date){
        	  if(date==-1){
        	  $('#updatejuese').window('close'); 
		    $.messager.alert('warning','修改失败');
		    
        	  }
         }
           
           
        }); 
	
}

//删除角色权限验证
function deljuese(){
	
 	var yonghu = $('#yonghu').val();	
	   $.ajax({
		 secureuri : false,
      type:"POST",
      url:"tree!panduanqiuanxian",
      data:{'yonghu':yonghu,
      	  'gongnengid':50
   	      
     	
      },
    secureuri : false,//一般设置为false
   
     success:function (date){
   	if(date==1){
   	
   		deljuese2();
		
   	}else if(date==0){
   		
 			$.messager.alert('warning','你没有此权限！');
   	 }
     },error:function callback(date){
  	   if(date==-1){
  		   $.messager.alert('warning','系统错误！');  
  	   }
     }
      
      
   }); 
	
}

//角色删除
function deljuese2(){

	 var select = $('#jiaose').datagrid('getSelected');
	   var roleid = select.roleId;
	if(select){
	
	    $.messager.confirm('warning','确认删除么?',function(id){
	    	if(id){
	    	
	    	  $.ajax({
           type:"POST",
           url:"tree!deljuese",
           data:{ 
        	      'roleidshan':roleid
        	      
           },
           secureuri : false,//一般设置为false
        
          success:function (date){
        	  if(date==2){
	          	$.messager.alert('warning','删除成功');
	          	$('#jiaose').datagrid('reload');
        	  }else if(date==1){
        		  
        			$.messager.alert('warning','不可删，有用户为此角色');
    	          	$('#jiaose').datagrid('reload');    
        	  }
          },error:function callback(date){
        	  if(date==-1){
			    $.messager.alert('warning','删除失败');
			  	$('#jiaose').datagrid('reload');
        	  }
         } 
        }); 
	   }
	    });
	}else{
		 $.messager.alert('warning','请选择一行数据','warning');
	}
	
	
}


