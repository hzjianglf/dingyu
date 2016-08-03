/**
 * 设置默认的窗口参数
 */
var popWin;
function popWinShow(title, varWidth, varHeight, border){
	popWin = new Ext.Window({
	     //renderTo: 'popWin-div',
		 //id: 'popWin',
		 closeAction: 'close',
		 modal: true,
		 resizable: false,
		 autoHeight: false,
		 border: false,
		 layout: 'fit',
		 title: title,
		 width: varWidth,
		 height: varHeight
	});
	if (border){
		popWin.border = true;
	}
	popWin.show();
	popWin.center();
}

/**
 * 隐藏窗口
 */
function popWinHide(){
	if (popWin){
		popWin.removeAll(true);
		popWin.close();
	}
}

//服务器端返回的消息格式：{success:true,message:'创建成功'}
$showResponseMsg = function(response){  
	var responseArray = Ext.util.JSON.decode(response.responseText);  
	var msg;
	var success = false;
	if (responseArray) {
		success = responseArray.success;
		msg = responseArray.message;
	}
	if (success){
		Ext.Msg.show({
			msg: msg,
	        buttons: Ext.MessageBox.OK,
	        icon: Ext.MessageBox.INFO
		});
	}else {
		Ext.Msg.show({
			msg: msg,
	        buttons: Ext.MessageBox.OK,
	        icon: Ext.MessageBox.ERROR
		});
	}
	return success;
}

function home() {
	var selectedNode = infoTree.getSelectionModel().getSelectedNode();
	if (selectedNode) {
		if (selectedNode.getOwnerTree()) {
			selectedNode.unselect(true);
		}
	}
	Ext.getCmp("centerLayout").load( {
		url : 'display/default.jsp',
		scripts : true
	});
}

function show(nodeID){
    var selectedNode = infoTree.getNodeById(nodeID);
    if (selectedNode){
	    infoTree.selectPath(selectedNode.getPath());
	    selectedNode.fireEvent('click', selectedNode);
    }
}

function getBreadcrumbPath(){
	  var node = infoTree.getSelectionModel().getSelectedNode();
	  var path = node.text;	  
	  while (node.parentNode && node.parentNode.id != 'root') {
	    path = "<a href=javascript:show('" + node.parentNode.id + "')>" + node.parentNode.text + "</a>" + " &gt;&gt; " + path;
	    node = node.parentNode;
	  }
	  return path;
	}

/**
 * 新增资源
 */
function createInfoContainer() {
	popWinShow('新增资源', 400, 410);
	popWin.load( {
		url : 'form/editContainer.jsp',
		scripts : true,
   		params : {
			operation : 'create'
		}
	});
}

function updateInfoContainer() {
	popWinShow('更新资源', 400, 410);
	popWin.load( {
		url : '/info/containerAction.action',
		scripts : true,
		params : {
			node : nodeID,
			operation : 'update'
		}
	});
}

function deleteInfoContainer() {
	var resourceNode = infoTree.getNodeById(nodeID);
	var message = '确定要删除资源及其下所有节点?';
	if (resourceNode){
		message = '确定要删除资源：<font color="blue">' + resourceNode.text + '</font>及其下所有节点?';
	}
	Ext.Msg.confirm('', message, function(btn) {
    	var myMask = new Ext.LoadMask(Ext.getBody(), {msg:"正在删除,请等候..."});
		if (btn == 'yes') {
		    myMask.show();
			Ext.Ajax.request( {
				url : '/info/containerAction.action',
				method : 'POST',
				timeout: 300000,
				params : {
					operation : 'delete',
					node : nodeID
				},
				success : function(response) {
					var success = $showResponseMsg(response);
					if (success) {
						if (resourceNode) {
							resourceNode.remove();
						}
						home();
					}
					myMask.hide();
				},
				failure : function(response) {
					$showResponseMsg(response);
					myMask.hide();
				}
			});
		}
	});
}

/**
 * 新增资源
 */
function createInfomation() {
	Ext.getCmp('centerLayout').removeAll(true);
	Ext.getCmp("centerLayout").load({
   		url: 'form/informationPanel.jsp',
   		scripts:true,
   		params : {
			node : nodeID,
			operation : 'create'
		}
   	});
}

/**
 * 新增资源
 */
function updateInformation() {
	Ext.getCmp('centerLayout').removeAll(true);
	Ext.getCmp("centerLayout").load({
   		url: 'form/informationPanel.jsp',
   		scripts:true,
   		params : {
			node : nodeID,
			operation : 'update'
		}
   	});
}

function deleteInformation() {
	var resourceNode = infoTree.getNodeById(nodeID);
	var message = '确定要删除信息发布?';
	if (resourceNode){
		message = '确定要删除信息发布：<font color="blue">' + resourceNode.text + '</font>?';
	}
	Ext.Msg.confirm('', message, function(btn) {
    	var myMask = new Ext.LoadMask(Ext.getBody(), {msg:"正在删除,请等候..."});
		if (btn == 'yes') {
		    myMask.show();
			Ext.Ajax.request( {
				url : '/info/informationAction.action',
				method : 'POST',
				timeout: 300000,
				params : {
					operation : 'delete',
					node : nodeID
				},
				success : function(response) {
					var success = $showResponseMsg(response);
					if (success) {
						if (resourceNode) {
							resourceNode.remove();
						}
						home();
					}
					myMask.hide();
				},
				failure : function(response) {
					$showResponseMsg(response);
					myMask.hide();
				}
			});
		}
	});
}
