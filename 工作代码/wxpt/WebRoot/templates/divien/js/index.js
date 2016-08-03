function getUrl(menuID,menuNamID){
	var urlStr = window.location.href;
	if(menuID == 0){
		window.location.href=urlStr;
	}else{
		var url = urlStr.split("!");
		window.location.href = url[0]+"!getMenuPage?"+url[1].split("?")[1]+"&menuNameID="+menuNamID +"&menuID="+menuID;
		//$("#url").html(url[0]+"!getMenuPage?"+url[1].split("?")[1]+"&menuNamID="+menuNamID);
	}
}