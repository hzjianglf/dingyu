<%@page import="net.risesoft.soa.filecube.util.OperationType"%>
<%@page import="net.risesoft.soa.filecube.util.GlobalConst"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script>
//全局变量,变量命名规则 js文件名_属性名;
/**
 * 资源类型-文件
 */
var global_resourceType_File = '<%=GlobalConst.RESOURCE_TYPE_FILE%>';
/**
 * 资源类型-文件夹
 */
var global_resourceType_Folder = '<%=GlobalConst.RESOURCE_TYPE_FOLDER%>';
/**
 * 全局error关键字
 */
/**
 * 资源类型：文件 英文标识
 */
var global_RESOURCE_TYPE_FILE_EN = '<%=GlobalConst.RESOURCE_TYPE_FILE_EN%>';
/**
 * 资源类型：文件夹 英文标识
 */
var global_RESOURCE_TYPE_FOLDER_EN = '<%=GlobalConst.RESOURCE_TYPE_FOLDER_EN%>';
/**
 * 文件夹树类型：全局文件夹树
 */
var global_FOLDER_TREE_TYPE_GLOBAL = '<%=GlobalConst.FOLDER_TREE_TYPE_GLOBAL%>';
/**
 * 文件夹树类型：部门文件夹树
 */
var global_FOLDER_TREE_TYPE_DEPARTMENT = '<%=GlobalConst.FOLDER_TREE_TYPE_DEPARTMENT%>';
/**
 * 文件夹树类型：个人文件夹树
 */
var global_FOLDER_TREE_TYPE_PERSONNEL = '<%=GlobalConst.FOLDER_TREE_TYPE_PERSONNEL%>';
/**
 * 记录点击树的类型，默认加载全局文件树
 */
var global_FOLDER_TREE_TYPE_CUR = global_FOLDER_TREE_TYPE_GLOBAL;
/**
 * 视图方式：列表视图
 */
var global_FILE_VIEWMODE_LIST = '<%= GlobalConst.FILE_VIEWMODE_LIST%>';
/**
 * 视图方式：图片视图
 */
var global_FILE_VIEWMODE_IMG = '<%= GlobalConst.FILE_VIEWMODE_IMG%>';
/**
 * 当前视图方式
 */
var global_CUR_FILE_VIEWMODE = global_FILE_VIEWMODE_LIST;

var global_CUR_FOLDER_ROOT_UID = '';
/**
 * 当前session中的用户uid 
 */
var global_CUR_SESSION_USER_ORGTYPE = ''; 
/**
 * 当前session中的登录名 
 */
var global_USER_ADMIN_TYPE = 'Admin';
 
/**
 * 操作类型--删除
 */
var global_OperationType_DELETE = "<%=OperationType.FC_DELETE.toString()%>";
/**
 * 操作类型--修改
 */
var global_OperationType_MODIFY = "<%=OperationType.FC_MODIFY.toString()%>";
/**
 * 操作类型--共享
 */
var global_OperationType_SHARE = "<%=OperationType.FC_SHARE.toString()%>";
/**
 * 操作类型--添加
 */
var global_OperationType_ADD = "<%=OperationType.FC_ADD.toString()%>";
/**
 * 操作类型--查看
 */
var global_OperationType_VIEW = "<%=OperationType.FC_VIEW.toString()%>";
/**
 * 操作类型--下载
 */
var global_OperationType_DOWNLOAD = "<%=OperationType.FC_DOWNLOAD.toString()%>";


/**
 * 全局ajax错误 返回值
 */
var global_ajax_error = 'error';
//全局方法，方法命名规则 js文件名_方法名();
/**
 * 带返回值的ajax
 * @param url     -- 访问的url
 * @param params  -- 参数 json格式
 * @param async   -- 是否异步，true：异步,false：同步
 */
function global_ajax(url,params,async){
	var rtnData;
	$.ajax({
		  type: 'POST',
		  url: url,
		  dataType: 'JSON',
		  data:params,
		  async: async,      //ajax同步
		  success: function(data){
			  rtnData = data;
		  },
		  error:function(){			 
			  rtnData = global_ajax_error;
		  }			  
	});
	return rtnData;
}
</script>
