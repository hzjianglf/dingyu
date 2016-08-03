function getUrl(menuID, menuNamID) {
	var urlStr = window.location.href;
	var url = urlStr.split("!");
	if (menuID == 0) {
		window.location.href = url[0] + "!index?"
		+ url[1].split("?")[1].split("&")[0];
	} else {
		window.location.href = url[0] + "!getMenuPage?"
				+ url[1].split("?")[1].split("&")[0] + "&menuNameID="
				+ menuNamID + "&menuID=" + menuID;
	}
}
function getPage(pageID){
	var urlStr = window.location.href;
	var url = urlStr.split("!");
	window.location.href = url[0] + "!getPage?"
	+ url[1].split("?")[1].split("&")[0]+"&pageID="+pageID;
}

function getChildUrl(menuID, menuNamID) {
	var urlStr = window.location.href;
	var url = urlStr.split("!");
	window.location.href = url[0] + "!getChildMenu?"
				+ url[1].split("?")[1].split("&")[0] + "&menuNameID="
				+ menuNamID + "&menuID=" + menuID;
	
}
function getAll(menuID, menuNamID) {
	var urlStr = window.location.href;
	var url = urlStr.split("!");
	window.location.href = url[0] + "!getAll?"
				+ url[1].split("?")[1].split("&")[0] + "&menuNameID="
				+ menuNamID + "&menuID=" + menuID;

}
function getLogUrl(){
	var urlStr = window.location.href;
	var url = urlStr.split("!");
	window.location.href = url[0] + "!getLogUrl?"
				+ url[1].split("?")[1].split("&")[0];
}
function getSearch(){
	var urlStr = window.location.href;
	var url = urlStr.split("!");
	window.location.href = url[0] + "!getSearch?"
				+ url[1].split("?")[1].split("&")[0];
}
function fyInit(menuID, menuNamID){
	var urlStr = window.location.href;
	var url = urlStr.split("!");
	if (menuID == 0) {
		window.location.href = url[0] + "!index?"
		+ url[1].split("?")[1].split("&")[0];
	} else {
		window.location.href = url[0] + "!getFenye?"
				+ url[1].split("?")[1].split("&")[0] + "&menuNameID="
				+ menuNamID + "&menuID=" + menuID+"&pageNo=1"+"&pageCount=7";
	}
}
function fenye(pageNo){
	var urlStr = window.location.href;
	var url = urlStr.split("!index?");
	window.location.href = url[0] + "!getFenye?"+url[1].split("pageNo")[0]+"pageNo="+pageNo+"&pageCount=7";
	
}
