//多维度选择的按钮
var tbar = new Ext.Toolbar({
	renderTo:'chooseDimensionButton',
    items:{
    	text: '选择维度展示方式',
    	icon: '',
    	width: 180,
    	handler:dimensionButtonClick  
    }
})

/**
 * 选择维度按钮事件
 */
function dimensionButtonClick(){
	Ext.PopWin.init('选择维度', 500, 360);
	
	popWin.load({
		url:'dimension_preChoose.action',
		scripts:true
	});
}
/**
 * 多维度树
 */
var dimensionTree = new Ext.tree.TreePanel({
    useArrows: true,    
    animate: true,
    enableDD: true,
    rootVisible:false,
    containerScroll: true,
    id:'dimensionTree',    
    border: false,    
    root: {           
        draggable: false,
        id: 'Root-tree'        
    },listeners: {
        click:dimensionTreeNodeClick
    }
});
/**
 * 多维度树点击事件
 * @param node -- 点击节点
 */
function dimensionTreeNodeClick(node){
	//将分页起始位置重置为0
	_dFilePageStart = 0;
	var nodePath = node.getPath();
	nodePath = nodePath.substring(1);
	var sqlArray = nodePath.split('/');	
	var treeNode = dimensionTree.getRootNode();
	var dimensionSql = [];
	for(var i = 0 ;i < sqlArray.length;i++){
		var nodeId = sqlArray[i];
		if(nodeId == 'Root-tree') continue;	
		
		treeNode = treeNode.findChild('id',nodeId);	
		dimensionSql[i] = {};
		dimensionSql[i].columnName = treeNode.attributes['columnName'];
		dimensionSql[i].value = treeNode.text;
	}
	Ext.getCmp('index-center-panel').load({url:"dimension_file.action?dimensionSql="
		+ encodeURIComponent(Ext.encode(dimensionSql)) + "&start=" + _dFilePageStart + "&rows=" + _dFilePagePageSize
   	,scripts:true});
}

$(document).ready(function(){	
	dimensionTree.render('showDimensionTree');
});