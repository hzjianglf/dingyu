
//当前点击的图片名称
$(document).ready(function(){	
	$(".imagesView").colorbox({
		rel:'imagesView',
		width:"75%", 
		height:"75%"
		/*,
		onOpen:function(){ 
			//alert('打开对话框!');			
		},
		onLoad:function(){ 					
			_currentImageID = $(this).attr('fileUid');		
			
		},
		onComplete:function(){
			$('#imageOperateDiv_a').remove();
			var operateHtml = '<div id="imageOperateDiv_a" style="padding-right:20px;">';
			operateHtml += $('#imageOperateDiv').html();
			operateHtml += "</div>";
			$('#cboxContent').append(operateHtml);	
			//alert('完成对话框加载!'); 
			$('.imageOperate').hover(
				function(){
					$(this).css('color','blue');
				},
				function(){
					$(this).css('color','#7C7C7C');
				}
			);					
		},
//		onCleanup:function(){ 
//			
//		},
		onClosed:function(){ 
			$('#imageOperateDiv_a').remove();
		}*/
	});		
});