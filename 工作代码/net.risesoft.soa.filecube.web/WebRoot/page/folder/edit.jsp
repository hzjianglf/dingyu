<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
</head>
<script type="text/javascript">
//创建表单
var folderForm = new Ext.FormPanel({	
    frame : true,
    defaults : {width : 160},
    labelWidth:100,
	labelAlign:"right",
	layout:"form",
	animCollapse:true,
	maskDisabled:true,
	autoHeight:true,
	renderTo:'editFolderForm',
    items : [
        {
        	xtype:"textfield",
            id:'folderName',
            fieldLabel : '文件夹名称',
            name : 'folder.name',
            allowBlank : false,
            blankText : '文件夹名称不能为空'
        },
        {	
        	xtype:"textarea",
        	id:'folderDes',
            fieldLabel : '文件夹描述',
            name : 'folder.description',
            height:150
        }
    ],    
    buttons : [
        {
            text : '保存',
            handler : function()
            {    
                //此方法在Class Ext.form.BasicForm里面,是FormPanel的父类
                if(folderForm.getForm().isValid()){
                	folderForm.getForm().doAction('submit',{
                        url:'folder_edit.action' ,//文件路径
                        method:'post',//提交方法post或get 
						params:'folder.folderUid=${fileFolder.folderUid}&folderTreeType=' + global_FOLDER_TREE_TYPE_CUR,  
						waitMsg:'正在保存，请稍后……',
                        //提交成功的回调函数
                        success:function(form,action){                        	
                        	var msg = Ext.util.JSON.decode(action.response.responseText);                        	
                            if (msg != 'error') {                                                         
                                Ext.Msg.alert('操作成功',msg.success);                               
                            } else {
                                Ext.Msg.alert('操作错误',msg);
                            }   
                            popWin.close();
                            _left_refreshTreeAndList('${fileFolder.parentUid}');
                        },
                        //提交失败的回调函数
                        failure:function(){
                             Ext.Msg.alert('错误','服务器出现错误请稍后再试！');
                        }
                      });    
                }
            }
        },
        {
            text : '取消',
            handler : function()
            {
        		folderForm.getForm().getEl().dom.reset();  
        		popWin.close();
            }
        }
    ]
});
Ext.getCmp('folderName').setValue('${fileFolder.name}');
Ext.getCmp('folderDes').setValue('${fileFolder.description}');
</script>
<body>
<div id="editFolderForm"></div>	
</body>
</html>