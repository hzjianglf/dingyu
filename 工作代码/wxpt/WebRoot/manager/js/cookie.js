var meantime = 60*30* 1000;
var mouseTime = new Date();
var keyTime = new Date();
$(document).mousemove(function() {
//	 alert("鼠标");
	mouseTime = new Date();

});
$(window).keydown(function() {
	keyTime = new Date();
//	alert("键盘");
});
setInterval('chekTime()', 1000);
function chekTime() {
//	alert(mouseTime+"\n"+keyTime);
	var currentTime = new Date();// 当前时间
	if ((currentTime - mouseTime) >= meantime
			&& (currentTime - keyTime) >= meantime) {
		var date = new Date();
		date.setTime(date.getTime() - 10000);
		document.cookie = "wxpts" + "=a;expires=" + date.toGMTString()
				+ "; path=/";
		var str = document.cookie;
//		 alert(currentTime.getTime()+"当前时间 "+mouseTime.getTime()+"鼠标时间"+keyTime+"键盘时间"+(currentTime
//		 - keyTime)+"时间差"+(currentTime - mouseTime));
		alert("登录失效了，请重新登录");
		parent.location = "http://www.uniqyw.com/wxpt/site/manage!manage";
	} else {
//		 alert(currentTime+"当前时间 "+mouseTime+"鼠标时间"+keyTime+"键盘时间"+(currentTime
//				 - keyTime)+"时间差");
		var date = new Date();
		// var expireDays=10;
		// 将date设置为10天以后的时间
		date.setTime(date.getTime() + 60*30* 1000);
		// 将userId和userName两个cookie设置为10天后过期
		var str = document.cookie;
		var arrStr = document.cookie.split(";");
		
		for ( var i = 0; i < arrStr.length; i++) {
			var temp = arrStr[i].split("=");
			if (temp[0] == "wxpts") {
				str = temp[1];
			}
		}
		 /*alert(currentTime.getTime()+"当前时间 "+mouseTime.getTime()+"鼠标时间"+keyTime+"键盘时间"+(currentTime
				 - keyTime)+"时间差");*/
		document.cookie = "wxpts" + "=" + str + ";expires="
				+ date.toGMTString() + "; path=/";
	}
}
