/**
 * 共通方法
 * @author 雷蕾
 */

// ------------------------ 共通ajax请求方法 ------------------------
// ------------------------ 共通ajax请求方法 ------------------------
/**
 * 共通ajax请求方法
 * @param requestName 请求名称（主要用于打印日志）
 * @param requestBody 请求参数
 * @param localRequestUrl 挡板数据地址
 * @param pageId pageId
 * @param successFunc 请求成功success=true时的回调函数
 * @param failFunc 请求失败success=false时的回调函数 没有特殊处理时传null
 */
function sendRequest(requestBody, requestUrl, successFunc, failFunc) {
    console.log(jsonToStr(requestBody));
    console.log(toServerUrl(requestUrl));
    $.ajax({
        type: "POST",
        async: true,
        url: toServerUrl(requestUrl),
        data: requestBody,
        dataType: 'json',
        success: function (json) {
            console.log("返回数据：" + jsonToStr(json));
            if (json.header.success) {
                // 调用请求成功的回调函数
                successFunc(json);
            } else {
                // 获取失败
                if ("NEEDLOGIN" == json.header.error_code) {
                    // 服务器端登录失效，弹出登录对话框

                } else {
                    if (typeof(failFunc) == "undefined" || failFunc == null) {
                        // 弹出错误提示
                    	showDialog(json.header.message, "toast", "", "");
                    } else {
                        // 如果传入了请求失败success=false时的回调函数，则回调
                        failFunc(json);
                    }
                }
            }
        },
        error: function (value, a, b) {
            if (typeof(failFunc) != "undefined" && failFunc != null) {
                failFunc({"header": {"success": false}});
            }
        }
    });
}
///**
// * 共通ajax请求方法
// * @param requestName 请求名称（主要用于打印日志）
// * @param requestBody 请求参数
// * @param localRequestUrl 挡板数据地址
// * @param pageId pageId
// * @param successFunc 请求成功时的回调函数
// */
//function sendRequest(requestName, requestBody, localRequestUrl, pageId, successFunc, loadingStr){
//
//    if(loadingStr == null || loadingStr == ""){
//        loadingStr = "读取数据中...";
//    }
//
//    jsonmessage.request.requestHeader = requestHeader;
//    jsonmessage.request.requestBody = requestBody;
//    createLoadingWindow(loadingStr, pageId);
//    plog(requestName + "---请求参数:");
//    plog(requestBody);
//
//    if(isLocalData){
//        requestUrl = localRequestUrl;
//    }
//
//    $.ajax({
//        type: ajaxType,
//        async: true,
//        url: requestUrl,
//        data: {msg:JSON.stringify(requestBody)},
//        dataType: 'json',
//        success: function(json) {
//            closeLodingWindow(pageId);
//            plog("返回数据："+jsonToStr(json));
//            if(json.package.header.success){
//                // 调用请求成功的回调函数
//                successFunc(json);
//            } else {
//                // 获取失败
//                plog(json.package.header.errMsg);
//                showOneBtnDialog("友情提示",json.package.header.errMsg);
//            }
//        },
//        error: function(value, a, b) {
//            closeLodingWindow(pageId);
//            plog("失败");
//            plog(value);
//            plog(a);
//            plog(b);
//            showOneBtnDialog("友情提示","服务器升级中...");
//        }
//    });
//}

// ------------------------ loading窗口的调用 ------------------------
// 调用jquerymobile原生的loading窗口方法同时调用一个生成遮罩层的方法
/**
 * 创建loading窗口
 * @param textval 要展示的信息内容
 * @param pageId 页面的id
 */
function createLoadingWindow(textval, pageId){
    if(textval==null||textval==""){
        textval="加载中...."
    }
    plog(textval+"#"+pageId);
    $('#'+pageId).showLoading();//loading遮罩窗口渲染
    $.mobile.loading( 'show', {
        text: textval,
        textVisible: true,
        theme: 'a',
        html: ""
    });
}


function back(){
    history.back();
}

/**
 * 关闭loading窗口
 * @param pageId page的id
 */
function closeLodingWindow(pageId){
    $('#'+pageId).hideLoading();//loading遮罩窗口关闭
    $.mobile.loading("hide");
}

// ------------------------ 各种提示信息 ------------------------
/**
 * 统一的红字信息提示：针对的是mobile的登录和填写意见时的提示.
 * @param mesDivId 提示信息作用的区域
 * @param mes 提示信息
 */
