<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%
String operation = request.getParameter("operation");
%>
<html>
<head>
<title></title>
<script type="text/javascript">
var resourceForm = new Ext.FormPanel({
    labelWidth: 65, 
    frame: false,
    height: popWin.getInnerHeight(),
    bodyStyle: 'padding:5px 5px 0',
    renderTo: 'resourceForm-div',
    items: [{
    	xtype:'textfield',
        name: 'resource.id',
        hidden: true,
        hideLabel: true,
		value: '${resource.id}'
    },{
    	xtype:'textfield',
        name: 'resource.type',
        hidden: true,
        hideLabel: true,
		value: 'infoContainer'
    },{
      xtype:'fieldset',
      title: '基本信息',
      bodyStyle: 'padding:2px 0 0',
      collapsible: false,
      autoHeight: true,
      defaultType: 'textfield',
      items: [{
        fieldLabel: '资源名称',
        name: 'resource.name',
        allowBlank: false,
        blankText: '资源名称不能为空!',
        maxLength: 100,
        maxLengthText: '该输入项的最大长度是 {0}',
        value: '${resource.name}',
        listeners: {
        	render:function(obj){
				obj.focus();
        	}
        },
        anchor: '100%'                
      },{
          fieldLabel: '资源别名',
          name: 'resource.aliasName',
          maxLength: 100,
          maxLengthText: '该输入项的最大长度是 {0}',
          value: '${resource.aliasName}',
          anchor: '100%'                
      },{
        fieldLabel: '显示图标',
        name: 'resource.icon',
        maxLength: 100,
        maxLengthText: '该输入项的最大长度是 {0}',
        value: '${resource.icon}',
        anchor: '100%'                
      },{
          fieldLabel: '站点地址',
          name: 'resource.site',
          maxLength: 1000,
          maxLengthText: '该输入项的最大长度是 {0}',
          value: '${resource.site}',
          anchor: '100%'                
      },{
          fieldLabel: '链接地址',
          name: 'resource.url',
          maxLength: 1000,
          maxLengthText: '该输入项的最大长度是 {0}',
          value: '${resource.url}',
          anchor: '100%'                
      },{
          fieldLabel: '打开类型',
          name: 'resource.openType',
          maxLength: 100,
          maxLengthText: '该输入项的最大长度是 {0}',
          value: '${resource.openType}',
          anchor: '100%'                
      },{
          fieldLabel: '打开位置',
          name: 'resource.target',
          maxLength: 100,
          maxLengthText: '该输入项的最大长度是 {0}',
          value: '${resource.target}',
          anchor: '100%'                
      },{
          fieldLabel: '资源状态',
          name: 'resource.status',
          maxLength: 100,
          maxLengthText: '该输入项的最大长度是 {0}',
          value: '${resource.status}',
          anchor: '100%'                
      },{
        fieldLabel: '资源描述',
        name: 'resource.description',
        xtype: 'textarea',
        maxLength: 2000,
        maxLengthText: '该输入项的最大长度是 {0}',
        value: '${resource.description}',
        anchor: '100% .4'
      }
      ]
    }],
    buttons: [{
        text: '保存',
        handler: function(){
           resourceForm.getForm().submit({
            url: '/info/containerAction.action',
            params: {node:nodeID, operation:'save'},
            method: 'POST',
            success: function(form,action){    
              Ext.Msg.show({
                msg: '保存成功!',
                buttons: Ext.MessageBox.OK,
                icon: Ext.MessageBox.INFO
              });
              <%if (operation.equals("create")){%>
              var resourceNode = infoTree.getNodeById(nodeID);
              if (resourceNode){
            	resourceNode.reload();
              }
              <%}%>
			  popWinHide();
			},
            failure:function(){   
              Ext.Msg.show({
                msg: '保存失败!',
                buttons: Ext.MessageBox.OK,
                icon: Ext.MessageBox.ERROR
              }); 
            }
          });
        } 
      },{
        text: '重置',
        handler: function(){
          resourceForm.getForm().reset();
        }
      }]
  });
</script>
</head>
<body>
<div id="resourceForm-div"></div>
</body>
</html>