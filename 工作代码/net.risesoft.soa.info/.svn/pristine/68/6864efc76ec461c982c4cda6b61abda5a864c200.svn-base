<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<html>
<head>
<title></title>
<script type="text/javascript">
var tableElementsGrid = new Ext.grid.EditorGridPanel({
    store: new Ext.data.Store({
		proxy: new Ext.data.HttpProxy({
		     url: '/info/dbInfo.action'
		}),
        reader: new Ext.data.JsonReader({}, [
                 {name: 'columnName'},
                 {name: 'columnType'},
                 {name: 'columnTypeName'},
                 {name: 'isList'},
                 {name: 'listName'},
                 {name: 'listLength'},
                 {name: 'textLength'},
                 {name: 'textDec'},
                 {name: 'defaultValue'},
                 {name: 'isSearch'},
                 {name: 'searchType'},
                 {name: 'tabIndex'}
              ])
    }),
    cm: new Ext.grid.ColumnModel({defaults: {
    	menuDisabled:true
    },
    columns:[
        {align:'center', width:120, dataIndex: 'columnName', header:'字段名称'},
        {align:'center', width:100, dataIndex: 'columnTypeName', header: '字段类型'},
        {align:'center', width:50, dataIndex: 'isList', xtype: 'checkcolumn', header: '列表'},
        {align:'center', width:120, dataIndex: 'listName', editor: new Ext.form.TextField({}), header: '列表名称'},
        {align:'center', width:60, dataIndex: 'listLength', editor: new Ext.form.NumberField({}), header: '列表宽度'},
        {align:'center', width:60, dataIndex: 'textLength', editor: new Ext.form.NumberField({}), header: '文字宽度'},
        {align:'center', width:100, dataIndex: 'textDec', editor: new Ext.form.TextField({}), heaeer: '文字修饰'},
        {align:'center', width:100, dataIndex: 'defaultValue', editor: new Ext.form.ComboBox({
            typeAhead: true,
            triggerAction: 'all',
            listClass: 'x-combo-list-small',
           	displayField: 'name',
           	valueField: 'value',
   	        mode: 'local',
   	        store: new Ext.data.SimpleStore({
          	   		fields: ['name',"value"],
          	   		data: [["",""],["创建人 ","Person"],["部门","Department"],["当前日期","Date"],["当前时间","DateTime"]]	 
              }),
          	autoLoad:true
        }), header: '默认值'},
        {align:'center', width:50, dataIndex: 'isSearch', xtype: 'checkcolumn', header: '搜索'},
        {align:'center', width:60, dataIndex: 'searchType', editor: new Ext.form.ComboBox({
            typeAhead: true,
            triggerAction: 'all',
            displayField: 'name',
           	valueField: 'name',
            listClass: 'x-combo-list-small',
            mode: 'local',
   	        store: new Ext.data.SimpleStore({
          	   		fields: ['name'],
          	   		data: [["文本 "],["日期 "],["下拉"],["时间段"]]	 
              }),
          	autoLoad:true
        }), header: '搜索类型'},
        {align:'center', width:60, dataIndex: 'tabIndex',editor: new Ext.form.NumberField({}), header:'序号'}
    ]}),
    viewConfig: {
  	    scrollOffset: 0,
  	 	//autoFill: true,
        forceFit: true,
        getRowClass: function(record, index) {
        	return 'org-grid-row td';
        }
      },
    bodyCfg: {
        cls: 'org-grid-panel'
    },
    border: true,
    autoExpandColumn: 'common',
    hideHeaders: false,
    autoHeight: true,
    columnLines: true,
    clicksToEdit: 1,
    enableColumnHide: false,
    loadMask: true,
    renderTo: 'tableElements-div'
});  

