/**
 * 常量
 * @author 雷蕾
 */

/** 应用服务器地址 */
var urlServer = "http://51.97.0.88:9090/default/mopserver";

/**
 * 图片上传地址
 */
var image_serve="http://192.168.1.2:8888";
function toImgUrl(url) {
    return image_serve + url;
}

/** 是否打印日志 */
var isPrintLog = true;

/** 是否使用本地挡板数据（数据存放：json/） */
var isLocalData = true;

/** ajax请求类型，如果是挡板数据，则变为GET请求 */
var ajaxType = "POST";
if(isLocalData){
    ajaxType = "GET";
}

/** 实际请求的应用服务器地址，如果isLocalData = true，这个地址将会在使用时变为挡板数据地址 */
var requestUrl = urlServer;

/**
*  常用报文头信息
*  赋值 Request_Header.属性名称
**/
var requestHeader = {
					 "deviceInfo":"",
					 "deviceIp":"",
					 "deviceUniCode":"",
					 "interfaceVersion":"",
					 "serviceCode":"",
					 "tokenID":"",
					 "transCode":""};
/**
 * 常用报文体
 * 
 */
var requestBody = {};
/**
 * 请求报文
 */
var jsonmessage = {"request":{"requestHeader":'',"requestBody":''}};
/**
 * 遍历循环
 */
var channelLoop = [];

/**
 * 拼接用的特殊字符
 */
var SPECIAL_CHAR = "@___@";

/**
 * 手机号表达式
 */
REG_TEL = "((\\d{11})|(400|800)([0-9\\-]{7,10})|^((\\d{7,8})|(\\d{4}|\\d{3})-(\\d{7,8})|(\\d{4}|\\d{3})-(\\d{7,8})-(\\d{4}|\\d{3}|\\d{2}|\\d{1})|(\\d{7,8})-(\\d{4}|\\d{3}|\\d{2}|\\d{1}))$)";

/*
* 整数
*/
REG_INTEGER = "^[1-9]\\d*$";

KEY_GRADE_1 = "1"; // 小学
KEY_GRADE_2 = "2"; // 初中
KEY_GRADE_3 = "3"; // 高中
KEY_COURSE_CHINESE = "chi"; // 语文
KEY_COURSE_MATH = "m"; // 数学
KEY_COURSE_ENGLISH = "e"; // 英语
KEY_COURSE_PHYSICAL = "phy"; // 物理
KEY_COURSE_CHEMISTRY = "che"; // 化学
KEY_COURSE_BIOLOGY = "b"; // 生物
KEY_COURSE_GEOGRAPHY = "g"; // 地理
KEY_COURSE_HISTORY = "h"; // 历史
KEY_COURSE_POLITICS = "pol"; // 政治











