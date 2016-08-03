<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
</head>
<script type="text/javascript">

//修改文件信息表单
var kindCmb = new Ext.data.SimpleStore({
  fields : ['value', 'text'],
  data : [['收文', '收文'], ['发文', '发文'],['信息','信息']]
});
var secretCmb = new Ext.data.SimpleStore({
  fields : ['value', 'text'],
  data : [['公开','公开'], ['内部', '内部'], ['秘密', '秘密'], ['机密', '机密'], ['绝密', '绝密']]
});
var enmergencyCmb = new Ext.data.SimpleStore({
  fields : ['value', 'text'],
  data : [['普通', '普通'], ['急件', '急件'], ['紧急', '紧急'], ['特急', '特急']]
});
var dt = new Date();

var years = ''; //所有年份
for(var num=dt.getFullYear() + 5; num >= dt.getFullYear() - 5;num--){
	var temp ="['" +  num + "','" + num+ "']" +"," ; 
	years=years+temp; 	
}

years = years.substr(0,years.length-1);

var yearCmb = new Ext.data.SimpleStore({
  fields : ['value', 'text'],
  data :eval("["+years+"]")
});
var editfileForm = new Ext.FormPanel({
	labelWidth : 75,
	frame : true,
	defaults : {width : 160},
	defaultType : 'textfield',
	renderTo:'editFileForm',
	items : [{	
	 		id:'fileName',
			fieldLabel : '文件名称',
			name : 'acFileInfo.name',
			allowBlank : false,
			blankText : '文件名称不能为空'
   	},
  	new Ext.form.TextArea({
			fieldLabel: '备注',
			id:'fileDes',
			name: 'acFileInfo.description',
			width:150,
			height:50
		})
   	,
   	{
			fieldLabel: '部门',
			id:'department',
			name: 'acFileInfo.departmentName',
			readOnly:true
	}
  	,
   	new Ext.form.ComboBox({
			id:'yearId',
			fieldLabel: '年份',
			hiddenName:'acFileInfo.year',//提交到后台的input的name 
		    width : 80,
		    store : yearCmb,//填充数据 
		    emptyText : '请选择',
		    mode : 'local',//数据模式，local代表本地数据 
		    //readOnly:true,
		    triggerAction : 'all',// 显示所有下列数据，一定要设置属性triggerAction为all 
		    valueField : 'value',//值 
		    displayField : 'text',//显示文本 
		    editable: false//是否允许输入 
		}),
		new Ext.form.ComboBox({
			id:'docKindId',
			fieldLabel: '文种',
			hiddenName:'acFileInfo.kind',//提交到后台的input的name 
		    width : 80,
		    store : kindCmb,//填充数据 
		    emptyText : '请选择',
		    mode : 'local',//数据模式，local代表本地数据 
		    //readOnly:true,
		    triggerAction : 'all',// 显示所有下列数据，一定要设置属性triggerAction为all 
		    valueField : 'value',//值 
		    displayField : 'text',//显示文本 
		    editable: false//是否允许输入 
		}),
		new Ext.form.ComboBox({
			id:'secretId',
			fieldLabel: '密级',
			hiddenName:'acFileInfo.fod.secretLevel',//提交到后台的input的name 
		    width : 80,
		    store : secretCmb,//填充数据 
		    emptyText : '请选择',
		    mode : 'local',//数据模式，local代表本地数据 
		   // readOnly:true,
		    triggerAction : 'all',// 显示所有下列数据，一定要设置属性triggerAction为all 
		    valueField : 'value',//值 
		    displayField : 'text',//显示文本 
		    editable: false//是否允许输入 
		}),
		new Ext.form.ComboBox({
			id:'enmergencyId',
			fieldLabel: '紧急程度',
			hiddenName:'acFileInfo.fod.enmergency',//提交到后台的input的name 
		    width : 80,
		    store : enmergencyCmb,//填充数据 
		    emptyText : '请选择',
		    mode : 'local',//数据模式，local代表本地数据 
		    //readOnly:true,
		    triggerAction : 'all',// 显示所有下列数据，一定要设置属性triggerAction为all 
		    valueField : 'value',//值 
		    displayField : 'text',//显示文本 
		    editable: false//是否允许输入 
		}),
		new Ext.form.Hidden({
	       	id:'fileUID',
	       	name: 'acFileInfo.fileUid'
	   	}),
	   	new Ext.form.Hidden({
	       	id:'departmentUid',
	       	name: 'acFileInfo.departmentUid'
	   	})
  	],
		buttons:[{
	       text : '修改',
	       handler : function(){	      	
	           if(editfileForm.getForm().isValid()){              
	        	   editfileForm.getForm().doAction('submit',{
	                   url:'file_edit.action',//文件路径
	                   method:'post',//提交方法post或get 
					   params:'',   
					   waitMsg:'正在保存，请稍后……',
	                   //提交成功的回调函数
	                   success:function(form,action){
	                	    popWin.close();
	                        Ext.Msg.alert('提示','文件修改成功'); 
	                        _left_refreshTreeAndList('${fileInfo.fileFolder.parentUid}');
	                   },
	                   //提交失败的回调函数
	                   failure:function(form,action){
	                        Ext.Msg.alert('错误','服务器出现错误请稍后再试！');
	                   }
	                });    
	           }
	       }
	   }, { 
		   text : '取消',
	       handler : function(){   		
				popWin.close();
	       }
	   }
	 ]
});
Ext.getCmp('fileUID').setValue('${fileInfo.fileUid}');
Ext.getCmp('fileName').setValue('${fileInfo.name}');
Ext.getCmp('fileDes').setValue('${fileInfo.description}');
Ext.getCmp('department').setValue('${fileInfo.departmentName}');
Ext.getCmp('departmentUid').setValue('${fileInfo.departmentUid}');
Ext.getCmp('yearId').setValue('${fileInfo.fod.year}');
Ext.getCmp('docKindId').setValue('${fileInfo.fod.kind}');
Ext.getCmp('secretId').setValue('${fileInfo.fod.secretLevel}');
Ext.getCmp('enmergencyId').setValue('${fileInfo.fod.enmergency}');
</script>
<body>
<div id="editFileForm"></div>	
</body>
</html>