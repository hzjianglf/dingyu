<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%
String operation = request.getParameter("operation");
String node = request.getParameter("node");
%>

<html>
<head>
<title></title>
<script language="javascript" type="text/javascript">
var informationPanel = new Ext.Panel({
	border: false,
	renderTo: 'information-panel-div',
	height: Ext.getCmp('centerLayout').getInnerHeight(),
    tbar: [{
		text: '保存',
		iconCls: 'save-icon',
		handler: function(){
			if (informationForm.getForm().isValid()){
				var _store = tableElementsGrid.getStore();
				var elements = new Array(_store.getCount());
			    for(var i = 0;i < _store.getCount(); i++) 
				{ 
				  var record = _store.getAt(i); 
				  elements[i] = Ext.util.JSON.encode(record.data);
				} 
			    informationForm.getForm().submit({
		            url: '/info/informationAction.action',
		            params: {node:'<%=node%>', operation:'save', elements: elements},
		            method: 'POST',
		            waitMsg: '正在保存，请稍候...',
		            timeout: 300000,
		            success: function(form,action){    
		              Ext.Msg.show({
		                msg: '保存信息成功!',
		                buttons: Ext.MessageBox.OK,
		                icon: Ext.MessageBox.INFO
		              });
		              <%if (operation.equals("create")){%>
		              var infoNode = infoTree.getNodeById('<%=node%>');
		              if (infoNode){
		            	  infoNode.reload();
		              }
		              <%}%>
					},
		            failure:function(form, action){
		              var msg = '保存信息失败!';
		  	          if (action.result){
		  		          msg = action.result.message;
		  		      } 	
		              Ext.Msg.show({
		                msg: msg,
		                buttons: Ext.MessageBox.OK,
		                icon: Ext.MessageBox.ERROR
		              }); 
		            }
		          });
			}else{
				Ext.Msg.show({
	                msg: '缺少数据项!',
	                buttons: Ext.MessageBox.OK,
	                icon: Ext.MessageBox.ERROR
              	});
				return;
			}

		}
	}],
    autoScroll: true,
    margins:'5 0 5 5',
    autoLoad: {url: '/info/informationAction.action', params:{node: '<%=node%>', operation: '<%=operation%>'}, scripts:true }
});

Ext.getCmp('centerLayout').add(informationPanel);

</script>

</head>
<body>
<div id="information-panel-div"></div>	
</body>
</html>