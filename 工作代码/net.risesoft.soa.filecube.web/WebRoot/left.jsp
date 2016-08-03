<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>tree</title>
<style type="text/css">
	html{
		font-size: 12px;	
		overflow: auto;
	}
</style>
<script type="text/javascript">

$(function(){
	$('.personnelCenterOption').click(function(){			
		$('.personnelCenterOption').each(function(){
			$(this).attr('click','');
			$(this).css('background-color','');
		});	
		$(this).css('background-color','#0099FF');	
		$(this).attr('click','click');		
		var clickId = this.id;
		optionClick(clickId);
	});
	//鼠标移上改变，选择搜索选项的背景色
	$('.personnelCenterOption').hover(
		function(){		
			$(this).css('background-color','#0099FF');			
		},
		function(){			
			if($(this).attr('click') != 'click'){
				$(this).css('background-color','');
			}
		}
	);	
});
//点击选择项调用的事件
function optionClick(clickId){
	if(clickId == 'myFavorite'){
		Ext.getCmp('index-center-panel').
		load({url:'favorite_preList.action?start=0&rows=' + parseInt((_leftWinHeight - 220)/30),scripts:true});
		return;
	}
	if(clickId == 'myOpenRecord'){		
		Ext.getCmp('index-center-panel').
		load({url:'openHistory_preList.action',scripts:true});
		return;
	}
	if(clickId == 'myQueryRecord'){
		Ext.getCmp('index-center-panel').
		load({url:'queryHistory_preList.action',scripts:true});
		return;
	}
	if(clickId == 'myTrash'){
		Ext.getCmp('index-center-panel').
		load({url:'file_preRecycleBin.action',scripts:true});
		return;
	}
	if(clickId == 'systemLog'){
		Ext.getCmp('index-center-panel').
		load({url:'log_preSystemLog.action',scripts:true});
		return;
	}
	if(clickId == 'myShare'){		
		Ext.getCmp('index-center-panel').
		load({url:'share_preList.action?start=0&rows=' + parseInt((_leftWinHeight - 220)/30),scripts:true});
		return;
	}
	if(clickId == 'OtherToMeShare'){
		Ext.getCmp('index-center-panel').
		load({url:'share_preOtherToMeList.action?start=0&rows=' + parseInt((_leftWinHeight - 220)/30),scripts:true});
		global_FOLDER_TREE_TYPE_CUR = 'NOT_PERSONNEL_TREE';				
		return;
	}
}	
</script>
</head>
<body>
	<div id="leftTree"  style="display: none"> <!-- 此层设置为none，目的网页加载时不闪 -->
		<div id="showFolderTree">			  
			<!-- 全局文件 -->
			<div id="globalFolderTree">      
           		     
		    </div>	
		     
		    <!-- 部门文件 --> 
		    <div id="departMentFolderTree">
		    	
		    </div>
		    
		    <table cellpadding=0 cellspacing=0 id="personnelFolderTreeParentTable" >		
				<tr class="personnelCenterOptionParent" >
					<td>	
						<img src="images/view/folder_user.png" >我的文件夹
						<img src="/framework/images/refresh.png" onclick="refreshPersonnelTree()"
						style="vertical-align: middle;cursor: pointer;margin-left: 50px;" title="点击刷新">
					 <td>
				</tr>	
				<tr>
					<td>			
						<!-- 我的文件 --> 		   
					    <div id="personnelFolderTree" style="margin-bottom: 15px;">		    	
					    	
					    </div>
			    	</td>
				</tr>				
				
				<tr class="personnelCenterOptionParent">
						<td>
						<img src="images/view/application_view_list.png" style="padding-top:4px;">历史记录
						</td>
				</tr>
				<tr id="myQueryRecord" class="personnelCenterOption" >
						<td>
						<img src="images/view/folder_find.png" >查询记录					
						</td>
				</tr>
				
				<tr id="myOpenRecord" class="personnelCenterOption" >
						<td>
						<img src="images/file/small/folders/album.gif" >浏览记录					
						</td>
				</tr>
							
				<tr  class="personnelCenterOptionParent">
					<td>
						<img src="images/view/share.png" 
						style="">共享
					</td>
				</tr>
				<tr id="myShare" class="personnelCenterOption" >
						<td>
							<img src="images/view/share_to_other.png" >我的共享							
						</td>
				</tr>
				
				<tr id="OtherToMeShare" class="personnelCenterOption">
						<td>
						<img src="images/view/share_to_me.png" >来自他人的共享
						</td>
				</tr>
						
				<tr  class="personnelCenterOptionParent">
						<td>
						<img src="images/view/heart.png" >收藏
						</td>
				</tr>	
				<tr id="myFavorite" class="personnelCenterOption" >
						<td>
						<img src="images/view/star.png" >我的收藏
						</td>
				</tr>	
			
				<tr  class="personnelCenterOptionParent">
						<td>
						<img src="images/view/server.png" >系统
						</td>
				</tr>	
				<tr id="myTrash" class="personnelCenterOption" >
						<td>
						<img src="images/button_operation/trash.gif" >回收站						
						</td>
				</tr>
				<tr style="">
					
					<td ></td>
				
					
				</tr>
				<tr id="systemLog" class="personnelCenterOption" >
						<td>
							<img src="images/view/info.png">系统日志
						</td>
				</tr>	    		
		    </table>
		  	    
	    </div>
	</div>
	
</body>
</html>