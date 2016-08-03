
/**
 * 多维度预览树
 */
var previewChooseDimensionTree = new Ext.tree.TreePanel({
	    useArrows: true, 
	    id:'previewChooseDimension',
	    animate: true,
	    enableDD: true,
	    rootVisible:false,
	    containerScroll: true,
	    border: false, 
	    root: {           
	        draggable: false,
	        id: '多维度预览'
	    }
	});

//获取预览树的根节点
var rootNode = previewChooseDimensionTree.getRootNode();
var _dimensionChoseCount = 0;
$(function(){
	//获取维度信息
	$.ajax({
		  type: 'POST',
		  url: 'dimension_getDimension.action',
		  dataType:'text',
		  success: function(data){			 
			 data = eval(data);
			 var str = "";
			 for( var i = 0;i < data.length;i++ ){
				 str += '<p class="dimensionContent"';
				 str += 'backgroundColor="#C0C0C0" dimensionChoseCount="noCount" ';
				 str += 'style="line-height: 30px;';
				 str += 'background-color: #C0C0C0;';
				 str += 'margin-top: 1px;cursor: pointer;"';
				 str += 'id="' + data[i].id + '">' + data[i].text + '</p>';				 
			 }			 
			 $('#chooseDimensionDiv').html(str);
			 $('.dimensionContent').click(function(){				 	
					var backgroundColor = $(this).attr('backgroundColor');
					var dimension = [];
					if(backgroundColor == '#0099FF'){
						$(this).css('background-color','#C0C0C0');
						$(this).attr('backgroundColor','#C0C0C0');		
						$(this).attr('dimensionChoseCount','noCount');
						_dimensionChoseCount--;
					}else{
						$(this).css('background-color','#0099FF');
						$(this).attr('backgroundColor','#0099FF');
						$(this).attr('dimensionChoseCount',_dimensionChoseCount++);
					}
					
					//获取选择的维度
					$('.dimensionContent').each(function(){
						var backgroundColor = $(this).attr('backgroundColor');				
						if(backgroundColor == '#0099FF'){
							var arrayLength = dimension.length++;				
							dimension[arrayLength] = {};
							dimension[arrayLength].text = $(this).html();				
							dimension[arrayLength].id = this.id;
							dimension[arrayLength].dimensionChoseCount = $(this).attr('dimensionChoseCount');
						}
						//删除预览树现有的维度节点
						var node = rootNode.findChild('id',this.id);
						if(node != null){
							node.remove();
						}
					});
					for (var j = 0; j < dimension.length; j++) {	
						for (var i = 1; i < dimension.length; i++) {
							if(dimension[i - 1].dimensionChoseCount > dimension[i].dimensionChoseCount){
								var c = dimension[i - 1];
								dimension[i - 1] = dimension[i];
								dimension[i] = c;
							}
						}			
					}
					var lastNode = rootNode;			
					for(var i = 0;i < dimension.length;i++){
						//获取预览树的最后一个节点
						lastNode.eachChild(function(node){
							node.expand(true);
							if(node.isLast()){
								lastNode = node;
							}			
						});	
						//添加预览树的维度节点
						lastNode.appendChild(new Ext.tree.TreeNode({
							text:dimension[i].text,
							id:dimension[i].id
						}));			
					}	
					//重新布局
					previewChooseDimensionTree.doLayout();
				});
		  },
		  error:function(){
			  Ext.Msg.alert('错误','服务器出现错误请稍后再试！');
		  }			  
	});
})

$(document).ready(function(){	
	previewChooseDimensionTree.render('previewDimensionDiv');
});

//提交后台的数据
var postData = [];
var allowSubmit = true;
/**
 * 提交维度选择的结果
 */
function chooseDimension(){
	postData = [];	
	eachTree(rootNode);
	if(!allowSubmit) return;	
	$.ajax({
		  type: 'POST',
		  url: 'dimension_choose.action',
		  data:{dimensionStr:Ext.encode(postData)},
		  dataType:'json',
		  success: function(data){	
			  $('#dimensionTree .x-tree-root-node').html('');
			  popWin.close();
			  dimensionTree.getRootNode().appendChild(data);			  
			  dimensionTree.doLayout();
		  },
		  error:function(){
			  Ext.Msg.alert('错误','服务器出现错误请稍后再试！');
		  }	
	});
}
/**
 * 循环遍历维度预览树
 * @param treeNode
 */
function eachTree(treeNode){	
	var i = 0;	
	treeNode.eachChild(function(node){		
		i++;
		var tmp = postData.length++;
		postData[tmp] = {};
		postData[tmp].id = node.id;
		postData[tmp].depth = tmp;
		eachTree(node);		
	});
	if(i > 1){
		alert("维度必须是单向的！");
		allowSubmit = false;
		return;
	}
}