var informationForm = new Ext.FormPanel({
    labelWidth: 60, 
    frame: false,
    width: 400,
    border: false,
    fileUpload: true,
    bodyStyle: 'padding:5px 5px 0',
    renderTo: 'informationForm-div',
    items: [{
    	xtype:'textfield',
        name: 'information.id',
        hidden: true,
        hideLabel: true,
		value: '${information.id}'
    },{
      xtype:'fieldset',
      title: '基本信息',
      bodyStyle: 'padding:2px 0 0',
      collapsible: false,
      autoHeight: true,
      defaultType: 'textfield',
      items: [{
        fieldLabel: '信息名称',
        name: 'information.name',
        allowBlank: false,
        blankText: '资源名称不能为空!',
        maxLength: 100,
        maxLengthText: '该输入项的最大长度是 {0}',
        value: '${information.name}',
        listeners: {
        	render:function(obj){
				obj.focus();
        	}
        },
        anchor: '100%'                
      },{
        fieldLabel: '显示图标',
        name: 'information.icon',
        maxLength: 100,
        maxLengthText: '该输入项的最大长度是 {0}',
        value: '${information.icon}',
        anchor: '100%'                
      },{
          fieldLabel: '链接地址',
          name: 'information.url',
          maxLength: 1000,
          maxLengthText: '该输入项的最大长度是 {0}',
          value: '${information.url}',
          anchor: '100%'                
      },
 	  new Ext.form.ComboBox({
   	       fieldLabel: '数据主表', 
   	       hiddenName: 'information.tableName',
   	       valueField: 'name',
   	       displayField: 'name',
   	       typeAhead: true,
   	   	   allowBlank: false,
           forceSelection: true,
           triggerAction: 'all',
           emptyText: '选择数据主表...',
           selectOnFocus: true,
           value: '${information.tableName}',
           store: new Ext.data.Store({
        	   baseParams: {operation: 'tables'},
        	   proxy: new Ext.data.HttpProxy({
        		     url: '/info/dbInfo.action'
        		 }),
        	   reader:new Ext.data.JsonReader({}, [
                         {name: 'name'}
                      ])
          }),
          listeners: {
        	  select:function(combo, record, index){
        		  Ext.getDom('elements-table').style.display = '';
        		  tableElementsGrid.getStore().load({params:{operation:'elements', tableName:combo.getValue(), node:'${information.id}'}});
          	  },
          	  afterrender:function(combo){
          		  if (combo.getValue() != ""){
          			Ext.getDom('elements-table').style.display = '';
          		    tableElementsGrid.getStore().load({params:{operation:'elements', tableName:combo.getValue(), node:'${information.id}'}});
          		  }
          	  }
          }
   	  }),{
          fieldLabel: '标题字段',
          name: 'information.titleField',
          allowBlank: false,
          maxLength: 100,
          maxLengthText: '该输入项的最大长度是 {0}',
          value: '${information.titleField}',
          anchor: '100%'                
      },{
          fieldLabel: '排序方式',
          name: 'information.sortField',
          maxLength: 100,
          maxLengthText: '该输入项的最大长度是 {0}',
          value: '${information.sortField}',
          anchor: '100%'                
      },{
          fieldLabel: '所属部门',
          name: 'information.module',
          maxLength: 100,
          maxLengthText: '该输入项的最大长度是 {0}',
          value: '${information.module}',
          anchor: '100%'                
      },{
          fieldLabel: '信息类别',
          name: 'information.infoType',
          maxLength: 100,
          maxLengthText: '该输入项的最大长度是 {0}',
          value: '${information.infoType}',
          anchor: '100%'                
      },{
          fieldLabel: '列表页数',
          allowBlank: false,
          xtype: 'numberfield',
          name: 'information.pageSize',
          value: ${information.pageSize},
          width: 40
      },new Ext.form.ComboBox({
	       fieldLabel: '列表显示', 
	       hiddenName: 'information.listShowStyle',
	       valueField: 'value',
	       displayField: 'name',
	       typeAhead: true,
	       forceSelection: true,
	       triggerAction: 'all',
	       emptyText: '选择显示方式...',
	       selectOnFocus: true,
	       mode: 'local',
	       value: '${information.listShowStyle}',
	       store: new Ext.data.SimpleStore({
       	   		fields: ['name',"value"],
       	   		data: [["默认显示","blog"],["列表显示","list"]]	 
           }),
       	   autoLoad:true
       }),new Ext.form.ComboBox({
	       fieldLabel: '表单显示', 
	       hiddenName: 'information.showStyle',
	       valueField: 'value',
	       displayField: 'name',
	       typeAhead: true,
	       forceSelection: true,
	       triggerAction: 'all',
	       emptyText: '选择显示方式...',
	       selectOnFocus: true,
	       mode: 'local',
	       value: '${information.showStyle}',
	       store: new Ext.data.SimpleStore({
       	   		fields: ['name',"value"],
       	   		data: [["模板显示","template"],["图片显示","image"]]	 
           }),
       	   autoLoad:true
       }),{
          fieldLabel: '表单模板',
          xtype: 'fileuploadfield',
          name: 'file',
          blankText: '请选择一个HTML文件!',
          buttonText: '',
          buttonCfg: {
              iconCls: 'upload-icon'
          },
          anchor: '100%'                
      }
      ]
    }]
  });
  
</script>
</head>
<body>
<table border="0" width="400" align="center">
<tr>
<td><div id="informationForm-div"></div></td>
</tr>
</table>
<p>
<table id="elements-table" height=30 border="1" align="center" cellpadding="0" cellspacing="0" style="display:none;background-color: #fafafa;border-collapse:collapse;" bordercolor="#E1E1E1">
    <!-- 
    <tr>
      	<td align=center height=40>
      	<table width=100% border=0 cellpadding="0" cellspacing="0">
      	<tr align=center style="border-collapse:collapse;">
      	    <td width=120>字段名称</td>
		 	<td width=1><img src="../images/separator.gif" width="1" alt=""></td>
		    <td width=100>字段类型</td>
		 	<td width=1><img src="../images/separator.gif" width="1" alt=""></td>
		 	<td width=48>列表</td>
		 	<td width=1><img src="../images/separator.gif" width="1" alt=""></td>
		 	<td width=118>列表名称</td>
		 	<td width=1><img src="../images/separator.gif" width="1" alt=""></td>
		 	<td width=60>列表宽度</td>
		 	<td width=1><img src="../images/separator.gif" width="1" alt=""></td>
		 	<td width=60>文字宽度</td>
		 	<td width=1><img src="../images/separator.gif" width="1" alt=""></td>
		 	<td width=98>文字修饰</td>
		 	<td width=1><img src="../images/separator.gif" width="1" alt=""></td>
		 	<td width=98>默认值</td>
		 	<td width=1><img src="../images/separator.gif" width="1" alt=""></td>
		 	<td width=50>搜索</td>
		 	<td width=1><img src="../images/separator.gif" width="1" alt=""></td>
		 	<td width=58>搜索类型</td>
		 	<td width=1><img src="../images/separator.gif" width="1" alt=""></td>
		 	<td width=58>序号</td>
		 	<td width=1><img src="../images/separator.gif" width="1" alt=""></td>
      	</tr>
      	</table>
      	</td>
 	</tr>
 	 -->
 	<tr>
 		<td><div id="tableElements-div"></div></td>
 	</tr>
</table>
&nbsp;
</body>
</html>