function toAlertMessage(mesDivId,msg){
    var content = "";
    content = "<h2>"+msg+"</h2>";
    $("#"+mesDivId).html(msg);
    $("#"+mesDivId).show();
    //6秒钟后隐藏提示区域
    setTimeout(function(){
        var content = "";
        $("#"+mesDivId).html(content);
        $("#"+mesDivId).hide();
    },6000);
}

// ------------------------ 字符串处理 ------------------------
/**
 * 字符串格式化
 * 使用方法：
 * （1）需格式化的字符串：var str = "格式化{0}个字符，成功{1}个";
 * （2）调用：String.format(str,第一个参数对应{0}, 第二个参数对应{1})
 * @returns {*}
 */
String.format = function() {
    var theString = arguments[0];

    for (var i = 1; i < arguments.length; i++) {
        var regEx = new RegExp("\\{" + (i - 1) + "\\}", "gm");
        theString = theString.replace(regEx, arguments[i]);
    }

    return theString;
}

/**
 * 将Json对象转为字符串
 * @param json Json对象
 */
function jsonToStr(json){
    return JSON.stringify(json);
}

/**
 * 将json格式的字符串转为Json对象
 * @param str json格式的字符串
 */
function strToJson(str){
    return JSON.parse(str);
}

/**
 * 处理空字符串，避免其显示为undefined
 * @param str
 * @returns {*}
 */
function undefinedHandler(str){
    if(str == null){
        return "";
    }
    return str;
}

/**
 * 移除开头和结尾的一个字符
 * @param string
 * @param char 要去掉的字符
 * @returns {*}
 */
function removeFirstLastChar(string, char){
    var str = string;
    var length = char.length;
    if (str.substr(0, length) == char) {
        str = str.substr(length);
    }
    if (str.substr(str.length - length, str.length) == char) {
        str = str.substr(0, str.length - length);
    }
    return str;
}

// ------------------------ 设备硬件相关 ------------------------
/**
 * 取得设备屏幕宽度
 * @returns {*|jQuery}
 */
function getDeviceWidth(){
    return $(window).width();
}

// ------------------------ 其他公共方法 ------------------------
/**
 * 检测数据是否为空并获取焦点
 * @param id 数据的id
 * @param msg 提示信息
 * @param tipId 提示生成区域id
 * @return
 */
function checkDataIsNull(id, msg, tipId){
    var resultVal = $("#"+id).val();
    if(resultVal == '' || resultVal == null){
        //toAlertMessage(tipId, msg);
        showDialog(msg, "toast", "", "");
        $("#"+id).focus();
        return false;
    }
    return true;
}
/**
 * 检测数据合法性
 * @param regModel 正则模板
 * @param data 需要校验的数据
 * @return
 */
function checkDataReg(regModel, data) {
    var reg = new RegExp(regModel);
    return reg.test(data);
}

/**
 * 检测数据合法性并获取焦点
 * @param regModel 正则模板
 * @param data 需要校验的数据
 * @param msg 提示信息
 * @param tipId 提示生成区域id
 * @return
 */
function checkData(regModel, id, msg, tipId){
    var reg = new RegExp(regModel);
    var data = $("#"+id).val();
    var result =  reg.test(data);
    if(result == false){
        toAlertMessage(tipId, msg);
        $("#"+id).focus();
        return false;
    }
    return true;
}

/**
 * 打印日志（在constant.js中控制开关）
 * @param log
 */
function plog(log){
    //if(isPrintLog){
        console.log(log);
    //}
}

/**
 * 获取url中的参数
 */
function getUrlParam(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
    var r = window.location.search.substr(1).match(reg);  //匹配目标参数
    if (r != null) return unescape(r[2]);
    return null; //返回参数值
}

/**
 * 获得根目录
 * @returns
 */
function getBasePath(){
    var curWwwPath=window.document.location.href;
    var pathName=window.document.location.pathname;
    var pos=curWwwPath.indexOf(pathName);
    var localhostPaht=curWwwPath.substring(0,pos);
    var projectName=pathName.substring(0,pathName.substr(1).indexOf('/')+1);
    return(localhostPaht+projectName);
}


	
function toServerUrl (url) {

	if (url.indexOf("?") > -1) {
		url += "&requestDataType=json";
	} else {
		url += "?requestDataType=json";
	}

	var tempId = getUrlTokenId();

	if(tempId != null ||tempId !="null"){
		url+="&tokenId="+tempId;// 设置tokenId
	}

	url+="&ts="+new Date().getTime();
	plog("the url:"+getBasePath() + url);
	return getBasePath() + url;
}


