$(document).ready(function(){
		selectProvince(); 			  			
});
function getProvinceName(code){
	var region = getRegionData();
	var result ;
	$(region.provinces).each(function(i,ite){
		if(ite.c == code){
			result = ite.n;
		}
	});
	return result;
}
function getCityName(code){
	var region = getRegionData();
	var result ;
	$(region.cities).each(function(i,ite){
		$(ite.city).each(function(j,item){
			if(item.c == code){
				result = item.n;
			}
		});
	});
	return result;
}
function getCountryName(code){
	var region = getRegionData();
	var result ;
	$(region.counties).each(function(i,ite){
		$(ite.county).each(function(j,item){
			if(item.c == code){
				result = item.n;
			}
		});
	});
	return result;
}
	

function selectProvince(){  	
	
	/*
		$.ajax({
			url:"js/region.json",
			type:"post",
			dataType:"json",
			success:function(data){  					
				var p = data.region.provinces;
				
				$("#province").append("<option value='0'>选择省份</option>");
				for(var i = 0; i < p.length; i ++){
					$("#province").append("<option value='"+p[i].c+"'>"+p[i].n+"</option>");						
				}
				
			},
			error:function(xmlHttp,result){
				//alert(xmlHttp.status);
			}
		});
		*/
	
	var p = getRegionData().provinces;
	
	$("#province").append("<option value='0'>选择省份</option>");
	for(var i = 0; i < p.length; i ++){
		$("#province").append("<option value='"+p[i].c+"'>"+p[i].n+"</option>");						
	}
	}

function searchCity(){
	$("#city").empty();
	$("#county").empty();
	var proviceCode = $("#province").find("option:selected").val();		
	if(proviceCode != '0'){
		
		var cities = getRegionData().cities;
		for(var i = 0; i < cities.length; i ++){
			if(cities[i].province == proviceCode){
				var c = cities[i].city;
				$("#city").append("<option value='0'>选择城市</option>");
				for(var j = 0; j < c.length; j ++){
					$("#city").append("<option value='"+c[j].c+"'>"+c[j].n+"</option>");
				}
			}
		}
		
	/*
		$.ajax({
			url:"js/region.json",
			type:"post",
			dataType:"json",
			success:function(data){  					
				var cities = data.region.cities;
				for(var i = 0; i < cities.length; i ++){
					if(cities[i].province == proviceCode){
						var c = cities[i].city;
						$("#city").append("<option value='0'>选择城市</option>");
						for(var j = 0; j < c.length; j ++){
							$("#city").append("<option value='"+c[j].c+"'>"+c[j].n+"</option>");
						}
					}
				}
				
			}
		}); 
		*/
	}

	// 默认城市
	if(null != defaultCity  && "" != defaultCity){
		$("#city").val(defaultCity);
		searchCounty();
	}	
	
	
	
	
}

function searchCounty(){
	$("#county").empty();
	var cityCode = $("#city").find("option:selected").val();
	if(cityCode != '0'){
		
		var counties = getRegionData().counties;
		for(var i = 0; i < counties.length; i ++){
			if(counties[i].city == cityCode){
				var c = counties[i].county;
				$("#county").append("<option value='0'>选择县区</option>");
				
				for(var j = 0; j < c.length; j ++){
					$("#county").append("<option value='"+c[j].c+"'>"+c[j].n+"</option>");
				}
			}
		}
		
		/*
		$.ajax({
			url:"js/region.json",
			type:"post",
			dataType:"json",
			success:function(data){  					
				var counties = data.region.counties;
				for(var i = 0; i < counties.length; i ++){
					if(counties[i].city == cityCode){
						var c = counties[i].county;
						$("#county").append("<option value='0'>选择县区</option>");
						
						for(var j = 0; j < c.length; j ++){
							$("#county").append("<option value='"+c[j].c+"'>"+c[j].n+"</option>");
						}
					}
				}
				
			}
		}); 
		*/
	}

	// 默认区县
	if(null != defaultCounty && "" != defaultCounty){
		$("#county").val(defaultCounty);
	}	
	
}

var region = null;

function getRegionData(){
	
	if(region == null){
	
		/*
		var tmp= localStorage.getItem("region");
		
		if(tmp == null || tmp=="null"){
			$.ajax({
				url:"js/region.json",
				type:"post",
				dataType:"json",
				async: false,
				success:function(data){  
					tmp = JSON.stringify(data.region);
					localStorage.removeItem("region");
					localStorage.setItem("region", tmp);				
				}
			}); 
		}
		
		
		region = JSON.stringify(tmp);
		*/
		
		$.ajax({
			url:getRootPath()+"/js/region.json",
			type:"post",
			dataType:"json",
			async: false,
			success:function(data){  
				region = data.region;			
			}
		}); 
	}
	return region;
}


var defaultProvince = "";
var defaultCity = "";
var defaultCounty = "";

/* 设置默认值 */
function setDefaultSelect(province, city, county){
	defaultProvince = province;
	defaultCity = city;
	defaultCounty = county;

	// 默认省份
	$("#province").val(province);
	searchCity();
}
function getRootPath(){
    var curWwwPath=window.document.location.href;
    var pathName=window.document.location.pathname;
    var pos=curWwwPath.indexOf(pathName);
    var localhostPaht=curWwwPath.substring(0,pos);
    var projectName=pathName.substring(0,pathName.substr(1).indexOf('/')+1);
    return(localhostPaht+projectName);
}