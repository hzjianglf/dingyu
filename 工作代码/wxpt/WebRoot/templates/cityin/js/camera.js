var pictureSource;		//图片来源

var destinationType;

var db = null;


function Photo(imagesrc, content, dateline, address, lat, lng) {
	this.imagesrc = imagesrc;
	this.content = content;
	this.dateline = dateline;
	this.address = address;
	this.latitude = lat;
	this.longitude = lng;
}


var lat = 0;

var lng = 0;

var addre = '';

var photo = null;

var isInit = false;

$(document).bind("pageinit", function() {

	var isHide = false;
	
	if(!isInit) {
		document.addEventListener("deviceready",onDeviceReady,false);
	}
	$('#camera').bind('click', function() {
		capturePhoto();
	});
	
	$('#album').bind('click', function() {
		getPhoto();
	});
	
	$('#publish').bind('click', function() {
		var imagesrc = $("#image").attr("src");
		var content = $("#text").val();
		var date = new Date();
		if(addre == '') {
			addre = "北京市天安门";
		}
		photo = new Photo(imagesrc, content, date.getTime(), addre, lat, lng);
		db.transaction(insert, errorCB, successCB);
		db.transaction(query, errorCB);
	});
	
	$('#ajax').bind('click', function() {
		$.getJSON("", function(json){
				alert(json);
		});
	});
	
	
	$('#menu').hide();
	
	$('#header').click(function() {
		if(!isHide) {
			$('#menu').hide();
			isHide = true;
		} else {
			$('#menu').show();
			isHide = false;
		}
	});
	
});

Date.prototype.format = function(format)
{
    var o =
    {
        "M+" : this.getMonth()+1, //month
        "d+" : this.getDate(),    //day
        "h+" : this.getHours(),   //hour
        "m+" : this.getMinutes(), //minute
        "s+" : this.getSeconds(), //second
        "q+" : Math.floor((this.getMonth()+3)/3),  //quarter
        "S" : this.getMilliseconds() //millisecond
    }
    if(/(y+)/.test(format))
    format=format.replace(RegExp.$1,(this.getFullYear()+"").substr(4 - RegExp.$1.length));
    for(var k in o)
    if(new RegExp("("+ k +")").test(format))
    format = format.replace(RegExp.$1,RegExp.$1.length==1 ? o[k] : ("00"+ o[k]).substr((""+ o[k]).length));
    return format;
}

// PhoneGap准备就绪，可以使用！
function onDeviceReady() {
	navigator.geolocation.getCurrentPosition(onSuccess, onError, { maximumAge: 3000, timeout: 24 * 60 * 60 * 1000, enableHighAccuracy: true });
	pictureSource=navigator.camera.PictureSourceType;
	destinationType=navigator.camera.DestinationType;
	db = window.openDatabase("Database", "1.0", "camera data", 10000);
    db.transaction(createDB, errorCB, successCB);
	db.transaction(query, errorCB);
	isInit = true;
}

function onSuccess(position) {
	lat = position.coords.latitude;
	lng = position.coords.longitude;
	var geocoder = new google.maps.Geocoder();
	geocoder.geocode( {'location': new google.maps.LatLng(lat, lng, true)}, function(results, status) {
		 if (status == google.maps.GeocoderStatus.OK) {
		 	addre = results[0].formatted_address;
		 } else {
		 	addre = "未知地址";
		 }
		 alert(addre);
	});
}

function onError(error) {
	alert("位置定位失败，是否打开位置服务！");
}

function createDB(tx) {
	tx.executeSql('CREATE TABLE IF NOT EXISTS photo (id INTEGER PRIMARY KEY, imagesrc VARCHAR, contents VARCHAR, address, VARCHAR, lat INTEGER, lng INTEGER, dateline INTEGER)');
}

function insert(tx) {
	tx.executeSql("INSERT INTO photo (imagesrc, contents, address, lat, lng, dateline) VALUES('"
	+ photo.imagesrc + "','"
	+ photo.content + "','" 
	+ photo.address + "','" 
	+ photo.latitude + "','" 
	+ photo.longitude + "','" 
	+ photo.dateline + "')");
}

function query(tx) {
	tx.executeSql("SELECT * FROM photo ORDER BY dateline DESC", [], querySuccess, errorCB);
}

function querySuccess(tx, results) {
	var photoList = $('#photoList');
	photoList.empty();
	photoList.listview('refresh',true);
	var result = '';
	for(var i = 0; i < results.rows.length; i++) {
		var date = new Date(results.rows.item(i).dateline);
		result += "<li><img src=\"" + results.rows.item(i).imagesrc + "\" />" 
		+ "<p class=\"speech\">" + results.rows.item(i).contents + "</p>" 
		+ "<h3>" + date.format('yyyy-MM-dd hh:mm:ss') + "</h3>" 
		+ "<p class=\"local\">在" + results.rows.item(i).address + "拍摄</p></li>";
	}
	photoList.append($(result));
	photoList.listview('refresh',true);
}

// 事务执行出错后调用的回调函数
function errorCB(tx, err) {
	alert("db error");
}

// 事务执行成功后调用的回调函数
function successCB() {
	
}

// 当成功获得一张照片的Base64编码数据后被调用
function onPhotoDataSuccess(imageURI) {
	// 取消注释以查看Base64编码的图像数据
	// console.log(imageData);
	// 获取图像句柄
	var image = document.getElementById('image');
	// 取消隐藏的图像元素
	image.style.display = 'block';
	// 显示拍摄的照片
	// 使用内嵌CSS规则来缩放图片
	image.src = imageURI;
}

// 当成功得到一张照片的URI后被调用
function onPhotoURISuccess(imageURI) {
	// 取消注释以查看图片文件的URI
	// console.log(imageURI);
	// 获取图片句柄
	var image = document.getElementById('image');
	// 取消隐藏的图像元素
	image.style.display = 'block';
	// 显示拍摄的照片
	// 使用内嵌CSS规则来缩放图片
	image.src = imageURI;
}

// “Capture Photo”按钮点击事件触发函数
function capturePhoto() {
	//设置数据源时要用文件不要使用BASE64
	navigator.camera.getPicture(onPhotoDataSuccess, onFail, { quality: 50, destinationType: Camera.DestinationType.FILE_URI });
}

//“From Photo Library”/“From Photo Album”按钮点击事件触发函数
function getPhoto(source) {
   	// 从设定的来源处获取图像文件URI
	navigator.camera.getPicture(onPhotoURISuccess, onFail, { quality: 50,
	destinationType: destinationType.FILE_URI,sourceType: pictureSource.PHOTOLIBRARY });
 }

 // 当有错误发生时触发此函数
function onFail(mesage) {
	alert('Failed because: ' + message);
}