function toServerPageUrl(url){
	if (url.indexOf("?") > -1) {
		url += "&ts="+new Date().getTime();
	} else {
		url += "?ts="+new Date().getTime();
	}
	
	var tempId = getUrlTokenId();

	if(tempId != null ||tempId !="null"){
		url+="&tokenId="+tempId;// 设置tokenId
	}
	return getBasePath() + url;
}

	

function getUrlTokenId() {
    var reg = new RegExp("(^|&)tokenId=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
    var r = window.top.location.search.substr(1).match(reg);  //匹配目标参数
    if (r != null && r != "") return unescape(r[2]);
    return null; //返回参数值
}


//
  

//格式化输入数字，并保留指定位小数  
//amount为原数字，_pow_为需要保留小数位数  
function powAmount(amount,_pow_) {  
     var amount_bak=amount;  
     var base=10;  
     if(isNaN(amount)){  
        //alert(amount+'必须为数字');  
        return;  
     }  
     if(isNaN(_pow_)){  
        //alert(_pow_+'必须为数字');  
        return;  
     }  
   amount = Math.round( ( amount - Math.floor(amount) ) *Math.pow(base,_pow_));  
   amount=amount<10 ? '.0' + amount : '.' + amount  
   amount=Math.floor(amount_bak)+amount;  
   return amount;  
}


/**
 * 判断是否是数字
 * @param number
 * @returns {Boolean}
 */
function isNumber(number){
	var isNum = /^\d+(\.\d+)?$/;
    if(!isNum.test(number)){       
        return false;
    }else{
    	return true;
    }
}

/**
 * 时间比较
 */
function dateCompare(startdate, enddate) {
	var arr = startdate.split("-");
	var starttime = new Date(arr[0], arr[1], arr[2]);
	var starttimes = starttime.getTime();

	var arrs = enddate.split("-");
	var lktime = new Date(arrs[0], arrs[1], arrs[2]);
	var lktimes = lktime.getTime();

	if (starttimes >= lktimes) {
		return false;
	} else
		return true;

}
/**
 * 弹出对话框
 * @param txt 文字内容
 * @param type 对话框类型 "info"：一个确定按钮；"confirm"：一个确定一个取消；"input"：一个输入框；"custom"：自定义
 * @param options 选项
 * @param dafaultValue 默认值
 */
function showDialog(txt, type, options,dafaultValue) {
    if (type == "info") {
        new ModalDialog(type, txt, options);
        //window.wxc.xcConfirm(txt, window.wxc.xcConfirm.typeEnum.info, options);
    } else if (type == "confirm") {
        new ModalDialog(type, txt, options);
    } else if(type == "toast"){
        new ModalDialog(type, txt, options);
        //window.wxc.xcConfirm(txt, window.wxc.xcConfirm.typeEnum.confirm, options);
    } else if (type == "input") {
        window.wxc.xcConfirm(txt, window.wxc.xcConfirm.typeEnum.input, options,dafaultValue);
    } else if (type == "custom") {
        window.wxc.xcConfirm(txt, window.wxc.xcConfirm.typeEnum.custom, options);
    } else if(type == "image"){
        new ModalDialog(type, txt);
    }
}

/**
 * 获取字节数
 * @param str
 * @returns
 */
function getByteNum(str){
    var char = str.replace(/[^\x00-\xff]/g, '**');
    return char.length;
}
/**
 * 课程Id转换为name
 */
function courseId2Name(id){
	if(id == KEY_COURSE_BIOLOGY){
		return "生物";
	}
	if(id == KEY_COURSE_CHEMISTRY){
		return "化学";
	}
	if(id == KEY_COURSE_CHINESE){
		return "语文";
	}
	if(id == KEY_COURSE_ENGLISH){
		return "英语";
	}
	if(id == KEY_COURSE_GEOGRAPHY){
		return "地理";
	}
	if(id == KEY_COURSE_HISTORY){
		return "历史";
	}
	if(id == KEY_COURSE_MATH){
		return "数学";
	}
	if(id == KEY_COURSE_PHYSICAL){
		return "物理";
	}
	if(id == KEY_COURSE_POLITICS){
		return "政治";
	}
	if(id == KEY_GRADE_1){
		return "小学";
	}
	if(id == KEY_GRADE_2){
		return "初中";
	}
	if(id == KEY_GRADE_3){
		return "高中";
	}
}
/**
 * 秒转换为分钟
 */
function second2Min(s){
	var showTime = "";
	var min ;
	if(s>60){
		min = parseInt(s/60);
	}
	var second = s%60;
	if(min){
		showTime = min+"分钟";
		if(second != 0){
			showTime += second + "秒";
		}
	}else{
		showTime += second + "秒";
	}
	return showTime;
}
